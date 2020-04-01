package view.gestionPlannification;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class ModficationPersonnelVolView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	
	private JComboBox ComboxSelectionVol;

	private JComboBox ComboxSelectionPersonnel;
	private JComboBox comboBoxTypeOp;
	private JButton btnAjouter;
	private JButton btnAnnuler;
	
	public ModficationPersonnelVolView(String title) {
		this.setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxTypeOp = new JComboBox();
		comboBoxTypeOp.setModel(new DefaultComboBoxModel(new String[] {"Ajouter", "Supprimer"}));
		comboBoxTypeOp.setBounds(28, 32, 128, 27);
		contentPane.add(comboBoxTypeOp);
		
		JLabel lblNewLabel = new JLabel("Type D'operation");
		lblNewLabel.setBounds(6, 16, 118, 16);
		contentPane.add(lblNewLabel);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(80, 92, 151, 34);
		contentPane.add(btnAjouter);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(274, 92, 151, 34);
		contentPane.add(btnAnnuler);
		
		ComboxSelectionVol = new JComboBox();
		ComboxSelectionVol.setModel(new DefaultComboBoxModel(new String[] {""}));
		ComboxSelectionVol.setBounds(346, 32, 134, 27);
		contentPane.add(ComboxSelectionVol);
		
		 ComboxSelectionPersonnel = new JComboBox();
		 ComboxSelectionPersonnel.setModel(new DefaultComboBoxModel(new String[] {"", "A"}));
		ComboxSelectionPersonnel.setBounds(182, 32, 128, 27);
		contentPane.add(ComboxSelectionPersonnel);
		
		JLabel lblNewLabel_2 = new JLabel("Selectionner Vol");
		lblNewLabel_2.setBounds(186, 16, 101, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblSelectionnerPersonnel = new JLabel("Selectionner Personnel");
		lblSelectionnerPersonnel.setBounds(346, 16, 172, 16);
		contentPane.add(lblSelectionnerPersonnel);
	}

	public JComboBox getComboxSelectionPersonnel() {
		return ComboxSelectionVol;
	}

	public JComboBox getComboxSelectionVol() {
		return ComboxSelectionPersonnel;
	}

	public JComboBox getComboBox() {
		return comboBoxTypeOp;
	}

	public JButton getBtnAjouter() {
		return btnAjouter;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public void setComboxSelectionPersonnel(JComboBox comboxSelectionPersonnel) {
		ComboxSelectionVol = comboxSelectionPersonnel;
	}

	public void setComboxSelectionVol(JComboBox comboxSelectionVol) {
		ComboxSelectionPersonnel = comboxSelectionVol;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBoxTypeOp = comboBox;
	}

	public void setBtnAjouter(JButton btnAjouter) {
		this.btnAjouter = btnAjouter;
	}

	public void setBtnAnnuler(JButton btnAnnuler) {
		this.btnAnnuler = btnAnnuler;
	}
	public JComboBox getComboBoxTypeOp() {
		return comboBoxTypeOp;
	}

	public void setComboBoxTypeOp(JComboBox comboBoxTypeOp) {
		this.comboBoxTypeOp = comboBoxTypeOp;
	}

}
