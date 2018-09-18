-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Pi 24.Jún 2016, 12:30
-- Verzia serveru: 10.1.10-MariaDB
-- Verzia PHP: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `mdata`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `cats`
--

CREATE TABLE `cats` (
  `id` smallint(6) NOT NULL,
  `meno` varchar(30) COLLATE utf8_slovak_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `cats`
--

INSERT INTO `cats` (`id`, `meno`) VALUES
(1, 'Káva'),
(2, 'Čokoláda'),
(3, 'Čaj'),
(4, 'Čerstvé Šťavy'),
(5, 'Nealko'),
(6, 'Limonada'),
(7, 'ladovy čaj'),
(8, 'Mieš neal drink'),
(9, 'Alko'),
(10, 'welcome drink'),
(11, 'Šampanske'),
(12, 'Likér'),
(13, 'Rum'),
(14, 'Brandy'),
(15, 'Whiskey'),
(16, 'Burbon'),
(17, 'Tequila'),
(18, 'Cognac'),
(19, 'Gin'),
(20, 'Mieš. al drink'),
(21, 'Pivo'),
(22, 'Nalievane vino'),
(23, 'Matyšak'),
(24, 'Nichta Vino'),
(25, 'Chateau Freistadt'),
(26, 'Tokaj'),
(27, 'Ine Vina'),
(28, 'Zvyhodnene menu'),
(29, 'Panini'),
(30, 'Dezert'),
(31, 'Pochutiny'),
(32, 'Pizza'),
(33, 'Burger');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `items`
--

CREATE TABLE `items` (
  `idItem` smallint(6) NOT NULL,
  `kod` smallint(6) DEFAULT NULL,
  `idCat` smallint(6) DEFAULT NULL,
  `nazov` varchar(48) DEFAULT NULL,
  `cena` decimal(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sťahujem dáta pre tabuľku `items`
--

INSERT INTO `items` (`idItem`, `kod`, `idCat`, `nazov`, `cena`) VALUES
(1, 1, 1, 'Espresso', '1.25'),
(2, 2, 1, 'Cappucino', '1.65'),
(3, 146, 1, 'Cappucino špeciál', '1.00'),
(4, 3, 1, 'Caffé Latte', '1.85'),
(5, 145, 1, 'Caffé Latte špeciál', '1.00'),
(6, 4, 1, 'Doppio', '1.95'),
(7, 6, 1, 'Viedenská ', '1.75'),
(8, 7, 1, 'Zalievaná', '1.05'),
(9, 8, 1, 'Alžírska', '2.15'),
(10, 9, 1, 'Írska', '2.15'),
(11, 11, 1, 'Bezkofeinivá ', '1.55'),
(12, 10, 1, 'Frappé', '2.25'),
(13, 12, 1, 'Mlieko', '0.10'),
(14, 14, 2, 'Horká ', '2.15'),
(15, 15, 2, 'Biela', '2.15'),
(16, 14, 2, 'Oriešková', '2.15'),
(17, 1993, 2, 'šľahačka', '0.50'),
(18, 18, 3, 'Mäta', '1.45'),
(19, 18, 3, 'Zelený', '1.45'),
(20, 18, 3, 'Harmanček', '1.45'),
(21, 18, 3, 'Čierny', '1.45'),
(22, 18, 3, 'Biely', '1.45'),
(23, 18, 3, 'Lesné plody', '1.45'),
(24, 31, 4, 'Jablko', '0.95'),
(25, 31, 4, 'Pomaranč', '0.95'),
(26, 31, 4, 'Limetka', '0.95'),
(27, 31, 4, 'Ananás', '0.95'),
(28, 31, 4, 'Jahoda', '0.95'),
(29, 31, 4, 'Kiwi', '0.95'),
(30, 19, 5, 'Coca Cola', '1.35'),
(31, 22, 5, 'Sprite', '1.35'),
(32, 23, 5, 'Kinley Tonic', '1.35'),
(33, 23, 5, 'Kinley Tonic zázvor', '1.35'),
(34, 24, 5, 'Džús Granini', '1.35'),
(36, 28, 5, 'RedBull', '2.45'),
(37, 29, 5, 'Kofola čapovaná', '0.30'),
(38, 133, 5, 'voda s citrónom', '0.15'),
(39, 105, 6, 'malá O,3l', '1.25'),
(40, 106, 6, 'stredná 0,5l', '1.95'),
(41, 107, 6, 'Veľká 1l', '2.65'),
(42, 1992, 7, 'maly', '1.65'),
(43, 1991, 7, 'velky', '2.15'),
(44, 118, 8, 'Levanduľový sen 0,35l', '2.95'),
(45, 118, 8, 'Pinky Pietra 0,35l', '2.95'),
(46, 118, 8, 'Mariannus Forest', '2.95'),
(47, 1990, 9, 'Pivovica M. 52%', '1.95'),
(48, 32, 9, 'Fínska vodka 40%', '1.55'),
(49, 147, 9, 'Double Cross vodka 40%', '6.25'),
(50, 33, 9, 'Nicolaus vodka 38%', '1.25'),
(51, 124, 9, 'Absolut vodka 40%', '1.55'),
(52, 124, 9, 'Absolut vodka citrus 40%', '1.55'),
(53, 34, 9, 'Hruškovica 42%', '1.85'),
(54, 35, 9, 'Bošácka slivovica 52%', '1.45'),
(55, 36, 9, 'Borovička 40%', '1.45'),
(56, 125, 9, 'Tatranský čaj 52%', '1.95'),
(57, 126, 10, 'Martini 15%', '2.15'),
(58, 1989, 10, 'Prosecco Treviso 12%', '12.00'),
(59, 1988, 10, 'Bianco Castellino 10%', '1.85'),
(60, 1987, 11, 'Hubert Deluxe 7%', '9.00'),
(61, 1986, 11, 'Moët et Chandon 12%', '75.00'),
(62, 38, 12, 'Fernet Stock 38%', '1.55'),
(63, 39, 12, 'Fernet Stock citrus 27%', '1.55'),
(64, 40, 12, 'Becherovka 38%', '1.65'),
(65, 41, 12, 'Vaječný  likér 17% ', '1.45'),
(66, 122, 12, 'Jägermeister 35%', '2.15'),
(67, 123, 12, 'Griotka 35%', '1.25'),
(68, 154, 12, 'Bayleis 17%', '2.15'),
(69, 166, 13, 'Ron Zacapa 23yo 40%', '5.95'),
(70, 37, 13, 'Captain Morgan 35%', '1.75'),
(71, 127, 13, 'Malibu 21%', '1.75'),
(72, 115, 13, 'Bacardi 37,5%', '1.95'),
(73, 115, 13, 'Bacardi Black 37,5%', '1.95'),
(74, 115, 13, 'Bacardi Oakheart 37,5%', '1.95'),
(75, 42, 14, 'Karpatské Brandy 40%', '2.15'),
(76, 167, 14, 'Karpatské Brandy špeciál 40%', '3.65'),
(77, 43, 14, 'Metaxa 5* 38%', '1.95'),
(78, 46, 15, 'Jack Daniels 40%', '2.65'),
(79, 45, 15, 'Tullamore Dew 40%', '2.15'),
(80, 44, 15, 'Jameson 40%', '2.15'),
(81, 152, 15, 'Ballantines 40%', '2.35'),
(82, 170, 15, 'Chivas Regal 12yo 40%', '3.15'),
(83, 171, 15, 'Black Burn 40%', '2.95'),
(84, 169, 16, 'Jim Beam  40%', '1.85'),
(85, 128, 17, 'Pepe Lopez 40%', '1.55'),
(86, 129, 18, 'Hennessy V.S. 40%', '3.25'),
(87, 168, 18, 'Braastad V.S.O.P. 40%', '4.45'),
(88, 153, 19, 'Beefeater 40%', '2.15'),
(89, 130, 20, 'Bavorák 0,35l', '2.85'),
(90, 131, 20, 'Cuba Libre 0,35l', '3.15'),
(91, 1985, 20, 'Pinakoláda 0,35l', '3.75'),
(92, 132, 20, 'Summertime 0,35l', '3.75'),
(93, 1984, 20, 'Aperol Spritz 0,2l', '3.95'),
(94, 50, 21, 'Mariannus svetlý 11°', '1.00'),
(95, 51, 21, 'Mariannus svetlý 11°', '1.35'),
(96, 137, 21, 'Mariannus svetlý 11°', '2.60'),
(97, 1983, 21, 'M. polotmavý 16°', '1.10'),
(98, 1982, 21, 'M. polotmavý 16°', '1.50'),
(99, 1981, 21, 'M. polotmavý 16°', '2.75'),
(100, 1983, 21, 'Mariannus tmavý 14°', '1.10'),
(101, 1982, 21, 'Mariannus tmavý 14°', '1.50'),
(102, 1981, 21, 'Mariannus tmavý 14°', '2.75'),
(103, 1983, 21, 'Mariannus višňový 13°', '1.10'),
(104, 1982, 21, 'Mariannus višňový 13°', '1.50'),
(105, 1981, 21, 'Mariannus višňový 13°', '2.75'),
(106, 47, 22, 'Nichta, Rizling vlašský, akostné', '1.65'),
(107, 48, 22, 'Nichta, Frankovka modrá, akostné', '1.65'),
(108, 48, 22, 'Piont Noir', '1.65'),
(109, 75, 23, 'Chardonnay 2013', '15.00'),
(110, 75, 23, 'Frankovka modrá 2013', '15.00'),
(111, 75, 23, '2009 Cabernet Sauvignon ', '15.00'),
(112, 75, 23, '2013 Cabernet Sauvignon', '15.00'),
(113, 67, 24, 'Muškát Moravský  2015', '10.00'),
(114, 68, 24, 'Tramín červcený 2014', '14.00'),
(115, 69, 24, 'Devín 2014', '16.00'),
(116, 70, 24, 'Dunaj 2014', '16.00'),
(117, 1980, 24, 'Frankovka modrá 2014', '8.50'),
(118, 1980, 24, 'Rízling vlašský 2015', '8.50'),
(119, 1979, 25, 'Tramín Červený 2014', '17.00'),
(120, 1979, 25, 'Alibernet 2011', '17.00'),
(121, 73, 26, 'Furmint Mono 2011', '18.00'),
(122, 74, 26, 'Macik Cuvée 2012', '18.00'),
(123, 1978, 27, 'Pinot Noir  2015', '8.50'),
(124, 1977, 27, 'Muškát Moravský  2014', '12.00'),
(125, 1976, 27, 'Pionot Gris 2013', '18.00'),
(126, 1975, 27, 'Chardonnay sel 2013', '24.00'),
(127, 1974, 28, 'Káva, 2x domáca špaldová sušienka , jogurt, džús', '2.90'),
(128, 1973, 28, 'Paninni, čaj', '2.90'),
(129, 1972, 28, '1l Pivo M., Pivný chlieb s nátierkou', '3.50'),
(130, 82, 29, 'Šunkové', '2.35'),
(131, 82, 29, 'Syrové', '2.35'),
(132, 162, 30, 'Marlenka', '2.85'),
(133, 62, 30, 'Zmrzlinový pohár', '2.35'),
(134, 1971, 30, 'Ovocný pohár so Šľahačkou', '2.00'),
(135, 1970, 30, 'cheesacake', '2.65'),
(136, 58, 31, 'Chipsy', '1.30'),
(137, 59, 31, 'Tyčinky Dru', '0.90'),
(138, 60, 31, 'Solené Arašidy', '0.85'),
(139, 164, 31, 'Pivný chlieb s nátierkou', '0.50'),
(140, 65, 32, 'Margherita', '4.50'),
(141, 65, 32, 'Vegetariana', '4.50'),
(142, 65, 32, 'Quatro', '4.50'),
(143, 65, 32, 'Athena', '4.50'),
(144, 65, 32, 'Salamandra', '4.50'),
(145, 65, 32, 'Buffalo Bill', '4.50'),
(146, 65, 32, 'Vesuvio', '4.50'),
(147, 65, 32, 'Pompej', '4.50'),
(148, 65, 32, 'El Diablo', '4.50'),
(149, 65, 32, 'Hawai', '4.50'),
(150, 65, 32, 'Thai', '4.50'),
(151, 98, 33, 'Domáci Hamburger', '4.90'),
(152, 99, 33, 'Domáci Cheesburger', '4.90'),
(153, 100, 33, 'Domáci Baconbugrer', '4.90');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `olist`
--

CREATE TABLE `olist` (
  `id` int(11) NOT NULL,
  `idi` smallint(6) DEFAULT NULL,
  `ido` mediumint(9) DEFAULT NULL,
  `paid` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `olist`
--

INSERT INTO `olist` (`id`, `idi`, `ido`, `paid`) VALUES
(1, 33, 1, 1),
(2, 26, 1, 0),
(3, 25, 1, 1),
(7, 122, 26, 1),
(14, 75, 26, 1),
(16, 32, 26, 1),
(17, 36, 26, 1),
(19, 81, 1, 0),
(20, 6, 27, 1),
(21, 8, 27, 1),
(22, 10, 27, 1),
(23, 34, 27, 1),
(24, 36, 27, 1),
(25, 37, 27, 1),
(26, 22, 27, 1),
(27, 19, 27, 1),
(28, 20, 27, 1),
(29, 41, 28, 1),
(30, 152, 28, 1),
(31, 153, 28, 1),
(32, 76, 28, 1),
(33, 16, 28, 1),
(34, 16, 28, 1),
(35, 16, 28, 1),
(36, 92, 28, 1),
(37, 93, 28, 1),
(38, 76, 29, 1),
(39, 45, 29, 1),
(40, 92, 29, 1),
(41, 20, 30, 1),
(42, 21, 30, 1),
(43, 22, 30, 1),
(44, 23, 30, 1),
(45, 87, 30, 1),
(46, 86, 30, 1),
(47, 80, 30, 1),
(48, 82, 30, 1),
(49, 83, 30, 1),
(50, 120, 30, 1),
(51, 80, 29, 1),
(52, 75, 31, 1),
(53, 77, 31, 1),
(54, 76, 31, 1),
(55, 142, 31, 1),
(56, 145, 31, 1),
(57, 148, 31, 1),
(64, 10, 32, 1),
(65, 88, 30, 1),
(66, 10, 32, 1),
(67, 84, 33, 1),
(68, 80, 34, 1),
(69, 77, 35, 0),
(70, 76, 35, 1),
(71, 75, 35, 0),
(72, 97, 36, 1),
(73, 78, 36, 1),
(74, 20, 36, 1),
(75, 21, 36, 1),
(76, 142, 36, 1),
(77, 20, 36, 1),
(78, 67, 36, 1),
(79, 153, 38, 0),
(80, 128, 38, 1),
(81, 31, 38, 1),
(82, 36, 38, 0),
(83, 34, 38, 0),
(84, 61, 36, 0),
(85, 112, 36, 0),
(86, 111, 36, 0),
(87, 96, 39, 0),
(88, 122, 39, 1),
(89, 63, 40, 0),
(90, 64, 40, 0);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `order`
--

CREATE TABLE `order` (
  `idO` mediumint(9) NOT NULL,
  `idT` tinyint(4) DEFAULT NULL,
  `name` varchar(30) COLLATE utf8_slovak_ci DEFAULT NULL,
  `ok` tinyint(1) DEFAULT NULL,
  `datum` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `order`
--

INSERT INTO `order` (`idO`, `idT`, `name`, `ok`, `datum`) VALUES
(1, 1, 'salez', 1, NULL),
(26, 2, 'matura', 1, NULL),
(27, 3, 'borci', 1, NULL),
(28, 4, 'oslava', 1, NULL),
(29, 5, 'jhg', 1, NULL),
(30, 8, 'barsdobre', 1, NULL),
(31, 6, 'test', 1, NULL),
(32, 2, 'supertest', 1, NULL),
(33, 1, 'delo', 1, NULL),
(34, 1, 'gol', 1, NULL),
(35, 8, 'tuki', 0, NULL),
(36, 9, '6', 0, NULL),
(37, 5, 'stôl 4', 1, NULL),
(38, 33, 'monika', 0, NULL),
(39, 1, 'stôl 1', 0, NULL),
(40, 6, 'bar 1', 0, '2016-06-20');

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `cats`
--
ALTER TABLE `cats`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pre tabuľku `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`idItem`),
  ADD KEY `items_cats_id_fk` (`idCat`);

--
-- Indexy pre tabuľku `olist`
--
ALTER TABLE `olist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `table_name_order_idO_fk` (`ido`),
  ADD KEY `table_name_items_idItem_fk` (`idi`);

--
-- Indexy pre tabuľku `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`idO`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `cats`
--
ALTER TABLE `cats`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT pre tabuľku `items`
--
ALTER TABLE `items`
  MODIFY `idItem` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=154;
--
-- AUTO_INCREMENT pre tabuľku `olist`
--
ALTER TABLE `olist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;
--
-- AUTO_INCREMENT pre tabuľku `order`
--
ALTER TABLE `order`
  MODIFY `idO` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- Obmedzenie pre exportované tabuľky
--

--
-- Obmedzenie pre tabuľku `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `items_cats_id_fk` FOREIGN KEY (`idCat`) REFERENCES `cats` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Obmedzenie pre tabuľku `olist`
--
ALTER TABLE `olist`
  ADD CONSTRAINT `table_name_items_idItem_fk` FOREIGN KEY (`idi`) REFERENCES `items` (`idItem`),
  ADD CONSTRAINT `table_name_order_idO_fk` FOREIGN KEY (`ido`) REFERENCES `order` (`idO`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
