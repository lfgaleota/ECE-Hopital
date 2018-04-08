package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.SQLSelect;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.exceptions.DatabaseException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilitaire de recherche de modèle Service
 */
public class ServiceFinder {
	private SQLSelect finder;

	/**
	 * Initialise un nouveau utilitaire de recherche de Service
	 */
	public ServiceFinder() {
		this.finder = new SQLSelect( Service.class );
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
	 * @param directeur Directeur exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public ServiceFinder directeur( Docteur directeur ) {
		this.finder.andWhere( "directeur", "=", directeur.getNumero() );
		return this;
	}

	static Service fromResultSet( ResultSet rs ) throws DatabaseException {
		try {
			return new Service(
					rs.getString( "code" ),
					rs.getString( "nom" ),
					rs.getString( "batiment" ),
					rs.getLong( "directeur" )
			);
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère l'ensemble des services répondant aux conditions
	 *
	 * @return Liste des services répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Service> findList() throws DatabaseException {
		try {
			List<Service> services = new LinkedList<>();
			ResultSet rs = this.finder.find();

			while( rs.next() ) {
				services.add( fromResultSet( rs ) );
			}

			return services;
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère un unique service répondant aux conditions
	 *
	 * @return Premier service répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Service findUnique() throws DatabaseException {
		return fromResultSet( this.finder.findUnique() );
	}
}
