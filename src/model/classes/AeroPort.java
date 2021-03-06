package model.classes;

public class AeroPort {
	
	private int  numAeroport;
	private String 	nomAeroport;
	private String nomVille;
	private String nomPays;
	
	
	public AeroPort(int  numAeroport, String nomAeroport, String nomVille, String nomPays) {
		super();
		this.numAeroport = numAeroport;
		this.nomAeroport = nomAeroport;
		this.setNomVille(nomVille);
		this.setNomPays(nomPays);
	}
	
	public AeroPort() {}

	public int getNumAeroport() {
		return numAeroport;
	}

	public void setNumAeroport(int  numAeroport) {
		this.numAeroport = numAeroport;
	}

	public String getNomAeroport() {
		return nomAeroport;
	}

	public void setNomAeroport(String nomAeroport) {
		this.nomAeroport = nomAeroport;
	}

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public String getNomPays() {
		return nomPays;
	}

	public void setNomPays(String nomPays) {
		this.nomPays = nomPays;
	}
	
	public String toString() {
		return nomAeroport+", "+nomVille+", "+nomPays ;
	}
	
}
