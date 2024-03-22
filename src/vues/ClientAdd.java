package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.TextField;
import dao.ClientDAO;
import entites.Client;

public class ClientAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TextField tnom;
	private TextField tprenom;
	private TextField tadresse;
	
	private ImageIcon background;
	private JLabel bgLabel;

	
	/**
	 * Create the panel.
	 */
	
	public ClientAdd() {
		
		setLayout(null);
		
		JLabel lblClientAdd = new JLabel("Ajouter un client");
		lblClientAdd.setBounds(0, 100, 1040, 51);
		lblClientAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientAdd.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblClientAdd);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(44, 194, 135, 42);
		lblNom.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblNom);
		
		tnom = new TextField();
		tnom.setBounds(209, 192, 288, 44);
		tnom.setBorder(new EmptyBorder(5, 5, 5, 5));
		tnom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnom.setColumns(10);
		add(tnom);
				
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(44, 286, 135, 42);
		lblPrenom.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblPrenom);
		
		tprenom = new TextField();
		tprenom.setBounds(209, 284, 288, 44);
		tprenom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tprenom.setColumns(10);
		add(tprenom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(44, 381, 155, 42);
		lblAdresse.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblAdresse);
		
		tadresse = new TextField();
		tadresse.setBounds(209, 379, 288, 44);
		tadresse.setFont(new Font("Calibri", Font.PLAIN, 20));
		tadresse.setColumns(10);
		add(tadresse);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAge.setBounds(543, 194, 155, 42);
		add(lblAge);
		
		TextField tage = new TextField();
		tage.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tage.setFont(new Font("Calibri", Font.PLAIN, 20));
		tage.setColumns(10);
		tage.setBounds(708, 192, 288, 44);
		add(tage);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setFont(new Font("Calibri", Font.BOLD, 25));
		lblVille.setBounds(44, 475, 155, 42);
		add(lblVille);
		
		TextField tville = new TextField();
		tville.setFont(new Font("Calibri", Font.PLAIN, 20));
		tville.setColumns(10);
		tville.setBounds(209, 473, 288, 44);
		add(tville);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(543, 477, 135, 42);
		lblEmail.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblEmail);
		
		TextField temail = new TextField();
		temail.setBounds(708, 475, 288, 44);
		temail.setFont(new Font("Calibri", Font.PLAIN, 20));
		temail.setColumns(10);
		temail.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(temail);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setBounds(543, 286, 135, 42);
		lblSexe.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblSexe);
		
		TextField tsexe = new TextField();
		tsexe.setBounds(708, 284, 288, 44);
		tsexe.setFont(new Font("Calibri", Font.PLAIN, 20));
		tsexe.setColumns(10);
		add(tsexe);
		
		JLabel lblTel = new JLabel("Téléphone");
		lblTel.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTel.setBounds(543, 383, 135, 42);
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
		ttel.setBounds(708, 381, 288, 44);
		add(ttel);
		
		JLabel lblPays = new JLabel("<html>Pays de<br>résidence<html>");
		lblPays.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPays.setBounds(44, 556, 135, 79);
		add(lblPays);
		
		TextField tpays = new TextField();
		tpays.setFont(new Font("Calibri", Font.PLAIN, 20));
		tpays.setColumns(10);
		tpays.setBounds(209, 571, 288, 44);
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
				
				Client c = new Client(nom,prenom,age,sexe,adresse,ville,tel,email,pays);
				new ClientDAO().save(c);
				JOptionPane.showMessageDialog(null, "Client ajouté.");
				Home.replace(new ClientList());
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
