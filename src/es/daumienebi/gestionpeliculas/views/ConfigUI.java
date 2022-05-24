package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.daumienebi.gestionpeliculas.config.Configuration;
import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.controllers.ConfigUIControlller;
import es.daumienebi.gestionpeliculas.controllers.HomeScreenController;
import es.daumienebi.gestionpeliculas.dao.mysql.DbConnection;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.Dialog.ModalityType;

public class ConfigUI extends JDialog{

	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtDbUser;
	private JPasswordField txtDbPassword;
	private JTextField txtDbName;
	
	/**
	 * To be translated
	*/
	
	public static JButton ConfigUI_btnConnect;
	public static JButton ConfigUI_btnCancel;
	public static JLabel ConfigUI_dbName;
	public static JCheckBox ConfigUI_chkBoxDefaultConfig;
	public static JLabel ConfigUI_dbPass;
	public static JLabel ConfigUI_dbUser;
	public static JLabel ConfigUI_Port;
	public static JTextField txtMovieImgServer;
	public static JTextField txtActorImgServer;
	public static JTextField txtFtpServer;
	public static JTextField txtFtpUser;
	public static JLabel ConfigUI_ftpPass;
	ConfigUIControlller controller = new ConfigUIControlller();
	private JSeparator separator_1;
	private JPasswordField txtFtpPass;
	
	
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

	
	public ConfigUI() {
		Inicializar();
		fillDefaultValues();
		controller.translate();
	}
	
