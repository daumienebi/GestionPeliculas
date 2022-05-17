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
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;

public class ActorTableModel extends AbstractTableModel {
	final static String ACTOR_IMAGE_SERVER = DefaultConfiguration.actor_image_server;
	private ArrayList<Actor> actorsList = new ArrayList<>();
	private String [] columns = {"Id","Name","Surname","Date of birth","Image"};
	private ResourceBundle bundle = null;
	
	public String [] translateColumns() {
		if(TranslatorUtil.bundle != null) {
			bundle = TranslatorUtil.bundle;
			columns[0] = bundle.getString("TableHeader_Id");
			columns[1] = bundle.getString("TableHeader_Name");
			columns[2] = bundle.getString("TableHeader_Surname");
			columns[3] = bundle.getString("TableHeader_DateOfBirth");
			columns[4] = bundle.getString("TableHeader_Image");
		}
		return columns;
	}
	
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
		case 4: return Icon.class;
		default : return Actor.class;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Actor actor = actorsList.get(rowIndex);
		
		switch(columnIndex) {
		case 0: return actor.getId();
		case 1: return actor.getNombre();
		case 2: return actor.getApellidos();
		case 3: return actor.getFechaNac();
		case 4: return getActorsImage(actor.getFoto());
		default:
			return "-";
		}
	}
	
	private ImageIcon getActorsImage(String imgRoute) {
		URL url = null;
		ImageIcon icon = null;
		ImageIcon default_icon = new ImageIcon(getClass().getResource("/resources/no_image.jpg"));
		try {
			url = new URL(ACTOR_IMAGE_SERVER + imgRoute);
			icon = new ImageIcon(url);
			if(icon.getImageLoadStatus() == MediaTracker.ERRORED) {
				//
			}
			if(icon == null || icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
				icon = default_icon;
			}
			Image img = icon.getImage();
			//Rescale the image
			Image imgNuevo = img.getScaledInstance(150,150,  java.awt.Image.SCALE_SMOOTH );
			icon =new ImageIcon(imgNuevo);
			return icon;
		} catch (Exception e) {
		}
		return null;
	}
}
