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
import resources.GlobalResources;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActorManagementUI extends JDialog {
	private ActorManagementUIController controller = new ActorManagementUIController();
	private  JTable table;
	private JTextField txtName;
	private ArrayList<Actor> actorsList = new ArrayList<>();
	
	//static values to obtain the selected table item
	static int row;
	static int column;
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
		Inicialize();
		loadActorsTable();
		
	}
	private void Inicialize() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ActorManagementUI.class.getResource("/resources/logo2.png")));
		setBounds(100, 100, 950, 700);
		
		JPanel actionBtnPane = new JPanel();
		getContentPane().add(actionBtnPane, BorderLayout.SOUTH);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int actor_id = getActorId();
				System.out.println("Actor ID :" + actor_id);
			}
		});
		btnEdit.setVisible(false);
		actionBtnPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int actor_id,response;
				actor_id= getActorId();
				response = controller.deleteActor(actor_id);
				if(response == 1) {
					JOptionPane.showMessageDialog(getContentPane(), "Record deleted successfully", "Delete Record",
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/resources/tick.jpg")));
					loadActorsTable();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "Error deleting the record", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setVisible(false);
		actionBtnPane.add(btnDelete);
		
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setBackground(new Color(245, 245, 220));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(170);
		
		
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
				ActorTableModel tableModel = new ActorTableModel(controller.fliterMovie(txtName.getText()));
				table.setModel(tableModel);
				table.removeColumn(table.getColumnModel().getColumn(0));
			}
		});
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(txtName);
		txtName.setColumns(20);
		buttomBtnActions(btnEdit,btnDelete);
		
	}
	void buttomBtnActions(JButton btnEdit,JButton btnDelete) {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int fila =table.getSelectedRow();
				if(fila >-1) {
					btnEdit.setVisible(true);
					btnDelete.setVisible(true);
				}else{
					btnEdit.setVisible(false);
					btnDelete.setVisible(false);
				}
			}
		});
	}
	
	int getActorId() {
		row = table.getSelectedRow();
		column = table.getSelectedColumn();
		int actor_id = Integer.parseInt(table.getModel().getValueAt(row, 0).toString()); //0 because thats where the id is, even though its not visible
		return actor_id;
	}
	void loadActorsTable() {
		actorsList = controller.getAllActors();
		ActorTableModel tableModel = new ActorTableModel(actorsList);
		table.setModel(tableModel);
		table.removeColumn(table.getColumnModel().getColumn(0));
		tableModel.fireTableDataChanged();
	}
	
	
		
}
