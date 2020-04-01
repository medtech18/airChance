package view.gestionReservationViews;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.classes.AeroPort;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;


public class ReserverVolView extends JFrame {
	private JTable table;
	private JOptionPane jop;
	private JButton btnreserver;
	private JButton btnpanier;
	private JButton btnRechercher;
	private JComboBox<AeroPort> cbxarrive;
	private JComboBox<AeroPort> cbxdepart;
	private JFormattedTextField txtDate;
	private JScrollPane scrollPane;


	
	public ReserverVolView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 208, 653, 282);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		
		btnreserver = new JButton("reserver dans ce vol");
		btnreserver.setEnabled(false);
		btnreserver.setBounds(306, 529, 184, 23);
		contentPane.add(btnreserver);
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		txtDate = new JFormattedTextField(format);
		txtDate.setBounds(265, 56, 225, 21);
		contentPane.add(txtDate);
		txtDate.setValue(new Date());
		txtDate.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setBounds(190, 59, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Depart");
		lblNewLabel_1.setBounds(190, 92, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Destination");
		lblNewLabel_2.setBounds(190, 125, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		cbxdepart = new JComboBox<AeroPort>();
		cbxdepart.setBounds(265, 88, 225, 22);
		contentPane.add(cbxdepart);
		
		cbxarrive = new JComboBox<AeroPort>();
		cbxarrive.setBounds(265, 121, 225, 22);
		contentPane.add(cbxarrive);
		
		btnRechercher = new JButton("recherche vol");
		btnRechercher.setBounds(305, 161, 89, 23);
		contentPane.add(btnRechercher);
		
		btnpanier = new JButton("panier");
		btnpanier.setBounds(661, 83, 89, 23);
		contentPane.add(btnpanier);
		jop = new JOptionPane();
	}

	

	
	public void setBtnDetail(JButton btnDetail) {
		this.btnreserver = btnDetail;
	}



	public JOptionPane getJop() {
		return jop;
	}



	public void setJop(JOptionPane jop) {
		this.jop = jop;
	}





	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}




	public JButton getBtnreserver() {
		return btnreserver;
	}



	public JButton getBtnpanier() {
		return btnpanier;
	}



	public JButton getBtnRechercher() {
		return btnRechercher;
	}



	public JComboBox<AeroPort> getCbxarrive() {
		return cbxarrive;
	}



	public JComboBox<AeroPort> getCbxdepart() {
		return cbxdepart;
	}



	public JFormattedTextField getTxtDate() {
		return txtDate;
	}



	public void setBtnreserver(JButton btnreserver) {
		this.btnreserver = btnreserver;
	}



	public void setBtnpanier(JButton btnpanier) {
		this.btnpanier = btnpanier;
	}



	public void setBtnNewButton(JButton btnNewButton) {
		this.btnRechercher = btnNewButton;
	}



	public void setCbxarrive(JComboBox cbxarrive) {
		this.cbxarrive = cbxarrive;
	}



	public void setCbxdepart(JComboBox cbxdepart) {
		this.cbxdepart = cbxdepart;
	}



	public void setTxtDate(JFormattedTextField txtDate) {
		this.txtDate = txtDate;
	}
}
