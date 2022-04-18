package es.daumienebi.gestionpeliculas.models;

public class Genero {
	private int id;
	private String genreName;
	//private ArrayList<Pelicula> getMovies; //returns movies of a specific genre
	public Genero(int id, String genreName) {
		super();
		this.id = id;
		this.genreName = genreName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	@Override
	public String toString() {
		return "Genero [id=" + id + ", genreName=" + genreName + "]";
	}
	
	
}
