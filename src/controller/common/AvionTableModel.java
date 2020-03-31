package controller.common;

import java.util.ArrayList;

import model.classes.Avion;
import model.classes.Personnel;

public class AvionTableModel extends GenericTableModel<Avion>{

	public AvionTableModel(ArrayList rowObjects) {
		super(rowObjects, new String[] {"NUM AVION ", "nb place_eco" , "nb place premiere" , "Num Model", "nom_modele" ,"nb_min_pilote","rayon_action" });
		// TODO Auto-generated constructor stub
	}
	
	public AvionTableModel()
	{
		super(new ArrayList<Avion>(),null);
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				// num vol
				return rowObjects.get(rowIndex).getNumAvion();
		
			case 1:
				// date depart
				return rowObjects.get(rowIndex).getNbrPlaceEco();
			case 2:
				// aeroport depart
				return rowObjects.get(rowIndex).getNbrPlacePremiere();
			case 3:
				// aeroport arrivee
				return rowObjects.get(rowIndex).getNbrPlaceAffaire();
			case 4:
				// duree
				return rowObjects.get(rowIndex).getNumModele().getNumModele();
			case 5:
				// distance
				return rowObjects.get(rowIndex).getNumModele().getNomModele();
			case 6:
				// num avion 
				return rowObjects.get(rowIndex).getNumModele().getNbPiloteMin();
			case 7:
				// num avion 
				return rowObjects.get(rowIndex).getNumModele().getRayonAction();

			default:
				throw new IllegalArgumentException();
		}
	}
	
}
