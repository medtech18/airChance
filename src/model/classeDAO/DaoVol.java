package model.classeDAO;


import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import controller.common.AlertMessages;
import model.classes.AeroPort;
import model.classes.Reservation;
import model.classes.Vol;

public class DaoVol extends DAO<Vol> {
	
	public Vol insert(Vol obj) {
		
		ResultSet result;
		
		try {

			
				
				PreparedStatement prepare = this.connect.prepareStatement(""
						+ "INSERT INTO Vol (num_vol ,date_vol,aeroport_origine,aeroport_destination,duree ,distance ,nbr_minplace_eco ,nbr_minplace_premiere ,nbr_minplace_affaire ,terminaison ,num_avion)"
						+ " VALUES(?, ?, ?, ?, ?, ?,?,?,?,0,?)");
				prepare.setInt		(1,  obj.getNumVol());
				prepare.setDate		(2,  obj.getDateVol());
				prepare.setInt		(3,  obj.getAeroportDepart().getNumAeroport());
				prepare.setInt		(4,  obj.getAeroportArrive().getNumAeroport());
				prepare.setFloat	(5,  obj.getDuree());
				prepare.setFloat	(6 , obj.getDistanceVol());
				prepare.setInt		(7 , obj.getnbrMinPlaceEco());
				prepare.setInt		(8 , obj.getnbrMinPlacePremiere());
				prepare.setInt		(9, obj.getnbrMinPlaceAffaire());
				prepare.setInt		(10, obj.getNumAvion().getNumAvion());

				prepare.executeUpdate();

				AlertMessages.InfoBox("VOL INSERER AVEC SUCCES ","INSERTION SUCCESS");

				prepare.close();
			} catch (SQLException e) {
				
				AlertMessages.ErrorBox(
						e.getMessage(),"SQL EXCEPTION");

				e.printStackTrace();
		}

		return obj;
	}

	
	
	@Override
	public Vol modify(Vol obj) {
		PreparedStatement prepare = null;
		try{
				 prepare = this.connect.prepareStatement("UPDATE VOL set date_vol = ? , aeroport_origine = ? , aeroport_destination = ?,"
						+ "duree = ? , distance = ? , NBR_MINPLACE_ECO = ? , nbr_minplace_premiere= ? , "
						+ "nbr_minplace_affaire = ?, terminaison = ? , num_avion = ?"
						+ "where num_vol = ? ");
				
				prepare.setDate		(1,  obj.getDateVol());
				prepare.setInt		(2,  obj.getAeroportDepart().getNumAeroport());
				prepare.setInt		(3,  obj.getAeroportArrive().getNumAeroport());
				prepare.setFloat	(4,  obj.getDuree());
				prepare.setFloat	(5 , obj.getDistanceVol());
				prepare.setInt		(6 , obj.getnbrMinPlaceEco());
				prepare.setInt		(7 , obj.getnbrMinPlacePremiere());
				prepare.setInt		(8, obj.getnbrMinPlaceAffaire());
				prepare.setInt		(9, obj.isVoltermine()?1:0);
				prepare.setInt		(10, obj.getNumAvion().getNumAvion());
				prepare.setInt		(11, obj.getNumVol());

				prepare.executeUpdate();
	
				AlertMessages.InfoBox("VOL MODIFIER AVEC SUCCES ","INSERTION SUCCESS");
	
			
				prepare.close();
			} catch (SQLException e) {
				

				AlertMessages.ErrorBox(
						e.getMessage(),"SQL EXCEPTION");
	
				e.printStackTrace();
			}

			

		return obj;
	}

	

