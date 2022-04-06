package es.daumienebi.gestionpeliculas.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbConnection {

	//creates a MYSQL DB connection using the available connector
	//This class will be modified later on
private static Connection Con;
    
    public static Connection connect(){
            
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String urlCon="jdbc:mysql://192.168.0.69:3306/taller";
            Con=DriverManager.getConnection(urlCon, "root", "root");
            Con.setAutoCommit(false);          
            return Con;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "The JDBC library was not found");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error in the database connection");
        }
        return null;
    }
     
    public static Connection getConexion(){
    	return Con != null ? Con : null;
    }
}
