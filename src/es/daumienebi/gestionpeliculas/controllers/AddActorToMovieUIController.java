package es.daumienebi.gestionpeliculas.controllers;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.dao.IActorDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLActorDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;

public class AddActorToMovieUIController {
	private IActorDAO actorDAO = new MySQLActorDAO();
	
	public ArrayList<Actor> getAllActors() {
		return actorDAO.getAllActors();
	}
	
	public ArrayList<Actor> fliterMovie(String title) {
		ArrayList<Actor> filteredActors = new ArrayList<>();
		filteredActors = actorDAO.filterActors(title);
		return filteredActors;
	}
	public Actor getActor(int actor_id) {
		return actorDAO.getActor(actor_id);
	}
	
	public void translate() {
		if(TranslatorUtil.bundle != null) {
			TranslatorUtil.translateAddActorToMovieUI(DefaultConfiguration.lang_id);
		}
	}
}
