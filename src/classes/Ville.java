package classes;

public class Ville {
	
	
	private String nomVille;
	private Pays nomPays;

	public Ville(String nomVille, Pays nomPays) {
		this.nomVille = nomVille;
		this.setNomPays(nomPays);
	}
	
	
	public Ville() {}
	
	
	public String getNomVille() {
		return nomVille;
	}

	
	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	
	public Pays getNomPays() {
		return nomPays;
	}
	

	public void setNomPays(Pays nomPays) {
		this.nomPays = nomPays;
	}
	
	
	public void ToString() {
		System.out.println("Ville: Nom ville: "+ " nom Pays: "+nomPays.getNomPays());
	}
	
	

}