	public static Vol selectbyID(int numVol) {
		Vol a = new Vol();
		
		try {
			
			ResultSet result = connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM Vol WHERE NUM_VOL = " + numVol);
			
			if(result.first())
			{
			
					a = new Vol(
							numVol,
							result.getDate("date_vol"),
							DaoAeroport.selectbyID(result.getInt(3)) ,
							DaoAeroport.selectbyID(result.getInt(4)) ,
							result.getFloat("duree") ,
							result.getFloat("distance") , 
							(result.getInt("terminaison")==1?true:false),
							DaoAvion.selectById(result.getInt("num_avion")) , 
							result.getInt("nbr_minplace_eco") ,
							result.getInt("nbr_minplace_premiere") ,
							result.getInt("nbr_minplace_affaire")
							);
			}

			
				
			result.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return a;
	}

	@Override
	public void delete(Vol object) {
		try
		{
			CallableStatement cstmt = this.connect.prepareCall(" {call delete_vol(?)}");
			cstmt.setInt(1, object.getNumVol());

			cstmt.execute();

		AlertMessages.InfoBox("VOL SUPPRIMEE AVEC SUCCES ","INSERTION SUCCESS");

	
		cstmt.close();
	} catch (SQLException e) {
		

		AlertMessages.ErrorBox(
				e.getMessage(),"SQL EXCEPTION");

		e.printStackTrace();
	}

	} // ici  On interdit la suppression d'une adresse
		
	@Override
	public ArrayList<Vol> selectAll() {
		ArrayList<Vol> volList = new ArrayList<Vol>();
		
		try {
			
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM vol");
			

			while(result.next())
			{
				Vol newVol = new Vol(
						result.getInt("num_vol"),
						result.getDate("date_vol"),
						DaoAeroport.selectbyID(result.getInt("AEROPORT_ORIGINE")) ,
						DaoAeroport.selectbyID(result.getInt("AEROPORT_DESTINATION")) ,
						result.getFloat("duree") ,
						result.getFloat("distance") , 
						(result.getInt("terminaison")==1?true:false),
						DaoAvion.selectById(result.getInt("num_avion")) , 
						result.getInt("nbr_minplace_eco") ,
						result.getInt("nbr_minplace_premiere") ,
						result.getInt("nbr_minplace_affaire")
						);
				volList.add(newVol);
				
			}
			
			result.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return volList;
	}
	
	public ArrayList<Vol> selectByReservation(int numreservation) {
		ArrayList<Vol> volList = new ArrayList<Vol>();
		
		try {
			
			ResultSet result = this .connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM vol where num_vol in (select num_vol from place_reserver where num_reservation = "+numreservation+")");
			

			while(result.next())
			{
				Vol newVol = new Vol(
							result.getInt("num_vol"),
							result.getDate("date_vol"),
							DaoAeroport.selectbyID(result.getInt("aeroport_origine")) ,
							DaoAeroport.selectbyID(result.getInt("aeroport_destination")) ,
							result.getFloat("duree") ,
							result.getFloat("distance") , 
							(result.getInt("terminaison")==1?true:false),
							DaoAvion.selectById(result.getInt("num_avion")) , 
							result.getInt("nbr_minplace_eco") ,
							result.getInt("nbr_minplace_premiere") ,
							result.getInt("nbr_minplace_affaire")
							);
				volList.add(newVol);
				
			}
			result.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		return volList;
	}
	
	public ArrayList<Vol> selectbyCritere(Date dvol,AeroPort depart,AeroPort destination) {
		ArrayList<Vol> volList = new ArrayList<Vol>();
		
		try {
			PreparedStatement pstmt = this .connect.prepareStatement(
					"SELECT * FROM vol where date_vol>= ? and aeroport_origine= ? and aeroport_destination= ? "
					+ "and place_dispo(num_vol)>0"
					);
			pstmt.setDate(1, dvol);
			pstmt.setInt(2,depart.getNumAeroport());
			pstmt.setInt(3,destination.getNumAeroport());
			pstmt.execute();
			ResultSet result = pstmt.getResultSet();
			
			while(result.next())
			{
				Vol newVol = new Vol(
						result.getInt("num_vol"),
						result.getDate("date_vol"),
						DaoAeroport.selectbyID(result.getInt("aeroport_origine")) ,
						DaoAeroport.selectbyID(result.getInt("aeroport_destination")) ,
						result.getFloat("duree") ,
						result.getFloat("distance") , 
						(result.getInt("terminaison")==1?true:false),
						DaoAvion.selectById(result.getInt("num_avion")) , 
						result.getInt("nbr_minplace_eco") ,
						result.getInt("nbr_minplace_premiere") ,
						result.getInt("nbr_minplace_affaire")
						);
				volList.add(newVol);
				
			}
			result.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return volList;
	}
	
	public ArrayList<Vol> getNonTerminatedVols() {
		ArrayList<Vol> volList = new ArrayList<Vol>();
		
		try {
			PreparedStatement pstmt = this .connect.prepareStatement(
					"SELECT DISTINCT * FROM VOL where TERMINAISON = 0"
					);
			pstmt.execute();
			ResultSet result = pstmt.getResultSet();
			
			while(result.next())
			{
				Vol newVol = new Vol(
						result.getInt("num_vol"),
						result.getDate("date_vol"),
						DaoAeroport.selectbyID(result.getInt("aeroport_origine")) ,
						DaoAeroport.selectbyID(result.getInt("aeroport_destination")) ,
						result.getFloat("duree") ,
						result.getFloat("distance") , 
						(result.getInt("terminaison")==1?true:false),
						DaoAvion.selectById(result.getInt("num_avion")) , 
						result.getInt("nbr_minplace_eco") ,
						result.getInt("nbr_minplace_premiere") ,
						result.getInt("nbr_minplace_affaire")
						);
				volList.add(newVol);
				

				
			}
			result.close();
			

		} catch (SQLException e) {
			AlertMessages.ErrorBox(
					e.getMessage(),"SQL EXCEPTION");

			e.printStackTrace();
		}
		
		return volList;
	}

}
