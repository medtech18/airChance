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
	   private JButton btnTerminateThisVol;
	   private JButton btnDeleteVol;
	   


	public SupressionVolView(String title) {
	      setTitle(title);
	      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	      this.setResizable(false);
	      table = new JTable();
	      table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	      btnTerminateThisVol = new JButton("Terminate this vol");
	      btnTerminateThisVol.setBounds(10, 273, 400, 29);
	      getContentPane().setLayout(null);
	      
	      JScrollPane scrollPane = new JScrollPane(table);
	      scrollPane.setBounds(0, 0, 750, 268);
	      getContentPane().add(scrollPane);
	      getContentPane().add(btnTerminateThisVol);
	      
	      btnDeleteVol = new JButton("delete this vol");
	      btnDeleteVol.setBounds(406, 273, 333, 29);
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

	public JButton getButton() {
		return btnTerminateThisVol;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setSelectedData(Object[] selectedData) {
		this.selectedData = selectedData;
	}

	public void setButton(JButton button) {
		this.btnTerminateThisVol = button;
	}
	   public JButton getBtnTerminateThisVol() {
		return btnTerminateThisVol;
	}

	public JButton getBtnDeleteVol() {
		return btnDeleteVol;
	}

	public void setBtnTerminateThisVol(JButton btnTerminateThisVol) {
		this.btnTerminateThisVol = btnTerminateThisVol;
	}

	public void setBtnDeleteVol(JButton btnDeleteVol) {
		this.btnDeleteVol = btnDeleteVol;
	}

}
