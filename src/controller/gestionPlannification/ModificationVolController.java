package controller.gestionPlannification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableModel;

import controller.common.AlertMessages;
import controller.common.AvionTableModel;
import controller.common.ComboxItem;
import controller.common.PersonnelTableModel;
import controller.common.VolTableModel;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoPersonnel;
import model.classeDAO.DaoPersonnelVol;
import model.classeDAO.DaoVol;
import model.classes.Adresse;
import model.classes.AeroPort;
import model.classes.Avion;
import model.classes.Model;
import model.classes.Personnel;
import model.classes.PersonnelVol;
import model.classes.Vol;
import view.gestionPlannification.GenericTableView;
import view.gestionPlannification.ModificationVolView;
import view.gestionPlannification.ModificationVolView;

public class ModificationVolController {

	
	
	
	// Models
	private DaoVol volModel;
	private DaoAvion avionModel;
	private DaoAeroport AeroPortModel;
	private DaoPersonnel personnelModel;
	private DaoPersonnelVol daoPersonnelVol;

	// Views
	private ModificationVolView modificationVolView;
	private GenericTableView avionMenuView;
	private GenericTableView personnelMenuView;

	// Data variables
	private ArrayList<Avion> avions;
	private ArrayList<AeroPort> aeroPorts;
	private ArrayList<Vol> vols;

	private Avion selectedAvion;
	private Vol selectedVol;
	private ArrayList<Personnel> selectedPilots;
	private ArrayList<Personnel> selectedHotesses;
	private AeroPort selectedAeroPortDep;
	private AeroPort selectedAeroPortDest;

	private ArrayList<Personnel> personnels;
	private Map<String, Object> inputsValues;
	private ArrayList<Personnel> tempSelectedPersonnel;
	private ActionListener perMenuViewListener;
	private ActionListener ViewListener;
	private boolean actionFromPiloteBtn;

	// Generic Jtable models
	private PersonnelTableModel personnelTableModel;
	private AvionTableModel avionTableModel;
	private VolTableModel volTableModel;
	


	public ModificationVolController(ModificationVolView modificationVolView, DaoVol volModel, DaoAvion avionModel, DaoAeroport aeroPortModel,
			DaoPersonnel personnelModel) {
		this.volModel = volModel;
		this.avionModel = avionModel;
		this.modificationVolView = modificationVolView;
		this.personnelMenuView = new GenericTableView("Choix des pilotes");
		this.personnelMenuView.setVisible(false);
		this.personnelMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.AeroPortModel = aeroPortModel;
		this.personnelModel = personnelModel;
		this.selectedPilots = new ArrayList<Personnel>();
		this.selectedHotesses = new ArrayList<Personnel>();
		this.selectedAeroPortDep = new AeroPort();
		this.selectedAeroPortDest = new AeroPort();
		this.inputsValues = new HashMap<String, Object>();
		this.actionFromPiloteBtn = true;
		this.personnelTableModel = new PersonnelTableModel(this.personnels);
		this.avionTableModel = new AvionTableModel(this.avions);
		this.volTableModel	= new VolTableModel(this.vols);
		this.daoPersonnelVol = new DaoPersonnelVol();

		fetchDataFromModel();
		createListenersModificationVolView();
		createListenersPersonnelMenuView();

	}

	public void fetchDataFromModel() {

		aeroPorts = AeroPortModel.selectAll();
		
		for(AeroPort obj : aeroPorts)
		{
			modificationVolView.getComboBoxAeroDep().addItem(obj);
			modificationVolView.getComboBoxAeroDest().addItem(obj);
		}
				
		vols = volModel.selectAll();

//		this.volTableModel.setRowObjects(this.vols);
//		modificationVolView.getTable().setModel(this.volTableModel);
		modificationVolView.getTable().setModel(new VolTableModel(vols));
//		modificationVolView.getTable().setAutoCreateRowSorter(true);


	}

