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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableModel;

import controller.common.AlertMessages;
import controller.common.AvionTableModel;
import controller.common.ComboxItem;
import controller.common.PersonnelTableModel;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoPersonnel;
import model.classeDAO.DaoVol;
import model.classes.Adresse;
import model.classes.AeroPort;
import model.classes.Avion;
import model.classes.Model;
import model.classes.Personnel;
import model.classes.PersonnelVol;
import model.classes.Vol;
import view.gestionPlannification.GenericTableView;
import view.gestionPlannification.PlannificationVolView;

public class PlannificationVolController {

	
	
	
	// Models
	private DaoVol volModel;
	private DaoAvion avionModel;
	private DaoAeroport AeroPortModel;
	private DaoPersonnel personnelModel;

	// Views
	private PlannificationVolView plannificationVolView;
	private GenericTableView avionMenuView;
	private GenericTableView personnelMenuView;

	// Data variables
	private ArrayList<Avion> avions;
	private ArrayList<AeroPort> aeroPorts;
	private Avion selectedAvion;
	private ArrayList<Personnel> selectedPilots;
	private ArrayList<Personnel> selectedHotesses;
	private AeroPort selectedAeroPortDep;
	private AeroPort selectedAeroPortDest;


	private ArrayList<Personnel> personnels;
	private Map<String, Object> inputsValues;
	private ArrayList<Personnel> tempSelectedPersonnel;
	private ActionListener perMenuViewListener;
	private boolean actionFromPiloteBtn;

	// Generic Jtable models
	private PersonnelTableModel personnelTableModel;
	private AvionTableModel avionTableModel;

	public PlannificationVolController(PlannificationVolView plannificationVolView, DaoVol volModel, DaoAvion avionModel, DaoAeroport aeroPortModel,
			DaoPersonnel personnelModel) {
		this.volModel = volModel;
		this.avionModel = avionModel;
		this.plannificationVolView = plannificationVolView;
		this.avionMenuView = new GenericTableView("Choix D'avion");
		this.avionMenuView.setVisible(false);
		this.avionMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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


		fetchDataFromModel();
		createListenersPlannificationVolView();
		createListenersPersonnelMenuView();
			
	}

	public void fetchDataFromModel() {

		aeroPorts = AeroPortModel.selectAll();
		for (AeroPort element : aeroPorts) {
			plannificationVolView.getComboBoxAeroDep().addItem(element);
			plannificationVolView.getComboBoxAeroDest().addItem(element);

			System.out.println(element);

		}

	}

	public void createListenersPlannificationVolView() {
		// perfom some action when Next button is clicked
		plannificationVolView.getNextBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {// if is number
					Date dvol;
					SimpleDateFormat dfFormat = new SimpleDateFormat("dd/MM/YYYY HH:MM");  
					dvol = new Date(dfFormat.parse(plannificationVolView.getTextFieldDateVol().getText()).getTime());

					inputsValues.put("textFieldDateVol", dvol);
					inputsValues.put("comboBoxAeroDep", plannificationVolView.getComboBoxAeroDep().getSelectedItem());
					inputsValues.put("comboBoxAeroDest", plannificationVolView.getComboBoxAeroDest().getSelectedItem());
					inputsValues.put("textFieldDuree",
							Double.valueOf(plannificationVolView.getTextFieldDuree().getText()));
					inputsValues.put("distanceVol",
							Double.valueOf(plannificationVolView.getTextFieldDistance().getText()));
					inputsValues.put("editTextPlaceAff",
							Integer.valueOf(plannificationVolView.getEditTextPlaceAff().getText()));
					inputsValues.put("editTextPlacePrem",
							Integer.valueOf(plannificationVolView.getEditTextPlacePrem().getText()));
					inputsValues.put("editTextPlaceEco",
							Integer.valueOf(plannificationVolView.getEditTextPlaceEco().getText()));
					inputsValues.put("textFieldDistance", Double.valueOf(plannificationVolView.getTextFieldDistance().getText()));
					setFieldsState(false);
				} catch (NumberFormatException e1) {
					// else then do blah
					setFieldsState(true);
					AlertMessages.ErrorBox(
							"Error on the fields , Either you left empty fields or you typed characters on number fields ",
							"Input Error");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		

		plannificationVolView.getBtnValiderAff().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				setFieldsState(false);
				plannificationVolView.getComboBoxNumAvion().setEnabled(false);
				plannificationVolView.getBtnChoixPilot().setEnabled(false);
				plannificationVolView.getBtnChoixHotesse().setEnabled(false);
				
				volModel.insert( new Vol(
							0000,
							(Date)inputsValues.get("textFieldDateVol"),
							selectedAeroPortDest,
							selectedAeroPortDep,
							((Double)inputsValues.get("textFieldDuree")).floatValue(),
							((Double)inputsValues.get("textFieldDistance")).floatValue(),
							false ,
							selectedAvion,
							(int)inputsValues.get("editTextPlaceEco"),
							(int)inputsValues.get("editTextPlacePrem"),
							(int)inputsValues.get("editTextPlaceAff")
							)
						);
				
				personnels = personnelModel.selectAll();
				personnelTableModel.setRowObjects(personnels);
				personnelMenuView.getTable().setModel(personnelTableModel);
				personnelMenuView.getTable().repaint();


				
				
		
			}
		});
		
		plannificationVolView.getBtnBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFieldsState(true);
				plannificationVolView.getTextFieldDateVol().setValue(new java.util.Date());
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
					if(plannificationVolView.getComboBoxAeroDep().getSelectedItem() != null)
					selectedAeroPortDep = (AeroPort) plannificationVolView.getComboBoxAeroDep().getSelectedItem();
					;

				}
			}
		});

		
		plannificationVolView.getComboBoxAeroDest().addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if(plannificationVolView.getComboBoxAeroDest().getSelectedItem() != null)
					selectedAeroPortDest = (AeroPort) plannificationVolView.getComboBoxAeroDest().getSelectedItem();
					;

				}
			}
		});

		plannificationVolView.getComboBoxNumAvion().addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
