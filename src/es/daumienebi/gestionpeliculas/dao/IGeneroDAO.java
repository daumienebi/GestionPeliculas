package es.daumienebi.gestionpeliculas.dao;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.models.Genero;

public interface IGeneroDAO{

	public void Insert(Genero genre);

	public void Delete(Genero genre);
	
	public ArrayList<Genero> getAllGenres();

	public void Modify(int id);
	
	public Genero getGenre(int id);

}
