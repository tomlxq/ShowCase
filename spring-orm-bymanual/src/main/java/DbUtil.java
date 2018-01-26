import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by tom on 2018/1/26.
 */

public class DbUtil {
    /*@Resource(name = "dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    public static Connection getConnection() {

        String driverName="com.mysql.jdbc.Driver";
        String dbURL="jdbc:mysql://localhost:3306/springbootdb?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
        String userName="springbootdb";
        String userPwd="123456";
        try{
            Class.forName(driverName);
            Connection conn= DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("连接数据库成功");
            return conn;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.print("连接失败");
        }
        return null;
    }
}
