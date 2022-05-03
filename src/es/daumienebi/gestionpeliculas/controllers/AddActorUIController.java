package es.daumienebi.gestionpeliculas.controllers;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	
	public String setFotoPerfil(JButton btnFoto) {
		// btnFoto -- el boton que servirá para colocar la foto de perfil
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Elegir foto perfil");
		String rutaImagen ="";
		String nombreImagen = "";
		
		//poner el directorio en el escritorio
		String dirEsc = System.getProperty("user.home");
		jfc.setCurrentDirectory(new File(dirEsc + "/Desktop"));
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Filtro imagenes", "png","jpg");
		jfc.setFileFilter(filtro);
		int opcion = jfc.showOpenDialog(jfc);
		if(opcion == JFileChooser.APPROVE_OPTION) {
			rutaImagen = jfc.getSelectedFile().getAbsolutePath();
			nombreImagen = jfc.getSelectedFile().getName();
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
		return nombreImagen; //to save the image name in the db
	}		
		
	public void print(Object value) {
		System.out.print(value);
	}
}
