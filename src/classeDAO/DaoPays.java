package classeDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Pays;

public class DaoPays extends DAO<Pays> {
	
	public Pays insert(Pays obj) {
		try{
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"INSERT into Vol values (" + obj.getNomPays() + ")");

			obj = this.selectById(obj.getNomPays());
			
	    } catch (SQLException e) {
	    	
	            e.printStackTrace();
	    }
	    
		return obj;
	}
	
	
	public Pays modify(Pays obj) {
		// il n'a qu'un seul champ c'est pour cela on peut pas mettre à jour le pays
		return obj;
	}
	
	public void delete(Pays object) {
		
		try {
			
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"DELETE FROM Pays WHERE nomPays = " + object.getNomPays());

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		
	}
		
		public Pays selectById(String nomPays) {
			Pays p = new Pays();

			try {
				
				ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM Pays WHERE nomPays = " + nomPays);
				
				if (result.first())
					
					p = new Pays(nomPays);
							
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return p;
			
		}
		
		
		public ArrayList<Pays> selectAll() {
			
			ArrayList<Pays> p = new ArrayList<Pays>();

			try {
				
				ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM Pays");
				
				while(result.next()) {
					p.add(new Pays(result.getString("nomPays")));
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

			return p;
		}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
