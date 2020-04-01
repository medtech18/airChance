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
import model.classes.Vol;
import view.gestionPlannification.GenericTableView;
import view.gestionPlannification.ModificationVolView;
import view.gestionPlannification.PlannificationVolView;
import view.gestionPlannification.TerminaisonVolView;

public class SupressionVolController {

	
	
	
	// Models
	private DaoVol volModel;

	// Views
	private TerminaisonVolView volMenuView;

	// Data variables
	private ArrayList<Vol> vols;

	private Vol selectedVol;
	// Generic Jtable models
	private VolTableModel volTableModel;


	public SupressionVolController(TerminaisonVolView volMenuView, DaoVol volModel) {
		this.volMenuView = volMenuView;
		this.volModel = volModel;
		vols = new ArrayList<Vol>();
		
		this.vols = volModel.getNonTerminatedVols();

		VolTableModel obj = new VolTableModel(vols);
		System.out.println(obj.getValue(0));
		volMenuView.getTable().setModel(obj);

		
	
	}
	

	public void createListenersPlannificationVolView() {
		// perfom some action when Next button is clicked

		volMenuView.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check for selected row first

					if (volMenuView.getTable().getSelectedRows().length > 0) {
						VolTableModel tempModel = (VolTableModel) volMenuView.getTable().getModel();

						for (int nRow : volMenuView.getTable().getSelectedRows()) {
							 selectedVol = tempModel.getValue(nRow);
							 selectedVol.setTerminaison(true);
							 volModel.modify(selectedVol);
						}
						
					}				

			}
		});
	}

	public VolTableModel getVolTableModel() {
		return volTableModel;
	}

	public void setVolTableModel(VolTableModel volTableModel) {
		this.volTableModel = volTableModel;
	}
}
