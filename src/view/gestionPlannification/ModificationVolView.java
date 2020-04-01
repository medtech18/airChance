package view.gestionPlannification;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.classes.AeroPort;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class ModificationVolView extends JFrame {

	private JTable table;
	private Object[] selectedData;
	private JButton btnChoisirUnVol;

	private JScrollPane scrollPane;
	private JLabel label;
	private JFormattedTextField TextFieldDateVol;
	private JFormattedTextField textFieldDuree;
	private JFormattedTextField textFieldDistance;
	private JFormattedTextField editTextPlaceAff;
	private JFormattedTextField editTextPlacePrem;
	private JFormattedTextField editTextPlaceEco;
	private JComboBox comboBoxNumAvion;
	private JComboBox<AeroPort> comboBoxAeroDest;
	private JComboBox<AeroPort> comboBoxAeroDep;
	private JButton btnChoixPilot;
	private JButton btnChoixHotesse;
	private JLabel labelChoixPilot;
	private JLabel labelChoixHotesse;
	private JButton nextBtn;
	private JButton btnAnnuler;
	private  JButton btnValiderModfi;

	public JButton getBtnValiderModfi() {
		return btnValiderModfi;
	}

	public void setBtnValiderModfi(JButton btnValiderModfi) {
		this.btnValiderModfi = btnValiderModfi;
	}

	public ModificationVolView(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		table = new JTable();
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		btnChoisirUnVol = new JButton("CHOISIR UN VOL");
		btnChoisirUnVol.setBounds(39, 320, 400, 29);
		getContentPane().setLayout(null);
      
        JScrollPane scrollPane = new JScrollPane(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 583, 319);
		getContentPane().add(scrollPane);
		getContentPane().add(btnChoisirUnVol);

		label = new JLabel("Date de Vol");
		label.setBounds(611, 28, 113, 16);
		getContentPane().add(label);

	    DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		TextFieldDateVol = new JFormattedTextField(format);
		TextFieldDateVol.setBounds(763, 23, 151, 26);
		TextFieldDateVol.setFocusLostBehavior(JFormattedTextField.PERSIST);
		getContentPane().add(TextFieldDateVol);

		JLabel label_1 = new JLabel("Aeroport depart");
		label_1.setBounds(611, 54, 124, 16);
		getContentPane().add(label_1);

		comboBoxAeroDep = new JComboBox<AeroPort>();
		comboBoxAeroDep.setEnabled(false);
		comboBoxAeroDep.setBounds(763, 50, 158, 27);
		getContentPane().add(comboBoxAeroDep);

		JLabel label_2 = new JLabel("Aeroport destination");
		label_2.setBounds(611, 83, 135, 16);
		getContentPane().add(label_2);

		comboBoxAeroDest = new JComboBox<AeroPort>();
		comboBoxAeroDest.setEnabled(false);
		comboBoxAeroDest.setBounds(763, 79, 158, 27);
		getContentPane().add(comboBoxAeroDest);

		JLabel label_3 = new JLabel("Duree de Vol");
		label_3.setBounds(611, 119, 124, 16);
		getContentPane().add(label_3);

		textFieldDuree = new JFormattedTextField();
		textFieldDuree.setEnabled(false);
		textFieldDuree.setBounds(763, 111, 151, 26);
		textFieldDuree.setColumns(10);
		getContentPane().add(textFieldDuree);

		JLabel label_4 = new JLabel("Distance de Vol");
		label_4.setBounds(611, 147, 113, 16);
		getContentPane().add(label_4);

		textFieldDistance = new JFormattedTextField();
		textFieldDistance.setEnabled(false);
		textFieldDistance.setBounds(763, 139, 151, 26);
		textFieldDistance.setColumns(10);
		getContentPane().add(textFieldDistance);

		JLabel label_5 = new JLabel("Nombre Place Affaire");
		label_5.setBounds(611, 175, 135, 16);
		getContentPane().add(label_5);

		editTextPlaceAff = new JFormattedTextField();
		editTextPlaceAff.setEnabled(false);
		editTextPlaceAff.setBounds(763, 167, 151, 26);
		editTextPlaceAff.setColumns(10);
		getContentPane().add(editTextPlaceAff);

		JLabel label_6 = new JLabel("Nombre Place Premium");
		label_6.setBounds(611, 203, 158, 16);
		getContentPane().add(label_6);

		editTextPlacePrem = new JFormattedTextField();
		editTextPlacePrem.setEnabled(false);
		editTextPlacePrem.setBounds(763, 198, 151, 26);
		editTextPlacePrem.setColumns(10);
		getContentPane().add(editTextPlacePrem);

		JLabel label_7 = new JLabel("Nombre Place ECO");
		label_7.setBounds(611, 228, 158, 16);
		getContentPane().add(label_7);

		editTextPlaceEco = new JFormattedTextField();
		editTextPlaceEco.setEnabled(false);
		editTextPlaceEco.setBounds(763, 223, 151, 26);
		editTextPlaceEco.setColumns(10);
		getContentPane().add(editTextPlaceEco);

		JLabel label_8 = new JLabel("Choix D'avion");
		label_8.setBounds(951, 28, 124, 16);
		getContentPane().add(label_8);

		comboBoxNumAvion = new JComboBox();
		comboBoxNumAvion.setEnabled(false);
		comboBoxNumAvion.setBounds(944, 50, 151, 26);
		getContentPane().add(comboBoxNumAvion);

		btnChoixPilot = new JButton("Select La Liste Pilote");
		btnChoixPilot.setEnabled(false);
		btnChoixPilot.setBounds(940, 98, 183, 29);
		getContentPane().add(btnChoixPilot);

		labelChoixPilot = new JLabel("vous n'avez choisi aucun(e) pilote");
		labelChoixPilot.setBounds(943, 139, 236, 16);
		getContentPane().add(labelChoixPilot);

		btnChoixHotesse = new JButton("Select La Liste Hotesse");
		btnChoixHotesse.setEnabled(false);
		btnChoixHotesse.setBounds(940, 170, 183, 29);
		getContentPane().add(btnChoixHotesse);

		labelChoixHotesse = new JLabel("vous n'avez choisi aucun(e) Hotesse");
		labelChoixHotesse.setBounds(943, 208, 231, 16);
		getContentPane().add(labelChoixHotesse);

		nextBtn = new JButton("NEXT");
		nextBtn.setEnabled(false);
		nextBtn.setBounds(634, 297, 135, 37);
		getContentPane().add(nextBtn);

		btnAnnuler = new JButton("ANNULER");
		btnAnnuler.setBounds(1035, 297, 124, 37);
		getContentPane().add(btnAnnuler);
		
		 btnValiderModfi = new JButton("Valider Modfication");
		btnValiderModfi.setBounds(832, 297, 135, 37);
		getContentPane().add(btnValiderModfi);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 377);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JButton getBtnChoisirUnVol() {
		return btnChoisirUnVol;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JLabel getLabel() {
		return label;
	}

	public JFormattedTextField getTextFieldDateVol() {
		return TextFieldDateVol;
	}

	public JFormattedTextField getTextFieldDuree() {
		return textFieldDuree;
	}

	public JFormattedTextField getTextFieldDistance() {
		return textFieldDistance;
	}

	public JFormattedTextField getEditTextPlaceAff() {
		return editTextPlaceAff;
	}

	public JFormattedTextField getEditTextPlacePrem() {
		return editTextPlacePrem;
	}

	public JFormattedTextField getEditTextPlaceEco() {
		return editTextPlaceEco;
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

	public JButton getNextBtn() {
		return nextBtn;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}


	public void setBtnChoisirUnVol(JButton btnChoisirUnVol) {
		this.btnChoisirUnVol = btnChoisirUnVol;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public void setTextFieldDateVol(JFormattedTextField textFieldDateVol) {
		TextFieldDateVol = textFieldDateVol;
	}

	public void setTextFieldDuree(JFormattedTextField textFieldDuree) {
		this.textFieldDuree = textFieldDuree;
	}

	public void setTextFieldDistance(JFormattedTextField textFieldDistance) {
		this.textFieldDistance = textFieldDistance;
	}

	public void setEditTextPlaceAff(JFormattedTextField editTextPlaceAff) {
		this.editTextPlaceAff = editTextPlaceAff;
	}

	public void setEditTextPlacePrem(JFormattedTextField editTextPlacePrem) {
		this.editTextPlacePrem = editTextPlacePrem;
	}

	public void setEditTextPlaceEco(JFormattedTextField editTextPlaceEco) {
		this.editTextPlaceEco = editTextPlaceEco;
	}

	public void setComboBoxNumAvion(JComboBox comboBoxNumAvion) {
		this.comboBoxNumAvion = comboBoxNumAvion;
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

	public void setNextBtn(JButton nextBtn) {
		this.nextBtn = nextBtn;
	}

	public void setBtnAnnuler(JButton btnAnnuler) {
		this.btnAnnuler = btnAnnuler;
	}


	public static int generateRandomInt(int upperRange) {
		Random random = new Random();
		return random.nextInt(upperRange);
	}

	public JTable getTable() {
		return table;
	}

	public Object[] getSelectedData() {
		return selectedData;
	}

	public JButton getButton() {
		return btnChoisirUnVol;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setSelectedData(Object[] selectedData) {
		this.selectedData = selectedData;
	}

	public void setButton(JButton button) {
		this.btnChoisirUnVol = button;
	}

	public JComboBox getComboBoxAeroDest() {
		return comboBoxAeroDest;
	}

	public JComboBox getComboBoxAeroDep() {
		return comboBoxAeroDep;
	}

	public void setComboBoxAeroDest(JComboBox comboBoxAeroDest) {
		this.comboBoxAeroDest = comboBoxAeroDest;
	}

	public void setComboBoxAeroDep(JComboBox comboBoxAeroDep) {
		this.comboBoxAeroDep = comboBoxAeroDep;
	}
}
