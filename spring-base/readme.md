#BeanFactory和FactoryBean的区别
* BeanFactory
BeanFactory是IOC最基本的容器，负责生产和管理bean，它为其他具体的IOC容器提供了最基本的规范，
例如DefaultListableBeanFactory,XmlBeanFactory,ApplicationContext 等具体的容器都是实现了BeanFactory，再在其基础之上附加了其他的功能。
BeanFactory，以Factory结尾，表示它是一个工厂类(接口)，用于管理Bean的一个工厂。在Spring中，BeanFactory是IOC容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。
Spring为我们提供了许多易用的BeanFactory实现，XmlBeanFactory就是常用的一个，该实现将以XML方式描述组成应用的对象及对象间的依赖关系。XmlBeanFactory类将持有此XML配置元数据，并用它来构建一个完全可配置的系统或应用。

实例化容器
````
Resource resource = new FileSystemResource("beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);
ClassPathResource resource = new ClassPathResource("beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml", "applicationContext-part2.xml"});
BeanFactory factory = (BeanFactory) context;
````
 基本就是这些了，接着使用getBean(String beanName)方法就可以取得bean的实例；BeanFactory提供的方法及其简单，仅提供了六种方法供客户调用：
````
　　boolean containsBean(String beanName) 判断工厂中是否包含给定名称的bean定义，若有则返回true
　　Object getBean(String) 返回给定名称注册的bean实例。根据bean的配置情况，如果是singleton模式将返回一个共享实例，否则将返回一个新建的实例，如果没有找到指定bean,该方法可能会抛出异常
　　Object getBean(String, Class) 返回以给定名称注册的bean实例，并转换为给定class类型
　　Class getType(String name) 返回给定名称的bean的Class,如果没有找到指定的bean实例，则排除NoSuchBeanDefinitionException异常
　　boolean isSingleton(String) 判断给定名称的bean定义是否为单例模式
　　String[] getAliases(String name) 返回给定bean名称的所有别名
````
* FactoryBean
FactoryBean是一个接口，当在IOC容器中的Bean实现了FactoryBean后，通过getBean(String BeanName)获取到的Bean对象并不是FactoryBean的实现类对象，而是这个实现类中的getObject()方法返回的对象。
要想获取FactoryBean的实现类，就要getBean(&BeanName)，在BeanName之前加上&。

以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身，如果要获取FactoryBean对象，请在id前面加一个&符号来获取。


#Java Spring 【@ContextConfiguration】java世界的那些注解
@ContextConfiguration Spring整合JUnit4测试时，使用注解引入多个配置文件

单个文件 
@ContextConfiguration(Locations="../applicationContext.xml")  
@ContextConfiguration(classes = SimpleConfiguration.class)

多个文件时，可用{}
@ContextConfiguration(locations = { "classpath*:/spring1.xml", "classpath*:/spring2.xml" })

@EnableAutoConfiguration”注解的作用在于让 Spring Boot 根据应用所声明的依赖来对 Spring 框架进行自动配置，这就减少了开发人员的工作量。注解“@RestController”和”@RequestMapping”由 Spring MVC 提供，用来创建 REST 服务。这两个注解和 Spring Boot 本身并没有关系。

只需要在主配置 Java 类上添加“@EnableAutoConfiguration”注解就可以启用自动配置。Spring Boot 的自动配置功能是没有侵入性的，只是作为一种基本的默认实现。开发人员可以通过定义其他 bean 来替代自动配置所提供的功能。比如当应用中定义了自己的数据源 bean 时，自动配置所提供的 HSQLDB 就不会生效。这给予了开发人员很大的灵活性。既可以快速的创建一个可以立即运行的原型应用，又可以不断的修改和调整以适应应用开发在不同阶段的需要。可能在应用最开始的时候，嵌入式的内存数据库（如 HSQLDB）就足够了，在后期则需要换成 MySQL 等数据库。Spring Boot 使得这样的切换变得很简单。


