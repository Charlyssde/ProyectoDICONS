create table software(
numInventario integer not null autoincrement,
nombre varchar(20) not null,
numeroLicencias integer not null,
observaciones varchar(250) not null,
fechaAdquisicion date not null,
primary key(numInventario));


create table hardware (
numInventario integer not null autoincrement,
marca varchar(20) not null,
modelo varchar	(15) not null,
numSerie varchar(10) not null,
fechaAdquisicion date not null,
tipo (15) not null,
primary key(numInventario));


create table responsable (
numPersonal varchar(20) not null,
nombre varchar(50) not null,
teléfono varchar (20) not null,
correoInstitucional (35) not null,
extensión(10) not null),
primary key(numPersonal));

create table dictamen(
folio integer not null autoincrement,
fechaDictamen date not null,
tipoDictamen varchar(20) not null,
observaciones varchar(250) not null
descripcionDetallada varchar (500) not null,
primary key(folio));

create table tecnicoacademico(
numPersonal varchar(20) not null ,
nombre varchar (50) not null,
correoInstitucional varchar (60) not null,
telefono varchar(25) not null,
extensión varchar(10) not null),
primary key(numPersonal);

create table area(
idArea integer not null,
edificio varchar(35) not null,
programaEducativo(40) not null,
uso varchar (100) not null),
primary key(idArea);

create table jefecc(
numPersonal varchar(20) not null ,
nombre varchar(50) not null,
teléfono varchar (20) not null,
correoInstitucional (35) not null,
primary key(numPersonal)
);