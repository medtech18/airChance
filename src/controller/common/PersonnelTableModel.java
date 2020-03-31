package controller.common;

import java.util.ArrayList;

import model.classes.Personnel;

public class PersonnelTableModel extends GenericTableModel<Personnel>{

	
	public PersonnelTableModel(ArrayList rowObjects) {
		super(rowObjects, new String[] {"NUM PERSONNEL ", "NOM" , "PRENOM" , "TOTAL HEURES DE VOL", "DATE DE DISPONIBLITE" ," ADRESSE DE PERSON","SEX" });
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

				// num Personnel
				return rowObjects.get(rowIndex).getNumPersonnel();
		
			case 1:
				// NOM 
				return rowObjects.get(rowIndex).getNom();
			case 2:
				// PRENOM
				return rowObjects.get(rowIndex).getPrenom();
			case 3:
				// TOTAL HEURES
				return rowObjects.get(rowIndex).getTotalHeureVol();
			case 4:
				// DATE DISPONIBILITE
				return rowObjects.get(rowIndex).getDateDisponibilie();
			case 5:
				// NUM ADRESSE
				return rowObjects.get(rowIndex).getNumAdresse();
			case 6:
				// GENRE 
				return rowObjects.get(rowIndex).getGenre();

			default:
				throw new IllegalArgumentException();
		}
	
	}
	
}
