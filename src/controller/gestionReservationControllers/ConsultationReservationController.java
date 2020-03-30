package controller.gestionReservationControllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.common.Tablemodelvol;
import model.classeDAO.DaoClient;
import model.classeDAO.DaoReservation;
import model.classeDAO.DaoVol;
import model.classes.Client;
import model.classes.Reservation;
import model.classes.Vol;
import view.gestionReservationViews.ConsultationReservationView;
import view.gestionReservationViews.DetailPlaceReserverView;


public class ConsultationReservationController {
	
	 ConsultationReservationView cReservationview;
	private DaoReservation reservation;
	private DaoVol vol;
	private Client client=new Client();
	private Reservation selectedReservation;

	public ConsultationReservationController(ConsultationReservationView view) {
		this.cReservationview = view;
		reservation=new DaoReservation();
		vol=new DaoVol();
		
		cReservationview.getBtnRecherche().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cReservationview.getComboBox().removeAllItems();
				cReservationview.getBtnDetail().setEnabled(false);
				client=DaoClient.selectById(Integer.parseInt(cReservationview.getTxtIdClient().getText()));
				if(client.getNumClient()==-1)
					cReservationview.getJop().showMessageDialog(null, "client inexistant!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				else
				{
					ArrayList<Reservation> listreservation=reservation.selectByclient(client);
					if(listreservation.size()==0)
						cReservationview.getJop().showMessageDialog(null, "pas de reservation pour ce client!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					else
					{
						
						for(Reservation r:listreservation)
						cReservationview.getComboBox().addItem(r);
						cReservationview.getComboBox().setEnabled(true);
						cReservationview.getBtnDetail().setEnabled(true);
					}
				}
				
					
				
			}
		}); 
		cReservationview.getComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( cReservationview.getComboBox().getSelectedItem()!=null) {
					selectedReservation=(Reservation) cReservationview.getComboBox().getSelectedItem();
					Tablemodelvol modele=new Tablemodelvol(vol.selectByReservation(selectedReservation.getNumReservation()));
					cReservationview.getTable().setModel(modele);
					cReservationview.getTable().setAutoCreateRowSorter(true);
				}
				
				
			}
		});
		
		
		cReservationview.getBtnDetail().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cReservationview.getTable().getSelectedRow()==-1)
				{
					cReservationview.getJop().showMessageDialog(null, "aucun vol selectionn�!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				}else
				{
					Vol selectedVol=((Tablemodelvol) cReservationview.getTable().getModel()).getValue(cReservationview.getTable().getSelectedRow());
					DetailPlaceReserverView viewPlaceReserver=new DetailPlaceReserverView();
					new DetailPlaceReserverController(viewPlaceReserver,selectedReservation,selectedVol);
					viewPlaceReserver.setVisible(true);
					
				}
				
			}
		});

	}
	

}