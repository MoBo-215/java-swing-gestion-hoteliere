package entites;

import java.util.Objects;

public class Hotel {
	private int id;
	private String nom;
	private String adresse;
	private String ville;
	private String description;
	private String dispo_parking_gratuit;
	private String presence_wifi_gratuit;
	private String horaire_arrivee;
	private String horaire_depart;
	private String presence_piscine;
	private String dispo_navette_aeroport;
	private String possibilite_animaux_compagnie;
	private int categorie_etoiles;
	private int id_societe;
	
	public Hotel() {
		
	}
	public Hotel(int id, String nom, String adresse, String ville, String description, String dispo_parking_gratuit, String presence_wifi_gratuit, String horaire_arrivee, String horaire_depart, String presence_piscine, String dispo_navette_aeroport, String possiblite_animaux_compagnie,int categorie_etoiles, int id_societe) {
		setId(id);
		setNom(nom);
		setAdresse(adresse);
		setVille(ville);
		setDescription(description);
		setDispo_parking_gratuit(dispo_parking_gratuit);
		setPresence_wifi_gratuit(presence_wifi_gratuit);
		setHoraire_arrivee(horaire_arrivee);
		setHoraire_depart(horaire_depart);
		setPresence_piscine(presence_piscine);
		setDispo_navette_aeroport(dispo_navette_aeroport);
		setPossibilite_animaux_compagnie(possibilite_animaux_compagnie);
		setCategorie_etoiles(categorie_etoiles);
		setId_societe(id_societe);
	}
	public Hotel(String nom, String adresse, String ville, String description, String dispo_parking_gratuit, String presence_wifi_gratuit, String horaire_arrivee, String horaire_depart, String presence_piscine, String dispo_navette_aeroport, String possiblite_animaux_compagnie,int categorie_etoiles, int id_societe) {
		setNom(nom);
		setAdresse(adresse);
		setVille(ville);
		setDescription(description);
		setDispo_parking_gratuit(dispo_parking_gratuit);
		setPresence_wifi_gratuit(presence_wifi_gratuit);
		setHoraire_arrivee(horaire_arrivee);
		setHoraire_depart(horaire_depart);
		setPresence_piscine(presence_piscine);
		setDispo_navette_aeroport(dispo_navette_aeroport);
		setPossibilite_animaux_compagnie(possibilite_animaux_compagnie);
		setCategorie_etoiles(categorie_etoiles);
		setId_societe(id_societe);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDispo_parking_gratuit() {
		return dispo_parking_gratuit;
	}
	public void setDispo_parking_gratuit(String dispo_parking_gratuit) {
		this.dispo_parking_gratuit = dispo_parking_gratuit;
	}
	public String getPresence_wifi_gratuit() {
		return presence_wifi_gratuit;
	}
	public void setPresence_wifi_gratuit(String presence_wifi_gratuit) {
		this.presence_wifi_gratuit = presence_wifi_gratuit;
	}
	public String getHoraire_arrivee() {
		return horaire_arrivee;
	}
	public void setHoraire_arrivee(String horaire_arrivee) {
		this.horaire_arrivee = horaire_arrivee;
	}
	public String getHoraire_depart() {
		return horaire_depart;
	}
	public void setHoraire_depart(String horaire_depart) {
		this.horaire_depart = horaire_depart;
	}
	public String getPresence_piscine() {
		return presence_piscine;
	}
	public void setPresence_piscine(String presence_piscine) {
		this.presence_piscine = presence_piscine;
	}
	public String getDispo_navette_aeroport() {
		return dispo_navette_aeroport;
	}
	public void setDispo_navette_aeroport(String dispo_navette_aeroport) {
		this.dispo_navette_aeroport = dispo_navette_aeroport;
	}
	public String getPossibilite_animaux_compagnie() {
		return possibilite_animaux_compagnie;
	}
	public void setPossibilite_animaux_compagnie(String possibilite_animaux_compagnie) {
		this.possibilite_animaux_compagnie = possibilite_animaux_compagnie;
	}
	public int getCategorie_etoiles() {
		return categorie_etoiles;
	}
	public void setCategorie_etoiles(int categorie_etoiles) {
		this.categorie_etoiles = categorie_etoiles;
	}
	public int getId_societe() {
		return id_societe;
	}
	public void setId_societe(int id_societe) {
		this.id_societe = id_societe;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	@Override
	public int hashCode() {
		return Objects.hash(adresse, categorie_etoiles, description, dispo_navette_aeroport, dispo_parking_gratuit,
				horaire_arrivee, horaire_depart, id, id_societe, nom, possibilite_animaux_compagnie, presence_piscine,
				presence_wifi_gratuit, ville);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		return Objects.equals(adresse, other.adresse) && categorie_etoiles == other.categorie_etoiles
				&& Objects.equals(description, other.description)
				&& Objects.equals(dispo_navette_aeroport, other.dispo_navette_aeroport)
				&& Objects.equals(dispo_parking_gratuit, other.dispo_parking_gratuit)
				&& Objects.equals(horaire_arrivee, other.horaire_arrivee)
				&& Objects.equals(horaire_depart, other.horaire_depart) && id == other.id
				&& id_societe == other.id_societe && Objects.equals(nom, other.nom)
				&& Objects.equals(possibilite_animaux_compagnie, other.possibilite_animaux_compagnie)
				&& Objects.equals(presence_piscine, other.presence_piscine)
				&& Objects.equals(presence_wifi_gratuit, other.presence_wifi_gratuit)
				&& Objects.equals(ville, other.ville);
	}
}
