package es.daumienebi.gestionpeliculas.controllers;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import es.daumienebi.gestionpeliculas.dao.mysql.MySQLActorDAO;

public class HomeScreenController {
	/**
	 * 
	 * @return An array with the relative routes of the images for the HomeScreen Image Slider.
	 */
	public static String [] imgList = {

            "/resources/hobbit.jpg","/resources/minion2.jpg",
            "/resources/hunger_games.jpg","/resources/themanfromuncle.jpg",
            "/resources/interstellar.jpg",
            "/resources/4k.jpg","/resources/machete.jpg",
            "/resources/johnwick.jpg","/resources/minion3.jpg",
            "/resources/warcraft.jpg","/resources/superman.jpg",
            "/resources/bobesponja.jpg","/resources/deadpool.jpg",
            "/resources/avengerGif.gif","/resources/minion1.jpg",               
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
		
}
