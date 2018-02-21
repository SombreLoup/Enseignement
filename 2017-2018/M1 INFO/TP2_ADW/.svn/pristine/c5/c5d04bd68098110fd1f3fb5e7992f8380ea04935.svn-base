package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Article;
import dao.DAOArticle;
import dao.jpa.DAOArticleJPA;

/**
 * Servlet implementation class TousLesAuteurs
 */
@WebServlet("/TousLesArticles")
public class TousLesArticles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String	pageSuivante = request.getParameter("suivantTousLesArticles");
		System.out.println("suivant="+pageSuivante);
		
		DAOArticle daoArticle = DAOArticleJPA.getInstance();
		List<Article> liste = daoArticle.loadAll();
		HttpSession session = request.getSession();
		session.setAttribute("ARTICLES", liste);
		
		System.out.println(liste);
		
		response.sendRedirect(pageSuivante);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
