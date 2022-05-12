package es.daumienebi.gestionpeliculas.models;

import java.time.LocalDate;

public class Movie {

	private int id;
	private String titulo;
	private String sinoposis;
	private double puntuation;
	private int duracionEnMinutos;
	private LocalDate fechaEstreno;
	private String caratula;
	private int id_genero;
	
	public Movie(int id,String titulo, String sinoposis, double puntuation, int duracionEnMinutos, LocalDate fechaEstreno,String caratula,int id_genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.sinoposis = sinoposis;
		this.puntuation = puntuation;
		this.duracionEnMinutos = duracionEnMinutos;
		this.fechaEstreno = fechaEstreno;
		this.caratula = caratula;
		this.id_genero = id_genero;
	}
	
	public Movie() {
		
	}	
	
	public int getId() {
		return id;
	}
	
	public int getId_genero() {
		return id_genero;
	}

	public void setId_genero(int id_genero) {
		this.id_genero = id_genero;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSinoposis() {
		return sinoposis;
	}
	public void setSinoposis(String sinoposis) {
		this.sinoposis = sinoposis;
	}
	public double getPuntuation() {
		return puntuation;
	}
	public void setPuntuation(double puntuation) {
		this.puntuation = puntuation;
	}
	public int getDuracionEnMinutos() {
		return duracionEnMinutos;
	}
	public void setDuracionEnMinutos(int duracionEnMinutos) {
		this.duracionEnMinutos = duracionEnMinutos;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getCaratula() {
		return caratula;
	}
	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	@Override
	public String toString() {
		return "Pelicula [id= " + id + ", titulo=" + titulo + ", sinoposis=" + sinoposis + ", puntuation=" + puntuation
				+ ", duracionEnMinutos=" + duracionEnMinutos + ", caratula=" + caratula + "]";
	}
	
	
}
