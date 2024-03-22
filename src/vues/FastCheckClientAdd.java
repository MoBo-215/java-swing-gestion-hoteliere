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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.TextField;
import dao.ClientDAO;
import entites.Chambre;
import entites.Client;

public class FastCheckClientAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon background;
	private JLabel bgLabel;

	private Chambre chambre;
	private Date datearrivee;
	private Date datedepart;
	private String nom;
	private String prenom;

	/**
	 * Create the panel.
	 */
	public FastCheckClientAdd(Chambre c, Date da, Date dd, String n, String pr) {
		this.chambre=c;
		this.datearrivee=da;
		this.datedepart=dd;
		this.nom=n;
		this.prenom=pr;
		
		setLayout(null);
		
		JLabel lblClientAdd = new JLabel("Ajouter un client");
		lblClientAdd.setBounds(0, 100, 1040, 51);
		lblClientAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientAdd.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblClientAdd);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNom.setBounds(50, 172, 135, 42);
		add(lblNom);
		
		TextField tnom = new TextField();
		tnom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnom.setColumns(10);
		tnom.setBorder(new EmptyBorder(5, 5, 5, 5));
		tnom.setBounds(215, 170, 288, 44);
		add(tnom);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPrenom.setBounds(50, 264, 135, 42);
		add(lblPrenom);
		
		TextField tprenom = new TextField();
		tprenom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tprenom.setColumns(10);
		tprenom.setBounds(215, 262, 288, 44);
		add(tprenom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAdresse.setBounds(50, 359, 155, 42);
		add(lblAdresse);
		
		TextField tadresse = new TextField();
		tadresse.setFont(new Font("Calibri", Font.PLAIN, 20));
		tadresse.setColumns(10);
		tadresse.setBounds(215, 357, 288, 44);
		add(tadresse);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAge.setBounds(549, 172, 155, 42);
		add(lblAge);
		
		TextField tage = new TextField();
		tage.setFont(new Font("Calibri", Font.PLAIN, 20));
		tage.setColumns(10);
		tage.setBounds(714, 170, 288, 44);
		add(tage);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setFont(new Font("Calibri", Font.BOLD, 25));
		lblVille.setBounds(50, 453, 155, 42);
		add(lblVille);
		
		TextField tville = new TextField();
		tville.setFont(new Font("Calibri", Font.PLAIN, 20));
		tville.setColumns(10);
		tville.setBounds(215, 451, 288, 44);
		add(tville);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri", Font.BOLD, 25));
		lblEmail.setBounds(549, 455, 135, 42);
		add(lblEmail);
		
		TextField temail = new TextField();
		temail.setFont(new Font("Calibri", Font.PLAIN, 20));
		temail.setColumns(10);
		temail.setBorder(new EmptyBorder(5, 5, 5, 5));
		temail.setBounds(714, 453, 288, 44);
		add(temail);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setFont(new Font("Calibri", Font.BOLD, 25));
		lblSexe.setBounds(549, 264, 135, 42);
		add(lblSexe);
		
		TextField tsexe = new TextField();
		tsexe.setFont(new Font("Calibri", Font.PLAIN, 20));
		tsexe.setColumns(10);
		tsexe.setBounds(714, 262, 288, 44);
		add(tsexe);
		
		JLabel lblTel = new JLabel("Téléphone");
		lblTel.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTel.setBounds(549, 361, 135, 42);
		add(lblTel);
		
		TextField ttel = new TextField();
		ttel.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		ttel.setFont(new Font("Calibri", Font.PLAIN, 20));
		ttel.setColumns(10);
		ttel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ttel.setBounds(714, 359, 288, 44);
		add(ttel);
		
		JLabel lblPays = new JLabel("<html>Pays de<br>résidence<html>");
		lblPays.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPays.setBounds(50, 534, 135, 79);
		add(lblPays);
		
		TextField tpays = new TextField();
		tpays.setFont(new Font("Calibri", Font.PLAIN, 20));
		tpays.setColumns(10);
		tpays.setBounds(215, 549, 288, 44);
		add(tpays);
		
		Button btnAjouter = new Button("Ajouter");
		btnAjouter.setBounds(402, 654, 243, 59);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = tnom.getText();
				String prenom = tprenom.getText();
				int age = Integer.parseInt(tage.getText());
				String sexe = tsexe.getText();
				String adresse = tadresse.getText();
				String ville = tville.getText();
				String tel = ttel.getText();
				String email = temail.getText();
				String pays = tpays.getText();
				
				Client cl = new Client(nom,prenom,age,sexe,adresse,ville,tel,email,pays);
				new ClientDAO().save(cl);
				JOptionPane.showMessageDialog(null, "Client ajouté.");
				Home.replace(new FastCheckResaAdd(c,da,dd));
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 35));
		btnAjouter.setBackground(Color.BLACK);
		add(btnAjouter);
		
		JPanel pback = new JPanel();
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new ClientList());
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
