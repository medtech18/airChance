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
 
/**
 * JList basic tutorial and example
 *
 * @author wwww.codejava.net
 */
public class PlannificationVolView extends JFrame {
 
    private JList<String> avionList;
    private JTextField textFieldDuree;
    private JTextField textFieldDistance;
    private JTextField editTextPlaceAff;

	private JTextField editTextPlacePrem;
    private JTextField editTextPlaceEco;
    private JLabel choixPilotLabel;


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
	private JScrollPane piloteChoiceList;
	private DefaultListModel<String> listModel;
 
    public JButton getBtnBack() {
		return btnBack;
	}

	public PlannificationVolView() {
    	setResizable(false);
        //create the model and add elementss

        
        
    	listModel = new DefaultListModel<>();
        getContentPane().setLayout(null);
        piloteChoiceList = new JScrollPane();
        piloteChoiceList.setBounds(370, 156, 200, 139);
        getContentPane().add(piloteChoiceList);
        
        avionList = new JList(listModel);
        avionList.setEnabled(false);

        piloteChoiceList.setRowHeaderView(avionList);
        
         
                //create the list
        //        countryList = new JList<>(listModel);
 
        
        choixPilotLabel = new JLabel("Choisir minimum 2 pilote :");
        choixPilotLabel.setBounds(370, 128, 200, 16);
        getContentPane().add(choixPilotLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Date de Vol");
        lblNewLabel_1.setBounds(15, 73, 113, 16);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblAeroportDepart = new JLabel("Aeroport depart");
        lblAeroportDepart.setBounds(15, 99, 124, 16);
        getContentPane().add(lblAeroportDepart);
        
        JLabel lblAeroportDestination = new JLabel("Aeroport destination");
        lblAeroportDestination.setBounds(15, 128, 135, 16);
        getContentPane().add(lblAeroportDestination);
        
        textFieldDuree = new JTextField();
        textFieldDuree.setColumns(10);
        textFieldDuree.setBounds(167, 156, 130, 26);
        getContentPane().add(textFieldDuree);
        
        JLabel lblDureeDeVol = new JLabel("Duree de Vol");
        lblDureeDeVol.setBounds(15, 164, 124, 16);
        getContentPane().add(lblDureeDeVol);
        
        textFieldDistance = new JTextField();
        textFieldDistance.setColumns(10);
        textFieldDistance.setBounds(167, 184, 130, 26);
        getContentPane().add(textFieldDistance);
        
        JLabel lblDistanceDeVol = new JLabel("Distance de Vol");
        lblDistanceDeVol.setBounds(15, 192, 113, 16);
        getContentPane().add(lblDistanceDeVol);
        
        editTextPlaceAff = new JTextField();
        editTextPlaceAff.setColumns(10);
        editTextPlaceAff.setBounds(167, 212, 130, 26);
        getContentPane().add(editTextPlaceAff);
        
        JLabel lblNombrePlaceAffaire = new JLabel("Nombre Place Affaire");
        lblNombrePlaceAffaire.setBounds(15, 220, 135, 16);
        getContentPane().add(lblNombrePlaceAffaire);
        
        editTextPlacePrem = new JTextField();
        editTextPlacePrem.setColumns(10);
        editTextPlacePrem.setBounds(167, 243, 130, 26);
        getContentPane().add(editTextPlacePrem);
        
        JLabel lblNombrePlacePremium = new JLabel("Nombre Place Premium");
        lblNombrePlacePremium.setBounds(15, 248, 158, 16);
        getContentPane().add(lblNombrePlacePremium);
        
        TextFieldDateVol = new JFormattedTextField();
        TextFieldDateVol.setBounds(167, 68, 130, 26);
        getContentPane().add(TextFieldDateVol);
        
        comboBoxAeroDep = new JComboBox();
        comboBoxAeroDep.setBounds(167, 95, 130, 27);
        getContentPane().add(comboBoxAeroDep);

        comboBoxAeroDest = new JComboBox();
        comboBoxAeroDest.setBounds(167, 124, 130, 27);
        getContentPane().add(comboBoxAeroDest);
        
        JLabel lblNombrePlaceEco = new JLabel("Nombre Place ECO");
        lblNombrePlaceEco.setBounds(15, 273, 158, 16);
        getContentPane().add(lblNombrePlaceEco);
        
        editTextPlaceEco = new JTextField();
        editTextPlaceEco.setColumns(10);
        editTextPlaceEco.setBounds(167, 268, 130, 26);
        getContentPane().add(editTextPlaceEco);
        
        JLabel lblNumAvion = new JLabel("Num Avion");

        lblNumAvion.setBounds(370, 77, 124, 16);
        getContentPane().add(lblNumAvion);
        
        comboBoxNumAvion = new JComboBox();
        comboBoxNumAvion.setEnabled(false);
        comboBoxNumAvion.setBounds(454, 69, 130, 27);
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
        
       
    
 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(627, 466);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
       

    
    public JList<String> getAvionList() {
		return avionList;
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

	public JLabel getChoixPilotLabel() {
		return choixPilotLabel;
	}

	public JScrollPane getPiloteChoiceList() {
		return piloteChoiceList;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public void setPiloteChoiceList(JScrollPane piloteChoiceList) {
		this.piloteChoiceList = piloteChoiceList;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	public void setChoixPilotLabel(JLabel choixPilotLabel) {
		this.choixPilotLabel = choixPilotLabel;
	}


}