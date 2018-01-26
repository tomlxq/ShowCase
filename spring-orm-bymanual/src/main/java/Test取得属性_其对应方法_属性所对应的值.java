import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2018/1/26.
 */
public class Test取得属性_其对应方法_属性所对应的值 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Object obj=new Employee(4,88,"Jack","旧金山");
        // 1.通过JavaBean获得对像所在类的属性
        BeanInfo bean = null;
        try
        {
            bean = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e)
        {
            e.printStackTrace();
        }

        //2. 获得所有的属性,除了id和class
        PropertyDescriptor[] propNames =  bean.getPropertyDescriptors();

        List<String> fieldNames = new ArrayList<String>();  //类的属性列表
        for(int i = 0; i < propNames.length; i ++){
            if(!"id".equalsIgnoreCase(propNames[i].getName()) &&
                    !"class".equalsIgnoreCase(propNames[i].getName())){
                fieldNames.add(propNames[i].getName());
            }
        }

        //2.获得类的属性所对应的get/set方法
        for(int i=0; i<fieldNames.size(); i++){
            String fieldName = fieldNames.get(i);   //首先得到属性名
            //查找属性名对应的PropertyDescriptor
            PropertyDescriptor propDes = findPropertyDescriptor(fieldName,propNames);

            //根据属性的PropertyDescriptor得到属性,set方法名,get方法名
            System.out.println(propDes.getName()+"\t"+propDes.getReadMethod().getName()+"\t"+propDes.getWriteMethod().getName());
//得到对像的属性所对应的值
            System.out.println(propDes.getName()+" "+   //打印属性名
                    propDes.getReadMethod().invoke(obj)); //执行它的get方法得到属性值
        }
    }

    private static PropertyDescriptor findPropertyDescriptor(String fieldName, PropertyDescriptor[] propNames) {
        for(PropertyDescriptor line:propNames){
            if(line.getName().equals(fieldName)){
                return line;
            }
        }
        return null;
    }
}
