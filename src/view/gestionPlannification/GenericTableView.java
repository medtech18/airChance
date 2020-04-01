package view.gestionPlannification;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GenericTableView extends JFrame {

	   private JTable table;
	   private JButton button;
	   


	public GenericTableView(String title) {
	      setTitle(title);
	      setBounds(100, 100, 718, 626);
	      
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      table = new JTable();
	      table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	      button = new JButton("SELECT");
	      button.setBounds(143, 266, 400, 29);
	      getContentPane().setLayout(null);
	      
	      JScrollPane scrollPane = new JScrollPane(table);
	      scrollPane.setBounds(0, 0, 705, 251);
	      getContentPane().add(scrollPane);
	      getContentPane().add(button);
	      setSize(707, 343);
	      setLocationRelativeTo(null);
	      setAlwaysOnTop(true);
	}


	public JTable getTable() {
		return table;
	}


	public JButton getButton() {
		return button;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
