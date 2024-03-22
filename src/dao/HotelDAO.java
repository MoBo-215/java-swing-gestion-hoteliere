package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Database;
import entites.Hotel;

public class HotelDAO {
	public void save(Hotel obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE hotel SET nom=?,adresse=?,ville=?,description=?,dispo_parking_gratuit=?,presence_wifi_gratuit=?,horaire_arrivee=?,horaire_depart=?,presence_piscine=?,dispo_navette_aeroport=?,possibilite_animaux_compagnie=?,categorie_etoiles=?,id_societe=? WHERE id=?");
				ps.setString(1, obj.getNom());
				ps.setString(2, obj.getAdresse());
				ps.setString(3, obj.getVille());
				ps.setString(4, obj.getDescription());
				ps.setString(5, obj.getDispo_parking_gratuit());
				ps.setString(6, obj.getPresence_wifi_gratuit());
				ps.setString(7, obj.getHoraire_arrivee());
				ps.setString(8, obj.getHoraire_depart());
				ps.setString(9, obj.getPresence_piscine());
				ps.setString(10, obj.getDispo_navette_aeroport());
				ps.setString(11, obj.getPossibilite_animaux_compagnie());
				ps.setInt(12, obj.getCategorie_etoiles());
				ps.setInt(13, obj.getId_societe());
				ps.setInt(14, obj.getId());
				ps.executeUpdate();
				
				PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps3.executeUpdate();
			}else {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO hotel (nom,adresse,ville,description,dispo_parking_gratuit,presence_wifi_gratuit,horaire_arrivee,horaire_depart,presence_piscine,dispo_navette_aeroport,possibilite_animaux_compagnie,categorie_etoiles,id_societe) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				ps.setString(1, obj.getNom());
				ps.setString(2, obj.getAdresse());
				ps.setString(3, obj.getVille());
				ps.setString(4, obj.getDescription());
				ps.setString(5, obj.getDispo_parking_gratuit());
				ps.setString(6, obj.getPresence_wifi_gratuit());
				ps.setString(7, obj.getHoraire_arrivee());
				ps.setString(8, obj.getHoraire_depart());
				ps.setString(9, obj.getPresence_piscine());
				ps.setString(10, obj.getDispo_navette_aeroport());
				ps.setString(11, obj.getPossibilite_animaux_compagnie());
				ps.setInt(12, obj.getCategorie_etoiles());
				ps.setInt(13, obj.getId_societe());
				ps.executeUpdate();
				
				PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps3.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Hotel getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Hotel c = new Hotel();
			c.setId(resultat.getInt("id"));
			c.setNom(resultat.getString("nom"));
			c.setAdresse(resultat.getString("adresse"));
			c.setVille(resultat.getString("ville"));
			c.setDescription(resultat.getString("description"));
			c.setDispo_parking_gratuit(resultat.getString("dispo_parking_gratuit"));
			c.setPresence_wifi_gratuit(resultat.getString("presence_wifi_gratuit"));
			c.setHoraire_arrivee(resultat.getString("horaire_arrivee"));
			c.setHoraire_depart(resultat.getString("horaire_depart"));
			c.setPresence_piscine(resultat.getString("presence_piscine"));
			c.setDispo_navette_aeroport(resultat.getString("dispo_navette_aeroport"));
			c.setPossibilite_animaux_compagnie(resultat.getString("possibilite_animaux_compagnie"));
			c.setCategorie_etoiles(resultat.getInt("categorie_etoiles"));
			c.setId_societe(resultat.getInt("id_societe"));

			return c;
		}catch(Exception ex){
			return null;
		}
	}
	
	public Hotel getByIdSociete(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE id_societe=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Hotel c = new Hotel();
			c.setId(resultat.getInt("id"));
			c.setNom(resultat.getString("nom"));
			c.setAdresse(resultat.getString("adresse"));
			c.setVille(resultat.getString("ville"));
			c.setDescription(resultat.getString("description"));
			c.setDispo_parking_gratuit(resultat.getString("dispo_parking_gratuit"));
			c.setPresence_wifi_gratuit(resultat.getString("presence_wifi_gratuit"));
			c.setHoraire_arrivee(resultat.getString("horaire_arrivee"));
			c.setHoraire_depart(resultat.getString("horaire_depart"));
			c.setPresence_piscine(resultat.getString("presence_piscine"));
			c.setDispo_navette_aeroport(resultat.getString("dispo_navette_aeroport"));
			c.setPossibilite_animaux_compagnie(resultat.getString("possibilite_animaux_compagnie"));
			c.setCategorie_etoiles(resultat.getInt("categorie_etoiles"));
			c.setId_societe(resultat.getInt("id_societe"));

			return c;
		}catch(Exception ex){
			return null;
		}
	}
	
	public ArrayList<Hotel> getAll() {
		ArrayList<Hotel> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Hotel c = new Hotel();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setDescription(resultat.getString("description"));
				c.setDispo_parking_gratuit(resultat.getString("dispo_parking_gratuit"));
				c.setPresence_wifi_gratuit(resultat.getString("presence_wifi_gratuit"));
				c.setHoraire_arrivee(resultat.getString("horaire_arrivee"));
				c.setHoraire_depart(resultat.getString("horaire_depart"));
				c.setPresence_piscine(resultat.getString("presence_piscine"));
				c.setDispo_navette_aeroport(resultat.getString("dispo_navette_aeroport"));
				c.setPossibilite_animaux_compagnie(resultat.getString("possibilite_animaux_compagnie"));
				c.setCategorie_etoiles(resultat.getInt("categorie_etoiles"));
				c.setId_societe(resultat.getInt("id_societe"));
				list.add(c);
			}
			
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void deleteById(int id) {
		try {
			PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
			ps1.executeUpdate();
			
			PreparedStatement ps5 = Database.connexion.prepareStatement("DELETE FROM paiement WHERE id_reservation IN(SELECT id FROM reservation WHERE id_chambre IN(SELECT id FROM chambre WHERE id_hotel IN(SELECT id FROM hotel WHERE id=?))");
			ps5.setInt(1, id);
			ps5.executeUpdate();
			
			PreparedStatement ps4 = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id_chambre IN(SELECT id FROM chambre WHERE id_hotel IN(SELECT id FROM hotel WHERE id=?))");
			ps4.setInt(1, id);
			ps4.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("DELETE FROM chambre WHERE id_hotel=?");
			ps2.setInt(1, id);
			ps2.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM hotel WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps3.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public ArrayList<Hotel> findHotels(String mot){
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE nom LIKE ? OR adresse LIKE ? OR ville LIKE ? OR description LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ps.setString(3,"%"+mot+"%");
			ps.setString(4,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Hotel c = new Hotel();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setDescription(resultat.getString("description"));
				c.setDispo_parking_gratuit(resultat.getString("dispo_parking_gratuit"));
				c.setPresence_wifi_gratuit(resultat.getString("presence_wifi_gratuit"));
				c.setHoraire_arrivee(resultat.getString("horaire_arrivee"));
				c.setHoraire_depart(resultat.getString("horaire_depart"));
				c.setPresence_piscine(resultat.getString("presence_piscine"));
				c.setDispo_navette_aeroport(resultat.getString("dispo_navette_aeroport"));
				c.setPossibilite_animaux_compagnie(resultat.getString("possibilite_animaux_compagnie"));
				c.setCategorie_etoiles(resultat.getInt("categorie_etoiles"));
				c.setId_societe(resultat.getInt("id_societe"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Hotel> findHotelsByNom(String mot){
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE nom LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Hotel c = new Hotel();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setDescription(resultat.getString("description"));
				c.setDispo_parking_gratuit(resultat.getString("dispo_parking_gratuit"));
				c.setPresence_wifi_gratuit(resultat.getString("presence_wifi_gratuit"));
				c.setHoraire_arrivee(resultat.getString("horaire_arrivee"));
				c.setHoraire_depart(resultat.getString("horaire_depart"));
				c.setPresence_piscine(resultat.getString("presence_piscine"));
				c.setDispo_navette_aeroport(resultat.getString("dispo_navette_aeroport"));
				c.setPossibilite_animaux_compagnie(resultat.getString("possibilite_animaux_compagnie"));
				c.setCategorie_etoiles(resultat.getInt("categorie_etoiles"));
				c.setId_societe(resultat.getInt("id_societe"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Hotel> findHotelsByAdresse(String mot){
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE adresse LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Hotel c = new Hotel();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setDescription(resultat.getString("description"));
				c.setDispo_parking_gratuit(resultat.getString("dispo_parking_gratuit"));
				c.setPresence_wifi_gratuit(resultat.getString("presence_wifi_gratuit"));
				c.setHoraire_arrivee(resultat.getString("horaire_arrivee"));
				c.setHoraire_depart(resultat.getString("horaire_depart"));
				c.setPresence_piscine(resultat.getString("presence_piscine"));
				c.setDispo_navette_aeroport(resultat.getString("dispo_navette_aeroport"));
				c.setPossibilite_animaux_compagnie(resultat.getString("possibilite_animaux_compagnie"));
				c.setCategorie_etoiles(resultat.getInt("categorie_etoiles"));
				c.setId_societe(resultat.getInt("id_societe"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Hotel> findHotelsByVille(String mot){
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE ville LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Hotel c = new Hotel();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setDescription(resultat.getString("description"));
				c.setDispo_parking_gratuit(resultat.getString("dispo_parking_gratuit"));
				c.setPresence_wifi_gratuit(resultat.getString("presence_wifi_gratuit"));
				c.setHoraire_arrivee(resultat.getString("horaire_arrivee"));
				c.setHoraire_depart(resultat.getString("horaire_depart"));
				c.setPresence_piscine(resultat.getString("presence_piscine"));
				c.setDispo_navette_aeroport(resultat.getString("dispo_navette_aeroport"));
				c.setPossibilite_animaux_compagnie(resultat.getString("possibilite_animaux_compagnie"));
				c.setCategorie_etoiles(resultat.getInt("categorie_etoiles"));
				c.setId_societe(resultat.getInt("id_societe"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Hotel> findHotelsByEtoiles(int etoiles){
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel WHERE categorie_etoiles=?");
			ps.setInt(1, etoiles);
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Hotel c = new Hotel();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setDescription(resultat.getString("description"));
				c.setDispo_parking_gratuit(resultat.getString("dispo_parking_gratuit"));
				c.setPresence_wifi_gratuit(resultat.getString("presence_wifi_gratuit"));
				c.setHoraire_arrivee(resultat.getString("horaire_arrivee"));
				c.setHoraire_depart(resultat.getString("horaire_depart"));
				c.setPresence_piscine(resultat.getString("presence_piscine"));
				c.setDispo_navette_aeroport(resultat.getString("dispo_navette_aeroport"));
				c.setPossibilite_animaux_compagnie(resultat.getString("possibilite_animaux_compagnie"));
				c.setCategorie_etoiles(resultat.getInt("categorie_etoiles"));
				c.setId_societe(resultat.getInt("id_societe"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	

	public int nbHotels() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM hotel");
			ResultSet resultat = ps.executeQuery();
			
			int nb=0;
			while(resultat.next()) {
				nb++;
			}
			
			return nb;
		}catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
}
