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
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO place_reserver (num_reservation, num_place, num_avion,num_vol) VALUES(?, ?, ?,?)");
			
			prepare.setInt(1, obj.getNumReservation().getNumReservation());
			prepare.setInt(2, obj.getNumPlace().getNumPlace());
			prepare.setInt(3, obj.getNumVol().getNumAvion().getNumAvion());
			prepare.setInt(4, obj.getNumVol().getNumVol());

	
			prepare.executeUpdate();
			obj = this.selectById(obj.getNumPlace().getNumPlace(),obj.getNumVol());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
	}

	
	public PlaceReserver modify(PlaceReserver obj) {
	
	//pas necessaire a implementer du coup c'est pas la peine de corriger les erreurs
		
//		try{	
//		
//			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
//			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
//            	
//				"UPDATE PlaceReserver SET place = '" + obj.getNumPlace().getNumPlace() + "',"+
//            	", " + " numReservation = " + obj.getNumReservation().getNumReservation() +
//            	" WHERE numVol = " +  obj.getNumVol().getNumVol()
//           );
//
//		} catch (SQLException e) {
//			
//            e.printStackTrace();
//		}
    
		return obj ;
	}

	

	public void delete(PlaceReserver obj) {
	
		try {
	
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM place_reserver WHERE num_place = '" +  obj.getNumPlace().getNumPlace() + "' AND num_vol = " + obj.getNumVol().getNumVol());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public PlaceReserver selectById(int numPlace , Vol vol) {
		
		PlaceReserver a = new PlaceReserver();
		
			try {
			
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT * FROM place_reserver WHERE num_place = '" + numPlace + "' AND num_vol = " + vol.getNumVol());
			
			if(result.first())
				
				a = new  PlaceReserver(DaoReservation.selectById(result.getInt("num_reservation")), DaoPlace.selectById(result.getInt("num_place"),vol.getNumAvion()), vol);
			    
		
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		return a;
	}
	
	
	public ArrayList<PlaceReserver> selectAll() {
		
		ArrayList<PlaceReserver> p = new ArrayList<PlaceReserver>();
		Vol v = new Vol();
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM place_reserver");
			
			while(result.next())
				v=DaoVol.selectbyID(result.getInt("num_vol"));
				p.add(new PlaceReserver(DaoReservation.selectById(result.getInt("num_reservation")),DaoPlace.selectById(result.getInt("num_place"),v.getNumAvion()) ,v));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	
public ArrayList<PlaceReserver> selectbyreservation(Reservation reserv,Vol vol) {
		
		ArrayList<PlaceReserver> p = new ArrayList<PlaceReserver>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM place_reserver where num_reservation="+reserv.getNumReservation()+"and num_vol="+vol.getNumVol());
			
			while(result.next())
				
				p.add(new PlaceReserver(reserv,DaoPlace.selectById(result.getInt("num_place"),vol.getNumAvion()) , vol));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}

public ArrayList<PlaceReserver> placedispo(Vol vol) {
	
	ArrayList<PlaceReserver> p = new ArrayList<PlaceReserver>();
	Vol v = new Vol();
	try {
		
		ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
                            "SELECT * FROM place_reserver");
		
		while(result.next())
		{
			v=DaoVol.selectbyID(result.getInt("num_vol"));
			p.add(new PlaceReserver(DaoReservation.selectById(result.getInt("num_reservation")),DaoPlace.selectById(result.getInt("num_place"),v.getNumAvion()) ,v));
		}
			
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	return p;
}

public float getprix(ArrayList<PlaceReserver> places) {
	float prix = 0;
	try {
		
//		(datereservation in date,
//                id_place in integer,
//                id_avion in integer,
//                id_vol in integer )
		ResultSet result;
		PreparedStatement prepare = connect.prepareStatement(
                "SELECT prix_place(?,?,?,?) FROM dual");
		
		for(PlaceReserver p:places)
		{
			prepare.setDate(1, p.getNumReservation().getDateReservation());
			prepare.setInt(2, p.getNumPlace().getNumPlace());
			prepare.setInt(3, p.getNumVol().getNumAvion().getNumAvion());
			prepare.setInt(4, p.getNumVol().getNumVol());
			result=prepare.executeQuery();
			if(result.next())
				prix+=result.getFloat(1);
		}
			
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return prix;
}


	
	

}
