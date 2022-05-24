package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.daumienebi.gestionpeliculas.config.Configuration;
import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.controllers.ConfigUIControlller;
import es.daumienebi.gestionpeliculas.controllers.HomeScreenController;
import es.daumienebi.gestionpeliculas.dao.mysql.DbConnection;
import es.daumienebi.gestionpeliculas.utils.ReportsUtil;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.SystemColor;

public class HomeScreen {

	private JFrame frmGestionPeliculas;
	private JPanel mainPanel;
	private JLabel imgSlider;
	
	//Generate Java Help
	private HelpBroker browser;
	HelpSet helpset;    
	
	//Components to be translated
	public static JMenu genreMenu;
	public static JMenu homeMenu;
	public static JMenu settingsMenu;
	public static JMenu helpMenu;
	public static JMenu reportMenu;
	public static JMenu movieMenu;
	public static JMenu actorMenu;
	public static JMenuItem refreshGui;
	public static JMenuItem menuConfigCon;
	public static JMenu dbMenu;
	public static JMenuItem menuOptionAddActor;
	public static JMenuItem menuTechManual;
	public static JMenuItem menuHelpContents;
	public static JMenuItem menuOptionEnglish;
	public static JMenuItem menuOptionSpanish;
	public static JMenuItem menuPersonalizedReports;
	public static JMenuItem BasicReports_Movies;
	public static JMenuItem BasicReports_Actors;
	public static JMenuItem BasicReports_Genres;
	public static JMenu menuOptionBasicReports;
	public static JMenuItem menuOptionMovieMng;
	public static JMenuItem menuOptionAddMovie;
	public static JMenuItem menuOptionRefresh;
	public static JMenuItem menuoptionExit;
	public static JMenuItem menuOptionActorMng;
	public static JMenuItem menuOptionGenreMng;
	public static JMenu menuSelectLanguage;
	
	
	String[] imgList = HomeScreenController.getMovieSliderImages();
	ConfigUIControlller configController = new ConfigUIControlller();
	
	Timer tm;
    int imgPos = 0; //for the image position
    private JButton btnBoton;
    private JTextField txtCampo;
    //private boolean muteAudio = false; //to be used later for controlling the background audio
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	      
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					HomeScreen window = new HomeScreen();										
					window.frmGestionPeliculas.setResizable(true);
					window.frmGestionPeliculas.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	void disableMenus() {
		if(DbConnection.getConnection() == null) { //Configuration.use_default_config == -1
			homeMenu.setEnabled(false);
			actorMenu.setEnabled(false);
			movieMenu.setEnabled(false);
			genreMenu.setEnabled(false);
			reportMenu.setEnabled(false);
			//helpMenu.setEnabled(false);
			settingsMenu.setEnabled(false);
		}else {
			homeMenu.setEnabled(true);
			actorMenu.setEnabled(true);
			movieMenu.setEnabled(true);
			genreMenu.setEnabled(true);
			reportMenu.setEnabled(true);
			//helpMenu.setEnabled(true);
			settingsMenu.setEnabled(true);
		}
	}
	
