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
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
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
import dao.HotelDAO;
import dao.ReservationDAO;
import entites.Chambre;
import entites.Client;
import entites.Hotel;
import entites.Reservation;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ReservationAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JLabel bgLabel;

	/**
	 * Create the panel.
	 */
	public ReservationAdd() {
		setLayout(null);
		
		JLabel lblResaAdd = new JLabel("Ajouter une réservation");
		lblResaAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblResaAdd.setFont(new Font("Calibri", Font.BOLD, 40));
		lblResaAdd.setBounds(0, 100, 1040, 51);
		add(lblResaAdd);
		
		JLabel lblHotel = new JLabel("Hôtel");
		lblHotel.setFont(new Font("Calibri", Font.BOLD, 25));
		lblHotel.setBounds(46, 238, 135, 31);
		add(lblHotel);
				
		JComboBox cbHotel = new JComboBox();
		cbHotel.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbHotel.setBounds(211, 227, 288, 42);
		add(cbHotel);

		JLabel lblChambre = new JLabel("Chambre");
		lblChambre.setFont(new Font("Calibri", Font.BOLD, 25));
		lblChambre.setBounds(46, 319, 135, 42);
		add(lblChambre);
		
		JComboBox cbChambre = new JComboBox();
		cbChambre.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbChambre.setBounds(211, 319, 288, 42);
		add(cbChambre);
		
		cbHotel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Hotel h = (Hotel)cbHotel.getSelectedItem();
				cbChambre.removeAllItems();
				for(Chambre ch : new ChambreDAO().getAllByIdHotel(h.getId())) {
					cbChambre.addItem(ch);
				}
			}
		});

		JLabel lblNomC = new JLabel("<html>Nom<br>client</html>");
		lblNomC.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNomC.setBounds(46, 394, 135, 59);
		add(lblNomC);
		
		TextField tnomc = new TextField();
		tnomc.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnomc.setColumns(10);
		tnomc.setBounds(211, 409, 288, 44);
		add(tnomc);
		
		JLabel lblPrenomC = new JLabel("<html>Prénom<br>client</html>");
		lblPrenomC.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPrenomC.setBounds(46, 491, 135, 59);
		add(lblPrenomC);
		
		TextField tprenomc = new TextField();
		tprenomc.setFont(new Font("Calibri", Font.PLAIN, 20));
		tprenomc.setColumns(10);
		tprenomc.setBounds(211, 506, 288, 44);
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
		tnbpers.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (((c<'0') || (c>'9')) && (c!=KeyEvent.VK_BACK_SPACE)) {
	                  e.consume();
	             }
	         }
	    });
		tnbpers.setFont(new Font("Calibri", Font.PLAIN, 20));
		tnbpers.setColumns(10);
		tnbpers.setBounds(708, 414, 288, 44);
		add(tnbpers);
		
		Button btnAjouter = new Button("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chambre ch = (Chambre)cbChambre.getSelectedItem();
				int id_chambre = ch.getId();
				int nbpers = Integer.parseInt(tnbpers.getText()+"");
				Date datedebut = Home.convertStringToDate(tdatedebut.getText());
				Date datefin = Home.convertStringToDate(tdatefin.getText());

				String nom_client = tnomc.getText();
				String prenom_client = tprenomc.getText();
				Client c = new ClientDAO().getByNomPrenom(nom_client, prenom_client);
				
				if(datedebut==null || datefin==null) {
					JOptionPane.showMessageDialog(null, "<html>Saisie de date incorrecte.<br>Ex correct : 2024-01-23</html>", "ERREUR RESERVATION", JOptionPane.ERROR_MESSAGE);
				}else if(new ReservationDAO().findReservationByDateAndIdChambre(datedebut,id_chambre)!=null || new ReservationDAO().findReservationByDateAndIdChambre(datefin,id_chambre)!=null){
					JOptionPane.showMessageDialog(null, "Chambre indisponible à ces dates.", "ERREUR RESERVATION", JOptionPane.ERROR_MESSAGE);
				}else if(c==null) {
					int input = JOptionPane.showConfirmDialog(null, "<html>Client introuvable.<br>Voulez-vous ajouter un client ?</html>", "Message", JOptionPane.YES_NO_OPTION);
					if(input==0) {
						Home.replace(new ClientAdd());
					}
				}else {
					int id_client = c.getId();
					Reservation r = new Reservation(datedebut,datefin,nbpers,id_chambre,id_client);
					new ReservationDAO().save(r);
					JOptionPane.showMessageDialog(null, "Réservation ajoutée.");
					Home.replace(new HomePanel());
				}
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 35));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(400, 614, 243, 59);
		add(btnAjouter);
		
		for(Hotel h: new HotelDAO().getAll()) {
			cbHotel.addItem(h);
		}

		JPanel pback = new JPanel();
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new ReservationList());
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
