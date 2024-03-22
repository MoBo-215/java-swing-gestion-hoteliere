package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Database;
import entites.Paiement;

public class PaiementDAO {
	public void save(Paiement obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE paiement SET date=?,montant=?,methode=?,id_reservation=? WHERE id=?");
				ps.setDate(1, obj.getDate());
				ps.setDouble(2, obj.getMontant());
				ps.setString(3, obj.getMethode());
				ps.setInt(4, obj.getId_reservation());
				ps.setInt(5, obj.getId());
				ps.executeUpdate();
				
				PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps3.executeUpdate();
			}else {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO paiement (date,montant,methode,id_reservation) VALUES(?,?,?,?)");
				ps.setDate(1, obj.getDate());
				ps.setDouble(2, obj.getMontant());
				ps.setString(3, obj.getMethode());
				ps.setInt(4, obj.getId_reservation());
				ps.executeUpdate();
				
				PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps3.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Paiement getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Paiement p = new Paiement();
			p.setId(resultat.getInt("id"));
			p.setDate(resultat.getDate("date"));
			p.setMontant(resultat.getDouble("montant"));
			p.setMethode(resultat.getString("methode"));
			p.setId_reservation(resultat.getInt("id_reservation"));
			
			return p;
		}catch(Exception ex){
			return null;
		}
	}
	
	public Paiement getByIdReservation(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE id_reservation=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Paiement p = new Paiement();
			p.setId(resultat.getInt("id"));
			p.setDate(resultat.getDate("date"));
			p.setMontant(resultat.getDouble("montant"));
			p.setMethode(resultat.getString("methode"));
			p.setId_reservation(resultat.getInt("id_reservation"));
			
			return p;
		}catch(Exception ex){
			return null;
		}
	}
	
	public ArrayList<Paiement> getAll() {
		ArrayList<Paiement> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Paiement p = new Paiement();
				p.setId(resultat.getInt("id"));
				p.setDate(resultat.getDate("date"));
				p.setMontant(resultat.getDouble("montant"));
				p.setMethode(resultat.getString("methode"));
				p.setId_reservation(resultat.getInt("id_reservation"));
				list.add(p);
			}
			
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Paiement> getAllByIdReservation(int id_reservation) {
		ArrayList<Paiement> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE id_reservation=?");
			ps.setInt(1, id_reservation);
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Paiement p = new Paiement();
				p.setId(resultat.getInt("id"));
				p.setDate(resultat.getDate("date"));
				p.setMontant(resultat.getDouble("montant"));
				p.setMethode(resultat.getString("methode"));
				p.setId_reservation(resultat.getInt("id_reservation"));
				list.add(p);
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
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM paiement WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps3.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Paiement> findPaiements(String mot){
		ArrayList<Paiement> list = new ArrayList<Paiement>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE date LIKE ? OR montant LIKE ? OR methode LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ps.setString(3,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Paiement p = new Paiement();
				p.setId(resultat.getInt("id"));
				p.setDate(resultat.getDate("date"));
				p.setMontant(resultat.getDouble("montant"));
				p.setMethode(resultat.getString("methode"));
				p.setId_reservation(resultat.getInt("id_reservation"));
				list.add(p);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int nbPaiements() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement");
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
	
	public double totalCAparAn() {
		double pa=0;
		double total=0;
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE YEAR(date)=YEAR(curdate())");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Paiement p = new Paiement();
				pa = resultat.getDouble("montant");
				total=total+pa;
			}
			
			return total;
		}catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public double totalCAparMois() {
		double pa=0;
		double total=0;
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE MONTH(date)=MONTH(curdate())");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Paiement p = new Paiement();
				pa = resultat.getDouble("montant");
				total=total+pa;
			}
			
			return total;
		}catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public double totalCAparJour() {
		double pa=0;
		double total=0;
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM paiement WHERE date=curdate()");
			ResultSet resultat = ps.executeQuery();
			
			while(resultat.next()) {
				Paiement p = new Paiement();
				pa = resultat.getDouble("montant");
				total=total+pa;
			}
			
			return total;
		}catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
}
