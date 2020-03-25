package classes;

public class LangueHotesse {
	
	private Personnel numPersonnel ;
	private Langue  numLangue ;
	
	public LangueHotesse() {}
	
	public LangueHotesse(Personnel numPersonnel  ,Langue  numLangue ) {
		this.numLangue = numLangue ;
		this.numPersonnel = numPersonnel ;
		
	}

	public Personnel getNumPersonnel() {
		return numPersonnel;
	}

	public void setNumPersonnel(Personnel numPersonnel) {
		this.numPersonnel = numPersonnel;
	}

	public Langue getNumLangue() {
		return numLangue;
	}

	public void setNumLangue(Langue numLangue) {
		this.numLangue = numLangue;
	}
	
	
	
	public void ToString() {
		System.out.println("numéro de Langue : "+ numLangue.getNumLangue()+" Numero du personnel  : "+numPersonnel.getNumPersonnel());
	}
	
	

}
