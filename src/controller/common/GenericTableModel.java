package controller.common;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public abstract class GenericTableModel<T> extends AbstractTableModel {

	protected String[] columnsHeaders;
	protected ArrayList<T> rowObjects;

	
	public GenericTableModel(ArrayList<T> rowObjects , final String[] columnsHeaders) {
		super();
		this.rowObjects = rowObjects;
		this.columnsHeaders = columnsHeaders;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowObjects.size();
	}
	
	@Override
	public int getColumnCount() {
		return columnsHeaders.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columnsHeaders[columnIndex];
	}
	
	public T getValue(int rowIndex) {
		return rowObjects.get(rowIndex);	
	}

	public String[] getColumnsHeaders() {
		return columnsHeaders;
	}

	public ArrayList<T> getRowObjects() {
		return rowObjects;
	}

	public void setColumnsHeaders(String[] columnsHeaders) {
		this.columnsHeaders = columnsHeaders;
	}

	public void setRowObjects(ArrayList<T> rowObjects) {
		this.rowObjects = rowObjects;
	}
}
