package classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Aeroport;
import classes.Pays;
import classes.Ville;

public class DaoAeroport extends DAO<Aeroport> {

	@Override
	public Aeroport insert(Aeroport obj) {
		int numAeroport = obj.getNumAeroport();
		String nomAeroport = obj.getNomAeroport();
		String nomVille = obj.getNomVille().getNomVille();
		String nomPays = obj.getNomPays().getNomPays();
		
		try {
			
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Aeroport (numAeroport, nomAeroport, nomVille, nomPays) VALUES(?, ?, ?, ?)");
			
			prepare.setInt(1, numAeroport);
			prepare.setString(2, nomAeroport);
			prepare.setString(3, nomVille);
			prepare.setString(4, nomPays);

			prepare.executeUpdate();
			
			obj = this.selectbyID(numAeroport);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	@Override
	public Aeroport modify(Aeroport obj) {
		
		int numAeroport = obj.getNumAeroport();
		String nomAeroport = obj.getNomAeroport();
		String nomVille = obj.getNomVille().getNomVille();
		String nomPays = obj.getNomPays().getNomPays();

		try{
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Aeroport SET nomVille = '" + nomVille + "',"+
                    " nomPays = '" + nomPays + "',"+
                    " nomAeroport = '" + nomAeroport + "'"+
                	" WHERE numAeroport = '" + numAeroport + "'"
                 );

			obj = this.selectbyID(obj.getNumAeroport());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}

	@Override
	public void delete(Aeroport object) {
		try{
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate("DELETE FROM Aeroport WHERE numAeroport = " + object.getNumAeroport());
	    
		} catch (SQLException e) {
			
	            e.printStackTrace();
	    }
	}
	

	public Aeroport selectbyID(int numAeroport) {
		Aeroport a = new Aeroport();
		
		try {
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Aeroport WHERE numAeroport  = '" + numAeroport + "'");
			
			
			if(result.first())
				
				a = new Aeroport(numAeroport,result.getString("nomAeroport"),new Ville(result.getString("nomVille"), new Pays(result.getString("nomPays"))),
					new Pays(result.getString("nomPays")));
		
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return a;
	}

	

	@Override
	public ArrayList<Aeroport> selectAll() {
		ArrayList<Aeroport> a = new ArrayList<Aeroport>();
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Aeroport");
			
			while(result.next())
				
				a.add(new Aeroport(result.getInt("numAeroport"),result.getString("nomAeroport"),new Ville(result.getString("nomVille"), new Pays(result.getString("nomPays"))),
					new Pays(result.getString("nomPays"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}

} 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	
	

