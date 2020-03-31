package controller.gestionReservationControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.common.TableModelPlace;
import model.classeDAO.DaoPlace;
import model.classes.Place;
import model.classes.PlaceReserver;
import model.classes.Reservation;
import model.classes.Vol;
import view.gestionReservationViews.ReservationPlaceView;

public class ReserverPlaceVol {
	DaoPlace daoPlace=new DaoPlace();

	public ReserverPlaceVol(ReservationPlaceView placeview,Vol vol,ReserverVolController reservVol,Reservation reservation) {
		ArrayList<Place> places=daoPlace.placedispo(vol);
		placeview.getTable().setModel(new TableModelPlace(places));
		
		placeview.getBtnreserver().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] indexselectedrows=placeview.getTable().getSelectedRows();
				if(indexselectedrows.length==0)
				{
					JOptionPane.showMessageDialog(null, "aucune place selectionee!", "Attention", JOptionPane.INFORMATION_MESSAGE);

				}
				for(int i=0;i<indexselectedrows.length;i++) {
					Place place=((TableModelPlace) placeview.getTable().getModel()).getValue(indexselectedrows[i]);
					PlaceReserver placereserver= new PlaceReserver(reservation, place, vol);
					if (reservVol.getPanier().equals(placereserver)!=true)
					reservVol.getPanier().add(placereserver);	
					
				}
				placeview.dispose();
			}
		});
		
		
		
		
	}

}
