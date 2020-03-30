package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Adresse;
import model.classes.Personnel;


public class DaoPersonnel extends DAO<Personnel> {
	
	 public Personnel insert(Personnel obj) {
		
		
		try {
			
				connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO Client (numPersonnel, nom, prenom, dateDisponibilie, totalHeureVol, numAdresse,genre) VALUES(?, ?, ?, ?, ?, ?,?)");
				
				prepare.setInt(1, obj.getNumPersonnel());
				prepare.setString(2, obj.getNom());
				prepare.setString(3, obj.getPrenom());
				prepare.setString(4, obj.getDateDisponibilie());
				prepare.setInt(5, obj.getTotalHeureVol());
				prepare.setInt(6, obj.getNumAdresse().getNumAdresse());
				prepare.setString(7, obj.getGenre());

				prepare.executeUpdate();
				obj = selectById(obj.getNumPersonnel());
			
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
		
	 }
	 
		public Personnel modify(Personnel obj) {
			
			try{
				
				connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
	                	"UPDATE Personnel SET nom = '" + obj.getNom() + "',"+
	                	" prenom = '" + obj.getPrenom() + "',"+
	                	" dateDisponibilie = '" + obj.getDateDisponibilie() + "',"+
	                	" totalHeureVol = " + obj.getTotalHeureVol() +
	                	" WHERE numPersonnel = " + obj.getNumPersonnel()
	                 );

		    } catch (SQLException e) {
		    	
		            e.printStackTrace();
		    }
		    
			return obj;
		}
		
		
		
		public void delete(Personnel obj) {
			
			try{
				
				
				connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
	                	"DELETE FROM Personnel WHERE numPersonnel = " + obj.getNumPersonnel()
	                 );
				
		    } catch (SQLException e) {
		    	
		            e.printStackTrace();
		    }
		}
		
		
		
		
		public static Personnel selectById(int numP) {
			
			Personnel p = new Personnel();
		
			
			try {
				
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM Personnel WHERE numPersonnel = " + numP);
				
				if(result.first())
				
					p = new Personnel(numP,result.getString("nom"),result.getString("prenom"),result.getInt("totalHeureVol"),result.getString("dateDisponibilie"),DaoAdresse.selectbyID(result.getInt("numAdresse")),result.getString("genre"));
					
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return p;
		}
	
		
		public ArrayList<Personnel> selectAll() {
			ArrayList<Personnel> p = new ArrayList<Personnel>();
			
//			try {
//				
//				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
//		                            "SELECT * FROM Personnel");
//				
//				while(result.next())
//					
//					p.add(new Personnel(result.getInt("numPersonnel"),result.getString("nom"),result.getString("prenom"),result.getInt("totalHeureVol"),result.getString("dateDisponibilite"),
//			                DaoAdresse.selectbyID(result.getInt("numAdresse")),result.getString("genre")));
//			
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//			
//			return p;
			
			for(int i= 0 ; i < 100 ; i++)
			p.add(new Personnel(100+i,"ADAM" , "EVE", DaoAvion.generateRandomInt(10),"12/02/2021",
					new Adresse(),"MALE") 
				);
			
			return p;
		}


	

}