##Spring事务处理机制
事务特性　ACID
* 原子性  atomicity
* 一致性　consistency 
* 隔离性　isolation
* 持久性　durability
````
public interface PlatformTransactionManager {
    TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
    void commit(TransactionStatus status) throws TransactionException;
    void rollback(TransactionStatus status) throws TransactionException;
}
````
从这里可知具体的具体的事务管理机制对Spring来说是透明的，它并不关心那些，那些是对应各个平台需要关心的，所以Spring事务管理的一个优点就是为不同的事务API提供一致的编程模型，如JTA、JDBC、Hibernate、JPA。下面分别介绍各个平台框架实现事务管理的机制。

* JDBC事务
````
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource" />
</bean>
````
实际上，DataSourceTransactionManager是通过调用java.sql.Connection来管理事务，而后者是通过DataSource获取到的。通过调用连接的commit()方法来提交事务，同样，事务失败则通过调用rollback()方法进行回滚。
* Hibernate事务
````
 <bean id="transactionManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
    <property name="sessionFactory" ref="sessionFactory" />
 </bean>
````
sessionFactory属性需要装配一个Hibernate的session工厂，HibernateTransactionManager的实现细节是它将事务管理的职责委托给org.hibernate.Transaction对象，而后者是从Hibernate Session中获取到的。当事务成功完成时，HibernateTransactionManager将会调用Transaction对象的commit()方法，反之，将会调用rollback()方法。

* Java持久化API事务（JPA）

Hibernate多年来一直是事实上的Java持久化标准，但是现在Java持久化API作为真正的Java持久化标准进入大家的视野。如果你计划使用JPA的话，那你需要使用Spring的JpaTransactionManager来处理事务。你需要在Spring中这样配置JpaTransactionManager：

    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="sessionFactory" ref="sessionFactory" />
    </bean>

JpaTransactionManager只需要装配一个JPA实体管理工厂（javax.persistence.EntityManagerFactory接口的任意实现）。JpaTransactionManager将与由工厂所产生的JPA EntityManager合作来构建事务。

* Java原生API事务

如果你没有使用以上所述的事务管理，或者是跨越了多个事务管理源（比如两个或者是多个不同的数据源），你就需要使用JtaTransactionManager：

    <bean id="transactionManager" class="org.springframework.transaction.jta.JtaTransactionManager">
        <property name="transactionManagerName" value="java:/TransactionManager" />
    </bean>

JtaTransactionManager将事务管理的责任委托给javax.transaction.UserTransaction和javax.transaction.TransactionManager对象，其中事务成功完成通过UserTransaction.commit()方法提交，事务失败通过UserTransaction.rollback()方法回滚。

##基本事务属性的定义
````
public interface TransactionDefinition {
    int getPropagationBehavior(); // 返回事务的传播行为
    int getIsolationLevel(); // 返回事务的隔离级别，事务管理器根据它来控制另外一个事务可以看到本事务内的哪些数据
    int getTimeout();  // 返回事务必须在多少秒内完成
    boolean isReadOnly(); // 事务是否只读，事务管理器能够根据这个返回值进行优化，确保事务是只读的
} 
````


##隔离级别：
    Read Uncommitted（读未提交）
    Read Committed（读已提交）
    Repeatable Read（可重复读）（MySQL默认隔离级别)
    Serializable（串行化）
    
    脏读、不可重复读、幻读
    脏读：一事务对数据进行了增删改,但未提交,有可能回滚,另一事务却读取了未提交的数据。
    不可重复读: 一事务对数据进行了更新或删除操作,另一事务两次查询的数据不一致。 
    幻读: 一事务对数据进行了新增操作,另一事务两次查询的数据不一致。
    脏读：主要是针对列内容的变化。
    幻读：主要是针对行数的变化

##Spring事务管理 @Transacational
    Isolation（隔离性）：
        DEFAULT：  使用数据库设置
        READ_UNCOMMITTED： 会出现脏读、不可重复读、幻读 
        READ_COMMITTED： 会出现不可重复读、幻读问题
        REPEATABLE_READ： 会出幻读
        SERIALIZABLE： 保证所有的情况不会发生
    propagation （传播性）：
        requeired
        supports
        requried_new
        not supported
        mandatory
        never
        nested
