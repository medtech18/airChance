package controller.gestionReservationControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.common.TableModelPlaceReserver;
import model.classeDAO.DaoClient;
import model.classeDAO.DaoPlaceReserver;
import model.classeDAO.DaoReservation;
import model.classes.Client;
import model.classes.PlaceReserver;
import model.classes.Reservation;
import view.gestionReservationViews.PanierView;


public class PanierController {
	DaoPlaceReserver daoPlaceReserver=new DaoPlaceReserver();
	DaoReservation daoReservation=new DaoReservation();
	DaoClient daoClient=new DaoClient();
	float prix=0;
	float prixreduc=0;
	
	public PanierController(PanierView panierView,ArrayList<PlaceReserver>PlacesReserver,Client client,ReserverVolController reservVolController, Reservation newReservation) {
		TableModelPlaceReserver model=new TableModelPlaceReserver(PlacesReserver);
		panierView.getTable().setModel(model);
		int point =client.getPointsFidelite()/50;
		for (int i=0;i<=point;i++)
			panierView.getReduction().addItem(i*5+"%");
		prix=daoPlaceReserver.getprix(PlacesReserver);
		newReservation.setPrix_reservation(prix);
		panierView.getPrix().setText(prix+" euro");
		
		panierView.getReduction().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( panierView.getReduction().getSelectedIndex()>-1) {
				prixreduc=prix-(prix*panierView.getReduction().getSelectedIndex()*5/100);
				newReservation.setPrix_reservation(prixreduc);
				panierView.getPrix().setText(prixreduc+" euro");
				}
			}
		});
		
		panierView.getBtnAnnuler().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panierView.dispose();
				reservVolController.getReservationview().dispose();
				
			}
		});
		panierView.getBtnValider().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				daoReservation.insert(newReservation);
				int reductionpoint=panierView.getReduction().getSelectedIndex()*50;
				daoClient.updatePoint(client, reductionpoint);
				for(PlaceReserver p:PlacesReserver) {
					daoPlaceReserver.insert(p);
				}
				JOptionPane.showMessageDialog(null, "reservation valide avec succes!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				panierView.dispose();
				reservVolController.getReservationview().dispose();
				
			}
		});
	}
	
	

}
