package model.classeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.classes.Vol;



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
				prepare.setInt		(11, obj.getNumAvion().getNumAvion());

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
			this.connect
			.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
			.executeUpdate("UPDATE VOL set num_vol = "			+obj.getNumVol() +","+
						   "date_vol = "			+obj.getDateVol() +"," + 
						   "aeroport_origine ="	+obj.getAeroportDepart().getNumAeroport() + "," +
						   "aeroport_destination ="+obj.getAeroportArrive().getNumAeroport() +","+
						   "duree ="				+obj.getDuree() + "," + 
						   "distance ="			+obj.getDistanceVol() +","+
						   "minplace_eco ="		+obj.getnbrMinPlaceEco() +","+ 
						   "nbr_minplace_premiere="+obj.getnbrMinPlacePremiere() +","+
						   "nbr_minplace_affaire ="+obj.getnbrMinPlaceAffaire() +","+
						   "terminaison ="			+(obj.isVoltermine()?1:0) +","+
						   "num_avion ="			+obj.getNumAvion().getNumAvion()
							
					);

			obj = this.selectbyID(obj.getNumVol());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
		return obj;
	}

	

	public static Vol selectbyID(int numVol) {
		Vol a = new Vol();
		
		try {
			
			ResultSet result = connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM Vol WHERE numVol = " + numVol);
			
			if(result.first())
			{
			
					a = new Vol(
							numVol,
							result.getString("date_vol"),
							DaoAeroport.selectbyID(result.getInt(3)) ,
							DaoAeroport.selectbyID(result.getInt(4)) ,
							result.getInt("duree") ,
							result.getInt("distance") , 
							(result.getInt("terminaison")==1?true:false),
							DaoAvion.selectById(result.getInt("num_avion")) , 
							result.getInt("nbr_minplace_eco") ,
							result.getInt("nbr_minplace_premiere") ,
							result.getInt("nbr_minplace_affaire")
							);
			}

			
				
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}

	@Override
	public void delete(Vol object) {} // ici  On interdit la suppression d'une adresse
		
	@Override
	public ArrayList<Vol> selectAll() {
		ArrayList<Vol> volList = new ArrayList<Vol>();
		
		try {
			
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM Adresse");
			

			while(result.next())
			{
				Vol newVol = new Vol(
							result.getInt("num_vol"),
							result.getString("date_vol"),
							DaoAeroport.selectbyID(result.getInt(3)) ,
							DaoAeroport.selectbyID(result.getInt(4)) ,
							result.getInt("duree") ,
							result.getInt("distance") , 
							(result.getInt("terminaison")==1?true:false),
							DaoAvion.selectById(result.getInt("num_avion")) , 
							result.getInt("nbr_minplace_eco") ,
							result.getInt("nbr_minplace_premiere") ,
							result.getInt("nbr_minplace_affaire")
							);
				volList.add(newVol);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return volList;
	}
	
	

	

}
