package ConnectionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MyConnection {

	String server;
	int port;
	String User;
	String Password;
	String Database;
	String jdbcurl;
	Connection con;
	
	public MyConnection() {
		server = "USER-PC\\SQLExpressRogi";
		port = 1433;
		User = "sa"; //Instruction for developer who wants to run this project:
			//Enter the username for SQL server for the current computer
		
		Password = "seselj217"; //Instruction for developer who wants to run this project:
						//Enter  the password for SQL server for current computer
		Database = "VirtualGambling";
		jdbcurl = "jdbc:sqlserver://" + server + ":" + port+";" + "user=" + User + ";" + "Password=" + Password + ";" +
		"databaseName=" + Database + ";";
	}
	
	public Connection getconn() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//notes: Requires that you initialize a driver so you can open a communications channel with the database.
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(jdbcurl);
			//notes: con is instance of Class Connection, and it's represents physical connection to the base on server
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(con!=null)System.out.println("Succesfuly connected to database.");
		else JOptionPane.showMessageDialog(null, "There is some problem. Try later.");
		return con;
		
	}
	
}

