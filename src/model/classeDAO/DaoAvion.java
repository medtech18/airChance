package model.classeDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Avion;


public class DaoAvion extends DAO<Avion> {
	
	public Avion insert(Avion obj) {

		
		
		try {
			
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO Avion (numAvion , nomModele, nbrPlaceEco , nbrPlacePremiere ,nbrPlaceAffaire) VALUES(?, ?, ?, ?, ?)");
				prepare.setInt(1, obj.getNumAvion());
				prepare.setInt(2, obj.getNumModele().getNumModele());
				prepare.setInt(3, obj.getNbrPlaceEco());
				prepare.setInt(4, obj.getNbrPlacePremiere());
				prepare.setInt(5, obj.getNbrPlaceAffaire());

				prepare.executeUpdate();
				obj = selectById(obj.getNumAvion());
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}

	public Avion modify(Avion obj) {
		
	
		
		try{
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Avion SET numModele = '" + obj.getNumModele().getNumModele() + "',"+
                	" WHERE numAvion = '" + obj.getNumAvion() + "'"
                 );

			obj = selectById(obj.getNumAvion());
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	
	public void delete(Avion obj) {
		
		try{
			
			// Suppression de l'avion portant le numéro de l'avion que l'on souhaite supprimer
			
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Avion WHERE numAvion = " + obj.getNumAvion()
                 );
			
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	public static Avion selectById(int numA) {
		
		Avion a = new Avion();

		
		try {
			
			ResultSet result =  connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Avion WHERE numAvion = " + numA);
			
			if(result.first())
				
					
				a = new Avion(numA,DaoModel.selectById(result.getInt("numModele")), result.getInt("nbrPlaceEco") ,result.getInt("nbrPlacePremiere"),result.getInt("nbrPlaceAffaire") );
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}
	
	public ArrayList<Avion> selectAll() {
		
		ArrayList<Avion> a = new ArrayList<Avion>();
	
		try {
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Avion");
			
			while(result.next()) {

				a.add(new Avion(result.getInt("numAvion"),DaoModel.selectById(result.getInt("numModele")),result.getInt("nbrPlaceEco") ,result.getInt("nbrPlacePremiere"),result.getInt("nbrPlaceAffaire")));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return a;
	}
	
	



}
