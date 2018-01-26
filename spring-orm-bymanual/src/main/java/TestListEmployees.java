import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tom on 2018/1/26.
 */
public class TestListEmployees {
    //使用List容器和Object数组来封装多条记录
    public static void main(String[] args) throws SQLException {
        //SQL语句，查询表中的所有记录
        String sql = "select * from t_employee";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //创建一个List容器,里面存储的是一条记录
        ArrayList<Object[]> rows = new ArrayList<Object[]>();

        conn =  DbUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while(rs.next()){
            //创建Object数组
            Object[] obj = new Object[3];
            obj[0] = rs.getObject("ename");
            obj[1] = rs.getObject("age");
            obj[2] = rs.getObject("address");
            //将Object数组中存储的一条数据记录添加到List容器中
            rows.add(obj);
        }

        //打印List中的记录
        for(Object[] obj: rows)
        {
            System.out.println(""+obj[0]+obj[1]+obj[2]);
        }
    }
}
