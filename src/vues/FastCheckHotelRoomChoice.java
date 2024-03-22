package vues;

import java.util.ArrayList;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import components.Panel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import dao.ChambreDAO;
import dao.HotelDAO;
import dao.ReservationDAO;
import entites.Chambre;
import entites.Database;
import components.Button;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class FastCheckHotelRoomChoice extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon background;
	private JLabel bgLabel;
	private JTable table;
	
	// Instanciation des attributs transmis par la page HomePanel
	private String ville;
	private Date datearrivee;
	private Date datedepart;

	// Récupération des données saisies dans les champs de recherche de HomePanel
	// grace au constructeur et ses paramètres
	public FastCheckHotelRoomChoice(String v, Date da, Date dd) {
		// Instanciation de la donnée attendue dans chaque paramètre
		this.ville=v;
		this.datearrivee=da;
		this.datedepart=dd;
		
		setLayout(null);
		
		Panel pfastcheck = new Panel();
		pfastcheck.setLayout(null);
		pfastcheck.setBackground(Color.BLACK);
		pfastcheck.setBounds(29, 135, 976, 583);
		add(pfastcheck);
		
		// Connexion à la base de données
		Database.connect();
		// Utilisation de la méthode DAO qui permet d'afficher le nombre de chambres disponibles dans une ville entre 2 dates
		JLabel lblVille = new JLabel(v+" : "+new ChambreDAO().nbChambresDispoByVilleAndDates(v,da,dd)+" chambre(s) trouvée(s)");
		lblVille.setBounds(29, 81, 976, 44);
		add(lblVille);
		lblVille.setForeground(new Color(0, 0, 0));
		lblVille.setFont(new Font("Calibri", Font.BOLD, 35));
		lblVille.setBackground(new Color(255, 237, 160));
		
		// Instanciation d'une arraylist de type Chambre qui contiendra les résultats
		// de la méthode DAO qui recherche les chambres disponibles dans une ville entre 2 dates
		ArrayList<Chambre> chambres = new ChambreDAO().findChambresDispoByVilleAndDates(v,da,dd);
		// Instanciation d'un tableau de chaines de caractère qui représenteront les entêtes de colonnes
		String columns[] = {"ID","HOTEL - CHAMBRE","DESCRIPTION HOTEL"};
		// Instanciation d'un tableau à 2 dimensions qui permettra de placer dans la table les données récupérées par la méthode ci-dessus
		String data[][] = new String[chambres.size()][columns.length];
		int i=0;
		// Initialisation des variables qui permettront le calcul du nombre de jours
		// du séjour afin d'obtenir le prix total à afficher
		int nbJoursSejour=0;
		double totalR=0;
		for(Chambre c: chambres) {
			data[i][0]=c.getId()+"";
			// Calcul effectué avec une méthode qui nécessite en paramètre les dates de départ et d'arrivée
			nbJoursSejour = Home.calculNbJoursReservation(dd,da);
			totalR=nbJoursSejour*c.getPrix_nuit();
			// Affichage des données du tableau dans la table 
			data[i][1]="<html>"+new HotelDAO().getById(c.getId_hotel()).getNom()+"<br>Chambre "+c.getNumero()+"<br>"+c.getNb_lits_simples()+" lits simples - "+c.getNb_lits_doubles()+" lits doubles.<br>Séjour : "+totalR+"0€</html>";
			data[i][2]="<html>"+new HotelDAO().getById(c.getId_hotel()).getDescription()+"</html>";
			i++;
		}
		// Instanciation d'un model par défaut pour la table
		DefaultTableModel model = new DefaultTableModel(data,columns);

		// Instanciation d'une table à laquelle on passe en paramètre le model crée ci-dessus
		table = new JTable(model);
		// Définition de la taille des colonnes de la table
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(350);
		table.getColumnModel().getColumn(2).setPreferredWidth(550);
		// Code qui permet de cacher la colonne "ID" car
		// non nécessaire dans l'affichage des résultats des chambres
		table.removeColumn(table.getColumn("ID"));
		// Bloque la possibilité de modifier les données affichées dans la table
		table.setDefaultEditor(Object.class, null);
		
		table.setTableHeader(null);
		table.setSelectionForeground(new Color(0, 0, 0));
		table.setSelectionBackground(new Color(252, 216, 73));
		table.setShowGrid(false);
		table.setGridColor(new Color(0, 0, 0));
		table.setRowMargin(10);
		table.setRowHeight(150);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(0, 0, 0));
		table.setFont(new Font("Calibri", Font.PLAIN, 25));
		table.setBounds(27, 34, 920, 525);
		pfastcheck.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(0, 0, 0));
		scrollPane.getViewport().setBackground(Color.BLACK);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(27, 34, 927, 466);
		
		// Design de la ScrollBar
		// Background noir
		scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			// ScrollBar jaune
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(252, 216, 73);
		    }
		    // Instanciation du bouton flèche bas
		    @Override
	        protected JButton createDecreaseButton(int orientation) {
		    	// retourne un bouton avec toutes les dimensions à zéro
		    	// ce qui permet d'avoir une scrollbar sans boutons
	            return createZeroButton();
	        }

		    // Idem pour le bouton flèche haut
	        @Override    
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        // Instanciation du bouton aux dimensions à zéro
	        private JButton createZeroButton() {
	            JButton jbutton = new JButton();
	            jbutton.setPreferredSize(new Dimension(0, 0));
	            jbutton.setMinimumSize(new Dimension(0, 0));
	            jbutton.setMaximumSize(new Dimension(0, 0));
	            return jbutton;
	        }
		});
		pfastcheck.add(scrollPane);
		
		// Une fois la chambre désirée choisie, l'user clique sur le bouton "GO !"
		Button btnGo = new Button("GO !");
		// On lie au bouton un écouteur d'événement qui permet de déclencher
		// une série d'actions lorsque l'on clique sur le bouton
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verif si une chambre a bien été sélectionnée
				if(table.getSelectedRow()!=-1) {
					// Recup l'index de la ligne
					int rowIndex = table.getSelectedRow();
					// Recup la valeur de cette ligne (qui contient l'id de la chambre sélectionnée)
					int selectedId = Integer.parseInt(data[rowIndex][0]);
					// Recup de l'objet Chambre via la méthode DAO getById
					Chambre ch = new ChambreDAO().getById(selectedId);
					// Ouverture de la page d'ajout de réservation avec
					// en paramètres la chambre sélectionnée et les dates du séjour
					Home.replace(new FastCheckResaAdd(ch,da,dd));
				// Message d'erreur si aucune chambre n'a été choisie par l'user
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une chambre à réserver.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGo.setText("GO !");
		btnGo.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnGo.setBackground(new Color(252, 216, 73));
		btnGo.setBounds(801, 513, 153, 48);
		pfastcheck.add(btnGo);
		
		// Instanciation d'un panel qui permet le retour à la page précédente
		// Existe sur toutes les pages de l'appli sauf la page d'accueil
		// Seule la page de redirection change en fonction de la page sur laquelle on est
		JPanel pback = new JPanel();
		pback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.replace(new HomePanel());
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
		bgLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgLabel.setSize(1040, 750);
		add(bgLabel);
		
	}
}
