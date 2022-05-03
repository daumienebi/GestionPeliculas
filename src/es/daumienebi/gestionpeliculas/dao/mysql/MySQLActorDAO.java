package es.daumienebi.gestionpeliculas.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IActorDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Pelicula;

public class MySQLActorDAO implements IActorDAO{	
	/*
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
	*/
	@Override
	public ArrayList<Actor> getAllActors() {
		ArrayList<Actor> actorsList = new ArrayList<>();
		int id;
		String nombre,apellido,foto;
		LocalDate fechanac = null;
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "Select * from actor order by id asc";
			PreparedStatement ps = null;
			ResultSet rs = null;			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				if(rs.getDate("fechanac") != null) {
					fechanac = LocalDate.parse(rs.getDate("fechanac").toString()) == null ? LocalDate.now() : LocalDate.parse(rs.getDate("fechanac").toString());
				}
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				foto = rs.getString("imagen");			
				Actor a = new Actor(id, nombre, apellido, fechanac, foto);
				actorsList.add(a);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return actorsList;
	}

	@Override
	public Actor getActor(int actor_id) {
		Actor actor = null;
		int id;
		String nombre,apellido,foto;
		LocalDate fechanac;
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;			
			ps = con.prepareStatement(sql);
			ps.setInt(1, actor_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
				fechanac = LocalDate.parse(rs.getDate("fechanac").toString());
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				foto = rs.getString("imagen");			
				actor = new Actor(id, nombre, apellido, fechanac, foto);
				return actor;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return null;
	}

	@Override
	public int deleteActor(int actor_id) {
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "DELETE FROM actor WHERE id = ?";
			PreparedStatement preparedSt = null;		
			preparedSt = con.prepareStatement(sql);
			preparedSt.setInt(1, actor_id);
			preparedSt.executeUpdate();
			con.commit();
			return 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public int modifyActor(Actor actor) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int addActor(Actor actor) {
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "INSERT INTO actor (nombre,apellido,fechanac,imagen) VALUES(?,?,?,?)";
			PreparedStatement preparedSt = null;		
			preparedSt = con.prepareStatement(sql);
			preparedSt.setString(1, actor.getNombre());
			preparedSt.setString(2, actor.getApellidos());
			preparedSt.setDate(3, Date.valueOf(actor.getFechaNac()));
			preparedSt.setString(4, actor.getFoto());
			preparedSt.executeUpdate();
			con.commit();
			return 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	@Override
	public ArrayList<Actor> filterActors(String name) {
		// TODO Auto-generated method stub
		ArrayList<Actor> actorsList = new ArrayList<>();
		int id;
		String nombre,apellido,foto;
		LocalDate fechanac;
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "Select * from actor where nombre like ? order by id asc";
			PreparedStatement preparedSt = null;
			ResultSet resultSet = null;			
			preparedSt = con.prepareStatement(sql);
			preparedSt.setString(1, "%"+name+"%");
			resultSet = preparedSt.executeQuery();
			while(resultSet.next()) {
				id = resultSet.getInt("id");
				fechanac = LocalDate.parse(resultSet.getDate("fechanac").toString());
				nombre = resultSet.getString("nombre");
				apellido = resultSet.getString("apellido");
				foto = resultSet.getString("imagen");			
				Actor a = new Actor(id, nombre, apellido, fechanac, foto);
				actorsList.add(a);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return actorsList;
	}

}
