package es.daumienebi.gestionpeliculas.controllers;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IGeneroDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLGenreDAO;
import es.daumienebi.gestionpeliculas.models.Genero;

public class GenreManagementUIController {

	private static IGeneroDAO genreDAO = new MySQLGenreDAO();
	
	public static ArrayList<Genero> getAllGenres() {
		return genreDAO.getAllGenres();
	}
}
