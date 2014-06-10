package demo.modele;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe Javabean qui contient un message
 *
 * @author Eric Boivin
 * @version 01/28/2010
 */

public class BeanMessage {

	private String nom;
	private Date date;
	private String message;
	private String style;
	
	//Format d'affichage des dates
	public static final String FORMAT_DATE = "yyyy-MM-dd HH:mm";

	
	/**
	 * Constructeur vide sans paramètres
	 */
	public BeanMessage(){
		//Rien
	}
	
	/**
	 * Constructeur avec tous les paramètres pour simplifier la création
	 */
	public BeanMessage(String nom, Date date, String message, String style) {
		this.nom = nom;
		this.date = date;
		this.message = message;
		this.style = style;
	}
	
	//Getters et setters
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Setter qui va automatiquement placer la date actuelle au lieu de la recevoir d'une autre couche
	 */
	public void setDate(){
		Calendar cal = Calendar.getInstance();
		this.date = cal.getTime();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
