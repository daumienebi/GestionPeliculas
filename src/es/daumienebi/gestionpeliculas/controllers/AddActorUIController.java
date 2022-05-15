package es.daumienebi.gestionpeliculas.controllers;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.dao.IActorDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLActorDAO;
import es.daumienebi.gestionpeliculas.models.Actor;

public class AddActorUIController {
	private static IActorDAO actorDAO = new MySQLActorDAO();
	private static String ACTOR_IMAGE_SERVER = DefaultConfiguration.actor_image_server;
	
	public ImageIcon getActorsImage(String imgRoute) {
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
	
	public int addActor(Actor actor) {
		return actorDAO.addActor(actor);
	}
	
	public File setFotoPerfil(JButton btnFoto) {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Elegir foto perfil");
		String rutaImagen ="";
		String nombreImagen = "";
		File imgFile = null;
		
		//user desktop directory
		String dirEsc = System.getProperty("user.home");
		jfc.setCurrentDirectory(new File(dirEsc + "/Desktop"));
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Image filter", "png","jpg");
		jfc.setFileFilter(filtro);
		int opcion = jfc.showOpenDialog(jfc);
		if(opcion == JFileChooser.APPROVE_OPTION) {
			rutaImagen = jfc.getSelectedFile().getAbsolutePath();
			imgFile = jfc.getSelectedFile();
			//nombreImagen = jfc.getSelectedFile().getName();
		}
		
		//comprobar que rutaImagen no esta vacio antes de colocar la imagen para que no se
		//descolque el contenedor
		if(!rutaImagen.isEmpty()) {
			//Obtengo El icono por defecto que quiero usar
			ImageIcon icon = new ImageIcon(rutaImagen);
			Image img = icon.getImage();
			//escalar la imagen
			Image imgNuevo = img.getScaledInstance(btnFoto.getWidth(),btnFoto.getHeight(),java.awt.Image.SCALE_SMOOTH );
			//volver a asignarle la imagen redimensionada al icono
			icon =new ImageIcon(imgNuevo);
			btnFoto.setIcon(icon);
		}
		return imgFile; //to save the image name in the db
	}		
		
	public int modifyActor(Actor actor) {
		return actorDAO.modifyActor(actor);
	}
	
	public void print(Object value) {
		System.out.print(value);
	}
}
