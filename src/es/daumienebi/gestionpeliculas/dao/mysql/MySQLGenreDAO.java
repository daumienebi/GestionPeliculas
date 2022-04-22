package es.daumienebi.gestionpeliculas.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.daumienebi.gestionpeliculas.dao.IGeneroDAO;
import es.daumienebi.gestionpeliculas.models.Genero;

public class MySQLGenreDAO implements IGeneroDAO{
	ArrayList<Genero> genreList = new ArrayList<>();
	
	public void createGenre() {
		genreList.add(new Genero(1,"Action"));//Accion
		genreList.add(new Genero(2,"Romance"));//Romance
		genreList.add(new Genero(3,"Crime"));//Crimen
		genreList.add(new Genero(4,"Fantacy"));//Fantasia
		genreList.add(new Genero(5,"Horror"));//Horror
		genreList.add(new Genero(6,"Sport"));//Deporte
		genreList.add(new Genero(7,"Comedy"));//Comedia
		genreList.add(new Genero(8,"War"));//Guerra
		genreList.add(new Genero(9,"Western"));//Occidental
		genreList.add(new Genero(10,"Science Fiction"));//Ciencia Ficcion
		genreList.add(new Genero(11,"Mystery"));//Misteria
	}
	
	@Override
	public void Insert(Genero genre) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void Delete(Genero genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Genero> getAllGenres() {
		
		try {
			Connection con = DbConnection.getConexion();
			ResultSet rs = con.prepareStatement("Select * from genre order by id asc").executeQuery();
			while(rs.next()) {
				Genero g = new Genero(rs.getInt("id"), rs.getString("name"));
				genreList.add(g);
				System.out.println(rs.getInt("id") + " " + rs.getString("name") + "\n");
				
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
	public Genero getGenre(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