	void Inicializar() {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfigUI.class.getResource("/resources/movie_management.png")));
		setTitle("Config Screen");
		setBounds(100, 100, 660, 670);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setBounds(33, 125, 116, 14);
		panel.add(lblNewLabel);
		
		txtIp = new JTextField();
		txtIp.setBounds(222, 122, 190, 20);
		panel.add(txtIp);
		txtIp.setColumns(10);
		
		ConfigUI_Port = new JLabel("PORT");
		ConfigUI_Port.setBounds(33, 159, 116, 14);
		panel.add(ConfigUI_Port);
		
		ConfigUI_dbUser = new JLabel("DB_USER");
		ConfigUI_dbUser.setBounds(33, 242, 116, 14);
		panel.add(ConfigUI_dbUser);
		
		ConfigUI_dbPass = new JLabel("DB_PASSWORD");
		ConfigUI_dbPass.setBounds(33, 288, 116, 14);
		panel.add(ConfigUI_dbPass);
		
		txtPort = new JTextField();
		txtPort.setBounds(222, 153, 80, 20);
		panel.add(txtPort);
		txtPort.setColumns(10);
		
		txtDbUser = new JTextField();
		txtDbUser.setBounds(222, 239, 190, 20);
		panel.add(txtDbUser);
		txtDbUser.setColumns(10);
		
		txtDbPassword = new JPasswordField();
		txtDbPassword.setBounds(222, 285, 190, 20);
		panel.add(txtDbPassword);
		
		ConfigUI_chkBoxDefaultConfig = new JCheckBox("Use default configuration");
		ConfigUI_chkBoxDefaultConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ConfigUI_chkBoxDefaultConfig.isSelected()) {
					disableTxtBoxes();
					fillDefaultValues();
				}else {
					enableTxtBoxes();
					//hideDefaultValues();
				}
			}
		});
		
		ConfigUI_chkBoxDefaultConfig.setBounds(33, 540, 209, 23);
		panel.add(ConfigUI_chkBoxDefaultConfig);
		ImageIcon icon = new ImageIcon(ConfigUI.class.getResource("/resources/db.png"));
		Image img = icon.getImage();			
		//scale the image
		Image imgNuevo = img.getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH );
		icon =new ImageIcon(imgNuevo);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		//lblNewLabel_4.setIcon(new ImageIcon(ConfigUI.class.getResource("/resources/db.png")));
		lblNewLabel_4.setIcon(icon);
		lblNewLabel_4.setBounds(277, 11, 99, 100);
		panel.add(lblNewLabel_4);
		
		ConfigUI_dbName = new JLabel("DB_NAME");
		ConfigUI_dbName.setBounds(33, 194, 116, 14);
		panel.add(ConfigUI_dbName);
		
		txtDbName = new JTextField();
		txtDbName.setBounds(222, 191, 190, 20);
		panel.add(txtDbName);
		txtDbName.setColumns(10);
		
		JLabel ConfigUI_movieImgServer = new JLabel("MOVIE IMAGE SERVER");
		ConfigUI_movieImgServer.setBounds(33, 336, 139, 14);
		panel.add(ConfigUI_movieImgServer);
		
		txtMovieImgServer = new JTextField();
		txtMovieImgServer.setBounds(222, 333, 355, 20);
		panel.add(txtMovieImgServer);
		txtMovieImgServer.setColumns(10);
		
		JLabel ConfigUI_actorImgServer = new JLabel("ACTOR IMAGE SERVER");
		ConfigUI_actorImgServer.setBounds(33, 377, 139, 14);
		panel.add(ConfigUI_actorImgServer);
		
		JLabel ConfigUI_ftpServer = new JLabel("FTP Server");
		ConfigUI_ftpServer.setBounds(33, 442, 116, 14);
		panel.add(ConfigUI_ftpServer);
		
		txtActorImgServer = new JTextField();
		txtActorImgServer.setColumns(10);
		txtActorImgServer.setBounds(222, 374, 355, 20);
		panel.add(txtActorImgServer);
		
		txtFtpServer = new JTextField();
		txtFtpServer.setColumns(10);
		txtFtpServer.setBounds(222, 439, 190, 20);
		panel.add(txtFtpServer);
		
		JLabel ConfigUI_ftpUser = new JLabel("FTP User");
		ConfigUI_ftpUser.setBounds(33, 473, 116, 14);
		panel.add(ConfigUI_ftpUser);
		
		txtFtpUser = new JTextField();
		txtFtpUser.setColumns(10);
		txtFtpUser.setBounds(222, 470, 80, 20);
		panel.add(txtFtpUser);
		
		ConfigUI_ftpPass = new JLabel("FTP Password");
		ConfigUI_ftpPass.setBounds(33, 504, 116, 14);
		panel.add(ConfigUI_ftpPass);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 422, 578, 9);
		panel.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(33, 316, 578, 9);
		panel.add(separator_1);
		
		txtFtpPass = new JPasswordField();
		txtFtpPass.setBounds(222, 501, 116, 20);
		panel.add(txtFtpPass);
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		ConfigUI_btnConnect = new JButton("Connect");
		ConfigUI_btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ConfigUI_chkBoxDefaultConfig.isSelected()) {
					//Configuration.use_default_connection = 1;
					DbConnection.connect();
					Connection con = DbConnection.getConnection();
					if(con != null) {
						ImageIcon icon = new ImageIcon(getClass().getResource("/resources/tick.jpg"));
						JOptionPane.showMessageDialog(getRootPane(),"Connection established successfully "+ '\n' +" The Application will proceed to restart automatically.","Database connection",JOptionPane.INFORMATION_MESSAGE,icon);							
						dispose();
					}
						}else {
							if(!txtIp.getText().isBlank() && !txtPort.getText().isBlank() && !txtDbUser.getText().isBlank() && 
							!txtDbName.getText().isBlank()) {
								DefaultConfiguration.ip = txtIp.getText();
								DefaultConfiguration.port = txtPort.getText();
								DefaultConfiguration.db_name = txtDbName.getText();
								DefaultConfiguration.db_user = txtDbUser.getText();
								String pass = new String(txtDbPassword.getPassword());
								DefaultConfiguration.db_password = pass;
								pass = "";
								DefaultConfiguration.ftp_user = txtFtpUser.getText().trim();
								DefaultConfiguration.ftp_password = txtFtpPass.getText().trim();
								DefaultConfiguration.ftp_server = txtFtpServer.getText().trim();
								DefaultConfiguration.actor_image_server = txtActorImgServer.getText().trim();
								DefaultConfiguration.movie_image_server = txtMovieImgServer.getText().trim();
								DbConnection.connect();
								Connection con = DbConnection.getConnection();
								if(con != null) {
									//controller.saveConfig();
									ImageIcon icon = new ImageIcon(getClass().getResource("/resources/tick.jpg"));
									JOptionPane.showMessageDialog(getRootPane(),"Connection established successfully !, the Application will proceed to restart automatically.","Database connection",JOptionPane.INFORMATION_MESSAGE,icon);							
									dispose();
								}else {
								}
								System.out.println(DefaultConfiguration.ip);
								System.out.println(DefaultConfiguration.port);
								System.out.println(DefaultConfiguration.db_name);
								System.out.println(DefaultConfiguration.db_user);
								System.out.println(DefaultConfiguration.db_password);
							}else {
								JOptionPane.showMessageDialog(null,"Fill  in the necessary fields","Error", JOptionPane.ERROR_MESSAGE);
							}					
						}				
				}			
			});
		
		/*
		ConfigUI_btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ConfigUI_chkBoxDefaultConfig.isSelected()) {
					Configuration.use_default_connection = 1;
					DbConnection.connect();
					Connection con = DbConnection.getConnection();
					if(con != null) {
						ImageIcon icon = new ImageIcon(getClass().getResource("/resources/tick.jpg"));
						JOptionPane.showMessageDialog(getRootPane(),"Connection established successfully "+ '\n' +" The Application will proceed to restart automatically.","Database connection",JOptionPane.INFORMATION_MESSAGE,icon);							
						dispose();
					}else {
						//JOptionPane.showMessageDialog(getRootPane(),"Connection established successfully !","Database connection",JOptionPane.ERROR_MESSAGE);
						Configuration.use_default_connection = -1;
					}
					
					//System.out.println(Configuration.db_password + " " + Configuration.db_user);
					//System.exit(0);
				}else {
					if(!txtIp.getText().isBlank() && !txtPort.getText().isBlank() && !txtDbUser.getText().isBlank() && 
							!txtDbName.getText().isBlank()) {
						Configuration.ip = txtIp.getText();
						Configuration.port = txtPort.getText();
						Configuration.db_name = txtDbName.getText();
						Configuration.db_user = txtDbUser.getText();
						//Configuration.db_password = "root";
						//Configuration.db_password = new String(); //check out the password control
						String pass = new String(txtDbPassword.getPassword());
						Configuration.db_password = pass;
						pass = "";
						HomeScreenController.setConfig();
						DbConnection.connect();
						Connection con = DbConnection.getConnection();
						if(con != null) {
							//controller.saveConfig();
							ImageIcon icon = new ImageIcon(getClass().getResource("/resources/tick.jpg"));
							JOptionPane.showMessageDialog(getRootPane(),"Connection established successfully !, the Application will proceed to restart automatically.","Database connection",JOptionPane.INFORMATION_MESSAGE,icon);							
							dispose();
						}else {
							Configuration.use_default_connection = -1;
							//controller.saveConfig();
						}
						System.out.println(DefaultConfiguration.ip);
						System.out.println(DefaultConfiguration.port);
						System.out.println(DefaultConfiguration.db_name);
						System.out.println(DefaultConfiguration.db_user);
						System.out.println(DefaultConfiguration.db_password);
						
						System.out.println("user :" +Configuration.db_user + " " + Configuration.db_password);
						//System.exit(0);
					}else {
						JOptionPane.showMessageDialog(null,"Fill  in the necessary fields","Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
			
		});
		*/
		panel_1.add(ConfigUI_btnConnect);	
		
		ConfigUI_btnCancel = new JButton("Cancel");
		ConfigUI_btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(ConfigUI_btnCancel);
	}
	
	void disableTxtBoxes() {
		txtDbPassword.setEnabled(false);
		txtDbUser.setEnabled(false);
		txtPort.setEnabled(false);
		txtIp.setEnabled(false);
		txtDbName.setEnabled(false);
		txtFtpPass.setEnabled(false);
		txtFtpServer.setEnabled(false);
		txtFtpUser.setEnabled(false);
		txtActorImgServer.setEnabled(false);
		txtMovieImgServer.setEnabled(false);
	}
	
	void enableTxtBoxes() {
		txtDbPassword.setEnabled(true);
		txtDbUser.setEnabled(true);
		txtPort.setEnabled(true);
		txtIp.setEnabled(true);
		txtDbName.setEnabled(true);
		txtFtpPass.setEnabled(true);
		txtFtpServer.setEnabled(true);
		txtFtpUser.setEnabled(true);
		txtActorImgServer.setEnabled(true);
		txtMovieImgServer.setEnabled(true);
	}
	
	void fillDefaultValues() {
		txtIp.setText(DefaultConfiguration.ip);
		txtPort.setText(DefaultConfiguration.port);
		txtDbUser.setText(DefaultConfiguration.db_user);
		txtDbPassword.setText(DefaultConfiguration.db_password);
		txtDbName.setText(DefaultConfiguration.db_name);
		txtFtpServer.setText(DefaultConfiguration.ftp_server);
		txtFtpUser.setText(DefaultConfiguration.ftp_user);
		txtActorImgServer.setText(DefaultConfiguration.actor_image_server);
		txtMovieImgServer.setText(DefaultConfiguration.movie_image_server);
		txtFtpPass.setText(DefaultConfiguration.ftp_password);
	}
	
	void fillConfigValues() {
		txtIp.setText(Configuration.ip);
		txtPort.setText(Configuration.port);
		txtDbUser.setText(Configuration.db_user);
		txtDbPassword.setText(Configuration.db_password);
		txtDbName.setText(Configuration.db_name);
		txtFtpPass.setText(Configuration.ftp_password);
		txtFtpServer.setText(Configuration.ftp_server);
		txtFtpUser.setText(Configuration.ftp_user);
		txtActorImgServer.setText(Configuration.actor_image_server);
		txtMovieImgServer.setText(Configuration.movie_image_server);
	}
	
	void hideDefaultValues() {
		txtIp.setText("");
		txtPort.setText("");
		txtDbUser.setText("");
		txtDbPassword.setText("");
		txtDbName.setText("");
	}
}
