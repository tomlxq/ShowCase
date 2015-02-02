1.配置pom.xml
给pom.xml加Mybatis的plugin：
<plugin>
   <groupId>org.mybatis.generator</groupId>
   <artifactId>mybatis-generator-maven-plugin</artifactId>
   <version>1.3.2</version>
           <configuration>
                 <verbose>true</verbose>
                 <overwrite>true</overwrite>
           </configuration>
</plugin>
2.配置generatorConfig.xml
生成的xml存放在src\main\resources路径下
3.执行maven命令
mvn mybatis-generator:generate
mvn org.mybatis.generator:mybatis-generator-maven-plugin:1.3.1:generate
生成Mybatis文件

create database sypro;
use mysql;
UPDATE user SET Password = password('test123') WHERE User = 'test' ;
GRANT ALL PRIVILEGES ON sypro.* TO 'test'@'%' IDENTIFIED BY 'test123' WITH GRANT OPTION;
GRANT ALL ON mysql.proc TO 'sypro'@'%';
flush privileges;


mvn archetype:create \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DgroupId=com.tom.demo \
  -DartifactId=ssmybatis
  参考
http://pan.baidu.com/s/1o6kFgEY