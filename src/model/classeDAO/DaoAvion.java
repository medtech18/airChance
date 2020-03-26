package model.classeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Avion;
import model.classes.Model;

public class DaoAvion extends DAO<Avion> {
	
	public Avion insert(Avion obj) {
		
		int numAvion = obj.getNumAvion();
		int numModele = obj.getNumModele().getNumModele();
		
		ResultSet result;
		
		try {
			
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT numAvion  FROM Avion");

			if (result.first()) {
				
				int lastId = result.getInt("numAvion");
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Avion (numAvion , nomModele) VALUES(?, ?)");
				prepare.setInt(1, lastId);
				prepare.setInt(2, numModele);

				prepare.executeUpdate();
				obj = this.selectById(numAvion);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}

	public Avion modify(Avion obj) {
		
		int numAvion = obj.getNumAvion();
		int numModele = obj.getNumModele().getNumModele();
		
		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Avion SET numModele = '" + numModele + "',"+
                	" WHERE numAvion = '" + numAvion + "'"
                 );

			obj = this.selectById(obj.getNumAvion());
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	
	public void delete(Avion obj) {
		
		try{
			
			// Suppression de l'avion portant le numéro de l'avion que l'on souhaite supprimer
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Avion WHERE numAvion = " + obj.getNumAvion()
                 );
			
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	public static Avion selectById(int numA) {
		
		Avion a = new Avion();
		
		DaoModel mDAO = new DaoModel();
		Model m = null;
		
		try {
			
			ResultSet result =  connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Avion WHERE numAvion = " + numA);
			
			if(result.first())
				
				m = mDAO.selectById(result.getInt("numModele"));
					
				a = new Avion(numA,m);
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}
	
	public ArrayList<Avion> selectAll() {
		
		ArrayList<Avion> a = new ArrayList<Avion>();
		DaoModel mDAO = new DaoModel();
		Model m = null;
		
		try {
			
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Avion");
			
			while(result.next()) {
				m = mDAO.selectById(result.getInt("numModele"));
				a.add(new Avion(result.getInt("numAvion"),m));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return a;
	}
	
	



}
