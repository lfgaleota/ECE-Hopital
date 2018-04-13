package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.hopital.Chambre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilitaire de recherche de modèle Chambre
 */
public class ChambreFinder {
	private SQLSelect<Chambre> finder;

	/**
	 * Initialise un nouveau utilitaire de recherche de Chambre
	 */
	public ChambreFinder() {
		this.finder = new SQLSelect<>( Chambre.class );
	}

	/**
	 * Conditionne le paramètre "no_chambre" de la Chambre
	 *
	 * @param numeroChambre Numéro de chambre exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ChambreFinder numeroChambre( Long numeroChambre ) {
		this.finder.andWhere( "no_chambre", "=", numeroChambre );
		return this;
	}

	/**
	 * Conditionne le paramètre "surveillant" de la Chambre
	 *
	 * @param numeroSurveillant Numéro de surveillant exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ChambreFinder numeroSurveillant( Long numeroSurveillant ) {
		this.finder.andWhere( "surveillant", "=", numeroSurveillant );
		return this;
	}

	/**
	 * Conditionne le paramètre "code_service" de la Chambre
	 *
	 * @param codeServiceRattache Code du service de rattachement exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ChambreFinder codeServiceRattache( String codeServiceRattache ) {
		this.finder.andWhere( "code_service", "=", codeServiceRattache );
		return this;
	}

	/**
	 * Conditionne le paramètre "nb_lits" de la Chambre
	 *
	 * @param nombreLits Nombre de lits exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ChambreFinder nombreLits( Integer nombreLits ) {
		this.finder.andWhere( "nb_lits", "=", nombreLits );
		return this;
	}

	/**
	 * Récupère l'ensemble des Chambres répondant aux conditions
	 *
	 * @return Liste des Chambres répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Chambre> findList() throws DatabaseException {
		return this.finder.findList();
	}

	/**
	 * Récupère une unique Chambre répondant aux conditions
	 *
	 * @return Première Chambre répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Chambre findUnique() throws DatabaseException {
		return this.finder.findUnique();
	}
}
