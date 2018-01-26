import com.google.common.collect.Lists;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang3.StringUtils;
import sun.misc.IOUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2018/1/26.
 */
public class ORMUtils {
    /**
     * 关闭数据库连接
     * @param conn 需要关闭的数据库连接对像
     */
    public static void close(Connection conn)
    {
        if(conn != null)
        {
            try
            {
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    /**
     * 执行需要Connection的查询语句
     * @param conn          数据库连接
     * @param sql           SQL语句
     * @param params        SQL语句参数列表
     * @return              查询结果集
     * @throws SQLException
     */
    public static ResultSet executeQuery(Connection conn,String sql,Object...params)
            throws SQLException{

        PreparedStatement pstmt = null;
        try
        {
            //执行SQL语句预编译
            pstmt = conn.prepareStatement(sql);
            //赋值参数
            for(int i=0; i<params.length; i ++){
                pstmt.setObject(i+1, params[i]);
            }
            //执行查询
            return pstmt.executeQuery();
        }catch(SQLException e){
            if(pstmt!=null)
                pstmt.close();
            throw new RuntimeException("SQL查询出错！",e);
        }
    }
    /**
     * 执行一条需要Connection的SQL语句
     * @param conn      数据库连接
     * @param sql       SQL语句
     * @param params    SQL语句的参数
     * @return          执行语句后影响的记录的行数
     * @throws SQLException
     */
    public static int executeUpdate(Connection conn,String sql,Object...params)
            throws SQLException{

        PreparedStatement pstmt = null;
        int rows = 0;
        //重组SQL语句执行
        try
        {
            pstmt = conn.prepareStatement(sql);
            for(int i=0; i<params.length; i++){
                pstmt.setObject(i+1, params[i]);
            }
            rows = pstmt.executeUpdate();
        }finally{
            if(pstmt!=null)
                pstmt.close();
        }

        return rows;
    }
    public static final String PRE_FIX = "t_";

    public static List<Employee> query(Class<Employee> employeeClass) throws IntrospectionException, SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Employee> list= Lists.newArrayList();
        //获得Employee类的BeanInfo
        BeanInfo employeeInfo = Introspector.getBeanInfo(employeeClass);
//2. 获得所有的属性,除了id和class
        PropertyDescriptor[] propNames =  employeeInfo.getPropertyDescriptors();
        List<String> fieldNames = new ArrayList<String>();  //类的属性列表
        for(int i = 0; i < propNames.length; i ++){
            if(!"class".equalsIgnoreCase(propNames[i].getName())){
                fieldNames.add(propNames[i].getName());
            }
        }
//SQL语句，查询表中的所有记录
        String sql = String.format("select * from %s%s", PRE_FIX,employeeClass.getName());
        System.out.println(sql);
        Connection conn = null;
        ResultSet rs = null;
        //创建一个List容器,里面存储的是一条记录
        conn = DbUtil.getConnection();
        rs = executeQuery(conn,sql) ;

        while (rs.next()) {
            Employee beanObj = new Employee();
           for(String line:fieldNames){
               PropertyDescriptor des = new PropertyDescriptor(line, beanObj.getClass());
               Method mr = des.getWriteMethod();
               System.out.println(rs.getObject(line));
               System.out.println(mr.getName());
               System.out.println(des.getName());
               System.out.println(des.getPropertyType());
               System.out.println("@@@@"+des.getPropertyType().getName());
               if(des.getPropertyType().getName().equals("java.lang.Integer")){
                   mr.invoke(beanObj,  rs.getInt(line));
               }else {
                   mr.invoke(beanObj, rs.getString(line));
               }
           }
            list.add(beanObj);
        }
        return list;
    }

    public static int insert(Employee employee) throws IntrospectionException, InvocationTargetException, IllegalAccessException, SQLException {

        Class<?> c=employee.getClass();
        StringBuffer s=new StringBuffer();
        s.append(String.format("insert into %s%s", PRE_FIX,c.getName()));
        BeanInfo employeeInfo = Introspector.getBeanInfo(c);
//2. 获得所有的属性,除了id和class
        PropertyDescriptor[] propNames =  employeeInfo.getPropertyDescriptors();
        List<String> fieldNames = new ArrayList<String>();  //类的属性列表
        StringBuffer v=new StringBuffer();
        for(int i = 0; i < propNames.length; i ++){
            if(!"class".equalsIgnoreCase(propNames[i].getName())){
                fieldNames.add(propNames[i].getName());
                PropertyDescriptor des = new PropertyDescriptor(propNames[i].getName(), c);
                Method m=des.getReadMethod();
                Object o= m.invoke(employee);
                System.out.println(m.getName());
                System.out.println(o);
                System.out.println(propNames[i].getPropertyType().getName());
                if(propNames[i].getPropertyType().getName().equals("java.lang.Integer")){
                    v.append(String.format("%s", o));
                }else{
                    v.append(String.format("'%s'", o));
                }
                if(i!=propNames.length-1){
                    v.append(",");
                }

            }
        }
        System.out.println(v);
        s.append(String.format("(%s)", StringUtils.join(fieldNames,",")));
        s.append(String.format(" values(%s)",v));
        System.out.println(s);
        Connection conn = null;
        conn = DbUtil.getConnection();
        return executeUpdate(conn,s.toString()) ;
    }
}
