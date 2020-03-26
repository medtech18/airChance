package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Adresse;



public class DaoAdresse extends DAO<Adresse> {
	
	
	
	public Adresse insert(Adresse obj) {

		try {
	
				PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO Adresse (numAdresse, numRue, nomRue, codePostal, nomVille, nomPays) VALUES(?, ?, ?, ?, ?, ?)");
				
				prepare.setInt(1, obj.getNumAdresse());
				prepare.setInt(2, obj.getNumAllee());
				prepare.setString(3, obj.getNomRue());
				prepare.setString(4, obj.getCodePostal());
				prepare.setString(5, obj.getNomVille());
				prepare.setString(6, obj.getNomPays());

				prepare.executeUpdate();
				obj = this.selectbyID(obj.getNumAdresse());
				
			
			
			} catch (SQLException e) {
				
				e.printStackTrace();
		}

		return obj;
	}

	@Override
	public Adresse modify(Adresse obj) {
		
	
		
		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(

                	"UPDATE Adresse SET nomVille = '" + obj.getNomVille() + "',"+
                    " nomPays = '" + obj.getNomPays() + "',"+
                    " nomRue = '" + obj.getNomRue() + "',"+
                    " numAllee = " + obj.getNumAllee() + ","+
                	" codePostal = '" + obj.getCodePostal() + "'"+
                	"UPDATE Adresse SET nomVille = '" + obj.getNomVille() + "',"+
                    " nomPays = '" +  obj.getNomPays() + "',"+
                    " nomRue = '" + obj.getNomRue() + "',"+
                    " numAllee = " + obj.getNumAllee() + ","+
                	" codePostal = '" + obj.getCodePostal() + "'"+
                	" WHERE numAdresse = " + obj.getNumAdresse()
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
					result.getString("nomVille"), result.getString("nomPays"));
		
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
				
				a.add(new Adresse(result.getInt("numAdresse"),result.getInt("numRue"),result.getString("nomRue"),result.getString("codePostal"),result.getString("nomVille"), result.getString("nomPays")));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}
	
	

	

}
