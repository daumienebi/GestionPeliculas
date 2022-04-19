package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConfigUI {

	private JFrame frmConfigScreen;
	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtDbUser;
	private JTextField txtDbPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigUI window = new ConfigUI();
					window.frmConfigScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfigUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfigScreen = new JFrame();
		frmConfigScreen.setIconImage(Toolkit.getDefaultToolkit().getImage(ConfigUI.class.getResource("/resources/movie_management.png")));
		frmConfigScreen.setTitle("Config Screen");
		frmConfigScreen.setBounds(100, 100, 440, 440);
		frmConfigScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmConfigScreen.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(33, 53, 116, 14);
		panel.add(lblNewLabel);
		
		txtIp = new JTextField();
		txtIp.setBounds(208, 50, 190, 20);
		panel.add(txtIp);
		txtIp.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setBounds(33, 109, 116, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DB_USER");
		lblNewLabel_2.setBounds(33, 168, 116, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DB_PASSWORD");
		lblNewLabel_3.setBounds(33, 228, 116, 14);
		panel.add(lblNewLabel_3);
		
		txtPort = new JTextField();
		txtPort.setBounds(208, 106, 80, 20);
		panel.add(txtPort);
		txtPort.setColumns(10);
		
		txtDbUser = new JTextField();
		txtDbUser.setBounds(208, 168, 190, 20);
		panel.add(txtDbUser);
		txtDbUser.setColumns(10);
		
		txtDbPassword = new JTextField();
		txtDbPassword.setBounds(208, 225, 190, 20);
		panel.add(txtDbPassword);
		txtDbPassword.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		frmConfigScreen.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Config Action");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Use default connection");
		panel_1.add(btnNewButton_1);
		
	}
}
