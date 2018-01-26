import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tom on 2018/1/26.
 */
public class TestORM {
    public static void main(String[] args) throws IntrospectionException, SQLException, InvocationTargetException, IllegalAccessException, InstantiationException {

       int count=ORMUtils.insert(new Employee(6,28,"bandk","yhv"));
        System.out.println(count);
        List<Employee> list=ORMUtils.query(Employee.class);
        System.out.println(list);
    }
}
