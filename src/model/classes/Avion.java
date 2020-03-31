package model.classes;

public class Avion {
	
	
	
	protected int numAvion;
	protected Model numModele;
	private int nbrPlaceEco  ;
	private int nbrPlacePremiere ;
	private int nbrPlaceAffaire ;
	
	public Avion() {}
	
	public Avion(int numAvion , Model numModele ,int nbrPlaceAffaire , int nbrPlacePremiere, int nbrPlaceEco) {
		this.numAvion = numAvion;
		this.numModele = numModele;
		this.nbrPlaceAffaire = nbrPlaceAffaire ;
		this.nbrPlaceEco = nbrPlaceEco ; 
		this.nbrPlacePremiere = nbrPlacePremiere ;
	}

	public int getNumAvion() {
		return numAvion;
	}

	public void setNumAvion(int numAvion) {
		this.numAvion = numAvion;
	}

	public Model getNumModele() {
		return numModele;
	}

	public void setNumModele(Model numModele) {
		this.numModele = numModele;
	}
	
	
	
	public int getNbrPlaceEco() {
		return nbrPlaceEco;
	}

	public void setNbrPlaceEco(int nbrPlaceEco) {
		this.nbrPlaceEco = nbrPlaceEco;
	}

	public int getNbrPlacePremiere() {
		return nbrPlacePremiere;
	}

	public void setNbrPlacePremiere(int nbrPlacePremiere) {
		this.nbrPlacePremiere = nbrPlacePremiere;
	}

	public int getNbrPlaceAffaire() {
		return nbrPlaceAffaire;
	}

	public void setNbrPlaceAffaire(int nbrPlaceAffaire) {
		this.nbrPlaceAffaire = nbrPlaceAffaire;
	}

	public String toString() { 
		return ""+numAvion ;
	}
	


}
