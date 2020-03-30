package controller.gestionPlannification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.common.ComboxItem;
import controller.common.MySelectionModel;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoPersonnel;
import model.classeDAO.DaoVol;
import model.classes.Aeroport;
import model.classes.Avion;
import model.classes.Model;
import view.gestionPlannification.AvionMenuView;
import view.gestionPlannification.PlannificationVolView;

public class PlannificationVolController {
	private DaoVol volModel ;
	private DaoAvion avionModel ;
	private DaoAeroport AeroPortModel;
	private DaoPersonnel personnelModel;
	private PlannificationVolView plannificationVolView ;
	private AvionMenuView avionMenuView ;
	private Avion selectedAvion;
	


	private Map<String,String> inputsValues;
	
	// Data variables
	private ArrayList<Avion> avions;
	private ArrayList<Aeroport> aeroPorts;
	
	public PlannificationVolController(PlannificationVolView plannificationVolView,AvionMenuView avionMenuView ,DaoVol volModel, DaoAvion avionModel , DaoAeroport aeroPortModel ,DaoPersonnel personnelModel ) {
		this.volModel = volModel;
		this.avionModel = avionModel;
		this.plannificationVolView = plannificationVolView;
		this.avionMenuView = avionMenuView;
		this.AeroPortModel = aeroPortModel;
		this.personnelModel = personnelModel;
		inputsValues = new HashMap<String,String>();
		fetchDataFromModel();
		createListenersPlannificationVolView();
	}
	
	public void fetchDataFromModel()
	{
		
		aeroPorts = AeroPortModel.selectAll();
		for (Aeroport element : aeroPorts) {
			plannificationVolView.getComboBoxAeroDep().addItem(element);
			plannificationVolView.getComboBoxAeroDest().addItem(element);
			
		}
		
	}
	
