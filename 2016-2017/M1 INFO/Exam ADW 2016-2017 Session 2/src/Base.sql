-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:8889
-- Généré le :  Mer 07 Juin 2017 à 09:12
-- Version du serveur :  5.6.35
-- Version de PHP :  7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `dvd2017`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `num_cat` int(11) NOT NULL,
  `lib_cat` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`num_cat`, `lib_cat`) VALUES
(1, 'Sciences Fiction'),
(2, 'Comédie'),
(3, 'Sciences Fiction'),
(4, 'Comédie');

-- --------------------------------------------------------

--
-- Structure de la table `dvd`
--

CREATE TABLE `dvd` (
  `num_dvd` int(11) NOT NULL,
  `titre_dvd` varchar(256) NOT NULL,
  `date_achat_dvd` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `dvd`
--

INSERT INTO `dvd` (`num_dvd`, `titre_dvd`, `date_achat_dvd`) VALUES
(1, 'La dernière folie de Mel Brooks', '2017-05-17'),
(2, 'La dernière folie de Mel Brooks', '2017-05-17'),
(3, 'La totale', '2017-05-03'),
(4, 'La totale', '2017-05-03');

-- --------------------------------------------------------

--
-- Structure de la table `dvd_cat`
--

CREATE TABLE `dvd_cat` (
  `num_cat` int(11) NOT NULL,
  `num_dvd` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `dvd_cat`
--

INSERT INTO `dvd_cat` (`num_cat`, `num_dvd`) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 2);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`num_cat`);

--
-- Index pour la table `dvd`
--
ALTER TABLE `dvd`
  ADD PRIMARY KEY (`num_dvd`);

--
-- Index pour la table `dvd_cat`
--
ALTER TABLE `dvd_cat`
  ADD KEY `num_cat` (`num_cat`,`num_dvd`),
  ADD KEY `num_dvd` (`num_dvd`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `num_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `dvd`
--
ALTER TABLE `dvd`
  MODIFY `num_dvd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `dvd_cat`
--
ALTER TABLE `dvd_cat`
  ADD CONSTRAINT `dvd_cat_ibfk_1` FOREIGN KEY (`num_cat`) REFERENCES `categorie` (`num_cat`),
  ADD CONSTRAINT `dvd_cat_ibfk_2` FOREIGN KEY (`num_dvd`) REFERENCES `dvd` (`num_dvd`);
