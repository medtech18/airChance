package view.gestionPlannification;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SupressionVolView extends JFrame {

	   private JTable table;
	   private Object[] selectedData;
	   private JButton btnDeleteVol;
	   


	public SupressionVolView(String title) {
	      setTitle(title);
	      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	      this.setResizable(false);
	      table = new JTable();
	      table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	      getContentPane().setLayout(null);
	      
	      JScrollPane scrollPane = new JScrollPane(table);
	      scrollPane.setBounds(0, 0, 750, 268);
	      getContentPane().add(scrollPane);
	      
	      btnDeleteVol = new JButton("delete this vol");
	      btnDeleteVol.setBounds(149, 273, 333, 29);
	      getContentPane().add(btnDeleteVol);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setSize(756, 330);
	      setLocationRelativeTo(null);
	      setVisible(true);
	}
	
	public static int generateRandomInt(int upperRange){
	    Random random = new Random();
	    return random.nextInt(upperRange);
	}

	public JTable getTable() {
		return table;
	}

	public Object[] getSelectedData() {
		return selectedData;
	}


	public void setTable(JTable table) {
		this.table = table;
	}

	public void setSelectedData(Object[] selectedData) {
		this.selectedData = selectedData;
	}


	public JButton getBtnDeleteVol() {
		return btnDeleteVol;
	}
	public void setBtnDeleteVol(JButton btnDeleteVol) {
		this.btnDeleteVol = btnDeleteVol;
	}

}
