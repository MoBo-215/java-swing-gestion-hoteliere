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
import dao.PaiementDAO;
import dao.ReservationDAO;
import entites.Paiement;
import entites.Reservation;
import javax.swing.JComboBox;

public class PaiementAdd extends JPanel {

	private static final long serialVersionUID = 1L;

	private TextField tdate;
	private TextField tmontant;
	private TextField tmethode;

	private ImageIcon background;
	private JLabel bgLabel;
	/**
	 * Create the panel.
	 */
	public PaiementAdd() {
		setLayout(null);

		JLabel lblPaiementAdd = new JLabel("Ajouter un paiement");
		lblPaiementAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaiementAdd.setFont(new Font("Calibri", Font.BOLD, 40));
		lblPaiementAdd.setBounds(0, 100, 1040, 51);
		add(lblPaiementAdd);
		
		JLabel lblDate = new JLabel("<html>Date<br>(aaaa-mm-jj)");
		lblDate.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDate.setBounds(226, 202, 135, 67);
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

		Button btnAjouter = new Button("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = Home.convertStringToDate(tdate.getText());
				Double montant = Double.parseDouble(tmontant.getText()+"");
				String methode = tmethode.getText();
				Reservation resa = (Reservation)cbResa.getSelectedItem();
				
				if(date==null) {
					JOptionPane.showMessageDialog(null, "<html>Saisie de date incorrecte.<br>Ex correct : 2024-01-23</html>", "ERREUR PAIEMENT", JOptionPane.ERROR_MESSAGE);
				}else {
					Paiement p = new Paiement(date,montant,methode,resa.getId());
					new PaiementDAO().save(p);
					JOptionPane.showMessageDialog(null, "Paiement ajouté.");
					Home.replace(new PaiementList());					
				}
				
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 35));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(390, 601, 243, 59);
		add(btnAjouter);
		
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
		
		for(Reservation r: new ReservationDAO().getAll()) {
			cbResa.addItem(r);
		}
		
		tdate.setText(Home.convertStringToDate(LocalDate.now().toString())+"");
		
		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setSize(1040, 750);
		add(bgLabel);
	}
}
