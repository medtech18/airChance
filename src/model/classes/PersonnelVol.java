package model.classes;

public class PersonnelVol {

	
	
	private Personnel numPersonnel ;
	private Vol numVol ;
	
	public PersonnelVol() {}
	
	public PersonnelVol(Personnel numPersonnel ,Vol numVol) {
		this.numPersonnel = numPersonnel;
		this.numVol = numVol ;
	}

	public Personnel getNumPersonnel() {
		return numPersonnel;
	}

	public void setNumPersonnel(Personnel numPersonnel) {
		this.numPersonnel = numPersonnel;
	}

	public Vol getNumVol() {
		return numVol;
	}

	public void setNumVol(Vol numVol) {
		this.numVol = numVol;
	}
	
	@Override
	public String toString() {
		return "numéro de vol : "+numVol.getNumVol()+" Numero du personnel  : "+numPersonnel.getNumPersonnel();
	}
	

}
