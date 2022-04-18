package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.daumienebi.gestionpeliculas.controllers.ActorManagementUIController;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.viewmodels.ActorTableModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ActorManagementUI extends JDialog {
	private  JTable table;
	private JTextField txtName;
	private ArrayList<Actor> actorsList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActorManagementUI dialog = new ActorManagementUI();
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
	public ActorManagementUI() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ActorManagementUI.class.getResource("/resources/logo2.png")));
		setBounds(100, 100, 950, 700);
		
		JPanel actionBtnPane = new JPanel();
		getContentPane().add(actionBtnPane, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Edit");
		actionBtnPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		actionBtnPane.add(btnNewButton_1);
		
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setBackground(new Color(245, 245, 220));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(170);
		actorsList = ActorManagementUIController.getAllActors();
		ActorTableModel tableModel = new ActorTableModel(actorsList);
		table.setModel(tableModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Actor's Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setIcon(new ImageIcon(ActorManagementUI.class.getResource("/resources/search.jpg")));
		panel.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ActorTableModel tableModel = new ActorTableModel(ActorManagementUIController.fliterMovie(txtName.getText()));
				table.setModel(tableModel);
			}
		});
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(txtName);
		txtName.setColumns(20);
		
	}

}
