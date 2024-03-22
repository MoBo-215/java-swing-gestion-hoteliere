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
import java.util.InputMismatchException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.TextField;
import dao.ChambreDAO;
import dao.PaiementDAO;
import dao.ReservationDAO;
import entites.Paiement;
import entites.Reservation;

public class PaiementAddFromResa extends JPanel {

	private static final long serialVersionUID = 1L;

	private TextField tdate;
	private TextField tmontant;
	private TextField tmethode;

	private ImageIcon background;
	private JLabel bgLabel;
	
	private Reservation reservation;
	/**
	 * Create the panel.
	 */
	public PaiementAddFromResa(Reservation r) {
		this.reservation=r;
		setLayout(null);

		JLabel lblPaiementAdd = new JLabel("Ajouter un paiement");
		lblPaiementAdd.setBounds(0, 100, 1040, 51);
		lblPaiementAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaiementAdd.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblPaiementAdd);
		
		JLabel lblDate = new JLabel("<html>Date<br>(aaaa-mm-jj)");
		lblDate.setBounds(226, 202, 135, 67);
		lblDate.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblDate);
		
		tdate = new TextField();
		tdate.setBounds(391, 225, 402, 44);
		tdate.setBorder(new EmptyBorder(5, 5, 5, 5));
		tdate.setFont(new Font("Calibri", Font.PLAIN, 20));
		tdate.setColumns(10);
		add(tdate);
				
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setBounds(226, 319, 135, 42);
		lblMontant.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblMontant);
		
		tmontant = new TextField();
		tmontant.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_DECIMAL)) {
	                  e.consume();
	             }
	         }
	    });
		tmontant.setBounds(391, 317, 402, 44);
		tmontant.setFont(new Font("Calibri", Font.PLAIN, 20));
		tmontant.setColumns(10);
		add(tmontant);
		
		tmethode = new TextField();
		tmethode.setBounds(391, 412, 402, 44);
		tmethode.setFont(new Font("Calibri", Font.PLAIN, 20));
		tmethode.setColumns(10);
		add(tmethode);
		
		JLabel lblMethode = new JLabel("Méthode");
		lblMethode.setBounds(226, 414, 135, 42);
		lblMethode.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblMethode);
		
		JLabel lblResa = new JLabel("Réservation");
		lblResa.setBounds(226, 507, 135, 42);
		lblResa.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblResa);
		
		JComboBox cbResa = new JComboBox();
		cbResa.setBounds(390, 507, 403, 42);
		add(cbResa);

		Button btnAjouter = new Button("Ajouter");
		btnAjouter.setBounds(390, 601, 243, 59);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récup des saisies 
				Date date = Home.convertStringToDate(tdate.getText());
				Double montant = Double.parseDouble(tmontant.getText()+"");
				String methode = tmethode.getText();
				Reservation resa = (Reservation)cbResa.getSelectedItem();
				
				// SI la date n'est pas valable
				if(date==null) {
					// Message d'erreur
					JOptionPane.showMessageDialog(null, "<html>Saisie de date incorrecte.<br>Ex correct : 2024-01-23</html>", "ERREUR PAIEMENT", JOptionPane.ERROR_MESSAGE);
				// SINON
				}else {
					// Instanciation d'un objet Paiement via constructeur
					Paiement p = new Paiement(date,montant,methode,resa.getId());
					// Enregistrement dans la BDD
					new PaiementDAO().save(p);
					// Message d'ajout ok
					JOptionPane.showMessageDialog(null, "Paiement ajouté.");
					// Redirection à la page PaiementList
					Home.replace(new PaiementList());					
				}
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 35));
		btnAjouter.setBackground(Color.BLACK);
		add(btnAjouter);
		
		JPanel pback = new JPanel();
		pback.setBounds(10, 0, 97, 83);
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new PaiementList());
			}
		});
		pback.setBackground(new Color(255, 235, 160));
		add(pback);		
		pback.setLayout(null);
		
		JLabel lblBack = new JLabel("<<");
		lblBack.setFont(new Font("Calibri", Font.BOLD, 40));
		lblBack.setBounds(10, 10, 47, 63);
		pback.add(lblBack);
		
		// Boucle qui permet de remplir le combobox avec les réservations
		for(Reservation re: new ReservationDAO().getAll()) {
			cbResa.addItem(re);
		}
		
		// Initialisation des variables de calcul pour préremplir le champ du montant avec le montant du séjour
		double totalR=0;
		int nbJoursSejour=0;

		// Calcul du montant de la nouvelle réservation
		nbJoursSejour = Home.calculNbJoursReservation(r.getDate_fin(),r.getDate_debut());
		totalR=nbJoursSejour*new ChambreDAO().getById(new ReservationDAO().getById(r.getId()).getId_chambre()).getPrix_nuit();
		
		// Remplissage des champs avec les données disponibles
		// - Date du jour pour la date du paiement
		// - Montant de la réservation
		// - Réservation (objet dans le combobox)
		tdate.setText(Home.convertStringToDate(LocalDate.now().toString())+"");
		tmontant.setText(totalR+"");
		cbResa.setSelectedItem(r);
		
		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, 1040, 750);
		add(bgLabel);
	}
}
