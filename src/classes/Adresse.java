package classes;

public class Adresse {
	
	private int numAdresse;
	private int numAllee;
	private String nomRue;
	private String codePostal;
	private Ville nomVille;
	private Pays nomPays;
	
	
	public Adresse() {}
	
	public Adresse(int numAdresse, int numAllee, String nomRue, String codePostal, Ville nomVille, Pays nomPays) {
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

	public Ville getNomVille() {
		return nomVille;
	}

	public void setNomVille(Ville nomVille) {
		this.nomVille = nomVille;
	}

	public Pays getNomPays() {
		return nomPays;
	}

	public void setNomPays(Pays nomPays) {
		this.nomPays = nomPays;
	}
	
	
	public void ToString() {
		System.out.println(" Adresse : "+" Numéro de l'adresse : "+ numAdresse +" Code Postal: "+codePostal+" numéro de la rue : "+ numAllee +" Nom de la ville : "+ 
		   nomVille.getNomVille() + " Pays : "+ nomPays.getNomPays());
		}
	
	
	
	
	
	
	

}