	public HomeScreen(){
		//Load the config file values
		//configController.loadConfig();
		//HomeScreenController.setConfig();
		
		initialize();
		//DbConnection.connect(); //not connecting at the beginning to avoid unnecessary blank screen
		generateHelp();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionPeliculas = new JFrame();
		frmGestionPeliculas.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource("/resources/movie_management.png")));
		//frmGestionPeliculas.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource("../help/movie_management.png")));

		frmGestionPeliculas.setTitle("Movie Management");
		frmGestionPeliculas.setBounds(100, 100, 1300,800);
		frmGestionPeliculas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionPeliculas.getContentPane().setLayout(new BorderLayout(0, 0));
		frmGestionPeliculas.setLocationRelativeTo(frmGestionPeliculas); //to center the JFrame to the center of the screen
		
		mainPanel = new JPanel();
		frmGestionPeliculas.getContentPane().add(mainPanel);		
		//https://www.youtube.com/watch?v=pN1uoJD_uSE -- image slider
		imgSlider = new JLabel();
		imgSlider.setHorizontalAlignment(SwingConstants.CENTER);
		imgSlider.setBounds(40, 30, 700, 300);
        SetImageSize(5);
        //Set a timer to slide through the images
        tm = new Timer(2500,new ActionListener() {
        	//add only
            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(imgPos);
                imgPos += 1;
                if(imgPos >= imgList.length)
                	imgPos = 0; 
            }
        });
		tm.start();
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.add(imgSlider);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		mainPanel.add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 204, 204));
		mainPanel.add(panel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 204, 204));
		mainPanel.add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(204, 255, 255));
		mainPanel.add(panel_3, BorderLayout.NORTH);
		
		btnBoton = new JButton("New button");
		btnBoton.setVisible(false);
		panel_3.add(btnBoton);
		
		txtCampo = new JTextField();
		txtCampo.setVisible(false);
		panel_3.add(txtCampo);
		txtCampo.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		frmGestionPeliculas.setJMenuBar(menuBar);
		
		dbMenu = new JMenu("Connection");
		dbMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(dbMenu);
		
		menuConfigCon = new JMenuItem("Configure Connection");
		menuConfigCon.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuConfigCon.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/management.jpg")));
		menuConfigCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigUI configUI = new ConfigUI();
				configUI.setLocationRelativeTo(frmGestionPeliculas);
				configUI.setVisible(true);
				
				if(DbConnection.getConnection() != null) {
					configUI.dispose();
					frmGestionPeliculas.dispose();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					HomeScreen window = new HomeScreen();										
					window.frmGestionPeliculas.setResizable(true);
					//HomeScreenController.setConfig();
					disableMenus();
					window.frmGestionPeliculas.setVisible(true);
				}
			}
		});
		dbMenu.add(menuConfigCon);
		
		refreshGui = new JMenuItem("Refresh GUI");
		refreshGui.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/refresh.jpg")));
		refreshGui.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		refreshGui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(" value before :" + Configuration.use_default_connection);
				frmGestionPeliculas.dispose();
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HomeScreen window = new HomeScreen();										
				window.frmGestionPeliculas.setResizable(true);
				//System.out.println(" value before :" + Configuration.use_default_connection);
				disableMenus();
				//System.out.println(" value after :" + Configuration.use_default_connection);
				window.frmGestionPeliculas.setVisible(true);
			}
		});
		dbMenu.add(refreshGui);
		homeMenu = new JMenu("Home");
		homeMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(homeMenu);
		
		menuoptionExit = new JMenuItem("Exit");
		menuoptionExit.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/exit.jpg")));
		menuoptionExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuoptionExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close the window
				int option;
				//JOptionPane.setDefaultLocale(Locale.ENGLISH);
				option =JOptionPane.showConfirmDialog(frmGestionPeliculas,"Are you sure you want to exit the app ?","Exit",JOptionPane.INFORMATION_MESSAGE);
				
				if(option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}else {}
					//do nothing				
			}
		});
		
		menuOptionRefresh = new JMenuItem("Refresh tables");
		menuOptionRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuOptionRefresh.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/refresh.jpg")));
		menuOptionRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		homeMenu.add(menuOptionRefresh);
		homeMenu.add(menuoptionExit);
		
		actorMenu = new JMenu("Actors");
		actorMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(actorMenu);
		menuOptionAddActor = new JMenuItem("Add new Actor");
		menuOptionAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActorUI addActor = new AddActorUI();
				addActor.setLocationRelativeTo(frmGestionPeliculas);
				addActor.setVisible(true);
			}
		});
		menuOptionAddActor.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/add_icon.jpg")));
		menuOptionAddActor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		actorMenu.add(menuOptionAddActor);
		
		menuOptionActorMng = new JMenuItem("Actors Management");
		menuOptionActorMng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActorManagementUI actorManagement = new ActorManagementUI();
				actorManagement.setLocationRelativeTo(frmGestionPeliculas);
				actorManagement.setVisible(true);				
			}
		});
		menuOptionActorMng.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuOptionActorMng.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/management.jpg")));
		actorMenu.add(menuOptionActorMng);
		
		movieMenu = new JMenu("Movies");
		movieMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(movieMenu);
		
		menuOptionAddMovie = new JMenuItem("Add new movie");
		menuOptionAddMovie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuOptionAddMovie.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/add_movie.jpg")));
		menuOptionAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMovieUI newMovieUI = new AddMovieUI();
				newMovieUI.setModal(true);
				newMovieUI.setLocationRelativeTo(frmGestionPeliculas);
				newMovieUI.setVisible(true);
			}
		});
		movieMenu.add(menuOptionAddMovie);
 		
		menuOptionMovieMng = new JMenuItem("Movie Management");
		menuOptionMovieMng.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuOptionMovieMng.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/management.jpg")));
		menuOptionMovieMng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovieManagementUI mngUI = new MovieManagementUI();
				mngUI.setModal(true);
				mngUI.setLocationRelativeTo(frmGestionPeliculas);
				mngUI.setVisible(true);
			}
		});
		movieMenu.add(menuOptionMovieMng);
		
		genreMenu = new JMenu("Genres");
		genreMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(genreMenu);
		
		menuOptionGenreMng = new JMenuItem("Genre Management");
		menuOptionGenreMng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenreManagementUI genreMng = new GenreManagementUI();
				genreMng.setModal(true);
				genreMng.setLocationRelativeTo(frmGestionPeliculas);
				genreMng.setVisible(true);
			}			
		});
		menuOptionGenreMng.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuOptionGenreMng.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/management.jpg")));
		genreMenu.add(menuOptionGenreMng);
		
		reportMenu = new JMenu("Reports");
		reportMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(reportMenu);
		
		menuOptionBasicReports = new JMenu("Basic Table Reports");
		reportMenu.add(menuOptionBasicReports);
		
		BasicReports_Genres = new JMenuItem("Genres");
		menuOptionBasicReports.add(BasicReports_Genres);
		
		BasicReports_Actors = new JMenuItem("Actors");
		BasicReports_Actors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reportName = "Actors.jrxml";
				ReportsUtil.viewReport(reportName);
			}
		});
		menuOptionBasicReports.add(BasicReports_Actors);
		
		BasicReports_Movies = new JMenuItem("Movies");
		BasicReports_Movies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reportName = "Movies.jrxml";
				ReportsUtil.viewReport(reportName);
			}
		});
		menuOptionBasicReports.add(BasicReports_Movies);
		BasicReports_Genres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//https://es.stackoverflow.com/questions/424596/el-sistema-no-puede-encontrar-el-archivo-especificado-jasperreport-java
				String reportName =  "Genres.jrxml";
				ReportsUtil.viewReport(reportName);
			}
		});
		
		menuPersonalizedReports = new JMenuItem("Personalized Reports");
		menuPersonalizedReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonalizedReportsUI reportsUI = new PersonalizedReportsUI();
				reportsUI.setModal(true);
				reportsUI.setLocationRelativeTo(frmGestionPeliculas);
				reportsUI.setVisible(true);
			}
		});
		reportMenu.add(menuPersonalizedReports);
		
		settingsMenu = new JMenu("Settings");
		settingsMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(settingsMenu);
		
		menuSelectLanguage = new JMenu("Select Language");
		menuSelectLanguage.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		settingsMenu.add(menuSelectLanguage);
		
		menuOptionSpanish = new JMenuItem("Spanish");
		menuOptionSpanish.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/esp_icon.jpg")));
		menuOptionSpanish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultConfiguration.lang_id = 2;
				int lang_id = DefaultConfiguration.lang_id;
				TranslatorUtil.translateHomeScreen(lang_id);
			}
		});
		menuSelectLanguage.add(menuOptionSpanish);
		
		menuOptionEnglish = new JMenuItem("English");
		menuOptionEnglish.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/gb_icon.jpg")));
		menuOptionEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultConfiguration.lang_id = 1;
				int lang_id = DefaultConfiguration.lang_id;
				TranslatorUtil.translateHomeScreen(lang_id);
			}
		});
		menuSelectLanguage.add(menuOptionEnglish);
		
		helpMenu = new JMenu("Help");
		helpMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(helpMenu);
		
		menuHelpContents = new JMenuItem("Help Contents");
		menuHelpContents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuHelpContents.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuHelpContents.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/help.jpg")));
		helpMenu.add(menuHelpContents);
		
		menuTechManual = new JMenuItem("Technical Manual");
		menuTechManual.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuTechManual.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/help.jpg")));
		helpMenu.add(menuTechManual);
		
		disableMenus();
		//disableTranslate();

		
		/**		
		frmGestionPeliculas.addKeyListener(new KeyAdapter () { 
	         @Override
	         public void keyPressed(KeyEvent e) {
	              if ( e.equals(KeyEvent.VK_F1 ) ) {
	                   // Do something
	            	  System.out.print("F1 is pressed");
	            	  generateHelp();
	              }
	         }
	    });
	    */
	}
	
	/*
	void disableTranslate() {
		if(DefaultConfiguration.lang_id == 1){
			menuOptionEnglish.setEnabled(false);
		}
		if(DefaultConfiguration.lang_id == 2){
			menuOptionSpanish.setEnabled(false);
		}
	}
	*/
	
	void generateHelp() {
		try 
        {
			//Definición del fichero de configuración (tipo HelpSet)
            URL helpURL = this.getClass().getResource("../help/help.hs"); 
            helpset = new HelpSet(null, helpURL);
            
            //Definición del objeto del visor (tipo HelpBroker)            
            browser = helpset.createHelpBroker();
            
            //Definición de botón de inicio de ayuda. El menú
            browser.enableHelpOnButton(menuHelpContents, "manual", helpset);
            
            //Definición de elementos con los que F1 abrirá la ayuda en determinadas páginas
            browser.enableHelpKey(btnBoton, "archivo", helpset);
            browser.enableHelpKey(txtCampo, "menu", helpset);     
            //frmGestionPeliculas.getContentPane().setLayout(null);            
        } 
        catch (HelpSetException ex) 
        {
            ex.printStackTrace();
        }
	}
	
	public void SetImageSize(int index){
		Image img = null;
		Image newImg;
		ImageIcon icon = new ImageIcon(getClass().getResource(imgList[index]));
		img = icon.getImage();
		newImg = img.getScaledInstance(imgSlider.getWidth(), imgSlider.getHeight(), Image.SCALE_SMOOTH);        
        ImageIcon finalImg = new ImageIcon(newImg);
        imgSlider.setIcon(finalImg);
    }
}
