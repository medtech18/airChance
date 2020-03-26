package classeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Avion;
import classes.Langue;
import classes.LangueHotesse;
import classes.Model;
import classes.Personnel;
import classes.Vol;
import classes.PersonnelVol;

public class DaoPersonnelVol extends DAO<PersonnelVol> {
	
	 public PersonnelVol insert(PersonnelVol obj) {
			
			int numPersonnel = obj.getNumPersonnel().getNumPersonnel();
			int numVol = obj.getNumVol().getNumVol();
			
			ResultSet result;
			
			try {
				
				result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT numVol FROM Personnel NATURAL JOIN VOL ");

				if (result.first()) {
					
					int lastId = result.getInt("numVol");
					PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Client (numVol, numPersonnel) VALUES(?, ?)");
					
					prepare.setInt(1, lastId);
					prepare.setInt(2, numPersonnel);

					prepare.executeUpdate();
					obj = this.selectById(numVol);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		    
			return obj;
			
		 }
	
	 
	 public PersonnelVol modify(PersonnelVol obj) {
			
		 	int numPersonnel = obj.getNumPersonnel().getNumPersonnel();
			int numVol = obj.getNumVol().getNumVol();
			
			try{
				
				this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
	                	"UPDATE Avion SET numVol = '" + numVol + "',"+
	                	" WHERE numPersonnel = '" + numPersonnel + "'"
	                 );

				obj = this.selectById(obj.getNumVol());
		    } catch (SQLException e) {
		    	
		            e.printStackTrace();
		    }
		    
			return obj;
		}
	
	 
	 

		public void delete(PersonnelVol obj) {
			// On peut pas supprimer le numero d'un vol lié au personnel 
		}
		
		
		public PersonnelVol selectById(int numV) {
			
			PersonnelVol pr = new PersonnelVol();
			
			DaoPersonnel pDAO = new DaoPersonnel();
			DaoVol vDAO = new DaoVol();
			
			vol v = null;
			Personnel p = null;
			
			try {
				
				ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM PersonnelVol WHERE numVol = " + numV);
				
				if(result.first())
					
					p = pDAO.selectById(result.getInt("numPersonnel"));
					v = vDAO.selectById(result.getInt("numVol"));
					
					pr = new PersonnelVol(p,v);
					
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return pr;
		}
		
		
		public ArrayList<PersonnelVol> selectAll() {
			
			ArrayList<PersonnelVol> a = new ArrayList<PersonnelVol>();
			
			DaoPersonnel pDAO = new DaoPersonnel();
			DaoVol vDAO = new DaoVol();
			
			vol v = null;
			Personnel p = null;
			
			
			try {
				
				ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM PersonnelVol");
				
				while(result.next()) {
					p = pDAO.selectById(result.getInt("numPersonnel"));
					v = vDAO.selectById(result.getInt("numVol"));
					a.add(new PersonnelVol(p,v));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
			return a;
		}
	
	
	
	

}
