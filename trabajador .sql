create table trabajador (
	id int auto_increment primary key not null,
	nombre varchar(100) default '' not null,
	fecha_nacimiento varchar(20) default '' not null,
	rfc  varchar(13) default '' not null,
	nss  varchar(11) default '' not null,
	curp  varchar(28) default '' not null,
	doc_identidad  varchar(20) default '' not null,
	num_documento varchar(20) default '' not null,
	tipo_licencia varchar(20) default '' not null,
	num_licencia varchar(20) default '' not null,
	fecha_expedicion  varchar(20) default '' not null,
	fecha_vgencia varchar(20) default '' not null,
	status  varchar(3) default '' not null
);

create table trabajador_direccion (
	id int auto_increment primary key not null,
	calle  varchar(50) default '' not null,
	numexterior  varchar(20) default '' not null,
	numinterior  varchar(20) default '' not null,
	colonia  varchar(30) default '' not null,
	cp  varchar(5) default '' not null,
	ciudad  varchar(40) default '' not null,
	estado  varchar(30) default '' not null,
	pais  varchar(20) default '' not null,
	trabajador_id int default 0 not null,
	status  varchar(2) default '' not null
);

create table trabajador_contacto (
	id int auto_increment primary key not null,
	nombre varchar(50) default '' not null,
	telefono  varchar(12) default '' not null,
	celular  varchar(12) default '' not null,
	tel_emergencia  varchar(12) default '' not null,
	email varchar(50) default '' not null,
	status varchar(2) default 'a' not null
);

create table trabajador_banco (
	id int auto_increment primary key not null,
	banco_id int default 0 not null,
	tipo_cuenta varchar(10) default '' not null,
	num_cuenta varchar(15) default '' not null,
	clabe  varchar(18) default '' not null,
	num_tarjeta  varchar(16) default '' not null,
	status varchar(2) default 'a' not null
);

