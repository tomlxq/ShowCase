-纯手写实现Springmvc基本功能，包括扫描注解依赖注入，通过访问注解上的url访问到这个方法。
+这就是一个简单的javaweb项目，大家导入到eclipse里面直接可以run起来

* 在maven中用tomcat8跑:

tomcat7:run
F:/data/wwwroot/ShowCase/spring-mvc-bymanual

* 主要逻辑：　
1. 实例化规则，加了@Controller、@Service注解的对象默认beanName就是类名首字母小写，同时也可以写别名，那么beanName就是别名 
2. 注入规则，加了@Autowired注解的属性，默认是通过这个属性的类型来找他的实现类，如果该接口实现类有多个那么抛出异常！那么我非得有两个实现类怎么办呢？可以，你需要给实现类起个别名比如@Service(“woShiNumberOne”)，然后注入的时候@Autowired(“woShiNumberOne”)这样注入就可以了。 
3. url映射方法，就是扫描每个Controller，如果类名上加了@RequestMapping注解那么这算是根目录，然后逐个扫描方法，只要方法上加了@RequestMapping注解那么这就是子目录，最后会将根目录+子目录拼接到一起映射到当前controller的当前方法。 
4. 方法参数注入规则，自动识别当前方法有多少个参数，除了request和response这两个参数以外的其他任何参数都需要加@RequestParam注解，用来给这个参数定义别名，否则无法注入进来！而且由于是简化版这里只支持基本数据类型的注入，不支持对象的注入，这点还没来得及写。

##元注解是指注解的注解，包括@Retention @Target @Document @Inherited四种。

1. @Retention: 定义注解的保留策略
````
@Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
@Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
````
首 先要明确生命周期长度 SOURCE < CLASS < RUNTIME ，所以前者能作用的地方后者一定也能作用。一般如果需要在运行时去动态获取注解信息，那只能用 RUNTIME 注解；如果要在编译时进行一些预处理操作，比如生成一些辅助代码（如 ButterKnife），就用 CLASS注解；如果只是做一些检查性的操作，比如 @Override 和 @SuppressWarnings，则可选用 SOURCE 注解。


2. @Target：定义注解的作用目标
源码为：
````
@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.ANNOTATION_TYPE)  
public @interface Target {  
    ElementType[] value();  
}  
@Target(ElementType.TYPE)   //接口、类、枚举、注解
@Target(ElementType.FIELD) //字段、枚举的常量
@Target(ElementType.METHOD) //方法
@Target(ElementType.PARAMETER) //方法参数
@Target(ElementType.CONSTRUCTOR)  //构造函数
@Target(ElementType.LOCAL_VARIABLE)//局部变量
@Target(ElementType.ANNOTATION_TYPE)//注解
@Target(ElementType.PACKAGE) ///包    
````
3. @Document：说明该注解将被包含在javadoc中
4. @Inherited：说明子类可以继承父类中的该注解

#ISSUES:
##maven启动 cannot be cast to javax.servlet.Filter/cannot be cast to javax.servlet.Servlet 报错
原因
折腾了好久，终于找出原因：servlet-api.jar与tomcat自带的包冲突
````
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.4</version>
    <scope>provided</scope>  <!--加上即可-->
</dependency>
````
<scope>，它主要管理依赖的部署。目前<scope>可以使用5个值：  
1. compile，默认值，会随着项目一起发布。 
2. provided，类似compile，希望运行容器提供。 
3. runtime，运行时使用。 
4. test，只在测试时使用,不会用于发布。 
5. system，类似provided

##写个接口的实现类，在方法的前面加了@Override居然报错
据说这是jdk的问题，@Override是JDK5就已经有了，但有个小小的Bug，就是不支持对接口的实现，认为这不是Override 而JDK6修正了这个Bug，无论是对父类的方法覆盖还是对接口的实现都可以加上@Override。
首先要确保安装了jdk 1.6，
然后，在eclipse中修改配置，在 Windows->Preferences-->java->Compiler-->compiler compliance level 中选择 1.6，
，刷新工程，重新编译下。
如果还是不行，就在报错的工程上，鼠标右键选择 Properties-->Java Compiler-->compiler compliance level 中选择 1.6,刷新工程，重新编译下。

 	 
