import controller.gestionPlannification.ModificationVolController;
import controller.gestionPlannification.PlannificationVolController;
import controller.gestionReservationControllers.ConsultationReservationController;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoPersonnel;
import model.classeDAO.DaoVol;
import view.gestionPlannification.GenericTableView;
import view.gestionPlannification.ModificationVolView;
import view.gestionPlannification.PlannificationVolView;
import view.gestionReservationViews.ConsultationReservationView;

public class App {
	public static void main(String[] args) {

// gestion plannification

		
		
		// plannification VOL
		DaoVol volModel = new DaoVol();
		DaoAvion avionModel = new DaoAvion();
		DaoAeroport aeroPortModel = new DaoAeroport();
		DaoPersonnel personnelModel = new DaoPersonnel();

//		PlannificationVolView plannificationVolView = new PlannificationVolView();
		GenericTableView avionMenuView = new GenericTableView("Choix D'avion");
		GenericTableView personnelMenuView = new GenericTableView("Choix des pilotes");

		avionMenuView.setVisible(false);
		personnelMenuView.setVisible(false);
//		plannificationVolView.setVisible(true);
//
//		PlannificationVolController volController = new PlannificationVolController(plannificationVolView,
//				avionMenuView, personnelMenuView, volModel, avionModel, aeroPortModel, personnelModel);

		// MODIFICATION VOL
		
//		ModificationVolView modificationVolView = new ModificationVolView("Modification de Vol");
//		ModificationVolController modificationVolController = new ModificationVolController(modificationVolView,avionMenuView, personnelMenuView, volModel, avionModel, aeroPortModel, personnelModel);
//		modificationVolView.setVisible(true);

		

// gestion resrvation

	    	 ConsultationReservationView reservationView=new ConsultationReservationView();
	    	 reservationView.setVisible(true);
	    	 ConsultationReservationController consultationController=new ConsultationReservationController(reservationView);
//

	}
}
