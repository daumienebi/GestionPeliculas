package es.daumienebi.gestionpeliculas.dao;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Movie;

public interface IMovieDAO {

	public ArrayList<Movie> getAllMovies();
	
	public int AddMovie(Movie movie);
	
	public int deleteMovie(int id);

	public ArrayList<Movie> filterMovies(String title);
	
	public int AddMovie(Movie movie, ArrayList<Actor> actorsList);
	
	public Movie getMovie(int movie_id);
	
	public ArrayList<Actor> getActorsPerMovie(int movie_id);

	public int modifyMovie(Movie movie, ArrayList<Actor> actorsList);

	public int modifyMovie(Movie movie);
	
}
