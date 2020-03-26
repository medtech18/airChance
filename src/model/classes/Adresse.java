package model.classes;

public class Adresse {
	
	private int numAdresse;
	private int numAllee;
	private String nomRue;
	private String codePostal;
	private String nomVille;
	private String nomPays;
	
	
	public Adresse() {}
	
	public Adresse(int numAdresse, int numAllee, String nomRue, String codePostal, String nomVille, String nomPays) {
		this.numAdresse = numAdresse;
		this.numAllee = numAllee;
		this.nomRue = nomRue;
		this.codePostal = codePostal;
		this.setNomVille(nomVille);
		this.setNomPays(nomPays);
	}

	public int getNumAdresse() {
		return numAdresse;
	}

	public void setNumAdresse(int numAdresse) {
		this.numAdresse = numAdresse;
	}

	public int getNumAllee() {
		return numAllee;
	}

	public void setNumAllee(int numAllee) {
		this.numAllee = numAllee;
	}

	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
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
	
	
	public void ToString() {
		System.out.println(" Adresse : "+" Numéro de l'adresse : "+ numAdresse +" Code Postal: "+codePostal+" numéro de la rue : "+ numAllee +" Nom de la ville : "+ 
		   nomVille + " Pays : "+ nomPays);
		}
	
	
	
	
	
	
	

}
