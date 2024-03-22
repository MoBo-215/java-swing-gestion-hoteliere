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

public class ClientEdit extends JPanel {

	private static final long serialVersionUID = 1L;

	private Client client;
	
	private ImageIcon background;
	private JLabel bgLabel;
	/**
	 * Create the panel.
	 */
	public ClientEdit(Client c) {
		this.client=c;
		
		setLayout(null);

		JLabel lblClientEdit = new JLabel("Modifier un client");
		lblClientEdit.setBounds(0, 100, 1040, 51);
		lblClientEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientEdit.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblClientEdit);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNom.setBounds(48, 172, 135, 42);
		add(lblNom);
		
		TextField tnom = new TextField();
		tnom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnom.setColumns(10);
		tnom.setBorder(new EmptyBorder(5, 5, 5, 5));
		tnom.setBounds(213, 170, 288, 44);
		add(tnom);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPrenom.setBounds(48, 264, 135, 42);
		add(lblPrenom);
		
		TextField tprenom = new TextField();
		tprenom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tprenom.setColumns(10);
		tprenom.setBounds(213, 262, 288, 44);
		add(tprenom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAdresse.setBounds(48, 359, 155, 42);
		add(lblAdresse);
		
		TextField tadresse = new TextField();
		tadresse.setFont(new Font("Calibri", Font.PLAIN, 20));
		tadresse.setColumns(10);
		tadresse.setBounds(213, 357, 288, 44);
		add(tadresse);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAge.setBounds(547, 172, 155, 42);
		add(lblAge);
		
		TextField tage = new TextField();
		tage.setFont(new Font("Calibri", Font.PLAIN, 20));
		tage.setColumns(10);
		tage.setBounds(712, 170, 288, 44);
		add(tage);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setFont(new Font("Calibri", Font.BOLD, 25));
		lblVille.setBounds(48, 453, 155, 42);
		add(lblVille);
		
		TextField tville = new TextField();
		tville.setFont(new Font("Calibri", Font.PLAIN, 20));
		tville.setColumns(10);
		tville.setBounds(213, 451, 288, 44);
		add(tville);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri", Font.BOLD, 25));
		lblEmail.setBounds(547, 455, 135, 42);
		add(lblEmail);
		
		TextField temail = new TextField();
		temail.setFont(new Font("Calibri", Font.PLAIN, 20));
		temail.setColumns(10);
		temail.setBorder(new EmptyBorder(5, 5, 5, 5));
		temail.setBounds(712, 453, 288, 44);
		add(temail);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setFont(new Font("Calibri", Font.BOLD, 25));
		lblSexe.setBounds(547, 264, 135, 42);
		add(lblSexe);
		
		TextField tsexe = new TextField();
		tsexe.setFont(new Font("Calibri", Font.PLAIN, 20));
		tsexe.setColumns(10);
		tsexe.setBounds(712, 262, 288, 44);
		add(tsexe);
		
		JLabel lblTel = new JLabel("Téléphone");
		lblTel.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTel.setBounds(547, 361, 135, 42);
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
		ttel.setBounds(712, 359, 288, 44);
		add(ttel);
		
		JLabel lblPays = new JLabel("<html>Pays de<br>résidence<html>");
		lblPays.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPays.setBounds(48, 534, 135, 79);
		add(lblPays);
		
		TextField tpays = new TextField();
		tpays.setFont(new Font("Calibri", Font.PLAIN, 20));
		tpays.setColumns(10);
		tpays.setBounds(213, 549, 288, 44);
		add(tpays);
		
		Button btnEnregistrer = new Button("Enregistrer");
		btnEnregistrer.setText("Enregistrer");
		btnEnregistrer.setBounds(402, 654, 243, 59);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.setNom(tnom.getText());
				client.setPrenom(tprenom.getText());
				client.setAge(Integer.parseInt(tage.getText()));
				client.setSexe(tsexe.getText());
				client.setAdresse(tadresse.getText());
				client.setVille(tville.getText());
				client.setNum_tel(ttel.getText());
				client.setEmail(temail.getText());
				client.setPays_residence(tpays.getText());
				
				new ClientDAO().save(client);
				JOptionPane.showMessageDialog(null, "Client modifié.");
				Home.replace(new ClientList());
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Calibri", Font.BOLD, 35));
		btnEnregistrer.setBackground(Color.BLACK);
		add(btnEnregistrer);
		
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
