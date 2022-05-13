package es.daumienebi.gestionpeliculas.dao.mysql;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IMovieDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Movie;

	public class MySQLMovieDAO implements IMovieDAO {
	
	@Override
	public ArrayList<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		ArrayList<Movie> movieList = new ArrayList<>();
		int id,duracion,id_genero;
		String titulo,sinopsis,caratula;
		double puntuacion;
		LocalDate fecha_estreno;
		Connection con = null;
		try {
			con = DbConnection.getConnection();
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
				Movie p = new Movie(id, titulo, sinopsis, puntuacion, duracion, fecha_estreno, caratula,id_genero);
				movieList.add(p);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return movieList;
	}

	public ArrayList<Movie> filterMovies(String title){
		ArrayList<Movie> filterList = new ArrayList<>();
		int id,duracion,id_genero;
		String titulo,sinopsis,caratula;
		double puntuacion;
		LocalDate fecha_estreno;
		Connection con = null;
		try {
			con = DbConnection.getConnection();
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
				Movie p = new Movie(id, titulo, sinopsis, puntuacion, duracion, fecha_estreno, caratula,id_genero);
				filterList.add(p);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return filterList;
	}
	
	@Override
	public int AddMovie(Movie movie) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DbConnection.getConnection();
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
	public int modifyMovie(Movie movie) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DbConnection.getConnection();
			String sql = 	"UPDATE movie SET titulo = ?, puntuacion = ?, duracion = ?, imagen = ?,"+
							" id_genero = ?, fecha_estreno = ?, sinopsis = ? WHERE id = ?";
			PreparedStatement preparedSt = null;		
			preparedSt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedSt.setString(1, movie.getTitulo());
			preparedSt.setDouble(2, movie.getPuntuation());
			preparedSt.setInt(3, movie.getDuracionEnMinutos());
			preparedSt.setString(4, movie.getCaratula());
			preparedSt.setInt(5, movie.getId_genero());
			preparedSt.setDate(6, Date.valueOf(movie.getFechaEstreno()));
			preparedSt.setString(7, movie.getSinoposis());
			preparedSt.setInt(8, movie.getId());
			preparedSt.executeUpdate();
			
			//the actors will not be modified in this case, it might be added later on
			con.commit();
			return  0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				print("Entered Rollback block");
				ex.printStackTrace();
			}
		}
		return -1;
		
	}

	@Override
	public int deleteMovie(int id) {
		Connection con = null;
		PreparedStatement preparedSt = null;
		try {
			con = DbConnection.getConnection();
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
	public int AddMovie(Movie movie, ArrayList<Actor> actorsList) {
		// TODO Auto-generated method stub
		int last_id = 0;
		Connection con = null;
		try {
			con = DbConnection.getConnection();
			
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
					preparedSt.setInt(1, actor.getId());
					preparedSt.setInt(2, last_id);
					preparedSt.executeUpdate();
				}
				sql = "SET FOREIGN_KEY_CHECKS = 1";
				preparedSt = con.prepareStatement(sql);
				preparedSt.executeUpdate();
			}
			con.commit();
			return  0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				print("Entered Rollback block");
				ex.printStackTrace();
			}
		}
		return -1;
	}
		
	public Movie getMovie(int movie_id) {
		Movie movie = null;
		int id, id_genero,duracion;
		String titulo,sinopsis,imagen;
		double puntuacion;
		LocalDate fecha_estreno;
		Connection con = null;
		try {
			con = DbConnection.getConnection();
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
				
				movie = new Movie(id, titulo, sinopsis, puntuacion, duracion, fecha_estreno, imagen, id_genero);
				return movie;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Actor> getActorsPerMovie(int movie_id){
		ArrayList<Actor> actorsList = new ArrayList<>();
		Connection con = null;
		int id;
		String nombre,apellido,foto;
		LocalDate fechanac;
		try {
			con = DbConnection.getConnection();
			String sql = "SELECT * FROM actor INNER JOIN movie_actor"
						+" ON(actor.id = movie_actor.actor_id)"
						+" WHERE movie_id = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;			
			ps = con.prepareStatement(sql);
			ps.setInt(1, movie_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				fechanac = LocalDate.parse(rs.getDate("fechanac").toString());
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				foto = rs.getString("imagen");			
				Actor actor = new Actor(id, nombre, apellido, fechanac, foto);
				actorsList.add(actor);
			}
			return actorsList;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private void print(Object value) {
		System.out.println(value);
	}

	@Override
	public int modifyMovie(Movie movie, ArrayList<Actor> actorsList) {
		// TODO Auto-generated method stub
		return 0;
	}
}
