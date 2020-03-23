package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Message;
import beans.Utilisateur;

public class MySqlImpl implements DaoInterface {

	private DaoFactory daoFactory;

	public MySqlImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Utilisateur findUserByLoginPwd(HttpServletRequest req) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		Utilisateur user = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"select nom,prenom,login,id_user,numero_compte,profil_user,solde_compte from users where login=? and mot_de_passe=?");
			preparedStatement.setString(1, req.getParameter("login"));
			preparedStatement.setString(2, req.getParameter("mdp"));
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				user = fetchUtilisateur(resultat);
			}
			connexion.commit();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (resultat != null)
					resultat.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return user;
	}

	@Override
	public List<Utilisateur> findAllUsers() {
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("select nom,prenom,login,id_user from users");

			while (resultat.next()) {
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String login = resultat.getString("login");
				int id = resultat.getInt("id_user");

				Utilisateur user = new Utilisateur(login, id, prenom, nom);
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			// Fermeture de la connexion
			try {
				if (resultat != null)
					resultat.close();
				if (statement != null)
					statement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}
		return users;
	}

	@Override
	public boolean transfert(HttpServletRequest req, String src) {
		Connection connexion = null;
		PreparedStatement destpreparedStatement = null;
		PreparedStatement srcpreparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			// Destination solde+montant
			destpreparedStatement = connexion
					.prepareStatement("update users set solde_compte=solde_compte+? where numero_compte=?");
			destpreparedStatement.setString(1, req.getParameter("montant"));
			destpreparedStatement.setString(2, req.getParameter("destination"));
			destpreparedStatement.executeUpdate();
			// Source solde-montant
			srcpreparedStatement = connexion
					.prepareStatement("update users set solde_compte=solde_compte-? where numero_compte=?");
			srcpreparedStatement.setString(1, req.getParameter("montant"));
			srcpreparedStatement.setString(2, src);
			srcpreparedStatement.executeUpdate();
			connexion.commit();

		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return true;
	}

	@Override
	public List<Message> findMessagesInbox(HttpServletRequest req, int userId) {
		List<Message> list = new ArrayList<Message>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("select id_msg,sujet_msg,corps_msg,u.nom,u.prenom from messages m, users u where m.id_user_from=u.id_user and id_user_to="+userId);

			while (resultat.next()) {
				list.add(fetchMessage(resultat));
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			// Fermeture de la connexion
			try {
				if (resultat != null)
					resultat.close();
				if (statement != null)
					statement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}
		return list;
	}

	@Override
	public boolean addMessage(HttpServletRequest req, int idFrom) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"insert into messages(id_user_to,id_user_from,sujet_msg,corps_msg) values(?,?,?,?)");
			preparedStatement.setInt(1, Integer.parseInt(req.getParameter("to")));
			preparedStatement.setInt(2, idFrom);
			preparedStatement.setString(3, req.getParameter("sujet"));
			preparedStatement.setString(4, req.getParameter("corps"));
			preparedStatement.executeUpdate();

			connexion.commit();

		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return true;

	}

	@Override
	public Utilisateur fetchUtilisateur(ResultSet resultat) throws SQLException {
		String nom = resultat.getString("nom");
		String prenom = resultat.getString("prenom");
		String login = resultat.getString("login");
		int id = resultat.getInt("id_user");
		String numero = resultat.getString("numero_compte");
		String profil = resultat.getString("profil_user");
		int solde = resultat.getInt("solde_compte");
		Utilisateur user = new Utilisateur(login, id, prenom, nom, numero, profil, solde);
		return user;
	}

	@Override
	public Message fetchMessage(ResultSet resultat) throws SQLException {
		String nom = resultat.getString("u.nom");
		String prenom = resultat.getString("u.prenom");
		String sujet = resultat.getString("sujet_msg");
		String corps = resultat.getString("corps_msg");
		
		Message msg = new Message(nom, prenom, sujet, corps);
		return msg;
	}

}
