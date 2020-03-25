package classes;

public class Avion {
	
	protected int numAvion;
	protected Model numModele;
	
	public Avion() {}
	
	public Avion(int numAvion , Model numModele) {
		this.numAvion = numAvion;
		this.numModele = numModele;
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
