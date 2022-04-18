package es.daumienebi.gestionpeliculas.dao.mysql;

import java.time.LocalDate;
import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IActorDAO;
import es.daumienebi.gestionpeliculas.models.Actor;

public class MySQLActorDAO implements IActorDAO{

	private static ArrayList<Actor> actorsList = new ArrayList<>();
	
	private void createActors() {
		//actorsList = new ArrayList<>();
		actorsList.add(new Actor(1,"Derick Daumienebi","Sakpa", LocalDate.now(),"/resources/damian.jpg"));
		actorsList.add(new Actor(2,"Andrea","Pereira San Martin", LocalDate.now(),"/resources/damian.jpg"));
		actorsList.add(new Actor(3,"Xalo","Garcia Paz", LocalDate.now(),"/resources/huevo.jpg"));
		actorsList.add(new Actor(4,"Maria","Lopez Vidal", LocalDate.now(),"/resources/damian.jpg"));
		actorsList.add(new Actor(5,"Lidia","Mirad Boto", LocalDate.now(),"/resources/damian.jpg"));
		actorsList.add(new Actor(6,"Raquel","Romero Blanco", LocalDate.now(),"/resources/damian.jpg"));
		actorsList.add(new Actor(7,"Eloy","Gonzalez", LocalDate.now(),"/resources/damian.jpg"));
		actorsList.add(new Actor(8,"Diego","Galloso Orejas", LocalDate.now(),"/resources/damian.jpg"));
		actorsList.add(new Actor(9,"Cristian","Coentreras", LocalDate.now(),"/resources/damian.jpg"));
		actorsList.add(new Actor(10,"Aroa","Fernandez H", LocalDate.now(),"/resources/damian.jpg"));
	}
	@Override
	public ArrayList<Actor> getAllActors() {
		if(actorsList.size() == 0) {
			createActors();
		}
		return actorsList;
	}

	@Override
	public Actor getActor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteActor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyActor(Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActor(Actor actor) {
		if(actor != null) {
			getAllActors().add(actor);
		}
		
	}

}
