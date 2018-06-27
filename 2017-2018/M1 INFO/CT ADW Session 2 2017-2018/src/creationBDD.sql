
-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:8889
-- Généré le :  Jeu 14 Juin 2018 à 16:20
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
  `titulaire_cb` varchar(128) NOT NULL,
  `num_cb` varchar(16) NOT NULL,
  `code_cb` varchar(3) NOT NULL,
  `mois_cb` int(11) NOT NULL,
  `annee_cb` int(11) NOT NULL,
  `num_cl` int(11) NOT NULL,
  `nom_cb` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `cartebancaire`
--

INSERT INTO `cartebancaire` (`id_cb`, `titulaire_cb`, `num_cb`, `code_cb`, `mois_cb`, `annee_cb`, `num_cl`, `nom_cb`) VALUES
(1, 'YANN LANUEL', '1234567890123456', '999', 12, 2019, 1, 'Yann Visa'),
(2, 'ISABELLE LANUEL', '0987654321234567', '888', 10, 2020, 1, 'Isa MasterCard');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `num_cl` int(11) NOT NULL,
  `nom_cl` varchar(128) NOT NULL,
  `prenom_cl` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`num_cl`, `nom_cl`, `prenom_cl`) VALUES
(1, 'LANUEL', 'Yann'),
(2, 'PHILIPPE', 'Edouard');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `num_cde` int(11) NOT NULL,
  `date_cde` date NOT NULL,
  `montant_cde` double NOT NULL,
  `num_cl` int(11) NOT NULL,
  `id_cb` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `commande`
--

INSERT INTO `commande` (`num_cde`, `date_cde`, `montant_cde`, `num_cl`, `id_cb`) VALUES
(1, '2018-06-22', 1500, 1, 0),
(2, '2010-01-31', 1000, 1, NULL),
(3, '2010-01-31', 1000, 1, NULL),
(4, '2010-01-31', 1000, 1, NULL),
(5, '2010-01-31', 1000, 1, NULL),
(6, '2010-01-31', 1000, 1, NULL),
(7, '2010-01-31', 1000, 1, NULL);

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
  ADD KEY `num_cl` (`num_cl`),
  ADD KEY `id_cb` (`id_cb`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `cartebancaire`
--
ALTER TABLE `cartebancaire`
  MODIFY `id_cb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `num_cl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `num_cde` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `cartebancaire`
--
ALTER TABLE `cartebancaire`
  ADD CONSTRAINT `CleEtrangereClient1` FOREIGN KEY (`num_cl`) REFERENCES `client` (`num_cl`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `CleEtrangereClient2` FOREIGN KEY (`num_cl`) REFERENCES `client` (`num_cl`);

