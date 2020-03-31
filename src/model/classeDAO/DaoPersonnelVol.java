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
				
					connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);

					PreparedStatement prepare = connect.prepareStatement("INSERT INTO Client (numVol, numPersonnel) VALUES(?, ?)");
					
					prepare.setInt(1, obj.getNumVol().getNumVol());
					prepare.setInt(2, obj.getNumPersonnel().getNumPersonnel());
				

					prepare.executeUpdate();
					
					obj = this.selectById(obj.getNumVol().getNumVol(),obj.getNumPersonnel().getNumPersonnel());
					
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return obj;
			
		 }
	
	 
	 public PersonnelVol modify(PersonnelVol obj) {
			
		
			try{
				
				connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
	                	"UPDATE Avion SET numVol = '" + obj.getNumVol().getNumVol() + "',"+
	                	" WHERE numPersonnel = '" + obj.getNumPersonnel().getNumPersonnel() + "'"
	                 );

				obj = this.selectById(obj.getNumVol().getNumVol() ,obj.getNumPersonnel().getNumPersonnel());
				
		    } catch (SQLException e) {
		    	
		            e.printStackTrace();
		    }
		    
			return obj;
		}
	
	 
	 

		public void delete(PersonnelVol obj) {
			// On peut pas supprimer le numero d'un vol lié au personnel 
		}
		
		
		public PersonnelVol selectById(int numVol , int numPersonnel) {
			
			PersonnelVol pr = new PersonnelVol();

			Vol v = null ;
			Personnel p= null;
			
			try {
				
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT * FROM PersonnelVol WHERE numVol = '" + numVol + "' AND numPersonnel = " + numPersonnel);
				
				if(result.first())
					
					p = DaoPersonnel.selectById(result.getInt("numPersonnel"));
					v = DaoVol.selectbyID(result.getInt("numVol"));
					
					pr = new PersonnelVol(p,v);
					
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return pr;
		}
		
		public ArrayList<PersonnelVol> selectAll() {
			
			ArrayList<PersonnelVol> a = new ArrayList<PersonnelVol>();
			
			Vol v = null ;
			Personnel p = null ;
			
			
			try {
				
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
		                            "SELECT * FROM PersonnelVol");
				
				while(result.next()) {
					
					p = DaoPersonnel.selectById(result.getInt("numPersonnel"));
					v = DaoVol.selectbyID(result.getInt("numVol"));
					
					a.add(new PersonnelVol(p,v));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
			return a;
		}
	
	
	
	

}
