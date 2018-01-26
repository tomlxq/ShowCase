import java.beans.*;

/**
 * Created by tom on 2018/1/26.
 */
public class TestJavaBean基本测试 {
    public static void main(String[] args) throws IntrospectionException {
        //1. 获得Employee类的BeanInfo
        BeanInfo employeeInfo = Introspector.getBeanInfo(Employee.class);

        //2. 获得Employee类的属性
        System.out.println("获得Employee类的属性");
        PropertyDescriptor[] fieldName = employeeInfo.getPropertyDescriptors();
        for(int i=0; i<fieldName.length; i++){
            System.out.println(fieldName[i].getName());
        }

        //3. 获得Employee类的方法
        System.out.println("获得Employee类的方法");
        MethodDescriptor[] methodName = employeeInfo.getMethodDescriptors();
        for(int i=0; i<methodName.length; i++){
            System.out.println(methodName[i].getName());
        }
    }
}
