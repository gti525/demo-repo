package ca.etsmtl.gti525.demojstl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/liste")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GestionnaireCartes gestionnaire;
	
	@Override
	public void init() throws ServletException {
		gestionnaire = new GestionnaireCartes();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("cartes", gestionnaire.obtenirListeCartes(request.getParameter("tri")));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/liste.jsp");
		dispatcher.forward(request, response);
		
	}
}
