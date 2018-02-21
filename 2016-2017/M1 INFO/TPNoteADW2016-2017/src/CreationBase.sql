-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 27 Avril 2017 à 15:34
-- Version du serveur :  5.5.42
-- Version de PHP :  7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `elections2012`
--

-- --------------------------------------------------------

--
-- Structure de la table `bureau_vote`
--

CREATE TABLE `bureau_vote` (
  `num_bur` int(11) NOT NULL,
  `lieu_bur` varchar(256) NOT NULL,
  `num_com` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bureau_vote`
--

INSERT INTO `bureau_vote` (`num_bur`, `lieu_bur`, `num_com`) VALUES
(1, 'Maire-Ecole', 1),
(2, 'Ecole Jules FERRY', 1),
(3, 'Groupe scolaire', 1);

-- --------------------------------------------------------

--
-- Structure de la table `candidat`
--

CREATE TABLE `candidat` (
  `code_cand` int(11) NOT NULL,
  `nom_cand` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commune`
--

CREATE TABLE `commune` (
  `num_com` int(11) NOT NULL,
  `nom_com` varchar(256) NOT NULL,
  `cp_com` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commune`
--

INSERT INTO `commune` (`num_com`, `nom_com`, `cp_com`) VALUES
(1, 'Moulins-lès-Metz', 57160);

-- --------------------------------------------------------

--
-- Structure de la table `resultat_partiel`
--

CREATE TABLE `resultat_partiel` (
  `code_cand` int(11) NOT NULL,
  `num_bur` int(11) NOT NULL,
  `voix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `bureau_vote`
--
ALTER TABLE `bureau_vote`
  ADD PRIMARY KEY (`num_bur`),
  ADD KEY `fk_commune_numero` (`num_com`);

--
-- Index pour la table `candidat`
--
ALTER TABLE `candidat`
  ADD PRIMARY KEY (`code_cand`);

--
-- Index pour la table `commune`
--
ALTER TABLE `commune`
  ADD PRIMARY KEY (`num_com`);

--
-- Index pour la table `resultat_partiel`
--
ALTER TABLE `resultat_partiel`
  ADD KEY `code_cand` (`code_cand`),
  ADD KEY `num_bur` (`num_bur`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `bureau_vote`
--
ALTER TABLE `bureau_vote`
  MODIFY `num_bur` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `candidat`
--
ALTER TABLE `candidat`
  MODIFY `code_cand` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `commune`
--
ALTER TABLE `commune`
  MODIFY `num_com` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `bureau_vote`
--
ALTER TABLE `bureau_vote`
  ADD CONSTRAINT `fk_commune_numero` FOREIGN KEY (`num_com`) REFERENCES `commune` (`num_com`);

--
-- Contraintes pour la table `resultat_partiel`
--
ALTER TABLE `resultat_partiel`
  ADD CONSTRAINT `resultat_partiel_ibfk_1` FOREIGN KEY (`num_bur`) REFERENCES `bureau_vote` (`num_bur`),
  ADD CONSTRAINT `resultat_partiel_ibfk_2` FOREIGN KEY (`code_cand`) REFERENCES `candidat` (`code_cand`);
