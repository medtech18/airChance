package model.classes;

public class Qualification {
	
	private Model  numModele ;
	private Personnel numPersonnel ;
	private int nbHeures ;
	
	public Qualification() {}
	
	public Qualification(Model  numModele, Personnel numPersonnel, int nbHeures) {
		super();
		this.numModele = numModele;
		this.numModele = numModele;
		this.numPersonnel = numPersonnel;
	}

	public Model getNumModele() {
		return numModele;
	}

	public void setNumModele(Model numModele) {
		this.numModele = numModele;
	}

	public Personnel getNumPersonnel() {
		return numPersonnel;
	}

	public void setNumPersonnel(Personnel numPersonnel) {
		this.numPersonnel = numPersonnel;
	}

	public int getNbHeures() {
		return nbHeures;
	}

	public void setNbHeures(int nbHeures) {
		this.nbHeures = nbHeures;
	}
	
	
	public void ToString() {
		System.out.println("Qualification : "+"Numéro du modèle: "+ numModele.getNumModele() +" Nombre d'heures de vol: "+nbHeures+" le numéro du Pilote : "+ numPersonnel.getNumPersonnel());
	}
	
	

}
