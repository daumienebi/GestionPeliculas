package es.daumienebi.gestionpeliculas.viewmodels;

import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import es.daumienebi.gestionpeliculas.models.Pelicula;

public class MovieTableModel extends AbstractTableModel{
	final static String MOVIE_IMAGE_SERVER = "http://192.168.56.101/moviemanagement_images/movies/";
	
	private ArrayList<Pelicula> movieList;
	private String [] columns = {"Id","Title","Rating","Duration","Premiere Date","Poster"};
	
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
		case 5: return Icon.class; //returns an icon class when its the Poster Column
		default : return Pelicula.class;
		}
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Pelicula movie = movieList.get(rowIndex);
		switch(columnIndex) {
		case 0: return movie.getId();
		case 1: return movie.getTitulo();
		case 2: return movie.getPuntuation() == 0.0 ? "--" : movie.getPuntuation();
		case 3: return movie.getDuracionEnMinutos();
		case 4: return movie.getFechaEstreno();
		case 5: return getImagePoster(movie.getCaratula());
		default : return "-";
		}
	}
	
	public ImageIcon getImagePoster(String imgRoute) {
		URL url = null;
		ImageIcon icon = null;
		ImageIcon default_icon = new ImageIcon(getClass().getResource("/resources/no_image.jpg"));
		try {
			url = new URL(MOVIE_IMAGE_SERVER + imgRoute);
			icon = new ImageIcon(url);
			if(icon.getImageLoadStatus() == MediaTracker.ERRORED) {
				//
			}
			if(icon == null || icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
				icon = default_icon;
			}
			Image img = icon.getImage();
			//Rescale the image
			Image imgNuevo = img.getScaledInstance(200,200,  java.awt.Image.SCALE_SMOOTH );
			icon =new ImageIcon(imgNuevo);
			return icon;
		} catch (Exception e) {
		}
		return null;
		
	}
	
	public String getPuntuation(double puntuation) {
		if(puntuation == 0.0) {
			return " --- ";
		}else {
			return String.valueOf(puntuation);
		}
	}
}
