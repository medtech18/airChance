package controller.gestionReservationControllers;

import controller.common.TableModelPlaceReserver;
import model.classeDAO.DaoPlaceReserver;
import model.classes.Client;
import model.classes.Reservation;
import model.classes.Vol;
import view.gestionReservationViews.DetailPlaceReserverView;


public class DetailPlaceReserverController {
	DetailPlaceReserverView view;
	Client client;
	Vol selectedvol;
	DaoPlaceReserver placeReserver=new DaoPlaceReserver();
	public DetailPlaceReserverController(DetailPlaceReserverView view, Reservation reserv, Vol selectedVol) {
		super();
		this.view = view;
		this.client=client;
		this.selectedvol=selectedVol;
		placeReserver.selectbyreservation(reserv, selectedVol);
		TableModelPlaceReserver model=new TableModelPlaceReserver(placeReserver.selectbyreservation(reserv, selectedVol));
		view.getTable().setModel(model);
		view.getTable().setAutoCreateRowSorter(true);
	}

}
