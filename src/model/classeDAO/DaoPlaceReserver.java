package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Place;
import model.classes.PlaceReserver;
import model.classes.Reservation;
import model.classes.Vol;



public class DaoPlaceReserver extends DAO<PlaceReserver> {
	
	
	public PlaceReserver insert(PlaceReserver obj) {
		
		try {
			
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO Place (numPlace, numVol, numReservation) VALUES(?, ?, ?)");
			
			prepare.setInt(1, obj.getNumPlace().getNumPlace());
			prepare.setInt(2, obj.getNumVol().getNumVol());
			prepare.setInt(3, obj.getNumReservation().getNumReservation());

	
			prepare.executeUpdate();
			obj = this.selectById(obj.getNumPlace().getNumPlace(),obj.getNumVol().getNumVol());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
	}

	
	public PlaceReserver modify(PlaceReserver obj) {
	
	
		try{	
		
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
            	
				"UPDATE PlaceReserver SET place = '" + obj.getNumPlace().getNumPlace() + "',"+
            	", " + " numReservation = " + obj.getNumReservation().getNumReservation() +
            	" WHERE numVol = " +  obj.getNumVol().getNumVol()
           );

		} catch (SQLException e) {
			
            e.printStackTrace();
		}
    
		return obj ;
	}

	

	public void delete(PlaceReserver obj) {
	
		try {
	
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM PlaceReserver WHERE numPlace = '" +  obj.getNumPlace().getNumPlace() + "' AND numReservation = " + obj.getNumReservation().getNumReservation());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public PlaceReserver selectById(int numPlace , int numVol) {
		
		PlaceReserver a = new PlaceReserver();
		
			try {
			
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT * FROM PlaceReserver WHERE numPlace = '" + numPlace + "' AND numVol = " + numVol);
			
			if(result.first())
				
				a = new  PlaceReserver(DaoReservation.selectById(result.getInt("numReservation")), DaoPlace.selectById(result.getInt("numPlace")),  DaoVol.selectbyID(result.getInt("numVol")));
			    
		
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		return a;
	}
	
	
	public ArrayList<PlaceReserver> selectAll() {
		
		ArrayList<PlaceReserver> p = new ArrayList<PlaceReserver>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM PlaceReserver");
			
			while(result.next())
				
				p.add(new PlaceReserver(DaoReservation.selectById(result.getInt("numReservation")),DaoPlace.selectById(result.getInt("numPlace")) , DaoVol.selectbyID(result.getInt("numVol"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	
public ArrayList<PlaceReserver> selectbyreservation(Reservation reserv,Vol vol) {
		
		ArrayList<PlaceReserver> p = new ArrayList<PlaceReserver>();
		
//		try {
//			
//			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
//	                            "SELECT * FROM PlaceReserver where num_reservation="+reserv.getNumReservation()+"and num_vol="+vol.getNumVol());
//			
//			while(result.next())
//				
//				p.add(new PlaceReserver(DaoReservation.selectById(result.getInt("numReservation")),DaoPlace.selectById(result.getInt("numPlace")) , vol));
//		
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
		
		p.add(new PlaceReserver(reserv,new Place(1,"eco","hublot",new DaoAvion().selectById(5)) , vol));
		p.add(new PlaceReserver(reserv,new Place(2,"affaire","couloir",new DaoAvion().selectById(5)) , vol));
		return p;
	}




	
	

}
