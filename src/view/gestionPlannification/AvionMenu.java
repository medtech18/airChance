package view.gestionPlannification;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.*;

import model.classes.Aeroport;
import model.classes.Avion;
import model.classes.Model;

public class AvionMenu extends JFrame {
	
   private JTable table;
   private DefaultTableModel model;
   private Object[][] data;
   private Object[] selectedData;
   private String[] columnNames;
   private JButton button;
   
   public AvionMenu(ArrayList<Avion> avionList) {
      setTitle("RemoveSelectedRow Test");
      
		for(int i= 0 ; i < 100 ; i++)
		{
			avionList.add(new Avion(100+i, generateRandomInt(100),generateRandomInt(9000),generateRandomInt(20000),new Model("BOEING",generateRandomInt(3000),generateRandomInt(10),generateRandomInt(1000))));
		}
      
//     data = new Object[][] {{"102", 222,33,44,"500032"}, {"103", 222,33,44,"400032"}, {"104", 22,333,54,"300032"}, {"105", 022,33,44,"900032"}};
      
      columnNames = new String[] {"NUM AVION ", "nb place_eco" , "nb place premiere" , "Num Model", "nom_modele" ,"nb_min_pilote","rayon_action" };
      model = new DefaultTableModel(data, columnNames);
      for(Avion ele : avionList)
      {
          model.addRow( new Object[]
        		  		{   ele.getNumAvion(), 
        		  			ele.getNbrMinPlaceEco(),
        		  			ele.getNbrMinPlacePremiere(),
        		  			ele.getNbrMinPlaceAffaire(),
        		  			ele.getNumModele().getNumModele(),
        		  			ele.getNumModele().getNomModele(),
        		  			ele.getNumModele().getNbPiloteMin(),
        		  			ele.getNumModele().getRayonAction()
        		  		 });
      }
      table = new JTable(model);
      table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      button = new JButton("SELECT");
      button.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent ae) {
            // check for selected row first
            if(table.getSelectedRow() != -1) {
            	
            	int column = 0;
            	int row = table.getSelectedRow();
            	String value = table.getModel().getValueAt(row, column).toString();

            	
//            	selectedData = data.getDataVector().elementAt(table.getSelectedRow());

               // remove selected row from the model
//               model.removeRow(table.getSelectedRow());
               JOptionPane.showMessageDialog(null, "you selected Aviom number : " + value);
            }
         }
      });
      add(new JScrollPane(table), BorderLayout.CENTER);
      add(button, BorderLayout.SOUTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400, 300);
      setLocationRelativeTo(null);
      setVisible(true);
   }
   
   public static void main(String args[]) {
      new AvionMenu(new ArrayList<Avion>());
   }
	public static int generateRandomInt(int upperRange){
	    Random random = new Random();
	    return random.nextInt(upperRange);
	}
}