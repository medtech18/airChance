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

public class ReserverPlaceVolController {
	DaoPlace daoPlace=new DaoPlace();

	public ReserverPlaceVolController(ReservationPlaceView placeview,Vol vol,ReserverVolController reservVol,Reservation reservation) {
		ArrayList<Place> places=daoPlace.placedispo(vol);
		
		for(PlaceReserver p:reservVol.getPanier()) {
			if(p.getNumVol().getNumVol()==vol.getNumVol())
			places.remove(p.getNumPlace());
		}
		if(places.size()==0) {
			placeview.dispose();
			JOptionPane.showMessageDialog(null, "vous avez reservez tout les place disponible dans ce vol!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		}
		
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
					
					if (reservVol.getPanier().contains(placereserver)==false)
					reservVol.getPanier().add(placereserver);	
					
				}
				placeview.dispose();
			}
		});
		
		
		
		
	}

}
