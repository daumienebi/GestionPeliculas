package es.daumienebi.gestionpeliculas.dao;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.models.Pelicula;

public interface IPeliculaDAO {

	public ArrayList<Pelicula> getAllMovies();
	
	public void AddMovie(Pelicula movie);
	
	public void modifyMovie(Pelicula movie);
	
	public void deleteMovie(int id);

	public ArrayList<Pelicula> filterMovies(String title);
}
