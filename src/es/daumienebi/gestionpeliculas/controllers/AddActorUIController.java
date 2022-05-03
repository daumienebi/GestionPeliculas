package es.daumienebi.gestionpeliculas.controllers;

import java.awt.MediaTracker;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import es.daumienebi.gestionpeliculas.dao.IActorDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLActorDAO;
import es.daumienebi.gestionpeliculas.models.Actor;

public class AddActorUIController {
	private static IActorDAO actorDAO = new MySQLActorDAO();
	
	public ImageIcon getImage(String imgUrl) {
		URL url = null;
		ImageIcon icon = null;
		ImageIcon default_icon = new ImageIcon(getClass().getResource("/resources/no_image.jpg"));
		try {
			url = new URL(imgUrl);
			icon = new ImageIcon(url);
			if(icon.getImageLoadStatus() == MediaTracker.ERRORED) {
				//JOptionPane.showMessageDialog(null, "Could not retrive the image");
				print("Could not retrieve the image");
			}
			if(icon == null || icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
				return default_icon;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return icon;
		
	}
	
	public int addActor(Actor actor) {
		return actorDAO.addActor(actor);
	}
	
	public void print(Object value) {
		System.out.print(value);
	}
}
