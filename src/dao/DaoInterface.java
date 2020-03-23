package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Message;
import beans.Utilisateur;

public interface DaoInterface {
	
	Utilisateur findUserByLoginPwd(HttpServletRequest req);
	List<Utilisateur> findAllUsers();
	boolean transfert(HttpServletRequest req, String src);
	List<Message> findMessagesInbox(HttpServletRequest req, int userId);
	boolean addMessage(HttpServletRequest req, int idFrom);
	Utilisateur fetchUtilisateur(ResultSet resultat) throws SQLException;
	Message fetchMessage(ResultSet resultat) throws SQLException;
	
}
