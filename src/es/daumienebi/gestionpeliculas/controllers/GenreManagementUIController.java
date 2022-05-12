package es.daumienebi.gestionpeliculas.controllers;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IGenreDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLGenreDAO;
import es.daumienebi.gestionpeliculas.models.Genre;

public class GenreManagementUIController {

	private static IGenreDAO genreDAO = new MySQLGenreDAO();
	
	public static ArrayList<Genre> getAllGenres() {
		return genreDAO.getAllGenres();
	}
}
