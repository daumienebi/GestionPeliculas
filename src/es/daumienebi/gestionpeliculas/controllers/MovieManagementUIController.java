package es.daumienebi.gestionpeliculas.controllers;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IPeliculaDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLPeliculaDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Pelicula;

public class MovieManagementUIController {
	final static String MOVIE_IMAGE_SERVER = "http://192.168.56.101/moviemanagement_images/movies/"; 
	private static IPeliculaDAO movieDAO = new MySQLPeliculaDAO();
	//private static MySQLPeliculaDAO movieDAO = new MySQLPeliculaDAO(); -- de esta manera si creo otro metodo en MYSQLPeliculaDAO, da error, necesito crearla en la interfaz tambien
	
	public static ArrayList<Pelicula> getAllMovies(){
		return movieDAO.getAllMovies();
	}
	
	public static ArrayList<Pelicula> fliterMovie(String title) {
		ArrayList<Pelicula> filteredMovies = new ArrayList<>();
		filteredMovies = movieDAO.filterMovies(title);
		return filteredMovies;
	}
	
	public static int deleteMovie(int id) {
		return movieDAO.deleteMovie(id);
	}
	
	public static Pelicula getMovie(int movie_id) {
		return movieDAO.getMovie(movie_id);
	}
}
