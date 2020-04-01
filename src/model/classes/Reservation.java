package model.classes;

import java.sql.Date;

public class Reservation {
	
	private int numReservation ;
	private Date dateReservation ;
	private Client numClient ;
	private float prix_reservation;
	
	public Reservation() {}
	
	public Reservation(int numReservation,Date dateReservation,Client numClient,float prix_reservation) {
		this.dateReservation = dateReservation ;
		this.numClient = numClient ;
		this.numReservation = numReservation; 
		this.prix_reservation=prix_reservation;
		
	}

	public float getPrix_reservation() {
		return prix_reservation;
	}

	public void setPrix_reservation(float prix_reservation) {
		this.prix_reservation = prix_reservation;
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
		return "num: " + numReservation + " date: " + dateReservation+" prix: "+prix_reservation;
	}
	
	
}
