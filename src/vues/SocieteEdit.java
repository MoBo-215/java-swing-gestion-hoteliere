package vues;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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

public class SocieteEdit extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Societe societe;
	private TextField tsiret;
	private TextField tnom;
	private TextField tadresse;
	
	private ImageIcon background;
	private JLabel bgLabel;

	/**
	 * Create the panel.
	 */
	public SocieteEdit(Societe s) {
		this.societe=s;
		
		setLayout(null);

		JLabel lblSocieteEdit = new JLabel("Modifier une société");
		lblSocieteEdit.setBounds(0, 100, 1040, 51);
		lblSocieteEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSocieteEdit.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblSocieteEdit);
		
		JLabel lblSiret = new JLabel("N° SIRET");
		lblSiret.setBounds(226, 227, 135, 42);
		lblSiret.setFont(new Font("Calibri", Font.BOLD, 25));
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
		tsiret.setBounds(391, 225, 402, 44);
		tsiret.setBorder(new EmptyBorder(5, 5, 5, 5));
		tsiret.setFont(new Font("Calibri", Font.PLAIN, 20));
		tsiret.setColumns(10);
		add(tsiret);
				
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(226, 319, 135, 42);
		lblNom.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblNom);
		
		tnom = new TextField();
		tnom.setBounds(391, 317, 402, 44);
		tnom.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnom.setColumns(10);
		add(tnom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(226, 414, 135, 42);
		lblAdresse.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblAdresse);
		
		tadresse = new TextField();
		tadresse.setBounds(391, 412, 402, 44);
		tadresse.setFont(new Font("Calibri", Font.PLAIN, 20));
		tadresse.setColumns(10);
		add(tadresse);
		
		Button btnEnregistrer = new Button("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				societe.setNum_siret(tsiret.getText());
				societe.setNom(tnom.getText());
				societe.setAdresse(tadresse.getText());
				
				if(tsiret.getText().length()!=14) {
					JOptionPane.showMessageDialog(null, "SIRET : 14 chiffres.","ERREUR SOCIETE", JOptionPane.ERROR_MESSAGE);
				}else {
					new SocieteDAO().save(societe);
					JOptionPane.showMessageDialog(null, "Société modifiée.");
					Home.replace(new SocieteList());					
				}
			}
		});
		btnEnregistrer.setBounds(391, 521, 243, 59);
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Calibri", Font.BOLD, 35));
		btnEnregistrer.setBackground(Color.BLACK);
		add(btnEnregistrer);
		
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
		
		tsiret.setText(societe.getNum_siret());
		tnom.setText(societe.getNom());
		tadresse.setText(societe.getAdresse());
		
		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, 1040, 750);
		add(bgLabel);
	}

}
