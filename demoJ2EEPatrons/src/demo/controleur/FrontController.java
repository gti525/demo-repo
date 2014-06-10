package demo.controleur;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Petit servlet démontrant une façon de faire un front controller
 *
 * @author Eric Boivin
 * @version 01/28/2010
 */

 public class FrontController extends HttpServlet  {
    
	/**
	 * HttpServlet implémente l'interface serializable, puisqu'il est possible de sérialiser, soit, de garder en persistance,
	 * l'état d'un servlet, par exemple pour conserver toutes les sessions à l'arrêt, et tout reprendre au démarrage. Afin de
	 * faire le lien entre une classe sérialisée et son implémentation, on met le champ serialVersionUID. Ce n'est vraiment
	 * pas nécessaire, mais sans ça, le compilateur va faire un avertissement. Alors bon, il est toujours mieux de le fixer.
	 */
	private static final long serialVersionUID = -5033259462658001604L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Redirige afin de centraliser les requêtes
		traiter(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Redirige afin de centraliser les requêtes
		traiter(request, response);
	}   
	
	/**
	 * Cette méthode reçoit toutes les requêtes du servlet, et fais les redirections nécessaires
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void traiter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Redirige au contrôleur de messages. Dans un Front Controller normal, il pourrait y avoir plusieurs servlets!
		ControleurMessage controleurMessage = new ControleurMessage();
		String page = controleurMessage.executerTraitement(request, response);
		redirige(request, response, page);
	}
	
	/**
	 * Faits la redirection vers les pages demandées par le sous contrôlleur
	 * @param request
	 * @param response
	 * @param page Le chemin et le nom de la page à afficher
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void redirige(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		//Redirige vers la page, fournie par le sous-contrôleur
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+page);
		dispatcher.forward(request, response);

	}   	
}