package es.daumienebi.gestionpeliculas.dao;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Movie;

public interface IActorDAO {
	
	ArrayList<Actor> getAllActors();
	
	Actor getActor(int id);
	
	int deleteActor(int id);
	
	int modifyActor(Actor actor);
	
	int addActor(Actor actor);
	
	ArrayList<Actor> filterActors(String name);
	
	int getNumberOfMovies(int actor_id);
}
