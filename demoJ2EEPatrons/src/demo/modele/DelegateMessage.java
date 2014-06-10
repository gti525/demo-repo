package demo.modele;

import java.util.ArrayList;

/**
 * Classe Business Delegate qui délègue les opérations d'affichage et de soumission de messages
 *
 * @author Eric Boivin
 * @version 01/29/2010
 */

public class DelegateMessage {
	
	public static void ajouterMessage(BeanMessage message) throws MessageException{
		MessageDAOlocal.ajouterMessage(message);
	}
	
	public static ArrayList obtenirMessages() throws MessageException{
		return MessageDAOlocal.obtenirMessages();
	}
	
}
