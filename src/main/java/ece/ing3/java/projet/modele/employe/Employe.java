package ece.ing3.java.projet.modele.employe;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;

/**
 * Modèle d'Employé stocké en base de donnée
 */
public class Employe extends Model {
	private Long numero;
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;

	/**
	 * Créer un nouveau employé
	 *
	 * @param numero  Numéro d'employé
	 * @param nom     Nom de l'employé
	 * @param prenom  Prénom de l'employé
	 * @param adresse Adresse de l'employé
	 * @param tel     Numéro de téléphone de l'employé
	 */
	public Employe( Long numero, String nom, String prenom, String adresse, String tel ) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
	}

	/**
	 * Récupère le numéro d'employé
	 *
	 * @return Numéro d'employé
	 */
	public Long getNumero() {
		return numero;
	}

	/**
	 * Récupère le nom de l'employé
	 *
	 * @return Nom de l'employé
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom de l'employé
	 *
	 * @param nom Nouveau nom de l'employé
	 */
	public void setNom( String nom ) {
		this.nom = nom;
	}

	/**
	 * Récupère le prénom de l'employé
	 *
	 * @return Prénom de l'employé
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie le prénom de l'employé
	 *
	 * @param prenom Nouveau prénom de l'employé
	 */
	public void setPrenom( String prenom ) {
		this.prenom = prenom;
	}

	/**
	 * Récupère l'adresse de l'employé
	 *
	 * @return Adresse de l'employé
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse de l'employé
	 *
	 * @param adresse Nouvelle adresse de l'employé
	 */
	public void setAdresse( String adresse ) {
		this.adresse = adresse;
	}

	/**
	 * Récupère le numéro de téléphone de l'employé
	 *
	 * @return Numéro de téléphone de l'employé
	 */
	public String getNumeroTelephone() {
		return tel;
	}

	/**
	 * Modifie le numéro de téléphone de l'employé
	 *
	 * @param numeroTelephone Nouveau numéro de téléphone de l'employé
	 */
	public void setNumeroTelephone( String numeroTelephone ) {
		this.tel = numeroTelephone;
	}

	/**
	 * Génère une représentation textuelle de l'employé
	 *
	 * @return Représentation textuelle de l'employé
	 */
	@Override
	public String toString() {
		return "Employe{" +
				"numero=" + getNumero() +
				", nom='" + getNom() + '\'' +
				", prenom='" + getPrenom() + '\'' +
				", adresse='" + getAdresse() + '\'' +
				", tel='" + getNumeroTelephone() + '\'' +
				'}';
	}

	/**
	 * Recherche un employé de numéro spécifique
	 *
	 * @param numero Numéro de l'employé à rechercher
	 * @return Employé de numéro donné ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static Employe find( Long numero ) throws DatabaseException {
		// TODO : Recherche d'employé par numéro
		return null;
	}
}
