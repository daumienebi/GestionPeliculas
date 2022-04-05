package es.daumienebi.gestionpeliculas.models;

import java.time.LocalDate;

public class Actor {

	private String nombre;
	private String apellidos;
	private LocalDate fechaNac;
	private String foto;
		
	public Actor(String nombre, String apellidos, LocalDate fechaNac, String foto) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.foto = foto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "Actor [nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNac=" + fechaNac + ", foto=" + foto
				+ "]";
	}
}
