package model.classeDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Aeroport;

public class DaoAeroport extends DAO<Aeroport> {
	

	@Override
	public Aeroport insert(Aeroport obj) {
		
	
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
	public Aeroport modify(Aeroport obj) {


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
	public void delete(Aeroport object) {
		try{
			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Aeroport WHERE num_aeroport = " + object.getNumAeroport());
	    
		} catch (SQLException e) {
			
	            e.printStackTrace();
	    }
	}
	

	public static Aeroport selectbyID(int numAeroport) {
		Aeroport a = new Aeroport();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Aeroport WHERE num_aeroport  = '" + numAeroport + "'");
			
			
			if(result.first())
				
				a = new Aeroport(numAeroport,result.getString("nom"),result.getString("ville"), result.getString("pays"));
				
		
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return a;
	}

	

	@Override
	public ArrayList<Aeroport> selectAll() {
		ArrayList<Aeroport> aeroPorts = new ArrayList<Aeroport>();
		
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Aeroport");
			
			while(result.next())
				
				aeroPorts.add(new Aeroport(result.getInt("num_aeroport"),result.getString("nom"),result.getString("ville"),
					result.getString("pays")));
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		

//		aeroPorts.add(new Aeroport(100,"MOHAMED 5 AEROPORT","CASA","MAROC"));
//		aeroPorts.add(new Aeroport(101,"MOHAMED 6 AEROPORT","FES","MAROC"));
//		aeroPorts.add(new Aeroport(101,"MOHAMED 7 AEROPORT","AGADIR","MAROC"));
//		aeroPorts.add(new Aeroport(103,"MOHAMED 8 AEROPORT","RABAT","MAROC"));
//		aeroPorts.add(new Aeroport(103,"MOHAMED 9 AEROPORT","TANGER","MAROC"));
//		aeroPorts.add(new Aeroport(103,"MOHAMED 9 AEROPORT","TANGER","MAROC"));

		return aeroPorts;
	}

} 
	  
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	
	

