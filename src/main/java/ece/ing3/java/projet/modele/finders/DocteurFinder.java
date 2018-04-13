package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.enums.Specialite;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilitaire de recherche de modèle Docteur
 */
public class DocteurFinder {
	private SQLSelect<Docteur> finder;

	/**
	 * Initialise un nouveau utilitaire de recherche du Docteur
	 */
	@SuppressWarnings( "unchecked" )
	public DocteurFinder() {
		this.finder = new SQLSelect<>( new Class[]{ Docteur.class, Employe.class } );
	}

	/**
	 * Conditionne le paramètre "numero" du Docteur
	 *
	 * @param numero Numéro exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder numero( Long numero ) {
		this.finder.andWhere( "numero", "=", numero );
		return this;
	}

	/**
	 * Conditionne le paramètre "nom" du Docteur
	 *
	 * @param nom Nom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder nom( String nom ) {
		this.finder.andWhere( "nom", "=", nom );
		return this;
	}

	/**
	 * Conditionne le paramètre "prenom" du Docteur
	 *
	 * @param prenom Prénom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder prenom( String prenom ) {
		this.finder.andWhere( "prenom", "=", prenom );
		return this;
	}

	/**
	 * Conditionne le paramètre "adresse" du Docteur
	 *
	 * @param adresse Adresse exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder adresse( String adresse ) {
		this.finder.andWhere( "adresse", "=", adresse );
		return this;
	}

	/**
	 * Conditionne le paramètre "tel" du Docteur
	 *
	 * @param numeroTelephone Numéro de téléphone exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder numeroTelephone( String numeroTelephone ) {
		this.finder.andWhere( "tel", "=", numeroTelephone );
		return this;
	}

	/**
	 * Conditionne le paramètre "specialite" du Docteur
	 *
	 * @param specialite Spécialité exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder specialite( Specialite specialite ) {
		this.finder.andWhere( "specialite", "=", specialite );
		return this;
	}

	/**
	 * Récupère l'ensemble des Docteurs répondant aux conditions
	 *
	 * @return Liste des Docteurs répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Docteur> findList() throws DatabaseException {
		return this.finder.findList();
	}

	/**
	 * Récupère un unique Docteur répondant aux conditions
	 *
	 * @return Premier Docteur répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Docteur findUnique() throws DatabaseException {
		return this.finder.findUnique();
	}
}
