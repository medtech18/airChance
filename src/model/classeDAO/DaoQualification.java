package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Qualification;




public class DaoQualification extends DAO<Qualification> {
	
	public Qualification insert(Qualification obj) {
		
		
		try {
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO Qualification (numModele, numPersonnel, nbHeures) VALUES(?, ?, ?)");
				
				prepare.setInt(1, obj.getNumModele().getNumModele());
				prepare.setInt(2, obj.getNumPersonnel().getNumPersonnel());
				prepare.setInt(3, obj.getNbHeures());

				prepare.executeUpdate();
				obj = selectById(obj.getNumModele().getNumModele(), obj.getNumPersonnel().getNumPersonnel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		return obj;
	}
	
	
	public Qualification modify(Qualification obj) {
		
		
		try{	
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	
					"UPDATE Qualification SET nbHeures = " + obj.getNbHeures() + ","+
		                	" WHERE numModele = '" + obj.getNumModele().getNumModele() + "' AND numPersonnel = " + obj.getNumPersonnel().getNumPersonnel());

	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj ;
		}
	
	
	
	
	public void delete(Qualification obj) {
		
		try {
	
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Qualification WHERE numModele = '" + obj.getNumModele().getNumModele() + "' AND numPersonnel = " + obj.getNumPersonnel().getNumPersonnel());

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
	
	
	public static Qualification selectById(int numModele, int numPersonnel) {
		
		Qualification q = new Qualification();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT * FROM Qualification WHERE numModele = '" + numModele + "' AND numPersonnel = " + numPersonnel);
			
			if(result.first())
				
				q = new Qualification(DaoModel.selectById(result.getInt("numModele")),DaoPersonnel.selectById(result.getInt("numPersonnel")), result.getInt("nbHeures"));
			    
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return q;
	}
	
	
	
	public ArrayList<Qualification> selectAll() {
		
		ArrayList<Qualification> q = new ArrayList<Qualification>();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Qualification");
			
			while(result.next())
				
				q.add(new Qualification(DaoModel.selectById(result.getInt("numModele")) ,DaoPersonnel.selectById(result.getInt("numPersonnel")), result.getInt("nbHeures")));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return q;
	}
	
	
	

}
