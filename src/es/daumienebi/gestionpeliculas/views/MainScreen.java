package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.daumienebi.gestionpeliculas.dao.mysql.DbConnection;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import java.awt.Color;

public class MainScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//DbConnection.conectar();
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		
		initialize();
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
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
	}

}
