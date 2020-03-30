package model.classes;

import java.sql.Date;

public class Reservation {
	
	
	private int numReservation ;
	private Date dateReservation ;
	private Client numClient ;
	
	public Reservation() {}
	
	public Reservation(int numReservation,Date dateReservation,Client numClient) {
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

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Client getNumClient() {
		return numClient;
	}

	public void setNumClient(Client numClient) {
		this.numClient = numClient;
	}
	

	public String toString() {
		return "num: " + numReservation + " date: " + dateReservation;
	}
	
	
}
