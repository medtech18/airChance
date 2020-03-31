package controller.gestionReservationControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import controller.common.AlertMessages;
import controller.common.VolTableModel;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoReservation;
import model.classeDAO.DaoVol;
import model.classes.AeroPort;
import model.classes.Client;
import model.classes.Reservation;
import model.classes.Vol;
import view.gestionReservationViews.ReserverVolView;

public class ReserverVolController {
	 ReserverVolView reservationview;
	private DaoReservation daoreservation=new DaoReservation();
	private DaoVol daoVol=new DaoVol();
	private Client client=new Client();
	private Reservation newReservation=new Reservation(1,new Date(System.currentTimeMillis()), client);
	private DaoAeroport daoAeroport=new DaoAeroport();
	private ArrayList<Vol> vols=new ArrayList<Vol>();
	
	public ReserverVolController(ReserverVolView reservationview,Client client) {
		super();
		this.client=client;
		this.reservationview = reservationview;
		ArrayList<AeroPort> aeroports=daoAeroport.selectAll();
		for(AeroPort a:aeroports) {
			reservationview.getCbxarrive().addItem(a);
			reservationview.getCbxdepart().addItem(a);
			
		}
		reservationview.getBtnRechercher().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dfFormat = new SimpleDateFormat("dd/MM/YYYY HH:MM");  
				Date dvol;
				try {
					dvol = new Date(dfFormat.parse(reservationview.getTxtDate().getText()).getTime());
					vols=daoVol.selectbyCritere(dvol,(AeroPort) reservationview.getCbxdepart().getSelectedItem(),(AeroPort) reservationview.getCbxarrive().getSelectedItem());
					VolTableModel modele=new VolTableModel(vols);
					reservationview.getTable().setModel(modele);
					reservationview.getTable().setAutoCreateRowSorter(true);
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
	}

}
