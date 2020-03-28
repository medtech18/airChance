import javax.swing.SwingUtilities;

import controller.gestionPlannification.PlannificationVolController;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoVol;
import view.gestionPlannification.PlannificationVolView;

	public class App {
	    public static void main(String[] args) {
	    	
	    	
	    	
	    	// gestion plannification
	    	 
	    	 DaoVol volModel  	 		= new DaoVol() ;
	    	 DaoAvion avionModel 		= new DaoAvion() ;
	    	 DaoAeroport AeroPortModel  = new DaoAeroport();;
	    	 PlannificationVolView plannificationVolView = new PlannificationVolView();
	    	 
	    	 plannificationVolView.setVisible(true);
	    	 
	    	 PlannificationVolController volController = new PlannificationVolController(plannificationVolView,volModel,avionModel,AeroPortModel);
	    	
	    	 
	    	 
	    	 // gestion resrvation
	    	 
	    	 

	    }
}
