package model.classeDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Adresse;


public class DaoAdresse extends DAO<Adresse> {
	
	
	
	public Adresse insert(Adresse obj) {

		try {
			
				connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
				PreparedStatement prepare = connect.prepareStatement("INSERT INTO Adresse (num_adresse, num_allee, rue, code_postal, ville, pays) VALUES(?, ?, ?, ?, ?, ?)");
				
				prepare.setInt(1, obj.getNumAdresse());
				prepare.setInt(2, obj.getNumAllee());
				prepare.setString(3, obj.getNomRue());
				prepare.setString(4, obj.getCodePostal());
				prepare.setString(5, obj.getNomVille());
				prepare.setString(6, obj.getNomPays());

				prepare.executeUpdate();
				obj = selectbyID(obj.getNumAdresse());
				
			
			
			} catch (SQLException e) {
				
				e.printStackTrace();
		}

		return obj;
	}

	@Override
	public Adresse modify(Adresse obj) {
		
	
		
		try{
			
			connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Adresse SET ville = '" + obj.getNomVille() + "',"+
                    " pays = '" +  obj.getNomPays() + "',"+
                    " rue = '" + obj.getNomRue() + "',"+
                    " numAllee = " + obj.getNumAllee() + ","+
                	" code_postal = '" + obj.getCodePostal() + "'"+
                	" WHERE num_adresse = " + obj.getNumAdresse()
                 );

			obj = selectbyID(obj.getNumAdresse());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}

	


	public static Adresse selectbyID(int numAdresse) {
		Adresse a = new Adresse();
		
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Adresse WHERE num_adresse = " + numAdresse);
			
			if(result.first())
				
				a = new Adresse(numAdresse,result.getInt("num_allee"),result.getString("rue"),result.getString("code_postal"),
					result.getString("ville"), result.getString("pays"));
		
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
			
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM Adresse");
			
			
			while(result.next())
				
				a.add(new Adresse(result.getInt("num_adresse"),result.getInt("num_allee"),result.getString("rue"),result.getString("code_postal"),result.getString("ville"), result.getString("pays")));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}
	
	

	

}
