package model.classes;

import java.sql.Date;

public class Vol {

	private int  numVol;
	private Date dateVol;
	
	private Aeroport aeroportArrive ;
	private Aeroport aeroportDepart ;
	
	private double duree;
	private double distanceVol;
	private int nbrMinPlaceEco ;
	private int nbrMinPlacePremiere ;
	private int nbrMinPlaceAffaire ;
	private boolean terminaison ;

	private Avion numAvion ;
	
	public Vol() {}
	
	public Vol(int numVol, Date dateVol, Aeroport aeroportArrive, Aeroport aeroportDepart, 
			double duree, double distanceVol, boolean terminaison , Avion numAvion,int nbrMinPlaceEco ,int nbrMinPlacePremiere , int nbrMinPlaceAffaire ) {
		super();
		this.numVol = numVol;
		this.dateVol = dateVol;
		this.aeroportArrive = aeroportArrive;
		this.aeroportDepart = aeroportDepart;
		this.duree = duree;
		this.distanceVol = distanceVol;
		this.terminaison = terminaison ;
		this.numAvion = numAvion;
		this.nbrMinPlaceAffaire = nbrMinPlaceAffaire ; 
		this.nbrMinPlacePremiere = nbrMinPlacePremiere ;
		this.nbrMinPlaceEco = nbrMinPlaceEco ;
	}
	
	public void setnbrMinPlaceAffaire() {
		this.nbrMinPlaceAffaire = nbrMinPlaceAffaire ;
	}
	
	public int getnbrMinPlaceAffaire() {
		return nbrMinPlaceAffaire ;
	}
	
	public void setnbrMinPlacePremiere() {
		this.nbrMinPlacePremiere = nbrMinPlacePremiere ;
	}
	
	public int getnbrMinPlacePremiere() {
		return nbrMinPlacePremiere ;
	}
	
	public void setnbrMinPlaceEco() {
		this.nbrMinPlaceEco = nbrMinPlaceEco ;
	}
	
	public int getnbrMinPlaceEco() {
		return nbrMinPlaceEco ;
	}
	

	public Aeroport getAeroportDepart() {
		return aeroportDepart;
	}
	
	public void setAeroportDepart(Aeroport aeroportDepart) {
		this.aeroportDepart = aeroportDepart;
	}
	
	public Avion getNumAvion() {
		return numAvion;
	}
	
	public void setNumAvion(Avion numAvion) {
		this.numAvion = numAvion;
	}
	
	public int getNumVol() {
		return numVol;
	}
	
	public void setNumVol(int numVol) {
		this.numVol = numVol;
	}
	
	public Date getDateVol() {
		return dateVol;
	}
	
	public void setDateVol(Date dateVol) {
		this.dateVol = dateVol;
	}
	
	public double getDuree() {
		return duree;
	}
	
	public void setDuree(double duree) {
		this.duree = duree;
	}
	
	public double getDistanceVol() {
		return distanceVol;
	}
	
	public void setDistanceVol(double distanceVol) {
		this.distanceVol = distanceVol;
	}
	
	public boolean isVoltermine(){
		return this.terminaison;
	}

	public void setTerminaison(boolean terminaison){
		this.terminaison = terminaison;
	}
	
	public Aeroport getAeroportArrive() {
		return aeroportArrive;
	}
	
	public void setAeroportArrive(Aeroport aeroportArrive) {
		this.aeroportArrive = aeroportArrive;
	}
	
	public String toString() {
		return "vol: " + numVol + " date du vol: " + dateVol + " Aeroport d'arrivée: " + aeroportArrive
				+ " aeroport départ: " + aeroportDepart+  " durée: " + duree
				+ " distance du vol: " + distanceVol + " numéro d'avion: " + numAvion;
	}
	


}
