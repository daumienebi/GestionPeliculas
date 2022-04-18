package es.daumienebi.gestionpeliculas.controllers;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IPeliculaDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLPeliculaDAO;
import es.daumienebi.gestionpeliculas.models.Pelicula;

public class MovieManagementUIController {
	private static IPeliculaDAO movieDAO = new MySQLPeliculaDAO();
	
	public static ArrayList<Pelicula> getAllMovies(){
		return movieDAO.getAllMovies();
	}
	
	public static ArrayList<Pelicula> fliterMovie(String title) {
		//para reordenar la tabla despues de meter un dato
		//creo un ArrayLista para meter los datos filtrados
		ArrayList<Pelicula> listaAux = new ArrayList<>();
		
		for(Pelicula movie : movieDAO.getAllMovies()) {
			if(movie.getTitulo().startsWith(title)) {
				listaAux.add(movie);
			}
		}
		return listaAux;
	}
}
