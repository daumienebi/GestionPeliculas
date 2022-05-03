package es.daumienebi.gestionpeliculas.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IPeliculaDAO;
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
			String sql = "INSERT INTO movie (titulo,puntacion,duracion,imagen,id_genero,fecha_estreno,sinopsis) VALUES(?,?,?,?,?,?,?)";
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
		// TODO Auto-generated method stub
		ArrayList<Pelicula> auxList = null;
		//try with an Iterator
		/*
		for(Pelicula movie : movieList) {
			if(movie.getId() == id) {
				//does nothing
			}
			else auxList.add(movie);
		}*/
		//movieList.clear();
		//movieList.addAll(auxList);
		return 0;
	}
	
	
}
