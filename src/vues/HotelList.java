package vues;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

import components.Button;
import components.ScrollPane;
import components.TextField;
import dao.ClientDAO;
import dao.HotelDAO;
import dao.SocieteDAO;
import entites.Client;
import entites.Database;
import entites.Hotel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HotelList extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;

	/**
	 * Create the panel.
	 */
	public HotelList() {
		setLayout(null);
		
		JLabel lblHotelList = new JLabel("Liste des hôtels");
		lblHotelList.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelList.setFont(new Font("Calibri", Font.BOLD, 40));
		lblHotelList.setBounds(0, 100, 1040, 51);
		add(lblHotelList);
		
		Database.connect();
		ArrayList<Hotel> hotels = new HotelDAO().getAll();
		String columns[] = {"ID", "NOM", "ADRESSE", "VILLE", "DESCRIPTION", "PARKING", "WIFI", "HORAIRE ARRIVEE", "HORAIRE DEPART", "PISCINE", "NAVETTE AEROPORT", "ANIMAUX COMPAGNIE", "ETOILES", "SOCIETE"};
		String data[][] = new String[hotels.size()][columns.length];
		int i=0;
		for(Hotel h: hotels) {
			data[i][0]=h.getId()+"";
			data[i][1]=h.getNom();
			data[i][2]=h.getAdresse();
			data[i][3]=h.getVille();
			data[i][4]=h.getDescription();
			data[i][5]=h.getDispo_parking_gratuit();
			data[i][6]=h.getPresence_wifi_gratuit();
			data[i][7]=h.getHoraire_arrivee()+"";
			data[i][8]=h.getHoraire_depart()+"";
			data[i][9]=h.getPresence_piscine();
			data[i][10]=h.getDispo_navette_aeroport();
			data[i][11]=h.getPossibilite_animaux_compagnie();
			data[i][12]=h.getCategorie_etoiles()+"";
			data[i][13]=new SocieteDAO().getById(h.getId_societe()).getNom();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columns);

		JTable table = new JTable(model);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(400);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(130);
		table.getColumnModel().getColumn(8).setPreferredWidth(130);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(10).setPreferredWidth(150);
		table.getColumnModel().getColumn(11).setPreferredWidth(150);
		table.getColumnModel().getColumn(12).setPreferredWidth(100);
		table.getColumnModel().getColumn(13).setPreferredWidth(130);
		
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
				Home.replace(new HotelAdd());
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
					Hotel h = new HotelDAO().getById(selectedId);
					
					Home.replace(new HotelEdit(h));
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez un hôtel à modifier.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
					int input = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer cet hôtel ?", "ATTENTION !", JOptionPane.YES_NO_OPTION); 
					if(input==0) {
						int rowIndex = table.getSelectedRow();	
						int selectedId = Integer.parseInt(data[rowIndex][0]); 
						new HotelDAO().deleteById(selectedId); 
						
						model.removeRow(rowIndex);
						JOptionPane.showMessageDialog(null, "Hôtel supprimé.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez un hôtel à supprimer.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSupprimer.setBackground(Color.BLACK);
		btnSupprimer.setBounds(855, 287, 170, 40);
		add(btnSupprimer);
		
		TextField trech = new TextField();
		trech.setFont(new Font("Tahoma", Font.PLAIN, 20));
		trech.setColumns(10);
		trech.setBounds(855, 562, 171, 40);
		add(trech);
		
		Button btnRechercher = new Button("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = trech.getText();
				ArrayList<Hotel> hotels = new HotelDAO().findHotels(txt);
				String columns[] = {"ID", "NOM", "ADRESSE", "VILLE", "DESCRIPTION", "PARKING", "WIFI", "HORAIRE ARRIVEE", "HORAIRE DEPART", "PISCINE", "NAVETTE AEROPORT", "ANIMAUX COMPAGNIE", "ETOILES", "SOCIETE"};
				String data[][] = new String[hotels.size()][columns.length];
				int i=0;
				for(Hotel h: hotels) {
					data[i][0]=h.getId()+"";
					data[i][1]=h.getNom();
					data[i][2]=h.getAdresse();
					data[i][3]=h.getVille();
					data[i][4]=h.getDescription();
					data[i][5]=h.getDispo_parking_gratuit();
					data[i][6]=h.getPresence_wifi_gratuit();
					data[i][7]=h.getHoraire_arrivee()+"";
					data[i][8]=h.getHoraire_depart()+"";
					data[i][9]=h.getPresence_piscine();
					data[i][10]=h.getDispo_navette_aeroport();
					data[i][11]=h.getPossibilite_animaux_compagnie();
					data[i][12]=h.getCategorie_etoiles()+"";
					data[i][13]=new SocieteDAO().getById(h.getId_societe()).getNom();
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(400);
				table.getColumnModel().getColumn(5).setPreferredWidth(100);
				table.getColumnModel().getColumn(6).setPreferredWidth(100);
				table.getColumnModel().getColumn(7).setPreferredWidth(130);
				table.getColumnModel().getColumn(8).setPreferredWidth(130);
				table.getColumnModel().getColumn(9).setPreferredWidth(100);
				table.getColumnModel().getColumn(10).setPreferredWidth(150);
				table.getColumnModel().getColumn(11).setPreferredWidth(150);
				table.getColumnModel().getColumn(12).setPreferredWidth(100);
				table.getColumnModel().getColumn(13).setPreferredWidth(130);
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
				ArrayList<Hotel> hotels = new HotelDAO().getAll();
				String columns[] = {"ID", "NOM", "ADRESSE", "VILLE", "DESCRIPTION", "PARKING", "WIFI", "HORAIRE ARRIVEE", "HORAIRE DEPART", "PISCINE", "NAVETTE AEROPORT", "ANIMAUX COMPAGNIE", "ETOILES", "SOCIETE"};
				String data[][] = new String[hotels.size()][columns.length];
				int i=0;
				for(Hotel h: hotels) {
					data[i][0]=h.getId()+"";
					data[i][1]=h.getNom();
					data[i][2]=h.getAdresse();
					data[i][3]=h.getVille();
					data[i][4]=h.getDescription();
					data[i][5]=h.getDispo_parking_gratuit();
					data[i][6]=h.getPresence_wifi_gratuit();
					data[i][7]=h.getHoraire_arrivee()+"";
					data[i][8]=h.getHoraire_depart()+"";
					data[i][9]=h.getPresence_piscine();
					data[i][10]=h.getDispo_navette_aeroport();
					data[i][11]=h.getPossibilite_animaux_compagnie();
					data[i][12]=h.getCategorie_etoiles()+"";
					data[i][13]=new SocieteDAO().getById(h.getId_societe()).getNom();
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				trech.setText("");
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(400);
				table.getColumnModel().getColumn(5).setPreferredWidth(100);
				table.getColumnModel().getColumn(6).setPreferredWidth(100);
				table.getColumnModel().getColumn(7).setPreferredWidth(130);
				table.getColumnModel().getColumn(8).setPreferredWidth(130);
				table.getColumnModel().getColumn(9).setPreferredWidth(100);
				table.getColumnModel().getColumn(10).setPreferredWidth(150);
				table.getColumnModel().getColumn(11).setPreferredWidth(150);
				table.getColumnModel().getColumn(12).setPreferredWidth(100);
				table.getColumnModel().getColumn(13).setPreferredWidth(130);
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
