package model.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class OracleConnection {
	
	
	
	private static final String configurationFile = "ressources/BD.properties";
	private static Connection connect;
	private static DataBaseProperties dap = new DataBaseProperties(configurationFile);


	public static Connection getInstance() {

		if (connect == null) {
			try {

	 	    // Enregistrement du driver Oracle
	  	    System.out.print("Loading Oracle driver... ");
	        Class.forName ("oracle.jdbc.OracleDriver");
	  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  	    
	  	    System.out.println("loaded");
	  	    
	
		    
	  	    // Etablissement de la connection
	  	    DataBaseProperties dap = new DataBaseProperties(configurationFile);
	  	    System.out.print("Connecting to the database... "); 
	  	    connect = DriverManager.getConnection(dap.getDatabaseUrl(), dap.getUsername(),dap.getPassword());
	   	    System.out.println("connected");
	  	    
	   	    connect.setAutoCommit(true);
	   	    
			} catch (ClassNotFoundException e) {
					e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connect;
	}
	

}
