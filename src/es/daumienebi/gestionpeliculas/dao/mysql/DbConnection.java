package es.daumienebi.gestionpeliculas.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import es.daumienebi.gestionpeliculas.config.Configuration;
import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;

public class DbConnection {
	private static Connection con;
/*

static String ip = Configuration.ip;
static String port = Configuration.port;
static String db_name = Configuration.db_name;
static String db_user = Configuration.db_user;
static String db_password = Configuration.db_password;
*/
	

	public static Connection connect(){        
        try
        {
        	//defaultConnection();
        	userConnection();
        	
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "The JDBC library was not found");
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null,"Error in the database connection, please check your settings.","Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
	}

	public static Connection defaultConnect() {       
        try
        {
        	defaultConnection();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "The JDBC library was not found");
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null,"Error in the database connection, please check your settings.","Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
}
    
	private static Connection defaultConnection() throws ClassNotFoundException, SQLException {
    	Class.forName("org.mariadb.jdbc.Driver");
		//Class.forName("com.mysql.cj.jdbc.Driver");
		String urlCon="jdbc:mariadb://"+DefaultConfiguration.ip+":"+DefaultConfiguration.port+"/"+DefaultConfiguration.db_name;
        con=DriverManager.getConnection(urlCon, DefaultConfiguration.db_user, DefaultConfiguration.db_password);
        con.setAutoCommit(false);          
        return con;
    }
    
    
    private static Connection userConnection() throws ClassNotFoundException, SQLException {
    	Class.forName("org.mariadb.jdbc.Driver");
		//Class.forName("com.mysql.cj.jdbc.Driver");
		String urlCon="jdbc:mariadb://"+Configuration.ip+":"+Configuration.port+"/"+Configuration.db_name;
        con=DriverManager.getConnection(urlCon, Configuration.db_user, Configuration.db_password);
        con.setAutoCommit(false);          
        return con;
    }
    
     
    public static Connection getConnection(){
    	return con != null ? con : null;
    }
    
    public static void setToNull(){
    	con = null;
    }
    
    public static void closeConnection() {
    	try {
			con.close();
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, "Error closing the connection");
		}
    }
}