	public void createListenersPlannificationVolView() {
		// perfom some action when Next button is clicked
		plannificationVolView.getNextBtn().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		inputsValues.put("textFieldDateVol", plannificationVolView.getTextFieldDateVol().getText());
	    		inputsValues.put("comboBoxAeroDep", plannificationVolView.getComboBoxAeroDep().getSelectedItem().toString());
	    		inputsValues.put("comboBoxAeroDest", plannificationVolView.getComboBoxAeroDest().getSelectedItem().toString());
	    		inputsValues.put("textFieldDuree", plannificationVolView.getTextFieldDuree().getText());
	    		inputsValues.put("textFieldDistance", plannificationVolView.getTextFieldDistance().getText());
	    		inputsValues.put("editTextPlaceAff", plannificationVolView.getEditTextPlaceAff().getText());
	    		inputsValues.put("editTextPlacePrem", plannificationVolView.getEditTextPlacePrem().getText());
	    		inputsValues.put("editTextPlaceEco", plannificationVolView.getEditTextPlaceEco().getText());
	    		
	    		boolean isThereEmptyFields = false;
	    		
	    		for (Map.Entry<String, String> ele : inputsValues.entrySet()) {
	    		    if(empty(ele.getValue().toString()) == true)
	    		    	isThereEmptyFields = true;	
	    		}
	    		
	    		if(isThereEmptyFields)
	    			setFieldsState(true);
	    		else
	    			setFieldsState(false);
	    	}
	    });
		
		plannificationVolView.getBtnBack().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {	    		
	    		setFieldsState(true);
	    	}
	    });
		
		plannificationVolView.getBtnAnnuler().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {	    		
	    		plannificationVolView.dispose();
	    	}
	    });
				
		plannificationVolView.getComboBoxAeroDep().addItemListener(new ItemListener() {

			
	    public void itemStateChanged(ItemEvent e) {
	        if (e.getStateChange() == ItemEvent.SELECTED) {
	        	ComboxItem item = (ComboxItem)e.getItem();
	            
	            System.out.println("[ " + (item.getKey())+ "--" + item.getValue()+ "]"); ;
	            
	         }
	    }
		});
		
		plannificationVolView.getComboBoxNumAvion().addPopupMenuListener(new PopupMenuListener()
		{
		    public void popupMenuWillBecomeVisible(PopupMenuEvent e)
		    {
       		 			avions = avionModel.getAvionsWith(
       		 			Integer.valueOf((String) inputsValues.get("editTextPlaceEco")),
       		 			Integer.valueOf((String) inputsValues.get("editTextPlacePrem")),
       		 			Integer.valueOf((String) inputsValues.get("editTextPlaceAff")),
       		 			Integer.valueOf((String) inputsValues.get("textFieldDistance"))
       		 		);
       		 			
       		 		createListenersAvionView();
       		 		
       		 		plannificationVolView.getListModel().addElement("USA");
       		 		plannificationVolView.getListModel().addElement("India");
       		 		plannificationVolView.getListModel().addElement("Vietnam");
       		 		plannificationVolView.getListModel().addElement("Canada");
       		 		plannificationVolView.getListModel().addElement("Denmark");
       				plannificationVolView.getListModel().addElement("France");
       				plannificationVolView.getListModel().addElement("Great Britain");
       				plannificationVolView.getListModel().addElement("Japan"); 
       				plannificationVolView.getListModel().addElement("Canada");
       				plannificationVolView.getListModel().addElement("Denmark");
       				plannificationVolView.getListModel().addElement("France");
       				plannificationVolView.getListModel().addElement("Great Britain");
       				plannificationVolView.getListModel().addElement("Japan");
       		        
//       				plannificationVolView.getAvionList().setSelectionModel(new MySelectionModel(plannificationVolView.getAvionList(), 2));
       				plannificationVolView.getAvionList().setEnabled(true);
		    }

		    public void popupMenuCanceled(PopupMenuEvent e) {}
		    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
		});

	}

	public void createListenersAvionView() {
		this.avionMenuView.setVisible(true); 
//   		AvionMenuView a = new AvionMenuView(plannificationVolView,avions,getPlannificationVolController());		
		this.avionMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		for(Avion ele : avions){
			avionMenuView.getModel().addRow( new Object[]
	        		  		{   ele.getNumAvion(), 
	        		  			ele.getNbrPlaceEco(),
	        		  			ele.getNbrPlacePremiere(),
	        		  			ele.getNbrPlaceAffaire(),
	        		  			ele.getNumModele().getNumModele(),
	        		  			ele.getNumModele().getNomModele(),
	        		  			ele.getNumModele().getNbPiloteMin(),
	        		  			ele.getNumModele().getRayonAction()
	        		  		 });
	      }
		avionMenuView.getButton().addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent ae) {
	         	 
	             // check for selected row first
	             if(avionMenuView.getTable().getSelectedRow() != -1) {
	             	
	             	int column = 0;
	             	int row = avionMenuView.getTable().getSelectedRow();
	             	String value = avionMenuView.getTable().getModel().getValueAt(row, column).toString();

	             	
//	             	selectedData = data.getDataVector().elementAt(table.getSelectedRow());

	                // remove selected row from the model
//	                model.removeRow(table.getSelectedRow());
	             	
	             	plannificationVolView.getEditTextPlaceAff().setText("Ssfdsfddsffds");
	             	plannificationVolView.getComboBoxNumAvion().addItem(value);
	             	
	             	selectedAvion = new Avion(
	             			(int)avionMenuView.getTable().getModel().getValueAt(row, 0),
	             			new Model(avionMenuView.getTable().getModel().getValueAt(row,4).toString(),
	             					  (int)avionMenuView.getTable().getModel().getValueAt(row,3),
	             					  444,
	             					  (int)avionMenuView.getTable().getModel().getValueAt(row,6)
	             					),
	             			(int)avionMenuView.getTable().getModel().getValueAt(row,1),
	             			(int)avionMenuView.getTable().getModel().getValueAt(row,2),122112);
	             	
	             	inputsValues.put("comboBoxNumAvion",value);
	             	plannificationVolView.getChoixPilotLabel().setText("Choisir au minimum " + value + " pilote(s)");
	             }
	             
	             avionMenuView.dispose();
	          }
	       });
	}
	
	public void viewInit()
	{
		
	}
    
	public DaoVol getVolModel() {
		return volModel;
	}
	public void setVolModel(DaoVol volModel) {
		this.volModel = volModel;
	}
	public DaoAvion getAvionModel() {
		return avionModel;
	}
	public void setAvionModel(DaoAvion avionModel) {
		this.avionModel = avionModel;
	}
	public PlannificationVolView getPlannificationVolView() {
		return plannificationVolView;
	}
	public void setPlannificationVolView(PlannificationVolView plannificationVolView) {
		this.plannificationVolView = plannificationVolView;
	}
	public void setSelectedAvion(Avion selectedAvion) {
		this.selectedAvion = selectedAvion;
	}

	public void setFieldsState(boolean state)
	{
    	plannificationVolView.getTextFieldDateVol().setEnabled(state);
    	plannificationVolView.getComboBoxAeroDep().setEnabled(state);
    	plannificationVolView.getComboBoxAeroDest().setEnabled(state);
    	plannificationVolView.getTextFieldDuree().setEnabled(state);
    	plannificationVolView.getTextFieldDistance().setEnabled(state);
    	plannificationVolView.getEditTextPlaceAff().setEnabled(state);
    	plannificationVolView.getEditTextPlacePrem().setEnabled(state);
    	plannificationVolView.getEditTextPlaceEco().setEnabled(state);
    	
    	plannificationVolView.getComboBoxNumAvion().setEnabled(!state);
	}
	
	public boolean empty(String value)
	{
		return (value == null || (value.length() == 0))?true:false;
	}
	public PlannificationVolController getPlannificationVolController()
	{
		return this;
	}

	public Map<String, String> getInputsValues() {
		return inputsValues;
	}

	public void setInputsValues(Map<String, String> inputsValues) {
		this.inputsValues = inputsValues;
	}

	
}
