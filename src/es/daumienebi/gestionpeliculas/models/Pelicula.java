package es.daumienebi.gestionpeliculas.models;

import java.time.LocalDate;

public class Pelicula {

	private String titulo;
	private String sinoposis;
	private int puntuation;
	private int duracionEnMinutos;
	private LocalDate fechaEstreno;
	private String caratula;
	
	public Pelicula(String titulo, String sinoposis, int puntuation, int duracionEnMinutos, LocalDate fechaEstreno,String caratula) {
		super();
		this.titulo = titulo;
		this.sinoposis = sinoposis;
		this.puntuation = puntuation;
		this.duracionEnMinutos = duracionEnMinutos;
		this.fechaEstreno = fechaEstreno;
		this.caratula = caratula;
	}
	
	public Pelicula() {
		
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
	public int getPuntuation() {
		return puntuation;
	}
	public void setPuntuation(int puntuation) {
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
		return "Pelicula [titulo=" + titulo + ", sinoposis=" + sinoposis + ", puntuation=" + puntuation
				+ ", duracionEnMinutos=" + duracionEnMinutos + ", caratula=" + caratula + "]";
	}
	
	
}
