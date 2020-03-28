package controller.gestionPlannification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoVol;
import model.classes.Aeroport;
import model.classes.Avion;
import model.classes.Model;
import view.gestionPlannification.PlannificationVolView;

public class PlannificationVolController {
	private DaoVol volModel ;
	private DaoAvion avionModel ;
	private DaoAeroport AeroPortModel;
	private PlannificationVolView plannificationVolView ;
	
	public PlannificationVolController(PlannificationVolView plannificationVolView ,DaoVol volModel, DaoAvion avionModel , DaoAeroport aeroPortModel) {
		this.volModel = volModel;
		this.avionModel = avionModel;
		this.plannificationVolView = plannificationVolView;
		this.AeroPortModel = aeroPortModel;
		viewInit();
	}
	
	public void viewInit()
	{
		
		ArrayList<Avion> avions = new ArrayList<Avion>();

		for(int i= 0 ; i < 100 ; i++)
		{
			avions.add(new Avion(100+i, generateRandomInt(100),generateRandomInt(9000),generateRandomInt(20000),new Model("BOEING",generateRandomInt(3000),generateRandomInt(10),generateRandomInt(1000))));
		}
		
		
		ArrayList<Aeroport> aeroPorts = new ArrayList<Aeroport>();
		aeroPorts.add(new Aeroport(100,"MOHAMED 5 AEROPORT","CASA","MAROC"));
		aeroPorts.add(new Aeroport(101,"MOHAMED 6 AEROPORT","FES","MAROC"));
		aeroPorts.add(new Aeroport(101,"MOHAMED 7 AEROPORT","AGADIR","MAROC"));
		aeroPorts.add(new Aeroport(103,"MOHAMED 8 AEROPORT","RABAT","MAROC"));
		aeroPorts.add(new Aeroport(103,"MOHAMED 9 AEROPORT","TANGER","MAROC"));
		aeroPorts.add(new Aeroport(103,"MOHAMED 9 AEROPORT","TANGER","MAROC"));
		
		
		
		
//      data = new Object[][] {{}, {"103", 222,33,44,"400032"}, {"104", 22,333,54,"300032"}, {"105", 022,33,44,"900032"}};


//		ArrayList<Aeroport> aeroPorts = AeroPortModel.selectAll();
//		for (Aeroport element : aeroPorts) {
//			plannificationVolView.getComboBoxAeroDep().addItem(new ComboItem(element.getNumAeroport(),element.getNomAeroport()));
//		}
		
		
//		ComboItem[] letters = {new ComboItem("A","NONO"),new ComboItem("B","NONO"),new ComboItem("C","NONO")};
//		for(ComboItem a: letters)
//		plannificationVolView.getComboBoxAeroDep().addItem(a);
//		
//		plannificationVolView.getComboBoxAeroDep().addItemListener(new ItemListener() {
//		    public void itemStateChanged(ItemEvent e) {
//		        if (e.getStateChange() == ItemEvent.SELECTED) {
//		        	ComboItem item = (ComboItem)e.getItem();
//		            
//		            
//		            System.out.println("[ " + (item.getKey())+ "--" + item.getValue()+ "]"); ;
//		            
//		         }
//		    }
//		});
//		        			

//		this.plannificationVolView.getCreerBtn().addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        		
//        	}
//        });
	}
    
	public DaoVol getVolModel() {
		return volModel;
	}
	public void setVolModel(DaoVol volModel) {
		this.volModel = volModel;
	}
	public DaoAvion getAvionModel() {
		return avionModel;
	}
	public void setAvionModel(DaoAvion avionModel) {
		this.avionModel = avionModel;
	}
	public PlannificationVolView getPlannificationVolView() {
		return plannificationVolView;
	}
	public void setPlannificationVolView(PlannificationVolView plannificationVolView) {
		this.plannificationVolView = plannificationVolView;
	}
	
	public static int generateRandomInt(int upperRange){
	    Random random = new Random();
	    return random.nextInt(upperRange);
	}
	
    public class ComboItem
    {
        private String key;
        private String value;

        public ComboItem( String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return value;
        }

        public String getKey()
        {
            return key;
        }

        public String getValue()
        {
            return value;
        }
    }
	
	
}
