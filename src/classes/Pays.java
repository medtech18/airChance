package classes;

public class Pays {


	private String nomPays;

	public Pays() {
	}

	public Pays(String nomPays) {
		this.nomPays = nomPays;
	}

	public String getNomPays() {
		return nomPays;
	}

	public void setNomPays(String nomPays) {
		this.nomPays = nomPays;
	}
	
	public void ToString() {
		System.out.println("Nom pays: "+nomPays);
	}



}
