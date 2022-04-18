package es.daumienebi.gestionpeliculas.controllers;

import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IActorDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLActorDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Pelicula;

public class ActorManagementUIController {
	private static IActorDAO actorDAO = new MySQLActorDAO();
	
	public static ArrayList<Actor> getAllActors() {
		return actorDAO.getAllActors();
	}
	
	public static ArrayList<Actor> fliterMovie(String title) {
		//para reordenar la tabla despues de meter un dato
		//creo un ArrayLista para meter los datos filtrados
		ArrayList<Actor> listaAux = new ArrayList<>();
		
		for(Actor actor : actorDAO.getAllActors()) {
			if(actor.getNombre().startsWith(title)) {
				listaAux.add(actor);
			}
		}
		return listaAux;
	}
}
