package model.classeDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import model.classes.Avion;
import model.classes.Model;




public class DaoAvion extends DAO<Avion> {
	
	public Avion insert(Avion obj) {

		
		
		try {
			
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO Avion (num_avion, nb_place_eco , nb_place_premiere ,nb_place_affaire,num_modele) VALUES(?, ?, ?, ?, ?)");
				prepare.setInt(1, obj.getNumAvion());
				prepare.setInt(2, obj.getNbrPlaceEco());
				prepare.setInt(3, obj.getNbrPlacePremiere());
				prepare.setInt(4, obj.getNbrPlaceAffaire());
				prepare.setInt(5, obj.getNumModele().getNumModele());
				
				prepare.executeUpdate();
				obj = selectById(obj.getNumAvion());
		
				prepare.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}

	public Avion modify(Avion obj) {
		
	
		
		try{
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Avion SET num_modele = '" + obj.getNumModele().getNumModele() + "',"+
                	" WHERE num_avion = '" + obj.getNumAvion() + "'"
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
                	"DELETE FROM Avion WHERE num_avion = " + obj.getNumAvion()
                 );
			
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	public static Avion selectById(int numA) {
		
		Avion a = new Avion();

		
		try {
			
			ResultSet result =  connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Avion WHERE num_avion = " + numA);
			
			if(result.first())
				
					
				a = new Avion(numA,DaoModel.selectById(result.getInt("num_modele")), result.getInt("nb_place_eco") ,result.getInt("nb_place_premiere"),result.getInt("nb_place_affaire") );
			result.close();
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

				a.add(new Avion(result.getInt("num_avion"),DaoModel.selectById(result.getInt("num_modele")),result.getInt("nb_place_eco") ,result.getInt("nb_place_premiere"),result.getInt("nb_place_affaire")));
			}
			result.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return a;
	}
	
	
	public ArrayList<Avion> getAvionsWith(int nb_place_eco , int nb_place_premiere , int nb_place_affaire , Double rayon_action){
		ArrayList<Avion> listAvions = new ArrayList<Avion>();
		try {
			
			

			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT DISTINCT num_avion,num_modele,nb_place_affaire,nb_place_premiere,nb_place_eco from avion natural join model"+
			" where nb_place_eco>="+nb_place_eco+
			" AND nb_place_premiere>="+nb_place_premiere+
			" AND nb_place_affaire>="+nb_place_affaire+
			" AND rayon_action>="+rayon_action);
					
			
			while(result.next())
			{
				Avion newVol = new Avion(
							result.getInt("num_avion"),
							DaoModel.selectById(result.getInt("num_modele")),
							result.getInt("nb_place_affaire"),
							result.getInt("nb_place_premiere"),
							result.getInt("nb_place_eco")
							);
				
				listAvions.add(newVol);
				
			}
			result.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return listAvions;
		
	}

	
	public static int generateRandomInt(int upperRange){
	    Random random = new Random();
	    return random.nextInt(upperRange);
	}


}
