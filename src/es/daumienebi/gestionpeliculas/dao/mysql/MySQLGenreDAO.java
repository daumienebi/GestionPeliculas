package es.daumienebi.gestionpeliculas.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IGenreDAO;
import es.daumienebi.gestionpeliculas.models.Genre;

public class MySQLGenreDAO implements IGenreDAO{
	ArrayList<Genre> genreList = new ArrayList<>();
	
	public void createGenre() {
		genreList.add(new Genre(1,"Action"));//Accion
		genreList.add(new Genre(2,"Romance"));//Romance
		genreList.add(new Genre(3,"Crime"));//Crimen
		genreList.add(new Genre(4,"Fantacy"));//Fantasia
		genreList.add(new Genre(5,"Horror"));//Horror
		genreList.add(new Genre(6,"Sport"));//Deporte
		genreList.add(new Genre(7,"Comedy"));//Comedia
		genreList.add(new Genre(8,"War"));//Guerra
		genreList.add(new Genre(9,"Western"));//Occidental
		genreList.add(new Genre(10,"Science Fiction"));//Ciencia Ficcion
		genreList.add(new Genre(11,"Mystery"));//Misteria
	}
	
	@Override
	public void Insert(Genre genre) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void Delete(Genre genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Genre> getAllGenres() {
		
		try {
			Connection con = DbConnection.getConnection();
			ResultSet rs = con.prepareStatement("Select * from genre order by id asc").executeQuery();
			while(rs.next()) {
				Genre g = new Genre(rs.getInt("id"), rs.getString("name"));
				genreList.add(g);
				//System.out.println(rs.getInt("id") + " " + rs.getString("name") + "\n");
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return genreList;
	}

	@Override
	public void Modify(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Genre getGenre(int id) {
		Connection con = null;
		PreparedStatement preparedSt = null;
		try {
			con = DbConnection.getConnection();
			String sql = "Select * from genre where id = ?";
			preparedSt = con.prepareStatement(sql);	
			preparedSt.setInt(1, id);
			ResultSet rs = preparedSt.executeQuery();
			if(rs.next()) {
				Genre genre = new Genre(rs.getInt("id"), rs.getString("name"));
				return genre;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
