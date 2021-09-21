/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100410
 Source Host           : localhost:3306
 Source Schema         : canchas-futgol

 Target Server Type    : MySQL
 Target Server Version : 100410
 File Encoding         : 65001

 Date: 17/07/2021 03:00:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for canchas
-- ----------------------------
DROP TABLE IF EXISTS `canchas`;
CREATE TABLE `canchas`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `descripcion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `numero_canchas` int(11) NULL DEFAULT NULL,
  `telefono` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `valor_dia` double NULL DEFAULT NULL,
  `valor_noche` double NULL DEFAULT NULL,
  `creado` datetime(0) NULL DEFAULT NULL,
  `modificado` datetime(0) NULL DEFAULT NULL,
  `usuario_id` int(11) NULL DEFAULT NULL,
  `imagen` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKo4tggahymyvlks9p5kp7l6486`(`usuario_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of canchas
-- ----------------------------
INSERT INTO `canchas` VALUES (6, 'Cancha 1', 'Pasto', 3, '1111', 'xxxx@xxx.com', 25, 40, '2021-07-07 00:00:00', '2021-07-10 00:24:06', NULL, 'dilbert3.jpg');
INSERT INTO `canchas` VALUES (7, 'Cancha 2', 'Pasto', 4, '22222', 'xtreme.scorpio@gmail.com', 30, 40, '2021-07-07 00:00:00', '2021-07-10 00:24:14', NULL, 'construccion.jpg');
INSERT INTO `canchas` VALUES (8, 'Cancha 3', 'Sintética', 4, '11111', 'xtreme.scorpio@gmail.com', 20, 30, '2021-07-07 05:14:17', '2021-07-10 00:24:19', NULL, NULL);
INSERT INTO `canchas` VALUES (9, 'Cancha 4', 'Sintética', 4, '2222', 'admin@example.com', 20, 30, '2021-07-07 00:00:00', '2021-07-10 00:24:24', NULL, NULL);

-- ----------------------------
-- Table structure for reservas
-- ----------------------------
DROP TABLE IF EXISTS `reservas`;
CREATE TABLE `reservas`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cancha_id` int(11) NULL DEFAULT NULL,
  `usuario_id` int(11) NULL DEFAULT NULL,
  `fecha` date NULL DEFAULT NULL,
  `hora` time(0) NULL DEFAULT NULL,
  `pagada` tinyint(1) NULL DEFAULT 0,
  `creado` datetime(0) NULL DEFAULT NULL,
  `modificado` datetime(0) NULL DEFAULT NULL,
  `cancelada` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKmc85ps4j27fo6uuek0iviq1c9`(`cancha_id`) USING BTREE,
  INDEX `FKcfh7qcr7oxomqk5hhbxdg2m7p`(`usuario_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of reservas
-- ----------------------------
INSERT INTO `reservas` VALUES (2, 6, 1, '2021-07-03', '11:00:00', 1, '2021-06-24 00:30:58', '2021-07-17 07:25:05', 0);
INSERT INTO `reservas` VALUES (5, 6, 47, '2021-07-09', '11:00:00', 0, '2021-07-07 05:31:01', '2021-07-17 07:26:20', 1);
INSERT INTO `reservas` VALUES (6, 7, 47, '2021-07-03', '11:00:00', 0, '2021-07-07 00:00:00', '2021-07-07 00:00:00', 0);
INSERT INTO `reservas` VALUES (7, 6, 47, '2021-07-03', '16:00:00', 1, '2021-07-08 07:18:55', '2021-07-17 07:26:26', 0);
INSERT INTO `reservas` VALUES (8, 6, 47, '2021-07-03', '09:00:00', 0, '2021-07-08 07:19:37', '2021-07-08 07:19:37', 0);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `rol` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (2, 'Cliente', 'cliente');
INSERT INTO `roles` VALUES (1, 'Administrador', 'admin');

-- ----------------------------
-- Table structure for tipo_cancha
-- ----------------------------
DROP TABLE IF EXISTS `tipo_cancha`;
CREATE TABLE `tipo_cancha`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tipo_cancha
-- ----------------------------
INSERT INTO `tipo_cancha` VALUES (1, 'Cemento');
INSERT INTO `tipo_cancha` VALUES (2, 'Sintética');

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `cedula` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `celular` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `creado` datetime(0) NULL DEFAULT NULL,
  `direccion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `estatus` int(11) NOT NULL,
  `modificado` datetime(0) NULL DEFAULT NULL,
  `nombre` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `rol_id` int(11) NOT NULL,
  `telefono` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `descripcion` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `usuario` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `imagen` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO `usuarios` VALUES (1, 'Magaña Valencia', '1234567890', '9811322994', '2021-07-01 23:32:03', 'CALLE 16 NO. 238A', 'xscorpio_@hotmail.com', 1, '2021-07-17 07:35:03', 'Josué Enmanuel', '$2a$10$Lhyqm.AqougTPk.5Ue0KAO5Kqevl.I1lniLe1WNURHMu5o34XjGHu', 1, '9811322994', 'admin', NULL, NULL, '11059652_893614970700495_7869101485585478193_n.jpg');
INSERT INTO `usuarios` VALUES (47, 'Prueba 2', '132456', 'aaa', '2021-07-01 00:00:00', '11', 'xtreme.scorpio@gmail.com', 1, '2021-07-14 04:43:23', 'Cliente 1 edit', '$2a$10$jNczDXHu8nBeiWgIjAx6veA1dJTQzSnHN5W5IDMjfn2x5sPLujfXy', 2, '2211', '1111', NULL, NULL, '36718743_1500198716751479_4798882193793351680_n.jpg');
INSERT INTO `usuarios` VALUES (52, 'asd', 'aa', '9811322994', '2021-07-17 06:11:23', 'calle 16 no. 238a', 'admin@example.com', 1, '2021-07-17 06:12:05', 'asdas', '$2a$10$9.o61DHRCmvlThdHkQyRVOSGo5saV7230duAUHBnJgiMtWH1vJ2ru', 2, '9811322994', 'aaa', NULL, NULL, NULL);
INSERT INTO `usuarios` VALUES (53, 'dsf', 'dsf', 'sdf', '2021-07-17 06:20:21', 'sdf', '', 1, '2021-07-17 06:20:21', 'dfds', '$2a$10$ZhLejllBOE8LsmqAE7PKouYTdbka12nveuVCvx1r1SzVD9XiVf1fG', 2, 'dsf', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for usuarios_reservas
-- ----------------------------
DROP TABLE IF EXISTS `usuarios_reservas`;
CREATE TABLE `usuarios_reservas`  (
  `usuario_id` int(11) NOT NULL,
  `reservas_id` int(11) NOT NULL,
  UNIQUE INDEX `UK_bwnrr2g95e1r0l7u1nv32m3w4`(`reservas_id`) USING BTREE,
  INDEX `FKi3moriuqr1ufm0etgkku7487r`(`usuario_id`) USING BTREE,
  CONSTRAINT `FKi3moriuqr1ufm0etgkku7487r` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usuarios_reservas
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
