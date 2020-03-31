package controller.gestionReservationControllers;

import java.util.ArrayList;

import controller.common.TableModelPlaceReserver;
import model.classes.PlaceReserver;
import view.gestionReservationViews.PanierView;

public class PanierController {
	public PanierController(PanierView panierView,ArrayList<PlaceReserver>PlacesReserver) {
		TableModelPlaceReserver model=new TableModelPlaceReserver(PlacesReserver);
		panierView.getTable().setModel(model);
		
		
		
	}
	

}
