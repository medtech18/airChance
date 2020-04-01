package controller.gestionReservationControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.common.AlertMessages;
import controller.common.VolTableModel;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoReservation;
import model.classeDAO.DaoVol;
import model.classes.AeroPort;
import model.classes.Client;
import model.classes.Place;
import model.classes.PlaceReserver;
import model.classes.Reservation;
import model.classes.Vol;
import view.gestionReservationViews.PanierView;
import view.gestionReservationViews.ReservationPlaceView;
import view.gestionReservationViews.ReserverVolView;

public class ReserverVolController {
	 ReserverVolView reservationview;
	private DaoReservation daoreservation=new DaoReservation();
	private DaoVol daoVol=new DaoVol();
	private Client client=new Client();
	private Reservation newReservation;
	private DaoAeroport daoAeroport=new DaoAeroport();
	private ArrayList<Vol> vols=new ArrayList<Vol>();
	private ArrayList<PlaceReserver> panier=new ArrayList<PlaceReserver>();
	
	ReserverVolController r=this;

	public ReserverVolController(ReserverVolView reservationview,Client client) {
		super();
		this.client=client;
		this.reservationview = reservationview;
		newReservation=new Reservation(1,new Date(System.currentTimeMillis()), client,0);
		ArrayList<AeroPort> aeroports=daoAeroport.selectAll();
		for(AeroPort a:aeroports) {
			reservationview.getCbxarrive().addItem(a);
			reservationview.getCbxdepart().addItem(a);
			
		}
		
		reservationview.getBtnRechercher().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
				Date dvol;
				reservationview.getBtnreserver().setEnabled(true);                                                      
				try {
					
					dvol = new Date(dfFormat.parse(reservationview.getTxtDate().getText()).getTime());
					
					System.out.println(dfFormat.parse(reservationview.getTxtDate().getText()));
					
					vols=daoVol.selectbyCritere(dvol,(AeroPort) reservationview.getCbxdepart().getSelectedItem(),(AeroPort) reservationview.getCbxarrive().getSelectedItem());
						if(vols.size()==0)
						{
							JOptionPane.showMessageDialog(null, "aucun vol compatible!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						}else
						{
							VolTableModel modele=new VolTableModel(vols);
							reservationview.getTable().setModel(modele);
							reservationview.getTable().setAutoCreateRowSorter(true);
							reservationview.getBtnreserver().setEnabled(true);
						}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		reservationview.getBtnreserver().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reservationview.getTable().getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "aucun vol selectionee!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				}else {
					Vol selectedVol=((VolTableModel) reservationview.getTable().getModel()).getValue(reservationview.getTable().getSelectedRow());
					ReservationPlaceView reserVolView=new ReservationPlaceView();
					reserVolView.setVisible(true);
					new ReserverPlaceVolController(reserVolView,selectedVol,r,newReservation);
				}
				
			}
		});
		
		reservationview.getBtnpanier().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanierView panierView=new PanierView();
				panierView.setVisible(true);
				new PanierController(panierView, panier,client,r,newReservation);
			}
		});
		
	}
	
	
	
	public ArrayList<PlaceReserver> getPanier() {
		return panier;
	}

	public void setPanier(ArrayList<PlaceReserver> panier) {
		this.panier = panier;
	}



	public ReserverVolView getReservationview() {
		return reservationview;
	}



	public void setReservationview(ReserverVolView reservationview) {
		this.reservationview = reservationview;
	}



}
