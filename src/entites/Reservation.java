package entites;

import java.sql.Date;
import java.util.Objects;

public class Reservation {
	private int id;
	private Date date_debut;
	private Date date_fin;
	private int nb_personnes;
	private int id_chambre;
	private int id_client;
	
	public Reservation() {
		
	}
	public Reservation(int id, Date date_debut, Date date_fin, int nb_personnes, int id_chambre, int id_client) {
		setId(id);
		setDate_debut(date_debut);
		setDate_fin(date_fin);
		setNb_personnes(nb_personnes);
		setId_chambre(id_chambre);
		setId_client(id_client);
	}
	public Reservation(Date date_debut, Date date_fin, int nb_personnes, int id_chambre, int id_client) {
		setDate_debut(date_debut);
		setDate_fin(date_fin);
		setNb_personnes(nb_personnes);
		setId_chambre(id_chambre);
		setId_client(id_client);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public int getNb_personnes() {
		return nb_personnes;
	}
	public void setNb_personnes(int nb_personnes) {
		this.nb_personnes = nb_personnes;
	}
	public int getId_chambre() {
		return id_chambre;
	}
	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	
	@Override
	public String toString() {
		return id+"";
	}
	@Override
	public int hashCode() {
		return Objects.hash(date_debut, date_fin, id, id_chambre, id_client, nb_personnes);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(date_debut, other.date_debut) && Objects.equals(date_fin, other.date_fin)
				&& id == other.id && id_chambre == other.id_chambre && id_client == other.id_client
				&& nb_personnes == other.nb_personnes;
	}

}
