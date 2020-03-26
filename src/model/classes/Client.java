package model.classes;

public class Client {
	
	private int numClient;
	private String nom, prenom;
	private String numPasseport;
	private int pointsFidelite;
	private Adresse numAdresse;


	public Client() {}
	
	public Client(int numClient, String nom, String prenom, String numPasseport, int pointsFidelite,
			Adresse numAdresse) {
		this.numClient = numClient;
		this.nom = nom;
		this.prenom = prenom;
		this.numPasseport = numPasseport;
		this.pointsFidelite = pointsFidelite;
		this.setNumAdresse(numAdresse);
	}
	
	
	public int getNumClient() {
		return numClient;
	}

	public void setNumClient(int numClient) {
		this.numClient = numClient;
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

	
	public String getNumPasseport() {
		return numPasseport;
	}

	
	
	public void setNumPasseport(String numPasseport) {
		this.numPasseport = numPasseport;
	}

	
	
	public int getPointsFidelite() {
		return pointsFidelite;
	}
	
	

	public void setPointsFidelite(int pointsFidelite) {
		this.pointsFidelite = pointsFidelite;
	}

	
	
	public Adresse getNumAdresse() {
		return numAdresse;
	}
	

	public void setNumAdresse(Adresse numAdresse) {
		this.numAdresse = numAdresse;
	}
	
	
	public void ToString() {
		System.out.println(numClient + " - Nom client : "+nom+ " Prénom: "+ prenom+ " Numéro de passeport: "+ numPasseport+" nombre de points fidélité: "+ 
				pointsFidelite+" Adresse: "+numAdresse.getNumAdresse());
	}
	

	
	

}
