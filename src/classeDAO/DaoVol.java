package classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Vol;


public class DaoVol extends DAO<Vol> {
	
	public Vol insert(Vol obj) {
		
		ResultSet result;
		
		try {
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
						"SELECT numAdresse FROM Adresse");

			
			if (result.first()) {
				int lastId = result.getInt("numAdresse");
				
				PreparedStatement prepare = this.connect.prepareStatement(""
						+ "INSERT INTO Vol (num_vol ,date_vol,aeroport_origine,aeroport_destination,duree ,distance ,_minplace_eco ,nbr_minplace_premiere ,nbr_minplace_affaire ,terminaison ,num_avion\n" + 
						")"
						+ " VALUES(?, ?, ?, ?, ?, ?,?,?,?,?,?)");
				prepare.setInt		(1, obj.getNumVol());
				prepare.setString	(2, obj.getDateVol());
				prepare.setInt		(3,obj.getAeroportDepart().getNumAeroport());
				prepare.setInt		(4, obj.getAeroportArrive().getNumAeroport());
				prepare.setFloat	(5, obj.getDuree());
				prepare.setFloat	(6, obj.getDistanceVol());
				prepare.setInt		(7, obj.getnbrMinPlaceEco());
				prepare.setInt		(8, obj.getnbrMinPlacePremiere());
				prepare.setInt		(9, obj.getnbrMinPlaceAffaire());
				prepare.setInt		(10, obj.isVoltermine()?1:0);
				prepare.setInt		(10, obj.getNumAvion().getNumAvion());

				prepare.executeUpdate();
				obj = this.selectbyID(obj.getNumVol());
				
			}
			
			} catch (SQLException e) {
				
				e.printStackTrace();
		}

		return obj;
	}

	@Override
	public Vol modify(Vol obj) {
		
		try{
			
			this.connect.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeUpdate(
                	"UPDATE Vol SET nomVille = '" + nomVille + "',"+
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

	


	public Vol selectbyID(int numAdresse) {
		Adresse a = new Adresse();
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(
	                            "SELECT * FROM Vol WHERE numAdresse = " + numAdresse);
			
			if(result.first())
				
				a = new Adresse(numAdresse,result.getInt("numRue"),result.getString("nomRue"),result.getString("codePostal"),
					new Ville(result.getString("nomVille"), new Pays(result.getString("nomPays"))),new Pays(result.getString("nomPays")));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}

	
	@Override
	public void delete(Vol object) {} // ici  On interdit la suppression d'une adresse
	
	
	
	@Override
	public ArrayList<Vol> selectAll() {
		ArrayList<Vol> a = new ArrayList<Vol>();
		
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
