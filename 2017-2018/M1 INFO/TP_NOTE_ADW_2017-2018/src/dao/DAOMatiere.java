package dao;

import java.util.List;

import core.Matiere;

public interface DAOMatiere {

	List<Matiere> getToutesLesMatieres();

	Matiere get(int num);

}
