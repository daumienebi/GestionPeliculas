package es.daumienebi.gestionpeliculas.views;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.controllers.AddActorUIController;
import es.daumienebi.gestionpeliculas.utils.TextFieldValidatorUtil;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;
import es.daumienebi.gestionpeliculas.utils.UploadImageUtil;
import es.daumienebi.gestionpeliculas.models.Actor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;

import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.bouncycastle.util.encoders.Translator;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class AddActorUI extends JDialog {
	
	private JPanel mainPanel;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	private JButton btnImage;
	
	//To be translated
	public static JButton btnAdd;
	public static JButton btnAddImage;
	public static JButton btnSave;
	public static JLabel AddActor_lblName;
	public static JLabel AddActor_lblSurname;
	public static JLabel AddActor_lblDateOfBirth;
	public static JLabel AddActor_lblDateFormat;
	public static String AddActor_windowTitle = "Add new Actor";
	
	Actor actor;
	private String imageName = "";
	private File imgFile;
	
	//Controller
	private AddActorUIController controller = new AddActorUIController();
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	
	public AddActorUI() {
		Inicialize();
		controller.translate();
		setTitle(AddActor_windowTitle);
	}
	
	void Inicialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddActorUI.class.getResource("/resources/movie_management.png")));
		setBounds(100, 100, 500, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle(AddActor_windowTitle);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		getContentPane().add(mainPanel,BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(panel, BorderLayout.NORTH);
		
		btnImage = new JButton("");
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgFile = controller.setFotoPerfil(btnImage);
			}
		});
		btnImage.setMargin(new Insets(0, 0, 0, 0));
		btnImage.setBounds(10,11,170,170);
		Image img = null;
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/add_image.png"));
		img = icon.getImage();			
		//scale the image
		Image imgNuevo = img.getScaledInstance(btnImage.getWidth(),btnImage.getHeight(),  java.awt.Image.SCALE_SMOOTH );
		icon =new ImageIcon(imgNuevo);
		btnImage.setIcon(icon);
		panel.add(btnImage);
		
		btnAddImage = new JButton("Add Image");
		btnAddImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//nombreImagen = controller.setFotoPerfil(btnImage);
				imgFile = controller.setFotoPerfil(btnImage);
			}
		});
		panel.add(btnAddImage);
		
		JPanel panel_1 = new JPanel();
		mainPanel.add(panel_1, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add Actor");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateActor_Add();
			}
		});
		panel_1.add(btnAdd);
		
		btnSave = new JButton("Save Actor");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateActor_Edit(actor);
			}
			
		});
		panel_1.add(btnSave);
		btnSave.setVisible(false);
		JPanel formPanel = new JPanel();
		mainPanel.add(formPanel, BorderLayout.CENTER);
		formPanel.setLayout(new GridLayout(4, 4, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_3);
		
		AddActor_lblName = new JLabel("Name");
		AddActor_lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_3.add(AddActor_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(25);
		panel_3.add(txtName);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_4);
		
		AddActor_lblSurname = new JLabel("Surname");
		AddActor_lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_4.add(AddActor_lblSurname);
		
		txtSurname = new JTextField();
		panel_4.add(txtSurname);
		txtSurname.setColumns(25);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_5);
		
		AddActor_lblDateOfBirth = new JLabel("Date of birth");
		AddActor_lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_5.add(AddActor_lblDateOfBirth);
		
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
		
		AddActor_lblDateFormat = new JLabel("(dd/mm/yyyy)");
		AddActor_lblDateFormat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_5.add(AddActor_lblDateFormat);
		
	}
	
	public AddActorUI(Actor actor) {
		this();
		this.actor = actor;
		setTitle("Edit actor");
		btnAddImage.setText("Edit Image");
		txtName.setText(actor.getNombre());
		txtSurname.setText(actor.getApellidos());
		int day,month,year;
		day = actor.getFechaNac().getDayOfMonth();
		month = actor.getFechaNac().getMonthValue();
		year = actor.getFechaNac().getYear();
		txtDay.setText(String.valueOf(day));
		txtMonth.setText(String.valueOf(month));
		txtYear.setText(String.valueOf(year));
		btnAdd.setVisible(false);
		btnSave.setVisible(true);
		imageName = actor.getFoto();
		btnImage.setIcon(controller.getActorsImage(actor.getFoto()));
		//btnAddImage.setVisible(true);
		
	}
	
	void validateActor_Add() {
		String name = txtName.getText();
		String surname = txtSurname.getText();
		LocalDate birthDate = null;
		boolean validDate = false;
		//validate the date
		if(TextFieldValidatorUtil.isNumeric(txtDay.getText()) && TextFieldValidatorUtil.isNumeric(txtMonth.getText()) && TextFieldValidatorUtil.isNumeric(txtYear.getText())) {
			int day =Integer.parseInt(txtDay.getText());
			int month =Integer.parseInt(txtMonth.getText());
			int year =Integer.parseInt(txtYear.getText());
			
			if(TextFieldValidatorUtil.isValidDate(day, month, year) && year >1800 && year <9999) {
				birthDate = LocalDate.of(year, month, day);
				validDate = true; //year takes more than 9999 -- fix that
			}else JOptionPane.showMessageDialog(mainPanel,"Incorrect Date Format","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		//check for blank text boxes
		if(!name.isBlank() && !surname.isBlank() && validDate) {
			actor = new Actor(0,name,surname,birthDate,imageName);
			//upload the image to the server
			Object [] uploadResult = new Object[2];
			if(imgFile != null) {
				uploadResult = UploadImageUtil.uploadActorImage(imgFile);
				boolean uploaded = Boolean.parseBoolean(uploadResult[0].toString());
				imageName = uploadResult[1].toString();
				if(uploaded) {
					actor.setFoto(imageName);
					addActor(actor);
				}else {
					JOptionPane.showMessageDialog(getContentPane(),"There was an error uploading the image","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				actor.setFoto("");
				addActor(actor);
			}			
		}else 
			JOptionPane.showMessageDialog(mainPanel,"Please fill in the necessary fields correctly","Error",JOptionPane.ERROR_MESSAGE);
	}

	void addActor(Actor actor) {
		//to be called internally by validateActor_Add()
		int response = controller.addActor(actor);
		if(response == 0) {
			JOptionPane.showMessageDialog(mainPanel,"The record has been added successfully",""
					,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/resources/tick.jpg")));
			dispose();
		}else {
			JOptionPane.showMessageDialog(mainPanel,"There was an error adding the record","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void editActor(Actor actor) {
		//to be called internally by validateActor_Edit()
		
	}
	
	void validateActor_Edit(Actor actor) {
		boolean uploaded = false;
		String name = txtName.getText();
		String surname = txtSurname.getText();
		LocalDate birthDate = null;
		boolean validDate = false;
		//validate the date
		if(TextFieldValidatorUtil.isNumeric(txtDay.getText()) && TextFieldValidatorUtil.isNumeric(txtMonth.getText()) && TextFieldValidatorUtil.isNumeric(txtYear.getText())) {
			int day =Integer.parseInt(txtDay.getText());
			int month =Integer.parseInt(txtMonth.getText());
			int year =Integer.parseInt(txtYear.getText());
			
			if(TextFieldValidatorUtil.isValidDate(day, month, year) && year >1800 && year <9999) {
				birthDate = LocalDate.of(year, month, day);
				validDate = true; //year takes more than 9999 -- fix that
			}else JOptionPane.showMessageDialog(mainPanel,"Incorrect Date Format","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		//check for blank text boxes
		if(!name.isBlank() && !surname.isBlank() && validDate) {
			//actor = new Actor(0,name,surname,birthDate,nombreImagen);
			//upload the image to the server
			
			if(imgFile != null) {
				Object [] uploadResult = new Object[2];
				uploadResult = UploadImageUtil.uploadActorImage(imgFile);
				
				uploaded = Boolean.parseBoolean(uploadResult[0].toString());
				imageName = uploadResult[1].toString();
				if(uploaded) {
					editActor(actor, name, surname, birthDate);
				}else {
					JOptionPane.showMessageDialog(mainPanel,"There was an error uploading the image","Error",JOptionPane.ERROR_MESSAGE);
				}					
			}else {
				imageName = actor.getFoto();
				editActor(actor, name, surname, birthDate);
			}
			
			/**
			actor.setNombre(name);
			actor.setApellidos(surname);
			actor.setFechaNac(birthDate);
			actor.setFoto(imagenName);
			//if the actor is modified correctly, upload the image to the server else, don't upload it
			
			int response = controller.modifyActor(actor);
			if(response == 0) {
				JOptionPane.showMessageDialog(mainPanel,"The record has been updated successfully",""
						,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/resources/tick.jpg")));
				dispose();
			}else {
				JOptionPane.showMessageDialog(mainPanel,"There was an error updating the record","Error",JOptionPane.ERROR_MESSAGE);
			}
			*/
			
		}else {
			JOptionPane.showMessageDialog(mainPanel,"Fill in the necessary fields","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void editActor(Actor actor,String name,String surname,LocalDate birthDate){
		actor.setNombre(name);
		actor.setApellidos(surname);
		actor.setFechaNac(birthDate);
		actor.setFoto(imageName);
		//if the actor is modified correctly, upload the image to the server else, don't upload it
		int response = controller.modifyActor(actor);
		if(response == 0) {
			JOptionPane.showMessageDialog(mainPanel,"The record has been updated successfully",""
					,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/resources/tick.jpg")));
			dispose();
		}else {
			JOptionPane.showMessageDialog(mainPanel,"There was an error updating the record","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
