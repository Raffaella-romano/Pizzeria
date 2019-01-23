package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


import beans.ProdottoBean;
import components.DriverManagerConnectionPool;

public class ProductModel {
	private static final String TAB_NAME = "prodotto"; //Nome tabella in DB
	public String db;
	public String username;
	public String password;
	
	public ProductModel(String db, String username, String password) {
	
		this.db = db;
		this.username = username;
		this.password = password;
	}
	
	//Genera query INSERT per salvare un prodotto appena creato ma non ancora venduto
	public synchronized void doAddProduct(ProdottoBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModel.TAB_NAME
				+ " VALUES (?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setDouble(1, product.getCodice());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getTipo());
			preparedStatement.setDouble(4, product.getPrezzo());
			preparedStatement.setString(5, product.getComponenti());
			
			System.out.println(preparedStatement.toString());
			
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	//Genera query INSERT per salvare un nuovo elemento all'interno del DB
	public synchronized void doSave(ProdottoBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModel.TAB_NAME
				+ " (codice, nome, prezzo, ingredienti, Tipo) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getCodice());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setDouble(3, product.getPrezzo());
			preparedStatement.setString(4, product.getComponenti());
			preparedStatement.setString(5, product.getTipo());
			
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}


//Genera la query per eliminare il prodotto dalla tabella
	public synchronized boolean doDeleteInt(int code) 
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String deleteSQL = "DELETE FROM " + ProductModel.TAB_NAME + " WHERE codice = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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
	
public synchronized List<ProdottoBean> doRetrieveProductByName(String nome) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<ProdottoBean> collezione= new ArrayList<ProdottoBean>();
		ProdottoBean bean = new ProdottoBean();


		String selectSQL = "SELECT * FROM " + ProductModel.TAB_NAME + " WHERE Nome = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection(db, username, password);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				bean.setCodice(rs.getInt("Codice"));
				bean.setNome(rs.getString("Nome"));
				bean.setComponenti(rs.getString("Ingredienti"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setTipo(rs.getString("Tipo"));
				
				collezione.add(bean);
			
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return collezione;
	}
public synchronized List<ProdottoBean> doRetrieveAllProduct()throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	List<ProdottoBean> products = new ArrayList<ProdottoBean>();

	String selectSQL = "SELECT * FROM " + ProductModel.TAB_NAME;

	try {
		connection = DriverManagerConnectionPool.getConnection(db, username, password);
		preparedStatement = connection.prepareStatement(selectSQL);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			ProdottoBean bean = new ProdottoBean();
			bean.setCodice(rs.getInt("Codice"));
			bean.setNome(rs.getString("Nome"));
			bean.setComponenti(rs.getString("ingredienti"));
			bean.setPrezzo(rs.getDouble("Prezzo"));
			bean.setTipo(rs.getString("Tipo"));

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
public synchronized Collection<ProdottoBean> doRetrieveProductByType(String tipo)throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();

	String selectSQL = "SELECT * FROM " + ProductModel.TAB_NAME+ " WHERE tipo= ?";
	try {
		connection = DriverManagerConnectionPool.getConnection(db, username, password);
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, tipo);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			ProdottoBean bean = new ProdottoBean();
			bean.setCodice(rs.getInt("Codice"));
			bean.setNome(rs.getString("Nome"));
			bean.setComponenti(rs.getString("ingredienti"));
			bean.setPrezzo(rs.getDouble("Prezzo"));
			bean.setTipo(rs.getString("Tipo"));

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
public synchronized ProdottoBean doRetrieveProductByKey(int code) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	ProdottoBean bean= new ProdottoBean();

	String selectSQL = "SELECT * FROM " + ProductModel.TAB_NAME + " WHERE codice = ?";

	try {
		connection = DriverManagerConnectionPool.getConnection(db, username, password);
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setInt(1, code);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			bean.setCodice(rs.getInt("Codice"));
			bean.setNome(rs.getString("Nome"));
			bean.setComponenti(rs.getString("Ingredienti"));
			bean.setPrezzo(rs.getDouble("Prezzo"));
			bean.setTipo(rs.getString("Tipo"));
			
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
public synchronized Collection<ProdottoBean> doRetrieveProductBySearch(String nome) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Collection<ProdottoBean> collezione= new LinkedList<ProdottoBean>();
	ProdottoBean bean = new ProdottoBean();


	String selectSQL = "SELECT * FROM " + ProductModel.TAB_NAME + " WHERE nome LIKE '%"+nome+"%'";
	try {
		connection = DriverManagerConnectionPool.getConnection(db, username, password);
		preparedStatement = connection.prepareStatement(selectSQL);

		//preparedStatement.setString(1, nome);

		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {

			bean.setCodice(rs.getInt("Codice"));
			bean.setNome(rs.getString("Nome"));
			bean.setComponenti(rs.getString("Ingredienti"));
			bean.setPrezzo(rs.getDouble("Prezzo"));
			bean.setTipo(rs.getString("Tipo"));
			
			collezione.add(bean);
			

			bean=new ProdottoBean();
	
		}

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return collezione;
}
}

