package model.classes;


public class Vol {

	private int  numVol;
	private String dateVol;
	
	private Aeroport aeroportArrive ;
	private Aeroport aeroportDepart ;
	
	private int duree;
	private int distanceVol;
	private int nbrMinPlaceEco ;
	private int nbrMinPlacePremiere ;
	private int nbrMinPlaceAffaire ;
	private boolean terminaison ;

	private Avion numAvion ;
	
	public Vol() {}
	
	public Vol(int numVol, String dateVol, Aeroport aeroportArrive, Aeroport aeroportDepart, 
			int duree, int distanceVol, boolean terminaison , Avion numAvion,int nbrMinPlaceEco ,int nbrMinPlacePremiere , int nbrMinPlaceAffaire ) {
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
	
	public String getDateVol() {
		return dateVol;
	}
	
	public void setDateVol(String dateVol) {
		this.dateVol = dateVol;
	}
	
	public int getDuree() {
		return duree;
	}
	
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	public int getDistanceVol() {
		return distanceVol;
	}
	
	public void setDistanceVol(int distanceVol) {
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
	
	public void ToString() {
		System.out.println("numéro vol: " + numVol + "date du vol: " + dateVol + " Aeroport d'arrivée: " + aeroportArrive.getNomAeroport()
				+ " aeroport départ: " + aeroportDepart.getNomAeroport() +  " durée: " + duree
				+ " distance du vol: " + distanceVol + " vol terminé ? " + terminaison + " numéro d'avion: " + numAvion.getNumAvion() + 
				"le nombre de minimum de place en Eco " + nbrMinPlaceEco + "le nombre minimum de place Affaire"+
				nbrMinPlaceAffaire + "le nombre minimum de place Première" + nbrMinPlacePremiere);
	}
	


}
