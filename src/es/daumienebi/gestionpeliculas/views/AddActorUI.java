package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JDialog;

public class AddActorUI extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddActorUI dialog = new AddActorUI();
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
	public AddActorUI() {
		setBounds(100, 100, 450, 300);
		
	}
	
	public AddActorUI(int x) {
		//get a new contructor to separate the two types of UI to be shown //trying out
	}

}
