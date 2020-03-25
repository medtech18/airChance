package classes;

public class Langue {
	
	private int numLangue ;
	private String nom ;
	
	public Langue() {}
	
	public Langue (int numLangue , String nom) {
		this.numLangue = numLangue ;
		this.nom = nom ;
	}

	public int getNumLangue() {
		return numLangue;
	}

	public void setNumLangue(int numLangue) {
		this.numLangue = numLangue;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public void ToString() {
		System.out.println("numéro de Langue : "+numLangue+" Nom de la langue : "+nom);
	}
	
	
	
	

}
