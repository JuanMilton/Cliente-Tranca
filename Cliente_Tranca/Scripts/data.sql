-- ----------------------------
-- Records of advertencia
-- ----------------------------
BEGIN;
INSERT INTO "advertencia" VALUES ('1', '10', '2014-05-28 21:44:02', 'No deben comer en horarios de trabajo', '1');
INSERT INTO "advertencia" VALUES ('2', '5', '2014-05-27 21:44:34', 'Se debe tener el uniforme correspondiente en horarios de trabajo', '1');
INSERT INTO "advertencia" VALUES ('3', '6', '2014-05-22 21:45:10', 'Todos los días deben estar en horarios de trabajo puntuales', '1');
INSERT INTO "advertencia" VALUES ('4', '7', '2014-05-13 21:46:19', 'Las visitas sólo pueden ingresar en horarios permitidos, según el reglamento', '1');
INSERT INTO "advertencia" VALUES ('5', '2', '2014-05-12 21:46:56', 'Los vehículos de afuera del condominio no son prioridad', '1');
INSERT INTO "advertencia" VALUES ('6', '3', '2014-05-01 21:47:21', 'Feliz día del trabajo a todos!!! XD', '1');
COMMIT;

-- ----------------------------
-- Records of guardia
-- ----------------------------
BEGIN;
INSERT INTO "guardia" VALUES ('666666', 'Laura', 'Montaño Huaji', '666666');
INSERT INTO "guardia" VALUES ('777777', 'Julio', 'Equilea', '777777');
INSERT INTO "guardia" VALUES ('888888', 'Arnold', 'Rosales Toledo', '888888');
INSERT INTO "guardia" VALUES ('999999', 'Urbamo', 'Molina Cuellar', '999999');
COMMIT;

-- ----------------------------
-- Records of propietario
-- ----------------------------
BEGIN;
INSERT INTO "propietario" VALUES ('123456', 'Yoselin', 'Torrico Ojeda', null, '123456-C', 't');
INSERT INTO "propietario" VALUES ('147852', 'Aracely', 'Medrano Moreno', null, '147852-C', 't');
INSERT INTO "propietario" VALUES ('258963', 'Jenny', 'Church Añez', null, '258963-B', 't');
INSERT INTO "propietario" VALUES ('321654', 'Lusia', 'Mercado Mondragon', null, '321654-A', 't');
INSERT INTO "propietario" VALUES ('456789', 'Limberg', 'Herbas Soliz', null, '456789-C', 't');
INSERT INTO "propietario" VALUES ('4920550', 'Juan Milton', 'Chambi Mendoza', null, '4920550-A', 't');
INSERT INTO "propietario" VALUES ('654987', 'Yessica', 'Herbas Suarez', null, '654987-B', 't');
INSERT INTO "propietario" VALUES ('99887766', 'Bismarck', 'Villca Soliz', null, '99887766-B', 't');
COMMIT;

-- ----------------------------
-- Records of propietario_vehiculo
-- ----------------------------
BEGIN;
INSERT INTO "propietario_vehiculo" VALUES ('123456', '5678ZXY');
INSERT INTO "propietario_vehiculo" VALUES ('147852', '1234ABC');
INSERT INTO "propietario_vehiculo" VALUES ('258963', '4321CBA');
INSERT INTO "propietario_vehiculo" VALUES ('321654', '1111AAA');
INSERT INTO "propietario_vehiculo" VALUES ('456789', '456OAB');
INSERT INTO "propietario_vehiculo" VALUES ('4920550', '4321CBA');
INSERT INTO "propietario_vehiculo" VALUES ('4920550', '456OAB');
INSERT INTO "propietario_vehiculo" VALUES ('654987', '456OAB');
INSERT INTO "propietario_vehiculo" VALUES ('654987', '987SDF');
INSERT INTO "propietario_vehiculo" VALUES ('99887766', '1111AAA');
COMMIT;

-- ----------------------------
-- Records of telefono_propietario
-- ----------------------------
BEGIN;
INSERT INTO "telefono_propietario" VALUES ('66244453', '147852');
INSERT INTO "telefono_propietario" VALUES ('66332211', '123456');
INSERT INTO "telefono_propietario" VALUES ('66332889', '123456');
INSERT INTO "telefono_propietario" VALUES ('69852231', '258963');
INSERT INTO "telefono_propietario" VALUES ('69888522', '654987');
INSERT INTO "telefono_propietario" VALUES ('72266423', '456789');
INSERT INTO "telefono_propietario" VALUES ('76231523', '321654');
INSERT INTO "telefono_propietario" VALUES ('76521459', '4920550');
INSERT INTO "telefono_propietario" VALUES ('76553311', '654987');
INSERT INTO "telefono_propietario" VALUES ('76899321', '258963');
INSERT INTO "telefono_propietario" VALUES ('77370309', '4920550');
INSERT INTO "telefono_propietario" VALUES ('77441122', '123456');
INSERT INTO "telefono_propietario" VALUES ('77665544', '99887766');
INSERT INTO "telefono_propietario" VALUES ('78460453', '4920550');
COMMIT;

-- ----------------------------
-- Records of tranca
-- ----------------------------
BEGIN;
INSERT INTO "tranca" VALUES ('1', 'Tranca Central', 'INGRESO');
COMMIT;

-- ----------------------------
-- Records of vehiculo
-- ----------------------------
BEGIN;
INSERT INTO "vehiculo" VALUES ('1111AAA', 'Arash', '2014', null, '15', 't');
INSERT INTO "vehiculo" VALUES ('1234ABC', 'Toyota', '2000', null, '10', 't');
INSERT INTO "vehiculo" VALUES ('4321CBA', 'Nissan', '2010', null, '11', 't');
INSERT INTO "vehiculo" VALUES ('456OAB', 'Corolla', '2013', null, '14', 't');
INSERT INTO "vehiculo" VALUES ('5678ZXY', 'Ferrari', '2011', null, '12', 't');
INSERT INTO "vehiculo" VALUES ('987SDF', 'Hummer', '2012', null, '13', 't');
COMMIT;
