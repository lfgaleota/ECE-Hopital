package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.hopital.Soigne;

import java.util.List;

/**
 * Utilitaire de recherche de modèle Soigne
 */
public class SoigneFinder {
	private SQLSelect<Soigne> finder;

	/**
	 * Initialise un nouveau utilitaire de recherche d'assignation à un docteur.
	 */
	public SoigneFinder() {
		this.finder = new SQLSelect<>( Soigne.class );
	}

	/**
	 * Conditionne le paramètre "no_chambre" de l'assignation à un docteur.
	 *
	 * @param numeroDocteur Numéro de chambre exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public SoigneFinder numeroDocteur( Long numeroDocteur ) {
		this.finder.andWhere( "no_docteur", "=", numeroDocteur );
		return this;
	}

	/**
	 * Conditionne le paramètre "no_chambre" de l'assignation à un docteur.
	 *
	 * @param numeroMalade Numéro de chambre exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public SoigneFinder numeroMalade( Long numeroMalade ) {
		this.finder.andWhere( "no_malade", "=", numeroMalade );
		return this;
	}

	/**
	 * Récupère l'ensemble des assignations à un docteur répondant aux conditions.
	 *
	 * @return Liste des Soignes répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Soigne> findList() throws DatabaseException {
		return this.finder.findList();
	}

	/**
	 * Récupère une unique assignation à un docteur répondant aux conditions.
	 *
	 * @return Première Soigne répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Soigne findUnique() throws DatabaseException {
		return this.finder.findUnique();
	}

	/**
	 * Indique si au moins une assignation à un docteur répondant aux conditions existe.
	 *
	 * @return Première Soigne répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public boolean hasAtLeastOne() throws DatabaseException {
		return this.finder.hasAtLeastOne();
	}
}
