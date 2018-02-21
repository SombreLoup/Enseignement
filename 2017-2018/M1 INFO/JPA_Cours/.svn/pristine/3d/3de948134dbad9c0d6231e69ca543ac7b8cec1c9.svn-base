-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Jeu 23 Octobre 2014 à 09:38
-- Version du serveur: 5.1.37
-- Version de PHP: 5.2.11

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `GestionAlbum`
--

-- --------------------------------------------------------

--
-- Structure de la table `album`
--

CREATE TABLE `album` (
  `code_album` int(11) NOT NULL AUTO_INCREMENT,
  `titre_album` varchar(128) NOT NULL,
  `annee_album` int(11) DEFAULT '0',
  PRIMARY KEY (`code_album`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `chanson`
--

CREATE TABLE `chanson` (
  `code_chanson` int(11) NOT NULL AUTO_INCREMENT,
  `titre_chanson` varchar(128) NOT NULL,
  `code_genre` int(11) NOT NULL,
  `code_album` int(11) DEFAULT NULL,
  `code_int` int(11) DEFAULT NULL,
  PRIMARY KEY (`code_chanson`),
  KEY `cle_etr_genre` (`code_genre`),
  KEY `cle_etr_album` (`code_album`),
  KEY `cle_etr_interprete` (`code_int`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

-- --------------------------------------------------------

--
-- Structure de la table `concert`
--

CREATE TABLE `concert` (
  `code_concert` int(11) NOT NULL AUTO_INCREMENT,
  `nom_concert` varchar(128) NOT NULL,
  `date_concert` date NOT NULL,
  PRIMARY KEY (`code_concert`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `genre`
--

CREATE TABLE `genre` (
  `code_genre` int(11) NOT NULL AUTO_INCREMENT,
  `lib_genre` varchar(128) NOT NULL,
  PRIMARY KEY (`code_genre`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

-- --------------------------------------------------------

--
-- Structure de la table `interprete`
--

CREATE TABLE `interprete` (
  `code_int` int(11) NOT NULL AUTO_INCREMENT,
  `nom_int` varchar(128) NOT NULL,
  PRIMARY KEY (`code_int`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

-- --------------------------------------------------------

--
-- Structure de la table `participation_concert`
--

CREATE TABLE `participation_concert` (
  `code_int` int(11) NOT NULL AUTO_INCREMENT,
  `code_concert` int(11) NOT NULL,
  PRIMARY KEY (`code_int`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;