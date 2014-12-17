ShowCase
========
maven安装配置

在Windows上安装Maven
echo %JAVA_HOME%
java -version

Maven的下载页面：http://maven.apache.org/download.html
M2_HOME，变量值为Maven的安装目录D:\bin\apache-maven-3.0
Path的变量，在变量值的末尾加上%M2_HOME%\bin;
设置MAVEN_OPTS的值为：-Xms128m -Xmx512m


运行如下命令检查Maven的安装情况
echo %M2_HOME%
mvn -v

修改库位置:
$M2_HOME/conf/settings.xml
<localRepository>${M2_HOME}/repository</localRepository>

http://www.cnblogs.com/dcba1112/archive/2011/05/01/2033805.html