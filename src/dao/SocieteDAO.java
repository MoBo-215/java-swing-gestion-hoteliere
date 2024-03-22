package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Database;
import entites.Societe;

public class SocieteDAO {
	public void save(Societe obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE societe SET num_siret=?,nom=?,adresse=? WHERE id=?");
				
				ps.setString(1, obj.getNum_siret());
				ps.setString(2, obj.getNom());
				ps.setString(3, obj.getAdresse());
				ps.setInt(4, obj.getId());
				
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO societe (num_siret,nom,adresse) VALUES(?,?,?)");
				
				ps.setString(1, obj.getNum_siret());
				ps.setString(2, obj.getNom());
				ps.setString(3, obj.getAdresse());
				
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Societe getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM societe WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Societe s = new Societe();
			s.setId(resultat.getInt("id"));
			s.setNum_siret(resultat.getString("num_siret"));
			s.setNom(resultat.getString("nom"));
			s.setAdresse(resultat.getString("adresse"));
			
			return s;
		}catch(Exception ex){
			return null;
		}
	}
	
	public ArrayList<Societe> getAll() {
		ArrayList<Societe> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM societe");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Societe s = new Societe();
				s.setId(resultat.getInt("id"));
				s.setNum_siret(resultat.getString("num_siret"));
				s.setNom(resultat.getString("nom"));
				s.setAdresse(resultat.getString("adresse"));
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
			
			PreparedStatement ps6 = Database.connexion.prepareStatement("DELETE FROM paiement WHERE id_reservation IN(SELECT id FROM reservation WHERE id_chambre IN(SELECT id FROM chambre WHERE id_hotel IN (SELECT id FROM hotel WHERE id_societe IN(SELECT id FROM societe WHERE id=?))))");
			ps6.setInt(1, id);
			ps6.executeUpdate();
			
			PreparedStatement ps5 = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id_chambre IN(SELECT id FROM chambre WHERE id_hotel IN (SELECT id FROM hotel WHERE id_societe IN(SELECT id FROM societe WHERE id=?)))");
			ps5.setInt(1, id);
			ps5.executeUpdate();
			
			PreparedStatement ps4 = Database.connexion.prepareStatement("DELETE FROM chambre WHERE id_hotel IN (SELECT id FROM hotel WHERE id_societe IN(SELECT id FROM societe WHERE id=?))");
			ps4.setInt(1, id);
			ps4.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("DELETE FROM hotel WHERE id_societe=?");
			ps2.setInt(1, id);
			ps2.executeUpdate();

			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM societe WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps3.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Societe> findSocietes(String mot){
		ArrayList<Societe> list = new ArrayList<Societe>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM societe WHERE num_siret LIKE ? OR nom LIKE ? OR adresse LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ps.setString(3,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Societe s = new Societe();
				s.setId(resultat.getInt("id"));
				s.setNum_siret(resultat.getString("num_siret"));
				s.setNom(resultat.getString("nom"));
				s.setAdresse(resultat.getString("adresse"));
				list.add(s);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbSocietes() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM societe");
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
