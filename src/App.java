import javax.swing.JFrame;

import controller.gestionPlannification.ModficationPersonnelVolController;
import controller.gestionPlannification.ModificationVolController;
import controller.gestionPlannification.PlannificationVolController;
import controller.gestionPlannification.TerminaisonVolController;
import controller.gestionReservationControllers.ConsultationReservationController;
import model.classeDAO.DaoAeroport;
import model.classeDAO.DaoAvion;
import model.classeDAO.DaoPersonnel;
import model.classeDAO.DaoVol;
import model.connection.OracleConnection;
import view.gestionPlannification.GenericTableView;
import view.gestionPlannification.ModficationPersonnelVolView;
import view.gestionPlannification.ModificationVolView;
import view.gestionPlannification.PlannificationVolView;
import view.gestionPlannification.TerminaisonVolView;
import view.gestionReservationViews.ConsultationReservationView;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class App extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	
	private JButton un;
	private JButton deux;
	private JButton trois;
	private JButton quatre;
	private JButton cinq;
	private JButton six;
	private JButton btnConnectDB;
	
	
	public App() {
		
		getContentPane().setLayout(null);
		this.setResizable(false);
		
		un = new JButton("Modifier personnel d'un Vol");
		un.setBounds(22, 71, 188, 88);
		getContentPane().add(un);
		
		 deux = new JButton("Modifier Un Vol");
		 deux.setBounds(232, 71, 202, 88);
		getContentPane().add(deux);
		
		JLabel lblNewLabel = new JLabel("Gestion de plannification");
		lblNewLabel.setBounds(34, 32, 319, 27);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 19));
		getContentPane().add(lblNewLabel);
		
		 trois = new JButton("Plannifier Un Nouveau Vol");
		 trois.setBounds(458, 71, 212, 88);
		getContentPane().add(trois);
		
		 quatre = new JButton("Supression D'un Vol");
		 quatre.setBounds(22, 175, 188, 78);
		getContentPane().add(quatre);
		
		 cinq = new JButton("Terminasion D'un Vol");
		 cinq.setBounds(232, 174, 202, 78);
		getContentPane().add(cinq);
		
		JLabel lblGestionDeReservation = new JLabel("Gestion de Reservation");
		lblGestionDeReservation.setBounds(34, 328, 319, 27);
		lblGestionDeReservation.setFont(new Font("Lucida Grande", Font.ITALIC, 19));
		getContentPane().add(lblGestionDeReservation);
		
		JButton six = new JButton("Consultation de Reservation");
		six.setBounds(44, 375, 188, 88);
		getContentPane().add(six);
		
		JLabel lblConnexionBd = new JLabel("Connexion BD");
		lblConnexionBd.setBounds(538, 299, 132, 23);
		getContentPane().add(lblConnexionBd);
		lblConnexionBd.setFont(new Font("Lucida Grande", Font.ITALIC, 19));
		
		textField = new JTextField();
		textField.setBounds(538, 339, 130, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(538, 368, 132, 26);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setBounds(447, 344, 79, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setBounds(447, 373, 79, 16);
		getContentPane().add(lblNewLabel_2);
		
		btnConnectDB = new JButton("Connect");
		btnConnectDB.setBounds(538, 404, 117, 29);
		getContentPane().add(btnConnectDB);
		
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setSize(800, 612);
	      setLocationRelativeTo(null);
	      setVisible(true);
		
		btnConnectDB.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				OracleConnection.setCredentials(textField.getText(),passwordField.getText());
				if(OracleConnection.getInstance() != null)
				{
					createListeners();
				}
			}
		});
	}
	
	void createListeners()
	{
		
		// plannification VOL
		DaoVol volModel = new DaoVol();
		DaoAvion avionModel = new DaoAvion();
		DaoAeroport aeroPortModel = new DaoAeroport();
		DaoPersonnel personnelModel = new DaoPersonnel();
		
				
		un.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModficationPersonnelVolView modficationPersonnelVolView = new ModficationPersonnelVolView("Modification Personnel de Vol");
				ModficationPersonnelVolController modficationPersonnelVolController = new ModficationPersonnelVolController(modficationPersonnelVolView, volModel, aeroPortModel,personnelModel);
				modficationPersonnelVolView.setVisible(true);
				modficationPersonnelVolView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		deux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificationVolView modificationVolView = new ModificationVolView("Modification de Vol");
				ModificationVolController modificationVolController = new ModificationVolController(modificationVolView, volModel, avionModel, aeroPortModel, personnelModel);
				modificationVolView.setVisible(true);
				modificationVolView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}
		});
		trois.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlannificationVolView plannificationVolView = new PlannificationVolView("Plannification d'un vol ");
				plannificationVolView.setVisible(true);
				PlannificationVolController volController = new PlannificationVolController(plannificationVolView, volModel, avionModel, aeroPortModel, personnelModel);
				plannificationVolView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		quatre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	 ConsultationReservationView reservationView=new ConsultationReservationView();
		    	 ConsultationReservationController consultationController=new ConsultationReservationController(reservationView);
		    	 reservationView.setVisible(true);

			}
		});
		cinq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TerminaisonVolView terminaisonVolView = new TerminaisonVolView("Modification de Vol");
				TerminaisonVolController terminaisonVolController = new TerminaisonVolController(terminaisonVolView,volModel);


			}
		});
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		

	}

	
	public static void main(String[] args) {


		App test = new App();
		test.setVisible(true);

	}
}
