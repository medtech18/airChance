package classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Adresse;
import classes.Pays;
import classes.Ville;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Adresse;
import classes.Pays;
import classes.Ville;

public class DaoAdresse extends DAO<Adresse> {
	
	public Adresse insert(Adresse obj) {
		
		int numAdresse = obj.getNumAdresse();
		int numAllee = obj.getNumAllee();
		String nomRue = obj.getNomRue();
		String codePostal = obj.getCodePostal();
		String nomVille = obj.getNomVille().getNomVille();
		String nomPays = obj.getNomPays().getNomPays();
		
		ResultSet result;
		
		try {
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT numAdresse FROM Adresse");

			
			if (result.first()) {
				int lastId = result.getInt("numAdresse");
				
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Adresse (numAdresse, numRue, nomRue, codePostal, nomVille, nomPays) VALUES(?, ?, ?, ?, ?, ?)");
				prepare.setInt(1, lastId);
				prepare.setInt(2, numAllee);
				prepare.setString(3, nomRue);
				prepare.setString(4, codePostal);
				prepare.setString(5, nomVille);
				prepare.setString(6, nomPays);

				prepare.executeUpdate();
				obj = this.selectbyID(numAdresse);
				
			}
			
			} catch (SQLException e) {
				
				e.printStackTrace();
		}

		return obj;
	}

	@Override
	public Adresse modify(Adresse obj) {
		
		int numAdresse = obj.getNumAdresse();
		int numAllee = obj.getNumAllee();
		String nomRue = obj.getNomRue();
		String codePostal = obj.getCodePostal();
		String nomVille = obj.getNomVille().getNomVille();
		String nomPays = obj.getNomPays().getNomPays();
		
		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Adresse SET nomVille = '" + nomVille + "',"+
                    " nomPays = '" + nomPays + "',"+
                    " nomRue = '" + nomRue + "',"+
                    " numAllee = " + numAllee + ","+
                	" codePostal = '" + codePostal + "'"+
                	" WHERE numAdresse = " + numAdresse
                 );

			obj = this.selectbyID(obj.getNumAdresse());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}




	public Adresse selectbyID(int numAdresse) {
		Adresse a = new Adresse();
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Adresse WHERE numAdresse = " + numAdresse);
			
			if(result.first())
				
				a = new Adresse(numAdresse,result.getInt("numRue"),result.getString("nomRue"),result.getString("codePostal"),
					new Ville(result.getString("nomVille"), new Pays(result.getString("nomPays"))),new Pays(result.getString("nomPays")));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}

	
	@Override
	public void delete(Adresse object) {} // ici  On interdit la suppression d'une adresse
	
	
	
	@Override
	public ArrayList<Adresse> selectAll() {
		ArrayList<Adresse> a = new ArrayList<Adresse>();
		
		try {
			
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM Adresse");
			
			
			while(result.next())
				
				a.add(new Adresse(result.getInt("numAdresse"),result.getInt("numRue"),result.getString("nomRue"),result.getString("codePostal"),new Ville(result.getString("nomVille"), new Pays(result.getString("nomPays"))),
					new Pays(result.getString("nomPays"))));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}
	
	

	

}
