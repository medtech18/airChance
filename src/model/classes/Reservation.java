package model.classes;

public class Reservation {
	
	
	private int numReservation ;
	private String dateReservation ;
	private Client numClient ;
	
	public Reservation() {}
	
	public Reservation(int numReservation,String dateReservation,Client numClient) {
		this.dateReservation = dateReservation ;
		this.numClient = numClient ;
		this.numReservation = numReservation; 
		
	}

	public int getNumReservation() {
		return numReservation;
	}

	public void setNumReservation(int numReservation) {
		this.numReservation = numReservation;
	}

	public String getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(String dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Client getNumClient() {
		return numClient;
	}

	public void setNumClient(Client numClient) {
		this.numClient = numClient;
	}
	

	public void ToString() {
		System.out.println("Numéro de la Réservation :  "+ numReservation + "La date de la Réservation : " + dateReservation + " - Nom de la ville : " + " - Le numéro du client : "+ numClient.getNumClient()) ;
	}
	
	
}
