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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.TextField;
import dao.ChambreDAO;
import dao.HotelDAO;
import entites.Chambre;
import entites.Database;
import entites.Hotel;

public class ChambreEdit extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;
	
	private Chambre chambre;
	
	/**
	 * Create the panel.
	 */
	public ChambreEdit(Chambre c) {
		this.chambre=c;
		
		setLayout(null);

		JLabel lblChambreEdit = new JLabel("Modifier une chambre");
		lblChambreEdit.setBounds(0, 100, 1040, 51);
		lblChambreEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblChambreEdit.setFont(new Font("Calibri", Font.BOLD, 40));
		add(lblChambreEdit);
		
		JLabel lblHotel = new JLabel("Hôtel");
		lblHotel.setBounds(42, 190, 135, 42);
		lblHotel.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblHotel);
		
		JComboBox cbHotel = new JComboBox();
		cbHotel.setBounds(207, 188, 288, 44);
		cbHotel.setBorder(new EmptyBorder(5, 5, 5, 5));
		cbHotel.setFont(new Font("Calibri", Font.PLAIN, 20));
		add(cbHotel);
				
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(42, 259, 135, 42);
		lblNumero.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblNumero);
		
		TextField tnumero = new TextField();
		tnumero.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tnumero.setBounds(207, 257, 288, 44);
		tnumero.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnumero.setColumns(10);
		add(tnumero);
		
		JLabel lblSuperficie = new JLabel("Superficie");
		lblSuperficie.setBounds(42, 327, 155, 44);
		lblSuperficie.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblSuperficie);
		
		TextField tsuperficie = new TextField();
		tsuperficie.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tsuperficie.setBounds(207, 327, 288, 44);
		tsuperficie.setFont(new Font("Calibri", Font.PLAIN, 20));
		tsuperficie.setColumns(10);
		add(tsuperficie);
		
		JLabel lblLitsSimples = new JLabel("Nb lits simples");
		lblLitsSimples.setFont(new Font("Calibri", Font.BOLD, 25));
		lblLitsSimples.setBounds(42, 403, 155, 42);
		add(lblLitsSimples);
		
		TextField tlitssimples = new TextField();
		tlitssimples.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tlitssimples.setFont(new Font("Calibri", Font.PLAIN, 20));
		tlitssimples.setColumns(10);
		tlitssimples.setBounds(207, 401, 288, 44);
		add(tlitssimples);
		
		JLabel lblLitsDoubles = new JLabel("Nb lits doubles");
		lblLitsDoubles.setFont(new Font("Calibri", Font.BOLD, 25));
		lblLitsDoubles.setBounds(42, 471, 155, 42);
		add(lblLitsDoubles);

		TextField tlitsdoubles = new TextField();
		tlitsdoubles.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tlitsdoubles.setFont(new Font("Calibri", Font.PLAIN, 20));
		tlitsdoubles.setBackground(new Color(255, 255, 255));
		tlitsdoubles.setBounds(207, 471, 288, 42);
		add(tlitsdoubles);

		JLabel lblSdb = new JLabel("SDB");
		lblSdb.setBounds(42, 537, 135, 42);
		lblSdb.setFont(new Font("Calibri", Font.BOLD, 25));
		add(lblSdb);

		JComboBox cbSdb = new JComboBox();
		cbSdb.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbSdb.setBackground(new Color(255, 255, 255));
		cbSdb.setBounds(207, 537, 288, 42);
		add(cbSdb);
		
		JLabel lblEcranPlat = new JLabel("Ecran plat");
		lblEcranPlat.setFont(new Font("Calibri", Font.BOLD, 25));
		lblEcranPlat.setBounds(548, 175, 135, 59);
		add(lblEcranPlat);
		
		JComboBox cbEcranPlat = new JComboBox();
		cbEcranPlat.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbEcranPlat.setBorder(new EmptyBorder(5, 5, 5, 5));
		cbEcranPlat.setBounds(713, 190, 288, 44);
		add(cbEcranPlat);
		
		JLabel lblBalcon = new JLabel("Balcon");
		lblBalcon.setFont(new Font("Calibri", Font.BOLD, 25));
		lblBalcon.setBounds(548, 252, 135, 59);
		add(lblBalcon);
		
		JComboBox cbBalcon = new JComboBox();
		cbBalcon.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbBalcon.setBounds(713, 259, 288, 44);
		add(cbBalcon);
		
		JLabel lblRefrigirateur = new JLabel("Réfrigirateur");
		lblRefrigirateur.setFont(new Font("Calibri", Font.BOLD, 25));
		lblRefrigirateur.setBounds(548, 329, 155, 44);
		add(lblRefrigirateur);
		
		JComboBox cbRefrigirateur = new JComboBox();
		cbRefrigirateur.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbRefrigirateur.setBackground(new Color(255, 255, 255));
		cbRefrigirateur.setBounds(713, 327, 288, 42);
		add(cbRefrigirateur);
		
		JLabel lblBaignoire = new JLabel("Baignoire");
		lblBaignoire.setFont(new Font("Calibri", Font.BOLD, 25));
		lblBaignoire.setBounds(548, 396, 155, 59);
		add(lblBaignoire);
		
		JComboBox cbBaignoire = new JComboBox();
		cbBaignoire.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbBaignoire.setBackground(new Color(255, 255, 255));
		cbBaignoire.setBounds(713, 403, 288, 42);
		add(cbBaignoire);
		
		JLabel lblInsono = new JLabel("Insonorisation");
		lblInsono.setFont(new Font("Calibri", Font.BOLD, 25));
		lblInsono.setBounds(548, 465, 155, 64);
		add(lblInsono);
		
		JComboBox cbInsono = new JComboBox();
		cbInsono.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbInsono.setBackground(new Color(255, 255, 255));
		cbInsono.setBounds(713, 471, 288, 42);
		add(cbInsono);

		JLabel lblPrixNuit = new JLabel("Prix / nuit");
		lblPrixNuit.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPrixNuit.setBounds(548, 539, 135, 42);
		add(lblPrixNuit);
		
		TextField tprixnuit = new TextField();
		tprixnuit.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tprixnuit.setFont(new Font("Calibri", Font.PLAIN, 20));
		tprixnuit.setBackground(new Color(255, 255, 255));
		tprixnuit.setBounds(713, 537, 288, 42);
		add(tprixnuit);

		Button btnEnregistrer = new Button("Enregistrer");
		btnEnregistrer.setText("Enregistrer");
		btnEnregistrer.setBounds(401, 643, 243, 59);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel h = (Hotel)cbHotel.getSelectedItem();
				int id_hotel = h.getId();
				int numero = Integer.parseInt(tnumero.getText());
				int superficie = Integer.parseInt(tsuperficie.getText());
				int nb_lits_simples = Integer.parseInt(tlitssimples.getText());
				int nb_lits_doubles = Integer.parseInt(tlitsdoubles.getText());
				String sdb = cbSdb.getSelectedItem().toString();
				String ecranplat = cbEcranPlat.getSelectedItem().toString();
				String balcon = cbBalcon.getSelectedItem().toString();
				String refrigirateur = cbRefrigirateur.getSelectedItem().toString();
				String baignoire = cbBaignoire.getSelectedItem().toString();
				String insono = cbInsono.getSelectedItem().toString();
				float prixnuit = Float.parseFloat(tprixnuit.getText());
				
				chambre.setId_hotel(id_hotel);
				chambre.setNumero(numero);
				chambre.setSuperficie(superficie);
				chambre.setNb_lits_simples(nb_lits_simples);
				chambre.setNb_lits_doubles(nb_lits_doubles);
				chambre.setPresence_sdb_privative(sdb);
				chambre.setPresence_ecran_plat(ecranplat);
				chambre.setPresence_balcon(balcon);
				chambre.setPresence_refrigirateur(refrigirateur);
				chambre.setPresence_baignoire(baignoire);
				chambre.setPresence_insonorisation(insono);
				chambre.setPrix_nuit(prixnuit);
				
				new ChambreDAO().save(c);
				JOptionPane.showMessageDialog(null, "Chambre modifiée.");
				Home.replace(new ChambreList());
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Calibri", Font.BOLD, 35));
		btnEnregistrer.setBackground(Color.BLACK);
		add(btnEnregistrer);
		
		String rep[] = {"OUI","NON"};
		
		Database.connect();
		for(Hotel h: new HotelDAO().getAll()) {
			cbHotel.addItem(h);
		}
		for(String r: rep) {
			cbSdb.addItem(r);
		}
		for(String r: rep) {
			cbEcranPlat.addItem(r);
		}
		for(String r: rep) {
			cbBalcon.addItem(r);
		}
		for(String r: rep) {
			cbRefrigirateur.addItem(r);
		}
		for(String r: rep) {
			cbBaignoire.addItem(r);
		}
		for(String r: rep) {
			cbInsono.addItem(r);
		}
		
		JPanel pback = new JPanel();
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new ChambreList());
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
		
		Hotel hotel = new HotelDAO().getById(c.getId_hotel());
		cbHotel.setSelectedItem(hotel);
		tnumero.setText(c.getNumero()+"");
		tsuperficie.setText(c.getSuperficie()+"");
		tlitssimples.setText(c.getNb_lits_simples()+"");
		tlitsdoubles.setText(c.getNb_lits_doubles()+"");
		cbSdb.setSelectedItem(c.getPresence_sdb_privative());
		cbEcranPlat.setSelectedItem(c.getPresence_ecran_plat());
		cbBalcon.setSelectedItem(c.getPresence_balcon());
		cbRefrigirateur.setSelectedItem(c.getPresence_refrigirateur());
		cbBaignoire.setSelectedItem(c.getPresence_baignoire());
		cbInsono.setSelectedItem(c.getPresence_insonorisation());
		tprixnuit.setText(c.getPrix_nuit()+"");

		background = new ImageIcon(this.getClass().getResource("/img/background2.png"));
		bgLabel = new JLabel(background);
		bgLabel.setBounds(0, 0, 1040, 750);
		add(bgLabel);

	}

}
