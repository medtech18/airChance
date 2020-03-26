package classeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Langue;



public class DaoLangue extends DAO<Langue>{
	
	public Langue insert(Langue obj) {
		
		int numLangue = obj.getNumLangue();
		String nom = obj.getNom();
		
		ResultSet result;
		
		try {
			
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT numLangue  FROM Langue");

			if (result.first()) {
				
				int lastId = result.getInt("numLangue");
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Avion (numLangue , nom) VALUES(?, ?)");
				prepare.setInt(1, lastId);
				prepare.setString(3, nom);

				prepare.executeUpdate();
				obj = this.selectById(numLangue);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return obj;
	}
	

	public Langue modify(Langue obj) {
		
		int numLangue = obj.getNumLangue();
		String nom = obj.getNom();
		
		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Langue SET numLangue = '" + numLangue + "',"+
                	" WHERE nom = '" + nom + "'"
                 );

			obj = this.selectById(obj.getNumLangue());
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	
	public void delete(Langue obj) {
		
		try{

			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Langue WHERE numLangue = " + obj.getNumLangue());
			
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	}
	
	

	public Langue selectById(int numL) {
		
		Langue l = new Langue();
	
		try {
			
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Langue WHERE numLangue = " + numL);
			
			if(result.first())
				
			
				l = new Langue(numL,result.getString("nom"));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return l;
	}
	
public ArrayList<Langue> selectAll() {
		
		ArrayList<Langue> l = new ArrayList<Langue>();
		
		try {
			
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Langue");
			
			while(result.next()) {
				
				l.add(new Langue(result.getInt("numLangue"),result.getString("nom")));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return l;
	}
	
	
	

}
