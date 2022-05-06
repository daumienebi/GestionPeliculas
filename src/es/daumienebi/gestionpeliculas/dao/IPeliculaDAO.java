package es.daumienebi.gestionpeliculas.dao;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Pelicula;

public interface IPeliculaDAO {

	public ArrayList<Pelicula> getAllMovies();
	
	public int AddMovie(Pelicula movie);
	
	public int modifyMovie(Pelicula movie);
	
	public int deleteMovie(int id);

	public ArrayList<Pelicula> filterMovies(String title);
	
	public int AddMovie(Pelicula movie, ArrayList<Actor> actorsList);
	
	public Pelicula getMovie(int movie_id);
}
