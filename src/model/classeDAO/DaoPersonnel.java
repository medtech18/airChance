package model.classeDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.AeroPort;
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
				
		public ArrayList<Personnel> getPersonnelWith(Date dateDisponibilite , AeroPort aeroPortDep, String personnel) {
			ArrayList<Personnel> prs = new ArrayList<Personnel>();

			
			try {
				PreparedStatement pstmt = this .connect.prepareStatement("SELECT * FROM "+personnel +"where DATEDISPONIBILITE >= ? and position_pilot_aeroport(num_personnel) = ?");
			   

				pstmt.setDate(1, dateDisponibilite);
				pstmt.setInt(2,aeroPortDep.getNumAeroport());
				pstmt.execute();
				ResultSet result = pstmt.getResultSet();
				


				while(result.next())
				{

					
					
						prs.add( new Personnel(
								result.getInt("NUM_PERSONNEL"),
								result.getString("nom"),
								result.getString("prenom"),
								result.getInt("TOTAL_HEURES_VOL"),
								result.getString("DATEDISPONIBILITE"),
						        DaoAdresse.selectbyID(result.getInt("NUM_ADRESSE")),
						        result.getString("genre"))
								);
					
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return prs;
			
		}

		public ArrayList<Personnel> selectAll() {
			ArrayList<Personnel> p = new ArrayList<Personnel>();
			
			try {
				
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM Personnel ");
				
				while(result.next())
					
					p.add(new Personnel(result.getInt("NUM_PERSONNEL"),result.getString("nom"),result.getString("prenom"),result.getInt("TOTAL_HEURES_VOL"),result.getString("DATEDISPONIBILITE"),
			                DaoAdresse.selectbyID(result.getInt("NUM_ADRESSE")),result.getString("genre")));
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return p;
			
		}


	

}