	public void createListenersModificationVolView() {
		// perfom some action when Next button is clicked
		modificationVolView.getNextBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getInputs();
				
				modificationVolView.getBtnChoixHotesse().setEnabled(true);
				modificationVolView.getBtnChoixPilot().setEnabled(true);


//	    		boolean isThereEmptyFields = false;
//	    		
//	    		for (Map.Entry<String, Object> ele : inputsValues.entrySet()) {
//	    		    if(empty(ele.getValue().toString()) == true)
//	    		    	isThereEmptyFields = true;	
//	    		}
//	    		
//	    		if(isThereEmptyFields)
//	    			setFieldsState(true);
//	    		else
//	    			setFieldsState(false);
			}
		});
		
		
		modificationVolView.getBtnValiderModfi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFieldsState(false);
				modificationVolView.getComboBoxNumAvion().setEnabled(false);
				modificationVolView.getBtnChoixPilot().setEnabled(false);
				modificationVolView.getBtnChoixHotesse().setEnabled(false);

				getInputs();
				
				if(selectedAvion == null)
				{
					selectedAvion = avionModel.selectById(selectedVol.getNumAvion().getNumAvion());
				}
				selectedVol.setNumAvion(selectedAvion);
				volModel.modify(selectedVol);
				for(Personnel ele : selectedHotesses)
				{
					daoPersonnelVol.modify(new PersonnelVol(ele,selectedVol));
				}
				for(Personnel ele : selectedPilots)
				{
					daoPersonnelVol.modify(new PersonnelVol(ele,selectedVol));
				}
				
				vols = volModel.selectAll();
				modificationVolView.getTable().setModel(new VolTableModel(vols));
				//modificationVolView.getTable().repaint();

				

			}
		});

		modificationVolView.getBtnAnnuler().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificationVolView.dispose();
			}
		});

		modificationVolView.getComboBoxAeroDep().addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectedAeroPortDep = (AeroPort) modificationVolView.getComboBoxAeroDep().getSelectedItem();
					;

				}
			}
		});
		
		modificationVolView.getComboBoxAeroDest().addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectedAeroPortDest = (AeroPort) modificationVolView.getComboBoxAeroDest().getSelectedItem();
					;

				}
			}
		});

		modificationVolView.getComboBoxNumAvion().addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
//		    	try {// if is number
				avions = avionModel.getAvionsWith((int) inputsValues.get("editTextPlaceEco"),
						(int) inputsValues.get("editTextPlacePrem"), (int) inputsValues.get("editTextPlaceAff"),
						(Double) inputsValues.get("textFieldDistance"));
