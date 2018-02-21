-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:8889
-- Généré le :  Lun 08 Mai 2017 à 15:52
-- Version du serveur :  5.6.35
-- Version de PHP :  7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `Elections2012`
--

--
-- Contenu de la table `bureau_vote`
--

INSERT INTO `bureau_vote` (`num_bur`, `lieu_bur`, `num_com`) VALUES
(1, 'Groupe scolaire', 1),
(2, 'Mairie-Ecole', 1),
(3, 'Ecole Jules FERRY', 1);

--
-- Contenu de la table `candidat`
--

INSERT INTO `candidat` (`code_cand`, `nom_cand`) VALUES
(1, 'Mme Eva JOLY'),
(2, 'M. Nicolas SARKOZY'),
(3, 'Mme Marine LE PEN'),
(4, 'M. Jean-Luc MÉLENCHON'),
(5, 'M. Philippe POUTOU'),
(6, 'Mme Nathalie ARTHAUD'),
(7, 'M. Jacques CHEMINADE'),
(8, 'M. François BAYROU'),
(9, 'M. Nicolas DUPONT-AIGNAN'),
(10, 'M. François HOLLANDE');

--
-- Contenu de la table `commune`
--

INSERT INTO `commune` (`num_com`, `nom_com`, `cp_com`) VALUES
(1, 'Moulins-lès-Metz', 57160);

--
-- Contenu de la table `resultat_partiel`
--

INSERT INTO `resultat_partiel` (`code_cand`, `num_bur`, `voix`) VALUES
(1, 1, 16),
(1, 2, 22),
(1, 3, 17),
(2, 1, 423),
(2, 2, 102),
(2, 3, 361),
(3, 1, 312),
(3, 2, 134),
(3, 3, 141),
(4, 1, 67),
(4, 2, 112),
(4, 3, 127),
(5, 1, 11),
(5, 2, 3),
(5, 3, 23),
(6, 1, 12),
(6, 2, 7),
(6, 3, 4),
(7, 1, 1),
(7, 2, 2),
(7, 3, 4),
(8, 1, 78),
(8, 2, 25),
(8, 3, 167),
(9, 1, 22),
(9, 2, 11),
(9, 3, 24),
(10, 1, 459),
(10, 2, 209),
(10, 3, 37);