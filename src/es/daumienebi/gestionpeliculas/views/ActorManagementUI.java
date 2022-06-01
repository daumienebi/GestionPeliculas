package es.daumienebi.gestionpeliculas.views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;

import es.daumienebi.gestionpeliculas.controllers.ActorManagementUIController;
import es.daumienebi.gestionpeliculas.models.Actor;
import es.daumienebi.gestionpeliculas.viewmodels.ActorTableModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActorManagementUI extends JDialog {
	private ActorManagementUIController controller = new ActorManagementUIController();
	private  JTable table;
	private JTextField txtName;
	private ArrayList<Actor> actorsList = new ArrayList<>();
	
	/**
	 * To be translated
	 */
	public static JLabel ActorMngment_lblActorName;
	public static JButton ActorMngment_btnDelete;
	public static JButton ActorMngment_btnEdit;
	
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
		controller.translate();
		loadActorsTable();
		
	}
	private void Inicialize() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ActorManagementUI.class.getResource("/resources/logo2.png")));
		setBounds(100, 100, 950, 700);
		
		JPanel actionBtnPane = new JPanel();
		getContentPane().add(actionBtnPane, BorderLayout.SOUTH);
		
		ActorMngment_btnEdit = new JButton("Edit");
		ActorMngment_btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int actor_id = getActorId();
				Actor actor = controller.getActor(actor_id);
				AddActorUI ui = new AddActorUI(actor);
				ui.setModal(true);
				ui.setLocationRelativeTo(getContentPane());
				ui.setVisible(true);
				loadActorsTable();
				
			}
		});
		ActorMngment_btnEdit.setVisible(false);
		actionBtnPane.add(ActorMngment_btnEdit);
		
		ActorMngment_btnDelete = new JButton("Delete");
		ActorMngment_btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int actor_id,response,exitCode;
				actor_id= getActorId();
				
				response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the actor ?", "Delete actor", JOptionPane.YES_NO_OPTION);
				if(response == JOptionPane.YES_OPTION) {
					exitCode = controller.deleteActor(actor_id);
					if(exitCode == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "Record deleted successfully", "Delete Record",
								JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/resources/tick.jpg")));
						loadActorsTable();
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "The record cannot be deleted at the moment because the Actor\\ Actress features in 1 or more movies", 
								"Error deleting the record", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
		ActorMngment_btnDelete.setVisible(false);
		actionBtnPane.add(ActorMngment_btnDelete);
		
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
		
		ActorMngment_lblActorName = new JLabel("Actor's Name");
		ActorMngment_lblActorName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ActorMngment_lblActorName.setIcon(new ImageIcon(ActorManagementUI.class.getResource("/resources/search.jpg")));
		panel.add(ActorMngment_lblActorName);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ActorTableModel tableModel = new ActorTableModel(controller.fliterMovie(txtName.getText()));
				tableModel.translateColumns();
				table.setModel(tableModel);
				table.removeColumn(table.getColumnModel().getColumn(0));
			}
		});
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(txtName);
		txtName.setColumns(20);
		
		//extra actions and more stuff
		buttomBtnActions(ActorMngment_btnEdit,ActorMngment_btnDelete);
		tableDoubleClick(table);
	}
	
	void tableDoubleClick(JTable table) {
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		            int id = Integer.valueOf(table.getModel().getValueAt(row, 0).toString());
		            //String nombre =table.getValueAt(row, 1).toString();
		            //String apellido = table.getValueAt(row, 2).toString();
		            //LocalDate fechaNac = LocalDate.parse(table.getValueAt(row, 3).toString());
		            //String foto = table.getValueAt(row, 4).toString();
		            //Actor actor = new Actor(id, nombre, apellido, fechaNac, foto);
		            Actor actor = controller.getActor(id);
		            if(actor == null) {
		            	JOptionPane.showMessageDialog(table, "Actor not found","Data not found",JOptionPane.ERROR_MESSAGE);
		            }else {
		            	ActorDetailsUI ui = new ActorDetailsUI(actor);
		            	ui.setLocationRelativeTo(getContentPane());
		            	ui.setVisible(true);
		            }
		        }
		    }
		});
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
		tableModel.translateColumns();
		table.setModel(tableModel);
		table.removeColumn(table.getColumnModel().getColumn(0));
		tableModel.fireTableDataChanged();
	}
	
	
		
}
