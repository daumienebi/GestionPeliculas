package es.daumienebi.gestionpeliculas.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;

import es.daumienebi.gestionpeliculas.controllers.AddActorUIController;
import es.daumienebi.gestionpeliculas.controllers.DataValidator;
import es.daumienebi.gestionpeliculas.models.Actor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import es.daumienebi.gestionpeliculas.views.AddActorUI.years;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddActorUI extends JDialog {
	AddActorUIController controller = new AddActorUIController();
	private JPanel mainPanel;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtDay;
	private JTextField textField_3;
	private JTextField txtMonth;
	private JTextField txtYear;
	Actor actor;
	/**
	 * Launch the application.
	 */
	enum years  {
		JAN,FEB,MAR,APR,MAY,JUN,JULY,AUG
	};
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddActorUI dialog = new AddActorUI();
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
	public AddActorUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddActorUI.class.getResource("/resources/movie_management.png")));
		setBounds(100, 100, 650, 600);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(panel, BorderLayout.NORTH);
		
		JButton btnImage = new JButton("");
		btnImage.setMargin(new Insets(0, 0, 0, 0));
		btnImage.setBounds(10,11,150,150);
		Image img = null;
		String imgUrl = "http://192.168.56.102/images/windows.png";				
		//String imgUrl = "https://xpectrumuwu.com/imagenes/logo.PNG";	
		ImageIcon icon = controller.getImage(imgUrl);
		img = icon.getImage();			
		//scale the image
		Image imgNuevo = img.getScaledInstance(btnImage.getWidth(),btnImage.getHeight(),  java.awt.Image.SCALE_SMOOTH );
		icon =new ImageIcon(imgNuevo);
		btnImage.setIcon(icon);
		panel.add(btnImage);
		
		JButton btnAddImage = new JButton("Add Image");
		btnAddImage.setIcon(new ImageIcon(AddActorUI.class.getResource("/resources/add_icon.jpg")));
		panel.add(btnAddImage);
		
		JPanel panel_1 = new JPanel();
		mainPanel.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Save Actor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String surname = txtSurname.getText();
				LocalDate birthDate = null;
				boolean validDate = false;
				//validate the date
				if(DataValidator.isNumeric(txtDay.getText()) && DataValidator.isNumeric(txtMonth.getText()) && DataValidator.isNumeric(txtYear.getText())) {
					int day =Integer.parseInt(txtDay.getText());
					int month =Integer.parseInt(txtMonth.getText());
					int year =Integer.parseInt(txtYear.getText());
					
					if(DataValidator.isValidDate(day, month, year)) {
						birthDate = LocalDate.of(year, month, day);
						validDate = true; //year takes more than 9999 -- fix that
					}else JOptionPane.showMessageDialog(mainPanel,"Incorrect Date Format","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				//check for blank text boxes
				if(!name.isBlank() && !surname.isBlank() && validDate) {
					actor = new Actor(1,name,surname,birthDate,"/resources/sus.jpg");
					AddActorUIController.addActor(actor);
					JOptionPane.showMessageDialog(mainPanel,"Actor/Actress Added Successfully",":)",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(mainPanel,"Fill in the necessary fields","Error",JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		panel_1.add(btnNewButton);
		
		JPanel formPanel = new JPanel();
		mainPanel.add(formPanel, BorderLayout.CENTER);
		formPanel.setLayout(new GridLayout(4, 4, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("Name");
		panel_3.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setColumns(25);
		panel_3.add(txtName);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Surname");
		panel_4.add(lblNewLabel_1);
		
		txtSurname = new JTextField();
		panel_4.add(txtSurname);
		txtSurname.setColumns(25);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Date of birth");
		panel_5.add(lblNewLabel_2);
		
		txtDay = new JTextField();
		panel_5.add(txtDay);
		txtDay.setColumns(3);
		
		JLabel lblNewLabel_4 = new JLabel("/");
		panel_5.add(lblNewLabel_4);
		
		txtMonth = new JTextField();
		panel_5.add(txtMonth);
		txtMonth.setColumns(3);
		
		JLabel lblNewLabel_5 = new JLabel("/");
		panel_5.add(lblNewLabel_5);
		
		txtYear = new JTextField();
		panel_5.add(txtYear);
		txtYear.setColumns(5);
		
		JLabel lblNewLabel_6 = new JLabel("(dd/mm/yyyy)");
		panel_5.add(lblNewLabel_6);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_6.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_6);
		
		JLabel lblNewLabel_3 = new JLabel("Date Picker");
		panel_6.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "21"}));
		panel_6.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(years.values()));
		panel_6.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007"}));
		panel_6.add(comboBox_2);
		
		textField_3 = new JTextField();
		panel_6.add(textField_3);
		textField_3.setColumns(10);
				
	}
	
	
	
	public AddActorUI(int actor_id) {
		//get a new contructor to separate the two types of UI to be shown //trying out
	}

}
