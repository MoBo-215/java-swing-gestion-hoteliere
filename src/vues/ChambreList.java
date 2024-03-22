package vues;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import components.Button;
import components.ScrollPane;
import components.TextField;
import dao.ChambreDAO;
import dao.ClientDAO;
import dao.HotelDAO;
import entites.Chambre;
import entites.Client;
import entites.Database;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChambreList extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;

	/**
	 * Create the panel.
	 */
	public ChambreList() {
		setLayout(null);
		
		JLabel lblChambreList = new JLabel("Liste des chambres");
		lblChambreList.setHorizontalAlignment(SwingConstants.CENTER);
		lblChambreList.setFont(new Font("Calibri", Font.BOLD, 40));
		lblChambreList.setBounds(0, 100, 1040, 51);
		add(lblChambreList);
		
		Database.connect();
		ArrayList<Chambre> chambres = new ChambreDAO().getAll();
		String columns[] = {"ID", "HOTEL", "N°", "LITS SIMPLES", "LITS DOUBLES", "SUPERFICIE", "SDB", "ECRAN PLAT", "BALCON", "REFRIGIRATEUR", "BAIGNOIRE", "INSONORISATION", "PRIX/NUIT"};
		String data[][] = new String[chambres.size()][columns.length];
		int i=0;
		for(Chambre c: chambres) {
			data[i][0]=c.getId()+"";
			data[i][1]=new HotelDAO().getById(c.getId_hotel()).getNom();
			data[i][2]=c.getNumero()+"";
			data[i][3]=c.getNb_lits_simples()+"";
			data[i][4]=c.getNb_lits_doubles()+"";
			data[i][5]=c.getSuperficie()+"";
			data[i][6]=c.getPresence_sdb_privative();
			data[i][7]=c.getPresence_ecran_plat();
			data[i][8]=c.getPresence_balcon();
			data[i][9]=c.getPresence_refrigirateur();
			data[i][10]=c.getPresence_baignoire();
			data[i][11]=c.getPresence_insonorisation();
			data[i][12]=c.getPrix_nuit()+"0";
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columns);

		JTable table = new JTable(model);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		table.getColumnModel().getColumn(10).setPreferredWidth(100);
		table.getColumnModel().getColumn(11).setPreferredWidth(150);
		table.getColumnModel().getColumn(12).setPreferredWidth(100);

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
				Home.replace(new ChambreAdd());
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
					Chambre c = new ChambreDAO().getById(selectedId);
					
					Home.replace(new ChambreEdit(c));
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une chambre à modifier.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
					int input = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer cette chambre ?", "ATTENTION !", JOptionPane.YES_NO_OPTION); 
					if(input==0) {
						int rowIndex = table.getSelectedRow();	
						int selectedId = Integer.parseInt(data[rowIndex][0]); 
						new ChambreDAO().deleteById(selectedId); 
						
						model.removeRow(rowIndex);
						JOptionPane.showMessageDialog(null, "Chambre supprimée.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une chambre à supprimer.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
				ArrayList<Chambre> chambres = new ChambreDAO().findChambres(txt);
				String columns[] = {"ID", "HOTEL", "N°", "LITS SIMPLES", "LITS DOUBLES", "SUPERFICIE", "SDB", "ECRAN PLAT", "BALCON", "REFRIGIRATEUR", "BAIGNOIRE", "INSONORISATION", "PRIX/NUIT"};
				String data[][] = new String[chambres.size()][columns.length];
				int i=0;
				for(Chambre c: chambres) {
					data[i][0]=c.getId()+"";
					data[i][1]=new HotelDAO().getById(c.getId_hotel()).getNom();
					data[i][2]=c.getNumero()+"";
					data[i][3]=c.getNb_lits_simples()+"";
					data[i][4]=c.getNb_lits_doubles()+"";
					data[i][5]=c.getSuperficie()+"";
					data[i][6]=c.getPresence_sdb_privative();
					data[i][7]=c.getPresence_ecran_plat();
					data[i][8]=c.getPresence_balcon();
					data[i][9]=c.getPresence_refrigirateur();
					data[i][10]=c.getPresence_baignoire();
					data[i][11]=c.getPresence_insonorisation();
					data[i][12]=c.getPrix_nuit()+"";
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(100);
				table.getColumnModel().getColumn(5).setPreferredWidth(100);
				table.getColumnModel().getColumn(6).setPreferredWidth(100);
				table.getColumnModel().getColumn(7).setPreferredWidth(100);
				table.getColumnModel().getColumn(8).setPreferredWidth(100);
				table.getColumnModel().getColumn(9).setPreferredWidth(150);
				table.getColumnModel().getColumn(10).setPreferredWidth(100);
				table.getColumnModel().getColumn(11).setPreferredWidth(150);
				table.getColumnModel().getColumn(12).setPreferredWidth(100);
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
				ArrayList<Chambre> chambres = new ChambreDAO().getAll();
				String columns[] = {"ID", "HOTEL", "N°", "LITS SIMPLES", "LITS DOUBLES", "SUPERFICIE", "SDB", "ECRAN PLAT", "BALCON", "REFRIGIRATEUR", "BAIGNOIRE", "INSONORISATION", "PRIX/NUIT"};
				String data[][] = new String[chambres.size()][columns.length];
				int i=0;
				for(Chambre c: chambres) {
					data[i][0]=c.getId()+"";
					data[i][1]=new HotelDAO().getById(c.getId_hotel()).getNom();
					data[i][2]=c.getNumero()+"";
					data[i][3]=c.getNb_lits_simples()+"";
					data[i][4]=c.getNb_lits_doubles()+"";
					data[i][5]=c.getSuperficie()+"";
					data[i][6]=c.getPresence_sdb_privative();
					data[i][7]=c.getPresence_ecran_plat();
					data[i][8]=c.getPresence_balcon();
					data[i][9]=c.getPresence_refrigirateur();
					data[i][10]=c.getPresence_baignoire();
					data[i][11]=c.getPresence_insonorisation();
					data[i][12]=c.getPrix_nuit()+"";
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				trech.setText("");
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(100);
				table.getColumnModel().getColumn(5).setPreferredWidth(100);
				table.getColumnModel().getColumn(6).setPreferredWidth(100);
				table.getColumnModel().getColumn(7).setPreferredWidth(100);
				table.getColumnModel().getColumn(8).setPreferredWidth(100);
				table.getColumnModel().getColumn(9).setPreferredWidth(150);
				table.getColumnModel().getColumn(10).setPreferredWidth(100);
				table.getColumnModel().getColumn(11).setPreferredWidth(150);
				table.getColumnModel().getColumn(12).setPreferredWidth(100);
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
