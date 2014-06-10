package demo.modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe DAO qui fait les opérations d'enregistrement et de lecture
 *
 * @author Eric Boivin
 * @version 01/31/2010
 */

public class MessageDAOlocal {

	// Fichier où sont enregistrés les messages
	private static String fichier = "c:\\fichierMessages.txt";
	//Délimitateur pour séparer les parties du message
	private static final String DELIMITATEUR = "|";
	
	/**
	 * Enregistre les données dans un fichier texte
	 * @param message Le message à ajouter
	 * @throws MessageException 
	 */
	public static void ajouterMessage(BeanMessage message) throws MessageException {
		
		try { 
				//Insère le message dans le fichier en y mettant un séparateur entre chaque paramètre
				BufferedWriter output = new BufferedWriter(new FileWriter(fichier, true)); 
				StringBuffer sbMessage = new StringBuffer();
				sbMessage.append(message.getNom());
				sbMessage.append(DELIMITATEUR);
				sbMessage.append(message.getDate());
				sbMessage.append(DELIMITATEUR);
				sbMessage.append(message.getMessage());
				sbMessage.append(DELIMITATEUR);
				sbMessage.append(message.getStyle());
				//Ajoute un retour de chariot.
				sbMessage.append(System.getProperty( "line.separator" ));
				System.out.println("Ecrit dans le fichier:"+sbMessage.toString());
				
				output.write(sbMessage.toString()); 
				output.close(); 
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Erreur dans l'insertion!");
				throw new MessageException();
			} 
	}
	
	/**
	 * Version locale du DAO de message. Enregistre les données dans un fichier texte
	 * @throws MessageException 
	 */
	public static ArrayList<BeanMessage> obtenirMessages() throws MessageException {
		
		ArrayList<BeanMessage> liste = new ArrayList<BeanMessage>();
		
		try { 
			BufferedReader input = new BufferedReader(new FileReader(fichier)); 
			String ligne; 
			while ((ligne = input.readLine()) != null) { 
				liste.add(convertirLigne(ligne)); 
			} 
			input.close(); 
		} catch (IOException e) { 
			System.out.println("Erreur dans l'affichage!");
			throw new MessageException();
		} 
		
		return liste;
	}

	/**
	 * Méthode utilitaire qui popule un BeanMessage à partir d'une ligne d'un fichier de texte
	 * @param ligne La ligne obtenue du fichier texte
	 */
	private static BeanMessage convertirLigne(String ligne) {
		
		String [] valeurs = null;
		//On sépare la ligne à l'aide du symbole de délimitation
		valeurs = ligne.split("\\|");
		System.out.println(valeurs[0]+"-"+valeurs[2]+"-"+valeurs[3]+valeurs[1]);
		SimpleDateFormat sdf = new SimpleDateFormat(BeanMessage.FORMAT_DATE);
		Date d = new Date();
		try {
			d = sdf.parse(valeurs[1]);
		} catch (ParseException e) {
			//En cas d'erreur, on ignore
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(d);
		
		BeanMessage message = new BeanMessage(valeurs[0],cal.getTime(),valeurs[2],valeurs[3]);
		return message;
	}

}
