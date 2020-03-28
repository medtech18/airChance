package model.classeDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Model;

public class DaoModel extends DAO<Model>{
	
	public Model insert(Model obj) {
		
	
		try {
			
				connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			
				PreparedStatement prepare =  connect.prepareStatement("INSERT INTO Modele (numModele, nomModele, nbPiloteMin, rayonAction) VALUES(?, ?, ?, ?)");
				prepare.setInt(1, obj.getNumModele());
				prepare.setString(2, obj.getNomModele());
				prepare.setInt(3, obj.getNbPiloteMin());
				prepare.setInt(4, obj.getRayonAction());

				prepare.executeUpdate();
				obj = selectById(obj.getNumModele());
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}
	
	public Model  modify(Model obj) {
		
		
		try{
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Modele SET numModele = " + obj.getNumModele() + ","+
                    " rayonAction = " + obj.getRayonAction() + ","+
                    " nbPiloteMin = " + obj.getNbPiloteMin() + ","+
                	" WHERE nomModele = '" + obj.getNomModele() + "'" 
                 );

			obj = selectById(obj.getNumModele());
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	public void delete(Model obj) {
		
		try{
			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Model WHERE numModele = '" + obj.getNumModele() + "'"
                 );
			
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		
	}
	
	public static Model selectById(int numModele) {
		Model m = new Model();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Modele WHERE numModele = '" + numModele + "'");
			
			if(result.first())
				
				m = new Model(result.getString("nomModele"),numModele,result.getInt("nbPiloteMin"),result.getInt("rayonAction"));
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
		return m;
	}
	
	public ArrayList<Model> selectAll() {
		
		ArrayList<Model> m = new ArrayList<Model>();
		
		try {
			
			ResultSet result =  connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Modele");
			
			while(result.next()) {
				
				m.add(new Model(result.getString("nomModele"),result.getInt("nbPiloteMin"),result.getInt("numModele"),result.getInt("rayonAction")));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return m;
	}
		
	
	

}
