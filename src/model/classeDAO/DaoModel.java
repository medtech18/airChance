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
			
				PreparedStatement prepare =  connect.prepareStatement("INSERT INTO Model (num_modele, nom_modele, nb_min_pilote, rayon_action) VALUES(?, ?, ?, ?)");
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
                	"UPDATE Model SET nom_modele = " + obj.getNomModele() + ","+
                    " rayon_action = " + obj.getRayonAction() + ","+
                    " nb_min_pilote = " + obj.getNbPiloteMin() + ","+
                	" WHERE num_modele = '" + obj.getNumModele() + "'" 
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
                	"DELETE FROM Model WHERE num_modele = '" + obj.getNumModele() + "'"
                 );
			
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		
	}
	
	public static Model selectById(int numModele) {
		Model m = new Model();
		
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Model WHERE num_modele = '" + numModele + "'");
			
			if(result.first())
				
				m = new Model(result.getString("nom_modele"),numModele,result.getInt("nb_min_pilote"),result.getInt("rayon_action"));
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
		return m;
	}
	
	public ArrayList<Model> selectAll() {
		
		ArrayList<Model> m = new ArrayList<Model>();
		
		try {
			
			ResultSet result =  connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Model");
			
			while(result.next()) {
				
				m.add(new Model(result.getString("nom_modele"),result.getInt("nb_min_pilote"),result.getInt("num_modele"),result.getInt("rayon_action")));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return m;
	}
		
	
	

}
