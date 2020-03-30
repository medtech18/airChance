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
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO Client (numClient, nom, prenom, numPasseport, pointsFidelite, numAdresse) VALUES(?, ?, ?, ?, ?, ?)");
				
				prepare.setInt(1, obj.getNumClient());
				prepare.setString(2, obj.getNom());
				prepare.setString(3, obj.getPrenom());
				prepare.setString(4, obj.getNumPasseport());
				prepare.setInt(5, obj.getPointsFidelite());
				prepare.setInt(6, obj.getNumAdresse().getNumAdresse());

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
                	" numPasseport = '" + obj.getNumPasseport() + "',"+
                	" pointsFidelite = " + obj.getPointsFidelite() +
                	" WHERE numClient = " + obj.getNumClient()
                 );

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	public void delete(Client obj) {
		try {
			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Client WHERE numClient = " + obj.getNumClient()
                 );

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	
	public static Client selectById(int numClient) {
		
		Client c = new Client();
		
//		try {
//			
//			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
//	                            "SELECT * FROM Client WHERE numClient = " + numClient);
//			
//			if(result.first())
//
//				
//				c = new Client(numClient,result.getString("nom"),result.getString("prenom"),result.getString("numPasseport"),result.getInt("pointsFidelite"), DaoAdresse.selectbyID(result.getInt("numAdresse")));
//		
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
		c=new Client(numClient,"nom","prenom","Passeport",0,DaoAdresse.selectbyID(0));
		return c;
	}
	
	public ArrayList<Client> selectAll() {
		ArrayList<Client> c = new ArrayList<Client>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Client");
			
			while(result.next())
				
				c.add(new Client(result.getInt("numClient"),result.getString("nom"),result.getString("prenom"),result.getString("numPasseport"),result.getInt("pointsFidelite"),
		                DaoAdresse.selectbyID(result.getInt("numAdresse"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return c;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
