package ece.ing3.java.projet.modele.hopital;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.finders.SoigneFinder;

import java.util.List;

/**
 * Modèle d'assignation à un docteur stocké en base de donnée
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Soigne extends Model {
	@Id
	@Column( name = "no_docteur" )
	private Long numeroDocteur;
	@Id
	@Column( name = "no_malade" )
	private Long numeroMalade;

	/**
	 * Créer une nouvelle assignation vide
	 */
	public Soigne() {
	}

	/**
	 * Créer une nouvelle assignation
	 *
	 * @param numeroDocteur Numéro de docteur lié
	 * @param numeroMalade  Numéro de malade lié
	 */
	public Soigne( Long numeroDocteur, Long numeroMalade ) {
		this.numeroDocteur = numeroDocteur;
		this.numeroMalade = numeroMalade;
	}

	/**
	 * Récupère le numéro de la chambre
	 *
	 * @return Numéro de la chambre
	 */
	public Long getNumeroDocteur() {
		return numeroDocteur;
	}

	/**
	 * Modifie le numéro de la chambre
	 *
	 * @param numeroDocteur Nouveau numéro de la chambre
	 */
	public void setNumeroDocteur( Long numeroDocteur ) {
		this.numeroDocteur = numeroDocteur;
	}

	/**
	 * Récupère le nombre de lits dans la chambre
	 *
	 * @return Nombre de lits dans la chambre
	 */
	public Long getNumeroMalade() {
		return numeroMalade;
	}

	/**
	 * Modifie le nombre de lits dans la chambre
	 *
	 * @param numeroMalade Nombre de lits dans la chambre
	 */
	public void setNumeroMalade( Long numeroMalade ) {
		this.numeroMalade = numeroMalade;
	}

	/**
	 * Récupère le docteur lié
	 *
	 * @return Docteur lié
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Docteur getDocteur() throws DatabaseException {
		return Docteur.find( numeroMalade );
	}

	/**
	 * Modifie le docteur lié
	 *
	 * @param docteur Nouveau malade lié
	 */
	public void setDocteur( Docteur docteur ) {
		this.numeroDocteur = docteur.getNumero();
	}

	/**
	 * Récupère le malade lié
	 *
	 * @return Malade lié
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Malade getMalade() throws DatabaseException {
		return Malade.find( numeroMalade );
	}

	/**
	 * Modifie le malade lié
	 *
	 * @param malade Nouveau malade lié
	 */
	public void setMalade( Malade malade ) {
		this.numeroMalade = malade.getNumero();
	}

	/**
	 * Recherche si une association malade-docteur existe déjà.
	 *
	 * @param numeroDocteur       Numéro de chambre à rechercher
	 * @param numeroMalade Code du service de rattachement à rechercher
	 * @return {@code true} si l'association existe
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static boolean find( Long numeroDocteur, Long numeroMalade ) throws DatabaseException {
		return ( new SoigneFinder() ).numeroDocteur( numeroDocteur ).numeroMalade( numeroMalade ).hasAtLeastOne();
	}

	/**
	 * Récupère l'ensemble des associations
	 *
	 * @return Liste des associations
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Soigne> findList() throws DatabaseException {
		return ( new SoigneFinder() ).findList();
	}

	/**
	 * Génère une représentation textuelle de la chambre
	 *
	 * @return Représentation textuelle de la chambre
	 */
	@Override
	public String toString() {
		return "Soigne{" +
				"numeroDocteur=" + numeroDocteur +
				", numeroMalade=" + numeroMalade +
				'}';
	}
}
