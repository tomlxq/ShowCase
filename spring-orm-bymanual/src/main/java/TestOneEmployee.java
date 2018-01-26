import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tom on 2018/1/26.
 */
public class TestOneEmployee {
    //通过JDBC获取的表的字段的值存储在Object类型的数组中，这样就达到了使用数组来封装一条数据记录的目的。
    public static void main(String[] args) throws SQLException {
        //SQL语句
        String sql = "select * from t_employee where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //创建一个Object数组,存储一条记录中的各个字段
        Object[] obj = new Object[3];

        conn =  DbUtil.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, 1);
        rs = pstmt.executeQuery();

        while(rs.next()){
            //获取记录字段
            obj[0] = rs.getObject("ename");
            obj[1] = rs.getObject("age");
            obj[2] = rs.getObject("address");
        }

        //打印这条记录
        System.out.println(""+obj[0]+obj[1]+obj[2]);
    }

}
