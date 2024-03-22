package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Database;
import entites.Reservation;

public class ReservationDAO {
	public void save(Reservation obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE reservation SET date_debut=?,date_fin=?,nb_personnes=?,id_chambre=?,id_client=? WHERE id=?");
				ps.setDate(1, obj.getDate_debut());
				ps.setDate(2, obj.getDate_fin());
				ps.setInt(3, obj.getNb_personnes());
				ps.setInt(4, obj.getId_chambre());
				ps.setInt(5, obj.getId_client());
				ps.setInt(6, obj.getId());
				ps.executeUpdate();
				
				PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps3.executeUpdate();
			}else {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO reservation (date_debut,date_fin,nb_personnes,id_chambre,id_client) VALUES(?,?,?,?,?)");
				ps.setDate(1, obj.getDate_debut());
				ps.setDate(2, obj.getDate_fin());
				ps.setInt(3, obj.getNb_personnes());
				ps.setInt(4, obj.getId_chambre());
				ps.setInt(5, obj.getId_client());
				ps.executeUpdate();
				
				PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps3.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Reservation getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Reservation s = new Reservation();
			s.setId(resultat.getInt("id"));
			s.setDate_debut(resultat.getDate("date_debut"));
			s.setDate_fin(resultat.getDate("date_fin"));
			s.setNb_personnes(resultat.getInt("nb_personnes"));
			s.setId_chambre(resultat.getInt("id_chambre"));
			s.setId_client(resultat.getInt("id_client"));

			return s;
		}catch(Exception ex){
			return null;
		}
	}
	
	public Reservation getByIdChambre(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id_chambre=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Reservation s = new Reservation();
			s.setId(resultat.getInt("id"));
			s.setDate_debut(resultat.getDate("date_debut"));
			s.setDate_fin(resultat.getDate("date_fin"));
			s.setNb_personnes(resultat.getInt("nb_personnes"));
			s.setId_chambre(resultat.getInt("id_chambre"));
			s.setId_client(resultat.getInt("id_client"));

			return s;
		}catch(Exception ex){
			return null;
		}
	}
	
	public Reservation getByIdClient(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id_client=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Reservation s = new Reservation();
			s.setId(resultat.getInt("id"));
			s.setDate_debut(resultat.getDate("date_debut"));
			s.setDate_fin(resultat.getDate("date_fin"));
			s.setNb_personnes(resultat.getInt("nb_personnes"));
			s.setId_chambre(resultat.getInt("id_chambre"));
			s.setId_client(resultat.getInt("id_client"));

			return s;
		}catch(Exception ex){
			return null;
		}
	}
	
	public ArrayList<Reservation> getAll() {
		ArrayList<Reservation> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Reservation s = new Reservation();
				s.setId(resultat.getInt("id"));
				s.setDate_debut(resultat.getDate("date_debut"));
				s.setDate_fin(resultat.getDate("date_fin"));
				s.setNb_personnes(resultat.getInt("nb_personnes"));
				s.setId_chambre(resultat.getInt("id_chambre"));
				s.setId_client(resultat.getInt("id_client"));
				list.add(s);
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
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("DELETE FROM paiement WHERE id_reservation=?");
			ps2.setInt(1, id);
			ps2.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps3.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteByIdChambre(int id) {
		try {
			PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
			ps1.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id_chambre=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps3.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteByIdClient(int id) {
		try {
			PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
			ps1.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id_client=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps3.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Reservation> findReservations(String mot){
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id_client IN(SELECT id FROM client WHERE nom LIKE ? OR prenom LIKE ?) OR id_chambre IN (SELECT id FROM chambre WHERE numero LIKE ?)");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ps.setString(3,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Reservation s = new Reservation();
				s.setId(resultat.getInt("id"));
				s.setDate_debut(resultat.getDate("date_debut"));
				s.setDate_fin(resultat.getDate("date_fin"));
				s.setNb_personnes(resultat.getInt("nb_personnes"));
				s.setId_chambre(resultat.getInt("id_chambre"));
				s.setId_client(resultat.getInt("id_client"));
				list.add(s);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Reservation findReservationByDateAndIdChambre(Date date, int id){
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE ? BETWEEN date_debut AND date_fin AND id_chambre=?");
			ps.setDate(1, date);
			ps.setInt(2, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Reservation s = new Reservation();
			s.setId(resultat.getInt("id"));
			s.setDate_debut(resultat.getDate("date_debut"));
			s.setDate_fin(resultat.getDate("date_fin"));
			s.setNb_personnes(resultat.getInt("nb_personnes"));
			s.setId_chambre(resultat.getInt("id_chambre"));
			s.setId_client(resultat.getInt("id_client"));

			return s;
		}catch(Exception ex){
			return null;
		}
	}
	
	public int nbResaByVille(String ville) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation WHERE id_chambre IN(SELECT id FROM chambre WHERE id_hotel IN(SELECT id FROM hotel WHERE ville=?))");
			ps.setString(1, ville);
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
	

	public int nbReservations() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM reservation");
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
