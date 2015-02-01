ShowCase
========
/*
use mysql;
UPDATE user SET Password = password('test123') WHERE User = 'test' ;
GRANT ALL PRIVILEGES ON koutu.* TO 'test'@'%' IDENTIFIED BY 'test123' WITH GRANT OPTION;
GRANT ALL ON mysql.proc TO 'koutu'@'%';
flush privileges;

-- select user,host from user;　
delete from user where user.user='';

CREATE DATABASE IF NOT EXISTS photograph;
USE photograph;

mysql -u root
create database ieasy;
mysql -u root --default-character-set=utf8 -B ieasy -e "source F:\data\wwwroot\tom_arch\opensource\ieasy\backup\sql\ieasy-2015-01-22 0917.sql"
*/
（转）struts2的零配置
http://www.cnblogs.com/fpjason/archive/2009/08/01/1536671.html
Interceptors 可以在方法级进行指定，使用Action 注解或在类上使用 InterceptorRefs注解。Class级别的拦截会被应用到类包含的所有action上。

