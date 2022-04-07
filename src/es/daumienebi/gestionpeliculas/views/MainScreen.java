package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.daumienebi.gestionpeliculas.dao.mysql.DbConnection;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen {

	private JFrame frmGestionPeliculas;
	private JPanel mainPanel;
	int panelHeight,panelWidth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//DbConnection.conectar();
					MainScreen window = new MainScreen();					
					window.frmGestionPeliculas.setLocationRelativeTo(null);
					window.frmGestionPeliculas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public MainScreen(){
		//UIManager.setLookAndFeel(UIManager. getCrossPlatformLookAndFeelClassName());
		//this.frame.setDefaultLookAndFeelDecorated(true);
		initialize();
		/*
		DbConnection.connect();
		
		ResultSet rs;
		try {
			PreparedStatement p = DbConnection.getConexion().prepareStatement("select email from cliente");
			rs = p.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionPeliculas = new JFrame();
		frmGestionPeliculas.setTitle("Gestion Peliculas");
		frmGestionPeliculas.setResizable(false);
		frmGestionPeliculas.setBounds(100, 100, 900,600);
		frmGestionPeliculas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionPeliculas.getContentPane().setLayout(new BorderLayout(0, 0));
		
		ImageIcon fondo = new ImageIcon(getClass().getResource("/images/background.jpg"));
		Image img = fondo.getImage();
		//escalar la imagen
		Image imgNuevo = img.getScaledInstance(1024,600,java.awt.Image.SCALE_SMOOTH );
		//volver a asignarle la imagen redimensionada al icono
		ImageIcon a =new ImageIcon(imgNuevo);
		mainPanel = new JPanel() {
			@Override
		     protected void paintComponent(Graphics g)
		     {
		        super.paintComponent(g);
		        g.drawImage(a.getImage(), 0, 0, null);
		     }
		};
		frmGestionPeliculas.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel inferiorPanel = new JPanel();
		mainPanel.add(inferiorPanel, BorderLayout.SOUTH);
		inferiorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton startBtn = new JButton("Start");
		inferiorPanel.add(startBtn);
		startBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 40));
		
		JMenuBar menuBar = new JMenuBar();
		frmGestionPeliculas.setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("Home");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Start");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Refresh");
		mnNewMenu.add(mntmNewMenuItem_1);
		
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
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Settings");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_4 = new JMenu("Database");
		mnNewMenu_1.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Configure Connection");
		mnNewMenu_4.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Help Contents");
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Technical Manual");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu((String) null);
		menuBar.add(mnNewMenu_3);
	}

}
