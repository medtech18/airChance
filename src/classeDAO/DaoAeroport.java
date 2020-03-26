package classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Aeroport;

public class DaoAeroport extends DAO<Aeroport> {
	

	@Override
	public Aeroport insert(Aeroport obj) {
		
	
		
		try {
			
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Aeroport (numAeroport, nomAeroport, nomVille, nomPays) VALUES(?, ?, ?, ?)");
			
			prepare.setInt(1, obj.getNumAeroport());
			prepare.setString(2, obj.getNomAeroport());
			prepare.setString(3, obj.getNomVille());
			prepare.setString(4, obj.getNomPays());

			prepare.executeUpdate();
			
			obj = this.selectbyID(obj.getNumAeroport());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	@Override
	public Aeroport modify(Aeroport obj) {


		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"UPDATE Aeroport SET nomVille = '" + obj.getNomVille() + "',"+
                    " nomPays = '" + obj.getNomPays() + "',"+
                    " nomAeroport = '" + obj.getNomAeroport() + "'"+
                	" WHERE numAeroport = '" + obj.getNumAeroport() + "'"
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
				
				a = new Aeroport(numAeroport,result.getString("nomAeroport"),result.getString("nomVille"), result.getString("nomPays"));
				
		
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
				
				a.add(new Aeroport(result.getInt("numAeroport"),result.getString("nomAeroport"),result.getString("nomVille"),
					result.getString("nomPays")));
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}

} 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	
	

