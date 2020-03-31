package model.classeDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Client;



public class DaoClient extends DAO<Client> {
	
	public Client insert(Client obj) {
		
		
		try {
			
				connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO Client (num_client, nom, prenom, num_passport,num_adresse , point) VALUES(?, ?, ?, ?, ?, ?)");
				
				prepare.setInt(1, obj.getNumClient());
				prepare.setString(2, obj.getNom());
				prepare.setString(3, obj.getPrenom());
				prepare.setString(4, obj.getNumPasseport());
				prepare.setInt(5, obj.getNumAdresse().getNumAdresse());
				prepare.setInt(6, obj.getPointsFidelite());
				

				prepare.executeUpdate();
				obj = selectById(obj.getNumClient());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
		
	}
	
	public Client modify(Client obj) {
		
		try{
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Client SET nom = '" + obj.getNom() + "',"+
                	" prenom = '" + obj.getPrenom() + "',"+
                	" num_passport = '" + obj.getNumPasseport() + "',"+
                	" point = " + obj.getPointsFidelite() +
                	" WHERE num_client = " + obj.getNumClient()
                 );

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	public void delete(Client obj) {
		try {
			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Client WHERE num_client = " + obj.getNumClient()
                 );

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	
	public static Client selectById(int numClient) {
		
		Client c = null;
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Client WHERE num_client = " + numClient);
			
			if(result.first())
				c = new Client(numClient,result.getString("nom"),result.getString("prenom"),result.getString("num_passport"),result.getInt("point"), DaoAdresse.selectbyID(result.getInt("num_adresse")));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c;
	}
	
	public ArrayList<Client> selectAll() {
		ArrayList<Client> c = new ArrayList<Client>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Client");
			
			while(result.next())
				
				c.add(new Client(result.getInt("num_client"),result.getString("nom"),result.getString("prenom"),result.getString("num_passport"),result.getInt("point"),
		                DaoAdresse.selectbyID(result.getInt("num_adresse"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return c;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
