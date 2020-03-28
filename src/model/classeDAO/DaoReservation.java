package model.classeDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Reservation;




public class DaoReservation extends DAO<Reservation> {
	
	
	public Reservation insert(Reservation obj) {
		
		try {
			
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO Place (numReservation, dateReservation, numClient) VALUES(?, ?, ?)");
			
			prepare.setInt(1, obj.getNumReservation());
			prepare.setString(2, obj.getDateReservation());
			prepare.setInt(3, obj.getNumClient().getNumClient());

	
			prepare.executeUpdate();
			obj = selectById(obj.getNumReservation());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
	}
	
	
	
	
	public Reservation modify(Reservation obj) {
		
		
		try{	
		
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
            	
				"UPDATE Reservation SET numReservation = '" + obj.getNumReservation()+ "',"+
            	", " + " dateReservation = " + obj.getDateReservation() +
            	" WHERE numClient = " + obj.getNumClient().getNumClient()
           );

		} catch (SQLException e) {
			
            e.printStackTrace();
		}
    
		return obj ;
	}

	
	public void delete(Reservation obj) {
		
		try {
	
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Reservation WHERE numReservation = '" +  obj.getNumReservation());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public static Reservation selectById(int numReservation) {
		
		Reservation rv = new Reservation();
		
			try {
			
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Reservation WHERE numReservation = '" + numReservation);

				
			if(result.first())
				
				rv = new Reservation(numReservation , result.getNString("dateReservation") , DaoClient.selectById(result.getInt("numClient")));
			    
		
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		return rv;
	}
	
	

	public ArrayList<Reservation> selectAll() {
		
		ArrayList<Reservation> rv = new ArrayList<Reservation>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Reservation");
			
			while(result.next())
				
				rv.add(new Reservation(result.getInt("numReservation"),result.getString("dateReservation"),DaoClient.selectById(result.getInt("numClient"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rv;
	}
	
	
	

}
