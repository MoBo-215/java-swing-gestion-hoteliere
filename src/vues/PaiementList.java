package vues;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

import components.Button;
import components.ScrollPane;
import components.TextField;
import dao.ClientDAO;
import dao.PaiementDAO;
import dao.ReservationDAO;
import dao.SocieteDAO;
import entites.Client;
import entites.Database;
import entites.Paiement;
import entites.Reservation;
import entites.Societe;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class PaiementList extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;
	/**
	 * Create the panel.
	 */
	public PaiementList() {
		setLayout(null);

		JLabel lblClientList = new JLabel("Liste des paiements");
		lblClientList.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientList.setFont(new Font("Calibri", Font.BOLD, 40));
		lblClientList.setBounds(0, 100, 1040, 51);
		add(lblClientList);
		
		Database.connect();
		ArrayList<Paiement> pmts = new PaiementDAO().getAll();
		String columns[] = {"ID", "DATE", "MONTANT", "METHODE", "ID RESERVATION", "CLIENT"};
		String data[][] = new String[pmts.size()][columns.length];
		int i=0;
		for(Paiement p: pmts) {
			data[i][0]=p.getId()+"";
			data[i][1]=p.getDate()+"";
			data[i][2]=p.getMontant()+"0";
			data[i][3]=p.getMethode();
			data[i][4]=p.getId_reservation()+"";
			data[i][5]=new ClientDAO().getById(new ReservationDAO().getById(p.getId_reservation()).getId_client()).getPrenom()+" "+new ClientDAO().getById(new ReservationDAO().getById(p.getId_reservation()).getId_client()).getNom().toUpperCase();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columns);

		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(300);
		
		table.setDefaultEditor(Object.class, null);
		table.setBounds(1, 25, 823, 0);
		table.setRowHeight(30);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(table);
		
		ScrollPane scrollPane = new ScrollPane(table);
      	setBorder(new EmptyBorder(10, 10, 10, 10));
		scrollPane.setBounds(20, 183, 825, 521);
		scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		scrollPane.getHorizontalScrollBar().setBackground(Color.BLACK);
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(252, 216, 73);
		    }
		    @Override
	        protected JButton createDecreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        @Override    
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        private JButton createZeroButton() {
	            JButton jbutton = new JButton();
	            jbutton.setPreferredSize(new Dimension(0, 0));
	            jbutton.setMinimumSize(new Dimension(0, 0));
	            jbutton.setMaximumSize(new Dimension(0, 0));
	            return jbutton;
	        }
		});
		scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(252, 216, 73);
		    }
		    @Override
	        protected JButton createDecreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        @Override    
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        private JButton createZeroButton() {
	            JButton jbutton = new JButton();
	            jbutton.setPreferredSize(new Dimension(0, 0));
	            jbutton.setMinimumSize(new Dimension(0, 0));
	            jbutton.setMaximumSize(new Dimension(0, 0));
	            return jbutton;
	        }
		});
		add(scrollPane);
		
		Button btnAjouter = new Button("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new PaiementAdd());
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(855, 183, 170, 40);
		add(btnAjouter);
		
		Button btnModifier = new Button("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					int rowIndex = table.getSelectedRow();
					int selectedId = Integer.parseInt(data[rowIndex][0]);
					Paiement p = new PaiementDAO().getById(selectedId);
					
					Home.replace(new PaiementEdit(p));
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez un paiement à modifier.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModifier.setBackground(Color.BLACK);
		btnModifier.setBounds(855, 237, 170, 40);
		add(btnModifier);
		
		Button btnSupprimer = new Button("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {	
					int input = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer ce paiement ?", "ATTENTION !", JOptionPane.YES_NO_OPTION); 
					if(input==0) {
						int rowIndex = table.getSelectedRow();	
						int selectedId = Integer.parseInt(data[rowIndex][0]); 
						new PaiementDAO().deleteById(selectedId); 
						
						model.removeRow(rowIndex);
						JOptionPane.showMessageDialog(null, "Paiement supprimé.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez un paiement à supprimer.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSupprimer.setBackground(Color.BLACK);
		btnSupprimer.setBounds(855, 287, 170, 40);
		add(btnSupprimer);
		
		Button btnRglementRservation = new Button("Liste des réservations");
		btnRglementRservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new ReservationList());
			}
		});
		btnRglementRservation.setText("<html><center>Liste des<br>réservations</center></html>");
		btnRglementRservation.setForeground(Color.WHITE);
		btnRglementRservation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRglementRservation.setBackground(Color.BLACK);
		btnRglementRservation.setBounds(855, 402, 170, 83);
		add(btnRglementRservation);
		
		TextField trech = new TextField();
		trech.setFont(new Font("Tahoma", Font.PLAIN, 20));
		trech.setColumns(10);
		trech.setBounds(855, 562, 171, 40);
		add(trech);
		
		Button btnRechercher = new Button("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = trech.getText();
				ArrayList<Paiement> pmts = new PaiementDAO().findPaiements(txt);
				String columns[] = {"ID", "DATE", "MONTANT", "METHODE", "ID RESERVATION", "CLIENT"};
				String data[][] = new String[pmts.size()][columns.length];
				int i=0;
				for(Paiement p: pmts) {
					data[i][0]=p.getId()+"";
					data[i][1]=p.getDate()+"";
					data[i][2]=p.getMontant()+"";
					data[i][3]=p.getMethode();
					data[i][4]=p.getId_reservation()+"";
					data[i][5]=new ClientDAO().getById(new ReservationDAO().getById(p.getId_reservation()).getId_client()).getPrenom()+" "+new ClientDAO().getById(new ReservationDAO().getById(p.getId_reservation()).getId_client()).getNom().toUpperCase();
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(150);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.getColumnModel().getColumn(3).setPreferredWidth(200);
				table.getColumnModel().getColumn(4).setPreferredWidth(200);
				table.getColumnModel().getColumn(5).setPreferredWidth(300);
			}
		});
		btnRechercher.setForeground(Color.WHITE);
		btnRechercher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRechercher.setBackground(Color.BLACK);
		btnRechercher.setBounds(855, 612, 170, 40);
		add(btnRechercher);
		
		Button btnAnnuler = new Button("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Paiement> pmts = new PaiementDAO().getAll();
				String columns[] = {"ID", "DATE", "MONTANT", "METHODE", "ID RESERVATION", "CLIENT"};
				String data[][] = new String[pmts.size()][columns.length];
				int i=0;
				for(Paiement p: pmts) {
					data[i][0]=p.getId()+"";
					data[i][1]=p.getDate()+"";
					data[i][2]=p.getMontant()+"";
					data[i][3]=p.getMethode();
					data[i][4]=p.getId_reservation()+"";
					data[i][5]=new ClientDAO().getById(new ReservationDAO().getById(p.getId_reservation()).getId_client()).getPrenom()+" "+new ClientDAO().getById(new ReservationDAO().getById(p.getId_reservation()).getId_client()).getNom().toUpperCase();
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				trech.setText("");
				
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(150);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.getColumnModel().getColumn(3).setPreferredWidth(200);
				table.getColumnModel().getColumn(4).setPreferredWidth(200);
				table.getColumnModel().getColumn(5).setPreferredWidth(300);
			}
		});
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnnuler.setBackground(Color.BLACK);
		btnAnnuler.setBounds(855, 664, 170, 40);
		add(btnAnnuler);
		
		trech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRechercher.doClick();
			}
		});
		
		JPanel pback = new JPanel();
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new HomePanel());
			}
		});
		pback.setBackground(new Color(255, 235, 160));
		pback.setBounds(10, 0, 97, 83);
		add(pback);		
		pback.setLayout(null);
		
		JLabel lblBack = new JLabel("<<");
		lblBack.setFont(new Font("Calibri", Font.BOLD, 40));
		lblBack.setBounds(10, 10, 47, 63);
		pback.add(lblBack);

		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setSize(1040, 750);
		add(bgLabel);
	}

}
