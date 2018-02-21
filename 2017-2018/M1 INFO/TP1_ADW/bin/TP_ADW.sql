# script créé le : Thu Dec 11 21:11:18 CET 2014 -   syntaxe MySQL ;

# use  TP_ADW ;

DROP TABLE IF EXISTS contient ;
CREATE TABLE contient (code_p int AUTO_INCREMENT NOT NULL,
code_a INT NOT NULL,
PRIMARY KEY (code_p,
 code_a) ) ENGINE=InnoDB;

DROP TABLE IF EXISTS Page ;
CREATE TABLE Page (code_p int AUTO_INCREMENT NOT NULL,
theme_p VARCHAR(256),
date_parution_p DATE,
PRIMARY KEY (code_p) ) ENGINE=InnoDB;

DROP TABLE IF EXISTS Article ;
CREATE TABLE Article (code_a int AUTO_INCREMENT NOT NULL,
titre_a VARCHAR(256),
nb_mots_a INT,
tarif_a DOUBLE,
code_aut INT NOT NULL,
PRIMARY KEY (code_a) ) ENGINE=InnoDB;

DROP TABLE IF EXISTS Auteur ;
CREATE TABLE Auteur (code_aut int AUTO_INCREMENT NOT NULL,
nom_aut VARCHAR(256),
statut_aut INT,
date_emb_aut DATE,
PRIMARY KEY (code_aut) ) ENGINE=InnoDB;


ALTER TABLE Article ADD CONSTRAINT FK_Article_code_aut FOREIGN KEY (code_aut) REFERENCES Auteur (code_aut);

ALTER TABLE contient ADD CONSTRAINT FK_contient_code_p FOREIGN KEY (code_p) REFERENCES Page (code_p);
ALTER TABLE contient ADD CONSTRAINT FK_contient_code_a FOREIGN KEY (code_a) REFERENCES Article (code_a);
