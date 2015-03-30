package ca.etsmtl.gti525.demo.frameworks;

public class Personne {
 
    private int id;
    private String prenom;
    private String nomDeFamille;
    private String compagnie;
     
    public Personne() {}
 
    public Personne(String name, String surname) {
        this.prenom = name;
        this.nomDeFamille = surname;
    }
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNomDeFamille() {
        return nomDeFamille;
    }
    public void setNomDeFamille(String nomDeFamille) {
        this.nomDeFamille = nomDeFamille;
    }
    public String getCompagnie() {
        return compagnie;
    }
    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }
    @Override
    public String toString() {
        return "Personne [id=" + id + ", prenom=" + prenom + ", nomDeFamille=" + nomDeFamille + ", compagnie=" + compagnie + "]";
    }
}