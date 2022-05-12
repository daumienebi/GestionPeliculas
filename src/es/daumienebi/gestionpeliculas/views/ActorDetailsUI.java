package es.daumienebi.gestionpeliculas.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.controllers.AddActorUIController;
import es.daumienebi.gestionpeliculas.dao.IActorDAO;
import es.daumienebi.gestionpeliculas.dao.mysql.MySQLActorDAO;
import es.daumienebi.gestionpeliculas.models.Actor;
import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class ActorDetailsUI extends JDialog {
	private IActorDAO actorDAO = new MySQLActorDAO();
	private JPanel mainPanel;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtFechaNac;
	private JTextField txtMovieNum;
	private JButton btnImage;

	/**
	 * Create the dialog.
	 */
	public ActorDetailsUI(Actor actor) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		Inicialize(actor);
		
	}
	void Inicialize(Actor actor) {
		setTitle(actor.getNombre() + " " + actor.getApellidos());
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddActorUI.class.getResource("/resources/movie_management.png")));
		setBounds(100, 100, 485, 530);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		getContentPane().add(mainPanel,BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(panel, BorderLayout.NORTH);
		
		btnImage = new JButton("");
		btnImage.setMargin(new Insets(0, 0, 0, 0));
		btnImage.setBounds(10,11,150,150);
		Image img = null;
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/add_image.png"));
		img = icon.getImage();			
		//scale the image
		Image imgNuevo = img.getScaledInstance(btnImage.getWidth(),btnImage.getHeight(),  java.awt.Image.SCALE_SMOOTH );
		icon =new ImageIcon(imgNuevo);
		btnImage.setIcon(icon);
		panel.add(btnImage);
		
		JPanel panel_1 = new JPanel();
		mainPanel.add(panel_1, BorderLayout.SOUTH);
		
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
		txtName.setEditable(false);
		txtName.setColumns(25);
		panel_3.add(txtName);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Surname");
		panel_4.add(lblNewLabel_1);
		
		txtSurname = new JTextField();
		txtSurname.setEditable(false);
		panel_4.add(txtSurname);
		txtSurname.setColumns(25);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Date of birth");
		panel_5.add(lblNewLabel_2);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setEditable(false);
		panel_5.add(txtFechaNac);
		txtFechaNac.setColumns(15);
		
		JLabel lblNewLabel_6 = new JLabel("(dd/mm/yyyy)");
		panel_5.add(lblNewLabel_6);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_6.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		formPanel.add(panel_6);
		
		JLabel lblNewLabel_3 = new JLabel("Number of movies");
		panel_6.add(lblNewLabel_3);
		
		txtMovieNum = new JTextField();
		txtMovieNum.setEditable(false);
		panel_6.add(txtMovieNum);
		txtMovieNum.setColumns(5);
		
		setValues(actor);
	}
	
	void setValues(Actor actor) {
		txtName.setText(actor.getNombre());
		txtSurname.setText(actor.getApellidos());
		txtFechaNac.setText(String.valueOf(actor.getFechaNac().toString()));
		btnImage.setIcon(getActorsImage(actor.getFoto()));
		txtMovieNum.setText(String.valueOf(actorDAO.getNumberOfMovies(actor.getId())));
	}
	
	private ImageIcon getActorsImage(String imgRoute) {
		URL url = null;
		ImageIcon icon = null;
		ImageIcon default_icon = new ImageIcon(getClass().getResource("/resources/no_image.jpg"));
		try {
			url = new URL(DefaultConfiguration.actor_image_server + imgRoute);
			icon = new ImageIcon(url);
			if(icon.getImageLoadStatus() == MediaTracker.ERRORED) {
				//
			}
			if(icon == null || icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
				icon = default_icon;
			}
			Image img = icon.getImage();
			//Rescale the image
			Image imgNuevo = img.getScaledInstance(150,150,  java.awt.Image.SCALE_SMOOTH );
			icon =new ImageIcon(imgNuevo);
			return icon;
		} catch (Exception e) {
		}
		return null;
	}

}
