package entites;

import java.sql.Date;
import java.util.Objects;

public class Paiement {
	private int id;
	private Date date;
	private double montant;
	private String methode;
	private int id_reservation;
	
	public Paiement() {
		
	}
	public Paiement(int id, Date date, double montant, String methode, int id_reservation) {
		setId(id);
		setDate(date);
		setMontant(montant);
		setMethode(methode);
		setId_reservation(id_reservation);
	}
	public Paiement(Date date, double montant, String methode, int id_reservation) {
		setDate(date);
		setMontant(montant);
		setMethode(methode);
		setId_reservation(id_reservation);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getMethode() {
		return methode;
	}
	public void setMethode(String methode) {
		this.methode = methode;
	}
	public int getId_reservation() {
		return id_reservation;
	}
	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}
	
	@Override
	public String toString() {
		return "Paiement [id=" + id + ", date=" + date + ", montant=" + montant + ", methode=" + methode
				+ ", id_reservation=" + id_reservation + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(date, id, id_reservation, methode, montant);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paiement other = (Paiement) obj;
		return Objects.equals(date, other.date) && id == other.id && id_reservation == other.id_reservation
				&& Objects.equals(methode, other.methode)
				&& Double.doubleToLongBits(montant) == Double.doubleToLongBits(other.montant);
	}
}
