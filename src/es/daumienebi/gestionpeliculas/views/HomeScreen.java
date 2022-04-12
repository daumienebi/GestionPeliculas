package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import es.daumienebi.gestionpeliculas.controllers.PeliculaController;
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
	String[] imgList = PeliculaController.getMovieSliderImages();
	Timer tm;
    int imgPos = 0; //for the image position
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
		frmGestionPeliculas.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource("/resources/logo2.png")));
		frmGestionPeliculas.setTitle("Movie Management");
		frmGestionPeliculas.setBounds(100, 100, 1024,700);
		frmGestionPeliculas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionPeliculas.getContentPane().setLayout(new BorderLayout(0, 0));
		
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
        tm = new Timer(2700,new ActionListener() {
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
		//imgSlider.setMargin(new Insets(0, 0, 0, 0));
		//imgSlider.setPreferredSize(new Dimension(50, 10));
		
		JMenuBar menuBar = new JMenuBar();
		frmGestionPeliculas.setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("Home");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close the window
				int option;
				option =JOptionPane.showConfirmDialog(frmGestionPeliculas, "Are you sure you want to exit the app ?");
				if(option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}else {}
					//do nothing				
			}
		});
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Refresh tables");
		mnNewMenu.add(mntmNewMenuItem_11);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_5 = new JMenu("Actors");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Actor Management");
		mnNewMenu_5.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("View all actors");
		mnNewMenu_5.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_4 = new JMenu("Movie");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Add new movie");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPeliculaUI newMovieUI = new AddPeliculaUI();
				newMovieUI.setModal(true);
				newMovieUI.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Movie Management");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PeliculasManagementUI mngUI = new PeliculasManagementUI();
				mngUI.setModal(true);
				mngUI.setLocationRelativeTo(frmGestionPeliculas);
				mngUI.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_1 = new JMenu("Genre");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Genre Management");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_3 = new JMenu("Settings");
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_6 = new JMenu("Select Language");
		mnNewMenu_3.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Spanish");
		mnNewMenu_6.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("English");
		mnNewMenu_6.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Pidgin");
		mnNewMenu_6.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Help Contents");
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Technical Manual");
		mnNewMenu_2.add(mntmNewMenuItem_2);
	}
	
	public void SetImageSize(int i){
		Image img = null;
		Image newImg;
		ImageIcon icon = new ImageIcon(getClass().getResource(imgList[i]));
		img = icon.getImage();
		newImg = img.getScaledInstance(imgSlider.getWidth(), imgSlider.getHeight(), Image.SCALE_SMOOTH);        
        ImageIcon finalImg = new ImageIcon(newImg);
        imgSlider.setIcon(finalImg);
    }

}
