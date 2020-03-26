package model.classeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Client;

public class DaoClient extends DAO<Client> {
	
	public Client insert(Client obj) {
		
		int numClient = obj.getNumClient();
		String nom = obj.getNom();
		String prenom = obj.getPrenom();
		String numPasseport = obj.getNumPasseport();
		int pointsFidelite = obj.getPointsFidelite();
		int numAdresse = obj.getNumAdresse().getNumAdresse();
		
		ResultSet result;
		
		try {
			
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT numClient FROM Client");

			if (result.first()) {
				
				int lastId = result.getInt("numClient");
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Client (numClient, nom, prenom, numPasseport, pointsFidelite, numAdresse) VALUES(?, ?, ?, ?, ?, ?)");
				
				prepare.setInt(1, lastId);
				prepare.setString(2, nom);
				prepare.setString(3, prenom);
				prepare.setString(4, numPasseport);
				prepare.setInt(5, pointsFidelite);
				prepare.setInt(6, numAdresse);

				prepare.executeUpdate();
				obj = this.selectById(numClient);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
		
	}
	
	public Client modify(Client obj) {
		
		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
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
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Client WHERE numClient = " + obj.getNumClient()
                 );

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	
	public Client selectById(int numClient) {
		
		Client c = new Client();
		
		try {
			
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Client WHERE numClient = " + numClient);
			
			if(result.first())
				
				c = new Client(numClient,result.getString("nom"),result.getString("prenom"),result.getString("numPasseport"),result.getInt("pointsFidelite"),new DaoAdresse().selectbyID(result.getInt("numAdresse")));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return c;
	}
	
	public ArrayList<Client> selectAll() {
		ArrayList<Client> c = new ArrayList<Client>();
		
		try {
			
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Client");
			
			while(result.next())
				
				c.add(new Client(result.getInt("numClient"),result.getString("nom"),result.getString("prenom"),result.getString("numPasseport"),result.getInt("pointsFidelite"),
		               new DaoAdresse().selectbyID(result.getInt("numAdresse"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return c;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
