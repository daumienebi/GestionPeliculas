package es.daumienebi.gestionpeliculas.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import es.daumienebi.gestionpeliculas.config.Configuration;

public class DbConnection {

	//creates a MYSQL DB connection using the available connector
	//This class will be modified later on
private static Connection con;
    
    public static Connection connect(){
        String ip = Configuration.getIp();
        String port = Configuration.getPort();
        String db_user = Configuration.getDb_user();
        String db_password = Configuration.getDb_password();
        try
        {
        	if(Configuration.use_default_connection == 1) {
        		Class.forName("com.mysql.cj.jdbc.Driver");
                String urlCon="jdbc:mysql://192.168.0.69:3306/taller";
                con=DriverManager.getConnection(urlCon, "root", "root");
                con.setAutoCommit(false);          
                return con;
        	}else {
        		Class.forName("com.mysql.cj.jdbc.Driver");
        		String urlCon="jdbc:mysql://"+ip+":"+port+"/moviedb";
                con=DriverManager.getConnection(urlCon, db_user, db_password);
                con.setAutoCommit(false);          
                return con;
        	}            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "The JDBC library was not found");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in the database connection");
        }
        return null;
    }
     
    public static Connection getConexion(){
    	return con != null ? con : null;
    }
}
