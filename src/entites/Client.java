package entites;

import java.util.Objects;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	private String adresse;
	private String ville;
	private String num_tel;
	private String email;
	private String pays_residence;
	
	public Client() {
		
	}
	public Client(int id, String nom, String prenom, int age, String sexe, String adresse, String ville, String num_tel, String email, String pays_residence) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setAge(age);
		setSexe(sexe);
		setAdresse(adresse);
		setVille(ville);
		setNum_tel(num_tel);
		setEmail(email);
		setPays_residence(pays_residence);
	}
	public Client(String nom, String prenom, int age, String sexe, String adresse, String ville, String num_tel, String email, String pays_residence) {
		setNom(nom);
		setPrenom(prenom);
		setAge(age);
		setSexe(sexe);
		setAdresse(adresse);
		setVille(ville);
		setNum_tel(num_tel);
		setEmail(email);
		setPays_residence(pays_residence);
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
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
	public String getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPays_residence() {
		return pays_residence;
	}
	public void setPays_residence(String pays_residence) {
		this.pays_residence = pays_residence;
	}
	
	@Override
	public String toString() {
		return prenom+" "+nom.toUpperCase();
	}
	@Override
	public int hashCode() {
		return Objects.hash(adresse, age, email, id, nom, num_tel, pays_residence, prenom, sexe, ville);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(adresse, other.adresse) && age == other.age && Objects.equals(email, other.email)
				&& id == other.id && Objects.equals(nom, other.nom) && Objects.equals(num_tel, other.num_tel)
				&& Objects.equals(pays_residence, other.pays_residence) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(sexe, other.sexe) && Objects.equals(ville, other.ville);
	}
}
