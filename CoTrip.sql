drop database app;

create database app;

use app;

create table CITY(
	cityname varchar(255) primary key not null
);

create table USER(
	id int primary key not null auto_increment,
	username varchar(255) unique not null,
	password varchar(255),
	city varchar(255),
	foreign key (city) references CITY (cityname)
);

create table PLACE(
	id int primary key not null auto_increment,
	type enum("store","restaurant","bar","cafe","university","museum","library" ),
	address varchar(255),
	name varchar(255) unique not null,
	url varchar(255),
	rating float
);

create table TRIP(
	id int primary key not null auto_increment,
	trip_date Date,
	place_id int,
	trip_name varchar(255) unique not null,
	foreign key (place_id) references PLACE (id)
);

create table USER_TRIP(
	id int primary key not null auto_increment,
	comment varchar(255),
	score enum("1","2","3","4","5"),
	user_id int not null,
	trip_id int not null,
	unique (user_id, trip_id),
	foreign key (user_id) references USER (id),
	foreign key (trip_id) references TRIP (id)
);

create table ACCEPT_LIST(
	id int primary key not null auto_increment,
	user_id int not null,
	trip_id int not null,
	unique(user_id, trip_id),
	foreign key (user_id) references USER(id),
	foreign key (trip_id) references TRIP(id)
);






insert into city (cityname) values ("Boston");
insert into city (cityname) values ("Los Angeles");
insert into city (cityname) values ("New York");
insert into city (cityname) values ("San Francisco");



