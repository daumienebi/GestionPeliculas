package es.daumienebi.gestionpeliculas.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.controllers.ActorManagementUIController;
import es.daumienebi.gestionpeliculas.utils.TextFieldValidatorUtil;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;
import es.daumienebi.gestionpeliculas.dao.IGenreDAO;
import es.daumienebi.gestionpeliculas.dao.IMovieDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLGenreDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLMovieDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.models.Genre;
import es.daumienebi.gestionpeliculas.models.Movie;
import es.daumienebi.gestionpeliculas.viewmodels.ActorTableModel;
import java.awt.SystemColor;

public class MovieDetailsUI extends JDialog {
	private static String MOVIE_IMAGE_SERVER = DefaultConfiguration.movie_image_server;
	private IMovieDAO movieDAO = new MySQLMovieDAO();
	private IGenreDAO genreDAO = new MySQLGenreDAO();
	private JTextField txtDuration;
	private JTextField txtRating;
	private JTextField txtYear;
	private ArrayList<Actor> actorsList = new ArrayList<>();
	private JTable table;
	private JTextArea txtSynopsis;
	private JTextField txtGenre;
	private JTextArea txtTitle;
	private JButton btnMoviePoster;
	
	/**
	 * To be translated
	 */
	public static JLabel MovieDetail_Title;
	public static JLabel MovieDetail_Rating;
	public static JLabel MovieDetail_Genre;
	public static JLabel MovieDetail_PremiereD;
	public static JLabel MovieDetail_Duration;
	
	
	/**
	 * Controllers
	 */
	private ActorManagementUIController controller = new ActorManagementUIController();
	
	/**
	 * Create the dialog.
	 */
	public MovieDetailsUI(Movie movie) {
		setModal(true);
		Inicialize(movie);
		translate();
	}
	
