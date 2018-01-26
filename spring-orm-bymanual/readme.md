##1.数据库准备
创建数据库 springbootdb & 插入数据：
````
mysql> create database springbootdb;
mysql> use springbootdb;
mysql> DROP TABLE IF EXISTS  `t_employee`;
mysql> CREATE TABLE `t_employee` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
    `age` int(10) unsigned  NOT NULL COMMENT '年龄',
    `ename` varchar(25) DEFAULT NULL COMMENT '雇员名字',
    `address` varchar(25) DEFAULT NULL COMMENT '雇员地址',
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
mysql> INSERT t_employee VALUES (1 ,18,'罗小强','乐山。');
mysql> INSERT t_employee VALUES (2 ,20,'小明','深圳。');
mysql> INSERT t_employee VALUES (3 ,28,'小张','广东省。');
mysql> create user springbootdb;
mysql> grant all on springbootdb.* to springbootdb;
mysql> use mysql;
mysql> update user set authentication_string=password("123456") where user="springbootdb";
mysql> flush privileges;
````
#ORM开发终级思想
首先，分析一下我们为什么需要ORM，ORM为什么能实现出来。
1. 对数据的插入，SQL语句的语法为： insert  into  表名(字段名1,字段名2)  values(值1,值2)。
2. 对数据的删除，SQL语句的语法为： delete from 表名 where id=xxx。
3. 对数据的修改，SQL语句的语法为： update 表示 set 字段名=值 where id=xxx。
对于上面的语句，我写的并不是很准确，但可以看出一些规律来，表和类对应，那表名就可以通过反射得到，字段名可通过反得到，value值可以通过字段对应的方法获得，而id又包含在类的属性中。
所以，以对像为单位进行数据的存储是可行的！而不管JDBC底层和数据库的操作。

在应用层传过来的是一个具体的对像obj的情况下：
1. 获得类名 ——> 表名  
````
//获得类名,也即表名  
Class clazz = obj.getClass();  
String className = clazz.getSimpleName();  
````
2. 通过JavaBean获得类的字段    
````
//获得BeanInfo对像  
BeanInfo bean = Introspector.getBeanInfo(obj.getClass());  
//获得类的属性名  
PropertyDescriptor[] propNames =  bean.getPropertyDescriptors();  
````
3. 获得属性对应的名称，并找得到其对应的PropertyDescriptor   
````
//遍历属性List  
for(int i=0; i<fieldNames.size(); i++){  
    String fieldName = fieldNames.get(i);   //首先得到属性名  
    //根据属性名查找属性名对应的PropertyDescriptor  
    PropertyDescriptor propDes = findPropertyDescriptor(fieldName,propNames);  
}  

private static PropertyDescriptor findPropertyDescriptor(String propName,PropertyDescriptor[] proDesc){  
    for(int i=0; i<proDesc.length; i++){  
        if(propName.equals(proDesc[i].getName())){  
                return proDesc[i];  
        }  
    }  
    return null;  
}  
````
得到了PropertyDescriptor 一切都好说了！

4. 构建SQL语句

有了上面的这些东西，我们就知道了表名，字段名，还可以通过 PropertyDescriptor获得字段对应的值，那么我们就可以构建出完成的SQL语句了，然后采用JDBC来执行该SQL语句就OK。
 1. BeanInfo类：它的功能和目的就是为了获取对bean属性的控制权。这样的话，只需要提供属性名和所属的bean类，就可以为每个属性构建一个PropertyDescriptor。    
PropertyDescriptor des = new PropertyDescriptor("name", Person.class)  

2. PropertyDescriptor类

Method getReadMethod()  获得应该用于读取属性值的方法。   
Method getWriteMethod() 获得应该用于写入属性值的方法。   
注意到返回的是Method类型，通过反射中执行方法的知识我们可以很容易的利用它们。如：    
propDes.getReadMethod().invoke(obj)  
这样就得到了传过来对像的属性的值，其中propDes是这个属性的描述符

##MySQL java连接被拒绝：java.sql.SQLException: Access denied for user 'root'@'****' (using password: YES)
````
C:\Users\tom>mysql -uroot
mysql> grant all privileges on *.* to springbootdb@'%' identified by '123456';
````