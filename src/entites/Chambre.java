package entites;

import java.util.Objects;

public class Chambre {
	private int id;
	private int numero;
	private int nb_lits_simples;
	private int nb_lits_doubles;
	private int superficie;
	private String presence_sdb_privative;
	private String presence_ecran_plat;
	private String presence_balcon;
	private String presence_refrigirateur;
	private String presence_baignoire;
	private String presence_insonorisation;
	private double prix_nuit;
	private int id_hotel;
	
	public Chambre() {
		
	}
	public Chambre(int id, int numero, int nb_lits_simples, int nb_lits_doubles, String presence_sdb_privative, String presence_ecran_plat, String presence_balcon, String presence_refrigirateur, String presence_baignoire, String presence_insonorisation, double prix_nuit, int id_hotel) {
		setId(id);
		setNumero(numero);
		setNb_lits_simples(nb_lits_simples);
		setNb_lits_doubles(nb_lits_doubles);
		setSuperficie(superficie);
		setPresence_sdb_privative(presence_sdb_privative);
		setPresence_ecran_plat(presence_ecran_plat);
		setPresence_balcon(presence_balcon);
		setPresence_refrigirateur(presence_refrigirateur);
		setPresence_baignoire(presence_baignoire);
		setPresence_insonorisation(presence_insonorisation);
		setPrix_nuit(prix_nuit);
		setId_hotel(id_hotel);
	}
	public Chambre(int numero, int nb_lits_simples, int nb_lits_doubles, String presence_sdb_privative, String presence_ecran_plat, String presence_balcon, String presence_refrigirateur, String presence_baignoire, String presence_insonorisation, double prix_nuit, int id_hotel) {
		setNumero(numero);
		setNb_lits_simples(nb_lits_simples);
		setNb_lits_doubles(nb_lits_doubles);
		setSuperficie(superficie);
		setPresence_sdb_privative(presence_sdb_privative);
		setPresence_ecran_plat(presence_ecran_plat);
		setPresence_balcon(presence_balcon);
		setPresence_refrigirateur(presence_refrigirateur);
		setPresence_baignoire(presence_baignoire);
		setPresence_insonorisation(presence_insonorisation);
		setPrix_nuit(prix_nuit);
		setId_hotel(id_hotel);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNb_lits_simples() {
		return nb_lits_simples;
	}
	public void setNb_lits_simples(int nb_lits_simples) {
		this.nb_lits_simples = nb_lits_simples;
	}
	public int getNb_lits_doubles() {
		return nb_lits_doubles;
	}
	public void setNb_lits_doubles(int nb_lits_doubles) {
		this.nb_lits_doubles = nb_lits_doubles;
	}
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	public String getPresence_sdb_privative() {
		return presence_sdb_privative;
	}
	public void setPresence_sdb_privative(String presence_sdb_privative) {
		this.presence_sdb_privative = presence_sdb_privative;
	}
	public String getPresence_ecran_plat() {
		return presence_ecran_plat;
	}
	public void setPresence_ecran_plat(String presence_ecran_plat) {
		this.presence_ecran_plat = presence_ecran_plat;
	}
	public String getPresence_balcon() {
		return presence_balcon;
	}
	public void setPresence_balcon(String presence_balcon) {
		this.presence_balcon = presence_balcon;
	}
	public String getPresence_refrigirateur() {
		return presence_refrigirateur;
	}
	public void setPresence_refrigirateur(String presence_refrigirateur) {
		this.presence_refrigirateur = presence_refrigirateur;
	}
	public String getPresence_baignoire() {
		return presence_baignoire;
	}
	public void setPresence_baignoire(String presence_baignoire) {
		this.presence_baignoire = presence_baignoire;
	}
	public String getPresence_insonorisation() {
		return presence_insonorisation;
	}
	public void setPresence_insonorisation(String presence_insonorisation) {
		this.presence_insonorisation = presence_insonorisation;
	}
	public double getPrix_nuit() {
		return prix_nuit;
	}
	public void setPrix_nuit(double prix_nuit) {
		this.prix_nuit = prix_nuit;
	}
	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	
	@Override
	public String toString() {
		return numero+"";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, id_hotel, nb_lits_doubles, nb_lits_simples, numero, presence_baignoire, presence_balcon,
				presence_ecran_plat, presence_insonorisation, presence_refrigirateur, presence_sdb_privative, prix_nuit,
				superficie);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chambre other = (Chambre) obj;
		return id == other.id && id_hotel == other.id_hotel && nb_lits_doubles == other.nb_lits_doubles
				&& nb_lits_simples == other.nb_lits_simples && numero == other.numero
				&& Objects.equals(presence_baignoire, other.presence_baignoire)
				&& Objects.equals(presence_balcon, other.presence_balcon)
				&& Objects.equals(presence_ecran_plat, other.presence_ecran_plat)
				&& Objects.equals(presence_insonorisation, other.presence_insonorisation)
				&& Objects.equals(presence_refrigirateur, other.presence_refrigirateur)
				&& Objects.equals(presence_sdb_privative, other.presence_sdb_privative)
				&& Double.doubleToLongBits(prix_nuit) == Double.doubleToLongBits(other.prix_nuit)
				&& superficie == other.superficie;
	}
}
