package es.daumienebi.gestionpeliculas.controllers;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.dao.IGenreDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLGenreDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLMovieDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Genre;
import es.daumienebi.gestionpeliculas.models.Movie;

public class AddMovieUIController {
	private static String MOVIE_IMAGE_SERVER = DefaultConfiguration.movie_image_server;
	private static MySQLMovieDAO movieDAO = new MySQLMovieDAO();
	private static IGenreDAO genreDAO = new MySQLGenreDAO();
	
	public ArrayList<Movie> getAllMovies(){
		return movieDAO.getAllMovies();
	}
	
	public int addMovie(Movie movie) {
		return movieDAO.AddMovie(movie);
	}
	
	public int deleteMovie(int id) {
		return movieDAO.deleteMovie(id);
	}
	
	public String setImagePoster(JButton imgBtn) {
		// btnFoto -- the button where the image will be displayed
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select the image poster");
		String imgRoute = "";
		String imgName ="";
		
		//poner el directorio en el escritorio
		String dirEsc = System.getProperty("user.home");
		jfc.setCurrentDirectory(new File(dirEsc + "/Desktop"));
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image Filter", "png","jpg");
		jfc.setFileFilter(filtro);
		int option = jfc.showOpenDialog(jfc);
		if(option == JFileChooser.APPROVE_OPTION) {
			imgRoute = jfc.getSelectedFile().getAbsolutePath();
			imgName = jfc.getSelectedFile().getName();
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
		
		return imgName;
	}
	
	public ArrayList<Genre> getAllGenres(){
		return genreDAO.getAllGenres();
	}
	
	public int addMovie(Movie movie, ArrayList<Actor> actorsList) {
		return movieDAO.AddMovie(movie, actorsList);
	}
	
	public ImageIcon getImagePoster(String imgRoute) {
		URL url = null;
		ImageIcon icon = null;
		ImageIcon default_icon = new ImageIcon(getClass().getResource("/resources/no_image.jpg"));
		try {
			url = new URL(MOVIE_IMAGE_SERVER + imgRoute);
			icon = new ImageIcon(url);
			if(icon.getImageLoadStatus() == MediaTracker.ERRORED) {
				//
			}
			if(icon == null || icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
				icon = default_icon;
			}
			Image img = icon.getImage();
			//Rescale the image
			Image imgNuevo = img.getScaledInstance(200,300,  java.awt.Image.SCALE_SMOOTH );
			icon =new ImageIcon(imgNuevo);
			return icon;
		} catch (Exception e) {
		}
		return null;
		
	}
	
	public Genre getMovieGenre(int genre_id) {
		return genreDAO.getGenre(genre_id);
	}
	
	public ArrayList<Actor> getActorsPerMovie(int movie_id){
		return movieDAO.getActorsPerMovie(movie_id);
	}
	
	public int modifyMovie(Movie movie) {
		return movieDAO.modifyMovie(movie);
	}

}
