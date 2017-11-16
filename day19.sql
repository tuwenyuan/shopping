create database day19;
use day19;
create table category(
	id varchar(100) primary key,
	name varchar(100) unique not null,
	description varchar(255)
);
create table book(
	id varchar(100) primary key,
	name varchar(100) not null,
	author varchar(100),
	price float(8,2),
	photo varchar(100),
	description varchar(255),
	categoryId varchar(100),
	constraint categoryId_fk foreign key(categoryId) references category(id)
);
create table user(#前台用户注册信息使用
	id varchar(100) primary key,
	username varchar(100) unique not null,
	nick varchar(100) not null,
	password varchar(100) not null,
	phonenum varchar(100) not null,
	address varchar(100) not null,
	email varchar(100) not null,
	code varchar(100) unique,
	actived bit(1)
);
create table orders(
	id varchar(100) primary key,
	ordersnum varchar(100) unique not null,
	num int,
	money float(8,2),
	createtime timestamp,
	status int,
	userId varchar(100),
	constraint userId_fk foreign key(userId) references user(id)
);
create table orders_item(
	id varchar(100) primary key,
	num int,
	price float(8,2),
	bookId varchar(100),
	ordersId varchar(100),
	constraint bookId_fk foreign key(bookId) references book(id),
	constraint ordersId_fk foreign key(ordersId) references orders(id)
);

#以下是后台的权限管理需要用到的表格
create table menu(
	id varchar(100) primary key,
	name varchar(100) unique,
	uri varchar(100),
	description varchar(255)
);
create table role(
	id varchar(100) primary key,
	name varchar(100) unique,
	description varchar(255)
);
create table manager(#存后台管理用户的基本信息
	id varchar(100) primary key,
	username varchar(100) unique not null,
	password varchar(100),
	nick varchar(100)
);
create table menu_role(
	m_id varchar(100),
	r_id varchar(100),
	primary key(m_id,r_id),
	constraint m_id_fk foreign key(m_id) references menu(id),
	constraint r_id_fk1 foreign key(r_id) references role(id)
);
create table role_manager(
	r_id varchar(100),
	manager_id varchar(100),
	primary key(r_id,manager_id),
	constraint manager_id_fk foreign key(manager_id) references manager(id),
	constraint r_id_fk2 foreign key(r_id) references role(id)
);