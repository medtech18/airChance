package model.classes;

public class Model {
	
	private String nomModele ;
	private int numModele;
	private int nbPiloteMin;
	private int rayonAction ;
	
	public Model() {}
	
	public Model (String nomModele,int numModele, int nbPiloteMin, int rayonAction) {
		
		this.numModele = numModele;
		this.nbPiloteMin = nbPiloteMin;
		this.rayonAction = rayonAction;
		this.nomModele= nomModele ;
		
	}
	
	public String getNomModele() {
		return nomModele ;
	}
	
	public void setNomModele(String nomModele) {
		this.nomModele = nomModele ;
	}

	public int getNumModele() {
		return numModele;
	}

	public void setNumModele(int numModele) {
		this.numModele = numModele;
	}

	public int getNbPiloteMin() {
		return nbPiloteMin;
	}

	public void setNbPiloteMin(int nbPiloteMin) {
		this.nbPiloteMin = nbPiloteMin;
	}

	public int getRayonAction() {
		return rayonAction;
	}

	public void setRayonAction(int rayonAction) {
		this.rayonAction = rayonAction;
	}
	
	public void ToString() {
		System.out.println("Le nom du Modèle : "+nomModele+"numéro du modèle: "+numModele+" nombre de pilotes min: "+nbPiloteMin +
				" rayon d'action  "+ rayonAction);
	}
	

}
