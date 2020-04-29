package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private String url;
	private String username;
	private String password;

	public static DaoFactory dao = null;

	DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DaoFactory getInstance() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}
		if (dao == null) {
			dao = new DaoFactory("jdbc:mysql://localhost:8889/sr03", "root", "root");
		}

		return dao;
	}

	public Connection getConnection() throws SQLException {
		Connection connexion = DriverManager.getConnection(url, username, password);
		connexion.setAutoCommit(false);
		return connexion;
	}

	// Récupération du Dao
	public DaoInterface getMySqlImpl() {
		return new MySqlImpl(this);
	}
}
