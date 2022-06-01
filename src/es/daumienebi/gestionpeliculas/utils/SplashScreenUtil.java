package es.daumienebi.gestionpeliculas.utils;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JProgressBar;
import java.awt.Toolkit;

public class SplashScreenUtil extends JFrame {

	private JPanel contentPane;
	public static JLabel lblLoading;
	public static JProgressBar progressBar;
	private JLabel lblNewLabel;
	public static JLabel lblDetail;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreenUtil frame = new SplashScreenUtil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SplashScreenUtil() {
		setUndecorated(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreenUtil.class.getResource("/resources/movie_management.jpg")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 401);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLoading = new JLabel("10");
		lblLoading.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoading.setBounds(296, 243, 45, 35);
		contentPane.add(lblLoading);
		
		JLabel lblNewLabel_1 = new JLabel("MOVIE MANAGEMENT");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(123, 11, 380, 65);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(10, 11, 77, 65);
		contentPane.add(lblIcon);
		ImageIcon default_icon = new ImageIcon(getClass().getResource("/resources/movie_management.png"));
	
		Image img = default_icon.getImage();
		//Rescale the image
		Image imgNuevo = img.getScaledInstance(70,50,  java.awt.Image.SCALE_SMOOTH );
		default_icon =new ImageIcon(imgNuevo);
		lblIcon.setIcon(default_icon);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(191, 304, 230, 27);
		contentPane.add(progressBar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(SplashScreenUtil.class.getResource("/resources/loading.gif")));
		lblNewLabel.setBounds(112, 95, 380, 137);
		contentPane.add(lblNewLabel);
		
		lblDetail = new JLabel("");
		lblDetail.setBounds(191, 278, 230, 14);
		contentPane.add(lblDetail);
	}
}
