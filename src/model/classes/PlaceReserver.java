package model.classes;

public class PlaceReserver {

	
	
	private Reservation numReservation ;
	private Place numPlace ;
	private Vol numVol ;
	
	public PlaceReserver() {}
	
	public PlaceReserver(Reservation numReservation,Place numPlace , Vol numVol) {

		this.numPlace = numPlace ;
		this.numReservation = numReservation ;
		this.numVol = numVol ;
		
	}

	public Reservation getNumReservation() {
		return numReservation;
	}

	public void setNumReservation(Reservation numReservation) {
		this.numReservation = numReservation;
	}

	public Place getNumPlace() {
		return numPlace;
	}

	public void setNumPlace(Place numPlace) {
		this.numPlace = numPlace;
	}

	public Vol getNumVol() {
		return numVol;
	}

	public void setNumVol(Vol numVol) {
		this.numVol = numVol;
	}
	
	
	public void ToString() {
		System.out.println(" - Place resrvée  : "+numReservation.getNumReservation() +" - Numéro de Place : "+numPlace.getNumPlace() +" - le numéro du Vol  : "+ numVol.getNumVol());
	}
	

}
