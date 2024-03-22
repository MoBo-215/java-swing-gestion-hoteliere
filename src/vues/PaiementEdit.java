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

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.TextField;
import dao.PaiementDAO;
import dao.ReservationDAO;
import entites.Paiement;
import entites.Reservation;

public class PaiementEdit extends JPanel {

	private static final long serialVersionUID = 1L;

	private Paiement paiement;

	private TextField tdate;
	private TextField tmontant;
	private TextField tmethode;

	private ImageIcon background;
	private JLabel bgLabel;
	/**
	 * Create the panel.
	 */
	public PaiementEdit(Paiement p) {
		this.paiement=p;
		
		setLayout(null);

		JLabel lblPaiementEdit = new JLabel("Modifier un paiement");
		lblPaiementEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaiementEdit.setFont(new Font("Calibri", Font.BOLD, 40));
		lblPaiementEdit.setBounds(0, 100, 1040, 51);
		add(lblPaiementEdit);
		
		JLabel lblDate = new JLabel("<html>Date<br>(aaaa-mm-jj)");
		lblDate.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDate.setBounds(226, 210, 135, 59);
		add(lblDate);
		
		tdate = new TextField();
		tdate.setBorder(new EmptyBorder(5, 5, 5, 5));
		tdate.setFont(new Font("Calibri", Font.PLAIN, 20));
		tdate.setColumns(10);
		tdate.setBounds(391, 225, 402, 44);
		add(tdate);
				
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setFont(new Font("Calibri", Font.BOLD, 25));
		lblMontant.setBounds(226, 319, 135, 42);
		add(lblMontant);
		
		tmontant = new TextField();
		tmontant.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tmontant.setFont(new Font("Calibri", Font.PLAIN, 20));
		tmontant.setColumns(10);
		tmontant.setBounds(391, 317, 402, 44);
		add(tmontant);
		
		tmethode = new TextField();
		tmethode.setFont(new Font("Calibri", Font.PLAIN, 20));
		tmethode.setColumns(10);
		tmethode.setBounds(391, 412, 402, 44);
		add(tmethode);
		
		JLabel lblMethode = new JLabel("Méthode");
		lblMethode.setFont(new Font("Calibri", Font.BOLD, 25));
		lblMethode.setBounds(226, 414, 135, 42);
		add(lblMethode);
		
		JLabel lblResa = new JLabel("Réservation");
		lblResa.setFont(new Font("Calibri", Font.BOLD, 25));
		lblResa.setBounds(226, 507, 135, 42);
		add(lblResa);
		
		JComboBox cbResa = new JComboBox();
		cbResa.setBounds(390, 507, 403, 42);
		add(cbResa);

		Button btnAjouter = new Button("Enregistrer");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = Home.convertStringToDate(tdate.getText());
				Float montant = Float.parseFloat(tmontant.getText()+"");
				String methode = tmethode.getText();
				Reservation resa = (Reservation)cbResa.getSelectedItem();
				
				if(date==null) {
					JOptionPane.showMessageDialog(null, "<html>Saisie de date incorrecte.<br>Ex correct : 2024-01-23</html>", "ERREUR PAIEMENT", JOptionPane.ERROR_MESSAGE);
				}else {
					paiement.setDate(date);
					paiement.setMontant(montant);
					paiement.setMethode(methode);
					paiement.setId_reservation(resa.getId());
					
					new PaiementDAO().save(paiement);
					JOptionPane.showMessageDialog(null, "Paiement modifié.");
					Home.replace(new PaiementList());
				}		
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 35));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(390, 601, 243, 59);
		add(btnAjouter);
		
		for(Reservation r: new ReservationDAO().getAll()) {
			cbResa.addItem(r);
		}
				
		JPanel pback = new JPanel();
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new PaiementList());
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
		
		tdate.setText(p.getDate().toString());
		tmontant.setText(p.getMontant()+"");
		tmethode.setText(p.getMethode());
		Reservation r = new ReservationDAO().getById(p.getId_reservation());
		cbResa.setSelectedItem(r);
		
		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setSize(1040, 750);
		add(bgLabel);
	}

}
