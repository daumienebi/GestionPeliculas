package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import es.daumienebi.gestionpeliculas.config.Configuration;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class ConfigUI extends JDialog{

	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtDbUser;
	private JPasswordField txtDbPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigUI dialog = new ConfigUI();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	/**
	 * Initialize the contents of the frame.
	 */
	public ConfigUI() {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfigUI.class.getResource("/resources/movie_management.png")));
		setTitle("Config Screen");
		setBounds(100, 100, 440, 440);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(33, 134, 116, 14);
		panel.add(lblNewLabel);
		
		txtIp = new JTextField();
		txtIp.setBounds(208, 131, 190, 20);
		panel.add(txtIp);
		txtIp.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setBounds(33, 178, 116, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DB_USER");
		lblNewLabel_2.setBounds(33, 225, 116, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DB_PASSWORD");
		lblNewLabel_3.setBounds(33, 272, 116, 14);
		panel.add(lblNewLabel_3);
		
		txtPort = new JTextField();
		txtPort.setBounds(208, 175, 80, 20);
		panel.add(txtPort);
		txtPort.setColumns(10);
		
		txtDbUser = new JTextField();
		txtDbUser.setBounds(208, 222, 190, 20);
		panel.add(txtDbUser);
		txtDbUser.setColumns(10);
		
		txtDbPassword = new JPasswordField();
		txtDbPassword.setBounds(208, 269, 190, 20);
		panel.add(txtDbPassword);
		
		JCheckBox chkBoxDefaultConfig = new JCheckBox("Use default configuration");
		chkBoxDefaultConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkBoxDefaultConfig.isSelected()) {
					disableTxtBoxes();
				}else enableTxtBoxes();
			}
		});
		
		chkBoxDefaultConfig.setBounds(33, 338, 209, 23);
		panel.add(chkBoxDefaultConfig);
		ImageIcon icon = new ImageIcon(ConfigUI.class.getResource("/resources/db.png"));
		Image img = icon.getImage();			
		//scale the image
		Image imgNuevo = img.getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH );
		icon =new ImageIcon(imgNuevo);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		//lblNewLabel_4.setIcon(new ImageIcon(ConfigUI.class.getResource("/resources/db.png")));
		lblNewLabel_4.setIcon(icon);
		lblNewLabel_4.setBounds(166, 11, 99, 100);
		panel.add(lblNewLabel_4);
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkBoxDefaultConfig.isSelected()) {
					Configuration.use_default_connection = 1;
					System.out.println(Configuration.db_password + " " + Configuration.db_user);
				}else {
					if(!txtIp.getText().isBlank() && !txtPort.getText().isBlank() && !txtDbUser.getText().isBlank() && !txtDbPassword.getPassword().toString().isBlank()) {
						Configuration.ip = txtIp.getText();
						Configuration.port = txtPort.getText();
						Configuration.db_user = txtDbUser.getText();
						Configuration.db_password = txtDbPassword.getPassword().toString().trim(); //check out the password control
						
						//call the saveConfig and then activate the home screen
						//maybe create a DefaultConfiguration.java to return the default config
						System.out.println(Configuration.db_password + " " + Configuration.db_user);
					}else {
						JOptionPane.showMessageDialog(null,"Fill  in the necessary fields","Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		panel_1.add(btnConnect);	
	}
	
	void disableTxtBoxes() {
		txtDbPassword.setEnabled(false);
		txtDbUser.setEnabled(false);
		txtPort.setEnabled(false);
		txtIp.setEnabled(false);
	}
	
	void enableTxtBoxes() {
		txtDbPassword.setEnabled(true);
		txtDbUser.setEnabled(true);
		txtPort.setEnabled(true);
		txtIp.setEnabled(true);
	}
}
