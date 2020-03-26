package classeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classes.Place;

public class DaoPlace extends DAO<Place> {
	
	public Place insert(Place obj) {
		
		try {
			
			int  numPlace = obj.getNumPlace();
			String classe = obj.getClasse();
			String position = obj.getPosition();
			int numAvion = obj.getNumAvion().getNumAvion();
			
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Place (numPlace, classe, position, numAvion) VALUES(?, ?, ?, ?)");
			
			prepare.setInt(1, numPlace);
			prepare.setString(2, classe);
			prepare.setString(3, position);
			prepare.setInt(6, numAvion);
	
			prepare.executeUpdate();
			obj = this.selectById(numPlace);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		return obj;
	}
	
	
	public Place modify(Place obj) {
		
		
		try{	
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect	.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	
					"UPDATE Place SET classe = '" + obj.getClasse() + "',"+
                	" position = '" + obj.getPosition() + "',"+
                	", " + " numAvion = " + obj.getNumAvion().getNumAvion() +
                	" WHERE numPlace = " + obj.getNumPlace()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj ;
		}
	
	
	
	
	
	
	public void delete(Place obj) {
	
		try {
	
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Place WHERE numPlace = '" + obj.getNumPlace() + "' AND numAvion = " + obj.getNumAvion().getNumAvion());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public Place selectById(int numPlace) {
		
		Place p = new Place();
		
		try {
			
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Place WHERE numPlace = '" + numPlace);
			
			if(result.first())
				
				p = new Place(numPlace,result.getString("nomClasse"),result.getString("nomPosition"),
			        new DaoAvion().selectById(result.getInt("numAvion")));
			    
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	
	
	public ArrayList<Place> selectAll() {
		
		ArrayList<Place> p = new ArrayList<Place>();
		
		try {
			
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Place");
			
			while(result.next())
				
				p.add(new Place(result.getInt("numPlace"),result.getString("classe"),result.getString("position"),new DaoAvion().selectById(result.getInt("numAvion"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return p;
	}
	

}
