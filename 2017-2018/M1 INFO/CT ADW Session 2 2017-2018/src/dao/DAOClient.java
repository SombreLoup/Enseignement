package dao;

import cb.Client;

public interface DAOClient {
	void update(Client client);
	Client get(int numClient);
}
