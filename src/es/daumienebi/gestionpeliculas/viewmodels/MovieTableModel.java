package es.daumienebi.gestionpeliculas.viewmodels;

import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.models.Movie;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;

public class MovieTableModel extends AbstractTableModel{
	final static String MOVIE_IMAGE_SERVER = DefaultConfiguration.movie_image_server;
	private ArrayList<Movie> movieList;
	//Resource Bundle to translate the table headers
	ResourceBundle bundle = null;
	private String [] columns = {"Id","Title","Rating","Duration","Premiere Date","Poster"};
	
	public String [] translateColumns() {
		if(TranslatorUtil.bundle != null) {
			bundle = TranslatorUtil.bundle;
			columns[0] = bundle.getString("TableHeader_Id");
			columns[1] = bundle.getString("TableHeader_Title");
			columns[2] = bundle.getString("TableHeader_Rating");
			columns[3] = bundle.getString("TableHeader_Duration");
			columns[4] = bundle.getString("TableHeader_PremiereDate");
			columns[5] = bundle.getString("TableHeader_Poster");
		}
		return columns;
	}
	
	public MovieTableModel(ArrayList<Movie> movieList) {
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
		default : return Movie.class;
		}
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Movie movie = movieList.get(rowIndex);
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
