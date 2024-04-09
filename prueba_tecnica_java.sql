CREATE DATABASE empresas_contratantes;

USE empresas_contratantes;

-- Tabla fuerte coder
CREATE TABLE coder(
	id int primary key auto_increment,
    nombre varchar(255) not null,
    apellidos varchar(255) not null,
    documento varchar(255) unique not null,
    cohorte int,
    cv TEXT
);

-- Tabla fuerte empresa
CREATE TABLE empresa(
	id int primary key auto_increment,
    nombre varchar(255) not null,
    sector varchar(255) not null,
    ubicacion varchar(255) not null,
    contacto varchar(255) not null
);

-- Tabla debil vacante
CREATE TABLE vacante(
	id int primary key auto_increment,
    empresa_id int,
    titulo varchar(255) not null,
    descripcion TEXT,
    duracion varchar(255),
    estado varchar(50),
    CONSTRAINT fk_empresa_id foreign key(empresa_id) REFERENCES empresa(id) on delete cascade
);

-- Tabla intermedia contratacion entre vacante coder
CREATE TABLE contratacion(
	id int primary key auto_increment,
    vacante_id int,
    coder_id int,
    fecha_aplicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado varchar(255) not null,
    salario decimal(10,2),
    CONSTRAINT fk_vacante_id foreign key(vacante_id) REFERENCES vacante(id) on delete cascade,
    CONSTRAINT fk_coder_id foreign key(coder_id) REFERENCES coder(id) on delete cascade
);

-- Agregar campos faltantes
ALTER TABLE vacante ADD tecnologia varchar(100);
ALTER TABLE vacante ADD clan varchar(50);

-- llenar con datos coder
INSERT INTO coder(nombre,apellidos, documento, cohorte, cv) VALUES("Santiago", "Echeverry Serna", "1214752148",1 ,"Javascript, html, css"),
("Adriana", "Ortiz Montoya", "1234567890",2 ,"Java, bases de datos, Springboot");

-- llenar con datos empresas
INSERT INTO empresa(nombre, sector, ubicacion, contacto) VALUES("Postobon", "Bello","Cerca a Fabricato-Autopista Bello","3003214567"),
("Confama","Medellin","Centro-San Ingnacion","3211234568");

-- llenar con datos vacante
INSERT INTO vacante(empresa_id, titulo, descripcion, duracion, estado,tecnologia,clan) VALUES(1,"Operador de producción y JAVA", "manejo de maquinaria y logistica en cadena de bebidas","3 meses","ACTIVO", "Javascript, html, css","Lovelace"),
(2,"C++ y oficina","Manejo herramientas ofimaticas, atencion al publico","2 meses","INACTIVO","Java, bases de datos, Springboot","Meta");


SELECT * FROM vacante INNER JOIN empresa ON vacante.empresa_id = empresa.id;coder



