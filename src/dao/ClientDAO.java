package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Client;
import entites.Database;

public class ClientDAO {
	public void save(Client obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE client SET nom=?,prenom=?,age=?,sexe=?,adresse=?,ville=?,num_tel=?,email=?,pays_residence=? WHERE id=?");
				
				ps.setString(1, obj.getNom());
				ps.setString(2, obj.getPrenom());
				ps.setInt(3, obj.getAge());
				ps.setString(4, obj.getSexe());
				ps.setString(5, obj.getAdresse());
				ps.setString(6, obj.getVille());
				ps.setString(7, obj.getNum_tel());
				ps.setString(8, obj.getEmail());
				ps.setString(9, obj.getPays_residence());
				ps.setInt(10, obj.getId());
				
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO client (nom,prenom,age,sexe,adresse,ville,num_tel,email,pays_residence) VALUES(?,?,?,?,?,?,?,?,?)");
				
				ps.setString(1, obj.getNom());
				ps.setString(2, obj.getPrenom());
				ps.setInt(3, obj.getAge());
				ps.setString(4, obj.getSexe());
				ps.setString(5, obj.getAdresse());
				ps.setString(6, obj.getVille());
				ps.setString(7, obj.getNum_tel());
				ps.setString(8, obj.getEmail());
				ps.setString(9, obj.getPays_residence());
				
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Client getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM client WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Client c = new Client();
			c.setId(resultat.getInt("id"));
			c.setNom(resultat.getString("nom"));
			c.setPrenom(resultat.getString("prenom"));
			c.setAge(resultat.getInt("age"));
			c.setSexe(resultat.getString("sexe"));
			c.setAdresse(resultat.getString("adresse"));
			c.setVille(resultat.getString("ville"));
			c.setNum_tel(resultat.getString("num_tel"));
			c.setEmail(resultat.getString("email"));
			c.setPays_residence(resultat.getString("pays_residence"));
			
			return c;
		}catch(Exception ex){
			return null;
		}
	}
	
	public ArrayList<Client> getAll() {
		ArrayList<Client> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM client");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Client c = new Client();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setPrenom(resultat.getString("prenom"));
				c.setAge(resultat.getInt("age"));
				c.setSexe(resultat.getString("sexe"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setNum_tel(resultat.getString("num_tel"));
				c.setEmail(resultat.getString("email"));
				c.setPays_residence(resultat.getString("pays_residence"));
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
			
			PreparedStatement ps4 = Database.connexion.prepareStatement("DELETE FROM paiement WHERE id_reservation IN(SELECT id FROM reservation WHERE id_client=?)");
			ps4.setInt(1, id);
			ps4.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("DELETE FROM reservation WHERE id_client=?");
			ps2.setInt(1, id);
			ps2.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM client WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps3.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Client> findClients(String mot){
		ArrayList<Client> list = new ArrayList<Client>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM client WHERE nom LIKE ? OR prenom LIKE ? OR adresse LIKE ? OR ville LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ps.setString(3,"%"+mot+"%");
			ps.setString(4,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Client c = new Client();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setPrenom(resultat.getString("prenom"));
				c.setAge(resultat.getInt("age"));
				c.setSexe(resultat.getString("sexe"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setNum_tel(resultat.getString("num_tel"));
				c.setEmail(resultat.getString("email"));
				c.setPays_residence(resultat.getString("pays_residence"));
				list.add(c);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Client getByNomPrenom(String nom, String prenom) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM client WHERE nom=? AND prenom=?");
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Client c = new Client();
			c.setId(resultat.getInt("id"));
			c.setNom(resultat.getString("nom"));
			c.setPrenom(resultat.getString("prenom"));
			c.setAge(resultat.getInt("age"));
			c.setSexe(resultat.getString("sexe"));
			c.setAdresse(resultat.getString("adresse"));
			c.setVille(resultat.getString("ville"));
			c.setNum_tel(resultat.getString("num_tel"));
			c.setEmail(resultat.getString("email"));
			c.setPays_residence(resultat.getString("pays_residence"));
			
			return c;
		}catch(Exception ex){
			return null;
		}
	}
	
	public ArrayList<Client> getAllByNomPrenom(String nom, String prenom) {
		ArrayList<Client> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM client WHERE nom=? AND prenom=?");
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Client c = new Client();
				c.setId(resultat.getInt("id"));
				c.setNom(resultat.getString("nom"));
				c.setPrenom(resultat.getString("prenom"));
				c.setAge(resultat.getInt("age"));
				c.setSexe(resultat.getString("sexe"));
				c.setAdresse(resultat.getString("adresse"));
				c.setVille(resultat.getString("ville"));
				c.setNum_tel(resultat.getString("num_tel"));
				c.setEmail(resultat.getString("email"));
				c.setPays_residence(resultat.getString("pays_residence"));
				list.add(c);
			}
			
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int nbClients() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM client");
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
