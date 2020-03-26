package model.classes;

public class PrixVol {
	
	
	private String datePrix ;
	private String classe ;
	private double prix; 
	private Vol numVol ;
	
	public PrixVol() {}
	
	public PrixVol(String datePrix , String classe , double prix ,  Vol numVol) {
		this.classe = classe; 
		this.datePrix = datePrix ;
		this.numVol = numVol ;
		this.prix = prix ;
	}

	public String getDatePrix() {
		return datePrix;
	}

	public void setDatePrix(String datePrix) {
		this.datePrix = datePrix;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Vol getNumVol() {
		return numVol;
	}

	public void setNumVol(Vol numVol) {
		this.numVol = numVol;
	}
	
	public void ToString() { 
		System.out.println(" La date de prix : " + datePrix +  "Type de classe  : "+ classe + "Le prix du vol : " +
	    prix + "le numéro de vol : " + numVol);
	}
	
	

}
