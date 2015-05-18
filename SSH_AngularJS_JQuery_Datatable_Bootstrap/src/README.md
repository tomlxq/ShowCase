讲述SSH框架下如何整合AngularJS， JQuery Datatable 以及Bootstrap。
use mysql;
UPDATE user SET Password = password('test123') WHERE User = 'ssh' ;
GRANT ALL PRIVILEGES ON ssh.* TO 'ssh'@'%' IDENTIFIED BY 'test123' WITH GRANT OPTION;
GRANT ALL ON mysql.proc TO 'ssh'@'%';
flush privileges;

-- select user,host from user;　
delete from user where user.user='';

CREATE DATABASE IF NOT EXISTS ssh;
USE ssh;

mysql -u root
create database ssh character set utf8;
