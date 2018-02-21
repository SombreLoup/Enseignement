-- phpMyAdmin SQL Dump
-- version 3.2.5
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mar 21 Avril 2015 à 16:38
-- Version du serveur: 5.1.44
-- Version de PHP: 5.2.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `ExamADW2015`
--

-- --------------------------------------------------------

--
-- Structure de la table `celibataire`
--

CREATE TABLE IF NOT EXISTS `celibataire` (
  `code_cel` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cel` varchar(256) NOT NULL,
  `dn_cel` date DEFAULT NULL,
  `cp_cel` varchar(5) DEFAULT NULL,
  `homme` smallint(6) NOT NULL DEFAULT '1',
  PRIMARY KEY (`code_cel`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `celibataire`
--

INSERT INTO `celibataire` VALUES(1, 'albert', '2015-04-05', '57000', 1);
INSERT INTO `celibataire` VALUES(2, 'marie', '2015-04-17', '54000', 0);

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE IF NOT EXISTS `participation` (
  `code_ren` int(11) NOT NULL,
  `code_cel` int(11) NOT NULL,
  KEY `code_ren` (`code_ren`),
  KEY `code_cel` (`code_cel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `participation`
--

INSERT INTO `participation` VALUES(1, 1);
INSERT INTO `participation` VALUES(1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `rencontre`
--

CREATE TABLE IF NOT EXISTS `rencontre` (
  `code_ren` int(11) NOT NULL AUTO_INCREMENT,
  `desc_ren` varchar(256) NOT NULL,
  `date_ren` date DEFAULT NULL,
  `note_ren` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`code_ren`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `rencontre`
--

INSERT INTO `rencontre` VALUES(1, 'Premier RDV', '2015-04-25', 4.8);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `participation_ibfk_2` FOREIGN KEY (`code_cel`) REFERENCES `celibataire` (`code_cel`),
  ADD CONSTRAINT `participation_ibfk_1` FOREIGN KEY (`code_ren`) REFERENCES `rencontre` (`code_ren`);
