package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import core.Page;
import dao.DAOPage;
import dao.jpa.DAOPageJPA;

/**
 * Servlet implementation class UnePage
 */
@WebServlet("/UnePage")
public class UnePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère proprement le code de la page que l'on veut charger
		String 	sCodePage = request.getParameter("codePage");
		int		codePage;

		try {
			codePage = Integer.parseInt(sCodePage);
		} catch (NumberFormatException e) {
			String erreur = "Erreur dans la conversion du code de la page";
			renvoieErreur(response, erreur);
			return;
		}
		
		// On charge proprement la page
		DAOPage dao = DAOPageJPA.getInstance();
		Page	page = dao.get(codePage);
		
		if (page==null) {
			String erreur = "Aucune page pour ce code "+codePage;
			renvoieErreur(response, erreur);
			return;
		}
		
		// On stocke la page dans la session
		request.getSession().setAttribute("PAGE", page);
		
		
		// On passe la main au suivant
		String	suivant = request.getParameter("suivantUnePage");
		if (suivant.trim()==null) {
			String erreur = "Le suivant est vide...";
			renvoieErreur(response, erreur);
			return;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(suivant);
		requestDispatcher.forward(request, response);
	}

	private void renvoieErreur(HttpServletResponse response, String erreur)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<body>"+erreur+"</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
