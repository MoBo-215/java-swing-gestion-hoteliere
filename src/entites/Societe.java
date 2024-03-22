package entites;

import java.util.Objects;

public class Societe {
	private int id;
	private String num_siret;
	private String nom;
	private String adresse;
	
	public Societe() {
		
	}
	public Societe(int id, String num_siret, String nom, String adresse) {
		setId(id);
		setNum_siret(num_siret);
		setNom(nom);
		setAdresse(adresse);
	}
	public Societe(String num_siret, String nom, String adresse) {
		setNum_siret(num_siret);
		setNom(nom);
		setAdresse(adresse);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNum_siret() {
		return num_siret;
	}
	public void setNum_siret(String num_siret) {
		this.num_siret = num_siret;
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
	
	@Override
	public String toString() {
		return nom;
	}
	@Override
	public int hashCode() {
		return Objects.hash(adresse, id, nom, num_siret);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Societe other = (Societe) obj;
		return Objects.equals(adresse, other.adresse) && id == other.id && Objects.equals(nom, other.nom)
				&& Objects.equals(num_siret, other.num_siret);
	}
	
}
