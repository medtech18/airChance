package controller.common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.classes.Vol;


public class VolTableModel extends AbstractTableModel{

	private final String[] entetes = { "num vol", "date depart", "aeroport depart", "aeroport arrivee", "duree", "distance", "num avion" };
	private ArrayList<Vol>vols;
	
	
	
	public VolTableModel(ArrayList<Vol> vols) {
		super();
		this.vols = vols;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return vols.size();
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				// num vol
				return vols.get(rowIndex).getNumVol();
		
			case 1:
				// date depart
				return vols.get(rowIndex).getDateVol();
			case 2:
				// aeroport depart
				return vols.get(rowIndex).getAeroportDepart();
			case 3:
				// aeroport arrivee
				return vols.get(rowIndex).getAeroportArrive();
			case 4:
				// duree
				return vols.get(rowIndex).getDuree();
			case 5:
				// distance
				return vols.get(rowIndex).getDistanceVol();
			case 6:
				// num avion 
				return vols.get(rowIndex).getNumAvion();
			default:
				throw new IllegalArgumentException();
		}
	
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	
	public Vol getValue(int rowIndex) {
		return vols.get(rowIndex);	
	}

}
