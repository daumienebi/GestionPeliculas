package es.daumienebi.gestionpeliculas.viewmodels;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import es.daumienebi.gestionpeliculas.models.Genero;

public class GenreTableModel extends AbstractTableModel{

	private ArrayList<Genero> genreList;
	private String [] columns = {"ID","NAME"};
	
	public GenreTableModel(ArrayList<Genero> genreList) {
		this.genreList = genreList;
	}	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return genreList.size();
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
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 4: return Icon.class; //returns an icon class when its the Poster Column
		default : return Pelicula.class;
		}
	}
	*/
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Genero genre = genreList.get(rowIndex);
		switch(columnIndex) {
		case 0: return genre.getId();
		case 1: return genre.getName();
		default : return "-";
		}
	}
	
	
	private ImageIcon getImagePoster(String imgRoute) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imgRoute));
		Image img = icon.getImage();
		//escalar la imagen
		Image imgNuevo = img.getScaledInstance(200,200,  java.awt.Image.SCALE_SMOOTH );
		//volver a asignarle la imagen redimensionada al icono
		icon =new ImageIcon(imgNuevo);
		return icon;
	}
}
