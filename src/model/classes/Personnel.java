package model.classes;

public class Personnel {

	private int numPersonnel ;
	private String nom , prenom ;
	private int totalHeureVol ;
	private String dateDisponibilie ;
	private Adresse numAdresse ;
	private String genre ;
	
	
	public Personnel() {}
	
	public Personnel(int numPersonnel ,String nom , String prenom, int totalHeureVol,String dateDisponibilite,
			Adresse numAdresse,String genre) {
		
		this.dateDisponibilie = dateDisponibilie ;
		this.genre = genre ;
		this.nom = nom ;
		this.numAdresse = numAdresse ; 
		this.numPersonnel = numPersonnel ;
		this.prenom = prenom ;
		this.totalHeureVol = totalHeureVol ;
		
		
		
		
		
	}

	public int getNumPersonnel() {
		return numPersonnel;
	}

	public void setNumPersonnel(int numPersonnel) {
		this.numPersonnel = numPersonnel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getTotalHeureVol() {
		return totalHeureVol;
	}

	public void setTotalHeureVol(int totalHeureVol) {
		this.totalHeureVol = totalHeureVol;
	}

	public String getDateDisponibilie() {
		return dateDisponibilie;
	}

	public void setDateDisponibilie(String dateDisponibilie) {
		this.dateDisponibilie = dateDisponibilie;
	}

	public Adresse getNumAdresse() {
		return numAdresse;
	}

	public void setNumAdresse(Adresse numAdresse) {
		this.numAdresse = numAdresse;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	public void ToString() {
		System.out.println(numPersonnel + " - Nom Personnel : "+nom+ " Prénom: "+ prenom+ " Date de disponibilité : "+dateDisponibilie+" Nombre total des heures de vol : "+ 
				totalHeureVol +"Genre : " + genre +" Adresse: "+numAdresse.getNumAdresse());
	}
	

	
}
