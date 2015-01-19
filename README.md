ShowCase
========

Test maven cases for it
目录汇总贴、PDF下载、源码下载
http://jinnianshilongnian.iteye.com/blog/1752171
https://github.com/zhangkaitao
http://www.iteye.com/topic/1132933



http请求信息包含六部分信息：
①请求方法，如GET或POST，表示提交的方式；
②URL，请求的地址信息；
③协议及版本；
④请求头信息（包括Cookie信息）；
⑤回车换行（CRLF）；
⑥请求内容区（即请求的内容或数据），如表单提交时的参数数据、URL请求参数（?abc=123 ？后边的）等。


TOM


手工加缺少的jar
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar -Dfile=F:\data\wwwroot\tom_arch\preference\spring3mvc\authority-1.0.0\WEB-INF\lib\ojdbc14-10.2.0.4.0.jar
mvn install:install-file -DgroupId=proxool -DartifactId=proxool -Dversion=0.9.1 -Dpackaging=jar -Dfile=F:\data\wwwroot\tom_arch\preference\spring3mvc\authority-1.0.0\WEB-INF\lib\proxool-0.9.1.jar
mvn install:install-file -DgroupId=proxool -DartifactId=proxool-cglib -Dversion=0.9.1 -Dpackaging=jar -Dfile=F:\data\wwwroot\tom_arch\preference\spring3mvc\authority-1.0.0\WEB-INF\lib\proxool-cglib-0.9.1.jar
mvn install:install-file -DgroupId=com.google.code -DartifactId=kaptcha -Dversion=2.3.3 -Dpackaging=jar -Dfile=F:\data\wwwroot\tom_arch\preference\spring3mvc\authority-1.0.0\WEB-INF\lib\kaptcha-2.3.3.jar



Spring4.1新特性（not ok）
http://jinnianshilongnian.iteye.com/blog/2103752