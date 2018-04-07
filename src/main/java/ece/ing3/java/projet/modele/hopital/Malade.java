package ece.ing3.java.projet.modele.hopital;

import ece.ing3.java.projet.database.sql.Model;

import java.util.List;

/**
 * Modèle de Malade stocké en base de donnée
 */
public class Malade extends Model {
	private Long numero;
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;
	private String mutuelle;

	/**
	 * Créer un nouveau malade
	 * @param numero Numéro du malade
	 * @param nom Nom du malade
	 * @param prenom Prénom du malade
	 * @param adresse Adresse du malade
	 * @param tel Numéro de téléphone du malade
	 * @param mutuelle Mutuelle du malade
	 */
	public Malade( Long numero, String nom, String prenom, String adresse, String tel, String mutuelle ) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.mutuelle = mutuelle;
	}

	/**
	 * Récupère le numéro du malade
	 * @return Numéro du malade
	 */
	public Long getNumero() {
		return numero;
	}

	/**
	 * Récupère le nom du malade
	 * @return Nom du malade
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du malade
	 * @param nom Nouveau nom du malade
	 */
	public void setNom( String nom ) {
		this.nom = nom;
	}

	/**
	 * Récupère le prénom du malade
	 * @return Prénom du malade
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie le prénom du malade
	 * @param prenom Nouveau prénom du malade
	 */
	public void setPrenom( String prenom ) {
		this.prenom = prenom;
	}

	/**
	 * Récupère l'adresse du malade
	 * @return Adresse du malade
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Modifie l'adresse du malade
	 * @param adresse Nouvelle adresse du malade
	 */
	public void setAdresse( String adresse ) {
		this.adresse = adresse;
	}

	/**
	 * Récupère le numéro de téléphone du malade
	 * @return Numéro de téléphone du malade
	 */
	public String getNumeroTelephone() {
		return tel;
	}

	/**
	 * Modifie le numéro de téléphone du malade
	 * @param numeroTelephone Nouveau numéro de téléphone du malade
	 */
	public void setNumeroTelephone( String numeroTelephone ) {
		this.tel = numeroTelephone;
	}

	/**
	 * Récupère la mutuelle du malade
	 * @return Mutuelle du malade
	 */
	public String getMutuelle() {
		return mutuelle;
	}

	/**
	 * Modifie la mutuelle du malade
	 * @param mutuelle Nouvelle mutuelle du malade
	 */
	public void setMutuelle( String mutuelle ) {
		this.mutuelle = mutuelle;
	}

	/**
	 * Récupère l'hospitalisation en cours du malade
	 * @return Hospitalisation en cours ou {@code null}
	 */
	public Hospitalisation getHospitalisation() {
		// TODO : Récupération de l'hospitalisation du malade
		return null;
	}
}
