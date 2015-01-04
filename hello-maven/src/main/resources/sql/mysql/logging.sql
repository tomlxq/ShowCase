# Logback: the reliable, generic, fast and flexible logging framework.
# Copyright (C) 1999-2010, QOS.ch. All rights reserved.
#
# See http://logback.qos.ch/license.html for the applicable licensing 
# conditions.

# This SQL script creates the required tables by ch.qos.logback.classic.db.DBAppender.
#
# It is intended for MySQL databases. It has been tested on MySQL 5.1.37 
# on Linux
USE photograph;

BEGIN;
DROP TABLE IF EXISTS logging_event_property;
DROP TABLE IF EXISTS logging_event_exception;
DROP TABLE IF EXISTS logging_event;
COMMIT;


BEGIN;
CREATE TABLE logging_event 
  (
    timestmp         BIGINT NOT NULL COMMENT '创建记录事件的时间戳',
    formatted_message  TEXT NOT NULL COMMENT '经 org.slf4j.impl.MessageFormatter  格式化后，被添加到记录事件的消息。',
    logger_name       VARCHAR(254) NOT NULL COMMENT '执行记录请求的 logger。',
    level_string      VARCHAR(254) NOT NULL COMMENT '记录事件的级别。',
    thread_name       VARCHAR(254) COMMENT '进程名称',
    reference_flag    SMALLINT COMMENT '用于标识那些关联了异常或 MDCproperty  值的记录事件。 其值由 ch.qos.logback.classic.db.DBHelper 计算。包含 MDC或上下文属性的记录事件的 reference_flag 是 1。包含异常的reference_flag 是 2。两者都包含的 reference_flag 是 3。',
    arg0              VARCHAR(254) COMMENT '参数1',
    arg1              VARCHAR(254) COMMENT '参数2',
    arg2              VARCHAR(254) COMMENT '参数3',
    arg3              VARCHAR(254) COMMENT '参数4',
    caller_filename   VARCHAR(254) NOT NULL COMMENT '执行记录请求的文件名。 ',
    caller_class      VARCHAR(254) NOT NULL COMMENT '执行记录请求的类。',
    caller_method     VARCHAR(254) NOT NULL COMMENT '执行记录请求的方法。',
    caller_line       CHAR(4) NOT NULL COMMENT '执行记录请求的行号。',
    event_id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '记录事件的数据库 ID。'
  );
COMMIT;

BEGIN;
CREATE TABLE logging_event_property
  (
    event_id	      BIGINT NOT NULL,
    mapped_key        VARCHAR(254) NOT NULL,
    mapped_value      TEXT,
    PRIMARY KEY(event_id, mapped_key),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );
COMMIT;

BEGIN;
CREATE TABLE logging_event_exception
  (
    event_id         BIGINT NOT NULL,
    i                SMALLINT NOT NULL,
    trace_line       VARCHAR(254) NOT NULL,
    PRIMARY KEY(event_id, i),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  ) COMMENT='记录系统错误信息';
COMMIT;