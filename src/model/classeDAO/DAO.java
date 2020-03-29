package model.classeDAO;


import java.sql.Connection;
import java.util.ArrayList;


public abstract class DAO<T> {
//	protected static Connection connect = OracleConnection.getInstance();
	protected static Connection connect;
	public abstract T insert(T obj);
	public abstract T modify(T obj);
	public abstract void delete(T obj);
	public abstract ArrayList<T> selectAll();

	
}