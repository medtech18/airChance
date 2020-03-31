package model.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {
	
	private static final String configurationFile = "BD.properties";

	private static DataBaseProperties dap = new DataBaseProperties(configurationFile);

	private static String jdbcDriver = dap.getJdbcDriver();
	private static String dbUrl = dap.getDatabaseUrl();
	private static String username = dap.getUsername();
	private static String password = dap.getPassword();

	private static Connection connect;

	public static Connection getInstance() {
		if (connect == null) {
			try {
				try {
					Class.forName(jdbcDriver);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				connect = DriverManager.getConnection(dbUrl, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}
	

}
