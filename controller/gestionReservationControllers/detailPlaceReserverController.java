package gestionReservationControllers;

import gestionReservationViews.detailPlaceReserverView;
import model.classeDAO.DaoPlaceReserver;
import model.classes.Client;
import model.classes.Reservation;
import model.classes.Vol;
import modelTables.tableModelPlaceReserver;

public class detailPlaceReserverController {
	detailPlaceReserverView view;
	Client client;
	Vol selectedvol;
	DaoPlaceReserver placeReserver=new DaoPlaceReserver();
	public detailPlaceReserverController(detailPlaceReserverView view, Reservation reserv, Vol selectedVol) {
		super();
		this.view = view;
		this.client=client;
		this.selectedvol=selectedVol;
		placeReserver.selectbyreservation(reserv, selectedVol);
		tableModelPlaceReserver model=new tableModelPlaceReserver(placeReserver.selectbyreservation(reserv, selectedVol));
		view.getTable().setModel(model);
		view.getTable().setAutoCreateRowSorter(true);
	}
	

}
