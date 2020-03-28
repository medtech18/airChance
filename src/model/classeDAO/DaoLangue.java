package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Langue;



public class DaoLangue extends DAO<Langue>{
	
	public Langue insert(Langue obj) {
		
		
		try {
			
				connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO Avion (numLangue , nom) VALUES(?, ?)");
				prepare.setInt(1, obj.getNumLangue());
				prepare.setString(2, obj.getNom());

				prepare.executeUpdate();
				obj = selectById(obj.getNumLangue());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}
	

	public Langue modify(Langue obj) {
		

		try{
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Langue SET numLangue = '" + obj.getNumLangue() + "',"+
                	" WHERE nom = '" + obj.getNom() + "'"
                 );

			obj = selectById(obj.getNumLangue());
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	
	public void delete(Langue obj) {
		
		try{

			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Langue WHERE numLangue = " + obj.getNumLangue());
			
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	

	public static Langue selectById(int numL) {
		
		Langue l = new Langue();
	
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Langue WHERE numLangue = " + numL);
			
			if(result.first())
				
			
				l = new Langue(numL,result.getString("nom"));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return l;
	}
	
public ArrayList<Langue> selectAll() {
		
		ArrayList<Langue> l = new ArrayList<Langue>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Langue");
			
			while(result.next()) {
				
				l.add(new Langue(result.getInt("numLangue"),result.getString("nom")));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return l;
	}
	
	
	

}
