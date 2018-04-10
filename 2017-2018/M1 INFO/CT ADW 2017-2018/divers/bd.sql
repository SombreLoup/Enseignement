CREATE TABLE `cheval` (
  `code_ch` int(11) NOT NULL,
  `nom_ch` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `course` (
  `num_c` int(11) NOT NULL,
  `nom_c` varchar(256) NOT NULL,
  `recompense` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `participation` (
  `num_p` int(11) NOT NULL,
  `date_p` date NOT NULL,
  `code_ch` int(11) NOT NULL,
  `num_c` int(11) NOT NULL,
  `place` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `cheval`
  ADD PRIMARY KEY (`code_ch`);

ALTER TABLE `course`
  ADD PRIMARY KEY (`num_c`);

ALTER TABLE `participation`
  ADD PRIMARY KEY (`num_p`),
  ADD KEY `code_ch` (`code_ch`),
  ADD KEY `num_c` (`num_c`);