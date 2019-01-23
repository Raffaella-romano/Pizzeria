package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import beans.*;
import components.*;

public class ClientModel{

	private static final String TAB_NAME = "cliente"; //Nome tabella in DB
	public String db;
	public String username;
	public String password;
	
	public ClientModel(String db, String username, String password) {
	
		this.db = db;
		this.username = username;
		this.password = password;
	}
	
	//Genera query INSERT per salvare un nuovo elemento all'interno del DB
	public synchronized int doSave(ClienteBean cliente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ClienteBean cb = new ClienteBean();
		String selectSQL = "SELECT * FROM cliente WHERE username = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cliente.getUsername());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				cb.setUsername(rs.getString("username"));
				
		} }finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println(cb.getUsername());
		if(cb.getUsername()=="") {
		Connection connection2 = null;
		PreparedStatement preparedStatement2 = null;

		String insertSQL = "INSERT INTO " + ClientModel.TAB_NAME
				+ " VALUES (?, ?, ?, ?)";

		try {
			connection2 = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement2 = connection2.prepareStatement(insertSQL);
			preparedStatement2.setString(1, cliente.getUsername());
			preparedStatement2.setString(2, cliente.getNome());
			preparedStatement2.setString(3, cliente.getCognome());
			preparedStatement2.setString(4, cliente.getPassword());

			
			System.out.println(preparedStatement2.toString());
			
			preparedStatement2.executeUpdate();

			connection2.commit();
			return 1;
		} finally {
			try {
				if (preparedStatement2 != null)
					preparedStatement2.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection2);
			}
		}
		}
		else {
			return 0;
		}
	}


	//Genera query DELETE per eliminare la riga identificata da 'code' all'interno del DB
	public synchronized boolean doDeleteString(String code) 
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ClientModel.TAB_NAME + " WHERE username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);

			result = preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);

			
	}

	//Genera query SELECT per ricevere i dati in base a quella determinata key
	
	public synchronized ClienteBean doRetrieveClientByName(String user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ClienteBean bean = new ClienteBean();

		String selectSQL = "SELECT * FROM " + ClientModel.TAB_NAME + " WHERE username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("psw"));

				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	//genera query SELECT * per prendere tutte le righe dal DB in un certo ordine 

	public synchronized Collection<ClienteBean> doRetrieveAllClient() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ClienteBean> products = new LinkedList<ClienteBean>();

		String selectSQL = "SELECT * FROM " + ClientModel.TAB_NAME;
		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ClienteBean bean = new ClienteBean();
				bean.setUsername(rs.getString("username"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
		
	}



}

