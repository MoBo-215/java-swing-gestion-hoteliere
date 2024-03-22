package vues;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.TextField;
import dao.SocieteDAO;
import entites.Societe;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class SocieteAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TextField tsiret;
	private TextField tnom;
	private TextField tadresse;

	private ImageIcon background;
	private JLabel bgLabel;

	/**
	 * Create the panel.
	 */
	public SocieteAdd() {
		setLayout(null);

		JLabel lblSocieteAdd = new JLabel("Ajouter une société");
		lblSocieteAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblSocieteAdd.setFont(new Font("Calibri", Font.BOLD, 40));
		lblSocieteAdd.setBounds(0, 100, 1040, 51);
		add(lblSocieteAdd);
		
		JLabel lblSiret = new JLabel("N° SIRET");
		lblSiret.setFont(new Font("Calibri", Font.BOLD, 25));
		lblSiret.setBounds(226, 227, 135, 42);
		add(lblSiret);
		
		tsiret = new TextField();
		tsiret.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tsiret.setBorder(new EmptyBorder(5, 5, 5, 5));
		tsiret.setFont(new Font("Calibri", Font.PLAIN, 20));
		tsiret.setColumns(10);
		tsiret.setBounds(391, 225, 402, 44);
		add(tsiret);
				
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNom.setBounds(226, 319, 135, 42);
		add(lblNom);
		
		tnom = new TextField();
		tnom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnom.setColumns(10);
		tnom.setBounds(391, 317, 402, 44);
		add(tnom);
		
		tadresse = new TextField();
		tadresse.setFont(new Font("Calibri", Font.PLAIN, 20));
		tadresse.setColumns(10);
		tadresse.setBounds(391, 412, 402, 44);
		add(tadresse);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAdresse.setBounds(226, 414, 135, 42);
		add(lblAdresse);
		
		Button btnAjouter = new Button("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String siret = tsiret.getText();
				String nom = tnom.getText();
				String adresse = tadresse.getText();
				
				if(siret.length()!=14) {
					JOptionPane.showMessageDialog(null, "SIRET : 14 chiffres.","ERREUR SOCIETE", JOptionPane.ERROR_MESSAGE);
				}else {
					Societe s = new Societe(siret,nom,adresse);
					new SocieteDAO().save(s);
					JOptionPane.showMessageDialog(null, "Société ajoutée.");
					Home.replace(new SocieteList());	
				}
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 35));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(391, 521, 243, 59);
		add(btnAjouter);
		
		JPanel pback = new JPanel();
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new SocieteList());
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
		bgLabel.setSize(1040, 750);
		add(bgLabel);
	}
}
