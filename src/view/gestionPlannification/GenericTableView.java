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

public class GenericTableView extends JFrame {

	   private JTable table;
	   private Object[] selectedData;
	   private JButton button;
	   


	public GenericTableView(String title) {
	      setTitle(title);
	      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	      
	      table = new JTable();
	      table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	      button = new JButton("SELECT");
	      button.setBounds(52, 255, 400, 29);
	      getContentPane().setLayout(null);
	      
	      JScrollPane scrollPane = new JScrollPane(table);
	      scrollPane.setBounds(0, 0, 502, 251);
	      getContentPane().add(scrollPane);
	      getContentPane().add(button);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setSize(502, 312);
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
		return button;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setSelectedData(Object[] selectedData) {
		this.selectedData = selectedData;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

}
