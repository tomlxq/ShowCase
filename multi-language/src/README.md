itext的PDF生成方案(参看testPDF)
http://swordshadow.iteye.com/blog/1983935
Spring MVC+Freemarker+Javascript的多语言（国际化i18n/本地化）和主题（Theme）实现
http://www.cnblogs.com/Mainz/archive/2012/08/04/2622858.html
http://www.programdevelop.com/4379151/
JFreeReport使用方法总结
http://blog.csdn.net/nonetracer/article/details/2056738
Spring MVC 学习笔记 十二 PDF/Excel格式输出(OK)
http://starscream.iteye.com/blog/1072180
Spring MVC 学习笔记 PDF/Excel格式输出(OK)
http://www.cnblogs.com/crazy-fox/archive/2012/02/18/2357705.html
 用spring MVC 生成Excel和PDF
http://blog.csdn.net/linlzk/article/details/3389925(OK)
http://liuzidong.iteye.com/blog/1071823(OK)
Spring 3 mvc中返回pdf,json,xml等不同的view
http://jackyrong.iteye.com/blog/1874918
通过HTML生成pdf方案
http://downpour.iteye.com/blog/509417
http://blog.csdn.net/shanliangliuxing/article/details/6833471
http://downpour.iteye.com/blog/509417?page=4#comments
不用再关心资源路径问题，所有调试项目在web root中取值
http://www.duyaofei.com/2014/02/25/spring-mvc%E5%92%8Cfreemaker%E7%9A%84%E6%95%B4%E5%90%88%E4%BB%A5%E5%8F%8Afreemarker-ftl%E4%B8%AD%E8%8E%B7%E5%8F%96basepath%E5%BD%93%E5%89%8Dcontext%E7%9B%AE%E5%BD%95%E4%BF%A1%E6%81%AF/
cdn引用
http://developer.baidu.com/wiki/index.php?title=docs/cplat/libs
下载插件
http://jqueryui.com/resources/download/jquery-ui-1.11.2.zip
http://lx.cdn.baidupcs.com/file/fd77f983873b2c0182e7a724c30375dd?bkt=p-e1679a4cbb2fa5d80d8db1e7fa56de87&xcode=b82bb204e22f789269891212f3f4ed46c8abf377798e00360047804bae6a1ada&fid=3089496167-250528-3858809381&time=1421066691&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-YHxnkNdwVjdbB7IfMI%2BelMFeVhM%3D&to=sc&fm=Bei,B,T,t&sta_dx=0&sta_cs=21&sta_ft=zip&sta_ct=7&newver=1&newfm=1&flow_ver=3&sl=81723466&expires=1421067291&rt=sh&r=388991346&mlogid=1275664103&sh=1&vuk=3075728161&vbdid=1415757863&fin=uploadifive-v1.1.2-standard.zip&fn=uploadifive-v1.1.2-standard.zip

http://www.serv-u.com/Serv-U-Administrator-Guide.pdf
http://www.serv-u.com/serv-u_db_integration_guide.pdf

yum -y install mysql mysql-connector-odbc
vi /etc/odbc.ini
[tmlsbabu]
Description = The Database for Asterisk  ;这一句，不重要。
Trace       = On        ;这也可有可无
TraceFile   = stderr  ;这同上
Driver      = MySQL  ;是的，你没看错，他写的是MySQL不是 /usr/lib/libmyodbc3_r.so，这样他还会自动识别你是需要64还是32位的驱动
SERVER      = localhost
USER        = root
PASSWORD    = koutu567!@#
PORT        = 3306
DATABASE    = koutu
mysqldmin -uroot -p password 'koutu567!@#';



[root@bo-test bin]# find  / -name mysql -print
[root@bo-test bin]# groupadd mysql
[root@bo-test bin]# useradd -g mysql mysql
[root@bo-test bin]# chown -R mysql:mysql /usr/lib64/
yum install mysql-server


自动部署至外部Tomcat
CATALINA_HOME=C:\opensource\tomcat-7.0.34
Path的最后面添加%CATALINA_HOME%\lib; %CATALINA_HOME%\lib\servlet-api.jar;%CATALINA_HOME%\lib\jsp-api.jar
C:\opensource\tomcat-7.0.34（Tomcat目录）下的conf目录，编辑tomcat-users.xml
<tomcat-users>
<role rolename="manager"/>
<role rolename="admin"/>
<role rolename="manager-gui"/>
<user username = "admin" password = "password" roles = "admin,manager,manager-gui,manager-script,manager-jmx,manager-status" />
</tomcat-users>

补充： 使用外部的tomcat7 需要使用 tomcat-maven-plugin 的新版本，版本支持tomcat6和tomcat7，
groupId也已经由之前的org.codehaus.mojo改为org.apache.tomcat.maven。
可以参考：Maven的Tomcat插件地址为，http://tomcat.apache.org/maven-plugin.html。
修改项目的pom.xml在project的build节点下 添加tomcat-maven-plugin插件信息，如下写法添加了tomcat6和tomcat7的插件
<plugins>
 <plugin>
  <groupId>org.apache.tomcat.maven</groupId>
  <artifactId>tomcat6-maven-plugin</artifactId>
  <version>2.0-SNAPSHOT</version>
  <configuration>
  <url>http://localhost:8080/manager/html</url>
  <server>tomcat</server>
  </configuration>
 </plugin>
 <plugin>
  <groupId>org.apache.tomcat.maven</groupId>
  <artifactId>tomcat7-maven-plugin</artifactId>
  <version>2.0-SNAPSHOT</version>
  <configuration>
  <url>http://localhost:8080/manager/text</url>
  <server>tomcat7</server>
  <username>admin</username>
  <password>password</password>
  </configuration>
 </plugin>
</plugins>

在project节点下，在插件仓库（plugin repositories）和普通仓库（repositories）中添加以下仓库到pom.xml，保证maven可以从仓库中下载到tomcat-maven-plugin插件，少添加了这段信息，会出现如下报错信息：
No plugin found for prefix 'tomcat' in the current project and in the plug
<repositories>
<repository>
    <id>people.apache.snapshots</id>
    <url>http://repository.apache.org/content/groups/snapshots-group/</url>
    <releases>
        <enabled>false</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
</repositories>
<pluginRepositories>
<pluginRepository>
    <id>apache.snapshots</id>
    <name>Apache Snapshots</name>
    <url>
        http://repository.apache.org/content/groups/snapshots-group/
    </url>
    <releases>
        <enabled>false</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</pluginRepository>
</pluginRepositories>

修改%MAVEN_HOME%\conf\setting.xml
在<servers>标签中加入
<server>
       <id>tomcat7</id>
       <username>admin</username>
       <password>password</password>
</server>

启动tomcat
运行请先启动tomcat，在maven build的goals中输入命令tomcat7:deploy


