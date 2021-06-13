DROP  DATABASE IF EXISTS hr;
create database hr;

DROP USER IF EXISTS hruser@'%';
CREATE USER 'hruser'@'%' IDENTIFIED BY 'password';
GRANT all ON *.* TO 'hruser'@'%';

use hr;

DROP TABLE IF EXISTS hr.user_role;

CREATE TABLE hr.user_role
	(
	id  VARCHAR (32) NOT NULL,
	uid VARCHAR (32),
	rid VARCHAR (32),
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.user_info;

CREATE TABLE hr.user_info
	(
	username VARCHAR (32) NOT NULL,
	phone    VARCHAR (32),
	address  VARCHAR (200),
	email    VARCHAR (200),
	unit     VARCHAR (32),
	`level`  VARCHAR (32),
	position VARCHAR (32),
	salary   VARCHAR (32),
	photo    VARCHAR (200),
	PRIMARY KEY (username)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.`user`;

CREATE TABLE hr.`user`
	(
	id       VARCHAR (32) NOT NULL,
	username VARCHAR (32),
	password VARCHAR (255),
	enabled  TINYINT,
	locked   TINYINT,
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.salary;

CREATE TABLE hr.salary
	(
	id     VARCHAR (32) NOT NULL,
	salary DECIMAL (10,2),
	notes  VARCHAR (400),
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.role;

CREATE TABLE hr.role
	(
	id     VARCHAR (32) NOT NULL,
	name   VARCHAR (32),
	nameZh VARCHAR (32),
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.org_unit;

CREATE TABLE hr.org_unit
	(
	id      VARCHAR (32) NOT NULL,
	name    VARCHAR (200),
	address VARCHAR (400),
	phone   VARCHAR (32),
	manager VARCHAR (32),
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.org_post;

CREATE TABLE hr.org_post
	(
	id    VARCHAR (32) NOT NULL,
	name  VARCHAR (32),
	notes VARCHAR (400),
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.menu;

CREATE TABLE hr.menu
	(
	id     VARCHAR (32) NOT NULL,
	sup_id VARCHAR (32),
	levels INT,
	name   VARCHAR (32),
	icon   VARCHAR (32),
	path   VARCHAR (32),
	sort   INT,
	rights VARCHAR (100),
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.holiday_flow;

CREATE TABLE hr.holiday_flow
	(
	id            VARCHAR (32) NOT NULL,
	user_id       VARCHAR (32),
	apply_date    DATETIME,
	bdate         DATETIME,
	edate         DATETIME,
	date_num      INT,
	approver_id   VARCHAR (32),
	approve_date  DATETIME,
	aprove_result INT,
	state         INT,
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.holiday;

CREATE TABLE hr.holiday
	(
	id              VARCHAR (32) NOT NULL,
	`user`          VARCHAR (32),
	total_num       INT,
	used_num        INT,
	applicating_num INT,
	left_num        INT,
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.employee_certificate;

CREATE TABLE hr.employee_certificate
	(
	id        VARCHAR (32) NOT NULL,
	`user`    VARCHAR (32),
	cert_no   VARCHAR (100),
	cert_name VARCHAR (100),
	cert_date DATETIME,
	notes     VARCHAR (100),
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

DROP TABLE IF EXISTS hr.certificate_flow;

CREATE TABLE hr.certificate_flow
	(
	id             VARCHAR (32) NOT NULL,
	type           INT,
	pre_id         VARCHAR (32),
	user_name      VARCHAR (32),
	apply_date     DATETIME,
	cert_no        VARCHAR (100),
	cert_name      VARCHAR (100),
	cert_date      DATETIME,
	approver_id    VARCHAR (32),
	approve_date   DATETIME,
	approve_result INT,
	state          INT,
	PRIMARY KEY (id)
	)DEFAULT charset=utf8;

