package ece.ing3.java.projet.modele.employe;

import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.enums.Specialite;
import ece.ing3.java.projet.modele.finders.DocteurFinder;
import ece.ing3.java.projet.modele.finders.ServiceFinder;

import java.util.List;

/**
 * Modèle de Docteur (un Employé) stocké en base de donnée
 */
public class Docteur extends Employe {
	private Specialite specialite;

	/**
	 * Créer un nouveau docteur vide
	 */
	public Docteur() {}

	/**
	 * Créer un nouveau docteur
	 *
	 * @param numero     Numéro d'employé
	 * @param nom        Nom du docteur
	 * @param prenom     Prénom du docteur
	 * @param adresse    Adresse du docteur
	 * @param tel        Numéro de téléphone du docteur
	 * @param specialite Spécialité du docteur
	 */
	public Docteur( Long numero, String nom, String prenom, String adresse, String tel, Specialite specialite ) {
		super( numero, nom, prenom, adresse, tel );
		this.specialite = specialite;
	}

	/**
	 * Récupère la spécialité du docteur
	 *
	 * @return Spécialité du docteur
	 */
	public Specialite getSpecialite() {
		return specialite;
	}

	/**
	 * Modifie la spécialité du docteur
	 *
	 * @param specialite Nouvelle spécialité du docteur
	 */
	public void setSpecialite( Specialite specialite ) {
		this.specialite = specialite;
	}

	/**
	 * Récupère les services dirigés par le docteur
	 *
	 * @return Liste des services dirigés par le docteur
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Service> getServicesDiriges() throws DatabaseException {
		return ( new ServiceFinder() ).numeroDirecteur( getNumero() ).findList();
	}

	/**
	 * Génère une représentation textuelle du docteur
	 *
	 * @return Représentation textuelle du docteur
	 */
	@Override
	public String toString() {
		return "Docteur{" +
				"specialite=" + getSpecialite() +
				" => " + super.toString() +
				'}';
	}

	/**
	 * Recherche un docteur de numéro spécifique
	 *
	 * @param numero Numéro du docteur à rechercher
	 * @return Docteur de numéro donné ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static Docteur find( Long numero ) throws DatabaseException {
		return ( new DocteurFinder() ).numero( numero ).findUnique();
	}

	/**
	 * Récupère l'ensemble des docteurs
	 *
	 * @return Liste des docteurs
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Docteur> findList() throws DatabaseException {
		return ( new DocteurFinder() ).findList();
	}
}
