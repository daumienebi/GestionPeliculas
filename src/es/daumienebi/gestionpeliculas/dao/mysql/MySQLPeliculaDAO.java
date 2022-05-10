package es.daumienebi.gestionpeliculas.dao.mysql;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IPeliculaDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Pelicula;

public class MySQLPeliculaDAO implements IPeliculaDAO {
	
	/**
	public void createMovies() {
		//movieList = new ArrayList<>();
		movieList.add(new Pelicula(1,"Los Increibles","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				7.5,120,LocalDate.now(),"/resources/increibles.jpg"));
		movieList.add(new Pelicula(2,"Interstellar","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				3.5,110,LocalDate.now(),"/resources/superman.jpg"));
		movieList.add(new Pelicula(3,"Superman vs Batman","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				7.5,110,LocalDate.now(),"/resources/superman.jpg"));
		movieList.add(new Pelicula(4,"Damian : The way home","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				1.5,110,LocalDate.now(),"/resources/damian.jpg"));
		movieList.add(new Pelicula(5,"DEADPOOL","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				10,110,LocalDate.now(),"/resources/deadpool.jpg"));
		movieList.add(new Pelicula(6,"Hunger games","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				5,110,LocalDate.now(),"/resources/hunger_games.jpg"));
		movieList.add(new Pelicula(7,"Spiderman 3","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				4.5,110,LocalDate.now(),"/resources/spiderman.jpg"));
		movieList.add(new Pelicula(8,"Superman : Damian Master","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				8.1,110,LocalDate.now(),"/resources/superman.jpg"));
		movieList.add(new Pelicula(9,"4k film : Latest Edition","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				9,110,LocalDate.now(),"/resources/4k.jpg"));
		movieList.add(new Pelicula(10,"Superman : Damian Master","Una familia aleatoria que busca encontrarse en una ciudad llena de Damianes, no se que poner",
				6.3,110,LocalDate.now(),"/resources/warcraft.jpg"));
	}
	*/
	
	@Override
	public ArrayList<Pelicula> getAllMovies() {
		// TODO Auto-generated method stub
		ArrayList<Pelicula> movieList = new ArrayList<>();
		int id,duracion,id_genero;
		String titulo,sinopsis,caratula;
		double puntuacion;
		LocalDate fecha_estreno;
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "Select * from movie order by id asc";
			PreparedStatement ps = null;
			ResultSet rs = null;			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				fecha_estreno = LocalDate.parse(rs.getDate("fecha_estreno").toString());
				duracion =rs.getInt("duracion");
				titulo = rs.getString("titulo");
				sinopsis = rs.getString("sinopsis");
				caratula = rs.getString("imagen");
				puntuacion = rs.getDouble("puntuacion");
				id_genero = rs.getInt("id_genero");
				Pelicula p = new Pelicula(id, titulo, sinopsis, puntuacion, duracion, fecha_estreno, caratula,id_genero);
				movieList.add(p);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return movieList;
	}

	public ArrayList<Pelicula> filterMovies(String title){
		ArrayList<Pelicula> filterList = new ArrayList<>();
		int id,duracion,id_genero;
		String titulo,sinopsis,caratula;
		double puntuacion;
		LocalDate fecha_estreno;
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "Select * from movie where titulo like ? order by id asc";
			PreparedStatement ps = null;
			ResultSet rs = null;			
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				fecha_estreno = LocalDate.parse(rs.getDate("fecha_estreno").toString());
				duracion =rs.getInt("duracion");
				titulo = rs.getString("titulo");
				sinopsis = rs.getString("sinopsis");
				caratula = rs.getString("imagen");
				puntuacion = rs.getDouble("puntuacion");
				id_genero = rs.getInt("id_genero");
				Pelicula p = new Pelicula(id, titulo, sinopsis, puntuacion, duracion, fecha_estreno, caratula,id_genero);
				filterList.add(p);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return filterList;
	}
	@Override
	public int AddMovie(Pelicula movie) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "INSERT INTO movie (titulo,puntuacion,duracion,imagen,id_genero,fecha_estreno,sinopsis) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement preparedSt = null;		
			preparedSt = con.prepareStatement(sql);
			preparedSt.setString(1, movie.getTitulo());
			preparedSt.setDouble(2, movie.getPuntuation());
			preparedSt.setInt(3, movie.getDuracionEnMinutos());
			preparedSt.setString(4, movie.getCaratula());
			preparedSt.setInt(5, movie.getId_genero());
			preparedSt.setDate(6, Date.valueOf(movie.getFechaEstreno()));
			preparedSt.setString(7, movie.getSinoposis());
			preparedSt.executeUpdate();
			con.commit();
			return  0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public int modifyMovie(Pelicula movie) {
		// TODO Auto-generated method stub
	
		return 0;
	}

