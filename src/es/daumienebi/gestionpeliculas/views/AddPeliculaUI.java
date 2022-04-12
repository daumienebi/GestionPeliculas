package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.List;
import java.awt.Font;

public class AddPeliculaUI extends JDialog {
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPeliculaUI dialog = new AddPeliculaUI();
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
	public AddPeliculaUI() {
		setTitle("Add a new movie");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddPeliculaUI.class.getResource("/resources/logo2.png")));
		setBounds(100, 100, 900, 700);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		FlowLayout fl_headerPanel = (FlowLayout) headerPanel.getLayout();
		fl_headerPanel.setHgap(10);
		fl_headerPanel.setVgap(20);
		fl_headerPanel.setAlignment(FlowLayout.LEFT);
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		JButton btnMoviePoster = new JButton("");
		btnMoviePoster.setMargin(new Insets(0, 0, 0, 0));
		btnMoviePoster.setBounds(20,20,200,300);
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/increibles.jpg"));
		Image img = icon.getImage();
		//escalar la imagen
		Image imgNuevo = img.getScaledInstance(btnMoviePoster.getWidth(),btnMoviePoster.getHeight(),  java.awt.Image.SCALE_SMOOTH );
		//volver a asignarle la imagen redimensionada al icono
		icon =new ImageIcon(imgNuevo);
		//panelSuperior.setLayout(null);
		btnMoviePoster.setIcon(icon);
		headerPanel.add(btnMoviePoster);
		
		JButton btnAddPoster = new JButton("Add Poster");
		headerPanel.add(btnAddPoster);
		
		Label label = new Label(" ");
		headerPanel.add(label);
		
		Label label_1 = new Label("                           ");
		headerPanel.add(label_1);
		
		Label label_2 = new Label("");
		headerPanel.add(label_2);
		
		Panel panel_5 = new Panel();
		headerPanel.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		Panel title_panel = new Panel();
		FlowLayout flowLayout_4 = (FlowLayout) title_panel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_5.add(title_panel);
		
		Panel panel_8 = new Panel();
		title_panel.add(panel_8);
		
		JLabel lblNewLabel_2 = new JLabel("Title");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8.add(lblNewLabel_2);
		
		Panel panel_7 = new Panel();
		title_panel.add(panel_7);
		
		textField_2 = new JTextField();
		panel_7.add(textField_2);
		textField_2.setColumns(20);
		
		Panel duration_panel = new Panel();
		FlowLayout flowLayout_3 = (FlowLayout) duration_panel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel_5.add(duration_panel);
		
		Panel panel_8_1 = new Panel();
		duration_panel.add(panel_8_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Duration");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_1.add(lblNewLabel_2_1);
		
		Panel panel_7_1 = new Panel();
		duration_panel.add(panel_7_1);
		
		textField = new JTextField();
		textField.setColumns(20);
		panel_7_1.add(textField);
		
		Panel premiere_panel = new Panel();
		FlowLayout flowLayout_2 = (FlowLayout) premiere_panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_5.add(premiere_panel);
		
		Panel panel_8_2 = new Panel();
		premiere_panel.add(panel_8_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Premiere Date");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_2.add(lblNewLabel_2_2);
		
		Panel panel_7_2 = new Panel();
		premiere_panel.add(panel_7_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel_7_2.add(textField_1);
		
		Panel rating_panel = new Panel();
		FlowLayout flowLayout_1 = (FlowLayout) rating_panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_5.add(rating_panel);
		
		Panel panel_8_3 = new Panel();
		rating_panel.add(panel_8_3);
		
		JLabel lblNewLabel_2_3 = new JLabel("Rating");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_8_3.add(lblNewLabel_2_3);
		
		Panel panel_7_3 = new Panel();
		rating_panel.add(panel_7_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(20);
		panel_7_3.add(textField_3);
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		Panel panel_1 = new Panel();
		getContentPane().add(panel_1, BorderLayout.EAST);
		
		Panel panel_2 = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel_2, BorderLayout.CENTER);
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Dialog", Font.ITALIC, 13));
		textArea.setText("Synopsis de una pelicula aleatoria para probar");
		panel_2.add(textArea);

	}
}
