package gestionReservationControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gestionReservationViews.consultationReservationView;
import gestionReservationViews.detailPlaceReserverView;
import model.classeDAO.DaoClient;
import model.classeDAO.DaoReservation;
import model.classeDAO.DaoVol;
import model.classes.Client;
import model.classes.Reservation;
import model.classes.Vol;
import modelTables.tablemodelvol;

public class consultationReservationController {
	
	 consultationReservationView cReservationview;
	private DaoReservation reservation;
	private DaoVol vol;
	private Client client=new Client();
	private Reservation selectedReservation;

	public consultationReservationController(consultationReservationView view) {
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
					tablemodelvol modele=new tablemodelvol(vol.selectByReservation(selectedReservation.getNumReservation()));
					cReservationview.getTable().setModel(modele);
					cReservationview.getTable().setAutoCreateRowSorter(true);
				}
				
				
			}
		});
		
		
		cReservationview.getBtnDetail().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cReservationview.getTable().getSelectedRow()==-1)
				{
					cReservationview.getJop().showMessageDialog(null, "aucun vol selectionné!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				}else
				{
					Vol selectedVol=((tablemodelvol) cReservationview.getTable().getModel()).getValue(cReservationview.getTable().getSelectedRow());
					detailPlaceReserverView viewPlaceReserver=new detailPlaceReserverView();
					new detailPlaceReserverController(viewPlaceReserver,selectedReservation,selectedVol);
					viewPlaceReserver.setVisible(true);
					
				}
				
			}
		});

	}
	

}
