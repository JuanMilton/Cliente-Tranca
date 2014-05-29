CREATE TABLE Ingreso_Salida_Visita ( 
	tipo integer,
	fecha_hora timestamp NOT NULL,
	ref_id_tranca integer NOT NULL,
	ref_placa_vehiculo_visita varchar(10) NOT NULL
)
;

CREATE TABLE Ingreso_Salida ( 
	tipo integer,
	fecha_hora timestamp NOT NULL,
	ref_placa_vehiculo varchar(10) NOT NULL,
	ref_id_tranca integer NOT NULL
)
;

CREATE TABLE visita_vehiculo ( 
	placa varchar(10) NOT NULL,
	ci varchar(10) NOT NULL
)
;

CREATE TABLE vehiculo_visita ( 
	placa varchar(10) NOT NULL,
	marca varchar(100),
	ci varchar(10)
)
;

CREATE TABLE visita ( 
	ci varchar(10) NOT NULL,
	nombres varchar(70) NOT NULL,
	apellidos varchar(200) NOT NULL
)
;

CREATE TABLE vehiculo ( 
	placa varchar(10) NOT NULL,
	marca varchar(100),
	modelo varchar(50),
	foto bytea,
	rfid integer NOT NULL,
	estado boolean NOT NULL
)
;

CREATE SEQUENCE tranca_id_seq INCREMENT 1 START 1
;

CREATE TABLE tranca ( 
	id integer DEFAULT nextval(('tranca_id_seq'::text)::regclass) NOT NULL,
	descripcion varchar(100),
	tipo integer NOT NULL
)
;

CREATE TABLE propietario_vehiculo ( 
	ci varchar(10) NOT NULL,
	placa varchar(10) NOT NULL
)
;

CREATE TABLE propietario ( 
	ci varchar(10) NOT NULL,
	nombres varchar(70) NOT NULL,
	apellidos varchar(200) NOT NULL,
	foto bytea,
	nro_licencia integer NOT NULL,
	estado boolean NOT NULL
)
;

CREATE TABLE telefono_propietario ( 
	telefono integer NOT NULL,
	ci varchar(10) NOT NULL
)
;

CREATE SEQUENCE Guardia_ci_seq INCREMENT 1 START 1
;

CREATE TABLE Guardia ( 
	ci varchar(10) DEFAULT nextval(('Guardia_ci_seq'::text)::regclass) NOT NULL,
	nombre varchar(100),
	apellido varchar(100),
	password varchar(50)
)
;

CREATE SEQUENCE Bitacora_id_seq INCREMENT 1 START 1
;

CREATE TABLE Bitacora ( 
	id bigint DEFAULT nextval(('Bitacora_id_seq'::text)::regclass) NOT NULL,
	fecha_hora timestamp,
	accion varchar(100),
	detalle varchar(250),
	ref_ci_guardia varchar(10)
)
;

CREATE SEQUENCE Advertencia_id_seq INCREMENT 1 START 1
;

CREATE TABLE Advertencia ( 
	id bigint DEFAULT nextval(('Advertencia_id_seq'::text)::regclass) NOT NULL,
	prioridad integer,
	fecha_hora timestamp,
	descripcion varchar(200),
	ref_id_tranca integer
)
;


ALTER TABLE Ingreso_Salida_Visita ADD CONSTRAINT PK_Ingreso_Salida_Visita 
	PRIMARY KEY (fecha_hora, ref_id_tranca, ref_placa_vehiculo_visita)
;


ALTER TABLE Ingreso_Salida ADD CONSTRAINT PK_Ingreso_Salida 
	PRIMARY KEY (fecha_hora, ref_placa_vehiculo, ref_id_tranca)
;


ALTER TABLE visita_vehiculo ADD CONSTRAINT PK_visita_vehiculo 
	PRIMARY KEY (placa, ci)
;


ALTER TABLE vehiculo_visita ADD CONSTRAINT PK_vehiculo_visita 
	PRIMARY KEY (placa)
;


ALTER TABLE visita ADD CONSTRAINT PK_visita 
	PRIMARY KEY (ci)
;


ALTER TABLE vehiculo ADD CONSTRAINT PK_vehiculo 
	PRIMARY KEY (placa)
;


ALTER TABLE tranca ADD CONSTRAINT PK_tranca 
	PRIMARY KEY (id)
;


ALTER TABLE propietario_vehiculo ADD CONSTRAINT PK_propietario_vehiculo 
	PRIMARY KEY (ci, placa)
;


ALTER TABLE propietario ADD CONSTRAINT PK_propietario 
	PRIMARY KEY (ci)
;


ALTER TABLE telefono_propietario ADD CONSTRAINT PK_telefono_propietario 
	PRIMARY KEY (telefono)
;


ALTER TABLE Guardia ADD CONSTRAINT PK_Guardia 
	PRIMARY KEY (ci)
;


ALTER TABLE Bitacora ADD CONSTRAINT PK_Bitacora 
	PRIMARY KEY (id)
;


ALTER TABLE Advertencia ADD CONSTRAINT PK_Advertencia 
	PRIMARY KEY (id)
;




ALTER TABLE Ingreso_Salida_Visita ADD CONSTRAINT FK_Ingreso_Salida_Visita_tranca 
	FOREIGN KEY (ref_id_tranca) REFERENCES tranca (id)
;

ALTER TABLE Ingreso_Salida_Visita ADD CONSTRAINT FK_Ingreso_Salida_Visita_vehiculo_visita 
	FOREIGN KEY (ref_placa_vehiculo_visita) REFERENCES vehiculo_visita (placa)
;

ALTER TABLE Ingreso_Salida ADD CONSTRAINT FK_Ingreso_Salida_tranca 
	FOREIGN KEY (ref_id_tranca) REFERENCES tranca (id)
;

ALTER TABLE Ingreso_Salida ADD CONSTRAINT FK_Ingreso_Salida_vehiculo 
	FOREIGN KEY (ref_placa_vehiculo) REFERENCES vehiculo (placa)
;

ALTER TABLE visita_vehiculo ADD CONSTRAINT FK_visita_vehiculo_visita 
	FOREIGN KEY (ci) REFERENCES visita (ci)
;

ALTER TABLE visita_vehiculo ADD CONSTRAINT FK_visita_vehiculo_vehiculo 
	FOREIGN KEY (placa) REFERENCES vehiculo_visita (placa)
;

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_propietario_vehiculo_propietario 
	FOREIGN KEY (ci) REFERENCES propietario (ci)
;

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_propietario_vehiculo_vehiculo 
	FOREIGN KEY (placa) REFERENCES vehiculo (placa)
;

ALTER TABLE telefono_propietario ADD CONSTRAINT FK_telefono_propietario 
	FOREIGN KEY (ci) REFERENCES propietario (ci)
;

ALTER TABLE Bitacora ADD CONSTRAINT FK_Bitacora_Guardia 
	FOREIGN KEY (ref_ci_guardia) REFERENCES Guardia (ci)
;

ALTER TABLE Advertencia ADD CONSTRAINT FK_Advertencia_tranca 
	FOREIGN KEY (ref_id_tranca) REFERENCES tranca (id)
;
