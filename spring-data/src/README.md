参考
https://github.com/Fruzenshtein/spr-data
数据库配置
/*<!-- 初始化数据表结构 -->*/
	/*	<jdbc:initialize-database data-source="dataSource" ignore-failures="ALL" >
			<jdbc:script location="classpath:sql/mysql/schema.sql" encoding="UTF-8" />
			<jdbc:script location="classpath:sql/mysql/data.sql"  encoding="UTF-8"/>
		</jdbc:initialize-database>*/

create database hibnatedb;
use hibnatedb;
CREATE TABLE `hibnatedb`.`shops` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `employees_number` INTEGER UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY(`id`)
)
ENGINE = InnoDB;
	-- 发出GRANT语句增加新用户
use mysql;
delete from mysql.user where user='';
GRANT ALL PRIVILEGES ON hibnatedb.* TO 'hibuser'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'hibuser'@'%';
GRANT ALL ON mysql.proc TO 'hibuser'@'%';
flush privileges;