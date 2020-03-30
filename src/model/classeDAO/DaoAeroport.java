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
			
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO Aeroport (numAeroport, nomAeroport, nomVille, nomPays) VALUES(?, ?, ?, ?)");
			
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
                	"UPDATE Aeroport SET nomVille = '" + obj.getNomVille() + "',"+
                    " nomPays = '" + obj.getNomPays() + "',"+
                    " nomAeroport = '" + obj.getNomAeroport() + "'"+
                	" WHERE numAeroport = '" + obj.getNumAeroport() + "'"
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
			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Aeroport WHERE numAeroport = " + object.getNumAeroport());
	    
		} catch (SQLException e) {
			
	            e.printStackTrace();
	    }
	}
	

	public static Aeroport selectbyID(int numAeroport) {
		Aeroport a = new Aeroport();
		
//		try {
//			
//			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
//	                            "SELECT * FROM Aeroport WHERE numAeroport  = '" + numAeroport + "'");
//			
//			
//			if(result.first())
//				
//				a = new Aeroport(numAeroport,result.getString("nomAeroport"),result.getString("nomVille"), result.getString("nomPays"));
//				
//		
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//		
		a=new Aeroport(numAeroport,"MOHAMED 5 AEROPORT","CASA","MAROC");
		return a;
	}

	

	@Override
	public ArrayList<Aeroport> selectAll() {
		ArrayList<Aeroport> a = new ArrayList<Aeroport>();
		
//		try {
//			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
//	                            "SELECT * FROM Aeroport");
//			
//			while(result.next())
//				
//				a.add(new Aeroport(result.getInt("numAeroport"),result.getString("nomAeroport"),result.getString("nomVille"),
//					result.getString("nomPays")));
//		
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
		
//		return a;
		
		ArrayList<Aeroport> aeroPorts = new ArrayList<Aeroport>();
		aeroPorts.add(new Aeroport(100,"MOHAMED 5 AEROPORT","CASA","MAROC"));
		aeroPorts.add(new Aeroport(101,"MOHAMED 6 AEROPORT","FES","MAROC"));
		aeroPorts.add(new Aeroport(101,"MOHAMED 7 AEROPORT","AGADIR","MAROC"));
		aeroPorts.add(new Aeroport(103,"MOHAMED 8 AEROPORT","RABAT","MAROC"));
		aeroPorts.add(new Aeroport(103,"MOHAMED 9 AEROPORT","TANGER","MAROC"));
		aeroPorts.add(new Aeroport(103,"MOHAMED 9 AEROPORT","TANGER","MAROC"));

		return aeroPorts;
	}

} 
	  
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	
	

