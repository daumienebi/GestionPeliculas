package es.daumienebi.gestionpeliculas.viewmodels;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import es.daumienebi.gestionpeliculas.models.Pelicula;

public class MovieTableModel extends AbstractTableModel{
	private ArrayList<Pelicula> movieList;
	private String [] columns = {"Title","Rating","Duration","Premiere Date","Poster"};
	
	public MovieTableModel(ArrayList<Pelicula> movieList) {
		this.movieList = movieList;
	}
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return movieList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columns[column];
	}


	/*
	 * Class[] columnTypes = new Class[] {
				Pelicula.class, Pelicula.class, Pelicula.class, Pelicula.class, Icon.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
	 */
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 4: return Icon.class; //returns an icon class when its the Poster Column
		default : return Pelicula.class;
		}
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Pelicula movie = movieList.get(rowIndex);
		switch(columnIndex) {
		case 0: return movie.getTitulo();
		case 1: return movie.getPuntuation();
		case 2: return movie.getDuracionEnMinutos();
		case 3: return movie.getFechaEstreno();
		case 4: return getImagePoster(movie.getCaratula());
		default : return "-";
		}
	}
	
	public ImageIcon getImagePoster(String imgRoute) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imgRoute));
		Image img = icon.getImage();
		//escalar la imagen
		Image imgNuevo = img.getScaledInstance(200,200,  java.awt.Image.SCALE_SMOOTH );
		//volver a asignarle la imagen redimensionada al icono
		icon =new ImageIcon(imgNuevo);
		return icon;
	}
	
	
}
