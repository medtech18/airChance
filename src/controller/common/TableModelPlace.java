package controller.common;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.classes.Place;
import model.classes.PlaceReserver;
import model.classes.Vol;

public class TableModelPlace extends  AbstractTableModel
{
	private final String[] entetes = { "num place", "classe", "position" };
	private ArrayList<Place> places;
		
	public TableModelPlace(ArrayList<Place> places) {
		super();
		this.places = places;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return places.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			// num place
			return places.get(rowIndex).getNumPlace();
	
		case 1:
			// classe
			return places.get(rowIndex).getClasse();
		case 2:
			// position
			return places.get(rowIndex).getPosition();
		default:
			throw new IllegalArgumentException();
	}
	}
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	
	public Place getValue(int rowIndex) {
		return places.get(rowIndex);	
	}

}
