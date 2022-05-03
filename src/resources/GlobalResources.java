package resources;

import java.awt.Image;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GlobalResources {
	
	
	public ImageIcon greenTickIcon() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/no_image.jpg"));
		return icon;
	}
	
}
