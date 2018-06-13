package dao.jpa;

import cb.Client;
import dao.DAOClient;

public class DAOClientJPA implements DAOClient {
	
	private	static DAOClientJPA		instance = null;

	public static DAOClient getInstance() {
		if (instance==null)
			instance = new DAOClientJPA();
		return instance;
	}
	
	@Override
	public Client get(int numClient) {
		return (Client)DAOJPA.getManager().find(Client.class, numClient);
	}

	@Override
	public void update(Client client) {
		Client merge = DAOJPA.getManager().merge(client);
		DAOJPA.getManager().persist(merge);
		DAOJPA.commit();
	}

}
