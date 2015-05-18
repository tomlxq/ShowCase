-- DROP DATABASE IF EXISTS `ssh`;
-- CREATE DATABASE IF NOT EXISTS `ssh`
USE `ssh`;

DROP TABLE IF EXISTS `PERSON`;
CREATE TABLE IF NOT EXISTS `PERSON` (
  `ID` int(9) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `BIRTHDAY` varchar(20) NOT NULL,
  `GENDER` varchar(5) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
INSERT INTO `PERSON` (`ID`, `NAME`, `BIRTHDAY`, `GENDER`) VALUES
    (1, 'NAME1', '1984-01-02', '男'),
    (2, 'NAME2', '1984-01-03', '男'),
    (3, 'NAME3', '1984-01-04', '男'),
    (4, 'NAME4', '1984-01-05', '女'),
    (5, 'NAME5', '1984-01-06', '男'),
    (6, 'NAME6', '1984-01-07', '女'),
    (7, 'NAME7', '1984-01-08', '男'),
    (8, 'NAME8', '1984-01-09', '男'),
    (9, 'NAME9', '1984-01-10', '男'),
    (10, 'NAME10', '1984-01-11', '女'),
    (11, 'NAME11', '1984-01-12', '男'),
    (12, 'NAME12', '1984-01-13', '男'),
    (13, 'NAME13', '1984-01-14', '女'),
    (14, 'NAME14', '1984-01-15', '男'),
    (15, 'NAME15', '1984-01-16', '男'),
    (16, 'NAME16', '1984-01-17', '女'),
    (17, 'NAME17', '1984-01-18', '男'),
    (18, 'NAME18', '1984-01-19', '人妖'),
    (19, 'NAME19', '1984-01-20', '男'),
    (20, 'NAME20', '1984-01-12', '男'),
    (21, 'NAME21', '1984-01-22', '男');