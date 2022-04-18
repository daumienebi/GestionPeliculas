package es.daumienebi.gestionpeliculas.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;


import es.daumienebi.gestionpeliculas.controllers.MovieManagementUIController;
import es.daumienebi.gestionpeliculas.models.Pelicula;
import es.daumienebi.gestionpeliculas.viewmodels.MovieTableModel;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.Icon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovieManagementUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilter;
	private JTable table;
	private ArrayList<Pelicula> movieList = MovieManagementUIController.getAllMovies();;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MovieManagementUI dialog = new MovieManagementUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			loadMovies();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MovieManagementUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MovieManagementUI.class.getResource("/resources/movie_management.png")));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Movie name :");
		lblNewLabel.setIcon(new ImageIcon(MovieManagementUI.class.getResource("/resources/search.jpg")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel);
		
		txtFilter = new JTextField();
		txtFilter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				MovieTableModel tableModel = new MovieTableModel(MovieManagementUIController.fliterMovie(txtFilter.getText().trim()));
				table.setModel(tableModel);
			}
			
		});
		txtFilter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(txtFilter);
		txtFilter.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		MovieTableModel tableModel = new MovieTableModel(movieList);
		table = new JTable();
		table.setModel(tableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBackground(Color.ORANGE);
		table.setRowHeight(200);
		scrollPane.setViewportView(table);//para visualizar la cabecera y hacer scroll a los registros
		JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnEdit = new JButton("Edit Movie");
			buttonPane.add(btnEdit);
			
			JButton btnDelete = new JButton("Delete Movie");
			buttonPane.add(btnDelete);
			
			
	}
	
	private static void loadMovies() {
		try {
			/*
			movieList = MovieManagementUIController.getAllMovies();
			MovieTableModel tableModel = new MovieTableModel(movieList);
			table.setModel(tableModel);
			*/
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	
}
