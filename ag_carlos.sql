-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2022 a las 11:27:33
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agenda_carlos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ag_carlos`
--

CREATE TABLE `ag_carlos` (
  `fecha` text COLLATE latin1_spanish_ci NOT NULL,
  `nota` text COLLATE latin1_spanish_ci NOT NULL,
  `codigo_nota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `ag_carlos`
--

INSERT INTO `ag_carlos` (`fecha`, `nota`, `codigo_nota`) VALUES
('20', 'buenas tardes', 13),
('20 octubre 2020', 'Prueba Final', 14);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ag_carlos`
--
ALTER TABLE `ag_carlos`
  ADD PRIMARY KEY (`codigo_nota`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ag_carlos`
--
ALTER TABLE `ag_carlos`
  MODIFY `codigo_nota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
