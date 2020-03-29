package view.gestionPlannification;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AvionMenuView extends JFrame {
	
   private JTable table;
   private DefaultTableModel model;
   private Object[][] data;
   private Object[] selectedData;
   private String[] columnNames;
   private JButton button;
   
   public AvionMenuView() {
      setTitle("Choix D'avion");
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

      columnNames = new String[] {"NUM AVION ", "nb place_eco" , "nb place premiere" , "Num Model", "nom_modele" ,"nb_min_pilote","rayon_action" };
      model = new DefaultTableModel(data, columnNames);
      
      table = new JTable(model);
      table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      button = new JButton("SELECT");

      
      add(new JScrollPane(table), BorderLayout.CENTER);
      add(button, BorderLayout.SOUTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400, 300);
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

	public DefaultTableModel getModel() {
		return model;
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

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public void setSelectedData(Object[] selectedData) {
		this.selectedData = selectedData;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
}