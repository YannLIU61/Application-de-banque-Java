package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Message;
import beans.Utilisateur;
import dao.DaoFactory;
import dao.DaoInterface;

/**
 * Servlet implementation class MyController
 */
@WebServlet("/MyController")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoInterface utlisateurDao;

//	Initialisation la connection
	public void init() {
		DaoFactory daofac = DaoFactory.getInstance();
		this.utlisateurDao = daofac.getMySqlImpl();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "msglist":
			Msglist(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		switch (action) {
		case "authenticate":
			Authenticate(request, response);
			break;
		case "disconnect":
			Disconnect(request, response);
			break;
		case "transfert":
			Transfert(request, response);
			break;
		case "sendmsg":
			SendMsg(request, response);
			break;
		}

	}

	private void Msglist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(request.getParameter("userid"));
		List<Message> messageList = utlisateurDao.findMessagesInbox(request, userId);
		session.setAttribute("messagesRecus", messageList);

		this.getServletContext().getRequestDispatcher("/WEB-INF/vw_messagerie.jsp").forward(request, response);

	}

	private void SendMsg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("connected_user");
		if (utlisateurDao.addMessage(request, user.getId())) {
			request.setAttribute("msg_ok", "Message envoyé avec succès.");
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/vw_moncompte.jsp").forward(request, response);
	}

	private void Transfert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("connected_user");
		if (utlisateurDao.transfert(request, user.getNumero())) {
			// update user's solde
			user.setSolde(user.getSolde() - Integer.parseInt(request.getParameter("montant")));
			// Update session
			session.setAttribute("connected_user", user);
			request.setAttribute("trf_ok", "Virement effectué avec succès");
		} else {
			request.setAttribute("bad_mt", "Le montant saisi est incorrect : " + request.getParameter("montant"));
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/vw_moncompte.jsp").forward(request, response);
	}

	private void Disconnect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// delete session
		HttpSession session = request.getSession();
		session.removeAttribute("connected_user");
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

	}

	private void Authenticate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur user = utlisateurDao.findUserByLoginPwd(request);
		List<Utilisateur> users = utlisateurDao.findAllUsers();
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("connected_user", user);
			session.setAttribute("listeUsers", users);
			this.getServletContext().getRequestDispatcher("/WEB-INF/vw_moncompte.jsp").forward(request, response);
		} else {

		}

	}

}