	@Override
	public int deleteMovie(int id) {
		Connection con = null;
		PreparedStatement preparedSt = null;
		try {
			con = DbConnection.getConexion();
			//firstly delete from movie_actor table
			String query1 = "DELETE FROM movie_actor WHERE movie_id = ?";
			preparedSt = con.prepareStatement(query1);
			preparedSt.setInt(1, id);
			preparedSt.executeUpdate();
			
			//finally delete the movies
			String query2 = "DELETE FROM movie WHERE id = ?";	
			preparedSt = con.prepareStatement(query2);
			preparedSt.setInt(1, id);
			preparedSt.executeUpdate();
			con.commit();
			return 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public int AddMovie(Pelicula movie, ArrayList<Actor> actorsList) {
		// TODO Auto-generated method stub
		int last_id = 0;
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			
			String sql = "INSERT INTO movie (titulo,puntuacion,duracion,imagen,id_genero,fecha_estreno,sinopsis) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement preparedSt = null;		
			preparedSt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedSt.setString(1, movie.getTitulo());
			preparedSt.setDouble(2, movie.getPuntuation());
			preparedSt.setInt(3, movie.getDuracionEnMinutos());
			preparedSt.setString(4, movie.getCaratula());
			preparedSt.setInt(5, movie.getId_genero());
			preparedSt.setDate(6, Date.valueOf(movie.getFechaEstreno()));
			preparedSt.setString(7, movie.getSinoposis());
			preparedSt.executeUpdate();
			
			//obtain the last inserted id
			ResultSet result = preparedSt.getGeneratedKeys();
			
			if(result.next()) {
				last_id = Integer.valueOf(result.getInt(1));
			}
			
			//add the actors to the movie
			if(actorsList.size() > 0 && actorsList != null) {
				sql = "SET FOREIGN_KEY_CHECKS = 0";
				preparedSt = con.prepareStatement(sql);
				preparedSt.executeUpdate();
				for(Actor actor : actorsList) {
					sql = "INSERT INTO movie_actor VALUES(?,?)";
					preparedSt = con.prepareStatement(sql);
					preparedSt.setInt(1, last_id);
					preparedSt.setInt(2, actor.getId());
					preparedSt.executeUpdate();
				}
				sql = "SET FOREIGN_KEY_CHECKS = 1";
				preparedSt = con.prepareStatement(sql);
				preparedSt.executeUpdate();
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				con.commit(); //o todo o nada  
				return  0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	
	public Pelicula getMovie(int movie_id) {
		Pelicula movie = null;
		int id, id_genero,duracion;
		String titulo,sinopsis,imagen;
		double puntuacion;
		LocalDate fecha_estreno;
		Connection con = null;
		try {
			con = DbConnection.getConexion();
			String sql = "SELECT * FROM movie WHERE id = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;			
			ps = con.prepareStatement(sql);
			ps.setInt(1, movie_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
				id_genero = rs.getInt("id_genero");
				fecha_estreno = LocalDate.parse(rs.getDate("fecha_estreno").toString());
				titulo = rs.getString("titulo");
				sinopsis = rs.getString("sinopsis");
				imagen = rs.getString("imagen");	
				duracion = rs.getInt("duracion");
				puntuacion = rs.getDouble("puntuacion");
				
				movie = new Pelicula(id, titulo, sinopsis, puntuacion, duracion, fecha_estreno, imagen, id_genero);
				return movie;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	//delete movies from movie_actor
}
