-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: May 20, 2020 at 01:21 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `olxdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `administradore`
--

DROP TABLE IF EXISTS `administradore`;
CREATE TABLE IF NOT EXISTS `administradore` (
  `correo` varchar(255) NOT NULL,
  `dni` varchar(255) NOT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `salario` int(11) NOT NULL,
  `id_cargo` int(11) DEFAULT NULL,
  PRIMARY KEY (`correo`,`dni`),
  KEY `FKpkng02b1s2pp3bv449j0ocace` (`id_cargo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
CREATE TABLE IF NOT EXISTS `cargos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` (`id_categoria`, `nombre`) VALUES
(1, 'Hogar'),
(5, 'Ropa para caballeros'),
(6, 'Vehiculos'),
(7, 'Electrodomesticos');

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
CREATE TABLE IF NOT EXISTS `chat` (
  `id_chat` int(11) NOT NULL AUTO_INCREMENT,
  `correo_cliente` varchar(255) DEFAULT NULL,
  `dni_cliente` varchar(255) DEFAULT NULL,
  `correo_ofertador` varchar(255) DEFAULT NULL,
  `dni_ofertador` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_chat`),
  KEY `FKlcdtc9627o97bye6alwq40rvb` (`correo_cliente`,`dni_cliente`),
  KEY `FKkow8irvwyx6wuciu8sjw7qsuw` (`correo_ofertador`,`dni_ofertador`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`id_chat`, `correo_cliente`, `dni_cliente`, `correo_ofertador`, `dni_ofertador`) VALUES
(2, 'aaddd@awd.com', '2000', 'aaddd@awd.com', '2000'),
(3, 'aaddd@awd.com', '2000', 'aaddd@awd.com', '2000');

-- --------------------------------------------------------

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
CREATE TABLE IF NOT EXISTS `departamentos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departamentos`
--

INSERT INTO `departamentos` (`id`, `codigo`, `nombre`) VALUES
(1, 5, 'Antioquia'),
(2, 8, 'Atlantico'),
(3, 11, 'D. C. Santa Fe de Bogotá'),
(4, 13, 'Bolivar'),
(5, 15, 'Boyaca'),
(6, 17, 'Caldas'),
(7, 18, 'Caqueta'),
(8, 19, 'Cauca'),
(9, 20, 'Cesar'),
(10, 23, 'Cordova'),
(11, 25, 'Cundinamarca'),
(12, 27, 'Choco'),
(13, 41, 'Huila'),
(14, 44, 'La Guajira'),
(15, 47, 'Magdalena'),
(16, 50, 'Meta'),
(17, 52, 'Nariño'),
(18, 54, 'Norte de Santander'),
(19, 63, 'Quindio'),
(20, 66, 'Risaralda'),
(21, 68, 'Santander'),
(22, 70, 'Sucre'),
(23, 73, 'Tolima'),
(24, 76, 'Valle'),
(25, 81, 'Arauca'),
(26, 85, 'Casanare'),
(27, 86, 'Putumayo'),
(28, 88, 'San Andres'),
(29, 91, 'Amazonas'),
(30, 94, 'Guainia'),
(31, 95, 'Guaviare'),
(32, 97, 'Vaupes'),
(33, 99, 'Vichada');

-- --------------------------------------------------------

--
-- Table structure for table `imgproducto`
--

DROP TABLE IF EXISTS `imgproducto`;
CREATE TABLE IF NOT EXISTS `imgproducto` (
  `id_img_producto` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_img_producto`),
  KEY `FK8cvowsoipif37q3v8d3ya9c3r` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `imgproducto`
--

INSERT INTO `imgproducto` (`id_img_producto`, `url`, `id_producto`) VALUES
(1, 'producto.jpg', 2),
(2, 'descarga.jfif', 3),
(3, 'hola.jfif', 5),
(4, 'bb.jpg', 14),
(5, 'D:\\OLX\\uploads\\producto.png', 5),
(6, 'D:\\OLX\\uploads\\WhatsApp Image 2019-08-21 at 7.30.45 AM.jpeg', 5),
(7, 'D:\\OLX\\uploads\\WhatsApp Image 2019-08-21 at 7.30.45 AM.jpeg', 14),
(8, 'D:\\OLX\\uploads\\450_1000.jpg', 3),
(9, 'D:\\OLX\\uploads\\jclic activity.jpg', 17),
(10, 'D:\\OLX\\uploads\\jclic activity.jpg', 17);

-- --------------------------------------------------------

