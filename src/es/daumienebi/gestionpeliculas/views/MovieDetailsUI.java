package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class MovieDetailsUI extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieDetailsUI dialog = new MovieDetailsUI();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public MovieDetailsUI() {
		setBounds(100, 100, 793, 511);
		URL url = getClass().getResource("/resources/avengerGif.Gif");
		ImageIcon img = new ImageIcon(url);
		JLabel lblNewLabel = new JLabel(img);
	}

}
