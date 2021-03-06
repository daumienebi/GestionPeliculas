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
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTable;
import es.daumienebi.gestionpeliculas.viewmodels.ActorTableModel;
import es.daumienebi.gestionpeliculas.controllers.AddMovieUIController;
import es.daumienebi.gestionpeliculas.utils.TextFieldValidatorUtil;
import es.daumienebi.gestionpeliculas.utils.UploadImageUtil;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Genre;
import es.daumienebi.gestionpeliculas.models.Movie;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddMovieUI extends JDialog {
	
	/**
	 * Components
	 */
	private JTextField txtDuration;
	private JTextField txtDay;
	private JTextField txtRating;
	private JTextField txtMonth;
	private JTextField txtYear;
	private JTable table;
	private JTextArea txtTitle;
	private JTextArea txtSynopsis;
	private JComboBox<Genre> genreCombo;
	private JButton btnMoviePoster;
	
	private Movie movie;
	private String posterImgName ="";
	private File imgFile;
	private int id_genero;
	
	
	/**
	 * To be translated
	 */
	public static JButton AddMovie_btnAddPoster;
	public static JButton AddMovie_btnAddMovie;
	public static JButton AddMovie_btnSaveMovie;
	public static JButton AddMovie_btnAddActors;
	public static JLabel AddMovie_valid_lblRating;
	public static String AddMovie_windowTitle = "Add new movie";
	public static JLabel AddMovie_lblTitle;
	public static JLabel AddMovie_lblDuration;
	public static JLabel AddMovie_lblRating;
	public static JLabel AddMovie_lblPremiereD;
	public static JLabel AddMovie_lblGenre;
	
	/**
	 * Collections
	 */
	private ArrayList<Actor> actorsList = new ArrayList<>();
	
	/**
	 * Controllers
	 */
	AddMovieUIController controller = new AddMovieUIController();
	
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
		setMaximumSize(new Dimension(900, 700));
		setDefaultCloseOperation(AddMovieUI.DISPOSE_ON_CLOSE);
		Inicialize();
		controller.translate();
		setTitle(AddMovie_windowTitle);
		loadGenreCombo();
	}
	
	public AddMovieUI(Movie movie) {
		//Instance to Edit a movie
		
		this();
		setDefaultCloseOperation(AddMovieUI.DISPOSE_ON_CLOSE);
		this.movie = movie;
		posterImgName = movie.getCaratula();
		AddMovie_btnAddMovie.setVisible(false);
		AddMovie_btnSaveMovie.setVisible(true);
		AddMovie_btnAddActors.setVisible(false);
		AddMovie_valid_lblRating.setVisible(true);
		setTitle("Edit Movie");
		AddMovie_btnAddMovie.setText("Save movie");
		txtTitle.setText(movie.getTitulo());
		int day,month,year; //date values
		txtSynopsis.setText(movie.getSinoposis());
		txtDuration.setText(String.valueOf(movie.getDuracionEnMinutos()));
		txtRating.setText(String.valueOf(movie.getPuntuation()));
		
		//get the genre
		Genre genre = controller.getMovieGenre(movie.getId_genero());
		
		if(genre != null) {
			
			genreCombo.setSelectedItem(genre);
			genreCombo.updateUI();
			//txtGenre.setText(genre.getName());
		}
		
		
		//get the list of actors
		actorsList = controller.getActorsPerMovie(movie.getId());
		table.setModel(new ActorTableModel(actorsList));
		table.removeColumn(table.getColumnModel().getColumn(0));
		table.removeColumn(table.getColumnModel().getColumn(3));
		
		//set the image
		btnMoviePoster.setIcon(controller.getImagePoster(movie.getCaratula()));
		AddMovie_btnAddPoster.setText("Edit image");
		
		//premiere date
		day = movie.getFechaEstreno().getDayOfMonth();
		month = movie.getFechaEstreno().getMonthValue();
		year = movie.getFechaEstreno().getYear();
		txtDay.setText(String.valueOf(day));
		txtMonth.setText(String.valueOf(month));
		txtYear.setText(String.valueOf(year));
		
		/*
		btnSaveMovie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validateMovie_Edit(movie);				
			}
		});
		*/	
	}
	/**
	 * Create the dialog.
	 */
	private void Inicialize() {
		setTitle("Add a new movie");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddMovieUI.class.getResource("/resources/movie_management.png")));
		setBounds(100, 100, 900, 700);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		FlowLayout fl_headerPanel = (FlowLayout) headerPanel.getLayout();
		fl_headerPanel.setHgap(10);
		fl_headerPanel.setVgap(20);
		fl_headerPanel.setAlignment(FlowLayout.LEFT);
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		btnMoviePoster = new JButton("");
		btnMoviePoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgFile = controller.setImagePoster(btnMoviePoster);
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
		
		AddMovie_btnAddPoster = new JButton("Add Poster");
		AddMovie_btnAddPoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgFile = controller.setImagePoster(btnMoviePoster);
			}
		});
		headerPanel.add(AddMovie_btnAddPoster);
		
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
		
		AddMovie_lblTitle = new JLabel("Title:");
		AddMovie_lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8.add(AddMovie_lblTitle);
		
		JLabel lblNewLabel_4 = new JLabel("             ");
		title_panel.add(lblNewLabel_4);
		
		Panel panel_7 = new Panel();
		title_panel.add(panel_7);
		
		txtTitle = new JTextArea();
		txtTitle.setLineWrap(true);
		txtTitle.setFont(new Font("Monospaced", Font.PLAIN, 20));
		txtTitle.setRows(1);
		txtTitle.setColumns(30);
		panel_7.add(txtTitle);
		
		Panel duration_panel = new Panel();
		FlowLayout flowLayout_3 = (FlowLayout) duration_panel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_5.add(duration_panel);
		
		Panel panel_8_1 = new Panel();
		duration_panel.add(panel_8_1);
		
		AddMovie_lblDuration = new JLabel("Duration:");
		AddMovie_lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_1.add(AddMovie_lblDuration);
		
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
		
		AddMovie_lblPremiereD = new JLabel("Premiere Date :");
		AddMovie_lblPremiereD.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_2.add(AddMovie_lblPremiereD);
		
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
		
		AddMovie_lblRating = new JLabel("Rating:");
		AddMovie_lblRating.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_3.add(AddMovie_lblRating);
		
		JLabel lblNewLabel_1 = new JLabel("             ");
		rating_panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("                 ");
		rating_panel.add(lblNewLabel_3);
		
		Panel panel_7_3 = new Panel();
		rating_panel.add(panel_7_3);
		
		txtRating = new JTextField();
		txtRating.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(TextFieldValidatorUtil.isDouble(txtRating.getText())) {
					double rating = Double.parseDouble(txtRating.getText());
					if(rating > 10 || rating < 0) {
						AddMovie_valid_lblRating.setVisible(true);
						AddMovie_valid_lblRating.setText("Cannot be > 10 or < 0");
						AddMovie_valid_lblRating.setForeground(Color.red);
					}else {
						AddMovie_valid_lblRating.setVisible(false);
					}
				}
				
			}
		});
		txtRating.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRating.setColumns(3);
		panel_7_3.add(txtRating);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(AddMovieUI.class.getResource("/resources/rating.png")));
		rating_panel.add(lblNewLabel_6);
		
		AddMovie_valid_lblRating = new JLabel("");
		rating_panel.add(AddMovie_valid_lblRating);
		AddMovie_valid_lblRating.setVisible(false);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout_7 = (FlowLayout) panel_3.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		flowLayout_7.setHgap(0);
		panel_5.add(panel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_3.add(panel_6);
		
		AddMovie_lblGenre = new JLabel("Genre:");
		panel_6.add(AddMovie_lblGenre);
		AddMovie_lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblNewLabel_12 = new JLabel("                                    ");
		panel_6.add(lblNewLabel_12);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_3.add(panel_4);
		
		genreCombo = new JComboBox();
		panel_4.add(genreCombo);
		genreCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id_genero = ((Genre)genreCombo.getSelectedItem()).getId();
				//String genre = ((Genre)genreCombo.getSelectedItem()).getName();
				//txtGenre.setText(genre);
			}
		});
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		AddMovie_btnAddMovie = new JButton("Add Movie");
		AddMovie_btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateMovie_Add();				
			}
		});
		panel.add(AddMovie_btnAddMovie);
		
		AddMovie_btnSaveMovie = new JButton("Save Movie");
		AddMovie_btnSaveMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateMovie_Edit(movie);
			}
		});
		panel.add(AddMovie_btnSaveMovie);
		
		Panel panel_2 = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 150));
		
		
		AddMovie_btnAddActors = new JButton("Add Actors");
		AddMovie_btnAddActors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActorToMovieUI ui = new AddActorToMovieUI();
				ui.setLocationRelativeTo(getContentPane());
				ui.setVisible(true);
				ArrayList<Actor>aux_actorsList = ui.getSelectedActors();
				if(aux_actorsList.size() >0 && aux_actorsList != null) {
					for(Actor actor : aux_actorsList) {
						actorsList.add(actor);
					}
					table.setModel(new ActorTableModel(actorsList));
					table.removeColumn(table.getColumnModel().getColumn(0));
					table.removeColumn(table.getColumnModel().getColumn(3));
				}
			}
		});
		
		txtSynopsis = new JTextArea();
		txtSynopsis.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtSynopsis.setLineWrap(true);
		txtSynopsis.setText("Un grupo de jovenes que intentan viajar a la tierra de Damian a lo mas lejos posibile");
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
		panel_2.add(AddMovie_btnAddActors);
		AddMovie_btnSaveMovie.setVisible(false);
	}
	
	void validateMovie_Add() {
		int id =0;//any number since it's auto-generated in the database
		String title = txtTitle.getText();
		String synopsis = txtSynopsis.getText();				
		LocalDate premiere_date = null;
		int duration =0;double rating= 0;
		boolean uploaded = false;
		
		if(TextFieldValidatorUtil.isDouble(txtRating.getText())) {
			rating = Double.valueOf(txtRating.getText());
		}
		if(TextFieldValidatorUtil.isNumeric(txtDuration.getText())) {
			duration = Integer.valueOf(txtDuration.getText());
		}
		boolean validDate = false;
		//validate the date
		if(TextFieldValidatorUtil.isNumeric(txtDay.getText()) && TextFieldValidatorUtil.isNumeric(txtMonth.getText()) && TextFieldValidatorUtil.isNumeric(txtYear.getText())) {
			int day =Integer.parseInt(txtDay.getText());
			int month =Integer.parseInt(txtMonth.getText());
			int year =Integer.parseInt(txtYear.getText());
			
			if(TextFieldValidatorUtil.isValidDate(day, month, year) && year >1800 && year <9999) {
				premiere_date = LocalDate.of(year, month, day);
				validDate = true; //year takes more than 9999 -- fix that
			}else JOptionPane.showMessageDialog(getContentPane(),"Incorrect Date Format","Error",JOptionPane.ERROR_MESSAGE);
		}				
		if(!title.isBlank() && !synopsis.isBlank() && duration >0 && validDate && id_genero > 0 && rating <= 10 && rating > 0) {
			movie = new Movie(id,title,synopsis,rating,duration,premiere_date,posterImgName,id_genero);
			//upload the image to the server
			Object [] uploadResult = new Object[2];
			if(imgFile != null) {
				uploadResult = UploadImageUtil.uploadMovieImage(imgFile);
				uploaded = Boolean.parseBoolean(uploadResult[0].toString());
				posterImgName = uploadResult[1].toString();
				if(uploaded) {
					movie.setCaratula(posterImgName);
					addMovie(movie, actorsList);
				}else {
					JOptionPane.showMessageDialog(getContentPane(),"There was an error uploading the image","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				movie.setCaratula("");
				addMovie(movie, actorsList);
			}	
		}else
			JOptionPane.showMessageDialog(getContentPane(),"Please fill in the necessary fields correctly","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	void validateMovie_Edit(Movie movie) {
		int id =0;//any number since it's auto-generated in the database
		String title = txtTitle.getText();
		String synopsis = txtSynopsis.getText();				
		LocalDate premiere_date = null;
		int duration =0;double rating= 0;
		
		if(TextFieldValidatorUtil.isDouble(txtRating.getText())) {
			rating = Double.valueOf(txtRating.getText());
		}
		if(TextFieldValidatorUtil.isNumeric(txtDuration.getText())) {
			duration = Integer.valueOf(txtDuration.getText());
		}
		boolean validDate = false;
		//validate the date
		if(TextFieldValidatorUtil.isNumeric(txtDay.getText()) && TextFieldValidatorUtil.isNumeric(txtMonth.getText()) && TextFieldValidatorUtil.isNumeric(txtYear.getText())) {
			int day =Integer.parseInt(txtDay.getText());
			int month =Integer.parseInt(txtMonth.getText());
			int year =Integer.parseInt(txtYear.getText());
			
			if(TextFieldValidatorUtil.isValidDate(day, month, year) && year >1800 && year <9999) {
				premiere_date = LocalDate.of(year, month, day);
				validDate = true; //year takes more than 9999 -- fix that
			}else JOptionPane.showMessageDialog(getContentPane(),"Incorrect Date Format","Error",JOptionPane.ERROR_MESSAGE);
		}				
		if(!title.isBlank() && !synopsis.isBlank() && duration >0 && validDate && id_genero > 0 && rating <= 10 && rating > 0) {
			//movie = new Movie(id,title,synopsis,rating,duration,premiere_date,imgRoute,id_genero);
			if(imgFile != null) {
				Object [] uploadResult = new Object[2];
				uploadResult = UploadImageUtil.uploadMovieImage(imgFile);
				
				boolean uploaded = Boolean.parseBoolean(uploadResult[0].toString());
				posterImgName = uploadResult[1].toString();
				
				if(uploaded) {
					editMovie(movie, title, synopsis, duration, rating);
				}else {
					JOptionPane.showMessageDialog(getContentPane(),"There was an error uploading the image","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				posterImgName = movie.getCaratula();
				editMovie(movie, title, synopsis, duration, rating);
			}
		}else
			JOptionPane.showMessageDialog(getContentPane(),"Fill in the necessary fields correctly","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	void editMovie(Movie movie,String title,String synopsis,int duration,double rating) {
		//to be called internally by validateMovie_Edit()
		movie.setTitulo(title);
		movie.setSinoposis(synopsis);
		movie.setCaratula(posterImgName);
		movie.setDuracionEnMinutos(duration);
		movie.setPuntuation(rating);
		movie.setId_genero(id_genero);
		int response = controller.modifyMovie(movie);
		if(response == 0) {
			JOptionPane.showMessageDialog(getContentPane(),"The movie has been edited and saved successfully",""
					,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/resources/tick.jpg")));
			dispose();
		}else
			JOptionPane.showMessageDialog(getContentPane(),"There was an error saving the record","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	void addMovie(Movie movie,ArrayList<Actor> actorsList) {
		//to be called internally by validateMovie_Add()
		int response = controller.addMovie(movie,actorsList);
		if(response == 0) {
			JOptionPane.showMessageDialog(getContentPane(),"The movie has been added successfully",""
					,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/resources/tick.jpg")));
			dispose();
		}else
			JOptionPane.showMessageDialog(getContentPane(),"There was an error adding the record","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	void loadGenreCombo() {
		for(Genre genre : controller.getAllGenres()) {
			genreCombo.addItem(genre);
		}
	}
}
