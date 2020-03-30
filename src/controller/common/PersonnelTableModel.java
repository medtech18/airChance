package controller.common;

import java.util.ArrayList;

import model.classes.Personnel;

public class PersonnelTableModel extends GenericTableModel<Personnel>{

	
	public PersonnelTableModel(ArrayList rowObjects) {
		super(rowObjects, new String[] {"NUM PERSONNEL ", "NOM" , "PRENOM" , "TOTAL HEURES DE VOL", "DATE DE DISPONIBLITE" ,"NUM ADRESSE","SEX" });
		// TODO Auto-generated constructor stub
	}
	
	public PersonnelTableModel()
	{
		super(new ArrayList<Personnel>(),null);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:

				// num vol
				return rowObjects.get(rowIndex).getNumPersonnel();
		
			case 1:
				// date depart
				return rowObjects.get(rowIndex).getNumPersonnel();
			case 2:
				// aeroport depart
				return rowObjects.get(rowIndex).getNom();
			case 3:
				// aeroport arrivee
				return rowObjects.get(rowIndex).getPrenom();
			case 4:
				// duree
				return rowObjects.get(rowIndex).getTotalHeureVol();
			case 5:
				// distance
				return rowObjects.get(rowIndex).getDateDisponibilie();
			case 6:
				// num avion 
				return 233232;
			case 7:
				// num avion 
				return rowObjects.get(rowIndex).getGenre();

			default:
				throw new IllegalArgumentException();
		}
	
	}
	
}
