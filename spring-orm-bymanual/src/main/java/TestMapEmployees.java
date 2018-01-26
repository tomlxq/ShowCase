import com.google.common.collect.Maps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by tom on 2018/1/26.
 */
public class TestMapEmployees {
    public static void main(String[] args) throws SQLException {
        //SQL语句，查询表中的所有记录
        String sql = "select * from t_employee";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn =  DbUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        //创建一个Map容器,里面存储的是一条记录
        HashMap<String,Object> map = new HashMap<String,Object>();

        while(rs.next()){
            HashMap<String,Object> temp= Maps.newHashMap();
            String ename=rs.getObject("ename").toString();
            temp.put("empname",ename);
            temp.put("age",    rs.getObject("age"));
            temp.put("address", rs.getObject("address"));
            map.put(ename,temp);
        }

        //获取记录值所在的Set集合
        Set<String> set = map.keySet();
        for(String s: set)
        {
            //打印记录的内容
            System.out.print(s+" "+map.get(s) + "\t");
        }
    }
}
