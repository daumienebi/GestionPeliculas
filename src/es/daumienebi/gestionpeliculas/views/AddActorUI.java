package es.daumienebi.gestionpeliculas.views;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import es.daumienebi.gestionpeliculas.controllers.AddActorUIController;
import es.daumienebi.gestionpeliculas.utils.TextFieldValidatorUtil;
import es.daumienebi.gestionpeliculas.utils.UploadImageUtil;
import es.daumienebi.gestionpeliculas.models.Actor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;

import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
	private JButton btnAdd;
	private JButton btnAddImage;
	private JButton btnSave;
	
	Actor actor;
	private String imagenName = "";
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
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddActorUI.class.getResource("/resources/movie_management.png")));
		setBounds(100, 100, 500, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("Add new actor");
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
				//nombreImagen = controller.setFotoPerfil(btnImage);
				imgFile = controller.setFotoPerfil(btnImage);
				//nombreImagen = imgFile.getName();
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
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_3.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setColumns(25);
		panel_3.add(txtName);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Surname");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_4.add(lblNewLabel_1);
		
		txtSurname = new JTextField();
		panel_4.add(txtSurname);
		txtSurname.setColumns(25);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Date of birth");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_6);
				
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
		imagenName = actor.getFoto();
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
			//upload the image to the server
			Object [] uploadResult = new Object[2];
			uploadResult = UploadImageUtil.uploadActorImage(imgFile);
			
			boolean uploaded = Boolean.parseBoolean(uploadResult[0].toString());
			imagenName = uploadResult[1].toString();
			
			if(uploaded) {
				actor = new Actor(0,name,surname,birthDate,imagenName);
				//if the actor is added correctly, upload the image to the server else, don't upload it
				int response = controller.addActor(actor);
				if(response == 0) {
					JOptionPane.showMessageDialog(mainPanel,"The record has been added successfully",""
							,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource("/resources/tick.jpg")));
					dispose();
				}else {
					JOptionPane.showMessageDialog(mainPanel,"There was an error adding the record","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(mainPanel,"There was an error uploading the image","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(mainPanel,"Fill in the necessary fields","Error",JOptionPane.ERROR_MESSAGE);
		}
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
				imagenName = uploadResult[1].toString();
				if(uploaded) {
					editActor(actor, name, surname, birthDate);
				}else {
					JOptionPane.showMessageDialog(mainPanel,"There was an error uploading the image","Error",JOptionPane.ERROR_MESSAGE);
				}					
			}else {
				imagenName = actor.getFoto();
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
	}

}
