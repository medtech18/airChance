import controller.gestionPlannification.PlannificationVolController;
import gestionReservationControllers.consultationReservationController;
import gestionReservationViews.consultationReservationView;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoPersonnel;
import model.classeDAO.DaoVol;
import view.gestionPlannification.AvionMenuView;
import view.gestionPlannification.PlannificationVolView;

	public class App {
	    public static void main(String[] args) {
	    	
	    	
	    	
	    	// gestion plannification
	    	 
	    	 DaoVol volModel  	 		= new DaoVol() ;
	    	 DaoAvion avionModel 		= new DaoAvion() ;
	    	 DaoAeroport aeroPortModel  = new DaoAeroport();
	    	 DaoPersonnel personnelModel  = new DaoPersonnel();
	    	 
//	    	 PlannificationVolView plannificationVolView = new PlannificationVolView();
//	    	 plannificationVolView.setVisible(true);
//	    	 AvionMenuView avionMenuView = new AvionMenuView();
//	    	 avionMenuView.setVisible(false);
//	    	 
//	    	 PlannificationVolController volController = new PlannificationVolController(plannificationVolView, avionMenuView , volModel,  avionModel ,  aeroPortModel , personnelModel);
//	    	
//	    	 
	    	 consultationReservationView reservationView=new consultationReservationView();
	    	 reservationView.setVisible(true);
	    	 consultationReservationController consultationController=new consultationReservationController(reservationView);
	    	 // gestion resrvation
	    	 
	    	 
	    	 
	    }
}
