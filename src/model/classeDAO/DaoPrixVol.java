package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.PrixVol;

public class DaoPrixVol extends DAO<PrixVol> {
	
	
	public PrixVol insert(PrixVol obj) {
		
		try {
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			PreparedStatement prepare = connect.prepareStatement("INSERT INTO Place (datePrix, classe, prix, numVol) VALUES(?, ?, ?, ?)");
	
			prepare.setString(1, obj.getDatePrix());
			prepare.setString(2, obj.getClasse());
			prepare.setDouble(3, obj.getPrix());
			prepare.setInt(4, obj.getNumVol().getNumVol());
	
			prepare.executeUpdate();
			obj = selectById(obj.getDatePrix(),obj.getClasse(),obj.getNumVol().getNumVol());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		return obj;
	}
	
	
	public PrixVol modify(PrixVol obj) {
		
		
		try{	
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	
					"UPDATE PrixVol SET datePrix = '" +  obj.getDatePrix() + "',"+
                	" classe = '" + obj.getClasse() + "',"+
                	", " + " prix = " + obj.getPrix() +
                	" WHERE numVol = " + obj.getNumVol().getNumVol()
                 );

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj ;
		}
	
	
	
	public void delete(PrixVol obj) {
		
		try {
	
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM PrixVol WHERE numVol = '" + obj.getNumVol().getNumVol());

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
	
	
	public static PrixVol selectById(String datePrix , String classe , int numVol) {
		
		PrixVol p = new PrixVol();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM PrixVol WHERE datePrix = '" + datePrix + "' AND classe = " + classe +"' AND numVol = " + numVol);
			
			if(result.first())
				
				p = new PrixVol(result.getString("datePrix") ,result.getString("classe"),result.getDouble("prix"),DaoVol.selectbyID(result.getInt("numVol")));
			    
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	
	
	public ArrayList<PrixVol> selectAll() {
		
		ArrayList<PrixVol> p = new ArrayList<PrixVol>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Place");
			
			while(result.next())
				
				p.add(new PrixVol(result.getString("datePrix") ,result.getString("classe"),result.getDouble("prix"),DaoVol.selectbyID(result.getInt("numVol"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
