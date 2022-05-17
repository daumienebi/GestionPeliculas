package es.daumienebi.gestionpeliculas.controllers;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.dao.IMovieDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLMovieDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Movie;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;

public class MovieManagementUIController {
	final static String MOVIE_IMAGE_SERVER = "http://192.168.56.101/moviemanagement_images/movies/"; 
	private static IMovieDAO movieDAO = new MySQLMovieDAO();
	//private static MySQLPeliculaDAO movieDAO = new MySQLPeliculaDAO(); -- de esta manera si creo otro metodo en MYSQLPeliculaDAO, da error, necesito crearla en la interfaz tambien
	
	public static ArrayList<Movie> getAllMovies(){
		return movieDAO.getAllMovies();
	}
	
	public static ArrayList<Movie> fliterMovie(String title) {
		ArrayList<Movie> filteredMovies = new ArrayList<>();
		filteredMovies = movieDAO.filterMovies(title);
		return filteredMovies;
	}
	
	public static int deleteMovie(int id) {
		return movieDAO.deleteMovie(id);
	}
	
	public static Movie getMovie(int movie_id) {
		return movieDAO.getMovie(movie_id);
	}
	
	public static void translate() {
		if(TranslatorUtil.bundle != null) {
			TranslatorUtil.translateMovieManagementUI(DefaultConfiguration.lang_id);
		}
	}
}
