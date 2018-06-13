-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:8889
-- Généré le :  Lun 11 Juin 2018 à 17:06
-- Version du serveur :  5.6.35
-- Version de PHP :  7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `ct_adw_2018_session_2`
--

-- --------------------------------------------------------

--
-- Structure de la table `cartebancaire`
--

CREATE TABLE `cartebancaire` (
  `id_cb` int(11) NOT NULL,
  `num_cb` varchar(16) NOT NULL,
  `code_cb` varchar(3) NOT NULL,
  `mois_cb` int(11) NOT NULL,
  `annee_cb` int(11) NOT NULL,
  `num_cl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `num_cl` int(11) NOT NULL,
  `nom_cl` varchar(128) NOT NULL,
  `prenom_cl` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `num_cde` int(11) NOT NULL,
  `date_cde` date NOT NULL,
  `montant_cde` double NOT NULL,
  `num_cl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `cartebancaire`
--
ALTER TABLE `cartebancaire`
  ADD PRIMARY KEY (`id_cb`),
  ADD KEY `num_cl` (`num_cl`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`num_cl`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`num_cde`),
  ADD KEY `num_cl` (`num_cl`);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `cartebancaire`
--
ALTER TABLE `cartebancaire`
  ADD CONSTRAINT `cartebancaire_ibfk_1` FOREIGN KEY (`num_cl`) REFERENCES `client` (`num_cl`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`num_cl`) REFERENCES `client` (`num_cl`);
