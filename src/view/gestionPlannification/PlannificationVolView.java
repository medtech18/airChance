package view.gestionPlannification;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PlannificationVolView extends JFrame {
	private JFormattedTextField textFieldDuree;
	private JFormattedTextField textFieldDistance;
	private JFormattedTextField editTextPlaceAff;

	private JFormattedTextField editTextPlacePrem;
	private JFormattedTextField editTextPlaceEco;

	public JButton getNextBtn() {
		return nextBtn;
	}

	private JButton nextBtn;
	private JButton btnAnnuler;

	private JComboBox comboBoxAeroDep;
	private JComboBox comboBoxAeroDest;
	private JFormattedTextField TextFieldDateVol;
	private JComboBox comboBoxNumAvion;
	private JButton btnBack;
	private JButton btnChoixPilot;
	private JButton btnChoixHotesse;
	private JLabel labelChoixPilot;
	private JLabel labelChoixHotesse;

	public JButton getBtnBack() {
		return btnBack;
	}

	public PlannificationVolView() {
		setResizable(false);
		// create the model and add elementss

		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Date de Vol");
		lblNewLabel_1.setBounds(15, 73, 113, 16);
		getContentPane().add(lblNewLabel_1);

		JLabel lblAeroportDepart = new JLabel("Aeroport depart");
		lblAeroportDepart.setBounds(15, 99, 124, 16);
		getContentPane().add(lblAeroportDepart);

		JLabel lblAeroportDestination = new JLabel("Aeroport destination");
		lblAeroportDestination.setBounds(15, 128, 135, 16);
		getContentPane().add(lblAeroportDestination);

		textFieldDuree = new JFormattedTextField();
		textFieldDuree.setColumns(10);
		textFieldDuree.setBounds(167, 156, 151, 26);
		getContentPane().add(textFieldDuree);

		JLabel lblDureeDeVol = new JLabel("Duree de Vol");
		lblDureeDeVol.setBounds(15, 164, 124, 16);
		getContentPane().add(lblDureeDeVol);

		textFieldDistance = new JFormattedTextField();
		textFieldDistance.setColumns(10);
		textFieldDistance.setBounds(167, 184, 151, 26);
		getContentPane().add(textFieldDistance);

		JLabel lblDistanceDeVol = new JLabel("Distance de Vol");
		lblDistanceDeVol.setBounds(15, 192, 113, 16);
		getContentPane().add(lblDistanceDeVol);

		editTextPlaceAff = new JFormattedTextField();
		editTextPlaceAff.setColumns(10);
		editTextPlaceAff.setBounds(167, 212, 151, 26);
		getContentPane().add(editTextPlaceAff);

		JLabel lblNombrePlaceAffaire = new JLabel("Nombre Place Affaire");
		lblNombrePlaceAffaire.setBounds(15, 220, 135, 16);
		getContentPane().add(lblNombrePlaceAffaire);

		editTextPlacePrem = new JFormattedTextField();
		editTextPlacePrem.setColumns(10);
		editTextPlacePrem.setBounds(167, 243, 151, 26);
		getContentPane().add(editTextPlacePrem);

		JLabel lblNombrePlacePremium = new JLabel("Nombre Place Premium");
		lblNombrePlacePremium.setBounds(15, 248, 158, 16);
		getContentPane().add(lblNombrePlacePremium);

		TextFieldDateVol = new JFormattedTextField();
		TextFieldDateVol.setBounds(167, 68, 151, 26);
		getContentPane().add(TextFieldDateVol);

		comboBoxAeroDep = new JComboBox();
		comboBoxAeroDep.setBounds(167, 95, 158, 27);
		getContentPane().add(comboBoxAeroDep);

		comboBoxAeroDest = new JComboBox();
		comboBoxAeroDest.setBounds(167, 124, 158, 27);
		getContentPane().add(comboBoxAeroDest);

		JLabel lblNombrePlaceEco = new JLabel("Nombre Place ECO");
		lblNombrePlaceEco.setBounds(15, 273, 158, 16);
		getContentPane().add(lblNombrePlaceEco);

		editTextPlaceEco = new JFormattedTextField();
		editTextPlaceEco.setColumns(10);
		editTextPlaceEco.setBounds(167, 268, 151, 26);
		getContentPane().add(editTextPlaceEco);

		JLabel lblNumAvion = new JLabel("Choix D'avion");

		lblNumAvion.setBounds(370, 68, 124, 16);
		getContentPane().add(lblNumAvion);

		comboBoxNumAvion = new JComboBox();
		comboBoxNumAvion.setEnabled(false);
		comboBoxNumAvion.setBounds(370, 95, 151, 26);
		getContentPane().add(comboBoxNumAvion);

		nextBtn = new JButton("NEXT");

		nextBtn.setBounds(217, 354, 183, 37);
		getContentPane().add(nextBtn);

		btnAnnuler = new JButton("ANNULER");
		btnAnnuler.setBounds(424, 354, 183, 37);
		getContentPane().add(btnAnnuler);

		btnBack = new JButton("RESET");
		btnBack.setBounds(15, 354, 183, 37);
		getContentPane().add(btnBack);

		btnChoixPilot = new JButton("Select La Liste Pilote");

		btnChoixPilot.setBounds(370, 156, 183, 29);
		getContentPane().add(btnChoixPilot);

		btnChoixHotesse = new JButton("Select La Liste Hotesse");
		btnChoixHotesse.setBounds(370, 235, 183, 29);
		getContentPane().add(btnChoixHotesse);

		labelChoixPilot = new JLabel("vous n'avez choisi aucun(e) pilote");
		labelChoixPilot.setBounds(365, 189, 213, 16);
		getContentPane().add(labelChoixPilot);

		labelChoixHotesse = new JLabel("vous n'avez choisi aucun(e) Hotesse");
		labelChoixHotesse.setBounds(370, 273, 213, 16);
		getContentPane().add(labelChoixHotesse);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JList Example");
		this.setSize(634, 471);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JTextField getTextFieldDuree() {
		return textFieldDuree;
	}

	public JTextField getTextFieldDistance() {
		return textFieldDistance;
	}

	public JTextField getEditTextPlaceAff() {
		return editTextPlaceAff;
	}

	public JTextField getEditTextPlacePrem() {
		return editTextPlacePrem;
	}

	public JTextField getEditTextPlaceEco() {
		return editTextPlaceEco;
	}

	public JButton getCreerBtn() {
		return nextBtn;
	}

	public void setCreerBtn(JButton creerBtn) {
		this.nextBtn = creerBtn;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public JComboBox getComboBoxAeroDep() {
		return comboBoxAeroDep;
	}

	public JComboBox getComboBoxAeroDest() {
		return comboBoxAeroDest;
	}

	public JFormattedTextField getTextFieldDateVol() {
		return TextFieldDateVol;
	}

	public JComboBox getComboBoxNumAvion() {
		return comboBoxNumAvion;
	}

	public JButton getBtnChoixPilot() {
		return btnChoixPilot;
	}

	public JButton getBtnChoixHotesse() {
		return btnChoixHotesse;
	}

	public JLabel getLabelChoixPilot() {
		return labelChoixPilot;
	}

	public JLabel getLabelChoixHotesse() {
		return labelChoixHotesse;
	}

	public void setBtnChoixPilot(JButton btnChoixPilot) {
		this.btnChoixPilot = btnChoixPilot;
	}

	public void setBtnChoixHotesse(JButton btnChoixHotesse) {
		this.btnChoixHotesse = btnChoixHotesse;
	}

	public void setLabelChoixPilot(JLabel labelChoixPilot) {
		this.labelChoixPilot = labelChoixPilot;
	}

	public void setLabelChoixHotesse(JLabel labelChoixHotesse) {
		this.labelChoixHotesse = labelChoixHotesse;
	}

}