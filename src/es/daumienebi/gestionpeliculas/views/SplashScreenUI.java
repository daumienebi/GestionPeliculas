package es.daumienebi.gestionpeliculas.views;

import java.sql.Connection;

import es.daumienebi.gestionpeliculas.dao.mysql.DbConnection;
import es.daumienebi.gestionpeliculas.utils.SplashScreenUtil;

public class SplashScreenUI {

	public static void main(String [] args) {
		SplashScreenUtil splash = new SplashScreenUtil();
		splash.setLocationRelativeTo(splash);
		splash.setVisible(true);
		
		try {		
			for(int i = 0; i <= 100; i++) {
				Thread.sleep(40);
				SplashScreenUtil.lblLoading.setText(""+i);
				SplashScreenUtil.progressBar.setValue(i);
				if(i == 10) {
					SplashScreenUtil.lblDetail.setText("Starting app...");
				}
				
				if(i == 50) {
					SplashScreenUtil.lblDetail.setText("Loading app properties...");
				}
				
				if(i == 80) {
					SplashScreenUtil.lblDetail.setText("Checking database connection...");
				} 
				
				if(i ==100) {
					SplashScreenUtil.lblDetail.setText("Connecting to database...");
					HomeScreen mainScreen = new HomeScreen();
					mainScreen.disableMenus();
					mainScreen.frmGestionPeliculas.setLocationRelativeTo(mainScreen.frmGestionPeliculas);					
					splash.dispose();
					mainScreen.frmGestionPeliculas.setVisible(true);	
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
