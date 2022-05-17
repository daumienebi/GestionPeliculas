package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.daumienebi.gestionpeliculas.utils.TextFieldValidatorUtil;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;
import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.utils.ReportsUtil;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PersonalizedReportsUI extends JDialog {
	private JTextField txtRating;
	private JTextField txtMovieCount;
	private JTextField txtPremiereYear;

	
	/**
	 * To be translated 
	 */
	public static String header1 = "Actors Report";
	public static String header2 = "Movies Report";	
	public static JLabel lblMovieCount;
	public static JLabel lblPremiereYear;
	public static JButton btnLoadReport1;
	public static JButton btnLoadReport2;
	public static JButton btnLoadReport3;
	public static JLabel lblRating;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalizedReportsUI dialog = new PersonalizedReportsUI();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	
	public PersonalizedReportsUI() {
		Inicialize();
		translate();
	}
	
	void Inicialize() {
		setTitle("Personalized Reports");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PersonalizedReportsUI.class.getResource("/resources/movie_management.png")));
		setBounds(100, 100, 690, 570);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab(header2, null, panel, null);
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		panel.setLayout(null);
		
		lblRating = new JLabel("Rating");
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRating.setBounds(30, 53, 80, 26);
		panel.add(lblRating);
		
		txtRating = new JTextField();
		txtRating.setBounds(270, 58, 59, 19);
		panel.add(txtRating);
		txtRating.setColumns(10);
		
		btnLoadReport1 = new JButton("Load Report");
		btnLoadReport1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TextFieldValidatorUtil.isDouble(txtRating.getText())) {
					double rating =Double.valueOf(txtRating.getText());
					try {
						Thread.sleep(2000);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					dispose();
					ReportsUtil.viewPersonalizedReport1("PersonalizedReport1.jrxml", rating);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "Incorrect data type, please introduce a correct value", "Invalid value", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnLoadReport1.setBounds(409, 57, 121, 21);
		panel.add(btnLoadReport1);
		
		JLabel lblNewLabel_1 = new JLabel(">");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(185, 59, 45, 20);
		panel.add(lblNewLabel_1);
		
		lblPremiereYear = new JLabel("Premiere Year");
		lblPremiereYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPremiereYear.setBounds(30, 170, 94, 26);
		panel.add(lblPremiereYear);
		
		JLabel lblNewLabel_1_1 = new JLabel("=");
		lblNewLabel_1_1.setForeground(Color.GREEN);
		lblNewLabel_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(185, 170, 45, 19);
		panel.add(lblNewLabel_1_1);
		
		txtPremiereYear = new JTextField();
		txtPremiereYear.setBounds(263, 176, 66, 19);
		panel.add(txtPremiereYear);
		txtPremiereYear.setColumns(10);
		
		btnLoadReport2 = new JButton("Load Report");
		btnLoadReport2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TextFieldValidatorUtil.isNumeric(txtPremiereYear.getText())) {
					int year = Integer.valueOf(txtPremiereYear.getText());
					try {
						Thread.sleep(2000);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					dispose();
					ReportsUtil.viewPersonalizedReport2("PersonalizedReport2.jrxml", year);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "Incorrect data type, please introduce a correct value", "Invalid value", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		btnLoadReport2.setBounds(409, 175, 121, 21);
		panel.add(btnLoadReport2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("The report shows the list of movies that have the rating greater than the introduced value");
		btnNewButton.setIcon(new ImageIcon(PersonalizedReportsUI.class.getResource("/resources/info.png")));
		btnNewButton.setBounds(536, 58, 15, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Show the list of movies that were premiered in the introduce year");
		btnNewButton_1.setIcon(new ImageIcon(PersonalizedReportsUI.class.getResource("/resources/info.png")));
		btnNewButton_1.setBounds(540, 175, 15, 21);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab(header1, null, panel_1, null);
		panel_1.setLayout(null);
		
		lblMovieCount = new JLabel("Movie count");
		lblMovieCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMovieCount.setBounds(10, 60, 103, 27);
		panel_1.add(lblMovieCount);
		
		JLabel lblNewLabel_3 = new JLabel("=\\>");
		lblNewLabel_3.setForeground(Color.GREEN);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(173, 66, 58, 18);
		panel_1.add(lblNewLabel_3);
		
		txtMovieCount = new JTextField();
		txtMovieCount.setBounds(275, 66, 54, 19);
		panel_1.add(txtMovieCount);
		txtMovieCount.setColumns(10);
		
		btnLoadReport3 = new JButton("Load Report");
		btnLoadReport3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TextFieldValidatorUtil.isNumeric(txtMovieCount.getText())) {
					int movie_count = Integer.valueOf(txtMovieCount.getText());
					try {
						Thread.sleep(2000);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					dispose();
					
					ReportsUtil.viewPersonalizedReport3("PersonalizedReport3.jrxml", movie_count);
					
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "Incorrect data type, please introduce a correct value", "Invalid value", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnLoadReport3.setBounds(409, 66, 108, 21);
		panel_1.add(btnLoadReport3);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setToolTipText("Show the list of actors that have participated in the number \\ greater than the number of movies given");
		btnNewButton_2.setIcon(new ImageIcon(PersonalizedReportsUI.class.getResource("/resources/info.png")));
		btnNewButton_2.setBounds(523, 65, 15, 21);
		panel_1.add(btnNewButton_2);

	}
	
	void translate() {
		if(TranslatorUtil.bundle != null) {
			TranslatorUtil.translatePersonalizedReportsUI(DefaultConfiguration.lang_id);
		}
	}
}
