package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Place;

public class DaoPlace extends DAO<Place> {
	
	public Place insert(Place obj) {
		
		try {
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO Place (numPlace, classe, position, numAvion) VALUES(?, ?, ?, ?)");
			
			prepare.setInt(1, obj.getNumPlace());
			prepare.setString(2, obj.getClasse());
			prepare.setString(3, obj.getPosition());
			prepare.setInt(6, obj.getNumAvion().getNumAvion());
	
			prepare.executeUpdate();
			obj = selectById(obj.getNumPlace());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
	}
	
	
	public Place modify(Place obj) {
		
		
		try{	
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	
					"UPDATE Place SET classe = '" + obj.getClasse() + "',"+
                	" position = '" + obj.getPosition() + "',"+
                	", " + " numAvion = " + obj.getNumAvion().getNumAvion() +
                	" WHERE numPlace = " + obj.getNumPlace()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj ;
		}
	
	
	
	
	
	
	public void delete(Place obj) {
	
		try {
	
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Place WHERE numPlace = '" + obj.getNumPlace() + "' AND numAvion = " + obj.getNumAvion().getNumAvion());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public static Place selectById(int numPlace ) {
		
		Place p = new Place();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Place WHERE numPlace = '" + numPlace);
			
			if(result.first())
				
				p = new Place(numPlace,result.getString("nomClasse"),result.getString("nomPosition"),
			        DaoAvion.selectById(result.getInt("numAvion")));
			    
		
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
				
				p.add(new Place(result.getInt("numPlace"),result.getString("classe"),result.getString("position"),DaoAvion.selectById(result.getInt("numAvion"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	

}
