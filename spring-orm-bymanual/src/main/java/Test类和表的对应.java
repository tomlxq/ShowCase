import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2018/1/26.
 */
public class Test类和表的对应 {
    public static void main(String[] args) throws SQLException {
        //SQL语句，查询表中的所有记录
        String sql = "select * from t_employee";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn =  DbUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        //创建一个List容器,里面存储的是多条记录
        List<Employee> list = new ArrayList<Employee>();

        while(rs.next()){
            //将数据库的一条记录封装到一个对像中
            Employee emp = new Employee(rs.getInt(1),
                    rs.getInt(2),rs.getString(3),
                    rs.getString(4));

            //将对像添加到List容器中
            list.add(emp);
        }

        for(Employee emp: list)
        {
            System.out.println(emp);
        }
    }
}
