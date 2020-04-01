package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.common.AlertMessages;
import model.classes.Personnel;
import model.classes.PersonnelVol;
import model.classes.Vol;

public class DaoPersonnelVol extends DAO<PersonnelVol> {
	
	 public PersonnelVol insert(PersonnelVol obj) {
		

		 
		 
			try {
				
					connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);

					PreparedStatement prepare = connect.prepareStatement("INSERT INTO PERSONNEL_VOL (NUM_VOL, NUM_PERSONNEL) VALUES(?, ?)");
					
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
	                	"UPDATE PERSONNEL_VOL SET NUM_VOL = '" + obj.getNumVol().getNumVol() + "',"+
	                	" WHERE NUM_PERSONNEL = '" + obj.getNumPersonnel().getNumPersonnel() + "'"
	                 );

				obj = this.selectById(obj.getNumVol().getNumVol() ,obj.getNumPersonnel().getNumPersonnel());
				
		    } catch (SQLException e) {
		    	
		            e.printStackTrace();
		    }
		    
			return obj;
		}
	
	 
	 

		public void delete(PersonnelVol obj) {
			// On peut pas supprimer le numero d'un vol lié au personnel 
							
				try{
					
					PreparedStatement prepare = connect.prepareStatement("DELETE FROM PERSONNEL_VOL WHERE NUM_PERSONNEL = ? AND NUM_VOL = ? ");
					
					
					prepare.setInt(1, obj.getNumPersonnel().getNumPersonnel());
					prepare.setInt(2, obj.getNumVol().getNumVol());

					prepare.execute();
					ResultSet result = prepare.getResultSet();
					
					AlertMessages.InfoBox("DELETE WITH SUCCES" , "DELETE INSERSION");
					
			    } catch (SQLException e) {
			    	
					AlertMessages.ErrorBox("ECHEC SUPPERESION DU VOL [N: " + obj.getNumVol().getNumVol()+ " , :P" + obj.getNumPersonnel().getNumPersonnel()+ "]","ECHEC INSERSION");

			            e.printStackTrace();
			    }
		}
		
		
		public PersonnelVol selectById(int numVol , int numPersonnel) {
			
			PersonnelVol pr = new PersonnelVol();

			Vol v = null ;
			Personnel p= null;
			
			try {
				
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT DISTINCT * FROM PERSONNEL_VOL WHERE NUM_VOL = '" + numVol + "' AND NUM_PERSONNEL = " + numPersonnel);
				
				if(result.first())
					
					p = DaoPersonnel.selectById(result.getInt("NUM_PERSONNEL"));
					v = DaoVol.selectbyID(result.getInt("NUM_VOL"));
					
					pr = new PersonnelVol(p,v);
					result.close();	
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return pr;
		}
		
		public ArrayList<Vol> selectAllPersonnelVols() {
			
			ArrayList<Vol> prs = new ArrayList<Vol>();
					
			
			try {
				
				PreparedStatement prepare = connect.prepareStatement("SELECT DISTINCT NUM_VOL FROM PERSONNEL_VOL ");
				
				prepare.execute();
				ResultSet result = prepare.getResultSet();

				while(result.next())
				{
					prs.add( DaoVol.selectbyID(result.getInt("NUM_VOL")));
					
				}	
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return prs;
		}


		@Override
		public ArrayList<PersonnelVol> selectAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
			

}
