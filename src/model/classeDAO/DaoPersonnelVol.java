package model.classeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Personnel;
import model.classes.PersonnelVol;
import model.classes.Vol;


public class DaoPersonnelVol extends DAO<PersonnelVol> {
	
	 public PersonnelVol insert(PersonnelVol obj) {
		
			
			try {

					PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Client (numVol, numPersonnel) VALUES(?, ?)");
					
					prepare.setInt(1, obj.getNumVol().getNumVol());
					prepare.setInt(2, obj.getNumPersonnel().getNumPersonnel());
				

					prepare.executeUpdate();
					
					obj = this.selectById(obj.getNumVol().getNumVol());
					
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return obj;
			
		 }
	
	 
	 public PersonnelVol modify(PersonnelVol obj) {
			
		
			try{
				
				this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
	                	"UPDATE Avion SET numVol = '" + obj.getNumVol().getNumVol() + "',"+
	                	" WHERE numPersonnel = '" + obj.getNumPersonnel().getNumPersonnel() + "'"
	                 );

				obj = this.selectById(obj.getNumVol().getNumVol());
				
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
			
			Vol v = null;
			Personnel p = null;
			
			try {
				
				ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM PersonnelVol WHERE numVol = " + numV);
				
				if(result.first())
					
					p = pDAO.selectById(result.getInt("numPersonnel"));
					v = DaoVol.selectbyID(result.getInt("numVol"));
					
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
			
			Vol v = null;
			Personnel p = null;
			
			
			try {
				
				ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM PersonnelVol");
				
				while(result.next()) {
					p = pDAO.selectById(result.getInt("numPersonnel"));
					v = vDAO.selectbyID(result.getInt("numVol"));
					a.add(new PersonnelVol(p,v));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
			return a;
		}
	
	
	
	

}
