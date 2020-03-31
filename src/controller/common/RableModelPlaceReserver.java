package controller.common;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.classes.PlaceReserver;

public class RableModelPlaceReserver extends  AbstractTableModel{
	private final String[] entetes = { "num place", "classe", "position" };
	private ArrayList<PlaceReserver> PlacesReserver;
	
	
	public RableModelPlaceReserver(ArrayList<PlaceReserver> PlacesReserver) {
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
			default:
				throw new IllegalArgumentException();
		}
	
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	

}
