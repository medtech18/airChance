package model.classeDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.AeroPort;

public class DaoAeroport extends DAO<AeroPort> {
	
	

	@Override
	public AeroPort insert(AeroPort obj) {
		
	
		try {
			
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO Aeroport (num_aeroport, nom, nomVille, nomPays) VALUES(?, ?, ?, ?)");
			
			prepare.setInt(1, obj.getNumAeroport());
			prepare.setString(2, obj.getNomAeroport());
			prepare.setString(3, obj.getNomVille());
			prepare.setString(4, obj.getNomPays());

			prepare.executeUpdate();
			
			obj = selectbyID(obj.getNumAeroport());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	@Override
	public AeroPort modify(AeroPort obj) {


		try{
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect	.createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Aeroport SET ville = '" + obj.getNomVille() + "',"+
                    " pays = '" + obj.getNomPays() + "',"+
                    " nom = '" + obj.getNomAeroport() + "'"+
                	" WHERE num_aeroport = '" + obj.getNumAeroport() + "'"
                 );

			obj = selectbyID(obj.getNumAeroport());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}

	@Override
	public void delete(AeroPort object) {
		try{
			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Aeroport WHERE num_aeroport = " + object.getNumAeroport());
	    
		} catch (SQLException e) {
			
	            e.printStackTrace();
	    }
	}
	

	public static AeroPort selectbyID(int numAeroport) {
		AeroPort a = new AeroPort();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Aeroport WHERE num_aeroport  = '" + numAeroport + "'");
			
			
			if(result.first())
				
				a = new AeroPort(numAeroport,result.getString("nom"),result.getString("ville"), result.getString("pays"));
				
		
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return a;
	}

	

	@Override
	public ArrayList<AeroPort> selectAll() {
		ArrayList<AeroPort> aeroPorts = new ArrayList<AeroPort>();
		
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Aeroport");
			
			while(result.next())
				
				aeroPorts.add(new AeroPort(result.getInt("num_aeroport"),result.getString("nom"),result.getString("ville"),
					result.getString("pays")));
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return aeroPorts;
	}

} 
	  
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	
	

