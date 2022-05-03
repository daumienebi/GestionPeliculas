package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.List;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.daumienebi.gestionpeliculas.viewmodels.ActorTableModel;
import es.daumienebi.gestionpeliculas.controllers.AddMovieUIController;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Pelicula;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddMovieUI extends JDialog {
	private JTextField txtDuration;
	private JTextField txtDay;
	private JTextField txtRating;
	private JTextField txtMonth;
	private JTextField txtYear;
	private ArrayList<Actor> actorsList = new ArrayList<>();
	private JTable table;
	private Pelicula movie;
	private String imgRoute;
	private JTextArea txtSynopsis;
	private JComboBox genreCombo;
	private int id_genero;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovieUI dialog = new AddMovieUI();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AddMovieUI() {
		Inicialize();
		loadGenreCombo();
	}
	/**
	 * Create the dialog.
	 */
	private void Inicialize() {
		setTitle("Add a new movie");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddMovieUI.class.getResource("/resources/movie_management.png")));
		setBounds(100, 100, 898, 700);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		FlowLayout fl_headerPanel = (FlowLayout) headerPanel.getLayout();
		fl_headerPanel.setHgap(10);
		fl_headerPanel.setVgap(20);
		fl_headerPanel.setAlignment(FlowLayout.LEFT);
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		JButton btnMoviePoster = new JButton("");
		btnMoviePoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgRoute = AddMovieUIController.setImagePoster(btnMoviePoster);
			}
		});
		btnMoviePoster.setMargin(new Insets(0, 0, 0, 0));
		btnMoviePoster.setBounds(20,20,200,300);
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/increibles.jpg"));
		Image img = icon.getImage();
		//escalar la imagen
		Image imgNuevo = img.getScaledInstance(btnMoviePoster.getWidth(),btnMoviePoster.getHeight(),  java.awt.Image.SCALE_SMOOTH );
		//volver a asignarle la imagen redimensionada al icono
		icon =new ImageIcon(imgNuevo);
		//panelSuperior.setLayout(null);
		btnMoviePoster.setIcon(icon);
		headerPanel.add(btnMoviePoster);
		
		JButton btnAddPoster = new JButton("Add Poster");
		btnAddPoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgRoute = AddMovieUIController.setImagePoster(btnMoviePoster);
			}
		});
		headerPanel.add(btnAddPoster);
		
		Panel panel_5 = new Panel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		headerPanel.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		Panel title_panel = new Panel();
		FlowLayout flowLayout_4 = (FlowLayout) title_panel.getLayout();
		panel_5.add(title_panel);
		
		Panel panel_8 = new Panel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_8.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		title_panel.add(panel_8);
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("             ");
		title_panel.add(lblNewLabel_4);
		
		Panel panel_7 = new Panel();
		title_panel.add(panel_7);
		
		JTextArea txtTitle = new JTextArea();
		txtTitle.setLineWrap(true);
		txtTitle.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtTitle.setRows(2);
		txtTitle.setColumns(30);
		panel_7.add(txtTitle);
		
		Panel duration_panel = new Panel();
		FlowLayout flowLayout_3 = (FlowLayout) duration_panel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_5.add(duration_panel);
		
		Panel panel_8_1 = new Panel();
		duration_panel.add(panel_8_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Duration:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel = new JLabel("                        ");
		duration_panel.add(lblNewLabel);
		
		Panel panel_7_1 = new Panel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_7_1.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		duration_panel.add(panel_7_1);
		
		txtDuration = new JTextField();
		txtDuration.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDuration.setColumns(4);
		panel_7_1.add(txtDuration);
		
		JLabel lblNewLabel_9 = new JLabel("min.");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		duration_panel.add(lblNewLabel_9);
		
		Panel premiere_panel = new Panel();
		FlowLayout flowLayout_2 = (FlowLayout) premiere_panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_5.add(premiere_panel);
		
		Panel panel_8_2 = new Panel();
		premiere_panel.add(panel_8_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Premiere Date :");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_5 = new JLabel(" ");
		premiere_panel.add(lblNewLabel_5);
		
		Panel panel_7_2 = new Panel();
		premiere_panel.add(panel_7_2);
		
		txtDay = new JTextField();
		txtDay.setColumns(2);
		panel_7_2.add(txtDay);
		
		JLabel lblNewLabel_7 = new JLabel("-");
		premiere_panel.add(lblNewLabel_7);
		
		txtMonth = new JTextField();
		txtMonth.setColumns(2);
		premiere_panel.add(txtMonth);
		
		JLabel lblNewLabel_8 = new JLabel("-");
		premiere_panel.add(lblNewLabel_8);
		
		txtYear = new JTextField();
		txtYear.setColumns(4);
		premiere_panel.add(txtYear);
		
		Panel rating_panel = new Panel();
		FlowLayout flowLayout_1 = (FlowLayout) rating_panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_5.add(rating_panel);
		
		Panel panel_8_3 = new Panel();
		rating_panel.add(panel_8_3);
		
		JLabel lblNewLabel_2_3 = new JLabel("Rating:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_3.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_1 = new JLabel("             ");
		rating_panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("                 ");
		rating_panel.add(lblNewLabel_3);
		
		Panel panel_7_3 = new Panel();
		rating_panel.add(panel_7_3);
		
		txtRating = new JTextField();
		txtRating.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRating.setColumns(3);
		panel_7_3.add(txtRating);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(AddMovieUI.class.getResource("/resources/rating.png")));
		rating_panel.add(lblNewLabel_6);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout_7 = (FlowLayout) panel_3.getLayout();
		flowLayout_7.setHgap(50);
		panel_5.add(panel_3);
		
		JLabel lblNewLabel_11 = new JLabel("Genre");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_3.add(lblNewLabel_11);
		
		genreCombo = new JComboBox();
		panel_3.add(genreCombo);
		genreCombo.setModel(new DefaultComboBoxModel(new String[] {"Sciencia Ficcion", "Miedo", "Comedia", ""}));
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnAddMovie = new JButton("Add Movie");
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id =1;
				String title = txtTitle.getText();
				String synopsis = txtSynopsis.getText();
				double rating = Double.valueOf(txtRating.getText());
				int day =Integer.valueOf(txtDay.getText());
				int month = Integer.valueOf(txtMonth.getText());
				int year =Integer.valueOf(txtYear.getText());
				int duration = Integer.valueOf(txtDuration.getText());
				
				movie = new Pelicula(id,title,synopsis,rating,duration,LocalDate.of(year, month, day),imgRoute,id_genero);
				int response = AddMovieUIController.addMovie(movie);
				if(response == 0) {
					JOptionPane.showMessageDialog(getContentPane(),"The record has been added successfully",""
							,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/resources/tick.jpg")));
				}else
					JOptionPane.showMessageDialog(getContentPane(),"There was an error adding the record","Error",JOptionPane.ERROR_MESSAGE);
					
			}
		});
		panel.add(btnAddMovie);
		
		Panel panel_1 = new Panel();
		getContentPane().add(panel_1, BorderLayout.EAST);
		
		Panel panel_2 = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 150));
		
		
		JButton btnAddActors = new JButton("Add Actors");
		btnAddActors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actorsList.add(new Actor(1,"Derick Daumienebi", "Sakpa",LocalDate.now(),"/resources/damian.jpg"));
				actorsList.add(new Actor(2,"Alejandro", "Bouza Sakpa",LocalDate.now(),"/resources/damian.jpg"));
				table.setModel(new ActorTableModel(actorsList));
			}
		});
		
		JTextArea txtSynopsis = new JTextArea();
		txtSynopsis.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtSynopsis.setLineWrap(true);
		txtSynopsis.setText("Synopsis de prueba para probar jajaj ajaja ajaja ajajaj ajaj ajaja ajajajajaj aDamian");
		txtSynopsis.setRows(3);
		txtSynopsis.setColumns(70);
		panel_2.add(txtSynopsis);
		
		JLabel lblNewLabel_10 = new JLabel("               ");
		panel_2.add(lblNewLabel_10);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBackground(Color.ORANGE);
		scrollPane.setViewportView(table);//para visualizar la cabecera y hacer scroll a los registros
		panel_2.add(scrollPane);
		panel_2.add(btnAddActors);

	}
	void loadGenreCombo() {
		
	}
}
