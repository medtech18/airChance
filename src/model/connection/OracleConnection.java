package model.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controller.common.AlertMessages;
public class OracleConnection {
	
	
	
	private static final String configurationFile = "ressources/BD.properties";
	private static Connection connect;
	private static DataBaseProperties dap = new DataBaseProperties(configurationFile);
	static final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:IM2AG";

	static  String USER = "*********";
	static  String PASSWD = "********";
	static  boolean areCredentialsSet = false;
	public static Connection getInstance() {

		if (connect == null && areCredentialsSet) {
			try {

	 	    // Enregistrement du driver Oracle
	  	    System.out.print("Loading Oracle driver... ");
	        Class.forName ("oracle.jdbc.OracleDriver");
	  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  	    
	  	    System.out.println("loaded");
	  	    
	
		    
	  	    // Etablissement de la connection
	  	  connect = DriverManager.getConnection(CONN_URL, USER,PASSWD);
//	  	    DataBaseProperties dap = new DataBaseProperties(configurationFile);
	  	    System.out.print("Connecting to the database... "); 
//	  	    connect = DriverManager.getConnection(dap.getDatabaseUrl(), dap.getUsername(),dap.getPassword());
	   	    System.out.println("connected");
			AlertMessages.InfoBox("DATABASE SUCCESSFULLY CONNECTED ","CONNECTION SUCCESS");
	   	    connect.setAutoCommit(true);
	   	    
			} catch (ClassNotFoundException e) {
				AlertMessages.ErrorBox(
						e.getMessage(),"SQL EXCEPTION");

//					e.printStackTrace();
			} catch (SQLException e) {
				AlertMessages.ErrorBox(
						e.getMessage(),"SQL EXCEPTION");

				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		return connect;
	}
	
	public static void setCredentials(final String _USER ,final String _PASSWD)
	{
		USER = _USER;
		PASSWD = _PASSWD;
		areCredentialsSet = true;
	}

}
