package model.classeDAO;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Client;
import model.classes.Reservation;


public class DaoReservation extends DAO<Reservation> {
	
	
	public Reservation insert(Reservation obj) {
		
		try {
			
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO reservation (num_reservation, date_reservation, num_client,prix_reservation) VALUES(?, ?, ?,?)");
			
			prepare.setInt(1, obj.getNumReservation());
			prepare.setDate(2, obj.getDateReservation());
			prepare.setInt(3, obj.getNumClient().getNumClient());
			prepare.setFloat(4, obj.getPrix_reservation());
	
			prepare.executeUpdate();
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery("select seq_num_reservation.currval from dual");
			result.next();
			obj.setNumReservation(result.getInt(1));
			
			result.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
	}
	
	
	
	
	public Reservation modify(Reservation obj) {
		
		
		try{	
		
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
            	
				"UPDATE Reservation SET num_client = '" + obj.getNumClient().getNumClient()+ "',"+
            	"prix_reservation= '"+ obj.getPrix_reservation()+ "', date_reservation = " + obj.getDateReservation() +
            	" WHERE num_reservation = " + obj.getNumReservation()
           );

		} catch (SQLException e) {
			
            e.printStackTrace();
		}
    
		return obj ;
	}

	
	public void delete(Reservation obj) {
		
		try {
	
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Reservation WHERE num_reservation = " +  obj.getNumReservation());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public static Reservation selectById(int numReservation) {
		
		Reservation rv = new Reservation();
		
			try {
			
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Reservation WHERE num_reservation = " + numReservation);

				
			if(result.first())
				
				rv = new Reservation(numReservation , result.getDate("date_reservation"), DaoClient.selectById(result.getInt("num_client")),result.getFloat("prix_reservation"));
			    
			result.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		return rv;
	}
	
	public static ArrayList<Reservation> selectByclient(Client numclient) {
		
		ArrayList<Reservation> rv=new ArrayList<Reservation>();
		
			try {
			
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Reservation WHERE num_client = '" + numclient.getNumClient()+"'");

				
			while(result.next())
				
				rv.add(new Reservation(result.getInt("num_reservation") , result.getDate("date_reservation") , numclient,result.getFloat("prix_reservation")));
			    
			result.close();
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
				
				rv.add(new Reservation(result.getInt("num_reservation"),result.getDate("date_reservation"),DaoClient.selectById(result.getInt("num_client")),result.getFloat("prix_reservation")));
		
			
			result.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rv;
	}
	
	
	

}
