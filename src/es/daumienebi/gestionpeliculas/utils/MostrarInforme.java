package es.daumienebi.gestionpeliculas.utils;
import es.daumienebi.gestionpeliculas.dao.mysql.DbConnection;
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
}
