package vues;

import javax.swing.JPanel;

import components.Button;
import components.Panel;
import components.TextField;
import dao.ReservationDAO;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;
	
	/**
	 * Create the panel.
	 */
	public HomePanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Gérez votre groupe<br>vraiment comme<br>vous le souhaitez !</html>");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 40));
		lblNewLabel.setBounds(49, 231, 350, 192);
		add(lblNewLabel);

		JComboBox cbGestion = new JComboBox();
		cbGestion.setBackground(new Color(255, 255, 255));
		cbGestion.setFont(new Font("Calibri", Font.PLAIN, 25));
		cbGestion.setBounds(49, 429, 350, 46);
		add(cbGestion);
		
		String tables[] = {"Chambre", "Client", "Hôtel", "Paiement", "Réservation", "Société"};
		for(String t: tables) {
			cbGestion.addItem(t);
		}
		
		Button btnGo = new Button("GO !");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = cbGestion.getSelectedIndex();
				if(row==0) {
					Home.replace(new ChambreList());
				}else if(row==1) {
					Home.replace(new ClientList());
				}else if(row==2) {
					Home.replace(new HotelList());
				}else if(row==3) {
					Home.replace(new PaiementList());
				}else if(row==4) {
					Home.replace(new ReservationList());
				}else if(row==5) {
					Home.replace(new SocieteList());
				}
			}
		});
		btnGo.setText("GO !");
		btnGo.setForeground(new Color(255, 255, 255));
		btnGo.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnGo.setBackground(new Color(0, 0, 0));
		btnGo.setBounds(267, 498, 130, 35);
		add(btnGo);
		
		Button btnStats = new Button("Stats");
		btnStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new StatsPanel());
			}
		});
		btnStats.setText("Stats");
		btnStats.setForeground(Color.WHITE);
		btnStats.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnStats.setBackground(Color.BLACK);
		btnStats.setBounds(22, 695, 130, 35);
		add(btnStats);

		Panel pfastcheck = new Panel();
		pfastcheck.setBackground(new Color(0, 0, 0));
		pfastcheck.setBounds(470, 113, 526, 468);
		add(pfastcheck);
		pfastcheck.setLayout(null);
		
		JLabel lblEffResa = new JLabel("Effectuer une réservation");
		lblEffResa.setHorizontalAlignment(SwingConstants.CENTER);
		lblEffResa.setFont(new Font("Calibri", Font.BOLD, 35));
		lblEffResa.setForeground(new Color(252, 216, 73));
		lblEffResa.setBackground(new Color(0, 0, 0));
		lblEffResa.setBounds(10, 10, 506, 55);
		pfastcheck.add(lblEffResa);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setFont(new Font("Calibri", Font.BOLD, 25));
		lblVille.setForeground(new Color(255, 255, 255));
		lblVille.setBackground(new Color(0, 0, 0));
		lblVille.setBounds(38, 97, 120, 41);
		pfastcheck.add(lblVille);
		
		JLabel lblDateArrivee = new JLabel("<html>Arrivée<br>(aaaa-mm-jj)</html>");
		lblDateArrivee.setForeground(Color.WHITE);
		lblDateArrivee.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDateArrivee.setBackground(Color.BLACK);
		lblDateArrivee.setBounds(38, 173, 178, 55);
		pfastcheck.add(lblDateArrivee);
		
		JLabel lblDateDepart = new JLabel("<html>Départ<br>(aaaa-mm-jj)</html>");
		lblDateDepart.setForeground(Color.WHITE);
		lblDateDepart.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDateDepart.setBackground(Color.BLACK);
		lblDateDepart.setBounds(38, 268, 178, 62);
		pfastcheck.add(lblDateDepart);
		
		TextField tville = new TextField();
		tville.setText("Paris");
		tville.setFont(new Font("Calibri", Font.PLAIN, 25));
		tville.setBounds(226, 97, 240, 41);
		pfastcheck.add(tville);
		
		TextField tdatearrivee = new TextField();
		tdatearrivee.setText("2024-04-11");
		tdatearrivee.setFont(new Font("Calibri", Font.PLAIN, 25));
		tdatearrivee.setBounds(226, 187, 240, 41);
		pfastcheck.add(tdatearrivee);
		
		TextField tdatedepart = new TextField();
		tdatedepart.setText("2024-04-15");
		tdatedepart.setFont(new Font("Calibri", Font.PLAIN, 25));
		tdatedepart.setBounds(226, 282, 240, 41);
		pfastcheck.add(tdatedepart);
		
		Button btnRechercher = new Button("Rechercher un hôtel");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération du contenu des champs à remplir
				String ville = tville.getText();
				// Ici utilisation d'une méthode qui permet la conversion d'une saisie type string
				// en une donnée de type Date
				Date datearrivee = Home.convertStringToDate(tdatearrivee.getText());
				Date datedepart = Home.convertStringToDate(tdatedepart.getText());
				
				// Vérification de la bonne récupération de la date
				if(datearrivee==null || datedepart==null) {
					JOptionPane.showMessageDialog(null, "<html>Saisie de date incorrecte.<br>Ex correct : 2024-01-23</html>", "ERREUR RESERVATION", JOptionPane.ERROR_MESSAGE);
				// Vérification si la date n'est pas antérieure à la date du jour
				}else if(datearrivee.before(Home.convertStringToDate(LocalDate.now().toString()))){
					JOptionPane.showMessageDialog(null, "Date antérieure à aujourd'hui.", "ERREUR RESERVATION", JOptionPane.ERROR_MESSAGE);
				// Si tout est ok, on ouvre une page avec le résultat de la recherche
				// Transmission des données saisies sur cette page en paramètres de la page suivante
				// car nécessaires pour la recherche
				}else {
					Home.replace(new FastCheckHotelRoomChoice(ville,datearrivee,datedepart));
				}
			}
		});
		btnRechercher.setBackground(new Color(252, 216, 73));
		btnRechercher.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnRechercher.setBounds(117, 381, 298, 48);
		pfastcheck.add(btnRechercher);
		
		Panel pfastcheck2 = new Panel();
		pfastcheck.setLayout(null);
		pfastcheck2.setBackground(new Color(0, 0, 0));
		pfastcheck2.setBounds(470, 591, 526, 139);
		add(pfastcheck2);
		pfastcheck2.setLayout(null);
		
		JLabel lblReglerResa = new JLabel("Régler les réservations");
		lblReglerResa.setForeground(Color.WHITE);
		lblReglerResa.setFont(new Font("Calibri", Font.BOLD, 35));
		lblReglerResa.setBackground(Color.BLACK);
		lblReglerResa.setBounds(28, 23, 353, 90);
		pfastcheck2.add(lblReglerResa);

		Button btnGoResa = new Button("GO");
		btnGoResa.setBounds(391, 23, 111, 90);
		pfastcheck2.add(btnGoResa);
		btnGoResa.setText("GO");
		btnGoResa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new ReservationList());
			}
		});
		btnGoResa.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnGoResa.setBackground(new Color(252, 216, 73));

		// Importation d'une image qui servira de background pour toutes les pages de l'application
		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgLabel.setSize(1040, 750);
		add(bgLabel);
	}
}
