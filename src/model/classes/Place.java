package model.classes;

public class Place {
	
	private int numPlace; 
	private String classe;
	private String position;
	private Avion numAvion;
	
	public Place() {}
	
	public Place(int numPlace, String classe, String position, Avion numAvion) {
		
		this.numPlace = numPlace ;
		this.classe = classe ;
		this.position = position ;
		this.numAvion = numAvion ;
		
	}

	public int getNumPlace() {
		return numPlace;
	}

	public void setNumPlace(int numPlace) {
		this.numPlace = numPlace;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Avion getNumAvion() {
		return numAvion;
	}

	public void setNumAvion(Avion numAvion) {
		this.numAvion = numAvion;
	}
	
	public void ToString() {
		System.out.println("numéro de la place: " +numPlace +" Classe: "+classe+" position: "+ position +
		"Le numéro d'avion : "+ numAvion.getNumAvion());
	}
	
	

}
