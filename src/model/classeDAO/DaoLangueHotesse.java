package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.LangueHotesse;



public class DaoLangueHotesse extends DAO<LangueHotesse> {
	
	public LangueHotesse insert(LangueHotesse obj) {
		
		
		try {
	
				connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO LangueHotesse (numPersonnel , numLangue) VALUES(?, ?)");
				prepare.setInt(1, obj.getNumPersonnel().getNumPersonnel());
				prepare.setInt(2, obj.getNumLangue().getNumLangue());

				prepare.executeUpdate();
				obj = this.selectById(obj.getNumPersonnel().getNumPersonnel(),obj.getNumLangue().getNumLangue());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}
	
	
	public LangueHotesse modify(LangueHotesse obj) {
		

		try{
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Avion SET numPersonnel = '" + obj.getNumPersonnel().getNumPersonnel() + "',"+
                	" WHERE numLangue = '" + obj.getNumLangue().getNumLangue() + "'"
                 );

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	
	public void delete(LangueHotesse obj) {
		
		try{
			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM LangueHotesse WHERE numPersonnel = " + obj.getNumPersonnel()
                 );
			
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	public LangueHotesse selectById(int numPersonnel , int numLangue) {
		
		LangueHotesse a = new LangueHotesse();
		
		
		try {
			
			ResultSet result =  connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM LangueHotesse WHERE numPersonnel = '" + numPersonnel + "' AND numLangue = " + numLangue);
			
			if(result.first())
				
				a = new LangueHotesse(DaoPersonnel.selectById(result.getInt("numPersonnel")),DaoLangue.selectById(result.getInt("numLangue"))
					);
			
			} catch (SQLException e) {
			
				e.printStackTrace();
				}
		
		return a;
	}
	
	
	public ArrayList<LangueHotesse> selectAll() {
		
		ArrayList<LangueHotesse> a = new ArrayList<LangueHotesse>();
		
		try {
			
			ResultSet result =  connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM LangueHotesse");
			
			while(result.next()) {
				
				a.add(new LangueHotesse(DaoPersonnel.selectById(result.getInt("numPersonnel")),DaoLangue.selectById(result.getInt("numLangue"))));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return a;
	}


}
