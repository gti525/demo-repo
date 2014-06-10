package demo.controleur;

import java.io.IOException;

import demo.modele.BeanMessage;
import demo.modele.DelegateMessage;
import demo.modele.MessageException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe sous-contr�leur qui ex�cute les op�rations en rapport � l'affichage et la lecture des messages
 *
 * @author Eric Boivin
 * @version 01/29/2010
 */

public class ControleurMessage {
	
	private static final long serialVersionUID = 1391688820894808468L;

	public String executerTraitement(HttpServletRequest request, HttpServletResponse response){
		//Par d�faut, on consid�re que l'utilisateur veut afficher les messages
		String pageResultante = "afficheMessage.jsp";
		//Op�ration appel�e lorsque l'utilisateur tente d'ajouter un message
		if (request.getRequestURL().indexOf("nouveauMessage") > 0){
			//Enregistre le message uniquement si il a �t� bel et bien rempli
			if (request.getParameter("message") != null && !request.getParameter("message").equals("")){
				//Popule le JavaBean du message
				BeanMessage message = new BeanMessage();
				message.setMessage((String)request.getParameter("message"));
				message.setNom((String)request.getParameter("nom"));
				message.setStyle((String)request.getParameter("style"));
				message.setDate();
				
				//Enregistre le message
				try {
					DelegateMessage.ajouterMessage(message);
				} catch (MessageException e) {
					request.setAttribute("messageErreur", "Il y a eu un probl�me. D�sol�");
				}
			}
		}else if (request.getRequestURL().indexOf("creerMessage") > 0){
			pageResultante = "creerMessage.jsp";
		}
		
		//Place la liste des messages en attribut
		try {
			request.setAttribute("listeMessages", DelegateMessage.obtenirMessages());
		} catch (MessageException e) {
			request.setAttribute("messageErreur", "Erreur avec la lecture des messages. D�sol�");
		}
		return pageResultante;
	}
}
