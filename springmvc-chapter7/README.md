编程式数据验证
声明式数据验证
spring3开始支持JSR-303验证框架，JSR-303支持XML风格的和注解风格的验证
（1、添加jar包：
此处使用Hibernate-validator实现（版本：hibernate-validator-4.3.0.Final-dist.zip），将如下jar包添加到classpath（WEB-INF/lib下即可）：
 写道
dist/lib/required/validation-api-1.0.0.GA.jar JSR-303规范API包
dist/hibernate-validator-4.3.0.Final.jar Hibernate 参考实现
（2、在Spring配置总添加对JSR-303验证框架的支持

Java代码  收藏代码
<!-- 以下 validator  ConversionService 在使用 mvc:annotation-driven 会 自动注册-->
<bean id="validator"
class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
        <property name="providerClass"  value="org.hibernate.validator.HibernateValidator"/>
        <!-- 如果不加默认到 使用classpath下的 ValidationMessages.properties -->
        <property name="validationMessageSource" ref="messageSource"/>
</bean>
此处使用Hibernate validator实现：
validationMessageSource属性：指定国际化错误消息从哪里取，此处使用之前定义的messageSource来获取国际化消息；如果此处不指定该属性，则默认到classpath下的ValidationMessages.properties取国际化错误消息
（3、使用JSR-303验证框架注解为模型对象指定验证信息
Java代码  收藏代码
package cn.javass.chapter7.model;
import javax.validation.constraints.NotNull;
public class UserModel {
    @NotNull(message="{username.not.empty}")
    private String username;
}
通过@NotNull指定此username字段不允许为空，当验证失败时将从之前指定的messageSource中获取“username.not.empty”对于的错误信息，此处只有通过“{错误消息键值}”格式指定的才能从messageSource获取。
