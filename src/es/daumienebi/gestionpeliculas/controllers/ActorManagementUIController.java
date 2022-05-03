package es.daumienebi.gestionpeliculas.controllers;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IActorDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLActorDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Pelicula;

public class ActorManagementUIController {
	private static IActorDAO actorDAO = new MySQLActorDAO();
	
	public ArrayList<Actor> getAllActors() {
		return actorDAO.getAllActors();
	}
	
	public ArrayList<Actor> fliterMovie(String title) {
		ArrayList<Actor> filteredActors = new ArrayList<>();
		filteredActors = actorDAO.filterActors(title);
		return filteredActors;
	}
	
	public int deleteActor(int actor_id) {
		return actorDAO.deleteActor(actor_id);
	}
}
