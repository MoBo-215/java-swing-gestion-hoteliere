package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import components.Panel;
import dao.ChambreDAO;
import dao.ClientDAO;
import dao.HotelDAO;
import dao.PaiementDAO;
import dao.ReservationDAO;
import dao.SocieteDAO;
import entites.Database;

public class StatsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;

	/**
	 * Create the panel.
	 */
	public StatsPanel() {
		setLayout(null);

		JLabel lblStats = new JLabel("Statistiques générales");
		lblStats.setBounds(0, 100, 1040, 51);
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblStats);
		
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
		
		Panel pfastcheck2 = new Panel();
		pfastcheck2.setBackground(Color.BLACK);
		pfastcheck2.setBounds(34, 195, 473, 521);
		add(pfastcheck2);
		pfastcheck2.setLayout(null);
		
		JLabel lblNbElements = new JLabel("Chiffres globaux");
		lblNbElements.setHorizontalAlignment(SwingConstants.CENTER);
		lblNbElements.setForeground(new Color(252, 217, 73));
		lblNbElements.setFont(new Font("Calibri", Font.PLAIN, 35));
		lblNbElements.setBounds(10, 20, 441, 41);
		pfastcheck2.add(lblNbElements);
		
		Database.connect();
		JLabel lblNbSocietes = new JLabel(new SocieteDAO().nbSocietes()+" societés");
		lblNbSocietes.setForeground(new Color(255, 255, 255));
		lblNbSocietes.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNbSocietes.setBounds(41, 82, 397, 60);
		pfastcheck2.add(lblNbSocietes);
		
		JLabel lblNbHotels = new JLabel(new HotelDAO().nbHotels()+" hôtels");
		lblNbHotels.setForeground(Color.WHITE);
		lblNbHotels.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNbHotels.setBounds(41, 157, 397, 60);
		pfastcheck2.add(lblNbHotels);
		
		JLabel lblNbChambres = new JLabel(new ChambreDAO().nbChambres()+" chambres");
		lblNbChambres.setForeground(Color.WHITE);
		lblNbChambres.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNbChambres.setBounds(41, 227, 397, 60);
		pfastcheck2.add(lblNbChambres);
		
		JLabel lblNbClients = new JLabel(new ClientDAO().nbClients()+" clients");
		lblNbClients.setForeground(Color.WHITE);
		lblNbClients.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNbClients.setBounds(41, 297, 397, 60);
		pfastcheck2.add(lblNbClients);
		
		JLabel lblNbReservations = new JLabel(new ReservationDAO().nbReservations()+" réservations");
		lblNbReservations.setForeground(Color.WHITE);
		lblNbReservations.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNbReservations.setBounds(41, 367, 397, 60);
		pfastcheck2.add(lblNbReservations);
		
		JLabel lblNbPaiements = new JLabel(new PaiementDAO().nbPaiements()+" paiements");
		lblNbPaiements.setForeground(Color.WHITE);
		lblNbPaiements.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNbPaiements.setBounds(41, 437, 397, 60);
		pfastcheck2.add(lblNbPaiements);
		
		Panel pfastcheck2_1 = new Panel();
		pfastcheck2_1.setBackground(Color.BLACK);
		pfastcheck2_1.setBounds(545, 195, 451, 521);
		add(pfastcheck2_1);
		pfastcheck2_1.setLayout(null);
		
		JLabel lblCA = new JLabel("Chiffres d'affaire");
		lblCA.setHorizontalAlignment(SwingConstants.CENTER);
		lblCA.setForeground(new Color(252, 217, 73));
		lblCA.setFont(new Font("Calibri", Font.PLAIN, 35));
		lblCA.setBounds(10, 20, 418, 41);
		pfastcheck2_1.add(lblCA);
		
		// Récupération du résultat d'une méthode DAO qui calcule le chiffre d'affaire par an
		double total = new PaiementDAO().totalCAparAn();
		JLabel lblResultatCA = new JLabel("CA 2024 (en cours) : "+total+"0€");
		lblResultatCA.setForeground(Color.WHITE);
		lblResultatCA.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblResultatCA.setBounds(31, 80, 397, 60);
		pfastcheck2_1.add(lblResultatCA);
		
		// Idem pour le mois en cours
		double totalmois = new PaiementDAO().totalCAparMois();
		JLabel lblResultatCAmois = new JLabel("CA des 30 derniers jours : "+totalmois+"0€");
		lblResultatCAmois.setForeground(Color.WHITE);
		lblResultatCAmois.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblResultatCAmois.setBounds(31, 151, 397, 60);
		pfastcheck2_1.add(lblResultatCAmois);
		
		// Idem pour la journée en cours
		double totaljour = new PaiementDAO().totalCAparJour();
		JLabel lblResultatCAjour = new JLabel("CA du jour : "+totaljour+"0€");
		lblResultatCAjour.setForeground(Color.WHITE);
		lblResultatCAjour.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblResultatCAjour.setBounds(31, 221, 397, 60);
		pfastcheck2_1.add(lblResultatCAjour);
		
		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, 1040, 750);
		add(bgLabel);		
	}
}