//		    	try {// if is number
				avions = avionModel.getAvionsWith((int) inputsValues.get("editTextPlaceEco"),
						(int) inputsValues.get("editTextPlacePrem"), (int) inputsValues.get("editTextPlaceAff"),
						(Double) inputsValues.get("textFieldDistance"));

				createListenersAvionView();

			}

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
		});

		plannificationVolView.getBtnChoixPilot().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionFromPiloteBtn = true;
				personnels = personnelModel.getPersonnelWith((Date)inputsValues.get("textFieldDateVol"), selectedAeroPortDep," pilots ");

				personnelTableModel.setRowObjects(personnels);
				personnelMenuView.getTable().setModel(personnelTableModel);
				personnelMenuView.getTable().setAutoCreateRowSorter(true);
				personnelMenuView.setVisible(true);

			}
		});

		plannificationVolView.getBtnChoixHotesse().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personnels = personnelModel.getPersonnelWith((Date)inputsValues.get("textFieldDateVol"), selectedAeroPortDep," hotesse ");
				personnelTableModel.setRowObjects(personnels);
				personnelMenuView.getTable().setModel(personnelTableModel);
				personnelMenuView.getTable().setAutoCreateRowSorter(true);

				actionFromPiloteBtn = false;
				personnelMenuView.setVisible(true);
			}
		});

	}

	public void createListenersAvionView() {

		this.avionMenuView.setVisible(true);
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

					plannificationVolView.getComboBoxNumAvion().removeAllItems();
					plannificationVolView.getComboBoxNumAvion().addItem(selectedAvion);
					
					plannificationVolView.getComboBoxNumAvion().setEnabled(false);
					plannificationVolView.getBtnChoixHotesse().setEnabled(true);
					plannificationVolView.getBtnChoixPilot().setEnabled(true);


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
						plannificationVolView.getLabelChoixPilot().setText("Vous avez selection "
								+ personnelMenuView.getTable().getSelectedRows().length + " pilote(s)");
						selectedPilots = tempSelectedPersonnel;
					}

					// action to apply on hotesses

					if (!actionFromPiloteBtn) {
						plannificationVolView.getLabelChoixHotesse().setText("Vous avez selection "
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

	public PlannificationVolView getPlannificationVolView() {
		return plannificationVolView;
	}

	public void setPlannificationVolView(PlannificationVolView plannificationVolView) {
		this.plannificationVolView = plannificationVolView;
	}

	public void setSelectedAvion(Avion selectedAvion) {
		this.selectedAvion = selectedAvion;
	}

	public void setFieldsState(boolean state) {
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

	public boolean empty(String value) {
		return (value == null || (value.length() == 0)) ? true : false;
	}

	public PlannificationVolController getPlannificationVolController() {
		return this;
	}

	public Map<String, Object> getInputsValues() {
		return inputsValues;
	}

	public void setInputsValues(Map<String, Object> inputsValues) {
		this.inputsValues = inputsValues;
	}

}