package model.classes;

public class Avion {
	
	protected int numAvion;
	private int nbrMinPlaceEco ;
	private int nbrMinPlacePremiere ;
	private int nbrMinPlaceAffaire ;
	protected Model numModele;

	
	public Avion() {}
	

	public Avion(int numAvion, int nbrMinPlaceEco, int nbrMinPlacePremiere, int nbrMinPlaceAffaire, Model numModele) {
		super();
		this.numAvion = numAvion;
		this.nbrMinPlaceEco = nbrMinPlaceEco;
		this.nbrMinPlacePremiere = nbrMinPlacePremiere;
		this.nbrMinPlaceAffaire = nbrMinPlaceAffaire;
		this.numModele = numModele;
	}


	public int getNbrMinPlaceEco() {
		return nbrMinPlaceEco;
	}


	public int getNbrMinPlacePremiere() {
		return nbrMinPlacePremiere;
	}


	public int getNbrMinPlaceAffaire() {
		return nbrMinPlaceAffaire;
	}


	public void setNbrMinPlaceEco(int nbrMinPlaceEco) {
		this.nbrMinPlaceEco = nbrMinPlaceEco;
	}


	public void setNbrMinPlacePremiere(int nbrMinPlacePremiere) {
		this.nbrMinPlacePremiere = nbrMinPlacePremiere;
	}


	public void setNbrMinPlaceAffaire(int nbrMinPlaceAffaire) {
		this.nbrMinPlaceAffaire = nbrMinPlaceAffaire;
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
	
	public void ToString() { 
		System.out.println("Avion numero : " + numAvion +  " Numéro du modele : "+ numModele.getNumModele());
	}
	


}
