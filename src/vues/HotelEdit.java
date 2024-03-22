package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;

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

public class HotelEdit extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Hotel hotel;
	private ImageIcon background;
	private JLabel bgLabel;
	/**
	 * Create the panel.
	 */
	public HotelEdit(Hotel h) {
		this.hotel=h;
		
		setLayout(null);
		
		JLabel lblHotelEdit = new JLabel("Modifier un hôtel");
		lblHotelEdit.setBounds(0, 100, 1040, 51);
		lblHotelEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelEdit.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblHotelEdit);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(44, 177, 135, 42);
		lblNom.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblNom);
		
		TextField tnom = new TextField();
		tnom.setBounds(209, 175, 288, 44);
		tnom.setBorder(new EmptyBorder(5, 5, 5, 5));
		tnom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnom.setColumns(10);
		add(tnom);
				
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(44, 246, 135, 42);
		lblAdresse.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblAdresse);
		
		TextField tadresse = new TextField();
		tadresse.setBounds(209, 244, 288, 44);
		tadresse.setFont(new Font("Calibri", Font.PLAIN, 20));
		tadresse.setColumns(10);
		add(tadresse);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(44, 314, 155, 44);
		lblVille.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblVille);
		
		TextField tville = new TextField();
		tville.setBounds(209, 314, 288, 44);
		tville.setFont(new Font("Calibri", Font.PLAIN, 20));
		tville.setColumns(10);
		add(tville);
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDesc.setBounds(44, 390, 155, 42);
		add(lblDesc);
		
		TextField tdesc = new TextField();
		tdesc.setFont(new Font("Calibri", Font.PLAIN, 20));
		tdesc.setColumns(10);
		tdesc.setBounds(209, 388, 288, 44);
		add(tdesc);
		
		JLabel lblParking = new JLabel("Parking");
		lblParking.setFont(new Font("Calibri", Font.BOLD, 25));
		lblParking.setBounds(44, 458, 155, 42);
		add(lblParking);

		JComboBox cbParking = new JComboBox();
		cbParking.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbParking.setBackground(new Color(255, 255, 255));
		cbParking.setBounds(209, 458, 288, 42);
		add(cbParking);

		JLabel lblWifi = new JLabel("WIFI");
		lblWifi.setBounds(44, 520, 135, 42);
		lblWifi.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblWifi);

		JComboBox cbWifi = new JComboBox();
		cbWifi.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbWifi.setBackground(new Color(255, 255, 255));
		cbWifi.setBounds(209, 520, 288, 42);
		add(cbWifi);
		
		JLabel lblHArrivee = new JLabel("<html>Horaire<br>arrivée</html>");
		lblHArrivee.setFont(new Font("Calibri", Font.BOLD, 25));
		lblHArrivee.setBounds(550, 162, 135, 59);
		add(lblHArrivee);
		
		TextField tharrivee = new TextField();
		tharrivee.setFont(new Font("Calibri", Font.PLAIN, 20));
		tharrivee.setColumns(10);
		tharrivee.setBorder(new EmptyBorder(5, 5, 5, 5));
		tharrivee.setBounds(715, 177, 288, 44);
		add(tharrivee);
		
		JLabel lblHdepart = new JLabel("<html>Horaire<br>départ</html>");
		lblHdepart.setFont(new Font("Calibri", Font.BOLD, 25));
		lblHdepart.setBounds(550, 239, 135, 59);
		add(lblHdepart);
		
		TextField thdepart = new TextField();
		thdepart.setFont(new Font("Calibri", Font.PLAIN, 20));
		thdepart.setColumns(10);
		thdepart.setBounds(715, 246, 288, 44);
		add(thdepart);
		
		JLabel lblPiscine = new JLabel("Piscine");
		lblPiscine.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPiscine.setBounds(550, 316, 155, 44);
		add(lblPiscine);
		
		JComboBox cbPiscine = new JComboBox();
		cbPiscine.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbPiscine.setBackground(new Color(255, 255, 255));
		cbPiscine.setBounds(715, 314, 288, 42);
		add(cbPiscine);
		
		JLabel lblNavette = new JLabel("<html>Navette<br>aéroport</html>");
		lblNavette.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNavette.setBounds(550, 383, 155, 59);
		add(lblNavette);
		
		JComboBox cbNavette = new JComboBox();
		cbNavette.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbNavette.setBackground(new Color(255, 255, 255));
		cbNavette.setBounds(715, 390, 288, 42);
		add(cbNavette);
		
		JLabel lblAnimaux = new JLabel("<html>Animaux<br>compagnie</html>");
		lblAnimaux.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAnimaux.setBounds(550, 452, 155, 64);
		add(lblAnimaux);
		
		JComboBox cbAnimaux = new JComboBox();
		cbAnimaux.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbAnimaux.setBackground(new Color(255, 255, 255));
		cbAnimaux.setBounds(715, 458, 288, 42);
		add(cbAnimaux);

		JLabel lblEtoiles = new JLabel("Etoiles");
		lblEtoiles.setFont(new Font("Calibri", Font.BOLD, 25));
		lblEtoiles.setBounds(550, 522, 135, 42);
		add(lblEtoiles);
		
		JComboBox cbEtoiles = new JComboBox();
		cbEtoiles.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbEtoiles.setBackground(new Color(255, 255, 255));
		cbEtoiles.setBounds(715, 520, 288, 42);
		add(cbEtoiles);

		JLabel lblSociete = new JLabel("Société");
		lblSociete.setFont(new Font("Calibri", Font.BOLD, 25));
		lblSociete.setBounds(44, 585, 135, 42);
		add(lblSociete);
		
		JComboBox cbSociete = new JComboBox();
		cbSociete.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbSociete.setBackground(Color.WHITE);
		cbSociete.setBounds(209, 585, 288, 42);
		add(cbSociete);

		Button btnEnregistrer = new Button("Enregistrer");
		btnEnregistrer.setText("Enregistrer");
		btnEnregistrer.setBounds(402, 654, 243, 59);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String parking = cbParking.getSelectedItem().toString();
				String wifi = cbWifi.getSelectedItem().toString();
				String horaire_arrivee = tharrivee.getText();
				String horaire_depart = thdepart.getText();
				String piscine = cbPiscine.getSelectedItem().toString();
				String navette = cbNavette.getSelectedItem().toString();
				String animaux = cbAnimaux.getSelectedItem().toString();
				int etoiles = Integer.parseInt(cbEtoiles.getSelectedItem().toString());
				Societe s = (Societe)cbSociete.getSelectedItem();
				int id_societe = s.getId();				
				
				hotel.setNom(tnom.getText());
				hotel.setAdresse(tadresse.getText());
				hotel.setVille(tville.getText());
				hotel.setDescription(tdesc.getText());
				hotel.setDispo_parking_gratuit(parking);
				hotel.setPresence_wifi_gratuit(wifi);
				hotel.setHoraire_arrivee(horaire_arrivee);
				hotel.setHoraire_depart(horaire_depart);
				hotel.setPresence_piscine(piscine);
				hotel.setDispo_navette_aeroport(navette);
				hotel.setPossibilite_animaux_compagnie(animaux);
				hotel.setCategorie_etoiles(etoiles);
				hotel.setId_societe(id_societe);
				
				new HotelDAO().save(hotel);
				JOptionPane.showMessageDialog(null, "Hôtel modifié.");
				Home.replace(new HotelList());
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Calibri", Font.BOLD, 35));
		btnEnregistrer.setBackground(Color.BLACK);
		add(btnEnregistrer);
		
		String rep[] = {"OUI","NON"};
		String rep2[] = {"ADMIS", "NON ADMIS"};
		int nb[] = {1,2,3,4,5};
		
		Database.connect();
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
		
		tnom.setText(hotel.getNom());
		tadresse.setText(hotel.getAdresse());
		tville.setText(hotel.getVille());
		tdesc.setText(hotel.getDescription());
		cbParking.setSelectedItem(hotel.getDispo_parking_gratuit());
		cbWifi.setSelectedItem(hotel.getPresence_wifi_gratuit());
		tharrivee.setText(hotel.getHoraire_arrivee());
		thdepart.setText(hotel.getHoraire_depart());
		cbPiscine.setSelectedItem(hotel.getPresence_piscine());
		cbNavette.setSelectedItem(hotel.getDispo_navette_aeroport());
		cbAnimaux.setSelectedItem(hotel.getPossibilite_animaux_compagnie());
		cbEtoiles.setSelectedItem(hotel.getCategorie_etoiles());
		
		
		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, 1040, 750);
		add(bgLabel);
		
	}
}
