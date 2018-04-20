package ece.ing3.java.projet.modele.hopital;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.finders.MaladeFinder;

import java.util.List;

/**
 * Modèle de Malade stocké en base de donnée
 */
public class Malade extends Model {
	@Id
	private Long numero;
	private String nom;
	private String prenom;
	private String adresse;
	@Column( name = "tel" )
	private String numeroTelephone;
	private String mutuelle;

	/**
	 * Créer un nouveau malade vide
	 */
	public Malade() {
	}

	/**
	 * Créer un nouveau malade
	 *
	 * @param numero          Numéro du malade
	 * @param nom             Nom du malade
	 * @param prenom          Prénom du malade
	 * @param adresse         Adresse du malade
	 * @param numeroTelephone Numéro de téléphone du malade
	 * @param mutuelle        Mutuelle du malade
	 */
	public Malade( Long numero, String nom, String prenom, String adresse, String numeroTelephone, String mutuelle ) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
		this.mutuelle = mutuelle;
	}

	/**
	 * Récupère le numéro du malade
	 *
	 * @return Numéro du malade
	 */
	public Long getNumero() {
		return numero;
	}

	/**
	 * Modifie le numéro du malade
	 *
	 * @param numero Nouveau numéro du malade
	 */
	public void setNumero( Long numero ) {
		this.numero = numero;
	}

	/**
	 * Récupère le nom du malade
	 *
	 * @return Nom du malade
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du malade
	 *
	 * @param nom Nouveau nom du malade
	 */
	public void setNom( String nom ) {
		this.nom = nom;
	}

	/**
	 * Récupère le prénom du malade
	 *
	 * @return Prénom du malade
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie le prénom du malade
	 *
	 * @param prenom Nouveau prénom du malade
	 */
	public void setPrenom( String prenom ) {
		this.prenom = prenom;
	}

	/**
	 * Récupère l'adresse du malade
	 *
	 * @return Adresse du malade
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse du malade
	 *
	 * @param adresse Nouvelle adresse du malade
	 */
	public void setAdresse( String adresse ) {
		this.adresse = adresse;
	}

	/**
	 * Récupère le numéro de téléphone du malade
	 *
	 * @return Numéro de téléphone du malade
	 */
	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	/**
	 * Modifie le numéro de téléphone du malade
	 *
	 * @param numeroTelephone Nouveau numéro de téléphone du malade
	 */
	public void setNumeroTelephone( String numeroTelephone ) {
		this.numeroTelephone = numeroTelephone;
	}

	/**
	 * Récupère la mutuelle du malade
	 *
	 * @return Mutuelle du malade
	 */
	public String getMutuelle() {
		return mutuelle;
	}

	/**
	 * Modifie la mutuelle du malade
	 *
	 * @param mutuelle Nouvelle mutuelle du malade
	 */
	public void setMutuelle( String mutuelle ) {
		this.mutuelle = mutuelle;
	}

	/**
	 * Récupère l'hospitalisation en cours du malade
	 *
	 * @return Hospitalisation en cours ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Hospitalisation getHospitalisation() throws DatabaseException {
		return Hospitalisation.find( numero );
	}

	/**
	 * Recherche un malade pour un numéro donné
	 *
	 * @param numero Numéro de malade de l'hospitalisation à rechercher
	 * @return Malade de numéro donné ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static Malade find( Long numero ) throws DatabaseException {
		return ( new MaladeFinder() ).numero( numero ).findUnique();
	}

	/**
	 * Récupère l'ensemble des malades
	 *
	 * @return Liste des malades
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Malade> findList() throws DatabaseException {
		return ( new MaladeFinder() ).findList();
	}

	/**
	 * Génère une représentation textuelle du malade
	 *
	 * @return Représentation textuelle du malade
	 */
	@Override
	public String toString() {
		return "Malade{" +
				"numero=" + getNumero() +
				", nom='" + getNom() + '\'' +
				", prenom='" + getPrenom() + '\'' +
				", adresse='" + getAdresse() + '\'' +
				", numeroTelephone='" + getNumeroTelephone() + '\'' +
				", mutuelle='" + getMutuelle() + '\'' +
				'}';
	}
}
