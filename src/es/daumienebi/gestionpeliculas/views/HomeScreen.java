package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import es.daumienebi.gestionpeliculas.controllers.HomeScreenController;
import es.daumienebi.gestionpeliculas.dao.mysql.DbConnection;
import resources.RoundedBorder;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;
import java.awt.Toolkit;
import javax.swing.JSlider;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class HomeScreen {

	private JFrame frmGestionPeliculas;
	private JPanel mainPanel;
	int panelHeight,panelWidth;
	private JLabel imgSlider;
	String[] imgList = HomeScreenController.getMovieSliderImages();
	Timer tm;
    int imgPos = 0; //for the image position
    private boolean muteAudio = false; //to be used later for controlling the background audio
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	      
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					HomeScreen window = new HomeScreen();										
					window.frmGestionPeliculas.setResizable(true);
					//GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
					//GraphicsDevice device = graphics.getDefaultScreenDevice();
				    //device.setFullScreenWindow(window.frmGestionPeliculas);
					window.frmGestionPeliculas.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomeScreen(){
		UIManager u = new UIManager();
		/*
		u.setLookAndFeel(UIManager. getCrossPlatformLookAndFeelClassName());
		*/
		//this.frmGestionPeliculas.setDefaultLookAndFeelDecorated(true);
		//frmGestionPeliculas.setUndecorated(true);	
		initialize();
		//frmGestionPeliculas.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
		//frmGestionPeliculas.getRootPane().setBackground(Color.black);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionPeliculas = new JFrame();
		frmGestionPeliculas.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource("/resources/movie_management.png")));
		frmGestionPeliculas.setTitle("Movie Management");
		frmGestionPeliculas.setBounds(100, 100, 1300,800);
		frmGestionPeliculas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionPeliculas.getContentPane().setLayout(new BorderLayout(0, 0));
		frmGestionPeliculas.setLocationRelativeTo(frmGestionPeliculas); //to center the JFrame to the center of the screen
		
		mainPanel = new JPanel() {
			@Override
		     protected void paintComponent(Graphics g)
		     {
		        //super.paintComponent(g);
		        //g.drawImage(a.getImage(), 0, 0, null);
		     }
		};
		frmGestionPeliculas.getContentPane().add(mainPanel);		
		/*
		 * Resize an image
		ImageIcon fondo = new ImageIcon(getClass().getResource("/resources/increibles.jpg"));
		//https://www.youtube.com/watch?v=pN1uoJD_uSE
		Image img = fondo.getImage();
		Image imgNuevo = img.getScaledInstance(350,500,java.awt.Image.SCALE_SMOOTH);
		//volver a asignarle la imagen redimensionada al icono
		ImageIcon a =new ImageIcon(imgNuevo);
		*/
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
		mainPanel.add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		mainPanel.add(panel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		mainPanel.add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		mainPanel.add(panel_3, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 9));
		frmGestionPeliculas.setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("Home");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/exit.jpg")));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem.addActionListener(new ActionListener() {
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
		
		JMenuItem refreshOption = new JMenuItem("Refresh tables");
		refreshOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		refreshOption.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/refresh.jpg")));
		refreshOption.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu.add(refreshOption);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_5 = new JMenu("Actors");
		mnNewMenu_5.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Add new Actor");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActorUI addActor = new AddActorUI();
				addActor.setLocationRelativeTo(frmGestionPeliculas);
				addActor.setVisible(true);
			}
		});
		mntmNewMenuItem_7.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/add_icon.jpg")));
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu_5.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Actors Management");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActorManagementUI actorManagement = new ActorManagementUI();
				actorManagement.setLocationRelativeTo(frmGestionPeliculas);
				actorManagement.setVisible(true);
				
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem_4.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/management.jpg")));
		mnNewMenu_5.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_4 = new JMenu("Movie");
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Add new movie");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem_6.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/add_movie.jpg")));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMovieUI newMovieUI = new AddMovieUI();
				newMovieUI.setModal(true);
				newMovieUI.setLocationRelativeTo(frmGestionPeliculas);
				newMovieUI.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Movie Management");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem_5.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/management.jpg")));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovieManagementUI mngUI = new MovieManagementUI();
				mngUI.setModal(true);
				mngUI.setLocationRelativeTo(frmGestionPeliculas);
				mngUI.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_1 = new JMenu("Genre");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Genre Management");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem_1.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/management.jpg")));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnReports = new JMenu("Reports");
		mnReports.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnReports);
		
		JMenu mnNewMenu_3 = new JMenu("Settings");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_6 = new JMenu("Select Language");
		mnNewMenu_6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu_3.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Spanish");
		mnNewMenu_6.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("English");
		mnNewMenu_6.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Pidgin");
		mnNewMenu_6.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_2 = new JMenu("Help");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Help Contents");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem_3.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/help.jpg")));
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Technical Manual");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem_2.setIcon(new ImageIcon(HomeScreen.class.getResource("/resources/help.jpg")));
		mnNewMenu_2.add(mntmNewMenuItem_2);
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
