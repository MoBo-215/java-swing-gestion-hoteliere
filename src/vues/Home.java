package vues;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setResizable(false);
		setTitle("DyoDyo - Gestion hôtelière");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mnMenuBar = new JMenuBar();
		mnMenuBar.setBounds(0, 0, 50, 24);
		setJMenuBar(mnMenuBar);
		
		JMenu mnMenu = new JMenu("MENU");
		mnMenuBar.add(mnMenu);
		
		JMenuItem mnitAccueil = new JMenuItem("Accueil");
		mnitAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new HomePanel());
			}
		});
		mnMenu.add(mnitAccueil);
		
		JMenu mnGestion = new JMenu("Gestion");
		mnMenu.add(mnGestion);
		
		JMenuItem mnitChambre = new JMenuItem("Chambre");
		mnitChambre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new ChambreList());
			}
		});
		mnGestion.add(mnitChambre);
		
		JMenuItem mnitClient = new JMenuItem("Client");
		mnitClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new ClientList());
			}
		});
		mnGestion.add(mnitClient);
		
		JMenuItem mnitHotel = new JMenuItem("Hotel");
		mnitHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new HotelList());
			}
		});
		mnGestion.add(mnitHotel);
		
		JMenuItem mnitPaiement = new JMenuItem("Paiement");
		mnitPaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new PaiementList());
			}
		});
		mnGestion.add(mnitPaiement);
		
		JMenuItem mnitReservation = new JMenuItem("Réservation");
		mnitReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new ReservationList());
			}
		});
		mnGestion.add(mnitReservation);
		
		JMenuItem mnitSociete = new JMenuItem("Société");
		mnitSociete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.replace(new SocieteList());
			}
		});
		mnGestion.add(mnitSociete);

		setBounds(250, 0, 1060, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		HomePanel panel = new HomePanel();
		panel.setBounds(0,0,1060,820);
		contentPane.add(panel);		
	}
	
	public static void replace(JPanel p) {
		p.setBounds(0,0,1060,820);
		contentPane.removeAll();
		contentPane.add(p);
		contentPane.revalidate();
	}
	
	public static Date convertStringToDate(String text){
		// Méthode qui permet de convertir une saisie de type String en format Date
		try {
			// Formatage de la saisie
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// Conversion de la saisie de type String en format LocalDate
			LocalDate localDate = LocalDate.parse(text, formatter);
			// Récupération de la valeur contenue dans localDate dans un objet Date
			Date date = Date.valueOf(localDate);
			// Retourne un objet de type Date avec la date saisie
			return date;
		}catch(Exception ex){
			// Dans le cas d'une exception (type de saisie incorrect, trop de caractères, etc)
			// Rien ne sera retourné
			return null;
		}
	}
	
	// Méthode qui calcule le nombre de jours entre deux dates de réservations.
	// Nécessaire au calcul du montant de la réservation.
	public static int calculNbJoursReservation(Date dateFin, Date dateDebut){
		// On entre en paramètres la date du début du séjour et la date de fin.
	    try {
	    	// On récupère dans une variable de type long la différence en millisecondes entre les deux dates.
	        long diff = dateFin.getTime() - dateDebut.getTime();
	        // On convertit via un calcul cette donnée en une variable de type float qui correspond aux jours.
	        float nb_float = (diff/(1000*60*60*24));
	        // On arrondit à la valeur inférieure pour obtenir un chiffre valable pour le calcul de jours.
	        int nb = Math.round(nb_float);
	        // On retourne le nombre de jours.
	        return nb;
	    }catch (Exception ex) {
	    	// On renvoie une exception en cas de saisie incorrecte.
		    System.out.println("Saisie incorrecte.");
		    // Dans le cas d'une exception (type de saisie incorrect, trop de caractères, etc)
		    // On retourne -1.
		    return -1;
	    }
	}
}
