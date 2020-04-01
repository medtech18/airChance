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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class App extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	
	private JButton un;
	private JButton deux;
	private JButton trois;
	private JButton quatre;
	private JButton cinq;
	private JButton btnConnectDB;
	private JButton btnGestionnerReservation;
	private ImageIcon img1 = new ImageIcon("ressources/img1.png");
	private ImageIcon img2 = new ImageIcon("ressources/img2.png");
	private ImageIcon img3 = new ImageIcon("ressources/img3.png");
	private JLabel lblNewLabel_3;

	
	public App() {
		
		getContentPane().setLayout(null);
		this.setResizable(false);
		
		un = new JButton("Modifier personnel d'un Vol");
		un.setEnabled(false);
		un.setBounds(22, 71, 188, 88);
		getContentPane().add(un);
		
		 deux = new JButton("Modifier Un Vol");
		 deux.setEnabled(false);
		 deux.setBounds(232, 71, 202, 88);
		getContentPane().add(deux);
		
		JLabel lblNewLabel = new JLabel("Gestion de plannification");
		lblNewLabel.setBounds(22, 32, 319, 27);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 19));
		getContentPane().add(lblNewLabel);
		
		 trois = new JButton("Plannifier Un Nouveau Vol");
		 trois.setEnabled(false);
		 trois.setBounds(458, 71, 212, 88);
		getContentPane().add(trois);
		
		 quatre = new JButton("Supression D'un Vol");
		 quatre.setEnabled(false);
		 quatre.setBounds(22, 175, 188, 78);
		getContentPane().add(quatre);
		
		 cinq = new JButton("Terminasion D'un Vol");
		 cinq.setEnabled(false);
		 cinq.setBounds(232, 174, 202, 78);
		getContentPane().add(cinq);
		
		JLabel lblGestionDeReservation = new JLabel("Gestion de Reservation");
		lblGestionDeReservation.setBounds(22, 343, 319, 27);
		lblGestionDeReservation.setFont(new Font("Lucida Grande", Font.ITALIC, 19));
		getContentPane().add(lblGestionDeReservation);
		
		JLabel lblConnexionBd = new JLabel("Connexion BD");
		lblConnexionBd.setBounds(525, 345, 132, 23);
		getContentPane().add(lblConnexionBd);
		lblConnexionBd.setFont(new Font("Lucida Grande", Font.ITALIC, 19));
		
		textField = new JTextField();
		textField.setBounds(525, 385, 130, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(525, 414, 132, 26);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME");
		lblNewLabel_1.setBounds(434, 390, 79, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setBounds(434, 419, 79, 16);
		getContentPane().add(lblNewLabel_2);
		
		btnConnectDB = new JButton("Connect");
		btnConnectDB.setBounds(525, 450, 117, 29);
		getContentPane().add(btnConnectDB);
		
		btnGestionnerReservation = new JButton("Gerer Reservation");
		btnGestionnerReservation.setEnabled(false);
		btnGestionnerReservation.setBounds(34, 379, 188, 78);
		getContentPane().add(btnGestionnerReservation);
		
		
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
					  un.setEnabled(true);
					  deux.setEnabled(true);
					  trois.setEnabled(true);
					  quatre.setEnabled(true);
					  cinq.setEnabled(true);
					  btnGestionnerReservation.setEnabled(true);
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

			}
		});
		cinq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TerminaisonVolView terminaisonVolView = new TerminaisonVolView("Modification de Vol");
				TerminaisonVolController terminaisonVolController = new TerminaisonVolController(terminaisonVolView,volModel);


			}
		});
		
		btnGestionnerReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	 ConsultationReservationView reservationView=new ConsultationReservationView();
		    	 ConsultationReservationController consultationController=new ConsultationReservationController(reservationView);
		    	 reservationView.setVisible(true);

			}
		});
		

	}

	
	public static void main(String[] args) {


		App test = new App();
		test.setVisible(true);

	}
}
