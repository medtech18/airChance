import javax.swing.JFrame;

import controller.gestionPlannification.ModficationPersonnelVolController;
import controller.gestionPlannification.ModificationVolController;
import controller.gestionPlannification.PlannificationVolController;
import controller.gestionPlannification.TerminaisonVolController;
import controller.gestionReservationControllers.ConsultationReservationController;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoPersonnel;
import model.classeDAO.DaoVol;
import view.gestionPlannification.GenericTableView;
import view.gestionPlannification.ModficationPersonnelVolView;
import view.gestionPlannification.ModificationVolView;
import view.gestionPlannification.PlannificationVolView;
import view.gestionPlannification.TerminaisonVolView;
import view.gestionReservationViews.ConsultationReservationView;

public class App {
	public static void main(String[] args) {

// gestion plannification

		
		
		// plannification VOL
		DaoVol volModel = new DaoVol();
		DaoAvion avionModel = new DaoAvion();
		DaoAeroport aeroPortModel = new DaoAeroport();
		DaoPersonnel personnelModel = new DaoPersonnel();

		GenericTableView avionMenuView = new GenericTableView("Choix D'avion");
		avionMenuView.setVisible(false);
		GenericTableView personnelMenuView = new GenericTableView("Choix des pilotes");
		personnelMenuView.setVisible(false);

//		PlannificationVolView plannificationVolView = new PlannificationVolView("Plannification d'un vol ");
//		plannificationVolView.setVisible(true);
//		PlannificationVolController volController = new PlannificationVolController(plannificationVolView,avionMenuView, personnelMenuView, volModel, avionModel, aeroPortModel, personnelModel);

		// MODIFICATION VOL
		
		
//		ModificationVolView modificationVolView = new ModificationVolView("Modification de Vol");
//		ModificationVolController modificationVolController = new ModificationVolController(modificationVolView,avionMenuView, personnelMenuView, volModel, avionModel, aeroPortModel, personnelModel);
//		modificationVolView.setVisible(true);
	
		
		
		// Ajout superession personnel Vol

//		ModficationPersonnelVolView modficationPersonnelVolView = new ModficationPersonnelVolView(" de Vol PERSONNEL");
//		ModficationPersonnelVolController modficationPersonnelVolController = new ModficationPersonnelVolController(modficationPersonnelVolView, personnelMenuView, volModel, aeroPortModel, personnelModel);
//		modficationPersonnelVolView.setVisible(true);

		// terminaison Vol 
		
		TerminaisonVolView terminaisonVolView = new TerminaisonVolView("Modification de Vol");
		terminaisonVolView.setVisible(true);
		TerminaisonVolController terminaisonVolController = new TerminaisonVolController(terminaisonVolView,volModel);

		
		
// gestion resrvation

//	    	 ConsultationReservationView reservationView=new ConsultationReservationView();
//	    	 reservationView.setVisible(true);
//	    	 ConsultationReservationController consultationController=new ConsultationReservationController(reservationView);


	}
}
