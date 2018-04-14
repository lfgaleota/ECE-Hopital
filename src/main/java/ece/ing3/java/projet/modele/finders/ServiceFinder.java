package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.exceptions.DatabaseException;

import java.util.List;

/**
 * Utilitaire de recherche de modèle Service
 */
public class ServiceFinder {
	private SQLSelect<Service> finder;

	/**
	 * Initialise un nouveau utilitaire de recherche de Service
	 */
	public ServiceFinder() {
		this.finder = new SQLSelect<>( Service.class );
	}

	/**
	 * Conditionne le paramètre "code" du Service
	 *
	 * @param code Code exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ServiceFinder code( String code ) {
		this.finder.andWhere( "code", "=", code );
		return this;
	}

	/**
	 * Conditionne le paramètre "nom" du Service
	 *
	 * @param nom Nom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ServiceFinder nom( String nom ) {
		this.finder.andWhere( "nom", "=", nom );
		return this;
	}

	/**
	 * Conditionne le paramètre "batiment" du Service
	 *
	 * @param batiment Bâtiment exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ServiceFinder batiment( String batiment ) {
		this.finder.andWhere( "batiment", "=", batiment );
		return this;
	}

	/**
	 * Conditionne le paramètre "directeur" du Service
	 *
	 * @param numeroDirecteur Numéro du directeur exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ServiceFinder numeroDirecteur( Long numeroDirecteur ) {
		this.finder.andWhere( "directeur", "=", numeroDirecteur );
		return this;
	}

	/**
	 * Récupère l'ensemble des Services répondant aux conditions
	 *
	 * @return Liste des Services répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Service> findList() throws DatabaseException {
		return this.finder.findList();
	}

	/**
	 * Récupère un unique Service répondant aux conditions
	 *
	 * @return Premier Service répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Service findUnique() throws DatabaseException {
		return this.finder.findUnique();
	}
}
