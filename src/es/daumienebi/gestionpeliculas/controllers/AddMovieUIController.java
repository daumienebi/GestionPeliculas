package es.daumienebi.gestionpeliculas.controllers;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.daumienebi.gestionpeliculas.dao.mysql.MySQLPeliculaDAO;
import es.daumienebi.gestionpeliculas.models.Pelicula;

public class AddMovieUIController {
	private static MySQLPeliculaDAO movieDAO = new MySQLPeliculaDAO();
	
	public static ArrayList<Pelicula> getAllMovies(){
		return movieDAO.getAllMovies();
	}
	
	public static void addMovie(Pelicula movie) {
		movieDAO.AddMovie(movie);
	}
	
	public static void deleteMovie(int id) {
		movieDAO.deleteMovie(id);
	}
	
	public static String setImagePoster(JButton imgBtn) {
		// btnFoto -- the button where the image will be displayed
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select the image poster");
		String imgRoute = "";
		
		//poner el directorio en el escritorio
		String dirEsc = System.getProperty("user.home");
		jfc.setCurrentDirectory(new File(dirEsc + "/Desktop"));
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image Filter", "png","jpg");
		jfc.setFileFilter(filtro);
		int option = jfc.showOpenDialog(jfc);
		if(option == JFileChooser.APPROVE_OPTION) {
			imgRoute = jfc.getSelectedFile().getAbsolutePath();
		}		
		//check if the imgRoute is not null before placing the button
		
		if(!imgRoute.isEmpty()) {
			//Obtain the default icon to be used incase the imgRoute is empty
			ImageIcon icon = new ImageIcon(imgRoute);
			Image img = icon.getImage();
			//rescale the image
			Image imgNuevo = img.getScaledInstance(imgBtn.getWidth(),imgBtn.getHeight(),java.awt.Image.SCALE_SMOOTH );
			//assign the new resized image to the button
			icon =new ImageIcon(imgNuevo);
			imgBtn.setIcon(icon);
		}
		
		return imgRoute;
	}
}