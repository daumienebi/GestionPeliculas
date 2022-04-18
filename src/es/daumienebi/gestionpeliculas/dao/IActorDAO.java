package es.daumienebi.gestionpeliculas.dao;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.models.Actor;

public interface IActorDAO {
	
	ArrayList<Actor> getAllActors();
	
	Actor getActor(int id);
	
	void deleteActor();
	
	void modifyActor(Actor actor);
	
	void addActor(Actor actor);
}
