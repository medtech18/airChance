package model.classes;

import java.sql.Date;

public class Vol {

	private int  numVol;
	private Date dateVol;
	
	private AeroPort aeroportArrive ;
	private AeroPort aeroportDepart ;
	
	private Float duree;
	private Float distanceVol;
	private int nbrMinPlaceEco ;
	private int nbrMinPlacePremiere ;
	private int nbrMinPlaceAffaire ;
	private boolean terminaison ;

	private Avion numAvion ;
	
	public Vol() {}
	
	public Vol(int numVol, Date dateVol, AeroPort aeroportArrive, AeroPort aeroportDepart, 
			Float duree, Float distanceVol, boolean terminaison , Avion numAvion,int nbrMinPlaceEco ,int nbrMinPlacePremiere , int nbrMinPlaceAffaire ) {
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
	

	public AeroPort getAeroportDepart() {
		return aeroportDepart;
	}
	
	public void setAeroportDepart(AeroPort aeroportDepart) {
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
	
	public Float getDuree() {
		return duree;
	}
	
	public void setDuree(Float duree) {
		this.duree = duree;
	}
	
	public Float getDistanceVol() {
		return distanceVol;
	}
	
	public void setDistanceVol(Float distanceVol) {
		this.distanceVol = distanceVol;
	}
	
	public boolean isVoltermine(){
		return this.terminaison;
	}

	public void setTerminaison(boolean terminaison){
		this.terminaison = terminaison;
	}
	
	public AeroPort getAeroportArrive() {
		return aeroportArrive;
	}
	
	public void setAeroportArrive(AeroPort aeroportArrive) {
		this.aeroportArrive = aeroportArrive;
	}
	
	@Override
	public String toString() {
		return "vol: " + numVol + " date du vol: " + dateVol + " Aeroport d'arrivée: " + aeroportArrive
				+ " aeroport départ: " + aeroportDepart+  " durée: " + duree
				+ " distance du vol: " + distanceVol + " numéro d'avion: " + numAvion;
	}
	


}
