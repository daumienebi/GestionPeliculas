package es.daumienebi.gestionpeliculas.models;

public class Genero {
	private int id;
	private String name;
	//private ArrayList<Pelicula> getMovies; //returns movies of a specific genre
	public Genero(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setGenreName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Genero [id=" + id + ", genreName=" + name + "]";
	}
	
	
}
