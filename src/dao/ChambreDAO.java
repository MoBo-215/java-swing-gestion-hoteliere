package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Chambre;
import entites.Database;

public class ChambreDAO {
	public void save(Chambre obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE chambre SET numero=?,nb_lits_simples=?,nb_lits_doubles=?,superficie=?,presence_sdb_privative=?,presence_ecran_plat=?,presence_balcon=?,presence_refrigirateur=?,presence_baignoire=?,presence_insonorisation=?,prix_nuit=?,id_hotel=? WHERE id=?");
				
				ps.setInt(1, obj.getNumero());
				ps.setInt(2, obj.getNb_lits_simples());
				ps.setInt(3, obj.getNb_lits_doubles());
				ps.setInt(4, obj.getSuperficie());
				ps.setString(5, obj.getPresence_sdb_privative());
				ps.setString(6, obj.getPresence_ecran_plat());
				ps.setString(7, obj.getPresence_balcon());
				ps.setString(8, obj.getPresence_refrigirateur());
				ps.setString(9, obj.getPresence_baignoire());
				ps.setString(10, obj.getPresence_insonorisation());
				ps.setDouble(11, obj.getPrix_nuit());
				ps.setInt(12, obj.getId_hotel());
				ps.setInt(13, obj.getId());
				ps.executeUpdate();
				
				PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps3.executeUpdate();
			}else {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO chambre (numero,nb_lits_simples,nb_lits_doubles,superficie,presence_sdb_privative,presence_ecran_plat,presence_balcon,presence_refrigirateur,presence_baignoire,presence_insonorisation,prix_nuit,id_hotel) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
				
				ps.setInt(1, obj.getNumero());
				ps.setInt(2, obj.getNb_lits_simples());
				ps.setInt(3, obj.getNb_lits_doubles());
				ps.setInt(4, obj.getSuperficie());
				ps.setString(5, obj.getPresence_sdb_privative());
				ps.setString(6, obj.getPresence_ecran_plat());
				ps.setString(7, obj.getPresence_balcon());
				ps.setString(8, obj.getPresence_refrigirateur());
				ps.setString(9, obj.getPresence_baignoire());
				ps.setString(10, obj.getPresence_insonorisation());
				ps.setDouble(11, obj.getPrix_nuit());
				ps.setInt(12, obj.getId_hotel());
				ps.executeUpdate();
				
				PreparedStatement ps2 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps2.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Chambre getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Chambre c = new Chambre();
			c.setId(resultat.getInt("id"));
			c.setNumero(resultat.getInt("numero"));
			c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
			c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
			c.setSuperficie(resultat.getInt("superficie"));
			c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
			c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
			c.setPresence_balcon(resultat.getString("presence_balcon"));
			c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
			c.setPresence_baignoire(resultat.getString("presence_baignoire"));
			c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
			c.setSuperficie(resultat.getInt("superficie"));
			c.setPrix_nuit(resultat.getDouble("prix_nuit"));
			c.setId_hotel(resultat.getInt("id_hotel"));
			
			return c;
		}catch(Exception ex){
			return null;
		}
	}
	
	public Chambre getByIdHotel(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id_hotel=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Chambre c = new Chambre();
			c.setId(resultat.getInt("id"));
			c.setNumero(resultat.getInt("numero"));
			c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
			c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
			c.setSuperficie(resultat.getInt("superficie"));
			c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
			c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
			c.setPresence_balcon(resultat.getString("presence_balcon"));
			c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
			c.setPresence_baignoire(resultat.getString("presence_baignoire"));
			c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
			c.setSuperficie(resultat.getInt("superficie"));
			c.setPrix_nuit(resultat.getDouble("prix_nuit"));
			c.setId_hotel(resultat.getInt("id_hotel"));
			
			return c;
		}catch(Exception ex){
			return null;
		}
	}
	
	public Chambre getByIdHotelAndNum(int idH, int num) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id_hotel=? AND numero=?");
			ps.setInt(1, idH);
			ps.setInt(2, num);

			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Chambre c = new Chambre();
			c.setId(resultat.getInt("id"));
			c.setNumero(resultat.getInt("numero"));
			c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
			c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
			c.setSuperficie(resultat.getInt("superficie"));
			c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
			c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
			c.setPresence_balcon(resultat.getString("presence_balcon"));
			c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
			c.setPresence_baignoire(resultat.getString("presence_baignoire"));
			c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
			c.setSuperficie(resultat.getInt("superficie"));
			c.setPrix_nuit(resultat.getDouble("prix_nuit"));
			c.setId_hotel(resultat.getInt("id_hotel"));
			
			return c;
		}catch(Exception ex){
			return null;
		}
	}
	
	public ArrayList<Chambre> getAll() {
		ArrayList<Chambre> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Chambre c = new Chambre();
				c.setId(resultat.getInt("id"));
				c.setNumero(resultat.getInt("numero"));
				c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
				c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
				c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
				c.setPresence_balcon(resultat.getString("presence_balcon"));
				c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
				c.setPresence_baignoire(resultat.getString("presence_baignoire"));
				c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPrix_nuit(resultat.getDouble("prix_nuit"));
				c.setId_hotel(resultat.getInt("id_hotel"));
				list.add(c);
			}
			
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Chambre> getAllByIdHotel(int id_hotel) {
		ArrayList<Chambre> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id_hotel=?");
			ps.setInt(1, id_hotel);
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Chambre c = new Chambre();
				c.setId(resultat.getInt("id"));
				c.setNumero(resultat.getInt("numero"));
				c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
				c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
				c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
				c.setPresence_balcon(resultat.getString("presence_balcon"));
				c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
				c.setPresence_baignoire(resultat.getString("presence_baignoire"));
				c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPrix_nuit(resultat.getDouble("prix_nuit"));
				c.setId_hotel(resultat.getInt("id_hotel"));
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
			
			PreparedStatement ps4 = Database.connexion.prepareStatement("DELETE FROM paiement WHERE id_reservation IN(SELECT id FROM reservation WHERE id_chambre IN(SELECT id FROM chambre WHERE id=?))");
			ps4.setInt(1, id);
			ps4.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id_chambre=?");
			ps2.setInt(1, id);
			ps2.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM chambre WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps3.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Chambre> findChambres(String mot){
		ArrayList<Chambre> list = new ArrayList<Chambre>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE numero LIKE ? OR prix_nuit LIKE ? OR superficie LIKE ? OR id_hotel IN(SELECT id FROM hotel WHERE nom LIKE ?)");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ps.setString(3,"%"+mot+"%");
			ps.setString(4,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Chambre c = new Chambre();
				c.setId(resultat.getInt("id"));
				c.setNumero(resultat.getInt("numero"));
				c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
				c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
				c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
				c.setPresence_balcon(resultat.getString("presence_balcon"));
				c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
				c.setPresence_baignoire(resultat.getString("presence_baignoire"));
				c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPrix_nuit(resultat.getDouble("prix_nuit"));
				c.setId_hotel(resultat.getInt("id_hotel"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Chambre> findChambresByNumero(int nb){
		ArrayList<Chambre> list = new ArrayList<Chambre>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE numero=?");
			ps.setInt(1, nb);
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Chambre c = new Chambre();
				c.setId(resultat.getInt("id"));
				c.setNumero(resultat.getInt("numero"));
				c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
				c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
				c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
				c.setPresence_balcon(resultat.getString("presence_balcon"));
				c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
				c.setPresence_baignoire(resultat.getString("presence_baignoire"));
				c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPrix_nuit(resultat.getDouble("prix_nuit"));
				c.setId_hotel(resultat.getInt("id_hotel"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Chambre> findChambresBySuperficie(int nb){
		ArrayList<Chambre> list = new ArrayList<Chambre>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE superficie=?");
			ps.setInt(1, nb);
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Chambre c = new Chambre();
				c.setId(resultat.getInt("id"));
				c.setNumero(resultat.getInt("numero"));
				c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
				c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
				c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
				c.setPresence_balcon(resultat.getString("presence_balcon"));
				c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
				c.setPresence_baignoire(resultat.getString("presence_baignoire"));
				c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPrix_nuit(resultat.getDouble("prix_nuit"));
				c.setId_hotel(resultat.getInt("id_hotel"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Chambre> findChambresByPrixNuit(double nb){
		ArrayList<Chambre> list = new ArrayList<Chambre>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE prix_nuit=?");
			ps.setDouble(1, nb);
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Chambre c = new Chambre();
				c.setId(resultat.getInt("id"));
				c.setNumero(resultat.getInt("numero"));
				c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
				c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
				c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
				c.setPresence_balcon(resultat.getString("presence_balcon"));
				c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
				c.setPresence_baignoire(resultat.getString("presence_baignoire"));
				c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPrix_nuit(resultat.getDouble("prix_nuit"));
				c.setId_hotel(resultat.getInt("id_hotel"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Chambre> findChambresDispoByVilleAndDates(String mot, Date da, Date dd){
		ArrayList<Chambre> list = new ArrayList<Chambre>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id_hotel IN(SELECT id FROM hotel WHERE ville LIKE ?) AND id NOT IN(SELECT id_chambre FROM reservation WHERE date_debut>=? AND date_fin<=?)");
			ps.setString(1,"%"+mot+"%");
			ps.setDate(2, da);
			ps.setDate(3, dd);
			
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Chambre c = new Chambre();
				c.setId(resultat.getInt("id"));
				c.setNumero(resultat.getInt("numero"));
				c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
				c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
				c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
				c.setPresence_balcon(resultat.getString("presence_balcon"));
				c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
				c.setPresence_baignoire(resultat.getString("presence_baignoire"));
				c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPrix_nuit(resultat.getDouble("prix_nuit"));
				c.setId_hotel(resultat.getInt("id_hotel"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbChambresDispoByVilleAndDates(String mot, Date da, Date dd){
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id_hotel IN(SELECT id FROM hotel WHERE ville=?) AND id NOT IN(SELECT id_chambre FROM reservation WHERE date_debut>=? AND date_fin<=?)");
			ps.setString(1, mot);
			ps.setDate(2, da);
			ps.setDate(3, dd);
			ResultSet resultat=ps.executeQuery();
			
			int nb=0;
			while(resultat.next()) {
				nb++;
			}
			
			return nb;
		}catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public ArrayList<Chambre> findChambresByHotel(String mot){
		ArrayList<Chambre> list = new ArrayList<Chambre>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre WHERE id_hotel IN(SELECT id FROM hotel WHERE nom=?)");
			ps.setString(1, mot);
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Chambre c = new Chambre();
				c.setId(resultat.getInt("id"));
				c.setNumero(resultat.getInt("numero"));
				c.setNb_lits_simples(resultat.getInt("nb_lits_simples"));
				c.setNb_lits_doubles(resultat.getInt("nb_lits_doubles"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPresence_sdb_privative(resultat.getString("presence_sdb_privative"));
				c.setPresence_ecran_plat(resultat.getString("presence_ecran_plat"));
				c.setPresence_balcon(resultat.getString("presence_balcon"));
				c.setPresence_refrigirateur(resultat.getString("presence_refrigirateur"));
				c.setPresence_baignoire(resultat.getString("presence_baignoire"));
				c.setPresence_insonorisation(resultat.getString("presence_insonorisation"));
				c.setSuperficie(resultat.getInt("superficie"));
				c.setPrix_nuit(resultat.getDouble("prix_nuit"));
				c.setId_hotel(resultat.getInt("id_hotel"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	

	public int nbChambres() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM chambre");
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
