package es.daumienebi.gestionpeliculas.dao.mysql;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IGeneroDAO;
import es.daumienebi.gestionpeliculas.models.Genero;

public class MySQLGenreDAO implements IGeneroDAO{
	ArrayList<Genero> genreList = new ArrayList<>();
	
	public void createGenre() {
		genreList.add(new Genero(1,"Action"));//Accion
		genreList.add(new Genero(2,"Romance"));//Romance
		genreList.add(new Genero(2,"Crime"));//Crimen
		genreList.add(new Genero(2,"Fantacy"));//Fantasia
		genreList.add(new Genero(2,"Horror"));//Horror
		genreList.add(new Genero(2,"Sport"));//Deporte
		genreList.add(new Genero(2,"Comedy"));//Comedia
		genreList.add(new Genero(2,"War"));//Guerra
		genreList.add(new Genero(2,"Western"));//Occidental
		genreList.add(new Genero(2,"Science Fiction"));//Ciencia Ficcion
		genreList.add(new Genero(2,"Mystery"));//Misteria
	}
	
	@Override
	public void Insert(Genero genre) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void Delete(Genero genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Genero> getAll() {
		if(genreList.size() ==0) {
			createGenre();
		}
		return genreList;
	}

	@Override
	public void Modify(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Genero getGenre(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
