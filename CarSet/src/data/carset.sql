-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:8501
-- Tiempo de generación: 12-09-2009 a las 21:16:47
-- Versión del servidor: 4.1.21
-- Versión de PHP: 5.2.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `carset`
--

-- --------------------------------------------------------


--
-- Estructura de tabla para la tabla `co_comerciales`
--
CREATE TABLE IF NOT EXISTS `co_comerciales` (
  `co_id` int(5) NOT NULL auto_increment,
  `co_nombre` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `co_telefono` varchar(15) collate utf8_spanish_ci default NULL,
  `co_telefono2` varchar(15) collate utf8_spanish_ci default NULL,
  `co_email` varchar(50) collate utf8_spanish_ci default NULL,
  `co_cargo` varchar(50) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`co_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `co_comerciales`
--
INSERT INTO co_comerciales (co_nombre) VALUES ('Selecciona');
INSERT INTO co_comerciales (co_nombre) VALUES ('Sergio Cortés');
INSERT INTO co_comerciales (co_nombre) VALUES ('Jose Luis Cubillo');
INSERT INTO co_comerciales (co_nombre) VALUES ('Raúl Sánchez');
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cl_clientes`
--
CREATE TABLE IF NOT EXISTS `cl_clientes` (
  `cl_id` int(10) NOT NULL auto_increment,
  `cl_fecha` date NOT NULL default '0000-00-00',
  `cl_nombre` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `cl_DNI_CIF` varchar(10) collate utf8_spanish_ci NOT NULL default '',
  `cl_direccion` varchar(255) collate utf8_spanish_ci default NULL,
  `cl_cod_postal` varchar(5) collate utf8_spanish_ci default NULL,
  `cl_poblacion` varchar(80) collate utf8_spanish_ci default NULL,
  `cl_provincia` varchar(50) collate utf8_spanish_ci default NULL,
  `cl_direccion_fiscal` varchar(255) collate utf8_spanish_ci default NULL,
  `cl_cod_postal_fiscal` varchar(5) collate utf8_spanish_ci default NULL,
  `cl_poblacion_fiscal` varchar(80) collate utf8_spanish_ci default NULL,
  `cl_provincia_fiscal` varchar(50) collate utf8_spanish_ci default NULL,
  `cl_telefono` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `cl_telefono2` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `cl_fax` varchar(15) collate utf8_spanish_ci default NULL,
  `cl_email` varchar(50) collate utf8_spanish_ci default NULL,
  `cl_web` varchar(50) collate utf8_spanish_ci default NULL,
  `cl_plazo` varchar(30) collate utf8_spanish_ci default NULL,
  `cl_dias_plazo` int(3) NOT NULL default '0',
  `fp_id` int(3) NOT NULL default '0',
  `cl_incremento_ta` double default '0',
  `cl_num_cuenta` varchar(30) collate utf8_spanish_ci default NULL,
  `cl_estado` varchar(30) collate utf8_spanish_ci NOT NULL default 'Activo',
  `co_id` int(5) NOT NULL default '0',
  PRIMARY KEY  (`cl_id`),
  KEY `cl_nombre` (`cl_nombre`),
  KEY `fp_id` (`fp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `cp_contactos_proveedor`
--
INSERT INTO cl_clientes (cl_fecha, cl_nombre, cl_DNI_CIF, cl_direccion, cl_cod_postal, cl_poblacion, cl_provincia, cl_direccion_fiscal, cl_cod_postal_fiscal, cl_poblacion_fiscal, cl_provincia_fiscal, cl_telefono, cl_telefono2, cl_fax, cl_email, cl_web, cl_plazo, cl_dias_plazo, fp_id, cl_num_cuenta, cl_estado, co_id) VALUES ('2009-10-01', 'PLANTILLA','00000000L','NINGUNA','00000','NINGUNA','MADRID','NINGUNA' ,'00000','NINGUNA','MADRID','910000000','910000000','','','','30 días FF', '0','1','','Activo','1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dp_departamentos`
--
CREATE TABLE IF NOT EXISTS `dp_departamentos` (
  `dp_id` int(10) NOT NULL auto_increment,
  `dp_nombre` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `dp_descripcion` varchar(255) collate utf8_spanish_ci NOT NULL default '',
  PRIMARY KEY  (`dp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `dp_departamentos`
--
INSERT INTO dp_departamentos (dp_nombre, dp_descripcion) VALUES ('Selecciona', 'Seleccionar');
INSERT INTO dp_departamentos (dp_nombre, dp_descripcion) VALUES ('RRHH', 'Recursos Humanos');
INSERT INTO dp_departamentos (dp_nombre, dp_descripcion) VALUES ('Compras', 'Departamento de Compras');
INSERT INTO dp_departamentos (dp_nombre, dp_descripcion) VALUES ('Marketing', 'Departamento de Marketing');
INSERT INTO dp_departamentos (dp_nombre, dp_descripcion) VALUES ('Operaciones', 'Departamento de Operaciones');
INSERT INTO dp_departamentos (dp_nombre, dp_descripcion) VALUES ('Dirección', 'Departamento de Dirección');
INSERT INTO dp_departamentos (dp_nombre, dp_descripcion) VALUES ('Otros', 'Departamento sin determinar');
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fa_facturas`
--
CREATE TABLE IF NOT EXISTS `fa_factura_cliente` (
  `fa_id` int(8) NOT NULL auto_increment,
  `fa_fecha` date NOT NULL default '0000-00-00',
  `fa_mes` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `fa_anyo` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `cl_id` int(10) NOT NULL,
  `fa_fecha_pago` date default NULL,
  `fa_pagado` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`fa_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `fa_facturas`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fc_factores_correccion`
--
CREATE TABLE IF NOT EXISTS `fc_factores_correccion` (
  `fc_id` int(10) NOT NULL auto_increment,
  `fc_nombre` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `fc_descripcion` varchar(255) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`fc_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `fc_factores_correccion`
--

INSERT INTO `fc_factores_correccion` (`fc_nombre`, `fc_descripcion`) VALUES('Ninguno', 'Sin factor de corrección');
INSERT INTO `fc_factores_correccion` (`fc_nombre`, `fc_descripcion`) VALUES('Industriales y Monovolumen', 'Vehículos industriales y Monovolúmenes');
INSERT INTO `fc_factores_correccion` (`fc_nombre`, `fc_descripcion`) VALUES('Todoterreno', 'Vehículos Todoterreno');
INSERT INTO `fc_factores_correccion` (`fc_nombre`, `fc_descripcion`) VALUES('Furgonetas', 'Furgonetas');
INSERT INTO `fc_factores_correccion` (`fc_nombre`, `fc_descripcion`) VALUES('Furgones', 'Furgones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fp_forma_pago`
--
CREATE TABLE IF NOT EXISTS `fp_forma_pago` (
  `fp_id` int(3) NOT NULL auto_increment,
  `fp_tipo` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `fp_descripcion` varchar(255) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`fp_id`),
  KEY `fp_tipo` (`fp_tipo`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `fp_forma_pago`
--
INSERT INTO `fp_forma_pago` (`fp_tipo`, `fp_descripcion`) VALUES('Pagaré', 'Pagaré bancario');
INSERT INTO `fp_forma_pago` (`fp_tipo`, `fp_descripcion`) VALUES('Recibo Domiciliado', 'Recibo Domiciliado');
INSERT INTO `fp_forma_pago` (`fp_tipo`, `fp_descripcion`) VALUES('Transferencia', 'Pago por transferencia');
INSERT INTO `fp_forma_pago` (`fp_tipo`, `fp_descripcion`) VALUES('Confirming', 'Confirming');
INSERT INTO `fp_forma_pago` (`fp_tipo`, `fp_descripcion`) VALUES('Cheque', 'Pago por cheque');
INSERT INTO `fp_forma_pago` (`fp_tipo`, `fp_descripcion`) VALUES('Efectivo', 'Pago en efectivo');
INSERT INTO `fp_forma_pago` (`fp_tipo`, `fp_descripcion`) VALUES('Tarjeta', 'Pago por tarjeta');
INSERT INTO `fp_forma_pago` (`fp_tipo`, `fp_descripcion`) VALUES('Otros', 'Otros medios de pago');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gr_grupos`
--
CREATE TABLE IF NOT EXISTS `gr_grupos` (
  `gr_id` int(3) NOT NULL auto_increment,
  `gr_nombre` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `gr_descripcion` varchar(255) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`gr_id`),
  UNIQUE KEY `gr_nombre` (`gr_nombre`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `gr_grupos`
--

INSERT INTO `gr_grupos` (`gr_nombre`, `gr_descripcion`) VALUES('Administradores', 'Administradores de la aplicación');
INSERT INTO `gr_grupos` (`gr_nombre`, `gr_descripcion`) VALUES('Trabajadores', 'Trabajadores de la empresa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pc_pedidos_clientes`
--
CREATE TABLE IF NOT EXISTS `pc_pedidos_clientes` (
  `pe_num` int(10) NOT NULL,
  `cl_id` int(10) NOT NULL,
  PRIMARY KEY  (`pe_num`,`cl_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `pc_pedidos_clientes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pe_pedidos`
--
CREATE TABLE IF NOT EXISTS `pe_pedidos` (
  `pe_num` int(10) NOT NULL auto_increment,
  `pe_fecha` date NOT NULL default '0000-00-00',
  `pe_descripcion` varchar(255) collate utf8_spanish_ci default NULL,
  `pe_direccion_origen` varchar(255) collate utf8_spanish_ci NOT NULL default '',
  `pe_poblacion_origen` varchar(50) collate utf8_spanish_ci default NULL,
  `pe_provincia_origen` varchar(50) collate utf8_spanish_ci default NULL,
  `pe_cp_origen` varchar(5) collate utf8_spanish_ci NOT NULL default '',
  `pe_fecha_origen` date NOT NULL default '0000-00-00',
  `pe_hora_origen` varchar(5) collate utf8_spanish_ci default NULL,
  `pe_tipo_origen` varchar(30) collate utf8_spanish_ci NOT NULL default '',
  `pe_nombre_origen` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `pe_telefono_origen` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `pe_direccion_destino` varchar(255) collate utf8_spanish_ci NOT NULL default '',
  `pe_poblacion_destino` varchar(50) collate utf8_spanish_ci default NULL,
  `pe_provincia_destino` varchar(50) collate utf8_spanish_ci default NULL,
  `pe_cp_destino` varchar(5) collate utf8_spanish_ci NOT NULL default '',
  `pe_fecha_destino` date NOT NULL default '0000-00-00',
  `pe_hora_destino` varchar(5) collate utf8_spanish_ci default NULL,
  `pe_tipo_destino` varchar(30) collate utf8_spanish_ci NOT NULL default '',
  `pe_nombre_destino` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `pe_telefono_destino` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `pe_servicio` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `pe_servicio_origen` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `pe_servicio_destino` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `pe_servicio_especial` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `pe_dias_campa` int(5) default '0',
  `pe_ida_vuelta` tinyint(1) NOT NULL default '0',
  `pe_soporte` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `pe_ve_matricula` varchar(10) collate utf8_spanish_ci NOT NULL default '',
  `pe_ve_marca` varchar(30) collate utf8_spanish_ci default NULL,
  `pe_ve_modelo` varchar(30) collate utf8_spanish_ci default NULL,
  `pe_hora_real_origen` varchar(5) collate utf8_spanish_ci default NULL,
  `pe_hora_real_destino` varchar(5) collate utf8_spanish_ci default NULL,
  `pe_solred` double default '0',
  `pe_viaje` double default '0',
  `pe_ta_es_cliente` double default NULL,
  `pe_ta_es_proveedor` double default NULL,
  `pe_suplemento` double default NULL,
  `fc_id` int(10) NOT NULL default '0',
  `pe_estado` varchar(30) collate utf8_spanish_ci NOT NULL default 'Activo',
  `pe_activo` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`pe_num`),
  KEY `pe_fecha` (`pe_fecha`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `pe_pedidos`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pp_pedidos_proveedores`
--
CREATE TABLE IF NOT EXISTS `pp_pedidos_proveedores` (
  `pe_num` int(10) NOT NULL,
  `pr_id` int(10) NOT NULL,
  PRIMARY KEY  (`pe_num`,`pr_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `pp_pedidos_proveedores`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pr_proveedores`
--
CREATE TABLE IF NOT EXISTS `pr_proveedores` (
  `pr_id` int(10) NOT NULL auto_increment,
  `pr_fecha` date NOT NULL default '0000-00-00',
  `pr_nombre_fiscal` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `pr_DNI_CIF` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `pr_regimen` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `pr_tipo` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `pr_direccion` varchar(255) collate utf8_spanish_ci default NULL,
  `pr_cod_postal` varchar(5) collate utf8_spanish_ci default NULL,
  `pr_poblacion` varchar(50) collate utf8_spanish_ci default NULL,
  `pr_provincia` varchar(30) collate utf8_spanish_ci default NULL,
  `pr_direccion_fiscal` varchar(255) collate utf8_spanish_ci default NULL,
  `pr_cod_postal_fiscal` varchar(5) collate utf8_spanish_ci default NULL,
  `pr_poblacion_fiscal` varchar(80) collate utf8_spanish_ci default NULL,
  `pr_provincia_fiscal` varchar(50) collate utf8_spanish_ci default NULL,
  `pr_telefono` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `pr_telefono2` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `pr_fax` varchar(15) collate utf8_spanish_ci default NULL,
  `pr_email` varchar(50) collate utf8_spanish_ci default NULL,
  `pr_plazo` varchar(50) collate utf8_spanish_ci default NULL,
  `pr_dias_plazo` int(3) NOT NULL default '0',
  `fp_id` int(3) NOT NULL default '0',
  `pr_incremento_ta` double default '0',
  `pr_num_cuenta` varchar(30) collate utf8_spanish_ci default NULL,
  `pr_estado` varchar(50) collate utf8_spanish_ci NOT NULL default 'Activo',
  `pr_dia_factura` varchar(15) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`pr_id`),
  KEY `fp_id` (`fp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `pr_proveedores`
--
-- --------------------------------------------------------
INSERT INTO pr_proveedores (pr_fecha, pr_nombre_fiscal, pr_DNI_CIF, pr_regimen, pr_tipo, pr_direccion, pr_cod_postal, pr_poblacion, pr_provincia, pr_direccion_fiscal, pr_cod_postal_fiscal, pr_poblacion_fiscal, pr_provincia_fiscal, pr_telefono, pr_telefono2, pr_fax, pr_email, pr_plazo, pr_dias_plazo, fp_id, pr_num_cuenta, pr_estado, pr_dia_factura) VALUES ('2009-10-01', 'PLANTILLA', '000000L', 'Empresa','Gruero' ,'NINGUNA', '00000' ,'NINGUNA' ,'MADRID','','','','MADRID','910000000', '', '', '','30 días FF','', '1', '','Activo', '0');


--
-- Estructura de tabla para la tabla `tc_tarifas_clientes`
--
CREATE TABLE IF NOT EXISTS `tc_tarifas_clientes` (
  `tc_id` int(5) NOT NULL auto_increment,
  `tc_servicio` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `tc_servicio_origen` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `tc_servicio_destino` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `tc_soporte` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `tc_fecha_desde` date NOT NULL default '0000-00-00',
  `tc_fecha_hasta` date NOT NULL default '0000-00-00',
  `tc_incremento` double default '0',
  `tc_tarifa` double default '0',
  `cl_id` int(10) NOT NULL default '0',
  PRIMARY KEY  (`tc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `tc_tarifas_clientes`
--
INSERT INTO tc_tarifas_clientes (tc_servicio, tc_servicio_origen, tc_servicio_destino, tc_soporte, tc_fecha_desde, tc_fecha_hasta, tc_incremento, tc_tarifa, cl_id) VALUES ('Urbano', 'MADRID', 'MADRID', 'Grúa' , '2009-11-03', '2050-01-01', 0, 0, 1);
INSERT INTO tc_tarifas_clientes (tc_servicio, tc_servicio_origen, tc_servicio_destino, tc_soporte, tc_fecha_desde, tc_fecha_hasta, tc_incremento, tc_tarifa, cl_id) VALUES ('Urbano', 'MADRID', 'MADRID', 'Camión completo' , '2009-11-03', '2050-01-01', 0, 0, 1);
INSERT INTO tc_tarifas_clientes (tc_servicio, tc_servicio_origen, tc_servicio_destino, tc_soporte, tc_fecha_desde, tc_fecha_hasta, tc_incremento, tc_tarifa, cl_id) VALUES ('Urbano', 'MADRID', 'MADRID', 'Conductor' , '2009-11-03', '2050-01-01', 0, 0, 1);
INSERT INTO tc_tarifas_clientes (tc_servicio, tc_servicio_origen, tc_servicio_destino, tc_soporte, tc_fecha_desde, tc_fecha_hasta, tc_incremento, tc_tarifa, cl_id) VALUES ('Urbano', 'MADRID', 'MADRID', 'Tren' ,'2009-11-03', '2050-01-01', 0, 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tp_tarifas_proveedores`
--
CREATE TABLE IF NOT EXISTS `tp_tarifas_proveedores` (
  `tp_id` int(5) NOT NULL auto_increment,
  `tp_servicio` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `tp_servicio_origen` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `tp_servicio_destino` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `tp_soporte` varchar(100) collate utf8_spanish_ci NOT NULL default '',
  `tp_fecha_desde` date NOT NULL default '0000-00-00',
  `tp_fecha_hasta` date NOT NULL default '0000-00-00',
  `tp_incremento` double default '0',
  `tp_tarifa` double default '0',
  `pr_id` int(10) NOT NULL default '0',
  PRIMARY KEY  (`tp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `tp_tarifas_proveedores`
--
INSERT INTO tp_tarifas_proveedores (tp_servicio, tp_servicio_origen, tp_servicio_destino, tp_soporte, tp_fecha_desde, tp_fecha_hasta, tp_incremento, tp_tarifa, pr_id) VALUES ('Urbano', 'MADRID', 'MADRID', 'Grúa' , '2009-11-03', '2050-01-01', 0, 0, 1);

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `sc_servicios_clientes`
--
CREATE TABLE IF NOT EXISTS `sc_servicios_clientes` (
  `sc_id` int(5) NOT NULL auto_increment,
  `sc_todoterreno` double default '0',
  `sc_industrial` double default '0',
  `sc_furgonetas` double default '0',
  `sc_furgones` double default '0',
  `sc_lavado_exterior` double default '0',
  `sc_lavado_exin` double default '0',
  `sc_lavado_integral` double default '0',
  `sc_int_ext_cuatro` double default '0',
  `sc_integral_cuatro` double default '0',
  `sc_int_ext_industrial` double default '0',
  `sc_integral_industrial` double default '0',
  `sc_desrotular_peg_facil` double default '0',
  `sc_desrotular_peg_normal` double default '0',
  `sc_desrotular_peg_dificil` double default '0',
  `sc_rotular_peg_facil` double default '0',
  `sc_rotular_peg_normal` double default '0',
  `sc_rotular_peg_dificil` double default '0',
  `sc_ldom_exterior` double default '0',
  `sc_ldom_exin` double default '0',
  `sc_ldom_integral` double default '0',
  `sc_ldom_int_ext_cuatro` double default '0',
  `sc_ldom_integral_cuatro` double default '0',
  `sc_ldom_int_ext_industrial` double default '0',
  `sc_ldom_integral_industrial` double default '0',
  `sc_itv` double default '0',
  `sc_pre_itv` double default '0',
  `sc_itv_pre_itv` double default '0',
  `sc_ida_vuelta` double default '0',
  `sc_entrada_campa` double default '0',
  `sc_campa` double default '0',
  `sc_peritacion` double default '0',
  `sc_mo_mecanica_chapa` double default '0',
  `sc_chequeo` double default '0',
  `sc_repostaje` double default '0',
  `sc_suplemento` double default '0',
  `sc_urgente` double default '0',
  `cl_id` int(10) NOT NULL default '0',
  PRIMARY KEY  (`sc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `sc_servicios_cliente`
--
INSERT INTO sc_servicios_clientes (sc_todoterreno,sc_industrial,sc_furgonetas,sc_furgones,sc_lavado_exterior,sc_lavado_exin,sc_lavado_integral,sc_int_ext_cuatro,sc_integral_cuatro,sc_int_ext_industrial,sc_integral_industrial,sc_desrotular_peg_facil,sc_desrotular_peg_normal,sc_desrotular_peg_dificil,sc_rotular_peg_facil,sc_rotular_peg_normal,sc_rotular_peg_dificil,sc_ldom_exterior,sc_ldom_exin,sc_ldom_integral,sc_ldom_int_ext_cuatro,sc_ldom_integral_cuatro,sc_ldom_int_ext_industrial,sc_ldom_integral_industrial,sc_itv,sc_pre_itv,sc_itv_pre_itv,sc_ida_vuelta,sc_entrada_campa,sc_campa,sc_peritacion,sc_mo_mecanica_chapa,sc_chequeo,sc_repostaje,sc_suplemento,sc_urgente, cl_id) VALUES ('1.5', '1.5', '1.5', '1.5', '0', '0', '0', '0', '0', '0', '0','0', '0', '0','0','0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1');

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `sp_servicios_proveedor`
--
CREATE TABLE IF NOT EXISTS `sp_servicios_proveedores` (
  `sp_id` int(5) NOT NULL auto_increment,
  `sp_todoterreno` double default '0',
  `sp_industrial` double default '0',
  `sp_furgonetas` double default '0',
  `sp_furgones` double default '0',
  `sp_lavado_exterior` double default '0',
  `sp_lavado_exin` double default '0',
  `sp_lavado_integral` double default '0',
  `sp_int_ext_cuatro` double default '0',
  `sp_integral_cuatro` double default '0',
  `sp_int_ext_industrial` double default '0',
  `sp_integral_industrial` double default '0',
  `sp_desrotular_peg_facil` double default '0',
  `sp_desrotular_peg_normal` double default '0',
  `sp_desrotular_peg_dificil` double default '0',
  `sp_rotular_peg_facil` double default '0',
  `sp_rotular_peg_normal` double default '0',
  `sp_rotular_peg_dificil` double default '0',
  `sp_ldom_exterior` double default '0',
  `sp_ldom_exin` double default '0',
  `sp_ldom_integral` double default '0',
  `sp_ldom_int_ext_cuatro` double default '0',
  `sp_ldom_integral_cuatro` double default '0',
  `sp_ldom_int_ext_industrial` double default '0',
  `sp_ldom_integral_industrial` double default '0',
  `sp_itv` double default '0',
  `sp_pre_itv` double default '0',
  `sp_itv_pre_itv` double default '0',
  `sp_ida_vuelta` double default '0',
  `sp_entrada_campa` double default '0',
  `sp_campa` double default '0',
  `sp_peritacion` double default '0',
  `sp_mo_mecanica_chapa` double default '0',
  `sp_chequeo` double default '0',
  `sp_repostaje` double default '0',
  `sp_suplemento` double default '0',
  `sp_urgente` double default '0',
  `pr_id` int(10) NOT NULL default '0',
  PRIMARY KEY  (`sp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `sp_servicios_proveedor`
--
INSERT INTO sp_servicios_proveedores (sp_todoterreno,sp_industrial,sp_furgonetas,sp_furgones,sp_lavado_exterior,sp_lavado_exin,sp_lavado_integral,sp_int_ext_cuatro,sp_integral_cuatro,sp_int_ext_industrial,sp_integral_industrial,sp_desrotular_peg_facil,sp_desrotular_peg_normal,sp_desrotular_peg_dificil,sp_rotular_peg_facil,sp_rotular_peg_normal,sp_rotular_peg_dificil,sp_ldom_exterior,sp_ldom_exin,sp_ldom_integral,sp_ldom_int_ext_cuatro,sp_ldom_integral_cuatro,sp_ldom_int_ext_industrial,sp_ldom_integral_industrial,sp_itv,sp_pre_itv,sp_itv_pre_itv,sp_ida_vuelta,sp_entrada_campa,sp_campa,sp_peritacion,sp_mo_mecanica_chapa,sp_chequeo,sp_repostaje,sp_suplemento,sp_urgente, pr_id) VALUES ('1.5', '1.5', '1.5', '1.5', '0', '0', '0', '0', '0', '0', '0','0', '0', '0','0','0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1');

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `usr_usuarios`
--
CREATE TABLE IF NOT EXISTS `usr_usuarios` (
  `usr_id` int(3) NOT NULL auto_increment,
  `usr_nombre` varchar(50) collate utf8_spanish_ci default NULL,
  `usr_password` varchar(25) collate utf8_spanish_ci NOT NULL default '',
  `gr_id` int(3) NOT NULL default '0',
  `usr_email` varchar(30) collate utf8_spanish_ci default NULL,
  PRIMARY KEY  (`usr_id`),
  UNIQUE KEY `usr_password` (`usr_password`),
  KEY `gr_id` (`gr_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=2 ;


--
-- Volcar la base de datos para la tabla `usr_usuarios`
--
INSERT INTO `usr_usuarios` (`usr_nombre`, `usr_password`, `gr_id`, `usr_email`) VALUES('rcortes', 'Ra09AB', 1, '');
INSERT INTO `usr_usuarios` (`usr_nombre`, `usr_password`, `gr_id`, `usr_email`) VALUES('sergio', 'alvarito', 1, '');
INSERT INTO `usr_usuarios` (`usr_nombre`, `usr_password`, `gr_id`, `usr_email`) VALUES('raul', 'prueba', 1, '');
INSERT INTO `usr_usuarios` (`usr_nombre`, `usr_password`, `gr_id`, `usr_email`) VALUES('joseluis', 'jlcubillo', 1, '');
INSERT INTO `usr_usuarios` (`usr_nombre`, `usr_password`, `gr_id`, `usr_email`) VALUES('luis', '190998', 2, '');

--
-- Estructura de tabla para la tabla `cc_contactos_clientes`
--
CREATE TABLE IF NOT EXISTS `cc_contactos_cliente` (
  `cc_id` int(5) NOT NULL auto_increment,
  `cc_nombre` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `cc_telefono` varchar(15) collate utf8_spanish_ci default NULL,
  `cc_telefono2` varchar(15) collate utf8_spanish_ci default NULL,
  `cc_email` varchar(30) collate utf8_spanish_ci default NULL,
  `cl_id` int(10)  NOT NULL,
  `dp_id` int(10) NOT NULL default '0',
  PRIMARY KEY  (`cc_id`),
  FULLTEXT KEY `cc_nombre` (`cc_nombre`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `cc_contactos_clientes`
--
INSERT INTO cc_contactos_cliente (cc_nombre, cc_telefono, cc_telefono2, cc_email, cl_id, dp_id) VALUES ('NINGUNO','910000000','','EMAIL@CONTACTOPLANTILLA.ES', '1', '7');
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `cl_clientes`
--
CREATE TABLE IF NOT EXISTS `cp_contactos_proveedor` (
  `cp_id` int(5) NOT NULL auto_increment,
  `cp_nombre` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `cp_telefono` varchar(15) collate utf8_spanish_ci default NULL,
  `cp_telefono2` varchar(15) collate utf8_spanish_ci default NULL,
  `cp_email` varchar(30) collate utf8_spanish_ci default NULL,
  `pr_id` int(10)  NOT NULL,
  `dp_id` int(10) NOT NULL default '0',
  PRIMARY KEY  (`cp_id`),
  FULLTEXT KEY `cp_nombre` (`cp_nombre`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;
--
-- Volcar la base de datos para la tabla `cl_clientes`
--
-- --------------------------------------------------------
INSERT INTO cp_contactos_proveedor (cp_nombre, cp_telefono, cp_telefono2, cp_email, pr_id, dp_id) VALUES ('NINGUNO','910000000','','EMAIL@CONTACTOPLANTILLA.ES', '1', '7');


CREATE TABLE IF NOT EXISTS `fa_facturas_aux` (
  `fa_num` varchar(20) collate utf8_spanish_ci NOT NULL default '',
  `fa_fecha` date NOT NULL,
  `fa_marca` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `fa_modelo` varchar(50) collate utf8_spanish_ci NOT NULL default '',
  `fa_matricula` varchar(10) collate utf8_spanish_ci default NULL,
  `fa_factor` varchar(50) collate utf8_spanish_ci default NULL,
  `fa_soporte` varchar(80) collate utf8_spanish_ci default NULL,
  `fa_traslado` varchar(50) collate utf8_spanish_ci default NULL,
  `fa_texto_traslado` varchar(100) collate utf8_spanish_ci default NULL,
  `fa_importe_traslado` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_factor_correccion` varchar(80) collate utf8_spanish_ci default NULL,
  `fa_texto_factor_correccion` varchar(50) collate utf8_spanish_ci default NULL,
  `fa_importe_factor_correccion` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `fa_suplemento` varchar(15) collate utf8_spanish_ci NOT NULL default '',
  `fa_texto_suplemento` varchar(15) collate utf8_spanish_ci default NULL,
  `fa_importe_suplemento` varchar(50) collate utf8_spanish_ci default NULL,
  `fa_servicio_adicional` varchar(50) collate utf8_spanish_ci default NULL,
  `fa_texto_servicio_adicional` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_importe_servicio_adicional` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_campa` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_texto_campa` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_importe_campa` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_campa2` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_texto_campa2` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_importe_campa2` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_label_ida` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_texto_ida` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_importe_ida` varchar(30) collate utf8_spanish_ci default NULL,
  `fa_importe_total` varchar(30) collate utf8_spanish_ci default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;