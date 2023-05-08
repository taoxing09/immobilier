-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 10 avr. 2023 à 18:43
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `agence_immobiliere`
--

-- --------------------------------------------------------

--
-- Structure de la table `bien`
--

DROP TABLE IF EXISTS `bien`;
CREATE TABLE IF NOT EXISTS `bien` (
  `id` int(11) NOT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `superficie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `locataire`
--

DROP TABLE IF EXISTS `locataire`;
CREATE TABLE IF NOT EXISTS `locataire` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `dateNaissance` date DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `idBienLoue` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idBienLoue` (`idBienLoue`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `loyer`
--

DROP TABLE IF EXISTS `loyer`;
CREATE TABLE IF NOT EXISTS `loyer` (
  `id` int(11) NOT NULL,
  `idBien` int(11) DEFAULT NULL,
  `idLocataire` int(11) DEFAULT NULL,
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `montant` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idBien` (`idBien`),
  KEY `idLocataire` (`idLocataire`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `visite`
--

DROP TABLE IF EXISTS `visite`;
CREATE TABLE IF NOT EXISTS `visite` (
  `id` int(11) NOT NULL,
  `idLocataire` int(11) DEFAULT NULL,
  `idBien` int(11) DEFAULT NULL,
  `dateVisite` date DEFAULT NULL,
  `heureVisite` time DEFAULT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idLocataire` (`idLocataire`),
  KEY `idBien` (`idBien`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