事务的第一个方面是传播行为（propagation behavior）。当事务方法被另一个事务方法调用时，必须指定事务应该如何传播。例如：方法可能继续在现有事务中运行，也可能开启一个新事务，并在自己的事务中运行。
Spring定义了七种传播行为：

    传播行为	含义
    PROPAGATION_REQUIRED	表示当前方法必须运行在事务中。如果当前事务存在，方法将会在该事务中运行。否则，会启动一个新的事务
    PROPAGATION_SUPPORTS	表示当前方法不需要事务上下文，但是如果存在当前事务的话，那么该方法会在这个事务中运行
    PROPAGATION_MANDATORY	表示该方法必须在事务中运行，如果当前事务不存在，则会抛出一个异常
    PROPAGATION_REQUIRED_NEW	表示当前方法必须运行在它自己的事务中。一个新的事务将被启动。如果存在当前事务，在该方法执行期间，当前事务会被挂起。如果使用JTATransactionManager的话，则需要访问TransactionManager
    PROPAGATION_NOT_SUPPORTED	表示该方法不应该运行在事务中。如果存在当前事务，在该方法运行期间，当前事务将被挂起。如果使用JTATransactionManager的话，则需要访问TransactionManager
    PROPAGATION_NEVER	表示当前方法不应该运行在事务上下文中。如果当前正有一个事务在运行，则会抛出异常
    PROPAGATION_NESTED	表示如果当前已经存在一个事务，那么该方法将会在嵌套事务中运行。嵌套的事务可以独立于当前事务进行单独地提交或回滚。如果当前事务不存在，那么其行为与PROPAGATION_REQUIRED一样。注意各厂商对这种传播行为的支持是有所差异的。可以参考资源管理器的文档来确认它们是否支持嵌套事务

##Spring分布式事务实现
分布式事务是指操作多个数据库之间的事务
spring的org.springframework.transaction.jta.JtaTransactionManager，提供了分布式事务支持。
如果使用WAS的JTA支持，把它的属性改为WebSphere对应的TransactionManager。 
在tomcat下，是没有分布式事务的，不过可以借助于第三方软件jotm（Java Open Transaction Manager ）和AtomikosTransactionsEssentials实现，在spring中分布式事务是通过jta（jotm，atomikos）来进行实现。
Spring分布式事务实现
http://blog.csdn.net/ithomer/article/details/10859235


##一般分布式事务处理模式包括：
	2阶段提交
	3阶段提交
	TCC（Try-Confirm-Cancel）
	可靠消息（消息队列、数据库表）
	SAGAS长事务
	补偿性事务

##duboo、spring cloud都可以算作是SOA框架
分布式事务控制支持依赖其他组件

	JTA（2阶段、3阶段）
	ActiveMQ消息中间件
	ByteTCC（TCC）
zookeeper、redis可以支持分布式锁

	redis对分布式锁的支持主要通过setnx，在使用redis分布式锁时候，一定要注意处理加锁客户端异常导致锁资源没有正常释放的情况（例如调用端down掉），在调用setnx时候需要加上对锁超时时间的判断。
	zookeeper对分布式锁的支持可以直接使用zookeeper curator-recipes客户端

##CAP（一致性、可用性、可靠性）
    一致性(Consistency)
    可用性(Availability)
    分区宽容度(Partition-Tolerant))
    
## 代理模式
1. 两个角色：被代理对象和执行者
2. 被代理对象必须要做，但不得不做的事，由代理对象做
3. 代理对象必须拿到代理对象的资料

* 动态代理
    产生一个接口,实现这个接口
    代理对象也要实现接口，并且生成代对象源代码，生成字节码文件，加载到JVM中
* cglib代理
    将被代理对象作为父类，生成子类，子类重写了子类的所有方法，并加了自已的操作
都做了一件事：字节码重组

