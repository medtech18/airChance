package classes;

public class Aeroport {
	
	private int  numAeroport;
	private String 	nomAeroport;
	private Ville nomVille;
	private Pays nomPays;
	
	
	public Aeroport(int  numAeroport, String nomAeroport, Ville nomVille, Pays nomPays) {
		super();
		this.numAeroport = numAeroport;
		this.nomAeroport = nomAeroport;
		this.setNomVille(nomVille);
		this.setNomPays(nomPays);
	}
	
	public Aeroport() {}

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
		System.out.println("Aeroport nom : "+ nomAeroport + " - Numéro d'aeroport : " + numAeroport + " - Nom de la ville : "+ nomVille.getNomVille()+" - Pays : "+ nomPays.getNomPays()) ;
	}
	
	
}
