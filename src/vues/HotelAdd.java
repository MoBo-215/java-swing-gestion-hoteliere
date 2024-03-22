package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.TextField;
import dao.HotelDAO;
import dao.SocieteDAO;
import entites.Database;
import entites.Hotel;
import entites.Societe;


public class HotelAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;
	/**
	 * Create the panel.
	 */
	public HotelAdd() {
		setLayout(null);

		JLabel lblHotelAdd = new JLabel("Ajouter un hôtel");
		lblHotelAdd.setBounds(0, 100, 1040, 51);
		lblHotelAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelAdd.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblHotelAdd);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(42, 171, 135, 42);
		lblNom.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblNom);
		
		TextField tnom = new TextField();
		tnom.setBounds(207, 169, 288, 44);
		tnom.setBorder(new EmptyBorder(5, 5, 5, 5));
		tnom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnom.setColumns(10);
		add(tnom);
				
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(42, 240, 135, 42);
		lblAdresse.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblAdresse);
		
		TextField tadresse = new TextField();
		tadresse.setBounds(207, 238, 288, 44);
		tadresse.setFont(new Font("Calibri", Font.PLAIN, 20));
		tadresse.setColumns(10);
		add(tadresse);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(42, 308, 155, 44);
		lblVille.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblVille);
		
		TextField tville = new TextField();
		tville.setBounds(207, 308, 288, 44);
		tville.setFont(new Font("Calibri", Font.PLAIN, 20));
		tville.setColumns(10);
		add(tville);
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDesc.setBounds(42, 384, 155, 42);
		add(lblDesc);
		
		TextField tdesc = new TextField();
		tdesc.setFont(new Font("Calibri", Font.PLAIN, 20));
		tdesc.setColumns(10);
		tdesc.setBounds(207, 382, 288, 44);
		add(tdesc);
		
		JLabel lblParking = new JLabel("Parking");
		lblParking.setFont(new Font("Calibri", Font.BOLD, 25));
		lblParking.setBounds(42, 452, 155, 42);
		add(lblParking);

		JComboBox cbParking = new JComboBox();
		cbParking.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbParking.setBackground(new Color(255, 255, 255));
		cbParking.setBounds(207, 452, 288, 42);
		add(cbParking);

		JLabel lblWifi = new JLabel("WIFI");
		lblWifi.setBounds(42, 518, 135, 42);
		lblWifi.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblWifi);

		JComboBox cbWifi = new JComboBox();
		cbWifi.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbWifi.setBackground(new Color(255, 255, 255));
		cbWifi.setBounds(207, 518, 288, 42);
		add(cbWifi);
		
		JLabel lblHArrivee = new JLabel("<html>Horaire<br>arrivée</html>");
		lblHArrivee.setFont(new Font("Calibri", Font.BOLD, 25));
		lblHArrivee.setBounds(548, 156, 135, 59);
		add(lblHArrivee);
		
		TextField tharrivee = new TextField();
		tharrivee.setFont(new Font("Calibri", Font.PLAIN, 20));
		tharrivee.setColumns(10);
		tharrivee.setBorder(new EmptyBorder(5, 5, 5, 5));
		tharrivee.setBounds(713, 171, 288, 44);
		add(tharrivee);
		
		JLabel lblHdepart = new JLabel("<html>Horaire<br>départ</html>");
		lblHdepart.setFont(new Font("Calibri", Font.BOLD, 25));
		lblHdepart.setBounds(548, 233, 135, 59);
		add(lblHdepart);
		
		TextField thdepart = new TextField();
		thdepart.setFont(new Font("Calibri", Font.PLAIN, 20));
		thdepart.setColumns(10);
		thdepart.setBounds(713, 240, 288, 44);
		add(thdepart);
		
		JLabel lblPiscine = new JLabel("Piscine");
		lblPiscine.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPiscine.setBounds(548, 310, 155, 44);
		add(lblPiscine);
		
		JComboBox cbPiscine = new JComboBox();
		cbPiscine.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbPiscine.setBackground(new Color(255, 255, 255));
		cbPiscine.setBounds(713, 308, 288, 42);
		add(cbPiscine);
		
		JLabel lblNavette = new JLabel("<html>Navette<br>aéroport</html>");
		lblNavette.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNavette.setBounds(548, 377, 155, 59);
		add(lblNavette);
		
		JComboBox cbNavette = new JComboBox();
		cbNavette.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbNavette.setBackground(new Color(255, 255, 255));
		cbNavette.setBounds(713, 384, 288, 42);
		add(cbNavette);
		
		JLabel lblAnimaux = new JLabel("<html>Animaux<br>compagnie</html>");
		lblAnimaux.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAnimaux.setBounds(548, 446, 155, 64);
		add(lblAnimaux);
		
		JComboBox cbAnimaux = new JComboBox();
		cbAnimaux.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbAnimaux.setBackground(new Color(255, 255, 255));
		cbAnimaux.setBounds(713, 452, 288, 42);
		add(cbAnimaux);

		JLabel lblEtoiles = new JLabel("Etoiles");
		lblEtoiles.setFont(new Font("Calibri", Font.BOLD, 25));
		lblEtoiles.setBounds(548, 520, 135, 42);
		add(lblEtoiles);
		
		JComboBox cbEtoiles = new JComboBox();
		cbEtoiles.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbEtoiles.setBackground(new Color(255, 255, 255));
		cbEtoiles.setBounds(713, 518, 288, 42);
		add(cbEtoiles);
		
		JLabel lblSociete = new JLabel("Société");
		lblSociete.setFont(new Font("Calibri", Font.BOLD, 25));
		lblSociete.setBounds(42, 587, 135, 42);
		add(lblSociete);
		
		JComboBox cbSociete = new JComboBox();
		cbSociete.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbSociete.setBackground(Color.WHITE);
		cbSociete.setBounds(207, 587, 288, 42);
		add(cbSociete);

		Button btnAjouter = new Button("Ajouter");
		btnAjouter.setBounds(402, 654, 243, 59);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = tnom.getText();
				String adresse = tadresse.getText();
				String ville = tville.getText();
				String desc = tdesc.getText();
				String parking = cbParking.getSelectedItem().toString();
				String wifi = cbWifi.getSelectedItem().toString();
				String harrivee = tharrivee.getText();
				String hdepart = thdepart.getText();
				String piscine = cbPiscine.getSelectedItem().toString();
				String navette = cbNavette.getSelectedItem().toString();
				String animaux = cbAnimaux.getSelectedItem().toString();
				int etoiles = Integer.parseInt(cbEtoiles.getSelectedItem().toString());
				Societe s = (Societe)cbSociete.getSelectedItem();
				int id_societe = s.getId();
				
				Hotel h = new Hotel();
				
				h.setNom(tnom.getText());
				h.setAdresse(tadresse.getText());
				h.setVille(tville.getText());
				h.setDescription(tdesc.getText());
				h.setDispo_parking_gratuit(parking);
				h.setPresence_wifi_gratuit(wifi);
				h.setHoraire_arrivee(harrivee);
				h.setHoraire_depart(hdepart);
				h.setPresence_piscine(piscine);
				h.setDispo_navette_aeroport(navette);
				h.setPossibilite_animaux_compagnie(animaux);
				h.setCategorie_etoiles(etoiles);
				h.setId_societe(id_societe);
				
				new HotelDAO().save(h);
				JOptionPane.showMessageDialog(null, "Hôtel ajouté.");
				Home.replace(new HotelList());
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 35));
		btnAjouter.setBackground(Color.BLACK);
		add(btnAjouter);
		
		String rep[] = {"OUI","NON"};
		String rep2[] = {"ADMIS", "NON ADMIS"};
		int nb[] = {1,2,3,4,5};
		
		for(String r: rep) {
			cbParking.addItem(r);
		}
		for(String r: rep) {
			cbWifi.addItem(r);
		}
		for(String r: rep) {
			cbPiscine.addItem(r);
		}
		for(String r: rep) {
			cbNavette.addItem(r);
		}
		for(String r: rep2) {
			cbAnimaux.addItem(r);
		}
		for(int nbr: nb) {
			cbEtoiles.addItem(nbr);
		}
		Database.connect();
		for(Societe s: new SocieteDAO().getAll()) {
			cbSociete.addItem(s);
		}
		
		JPanel pback = new JPanel();
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new HotelList());
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
		bgLabel.setBounds(0, 0, 1040, 750);
		add(bgLabel);
	}
}