//				


				createListenersAvionView();

			}

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
		});

		modificationVolView.getBtnChoixPilot().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionFromPiloteBtn = true;
				
				personnels = personnelModel.getPersonnelWith((Date)inputsValues.get("textFieldDateVol"), selectedAeroPortDep," pilots ");

				personnelTableModel.setRowObjects(personnels);
				personnelMenuView.getTable().setModel(personnelTableModel);
				personnelMenuView.getTable().setAutoCreateRowSorter(true);
				personnelMenuView.setVisible(true);
				modificationVolView.getNextBtn().setEnabled(true);


			}
		});

		modificationVolView.getBtnChoixHotesse().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personnels = personnelModel.getPersonnelWith((Date)inputsValues.get("textFieldDateVol"), selectedAeroPortDep," hotesse ");
				personnelTableModel.setRowObjects(personnels);
				personnelMenuView.getTable().setModel(personnelTableModel);
				personnelMenuView.getTable().setAutoCreateRowSorter(true);
				personnelMenuView.setVisible(true);

				actionFromPiloteBtn = false;
				personnelMenuView.setVisible(true);
				modificationVolView.getNextBtn().setEnabled(true);

			}
		});
		
		modificationVolView.getBtnChoisirUnVol().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (modificationVolView.getTable().getSelectedRow() != -1) {
					VolTableModel volTableModel = (VolTableModel) modificationVolView.getTable().getModel();

					int nRow = modificationVolView.getTable().getSelectedRow();
						
						selectedVol = (Vol)volTableModel.getValue(nRow);
						setFieldsState(true);
						try
						{
							selectedAeroPortDep  =  getObjectEqualTo(selectedVol.getAeroportDepart(),aeroPorts);
							selectedAeroPortDest =  getObjectEqualTo(selectedVol.getAeroportArrive(),aeroPorts);
							
							modificationVolView.getTextFieldDateVol().setValue(selectedVol.getDateVol());
							modificationVolView.getComboBoxAeroDep().setSelectedIndex(aeroPorts.indexOf(selectedAeroPortDep));
							modificationVolView.getComboBoxAeroDest().setSelectedIndex(aeroPorts.indexOf(selectedAeroPortDest));
							modificationVolView.getTextFieldDuree().setText(String.valueOf(selectedVol.getDuree()));
							modificationVolView.getTextFieldDistance().setText(String.valueOf(selectedVol.getDistanceVol()));
							modificationVolView.getEditTextPlaceAff().setText(String.valueOf(selectedVol.getnbrMinPlaceAffaire()));
							modificationVolView.getEditTextPlaceEco().setText(String.valueOf(selectedVol.getnbrMinPlaceEco()));
							modificationVolView.getEditTextPlacePrem().setText(String.valueOf(selectedVol.getnbrMinPlacePremiere()));
							modificationVolView.getComboBoxNumAvion().addItem(selectedVol.getNumAvion());
							
//							modificationVolView.getComboBoxAeroDep().addItem(element);
//							modificationVolView.getComboBoxAeroDest().addItem(element);


						} catch (NumberFormatException e1) {
							// else then do blah
							AlertMessages.ErrorBox(
									"Error Converstion de Number vers String , Contact Administrator For technical support ",
									"Input Error");

						}

				}
			}
		});

	}

		
	public void createListenersAvionView() {
		
		avionMenuView = new GenericTableView("Choix D'avion");;
		avionMenuView.setVisible(true);
		this.avionMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.avionTableModel.setRowObjects(this.avions);
		avionMenuView.getTable().setModel(this.avionTableModel);
		avionMenuView.getTable().setAutoCreateRowSorter(true);

		this.avionMenuView.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				// check for selected row first
				if (avionMenuView.getTable().getSelectedRow() != -1) {

					int row = avionMenuView.getTable().getSelectedRow();
					AvionTableModel tempModel = (AvionTableModel) avionMenuView.getTable().getModel();

					selectedAvion = tempModel.getValue(row);

					
					modificationVolView.getComboBoxNumAvion().removeAllItems();
					modificationVolView.getComboBoxNumAvion().addItem(selectedAvion);
					
//					modificationVolView.getComboBoxNumAvion().setEnabled(false);
					modificationVolView.getBtnChoixHotesse().setEnabled(true);
					modificationVolView.getBtnChoixPilot().setEnabled(true);

			}

				avionMenuView.dispose();
			}
		});

	}

	public void createListenersPersonnelMenuView() {

		tempSelectedPersonnel = new ArrayList<Personnel>();
		this.personnelMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.personnels = personnelModel.selectAll();
		this.personnelTableModel.setRowObjects(this.personnels);
		personnelMenuView.getTable().setModel(this.personnelTableModel);
		personnelMenuView.getTable().setAutoCreateRowSorter(true);

		perMenuViewListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				// check for selected row first

				if (personnelMenuView.getTable().getSelectedRows().length > 0) {
					PersonnelTableModel tempModel = (PersonnelTableModel) personnelMenuView.getTable().getModel();

					for (int nRow : personnelMenuView.getTable().getSelectedRows()) {

						tempSelectedPersonnel.add(tempModel.getValue(nRow));
					}

					// action to apply on pilote

					if (actionFromPiloteBtn) {
						modificationVolView.getLabelChoixPilot().setText("Vous avez selection "
								+ personnelMenuView.getTable().getSelectedRows().length + " pilote(s)");
						selectedPilots = tempSelectedPersonnel;
					}

					// action to apply on hotesses

					if (!actionFromPiloteBtn) {
						modificationVolView.getLabelChoixHotesse().setText("Vous avez selection "
								+ personnelMenuView.getTable().getSelectedRows().length + " hotesse(s)");
						selectedHotesses = tempSelectedPersonnel;
						
						
					}

					personnelMenuView.dispose();
				}
			}

		};

		
		this.personnelMenuView.getButton().addActionListener(perMenuViewListener);

	}

	public void viewInit() {

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

	public ModificationVolView getModificationVolView() {
		return modificationVolView;
	}

	public void setModificationVolView(ModificationVolView modificationVolView) {
		this.modificationVolView = modificationVolView;
	}

	public void setSelectedAvion(Avion selectedAvion) {
		this.selectedAvion = selectedAvion;
	}

	public void setFieldsState(boolean state) {
		modificationVolView.getTextFieldDateVol().setEnabled(state);
		modificationVolView.getComboBoxAeroDep().setEnabled(state);
		modificationVolView.getComboBoxAeroDest().setEnabled(state);
		modificationVolView.getTextFieldDuree().setEnabled(state);
		modificationVolView.getTextFieldDistance().setEnabled(state);
		modificationVolView.getEditTextPlaceAff().setEnabled(state);
		modificationVolView.getEditTextPlacePrem().setEnabled(state);
		modificationVolView.getEditTextPlaceEco().setEnabled(state);
		modificationVolView.getComboBoxNumAvion().setEnabled(!state);
		modificationVolView.getNextBtn().setEnabled(state);

	}
	

	public boolean empty(String value) {
		return (value == null || (value.length() == 0)) ? true : false;
	}

	public ModificationVolController getPlannificationVolController() {
		return this;
	}

	public Map<String, Object> getInputsValues() {
		return inputsValues;
	}

	public void setInputsValues(Map<String, Object> inputsValues) {
		this.inputsValues = inputsValues;
	}
	
	public AeroPort getObjectEqualTo(AeroPort obj, ArrayList<AeroPort> arr)
	{
		int index = 0;
		
		
		for(AeroPort e : arr)
		{
			if(e.getNumAeroport() == obj.getNumAeroport())
			{
				return e;
			}
			index++;
		}
		return obj;
		
	}
	
	public void getInputs()
	{
		try {// if is number
			Date dvol;
			SimpleDateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
			dvol = new Date(dfFormat.parse(modificationVolView.getTextFieldDateVol().getText()).getTime());

			inputsValues.put("textFieldDateVol", dvol);
			inputsValues.put("comboBoxAeroDep", modificationVolView.getComboBoxAeroDep().getSelectedItem());
			inputsValues.put("comboBoxAeroDest", modificationVolView.getComboBoxAeroDest().getSelectedItem());
			inputsValues.put("textFieldDuree",Double.valueOf(modificationVolView.getTextFieldDuree().getText()));
			inputsValues.put("editTextPlaceAff",
					Integer.valueOf(modificationVolView.getEditTextPlaceAff().getText()));
			inputsValues.put("editTextPlacePrem",
					Integer.valueOf(modificationVolView.getEditTextPlacePrem().getText()));
			inputsValues.put("editTextPlaceEco",
					Integer.valueOf(modificationVolView.getEditTextPlaceEco().getText()));
			inputsValues.put("textFieldDistance", Double.valueOf(modificationVolView.getTextFieldDistance().getText()));
			setFieldsState(false);
			
			selectedVol.setAeroportArrive(this.selectedAeroPortDest);
			selectedVol.setAeroportDepart(this.selectedAeroPortDep);
			selectedVol.setDateVol(dvol);
			selectedVol.setDuree(((Double)inputsValues.get("textFieldDuree")).floatValue());
			selectedVol.setDistanceVol(((Double)inputsValues.get("textFieldDistance")).floatValue());
			selectedVol.setnbrMinPlaceEco((int)inputsValues.get("editTextPlaceEco"));
			selectedVol.setnbrMinPlacePremiere((int)inputsValues.get("editTextPlacePrem"));
			selectedVol.setnbrMinPlaceAffaire((int)inputsValues.get("editTextPlaceAff"));

		} catch (NumberFormatException e1) {
			// else then do blah
			AlertMessages.ErrorBox(
					"Error on the fields , Either you left empty fields or you typed characters on number fields ",
					"Input Error");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}