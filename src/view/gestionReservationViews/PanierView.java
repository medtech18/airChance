package view.gestionReservationViews;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;

public class PanierView extends JFrame {

	private JPanel contentPane;
	private JLabel Prix;
	private JComboBox Reduction;
	private JTable table;
	private JButton btnValider;
	private JButton btnAnnuler;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanierView frame = new PanierView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PanierView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 493, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 33, 390, 261);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Reduction = new JComboBox();
		Reduction.setBounds(239, 306, 30, 22);
		contentPane.add(Reduction);
		
		JLabel lblNewLabel = new JLabel("reduction %");
		lblNewLabel.setBounds(167, 310, 62, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prix total:");
		lblNewLabel_1.setBounds(167, 350, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		Prix = new JLabel("");
		Prix.setBounds(239, 350, 49, 14);
		contentPane.add(Prix);
		
		btnValider = new JButton("Valider");
		btnValider.setBounds(122, 381, 89, 23);
		contentPane.add(btnValider);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(260, 381, 89, 23);
		contentPane.add(btnAnnuler);
	}

	public JLabel getPrix() {
		return Prix;
	}

	public JComboBox getReduction() {
		return Reduction;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnValider() {
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public void setPrix(JLabel prix) {
		Prix = prix;
	}

	public void setReduction(JComboBox reduction) {
		Reduction = reduction;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setBtnValider(JButton btnValider) {
		this.btnValider = btnValider;
	}

	public void setBtnAnnuler(JButton btnAnnuler) {
		this.btnAnnuler = btnAnnuler;
	}
}
