package model.classeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Langue;
import model.classes.LangueHotesse;
import model.classes.Personnel;


public class DaoLangueHotesse extends DAO<LangueHotesse> {
	
	public LangueHotesse insert(LangueHotesse obj) {
		
		int numPersonnel = obj.getNumPersonnel().getNumPersonnel();
		int numLangue = obj.getNumLangue().getNumLangue();
		
		ResultSet result;
		
		try {
			
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT numPersonnel  FROM LangueHotesse");

			if (result.first()) {
				
				int lastId = result.getInt("numPersonnel");
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO LangueHotesse (numPersonnel , numLangue) VALUES(?, ?)");
				prepare.setInt(1, lastId);
				prepare.setInt(3, numLangue);

				prepare.executeUpdate();
				obj = this.selectById(numPersonnel);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}
	
	
	public LangueHotesse modify(LangueHotesse obj) {
		
		int numPersonnel = obj.getNumPersonnel().getNumPersonnel();
		int numLangue = obj.getNumLangue().getNumLangue();
		
		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Avion SET numPersonnel = '" + numPersonnel + "',"+
                	" WHERE numLangue = '" + numLangue + "'"
                 );

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	
	public void delete(LangueHotesse obj) {
		
		try{
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM LangueHotesse WHERE numPersonnel = " + obj.getNumPersonnel()
                 );
			
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	public LangueHotesse selectById(int numP) {
		
		LangueHotesse a = new LangueHotesse();
		
		DaoLangue lDAO = new DaoLangue();
		DaoPersonnel pDAO = new DaoPersonnel();
		Langue l = null;
		Personnel p = null;
		
		try {
			
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM LangueHotesse WHERE numPersonnel = " + numP);
			
			if(result.first())
				
				
				p = pDAO.selectById(result.getInt("numPersonnel"));
				l = lDAO.selectById(result.getInt("numLangue"));
					
				a = new LangueHotesse(p,l);
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}
	
	
	public ArrayList<LangueHotesse> selectAll() {
		
		ArrayList<LangueHotesse> a = new ArrayList<LangueHotesse>();
		DaoLangue lDAO = new DaoLangue();
		DaoPersonnel pDAO = new DaoPersonnel();
		Langue l = null;
		Personnel p = null;
		
		try {
			
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM LangueHotesse");
			
			while(result.next()) {
				p = pDAO.selectById(result.getInt("numPersonnel"));
				l = lDAO.selectById(result.getInt("numLangue"));
				a.add(new LangueHotesse(p,l));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return a;
	}


}
