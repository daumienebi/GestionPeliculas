package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JDialog;

public class ActorDetailsUI extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActorDetailsUI dialog = new ActorDetailsUI();
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
	public ActorDetailsUI() {
		setBounds(100, 100, 450, 300);
		
	}

}
