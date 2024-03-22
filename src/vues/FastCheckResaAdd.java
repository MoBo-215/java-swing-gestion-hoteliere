package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.TextField;
import dao.ChambreDAO;
import dao.ClientDAO;
import dao.ReservationDAO;
import entites.Chambre;
import entites.Client;
import entites.Reservation;

public class FastCheckResaAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;
	
	private Chambre chambre;
	private Date datearrivee;
	private Date datedepart;
	
	/**
	 * Create the panel.
	 */
	public FastCheckResaAdd(Chambre c, Date da, Date dd) {
		this.chambre=c;
		this.datearrivee=da;
		this.datedepart=dd;
		
		setLayout(null);
		
		JLabel lblResaAdd = new JLabel("Finaliser une réservation");
		lblResaAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblResaAdd.setFont(new Font("Calibri", Font.BOLD, 40));
		lblResaAdd.setBounds(0, 100, 1040, 51);
		add(lblResaAdd);
		
		JLabel lblChambre = new JLabel("Chambre");
		lblChambre.setFont(new Font("Calibri", Font.BOLD, 25));
		lblChambre.setBounds(44, 227, 135, 42);
		add(lblChambre);
		
		TextField tchambre = new TextField();
		tchambre.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tchambre.setBorder(new EmptyBorder(5, 5, 5, 5));
		tchambre.setFont(new Font("Calibri", Font.PLAIN, 20));
		tchambre.setColumns(10);
		tchambre.setBounds(209, 225, 288, 44);
		add(tchambre);
				
		JLabel lblNomC = new JLabel("<html>Nom<br>client</html>");
		lblNomC.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNomC.setBounds(44, 302, 135, 59);
		add(lblNomC);
		
		TextField tnomc = new TextField();
		tnomc.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnomc.setColumns(10);
		tnomc.setBounds(209, 317, 288, 44);
		add(tnomc);
		
		JLabel lblPrenomC = new JLabel("<html>Prénom<br>client</html>");
		lblPrenomC.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPrenomC.setBounds(44, 399, 135, 59);
		add(lblPrenomC);
		
		TextField tprenomc = new TextField();
		tprenomc.setFont(new Font("Calibri", Font.PLAIN, 20));
		tprenomc.setColumns(10);
		tprenomc.setBounds(209, 414, 288, 44);
		add(tprenomc);
		
		JLabel lblDateDebut = new JLabel("<html>Date début<br>(aaaa-mm-jj)</html>");
		lblDateDebut.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDateDebut.setBounds(543, 210, 135, 59);
		add(lblDateDebut);
		
		TextField tdatedebut = new TextField();
		tdatedebut.setFont(new Font("Calibri", Font.PLAIN, 20));
		tdatedebut.setColumns(10);
		tdatedebut.setBorder(new EmptyBorder(5, 5, 5, 5));
		tdatedebut.setBounds(708, 225, 288, 44);
		add(tdatedebut);
		
		JLabel lblDateFin = new JLabel("<html>Date fin<br>(aaaa-mm-jj)</html>");
		lblDateFin.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDateFin.setBounds(543, 302, 135, 59);
		add(lblDateFin);
		
		TextField tdatefin = new TextField();
		tdatefin.setFont(new Font("Calibri", Font.PLAIN, 20));
		tdatefin.setColumns(10);
		tdatefin.setBounds(708, 317, 288, 44);
		add(tdatefin);
		
		JLabel lblNbPersonnes = new JLabel("Nb personnes");
		lblNbPersonnes.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNbPersonnes.setBounds(543, 416, 155, 42);
		add(lblNbPersonnes);
		
		TextField tnbpers = new TextField();
		// Instanciation d'un écouteur de clavier qui controle la saisie
		// afin de n'autoriser que l'utilisation de chiffres
		tnbpers.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	        	 // Instanciation d'un caractère avec un getter qui récupère la touche tapée
	             char c = e.getKeyChar();
	             // SI la touche tapée est inférieure à 0 ou supérieur à 9 ET n'est pas un point pour décimal
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_DECIMAL)) {
	            	 // la touche est ignorée
	                  e.consume();
	             }
	         }
	    });
		tnbpers.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnbpers.setColumns(10);
		tnbpers.setBounds(708, 414, 288, 44);
		add(tnbpers);
		
		// Instanciation du bouton Ajouter qui permet de transmettre
		// les données saisies afin d'enregistrer une réservation
		Button btnAjouter = new Button("Ajouter");
		// Ecouteur d'événement au clic sur le bouton
		// Suite d'actions se déclenche
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récup des saisies de l'user
				int id_chambre = chambre.getId();
				int nbpers = Integer.parseInt(tnbpers.getText()+"");
				Date datedebut = Home.convertStringToDate(tdatedebut.getText());
				Date datefin = Home.convertStringToDate(tdatefin.getText());

				String nom_client = tnomc.getText();
				String prenom_client = tprenomc.getText();
				
				// Recup d'un objet Client avec le nom prenom via méthode DAO
				Client c = new ClientDAO().getByNomPrenom(nom_client, prenom_client);
				
				// SI une des deux dates est nulle
				if(datedebut==null || datefin==null) {
					// Message d'erreur
					JOptionPane.showMessageDialog(null, "<html>Saisie de date incorrecte.<br>Ex correct : 2024-01-23</html>", "ERREUR RESERVATION", JOptionPane.ERROR_MESSAGE);
				// SI la date est antérieure à aujourd'hui
				// (qui sera en revanche possible dans l'ajout de résa via la gestion de tables classique
				}else if(datedebut.before(Home.convertStringToDate(LocalDate.now().toString()))){
					// Message d'erreur
					JOptionPane.showMessageDialog(null, "Date antérieure à aujourd'hui.", "ERREUR RESERVATION", JOptionPane.ERROR_MESSAGE);
				// SI il existe déjà une résa correspondant à la chambre et soit date début ou date fin du séjour saisies
				}else if(new ReservationDAO().findReservationByDateAndIdChambre(datedebut,id_chambre)!=null || new ReservationDAO().findReservationByDateAndIdChambre(datefin,id_chambre)!=null){
					// Message d'erreur
					JOptionPane.showMessageDialog(null, "Chambre indisponible à ces dates.", "ERREUR RESERVATION", JOptionPane.ERROR_MESSAGE);
				// SI la chambre choisie n'existe pas
				}else if(new ChambreDAO().getById(id_chambre)==null){
					// Message d'erreur et possibilité de consulter la liste des chambres si besoin
					JOptionPane.showMessageDialog(null, "<html>Chambre introuvable dans l'hôtel sélectionné.<br>Consulter la liste des chambres ?</html>", "ERREUR RESERVATION", JOptionPane.YES_NO_OPTION);
					// Initialisation du choix 
					int input=-1;
					// SI OUI
					if(input==0) {
						// Ouverture de la page ChambreList
						Home.replace(new ChambreList());
					}
				}else {
					//SI le client n'existe pas
					if(c==null) {
						// Message d'erreur et proposition d'ajouter un client
						int input = JOptionPane.showConfirmDialog(null, "<html>Client introuvable.<br>Voulez-vous ajouter un client ?</html>", "Message", JOptionPane.YES_NO_OPTION);
						// SI OUI
						if(input==0) {
							// Ouverture page ajout client avec paramètres qui permetttront
							// d'une part de créer le client avec le nom prénom déjà saisis
							// d'autre part de reremplir la page de réservation avec les champs préremplis précédemment
							Home.replace(new FastCheckClientAdd(chambre,da,dd,nom_client,prenom_client));
						}
					}else {
						// Recup id client si client existe déjà
						int id_client = c.getId();
						// Instanciation d'un objet Réservation via constructeur
						Reservation r = new Reservation(datedebut,datefin,nbpers,id_chambre,id_client);
						// Enregistrement de la nouvelle réservation dans la BDD
						new ReservationDAO().save(r);
						
						// Message de confirmation de l'ajout
						// Proposition de réglement de la resa
						int input2 = JOptionPane.showConfirmDialog(null, "<html>Réservation ajoutée.<br>Voulez-vous régler la réservation ?</html>", "Message", JOptionPane.YES_NO_OPTION);
						// SI OUI
						if(input2==0) {
							// Ouverture de la page ReservationList
							Home.replace(new ReservationList());
						// SINON
						}else {
							// Retour à la page d'accueil HomePanel
							Home.replace(new HomePanel());
						}
					}					
				}
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 35));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(400, 521, 243, 59);
		add(btnAjouter);

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
		
		// Pré remplissage de champs avec les données transmises en paramètres
		tchambre.setText(chambre.getNumero()+"");
		tdatedebut.setText(da.toString());
		tdatefin.setText(dd.toString());

		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setSize(1040, 750);
		add(bgLabel);
		
		
	}
}
