package ece.ing3.java.projet.modele.employe;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.finders.EmployeFinder;

import java.util.List;

/**
 * Modèle d'Employé stocké en base de donnée
 */
public class Employe extends Model {
	@Id
	private Long numero;
	private String nom;
	private String prenom;
	private String adresse;
	@Column( name = "tel" )
	private String numeroTelephone;

	/**
	 * Créer un noueau employé vide
	 */
	public Employe() {}

	/**
	 * Créer un nouveau employé
	 *
	 * @param numero          Numéro d'employé
	 * @param nom             Nom de l'employé
	 * @param prenom          Prénom de l'employé
	 * @param adresse         Adresse de l'employé
	 * @param numeroTelephone Numéro de téléphone de l'employé
	 */
	public Employe( Long numero, String nom, String prenom, String adresse, String numeroTelephone ) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
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
	 * Modifie le numéro d'employé
	 *
	 * @param numero Nouveau numéro de l'employé
	 */
	public void setNumero( Long numero ) {
		this.numero = numero;
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
		return numeroTelephone;
	}

	/**
	 * Modifie le numéro de téléphone de l'employé
	 *
	 * @param numeroTelephone Nouveau numéro de téléphone de l'employé
	 */
	public void setNumeroTelephone( String numeroTelephone ) {
		this.numeroTelephone = numeroTelephone;
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
				", numeroTelephone='" + getNumeroTelephone() + '\'' +
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
		return ( new EmployeFinder() ).numero( numero ).findUnique();
	}

	/**
	 * Récupère l'ensemble des employés
	 *
	 * @return Liste des employés
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Employe> findBaseList() throws DatabaseException {
		return ( new EmployeFinder() ).findList();
	}
}
