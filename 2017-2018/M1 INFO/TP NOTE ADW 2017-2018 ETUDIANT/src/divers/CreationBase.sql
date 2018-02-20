CREATE TABLE `classe` (
  `id_cl` int(11) NOT NULL,
  `nom_cl` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `devoir` (
  `code_dev` int(11) NOT NULL,
  `desc_dev` varchar(512) NOT NULL,
  `date_lim` date NOT NULL,
  `id_cl` int(11) NOT NULL,
  `num_mat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `est_composee` (
  `id_cl` int(11) NOT NULL,
  `num_mat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `matiere` (
  `num_mat` int(11) NOT NULL,
  `nom_mat` varchar(64) NOT NULL,
  `epreuve_bac` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `classe`
  ADD PRIMARY KEY (`id_cl`);

ALTER TABLE `devoir`
  ADD PRIMARY KEY (`code_dev`);

ALTER TABLE `matiere`
  ADD PRIMARY KEY (`num_mat`);


ALTER TABLE `classe`
  MODIFY `id_cl` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `devoir`
  MODIFY `code_dev` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `matiere`
  MODIFY `num_mat` int(11) NOT NULL AUTO_INCREMENT;
  
  INSERT INTO `classe` (`id_cl`, `nom_cl`) VALUES
(1, '1ère S 4'),
(2, '1ère S 12');

INSERT INTO `devoir` (`code_dev`, `desc_dev`, `date_lim`, `id_cl`, `num_mat`) VALUES
(1, 'Faire l''exercice 45 page 165', '2018-01-18', 1, 1);

INSERT INTO `est_composee` (`id_cl`, `num_mat`) VALUES
(1, 1),
(1, 3),
(1, 7);

INSERT INTO `matiere` (`num_mat`, `nom_mat`, `epreuve_bac`) VALUES
(1, 'Mathématiques', 1),
(2, 'Français', 1),
(3, 'Physique', 0),
(4, 'Philosophie', 0),
(5, 'Allemand LV1', 1),
(6, 'Allemand LV2', 0),
(7, 'Anglais LV1', 1),
(8, 'Anglais LV2', 0);