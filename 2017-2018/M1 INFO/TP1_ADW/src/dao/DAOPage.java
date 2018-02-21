package dao;

import core.Page;


public interface DAOPage {
	Page	get(int code);
	void	save(Page page);
	void	remove(Page page);
	int		getNombrePages();
}
