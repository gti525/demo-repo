package demo.vue;

import java.util.Random;

/**
 * Classe View Helper qui affiche un message avec des couleurs aléatoires
 *
 * @author Eric Boivin
 * @version 01/28/2010
 */

public class MessageCouleurViewHelper {

	//Constantes privées pour les couleurs de l'arc-en-ciel. 
	
	private static final String COULEUR_ROUGE = "#ff0000";
	private static final String COULEUR_ORANGE = "#ff3300";
	private static final String COULEUR_JAUNE = "#ffff00";
	private static final String COULEUR_VERT = "#00ff00";
	private static final String COULEUR_BLEU = "#0000ff";
	private static final String COULEUR_INDIGO = "#6f00ff";
	private static final String COULEUR_VIOLET = "#8f00ff";
	 
	private static String[] couleurs = {COULEUR_ROUGE,
		COULEUR_ORANGE, COULEUR_JAUNE, COULEUR_VERT,
		COULEUR_BLEU, COULEUR_INDIGO, COULEUR_VIOLET
	};
	
	public static String genererMessageColore(String message){
		StringBuffer chaine = new StringBuffer();
		
		if (message.length() > 0 ){
			
			Random generateur = new Random();
			
			for (int i=0;i<message.length();i++){
				//Prend la couleur à utiliser au hasard (0-6)
				int indexCouleur = generateur.nextInt(7);
				
				/*On construit la balise HTML. Comme les guillemets sont des caractères spéciaux et
				 * qu'on ne veut pas fermer la String, on utilise \" pour ignorer le caractère */
				chaine.append("<font color=\"");
				chaine.append(couleurs[indexCouleur]);
				chaine.append("\">");
				chaine.append(message.charAt(i));
				chaine.append("</font>");
			}
		}
		return chaine.toString();
	}
}
