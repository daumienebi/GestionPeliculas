package es.daumienebi.gestionpeliculas.models;

import java.time.LocalDate;

public class Pelicula {

	private int id;
	private String titulo;
	private String sinoposis;
	private double puntuation;
	private int duracionEnMinutos;
	private LocalDate fechaEstreno;
	private String caratula;
	
	public Pelicula(int id,String titulo, String sinoposis, double puntuation, int duracionEnMinutos, LocalDate fechaEstreno,String caratula) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.sinoposis = sinoposis;
		this.puntuation = puntuation;
		this.duracionEnMinutos = duracionEnMinutos;
		this.fechaEstreno = fechaEstreno;
		this.caratula = caratula;
	}
	
	public Pelicula() {
		
	}	
	
	public int getId() {
		return id;
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
