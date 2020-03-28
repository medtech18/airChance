package model.classeDAO;


import java.sql.Connection;
import java.util.ArrayList;

import model.connection.OracleConnection;


public abstract class DAO<T> {
	protected static Connection connect = OracleConnection.getInstance();

	public abstract T insert(T obj);
	public abstract T modify(T obj);
	public abstract void delete(T obj);
	public abstract ArrayList<T> selectAll();

	
}