--
-- Table structure for table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
CREATE TABLE IF NOT EXISTS `mensajes` (
  `id_mensaje` int(11) NOT NULL,
  `fecha_envio` datetime DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `id_chat` int(11) NOT NULL,
  `correo_remitente` varchar(255) DEFAULT NULL,
  `dni_remitente` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_chat`,`id_mensaje`),
  KEY `FKigsp0ffi3bbdn0pq7shrqr06o` (`correo_remitente`,`dni_remitente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
CREATE TABLE IF NOT EXISTS `municipios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `departamento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc2c839fcdfxwfd110ydm64fid` (`departamento_id`)
) ENGINE=InnoDB AUTO_INCREMENT=500 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `municipios`
--

INSERT INTO `municipios` (`id`, `codigo`, `nombre`, `departamento_id`) VALUES
(1, 1, 'MEDELLIN', 1),
(2, 2, 'ABEJORRAL', 1),
(3, 4, 'ABRIAQUI', 1),
(4, 21, 'ALEJANDRIA', 1),
(5, 30, 'AMAGA', 1),
(6, 31, 'AMALFI', 1),
(7, 34, 'ANDES', 1),
(8, 36, 'ANGELOPOLIS', 1),
(9, 38, 'ANGOSTURA', 1),
(10, 40, 'ANORI', 1),
(11, 42, 'ANTIOQUIA', 1),
(12, 44, 'ANZA', 1),
(13, 45, 'APARTADO', 1),
(14, 51, 'ARBOLETES', 1),
(15, 55, 'ARGELIA', 1),
(16, 59, 'ARMENIA', 1),
(17, 79, 'BARBOSA', 1),
(18, 86, 'BELMIRA', 1),
(19, 88, 'BELLO', 1),
(20, 91, 'BETANIA', 1),
(21, 93, 'BETULIA', 1),
(22, 101, 'BOLIVAR', 1),
(23, 107, 'BRICEÑO', 1),
(24, 113, 'BURITICA', 1),
(25, 120, 'CACERES', 1),
(26, 125, 'CAICEDO', 1),
(27, 129, 'CALDAS', 1),
(28, 134, 'CAMPAMENTO', 1),
(29, 138, 'CAÑASGORDAS', 1),
(30, 142, 'CARACOLI', 1),
(31, 145, 'CARAMANTA', 1),
(32, 147, 'CAREPA', 1),
(33, 148, 'CARMEN DE VIBORAL', 1),
(34, 150, 'CAROLINA', 1),
(35, 154, 'CAUCASIA', 1),
(36, 172, 'CHIGORODO', 1),
(37, 190, 'CISNEROS', 1),
(38, 197, 'COCORNA', 1),
(39, 206, 'CONCEPCION', 1),
(40, 209, 'CONCORDIA', 1),
(41, 212, 'COPACABANA', 1),
(42, 234, 'DABEIBA', 1),
(43, 237, 'DON MATIAS', 1),
(44, 240, 'EBEJICO', 1),
(45, 250, 'EL BAGRE', 1),
(46, 264, 'ENTRERRIOS', 1),
(47, 266, 'ENVIGADO', 1),
(48, 282, 'FREDONIA', 1),
(49, 284, 'FRONTINO', 1),
(50, 306, 'GIRALDO', 1),
(51, 308, 'GIRARDOTA', 1),
(52, 310, 'GOMEZ PLATA', 1),
(53, 313, 'GRANADA', 1),
(54, 315, 'GUADALUPE', 1),
(55, 318, 'GUARNE', 1),
(56, 321, 'GUATAPE', 1),
(57, 347, 'HELICONIA', 1),
(58, 353, 'HISPANIA', 1),
(59, 360, 'ITAGUI', 1),
(60, 361, 'ITUANGO', 1),
(61, 364, 'JARDIN', 1),
(62, 368, 'JERICO', 1),
(63, 376, 'LA CEJA', 1),
(64, 380, 'LA ESTRELLA', 1),
(65, 390, 'LA PINTADA', 1),
(66, 400, 'LA UNION', 1),
(67, 411, 'LIBORINA', 1),
(68, 425, 'MACEO', 1),
(69, 440, 'MARINILLA', 1),
(70, 467, 'MONTEBELLO', 1),
(71, 475, 'MURINDO', 1),
(72, 480, 'MUTATA', 1),
(73, 483, 'NARIÑO', 1),
(74, 490, 'NECOCLI', 1),
(75, 495, 'NECHI', 1),
(76, 501, 'OLAYA', 1),
(77, 541, 'PEÑOL', 1),
(78, 543, 'PEQUE', 1),
(79, 576, 'PUEBLORRICO', 1),
(80, 579, 'PUERTO BERRIO', 1),
(81, 585, 'PUERTO NARE (LA MAGDALENA)', 1),
(82, 591, 'PUERTO TRIUNFO', 1),
(83, 604, 'REMEDIOS', 1),
(84, 607, 'RETIRO', 1),
(85, 615, 'RIONEGRO', 1),
(86, 628, 'SABANALARGA', 1),
(87, 631, 'SABANETA', 1),
(88, 642, 'SALGAR', 1),
(89, 647, 'SAN ANDRES', 1),
(90, 649, 'SAN CARLOS', 1),
(91, 652, 'SAN FRANCISCO', 1),
(92, 656, 'SAN JERONIMO', 1),
(93, 658, 'SAN JOSE DE LA MONTAÑA', 1),
(94, 659, 'SAN JUAN DE URABA', 1),
(95, 660, 'SAN LUIS', 1),
(96, 664, 'SAN PEDRO', 1),
(97, 665, 'SAN PEDRO DE URABA', 1),
(98, 667, 'SAN RAFAEL', 1),
(99, 670, 'SAN ROQUE', 1),
(100, 674, 'SAN VICENTE', 1),
(101, 679, 'SANTA BARBARA', 1),
(102, 686, 'SANTA ROSA DE OSOS', 1),
(103, 690, 'SANTO DOMINGO', 1),
(104, 697, 'SANTUARIO', 1),
(105, 736, 'SEGOVIA', 1),
(106, 756, 'SONSON', 1),
(107, 761, 'SOPETRAN', 1),
(108, 789, 'TAMESIS', 1),
(109, 790, 'TARAZA', 1),
(110, 792, 'TARSO', 1),
(111, 809, 'TITIRIBI', 1),
(112, 819, 'TOLEDO', 1),
(113, 837, 'TURBO', 1),
(114, 842, 'URAMITA', 1),
(115, 847, 'URRAO', 1),
(116, 854, 'VALDIVIA', 1),
(117, 856, 'VALPARAISO', 1),
(118, 858, 'VEGACHI', 1),
(119, 861, 'VENECIA', 1),
(120, 873, 'VIGIA DEL FUERTE', 1),
(121, 885, 'YALI', 1),
(122, 887, 'YARUMAL', 1),
(123, 890, 'YOLOMBO', 1),
(124, 893, 'YONDO', 1),
(125, 895, 'ZARAGOZA', 1),
(126, 1, 'BARRANQUILLA (DISTRITO ESPECIAL INDUSTRIAL Y PORTUARIO DE BARRANQUILLA)', 2),
(127, 78, 'BARANOA', 2),
(128, 137, 'CAMPO DE LA CRUZ', 2),
(129, 141, 'CANDELARIA', 2),
(130, 296, 'GALAPA', 2),
(131, 372, 'JUAN DE ACOSTA', 2),
(132, 421, 'LURUACO', 2),
(133, 433, 'MALAMBO', 2),
(134, 436, 'MANATI', 2),
(135, 520, 'PALMAR DE VARELA', 2),
(136, 549, 'PIOJO', 2),
(137, 558, 'POLO NUEVO', 2),
(138, 560, 'PONEDERA', 2),
(139, 573, 'PUERTO COLOMBIA', 2),
(140, 606, 'REPELON', 2),
(141, 634, 'SABANAGRANDE', 2),
(142, 638, 'SABANALARGA', 2),
(143, 675, 'SANTA LUCIA', 2),
(144, 685, 'SANTO TOMAS', 2),
(145, 758, 'SOLEDAD', 2),
(146, 770, 'SUAN', 2),
(147, 832, 'TUBARA', 2),
(148, 849, 'USIACURI', 2),
(149, 1, 'Santa Fe de Bogotá', 3),
(150, 1, 'USAQUEN', 3),
(151, 2, 'CHAPINERO', 3),
(152, 3, 'SANTA FE', 3),
(153, 4, 'SAN CRISTOBAL', 3),
(154, 5, 'USME', 3),
(155, 6, 'TUNJUELITO', 3),
(156, 7, 'BOSA', 3),
(157, 8, 'KENNEDY', 3),
(158, 9, 'FONTIBON', 3),
(159, 10, 'ENGATIVA', 3),
(160, 11, 'SUBA', 3),
(161, 12, 'BARRIOS UNIDOS', 3),
(162, 13, 'TEUSAQUILLO', 3),
(163, 14, 'MARTIRES', 3),
(164, 15, 'ANTONIO NARIÑO', 3),
(165, 16, 'PUENTE ARANDA', 3),
(166, 17, 'CANDELARIA', 3),
(167, 18, 'RAFAEL URIBE', 3),
(168, 19, 'CIUDAD BOLIVAR', 3),
(169, 20, 'SUMAPAZ', 3),
(170, 1, 'CARTAGENA (DISTRITO TURISTICO Y CULTURAL DE CARTAGENA)', 4),
(171, 6, 'ACHI', 4),
(172, 30, 'ALTOS DEL ROSARIO', 4),
(173, 42, 'ARENAL', 4),
(174, 52, 'ARJONA', 4),
(175, 62, 'ARROYOHONDO', 4),
(176, 74, 'BARRANCO DE LOBA', 4),
(177, 140, 'CALAMAR', 4),
(178, 160, 'CANTAGALLO', 4),
(179, 188, 'CICUCO', 4),
(180, 212, 'CORDOBA', 4),
(181, 222, 'CLEMENCIA', 4),
(182, 244, 'EL CARMEN DE BOLIVAR', 4),
(183, 248, 'EL GUAMO', 4),
(184, 268, 'EL PEÑON', 4),
(185, 300, 'HATILLO DE LOBA', 4),
(186, 430, 'MAGANGUE', 4),
(187, 433, 'MAHATES', 4),
(188, 440, 'MARGARITA', 4),
(189, 442, 'MARIA LA BAJA', 4),
(190, 458, 'MONTECRISTO', 4),
(191, 468, 'MOMPOS', 4),
(192, 473, 'MORALES', 4),
(193, 549, 'PINILLOS', 4),
(194, 580, 'REGIDOR', 4),
(195, 600, 'RIO VIEJO', 4),
(196, 620, 'SAN CRISTOBAL', 4),
(197, 647, 'SAN ESTANISLAO', 4),
(198, 650, 'SAN FERNANDO', 4),
(199, 654, 'SAN JACINTO', 4),
(200, 655, 'SAN JACINTO DEL CAUCA', 4),
(201, 657, 'SAN JUAN NEPOMUCENO', 4),
(202, 667, 'SAN MARTIN DE LOBA', 4),
(203, 670, 'SAN PABLO', 4),
(204, 673, 'SANTA CATALINA', 4),
(205, 683, 'SANTA ROSA', 4),
(206, 688, 'SANTA ROSA DEL SUR', 4),
(207, 744, 'SIMITI', 4),
(208, 760, 'SOPLAVIENTO', 4),
(209, 780, 'TALAIGUA NUEVO', 4),
(210, 810, 'TIQUISIO (PUERTO RICO)', 4),
(211, 836, 'TURBACO', 4),
(212, 838, 'TURBANA', 4),
(213, 873, 'VILLANUEVA', 4),
(214, 894, 'ZAMBRANO', 4),
(215, 1, 'TUNJA', 5),
(216, 22, 'ALMEIDA', 5),
(217, 47, 'AQUITANIA', 5),
(218, 51, 'ARCABUCO', 5),
(219, 87, 'BELEN', 5),
(220, 90, 'BERBEO', 5),
(221, 92, 'BETEITIVA', 5),
(222, 97, 'BOAVITA', 5),
(223, 104, 'BOYACA', 5),
(224, 106, 'BRICEÑO', 5),
(225, 109, 'BUENAVISTA', 5),
(226, 114, 'BUSBANZA', 5),
(227, 131, 'CALDAS', 5),
(228, 135, 'CAMPOHERMOSO', 5),
(229, 162, 'CERINZA', 5),
(230, 172, 'CHINAVITA', 5),
(231, 176, 'CHIQUINQUIRA', 5),
(232, 180, 'CHISCAS', 5),
(233, 183, 'CHITA', 5),
(234, 185, 'CHITARAQUE', 5),
(235, 187, 'CHIVATA', 5),
(236, 189, 'CIENEGA', 5),
(237, 204, 'COMBITA', 5),
(238, 212, 'COPER', 5),
(239, 215, 'CORRALES', 5),
(240, 218, 'COVARACHIA', 5),
(241, 223, 'CUBARA', 5),
(242, 224, 'CUCAITA', 5),
(243, 226, 'CUITIVA', 5),
(244, 232, 'CHIQUIZA', 5),
(245, 236, 'CHIVOR', 5),
(246, 238, 'DUITAMA', 5),
(247, 244, 'EL COCUY', 5),
(248, 248, 'EL ESPINO', 5),
(249, 272, 'FIRAVITOBA', 5),
(250, 276, 'FLORESTA', 5),
(251, 293, 'GACHANTIVA', 5),
(252, 296, 'GAMEZA', 5),
(253, 299, 'GARAGOA', 5),
(254, 317, 'GUACAMAYAS', 5),
(255, 322, 'GUATEQUE', 5),
(256, 325, 'GUAYATA', 5),
(257, 332, 'GUICAN', 5),
(258, 362, 'IZA', 5),
(259, 367, 'JENESANO', 5),
(260, 368, 'JERICO', 5),
(261, 377, 'LABRANZAGRANDE', 5),
(262, 380, 'LA CAPILLA', 5),
(263, 401, 'LA VICTORIA', 5),
(264, 403, 'LA UVITA', 5),
(265, 407, 'VILLA DE LEIVA', 5),
(266, 425, 'MACANAL', 5),
(267, 442, 'MARIPI', 5),
(268, 455, 'MIRAFLORES', 5),
(269, 464, 'MONGUA', 5),
(270, 466, 'MONGUI', 5),
(271, 469, 'MONIQUIRA', 5),
(272, 476, 'MOTAVITA', 5),
(273, 480, 'MUZO', 5),
(274, 491, 'NOBSA', 5),
(275, 494, 'NUEVO COLON', 5),
(276, 500, 'OICATA', 5),
(277, 507, 'OTANCHE', 5),
(278, 511, 'PACHAVITA', 5),
(279, 514, 'PAEZ', 5),
(280, 516, 'PAIPA', 5),
(281, 518, 'PAJARITO', 5),
(282, 522, 'PANQUEBA', 5),
(283, 531, 'PAUNA', 5),
(284, 533, 'PAYA', 5),
(285, 537, 'PAZ DEL RIO', 5),
(286, 542, 'PESCA', 5),
(287, 550, 'PISBA', 5),
(288, 572, 'PUERTO BOYACA', 5),
(289, 580, 'QUIPAMA', 5),
(290, 599, 'RAMIRIQUI', 5),
(291, 600, 'RAQUIRA', 5),
(292, 621, 'RONDON', 5),
(293, 632, 'SABOYA', 5),
(294, 638, 'SACHICA', 5),
(295, 646, 'SAMACA', 5),
(296, 660, 'SAN EDUARDO', 5),
(297, 664, 'SAN JOSE DE PARE', 5),
(298, 667, 'SAN LUIS DE GACENO', 5),
(299, 673, 'SAN MATEO', 5),
(300, 676, 'SAN MIGUEL DE SEMA', 5),
(301, 681, 'SAN PABLO DE BORBUR', 5),
(302, 686, 'SANTANA', 5),
(303, 690, 'SANTA MARIA', 5),
(304, 693, 'SANTA ROSA DE VITERBO', 5),
(305, 696, 'SANTA SOFIA', 5),
(306, 720, 'SATIVANORTE', 5),
(307, 723, 'SATIVASUR', 5),
(308, 740, 'SIACHOQUE', 5),
(309, 753, 'SOATA', 5),
(310, 755, 'SOCOTA', 5),
(311, 757, 'SOCHA', 5),
(312, 759, 'SOGAMOSO', 5),
(313, 761, 'SOMONDOCO', 5),
(314, 762, 'SORA', 5),
(315, 763, 'SOTAQUIRA', 5),
(316, 764, 'SORACA', 5),
(317, 774, 'SUSACON', 5),
(318, 776, 'SUTAMARCHAN', 5),
(319, 778, 'SUTATENZA', 5),
(320, 790, 'TASCO', 5),
(321, 798, 'TENZA', 5),
(322, 804, 'TIBANA', 5),
(323, 806, 'TIBASOSA', 5),
(324, 808, 'TINJACA', 5),
(325, 810, 'TIPACOQUE', 5),
(326, 814, 'TOCA', 5),
(327, 816, 'TOGUI', 5),
(328, 820, 'TOPAGA', 5),
(329, 822, 'TOTA', 5),
(330, 832, 'TUNUNGUA', 5),
(331, 835, 'TURMEQUE', 5),
(332, 837, 'TUTA', 5),
(333, 839, 'TUTASA', 5),
(334, 842, 'UMBITA', 5),
(335, 861, 'VENTAQUEMADA', 5),
(336, 879, 'VIRACACHA', 5),
(337, 897, 'ZETAQUIRA', 5),
(338, 1, 'MANIZALES', 6),
(339, 13, 'AGUADAS', 6),
(340, 42, 'ANSERMA', 6),
(341, 50, 'ARANZAZU', 6),
(342, 88, 'BELALCAZAR', 6),
(343, 174, 'CHINCHINA', 6),
(344, 272, 'FILADELFIA', 6),
(345, 380, 'LA DORADA', 6),
(346, 388, 'LA MERCED', 6),
(347, 433, 'MANZANARES', 6),
(348, 442, 'MARMATO', 6),
(349, 444, 'MARQUETALIA', 6),
(350, 446, 'MARULANDA', 6),
(351, 486, 'NEIRA', 6),
(352, 495, 'NORCASIA', 6),
(353, 513, 'PACORA', 6),
(354, 524, 'PALESTINA', 6),
(355, 541, 'PENSILVANIA', 6),
(356, 614, 'RIOSUCIO', 6),
(357, 616, 'RISARALDA', 6),
(358, 653, 'SALAMINA', 6),
(359, 662, 'SAMANA', 6),
(360, 665, 'SAN JOSE', 6),
(361, 777, 'SUPIA', 6),
(362, 867, 'VICTORIA', 6),
(363, 873, 'VILLAMARIA', 6),
(364, 877, 'VITERBO', 6),
(365, 1, 'FLORENCIA', 7),
(366, 29, 'ALBANIA', 7),
(367, 94, 'BELEN DE LOS ANDAQUIES', 7),
(368, 150, 'CARTAGENA DEL CHAIRA', 7),
(369, 205, 'CURILLO', 7),
(370, 247, 'EL DONCELLO', 7),
(371, 256, 'EL PAUJIL', 7),
(372, 410, 'LA MONTAÑITA', 7),
(373, 460, 'MILAN', 7),
(374, 479, 'MORELIA', 7),
(375, 592, 'PUERTO RICO', 7),
(376, 610, 'SAN JOSE DE FRAGUA', 7),
(377, 753, 'SAN VICENTE DEL CAGUAN', 7),
(378, 756, 'SOLANO', 7),
(379, 785, 'SOLITA', 7),
(380, 860, 'VALPARAISO', 7),
(381, 1, 'POPAYAN', 8),
(382, 22, 'ALMAGUER', 8),
(383, 50, 'ARGELIA', 8),
(384, 75, 'BALBOA', 8),
(385, 100, 'BOLIVAR', 8),
(386, 110, 'BUENOS AIRES', 8),
(387, 130, 'CAJIBIO', 8),
(388, 137, 'CALDONO', 8),
(389, 142, 'CALOTO', 8),
(390, 212, 'CORINTO', 8),
(391, 256, 'EL TAMBO', 8),
(392, 290, 'FLORENCIA', 8),
(393, 318, 'GUAPI', 8),
(394, 355, 'INZA', 8),
(395, 364, 'JAMBALO', 8),
(396, 392, 'LA SIERRA', 8),
(397, 397, 'LA VEGA', 8),
(398, 418, 'LOPEZ (MICAY)', 8),
(399, 450, 'MERCADERES', 8),
(400, 455, 'MIRANDA', 8),
(401, 473, 'MORALES', 8),
(402, 513, 'PADILLA', 8),
(403, 517, 'PAEZ (BELALCAZAR)', 8),
(404, 532, 'PATIA (EL BORDO)', 8),
(405, 533, 'PIAMONTE', 8),
(406, 548, 'PIENDAMO', 8),
(407, 573, 'PUERTO TEJADA', 8),
(408, 585, 'PURACE (COCONUCO)', 8),
(409, 622, 'ROSAS', 8),
(410, 693, 'SAN SEBASTIAN', 8),
(411, 698, 'SANTANDER DE QUILICHAO', 8),
(412, 701, 'SANTA ROSA', 8),
(413, 743, 'SILVIA', 8),
(414, 760, 'SOTARA (PAISPAMBA)', 8),
(415, 780, 'SUAREZ', 8),
(416, 807, 'TIMBIO', 8),
(417, 809, 'TIMBIQUI', 8),
(418, 821, 'TORIBIO', 8),
(419, 824, 'TOTORO', 8),
(420, 845, 'VILLARICA', 8),
(421, 1, 'VALLEDUPAR', 9),
(422, 11, 'AGUACHICA', 9),
(423, 13, 'AGUSTIN CODAZZI', 9),
(424, 32, 'ASTREA', 9),
(425, 45, 'BECERRIL', 9),
(426, 60, 'BOSCONIA', 9),
(427, 175, 'CHIMICHAGUA', 9),
(428, 178, 'CHIRIGUANA', 9),
(429, 228, 'CURUMANI', 9),
(430, 238, 'EL COPEY', 9),
(431, 250, 'EL PASO', 9),
(432, 295, 'GAMARRA', 9),
(433, 310, 'GONZALEZ', 9),
(434, 383, 'LA GLORIA', 9),
(435, 400, 'LA JAGUA IBIRICO', 9),
(436, 443, 'MANAURE (BALCON DEL CESAR)', 9),
(437, 517, 'PAILITAS', 9),
(438, 550, 'PELAYA', 9),
(439, 570, 'PUEBLO BELLO', 9),
(440, 614, 'RIO DE ORO', 9),
(441, 621, 'LA PAZ (ROBLES)', 9),
(442, 710, 'SAN ALBERTO', 9),
(443, 750, 'SAN DIEGO', 9),
(444, 770, 'SAN MARTIN', 9),
(445, 787, 'TAMALAMEQUE', 9),
(446, 1, 'MONTERIA', 10),
(447, 68, 'AYAPEL', 10),
(448, 79, 'BUENAVISTA', 10),
(449, 90, 'CANALETE', 10),
(450, 162, 'CERETE', 10),
(451, 168, 'CHIMA', 10),
(452, 182, 'CHINU', 10),
(453, 189, 'CIENAGA DE ORO', 10),
(454, 300, 'COTORRA', 10),
(455, 350, 'LA APARTADA', 10),
(456, 417, 'LORICA', 10),
(457, 419, 'LOS CORDOBAS', 10),
(458, 464, 'MOMIL', 10),
(459, 466, 'MONTELIBANO', 10),
(460, 500, 'MOÑITOS', 10),
(461, 555, 'PLANETA RICA', 10),
(462, 570, 'PUEBLO NUEVO', 10),
(463, 574, 'PUERTO ESCONDIDO', 10),
(464, 580, 'PUERTO LIBERTADOR', 10),
(465, 586, 'PURISIMA', 10),
(466, 660, 'SAHAGUN', 10),
(467, 670, 'SAN ANDRES SOTAVENTO', 10),
(468, 672, 'SAN ANTERO', 10),
(469, 675, 'SAN BERNARDO DEL VIENTO', 10),
(470, 678, 'SAN CARLOS', 10),
(471, 686, 'SAN PELAYO', 10),
(472, 807, 'TIERRALTA', 10),
(473, 855, 'VALENCIA', 10),
(474, 1, 'AGUA DE DIOS', 11),
(475, 19, 'ALBAN', 11),
(476, 35, 'ANAPOIMA', 11),
(477, 40, 'ANOLAIMA', 11),
(478, 53, 'ARBELAEZ', 11),
(479, 86, 'BELTRAN', 11),
(480, 95, 'BITUIMA', 11),
(481, 99, 'BOJACA', 11),
(482, 120, 'CABRERA', 11),
(483, 123, 'CACHIPAY', 11),
(484, 126, 'CAJICA', 11),
(485, 148, 'CAPARRAPI', 11),
(486, 151, 'CAQUEZA', 11),
(487, 154, 'CARMEN DE CARUPA', 11),
(488, 168, 'CHAGUANI', 11),
(489, 175, 'CHIA', 11),
(490, 178, 'CHIPAQUE', 11),
(491, 181, 'CHOACHI', 11),
(492, 183, 'CHOCONTA', 11),
(493, 200, 'COGUA', 11),
(494, 214, 'COTA', 11),
(495, 224, 'CUCUNUBA', 11),
(496, 245, 'EL COLEGIO', 11),
(497, 258, 'EL PEÑON', 11),
(498, 260, 'EL ROSAL', 11),
(499, 269, 'FACATATIVA', 11);

-- --------------------------------------------------------

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `num_contacto` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `id_ubicacion` int(11) DEFAULT NULL,
  `id_subcategoria` int(11) DEFAULT NULL,
  `correo_contacto` varchar(255) DEFAULT NULL,
  `id_usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `FKbwoxsvelyulwlb7iajgejhsv9` (`id_ubicacion`),
  KEY `FKtim3jm3v6kow02wxqthc7e1ws` (`id_subcategoria`),
  KEY `FKfqwx2murghcijd3118e9ibd2q` (`correo_contacto`,`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productos`
--

INSERT INTO `productos` (`id_producto`, `descripcion`, `direccion`, `estado`, `fecha_publicacion`, `num_contacto`, `precio`, `titulo`, `id_ubicacion`, `id_subcategoria`, `correo_contacto`, `id_usuario`) VALUES
(2, 'Vendo mi horno que ya no uso', 'Una dirección cualquiera', b'1', '2020-04-14', '3146259895', 150000, 'Horno microondas en perfecto estado', 2, 1, 'correo@correo.com', '1'),
(3, 'Esto es una descripción de ejemplo', 'dirección de ejemplo', b'0', '2020-03-05', '7894563', 120000, 'Título de ejemplo 2', 1, 1, 'correo@correo.com', '1'),
(5, 'Nevera 2 años de uso', 'Calle 1', b'1', '2021-08-10', '3156825658', 700000, 'Nevera', 1, 1, 'jose@correo.com', '1094975448'),
(14, 'esta e suna prueba del primer producto por interface', 'adwffff', b'1', '2020-05-05', '55444', 2000, 'primer producti', 2, 1, 'correo@correo.com', '1'),
(17, 'gggggggggggggggggggggg', 'bbbbbbbbbbbbbbbb', b'1', '2020-03-12', '77777', 2000000, 'ggggggggggg', 1, 1, 'aaddd@awd.com', '2000'),
(18, 'hhhhh', 'hhhh', b'1', '2020-12-12', '4555', 54454, 'hhhhh', 1, 1, 'aaddd@awd.com', '2000'),
(19, 'esta e suna prueba del primer producto por interface', 'awdadw', b'0', '2020-12-12', '666666666', 5000, 'hola mundo otra vez', 1, 1, 'aaddd@awd.com', '2000');

-- --------------------------------------------------------

--
-- Table structure for table `subcategorias`
--

DROP TABLE IF EXISTS `subcategorias`;
CREATE TABLE IF NOT EXISTS `subcategorias` (
  `id_sub_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_sub_categoria`),
  KEY `FKtjg6rtuadc9msnoeliyc48dm2` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subcategorias`
--

INSERT INTO `subcategorias` (`id_sub_categoria`, `nombre`, `id_categoria`) VALUES
(1, 'Electrodomésticos', 1),
(5, 'Muebles', 1),
(6, 'neveras', 7),
(7, 'campero', 6);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `correo` varchar(255) NOT NULL,
  `dni` varchar(255) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` datetime DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`correo`,`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`correo`, `dni`, `apellido`, `direccion`, `fecha_nacimiento`, `nombre`, `password`, `telefono`) VALUES
('aaddd@awd.com', '2000', 'hhhhh', 'vfvvvvv', '2020-02-11 05:00:00', 'ggg', '1245', '8888'),
('correo@correo.com', '1', 'Pérez', 'calle y carrera', '2020-04-08 00:00:00', 'Juan', '123', '3216548978'),
('ejemplo3@awd.com', '1097', 'ejemplo', 'hola', '2020-02-11 05:00:00', 'hola', '123', '5454'),
('ejemplo4@awd.cp', '1098', 'pues', 'awdwa', '2020-02-11 05:00:00', 'hola bb', '123', '56666'),
('gggs@awd.cpk', '1011111', 'ggggh', 'gggg', '2021-10-03 05:00:00', 'ffff', '12345678', '88888'),
('jose@correo.com', '1094975448', 'Cruz', 'Edificio Baleares Apto 302', '1999-06-26 00:00:00', 'Jose Miguel', 'jmc', '3156825658'),
('papu@correo.com', '2320181007', 'Durán', 'Toledo Campestre', '2020-04-08 00:00:00', 'Nicolás', 'ndg', '3051231231'),
('sebas@correo.com', '2320181017', 'Hidalgo', 'Montenegro', '1997-08-12 00:00:00', 'Sebastián', 'shs', '3169562014');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administradore`
--
ALTER TABLE `administradore`
  ADD CONSTRAINT `FKpkng02b1s2pp3bv449j0ocace` FOREIGN KEY (`id_cargo`) REFERENCES `cargos` (`id`);

--
-- Constraints for table `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `FKkow8irvwyx6wuciu8sjw7qsuw` FOREIGN KEY (`correo_ofertador`,`dni_ofertador`) REFERENCES `usuarios` (`correo`, `dni`),
  ADD CONSTRAINT `FKlcdtc9627o97bye6alwq40rvb` FOREIGN KEY (`correo_cliente`,`dni_cliente`) REFERENCES `usuarios` (`correo`, `dni`);

--
-- Constraints for table `imgproducto`
--
ALTER TABLE `imgproducto`
  ADD CONSTRAINT `FK8cvowsoipif37q3v8d3ya9c3r` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`);

--
-- Constraints for table `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `FK3ca32ynifh777t3o71h126i9s` FOREIGN KEY (`id_chat`) REFERENCES `chat` (`id_chat`),
  ADD CONSTRAINT `FKigsp0ffi3bbdn0pq7shrqr06o` FOREIGN KEY (`correo_remitente`,`dni_remitente`) REFERENCES `usuarios` (`correo`, `dni`);

--
-- Constraints for table `municipios`
--
ALTER TABLE `municipios`
  ADD CONSTRAINT `FKc2c839fcdfxwfd110ydm64fid` FOREIGN KEY (`departamento_id`) REFERENCES `departamentos` (`id`);

--
-- Constraints for table `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FKbwoxsvelyulwlb7iajgejhsv9` FOREIGN KEY (`id_ubicacion`) REFERENCES `municipios` (`id`),
  ADD CONSTRAINT `FKfqwx2murghcijd3118e9ibd2q` FOREIGN KEY (`correo_contacto`,`id_usuario`) REFERENCES `usuarios` (`correo`, `dni`),
  ADD CONSTRAINT `FKtim3jm3v6kow02wxqthc7e1ws` FOREIGN KEY (`id_subcategoria`) REFERENCES `subcategorias` (`id_sub_categoria`);

--
-- Constraints for table `subcategorias`
--
ALTER TABLE `subcategorias`
  ADD CONSTRAINT `FKtjg6rtuadc9msnoeliyc48dm2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
