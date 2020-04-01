package controller.gestionPlannification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.common.AlertMessages;
import controller.common.AvionTableModel;
import controller.common.PersonnelTableModel;
import controller.common.VolTableModel;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoPersonnel;
import model.classeDAO.DaoPersonnelVol;
import model.classeDAO.DaoVol;
import model.classes.AeroPort;
import model.classes.Avion;
import model.classes.Personnel;
import model.classes.PersonnelVol;
import model.classes.Vol;
import view.gestionPlannification.GenericTableView;
import view.gestionPlannification.ModficationPersonnelVolView;
import view.gestionPlannification.ModificationVolView;

public class ModficationPersonnelVolController {

	// Models
	private ModficationPersonnelVolView modficationPersonnelVolView;
	private DaoPersonnel personnelModel;
	private DaoPersonnelVol personnelVolModel;
	private DaoVol volModel;
	private DaoAeroport aeroPortModel;

	// Views
	private GenericTableView modificationVolView;
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
	private boolean typeOpAjoute = true;

	// Generic Jtable models
	private PersonnelTableModel personnelTableModel;
	private VolTableModel volTableModel;
	
		
	public ModficationPersonnelVolController(ModficationPersonnelVolView modficationPersonnelVolView, DaoVol volModel, DaoAeroport aeroPortModel,DaoPersonnel personnelModel) {
		// TODO Auto-generated constructor stub
		this.modficationPersonnelVolView = modficationPersonnelVolView;
		this.personnelModel = personnelModel;
		this.volModel = volModel;
		this.aeroPortModel = aeroPortModel;	
		this.volModel = volModel;
		this.modificationVolView = new GenericTableView("Choix DE VOL");
		this.modificationVolView.setVisible(false);
		this.modificationVolView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.personnelMenuView = new GenericTableView("Perosnnel Menu View");
		this.personnelMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.personnelMenuView.setVisible(false);
		this.personnelModel = personnelModel;
		this.personnelVolModel = new DaoPersonnelVol();
		this.selectedPilots = new ArrayList<Personnel>();
		this.selectedHotesses = new ArrayList<Personnel>();
		this.selectedAeroPortDep = new AeroPort();
		this.selectedAeroPortDest = new AeroPort();
		this.inputsValues = new HashMap<String, Object>();
		this.personnelTableModel = new PersonnelTableModel(this.personnels);
		this.volTableModel	= new VolTableModel(this.vols);
		this.tempSelectedPersonnel = new ArrayList<Personnel>();
		modficationPersonnelVolView.getComboxSelectionPersonnel().setVisible(true);
		modficationPersonnelVolView.getComboxSelectionPersonnel().setEnabled(false);


		
		createListeners();

//		createListenersPersonnelMenuView();
//		createListenersVolView();
	}
	
	

		// TODO Auto-generated constructor stub


	public void createListeners()
	{
		modficationPersonnelVolView.getBtnAjouter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(typeOpAjoute)
				{
					for( Personnel obj : tempSelectedPersonnel)
					{
						PersonnelVol PersonnelVol = personnelVolModel.insert(new PersonnelVol(obj,selectedVol));
						if(PersonnelVol == null)
						{
							AlertMessages.InfoBox("ECHEC INSERSION DU VOL ET PERSO" + selectedVol.getNumVol(), "ECHEC INSERSION");

						}else
						{
							AlertMessages.InfoBox("VOL ET PERSO INSERE AVEC SUCCES" + selectedVol.getNumVol(), "SUCCES INSERSION");

						}
					}
				}else
				{
					
					for( Personnel obj : tempSelectedPersonnel)
					{
						personnelVolModel.delete(new PersonnelVol(obj,selectedVol));

					}	
					
				}
			}
		});
		modficationPersonnelVolView.getComboBoxTypeOp().addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if(modficationPersonnelVolView.getComboBoxTypeOp().getSelectedIndex() == 0)
					{
						typeOpAjoute = true;
						modficationPersonnelVolView.getBtnAjouter().setText("Ajouter");
						modficationPersonnelVolView.getComboxSelectionPersonnel().setVisible(true);

					}else
					{
						typeOpAjoute = false;
						modficationPersonnelVolView.getBtnAjouter().setText("Supprimer");
						modficationPersonnelVolView.getComboxSelectionPersonnel().setVisible(false);
						

					}
				}
			}
		});
		
		modficationPersonnelVolView.getComboxSelectionVol().addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				vols = volModel.selectAll();
				createListenersVolView();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			});
		
	
		
		modficationPersonnelVolView.getComboxSelectionPersonnel().addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
//		    	try {// if is number
				personnels = personnelModel.selectAll();
				createListenersPersonnelMenuView();
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			});

	}
	
	public void createListenersVolView() {
		this.modificationVolView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.modificationVolView.setVisible(true);
		
		if(!typeOpAjoute)
		{
			vols = volModel.selectAll();
			
		}else
		{
			vols = personnelVolModel.selectAllPersonnelVols();
		}
		modificationVolView.getTable().setModel(new VolTableModel(vols));
		modificationVolView.getTable().setAutoCreateRowSorter(true);

		this.modificationVolView.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				// check for selected row first
				if (modificationVolView.getTable().getSelectedRow() != -1) {

					int row = modificationVolView.getTable().getSelectedRow();
					VolTableModel tempModel = (VolTableModel) modificationVolView.getTable().getModel();

					selectedVol = tempModel.getValue(row);
					
					modficationPersonnelVolView.getComboxSelectionVol().removeAllItems();
					modficationPersonnelVolView.getComboxSelectionVol().addItem(selectedVol);
					modficationPersonnelVolView.getComboxSelectionPersonnel().setVisible(true);
					modficationPersonnelVolView.getComboxSelectionPersonnel().setEnabled(true);

					
			}
				modificationVolView.dispose();
			}
		});

	}
	
	public void createListenersPersonnelMenuView() {

		
		this.personnelMenuView.setVisible(true);
		this.personnelMenuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		if(!typeOpAjoute)
		{
			this.personnels = personnelModel.selectAllVOLPersonnelWith(selectedVol.getNumVol());

		}else
		{
			this.personnels = personnelModel.getPersonnelWith(selectedVol.getDateVol(),selectedVol.getAeroportDepart(),"  PERSONNEL ");
		}
		
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
					
					modficationPersonnelVolView.getComboxSelectionPersonnel().removeAllItems();
					modficationPersonnelVolView.getComboxSelectionPersonnel().addItem(personnelMenuView.getTable().getSelectedRows().length + " Selected");


					personnelMenuView.dispose();
				}
			}

		};

		
		this.personnelMenuView.getButton().addActionListener(perMenuViewListener);

	}

	
	

}
