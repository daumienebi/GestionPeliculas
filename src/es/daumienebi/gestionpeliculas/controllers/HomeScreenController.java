package es.daumienebi.gestionpeliculas.controllers;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import es.daumienebi.gestionpeliculas.config.Configuration;
import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;

public class HomeScreenController {
	/**
	 * 
	 * @return An array with the relative routes of the images for the HomeScreen Image Slider.
	 */
	public static String [] imgList = {

            "/resources/hobbit.jpg","/resources/minion2.jpg",
            "/resources/themanfromuncle.jpg",
            "/resources/interstellar.jpg",
            "/resources/machete.jpg","/resources/4k.jpg",
            "/resources/johnwick.jpg","/resources/minion3.jpg",
            "/resources/warcraft.jpg","/resources/superman.jpg",
            "/resources/bobesponja.jpg","/resources/deadpool.jpg","/resources/minion1.jpg",               
	};
	
	public static String[] getMovieSliderImages(){
		return imgList;
	}
	
	public static void refreshTables() {
		//idea : instantiate all the DAOs to get the data, create a new table model and refresh the data		
	}
	
	//not finished
	private static void SetImageSize(int index, JLabel imgSlider){
		Image img = null;
		Image newImg;
		ImageIcon icon;
		icon = new ImageIcon(imgSlider.getClass().getResource(imgList[index]));
		img = icon.getImage();
		newImg = img.getScaledInstance(imgSlider.getWidth(), imgSlider.getHeight(), Image.SCALE_SMOOTH);        
        ImageIcon finalImg = new ImageIcon(newImg);
        imgSlider.setIcon(finalImg);
    }
		
	public static void setConfig() {		
		//demasiados iffss pero de momento sirve 
		if(DefaultConfiguration.ip.equals(Configuration.ip)) {
			DefaultConfiguration.ip = Configuration.ip;
		}
		if(DefaultConfiguration.db_name.equals(Configuration.db_name)) {
			DefaultConfiguration.db_name = Configuration.db_name;
		}
		if(DefaultConfiguration.db_user.equals(Configuration.db_user)) {
			DefaultConfiguration.db_user = Configuration.db_user;
		}
		if(DefaultConfiguration.db_password.equals(Configuration.db_password)) {
			DefaultConfiguration.db_password = Configuration.db_password;
		}
		if(DefaultConfiguration.port.equals(Configuration.port)) {
			DefaultConfiguration.port = Configuration.port;
		}
		if(DefaultConfiguration.actor_image_server.equals(Configuration.actor_image_server)) {
			DefaultConfiguration.actor_image_server = Configuration.actor_image_server;
		}
		if(DefaultConfiguration.movie_image_server.equals(Configuration.movie_image_server)) {
			DefaultConfiguration.movie_image_server = Configuration.movie_image_server;
		}
		if(DefaultConfiguration.ftp_password.equals(Configuration.ftp_password)) {
			DefaultConfiguration.ftp_password = Configuration.ftp_password;
		}
		if(DefaultConfiguration.ftp_server.equals(Configuration.ftp_server)) {
			DefaultConfiguration.ftp_server = Configuration.ftp_server;
		}
		if(DefaultConfiguration.ftp_user.equals(Configuration.ftp_user)) {
			DefaultConfiguration.ftp_user = Configuration.ftp_user;
		}
	}
}
