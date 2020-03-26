package classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Model;

public class DaoModel extends DAO<Model>{
	
	public Model insert(Model obj) {
		
		String nomModele = obj.getNomModele();
		int nbPiloteMin = obj.getNbPiloteMin();
		int rayonAction = obj.getRayonAction();
		int numModele = obj.getNumModele();
		
		try {
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Modele (numModele, nomModele, nbPiloteMin, rayonAction) VALUES(?, ?, ?, ?)");
				prepare.setInt(1, numModele);
				prepare.setString(2, nomModele);
				prepare.setInt(3, nbPiloteMin);
				prepare.setInt(4, rayonAction);

				prepare.executeUpdate();
				obj = this.selectById(numModele);
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}
	
	public Model  modify(Model obj) {
		String nomModele = obj.getNomModele();
		int nbPiloteMin = obj.getNbPiloteMin();
		int rayonAction = obj.getRayonAction();
		int numModele = obj.getNumModele();
		
		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Modele SET numModele = " + numModele + ","+
                    " rayonAction = " + rayonAction + ","+
                    " nbPiloteMin = " + nbPiloteMin + ","+
                	" WHERE nomModele = '" + nomModele + "'" 
                 );

			obj = this.selectById(obj.getNumModele());
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	public void delete(Model obj) {
		
		try{
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Model WHERE numModele = '" + obj.getNumModele() + "'"
                 );
			
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		
	}
	
	public Model selectById(int numModele) {
		Model m = new Model();
		
		try {
			
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
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
			
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
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
