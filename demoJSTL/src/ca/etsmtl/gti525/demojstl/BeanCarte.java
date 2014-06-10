package ca.etsmtl.gti525.demojstl;

import java.util.Comparator;

public class BeanCarte implements Comparable<BeanCarte>{
	private String nom;
	private String image;
	private String faction;
	private int numero;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFaction() {
		return faction;
	}
	public void setFaction(String faction) {
		this.faction = faction;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero=numero;
	}
	
	public int compareTo(BeanCarte autre) {
		// TODO Auto-generated method stub
		return numero - autre.numero;
	}
	
	public static Comparator<BeanCarte> COMPARER_PAR_NOM = new Comparator<BeanCarte>() {
        public int compare(BeanCarte un, BeanCarte deux) {
            return un.nom.compareTo(deux.nom);
        }
    };

    public static Comparator<BeanCarte> COMPARER_PAR_FACTION = new Comparator<BeanCarte>() {
    	public int compare(BeanCarte un, BeanCarte deux) {
            return un.faction.compareTo(deux.faction);
        }
    };
    

}
