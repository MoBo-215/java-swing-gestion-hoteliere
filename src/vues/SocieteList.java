package vues;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.Button;
import components.ScrollPane;
import components.TextField;
import dao.SocieteDAO;
import entites.Database;
import entites.Societe;

import javax.swing.JTable;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class SocieteList extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;

	/**
	 * Create the panel.
	 */
	public SocieteList() {
		setLayout(null);

		JLabel lblClientList = new JLabel("Liste des sociétés");
		lblClientList.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientList.setFont(new Font("Calibri", Font.BOLD, 40));
		lblClientList.setBounds(0, 100, 1040, 51);
		add(lblClientList);
		
		Database.connect();
		ArrayList<Societe> socs = new SocieteDAO().getAll();
		String columns[] = {"ID", "SIRET", "NOM", "ADRESSE"};
		String data[][] = new String[socs.size()][columns.length];
		int i=0;
		for(Societe s: socs) {
			data[i][0]=s.getId()+"";
			data[i][1]=s.getNum_siret();
			data[i][2]=s.getNom();
			data[i][3]=s.getAdresse();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columns);

		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(400);
		
		table.setDefaultEditor(Object.class, null);
		table.setBounds(1, 25, 823, 0);
		table.setRowHeight(30);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(table);
		
		ScrollPane scrollPane = new ScrollPane(table);
      	setBorder(new EmptyBorder(10, 10, 10, 10));
		scrollPane.setBounds(20, 183, 825, 521);
		add(scrollPane);
		
		Button btnAjouter = new Button("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new SocieteAdd());
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
					Societe s = new SocieteDAO().getById(selectedId);
					
					Home.replace(new SocieteEdit(s));
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une société à modifier.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
					int input = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer cette société ?", "ATTENTION !", JOptionPane.YES_NO_OPTION); 
					if(input==0) {
						int rowIndex = table.getSelectedRow();	
						int selectedId = Integer.parseInt(data[rowIndex][0]); 
						new SocieteDAO().deleteById(selectedId); 
						
						model.removeRow(rowIndex);
						JOptionPane.showMessageDialog(null, "Société supprimée.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une société à supprimer.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
				ArrayList<Societe> socs = new SocieteDAO().findSocietes(txt);
				String columns[] = {"ID", "SIRET", "NOM", "ADRESSE"};
				String data[][] = new String[socs.size()][columns.length];
				int i=0;
				for(Societe s: socs) {
					data[i][0]=s.getId()+"";
					data[i][1]=s.getNum_siret();
					data[i][2]=s.getNom();
					data[i][3]=s.getAdresse();
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
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
				ArrayList<Societe> socs = new SocieteDAO().getAll();
				String columns[] = {"ID", "SIRET", "NOM", "ADRESSE"};
				String data[][] = new String[socs.size()][columns.length];
				int i=0;
				for(Societe s: socs) {
					data[i][0]=s.getId()+"";
					data[i][1]=s.getNum_siret();
					data[i][2]=s.getNom();
					data[i][3]=s.getAdresse();
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
				trech.setText("");
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
