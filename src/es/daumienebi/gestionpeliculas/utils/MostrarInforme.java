package es.daumienebi.gestionpeliculas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.loading.PrivateClassLoader;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.dao.mysql.DbConnection;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class MostrarInforme {	
		
	/**
	 * 
	 * @param rutaInforme - The route where the report is stored
	 */
	public static void mostrar(String rutaInforme) {
		try {
			//Compile the report
			JasperReport report = JasperCompileManager.compileReport(rutaInforme);
			
			//Fill the report
			JasperPrint viewer = JasperFillManager.fillReport(report,null,DbConnection.getConnection());
				
			//View the report
			JasperViewer.viewReport(viewer,false);
			System.out.println("Where is the viewer");
				
		}catch (JRException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	private Connection getConnection() throws ClassNotFoundException,SQLException{
		String ip      = DefaultConfiguration.ip;
		String db_user = DefaultConfiguration.db_user;
		String db_name = DefaultConfiguration.db_name;
		String pass    = DefaultConfiguration.db_password;
		
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://"+ip+":"+"3306/"+db_name;
		Connection con = DriverManager.getConnection(url, db_user, pass);
		return con;
	}
}
