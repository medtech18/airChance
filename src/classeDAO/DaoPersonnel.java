package classeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Personnel;




public class DaoPersonnel extends DAO<Personnel> {
	
	 public Personnel insert(Personnel obj) {
		
		int numPersonnel = obj.getNumPersonnel();
		String nom = obj.getNom();
		String prenom = obj.getPrenom();
		String dateDisponibilie = obj.getDateDisponibilie();
		int totalHeureVol = obj.getTotalHeureVol();
		int numAdresse = obj.getNumAdresse().getNumAdresse();
		String genre = obj.getGenre();
		
		ResultSet result;
		
		try {
			
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT numPersonnel FROM Personnel");

			if (result.first()) {
				
				int lastId = result.getInt("numPersonnel");
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Client (numPersonnel, nom, prenom, dateDisponibilie, totalHeureVol, numAdresse,genre) VALUES(?, ?, ?, ?, ?, ?,?)");
				
				prepare.setInt(1, lastId);
				prepare.setString(2, nom);
				prepare.setString(3, prenom);
				prepare.setString(4, dateDisponibilie);
				prepare.setInt(5, totalHeureVol);
				prepare.setInt(6, numAdresse);
				prepare.setString(7, genre);

				prepare.executeUpdate();
				obj = this.selectById(numPersonnel);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
		
	 }
	 
		public Personnel modify(Personnel obj) {
			
			try{
				
				this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
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
				
				
				this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
	                	"DELETE FROM Personnel WHERE numPersonnel = " + obj.getNumPersonnel()
	                 );
				
		    } catch (SQLException e) {
		    	
		            e.printStackTrace();
		    }
		}
		
		public Personnel selectById(int numP) {
			
			Personnel p = new Personnel();
		
			
			try {
				
				ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM Personnel WHERE numPersonnel = " + numP);
				
				if(result.first())
				
					p = new Personnel(numP,result.getString("nom"),result.getString("prenom"),result.getInt("totalHeureVol"),result.getString("dateDisponibilie"),new DaoAdresse().selectbyID(result.getInt("numAdresse")),result.getString("genre"));
					
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return p;
		}
		
		
		public ArrayList<Personnel> selectAll() {
			ArrayList<Personnel> p = new ArrayList<Personnel>();
			
			try {
				
				ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM Personnel");
				
				while(result.next())
					
					p.add(new Personnel(result.getInt("numPersonnel"),result.getString("nom"),result.getString("prenom"),result.getInt("totalHeureVol"),result.getString("dateDisponibilite"),
			               new DaoAdresse().selectbyID(result.getInt("numAdresse")),result.getString("genre")));
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return p;
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	

}
