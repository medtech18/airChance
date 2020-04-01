package view.gestionReservationViews;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;


public class ConsultationReservationView extends JFrame {
	private JTable table;
	private JTextField txtIdClient;
	private Button btnRecherche;
	private JComboBox comboBox;
	private JOptionPane jop;
	private JButton btnDetail;
	private JButton btnReserver;
	private JButton btnsupprimer;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ConsultationReservationView() {
		setTitle("consultation de reservation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("ID Client");
		label.setAlignment(Label.CENTER);
		label.setBounds(190, 25, 91, 21);
		contentPane.add(label);
		
		txtIdClient = new JTextField();
		txtIdClient.setBounds(286, 25, 96, 20);
		contentPane.add(txtIdClient);
		txtIdClient.setColumns(10);
		
		btnRecherche = new Button("Rechercher");
		
		btnRecherche.setBounds(423, 25, 67, 21);
		contentPane.add(btnRecherche);
		
		JLabel lblNewLabel = new JLabel("reservation:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(59, 64, 94, 21);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(163, 64, 373, 21);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 130, 653, 360);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		
		btnDetail = new JButton("detail place reserver");
		btnDetail.setEnabled(false);
		btnDetail.setBounds(306, 529, 184, 23);
		contentPane.add(btnDetail);
		
		btnReserver = new JButton("Reserver");
		btnReserver.setEnabled(false);
		btnReserver.setBounds(572, 25, 89, 23);
		contentPane.add(btnReserver);
		
		btnsupprimer = new JButton("Supprimer Reservation");
		btnsupprimer.setEnabled(false);
		btnsupprimer.setBounds(557, 65, 119, 23);
		contentPane.add(btnsupprimer);
		jop = new JOptionPane();
	}

	

	public JButton getBtnDetail() {
		return btnDetail;
	}



	public void setBtnDetail(JButton btnDetail) {
		this.btnDetail = btnDetail;
	}



	public JOptionPane getJop() {
		return jop;
	}



	public void setJop(JOptionPane jop) {
		this.jop = jop;
	}



	public Button getBtnRecherche() {
		return btnRecherche;
	}



	public void setBtnRecherche(Button btnRecherche) {
		this.btnRecherche = btnRecherche;
	}



	public JComboBox getComboBox() {
		return comboBox;
	}



	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}



	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTxtIdClient() {
		return txtIdClient;
	}

	public void setTxtIdClient(JTextField txtIdClient) {
		this.txtIdClient = txtIdClient;
	}



	public JButton getBtnReserver() {
		return btnReserver;
	}



	public void setBtnReserver(JButton btnReserver) {
		this.btnReserver = btnReserver;
	}



	public JButton getBtnsupprimer() {
		return btnsupprimer;
	}



	public void setBtnsupprimer(JButton btnsupprimer) {
		this.btnsupprimer = btnsupprimer;
	}
}