	void Inicialize(Movie movie) {
		setTitle(movie.getTitulo());
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddMovieUI.class.getResource("/resources/movie_management.png")));
		setBounds(100, 100, 770, 700);
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
				//imgRoute = AddMovieUIController.setImagePoster(btnMoviePoster);
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
		
		MovieDetail_Title = new JLabel("Title:");
		MovieDetail_Title.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8.add(MovieDetail_Title);
		
		JLabel lblNewLabel_4 = new JLabel("             ");
		title_panel.add(lblNewLabel_4);
		
		Panel panel_7 = new Panel();
		title_panel.add(panel_7);
		
		txtTitle = new JTextArea();
		txtTitle.setForeground(SystemColor.textHighlight);
		txtTitle.setEditable(false);
		txtTitle.setLineWrap(true);
		txtTitle.setFont(new Font("Monospaced", Font.BOLD, 20));
		txtTitle.setRows(1);
		txtTitle.setColumns(30);
		panel_7.add(txtTitle);
		
		Panel duration_panel = new Panel();
		FlowLayout flowLayout_3 = (FlowLayout) duration_panel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_5.add(duration_panel);
		
		Panel panel_8_1 = new Panel();
		duration_panel.add(panel_8_1);
		
		MovieDetail_Duration = new JLabel("Duration:");
		MovieDetail_Duration.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_1.add(MovieDetail_Duration);
		
		JLabel lblNewLabel = new JLabel("                        ");
		duration_panel.add(lblNewLabel);
		
		Panel panel_7_1 = new Panel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_7_1.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		duration_panel.add(panel_7_1);
		
		txtDuration = new JTextField();
		txtDuration.setEditable(false);
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
		
		MovieDetail_PremiereD = new JLabel("Premiere Date :");
		MovieDetail_PremiereD.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_2.add(MovieDetail_PremiereD);
		
		JLabel lblNewLabel_5 = new JLabel(" ");
		premiere_panel.add(lblNewLabel_5);
		
		txtYear = new JTextField();
		txtYear.setEditable(false);
		txtYear.setColumns(10);
		premiere_panel.add(txtYear);
		
		Panel rating_panel = new Panel();
		FlowLayout flowLayout_1 = (FlowLayout) rating_panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_5.add(rating_panel);
		
		Panel panel_8_3 = new Panel();
		rating_panel.add(panel_8_3);
		
		MovieDetail_Rating = new JLabel("Rating:");
		MovieDetail_Rating.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_3.add(MovieDetail_Rating);
		
		JLabel lblNewLabel_1 = new JLabel("             ");
		rating_panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("                 ");
		rating_panel.add(lblNewLabel_3);
		
		Panel panel_7_3 = new Panel();
		panel_7_3.setBackground(SystemColor.menu);
		rating_panel.add(panel_7_3);
		
		txtRating = new JTextField();
		txtRating.setEditable(false);
		txtRating.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtRating.setColumns(3);
		panel_7_3.add(txtRating);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(AddMovieUI.class.getResource("/resources/rating.png")));
		rating_panel.add(lblNewLabel_6);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout_7 = (FlowLayout) panel_3.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		flowLayout_7.setHgap(0);
		panel_5.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_3.add(panel_4);
		
		MovieDetail_Genre = new JLabel("Genre:");
		MovieDetail_Genre.setBackground(Color.GRAY);
		panel_4.add(MovieDetail_Genre);
		MovieDetail_Genre.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblNewLabel_7 = new JLabel("                                       ");
		panel_3.add(lblNewLabel_7);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_3.add(panel_6);
		
		txtGenre = new JTextField();
		txtGenre.setEditable(false);
		panel_6.add(txtGenre);
		txtGenre.setColumns(15);
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		Panel panel_1 = new Panel();
		getContentPane().add(panel_1, BorderLayout.EAST);
		
		Panel panel_2 = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 150));
		
		txtSynopsis = new JTextArea();
		txtSynopsis.setEditable(false);
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
		
		setValues(movie);
		tableDoubleClick(table);
	}
	
	void tableDoubleClick(JTable table) {
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		            int id = Integer.valueOf(table.getModel().getValueAt(row, 0).toString());
		            Actor actor = controller.getActor(id);
		            if(actor == null) {
		            	JOptionPane.showMessageDialog(table, "Actor not found","Data not found",JOptionPane.ERROR_MESSAGE);
		            }else {
		            	ActorDetailsUI ui = new ActorDetailsUI(actor);
		            	ui.setLocationRelativeTo(getContentPane());
		            	ui.setVisible(true);
		            }
		        }
		    }
		});
	}
	
	void setValues(Movie movie) {
		txtTitle.setText(movie.getTitulo());
		txtSynopsis.setText(movie.getSinoposis());
		txtDuration.setText(String.valueOf(movie.getDuracionEnMinutos()));
		txtRating.setText(String.valueOf(movie.getPuntuation()));
		if(TextFieldValidatorUtil.isDouble(String.valueOf(movie.getPuntuation()))) {
			double rating = Double.valueOf(String.valueOf(movie.getPuntuation()));
			if(rating < 5) {
				txtRating.setForeground(Color.red);
			}else if(rating >= 5 && rating < 8) {
				txtRating.setForeground(Color.orange);
			}else {
				txtRating.setForeground(Color.green);
			}
		}
		//double rating = Double.value
		txtYear.setText(String.valueOf(movie.getFechaEstreno().toString()));
		Genre genre = genreDAO.getGenre(movie.getId_genero());
		txtGenre.setText(genre.getName());
		btnMoviePoster.setIcon(getImagePoster(movie.getCaratula()));
		loadActorsTable(movie.getId());
	}
	
	void loadActorsTable(int actor_id) {
		actorsList = movieDAO.getActorsPerMovie(actor_id);
		table.setModel(new ActorTableModel(actorsList));
		table.removeColumn(table.getColumnModel().getColumn(0));
		table.removeColumn(table.getColumnModel().getColumn(3));
	}
	
	void translate() {
		if(TranslatorUtil.bundle != null) {
			TranslatorUtil.translateMovieDetailsUI(DefaultConfiguration.lang_id);
		}
	}
	
	public ImageIcon getImagePoster(String imgRoute) {
		URL url = null;
		ImageIcon icon = null;
		ImageIcon default_icon = new ImageIcon(getClass().getResource("/resources/no_image.jpg"));
		try {
			url = new URL(MOVIE_IMAGE_SERVER + imgRoute);
			icon = new ImageIcon(url);
			if(icon.getImageLoadStatus() == MediaTracker.ERRORED) {
				//
			}
			if(icon == null || icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
				icon = default_icon;
			}
			Image img = icon.getImage();
			//Rescale the image
			Image imgNuevo = img.getScaledInstance(200,300,  java.awt.Image.SCALE_SMOOTH );
			icon =new ImageIcon(imgNuevo);
			return icon;
		} catch (Exception e) {
		}
		return null;
		
	}
}
