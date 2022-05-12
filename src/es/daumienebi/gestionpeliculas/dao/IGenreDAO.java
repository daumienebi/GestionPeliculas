package es.daumienebi.gestionpeliculas.dao;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.models.Genre;

public interface IGenreDAO{

	public void Insert(Genre genre);

	public void Delete(Genre genre);
	
	public ArrayList<Genre> getAllGenres();

	public void Modify(int id);
	
	public Genre getGenre(int id);

}
