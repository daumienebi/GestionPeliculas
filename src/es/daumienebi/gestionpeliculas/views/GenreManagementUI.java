package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import es.daumienebi.gestionpeliculas.controllers.ActorManagementUIController;
import es.daumienebi.gestionpeliculas.controllers.GenreManagementUIController;
import es.daumienebi.gestionpeliculas.models.Genre;
import es.daumienebi.gestionpeliculas.viewmodels.ActorTableModel;
import es.daumienebi.gestionpeliculas.viewmodels.GenreTableModel;

import java.awt.BorderLayout;
import java.awt.Color;

public class GenreManagementUI extends JDialog {
	JTable table;
	private ArrayList<Genre> genreList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenreManagementUI dialog = new GenreManagementUI();
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
	public GenreManagementUI() {
		setBounds(100, 100, 307, 526);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		table = new JTable();
		table.setBackground(new Color(245, 245, 220));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(170);
		genreList = GenreManagementUIController.getAllGenres();
		GenreTableModel tableModel = new GenreTableModel(genreList);
		contentPane.setLayout(new BorderLayout(0, 0));
		tableModel.translateColumns();
		table.setModel(tableModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
	}
}
