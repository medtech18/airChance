package controller.common;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.classes.PlaceReserver;

public class TableModelPlaceReserver extends  AbstractTableModel{
	private final String[] entetes = { "num place", "classe", "position","vol","date depart","depart","arriver" };
	private ArrayList<PlaceReserver> PlacesReserver;
	
	
	public TableModelPlaceReserver(ArrayList<PlaceReserver> PlacesReserver) {
		super();
		this.PlacesReserver = PlacesReserver;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return PlacesReserver.size();
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}
//	 "num place", "classe", "position","vol","date depart","depart","arriver"
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				// num place
				return PlacesReserver.get(rowIndex).getNumPlace().getNumPlace();
		
			case 1:
				// classe
				return PlacesReserver.get(rowIndex).getNumPlace().getClasse();
			case 2:
				// position
				return PlacesReserver.get(rowIndex).getNumPlace().getPosition();
			case 3:
				// vol
				return PlacesReserver.get(rowIndex).getNumVol().getNumVol();
			case 4:
				// date depart
				return PlacesReserver.get(rowIndex).getNumVol().getDateVol();
			case 5:
				// depart
				return PlacesReserver.get(rowIndex).getNumVol().getAeroportDepart().getNomAeroport();
			case 6:
				// arriver
				return PlacesReserver.get(rowIndex).getNumVol().getAeroportArrive().getNomAeroport();
			default:
				throw new IllegalArgumentException();
		}
	
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	

}
