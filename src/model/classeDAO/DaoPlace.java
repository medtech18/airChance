package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Avion;
import model.classes.Place;
import model.classes.Vol;

public class DaoPlace extends DAO<Place> 
{

	
	public Place insert(Place obj) {
		
		try {
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			
			
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO Place (num_place, classe, position, num_avion) VALUES(?, ?, ?, ?)");
			
			prepare.setInt(1, obj.getNumPlace());
			prepare.setString(2, obj.getClasse());
			
			prepare.setString(3, obj.getPosition());
			prepare.setInt(6, obj.getNumAvion().getNumAvion());
	
			prepare.executeUpdate();
			obj = selectById(obj.getNumPlace(),obj.getNumAvion());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	    
		return obj;
	}
	
	
	public Place modify(Place obj) {
		
		//on peux pas modifier les avions
//		try{	
//			
//			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
//			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
//                	
//					"UPDATE Place SET classe = '" + obj.getClasse() + "',"+
//                	" position = '" + obj.getPosition() + "'"+
//                	"where num_avion = " + obj.getNumAvion().getNumAvion() +
//                	" and num_place = " + obj.getNumPlace()
//                 );
//
//	    } catch (SQLException e) {
//	            e.printStackTrace();
//	    }
//	    
		return obj ;
		}
	
	
	
	
	
	
	public void delete(Place obj) {
		//on peux pas modifier les avions
		
//		try {
//	
//			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
//					"DELETE FROM Place WHERE num_place = '" + obj.getNumPlace() + "' AND num_avion = " + obj.getNumAvion().getNumAvion());
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}
	
	
	public static Place selectById(int numPlace,Avion avion ) {
		
		Place p = new Place();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Place WHERE num_place = " + numPlace+" and num_avion ="+avion.getNumAvion());
			
			if(result.first())
				
				p = new Place(numPlace,result.getString("classe"),result.getString("position"),avion);
			    
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	
	
	public ArrayList<Place> selectAll() {
		
		ArrayList<Place> p = new ArrayList<Place>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Place");
			
			while(result.next())
				
				p.add(new Place(result.getInt("num_place"),result.getString("classe"),result.getString("position"),DaoAvion.selectById(result.getInt("num_avion"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
public ArrayList<Place> placedispo(Vol vol) {
		
		ArrayList<Place> p = new ArrayList<Place>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM TABLE(allplace_dispo_rows("+vol.getNumVol()+"))");
			
			while(result.next())
				
				p.add(new Place(result.getInt("num_place"),result.getString("classe"),result.getString("position"),DaoAvion.selectById(result.getInt("num_avion"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	

}
