package vues;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import dao.ChambreDAO;
import dao.ClientDAO;
import dao.HotelDAO;
import dao.PaiementDAO;
import dao.ReservationDAO;
import dao.SocieteDAO;
import entites.Database;
import entites.Paiement;
import entites.Reservation;
import entites.Societe;

public class ReservationList extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;

	/**
	 * Create the panel.
	 */
	public ReservationList() {
		setLayout(null);

		JLabel lblClientList = new JLabel("Liste des réservations");
		lblClientList.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientList.setFont(new Font("Calibri", Font.BOLD, 40));
		lblClientList.setBounds(0, 100, 1040, 51);
		add(lblClientList);
		
		Database.connect();
		ArrayList<Reservation> resas = new ReservationDAO().getAll();
		String columns[] = {"ID", "CLIENT", "PAIEMENT", "DATE DEBUT", "DATE FIN", "HOTEL", "CHAMBRE", "NB PERSONNES"};
		String data[][] = new String[resas.size()][columns.length];
		int i=0;
		double totalP=0;
		double totalR=0;
		double restePayer=0;
		int nbJoursSejour=0;
		for(Reservation r: resas) {
			data[i][0]=r.getId()+"";
			data[i][1]=new ClientDAO().getById(r.getId_client()).getPrenom()+" "+new ClientDAO().getById(r.getId_client()).getNom().toUpperCase();

			nbJoursSejour = Home.calculNbJoursReservation(r.getDate_fin(),r.getDate_debut());
			totalR=nbJoursSejour*new ChambreDAO().getById(new ReservationDAO().getById(r.getId()).getId_chambre()).getPrix_nuit();
			for(Paiement p: new PaiementDAO().getAllByIdReservation(r.getId())) {
				totalP=totalP+p.getMontant();
			}
			restePayer=totalR-totalP;
			
			if(restePayer==0) {
				data[i][2]="Réservation réglée";
			}else {
				data[i][2]="Reste à payer : "+restePayer+"0€";
			}
			
			data[i][3]=r.getDate_debut()+"";
			data[i][4]=r.getDate_fin()+"";
			data[i][5]=new HotelDAO().getById(new ChambreDAO().getById(r.getId_chambre()).getId_hotel()).getNom();
			data[i][6]=new ChambreDAO().getById(r.getId_chambre()).getNumero()+"";
			data[i][7]=r.getNb_personnes()+"";
			
			nbJoursSejour=0;
			totalP=0;
			totalR=0;
			restePayer=0;
			
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columns);

		JTable table = new JTable(model);

		// Désactivation de l'adaptation automatique aux tailles de la table :
		// Lorsque l'on décide de régler manuellement la taille des colonnes,
		// il faut le faire pour toutes pour que les préférences soient bien prises en compte.
		// En revanche, la taille (bounds) de la table ne sera jamais dépassée.
		// Il faut donc désactiver l'adapt auto pour pouvoir définir la taille de toutes les colonnes
		// même si elles dépassent la taille max de la table : scrollbar permettra la navigation horizontale.
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(130);
		
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
				Home.replace(new ReservationAdd());
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
					Reservation r = new ReservationDAO().getById(selectedId);
					
					Home.replace(new ReservationEdit(r));
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une réservation à modifier.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
					int input = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer cette réservation ?", "ATTENTION !", JOptionPane.YES_NO_OPTION); 
					if(input==0) {
						int rowIndex = table.getSelectedRow();	
						int selectedId = Integer.parseInt(data[rowIndex][0]); 
						new ReservationDAO().deleteById(selectedId); 
						
						model.removeRow(rowIndex);
						JOptionPane.showMessageDialog(null, "Réservation supprimée.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une réservation à supprimer.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSupprimer.setBackground(Color.BLACK);
		btnSupprimer.setBounds(855, 287, 170, 40);
		add(btnSupprimer);
		
		Button btnRglementRservation = new Button("<html><center>Régler une<br>réservation</center></html>");
		btnRglementRservation.setText("<html><center>Régler une<br>réservation</center></html>");
		btnRglementRservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					int rowIndex = table.getSelectedRow();
					int selectedId = Integer.parseInt(data[rowIndex][0]);
					Reservation r = new ReservationDAO().getById(selectedId);
					
					if(data[rowIndex][2]=="Réservation réglée"){
						JOptionPane.showMessageDialog(null, "Réservation déjà réglée en totalité.");
					}else {
						Home.replace(new PaiementAddFromResa(r));						
					}
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une réservation à régler.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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
				ArrayList<Reservation> resas = new ReservationDAO().findReservations(txt);
				String columns[] = {"ID", "CLIENT", "PAIEMENT", "DATE DEBUT", "DATE FIN", "HOTEL", "CHAMBRE", "NB PERSONNES"};
				String data[][] = new String[resas.size()][columns.length];
				int i=0;
				double totalP=0;
				double totalR=0;
				double restePayer=0;
				int nbJoursSejour=0;
				for(Reservation r: resas) {
					data[i][0]=r.getId()+"";
					data[i][1]=new ClientDAO().getById(r.getId_client()).getPrenom()+" "+new ClientDAO().getById(r.getId_client()).getNom().toUpperCase();

					nbJoursSejour = Home.calculNbJoursReservation(r.getDate_fin(),r.getDate_debut());
					totalR=nbJoursSejour*new ChambreDAO().getById(new ReservationDAO().getById(r.getId()).getId_chambre()).getPrix_nuit();
					for(Paiement p: new PaiementDAO().getAllByIdReservation(r.getId())) {
						totalP=totalP+p.getMontant();
					}
					restePayer=totalR-totalP;
					
					if(restePayer==0) {
						data[i][2]="Réservation réglée";
					}else {
						data[i][2]="Reste à payer : "+restePayer+"€";
					}
					
					data[i][3]=r.getDate_debut()+"";
					data[i][4]=r.getDate_fin()+"";
					data[i][5]=new HotelDAO().getById(new ChambreDAO().getById(r.getId_chambre()).getId_hotel()).getNom();
					data[i][6]=new ChambreDAO().getById(r.getId_chambre()).getNumero()+"";
					data[i][7]=r.getNb_personnes()+"";

					nbJoursSejour=0;
					totalP=0;
					totalR=0;
					restePayer=0;
					
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(250);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(100);
				table.getColumnModel().getColumn(5).setPreferredWidth(150);
				table.getColumnModel().getColumn(6).setPreferredWidth(100);
				table.getColumnModel().getColumn(7).setPreferredWidth(130);	
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
				ArrayList<Reservation> resas = new ReservationDAO().getAll();
				String columns[] = {"ID", "CLIENT", "PAIEMENT", "DATE DEBUT", "DATE FIN", "HOTEL", "CHAMBRE", "NB PERSONNES"};
				String data[][] = new String[resas.size()][columns.length];
				int i=0;
				double totalP=0;
				double totalR=0;
				double restePayer=0;
				int nbJoursSejour=0;
				for(Reservation r: resas) {
					data[i][0]=r.getId()+"";
					data[i][1]=new ClientDAO().getById(r.getId_client()).getPrenom()+" "+new ClientDAO().getById(r.getId_client()).getNom().toUpperCase();

					nbJoursSejour = Home.calculNbJoursReservation(r.getDate_fin(),r.getDate_debut());
					totalR=nbJoursSejour*new ChambreDAO().getById(new ReservationDAO().getById(r.getId()).getId_chambre()).getPrix_nuit();
					for(Paiement p: new PaiementDAO().getAllByIdReservation(r.getId())) {
						totalP=totalP+p.getMontant();
					}
					restePayer=totalR-totalP;
					
					if(restePayer==0) {
						data[i][2]="Réservation réglée";
					}else {
						data[i][2]="Reste à payer : "+restePayer+"€";
					}
					
					data[i][3]=r.getDate_debut()+"";
					data[i][4]=r.getDate_fin()+"";
					data[i][5]=new HotelDAO().getById(new ChambreDAO().getById(r.getId_chambre()).getId_hotel()).getNom();
					data[i][6]=new ChambreDAO().getById(r.getId_chambre()).getNumero()+"";
					data[i][7]=r.getNb_personnes()+"";
					
					nbJoursSejour=0;
					totalP=0;
					totalR=0;
					restePayer=0;
					
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				trech.setText("");
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(250);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(100);
				table.getColumnModel().getColumn(5).setPreferredWidth(150);
				table.getColumnModel().getColumn(6).setPreferredWidth(100);
				table.getColumnModel().getColumn(7).setPreferredWidth(130);
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
