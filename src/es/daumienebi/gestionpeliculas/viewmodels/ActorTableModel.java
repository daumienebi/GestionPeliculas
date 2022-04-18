package es.daumienebi.gestionpeliculas.viewmodels;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import es.daumienebi.gestionpeliculas.models.Actor;

public class ActorTableModel extends AbstractTableModel {
	
	private ArrayList<Actor> actorsList = new ArrayList<>();
	private String [] columns = {"Name","Surname","Date of birth","Image"};
	
	public ActorTableModel(ArrayList<Actor> actorsList) {
		this.actorsList = actorsList;
	}
	
	public ActorTableModel() {
		
	}
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columns[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return actorsList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 3: return Icon.class;
		default : return Actor.class;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Actor actor = actorsList.get(rowIndex);
		
		switch(columnIndex) {
		case 0: return actor.getNombre();
		case 1: return actor.getApellidos();
		case 2: return actor.getFechaNac();
		case 3: return getActorsImage(actor.getFoto());
		default:
			return "-";
		}
	}
	
	private ImageIcon getActorsImage(String imgRoute) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imgRoute));
		Image img = icon.getImage();
		//escalar la imagen
		Image imgNuevo = img.getScaledInstance(150,150,  java.awt.Image.SCALE_SMOOTH );
		//volver a asignarle la imagen redimensionada al icono
		icon =new ImageIcon(imgNuevo);
		return icon;
	}
}
