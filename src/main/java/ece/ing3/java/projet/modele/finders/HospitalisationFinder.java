package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilitaire de recherche de modèle Hospitalisation
 */
public class HospitalisationFinder {
	private SQLSelect finder;

	/**
	 * Initialise un nouveau utilitaire de recherche de Hospitalisation
	 */
	public HospitalisationFinder() {
		this.finder = new SQLSelect( Hospitalisation.class );
	}

	/**
	 * Conditionne le paramètre "no_malade" de l'Hospitalisation
	 *
	 * @param numeroMalade Numéro de malade exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public HospitalisationFinder numeroMalade( Long numeroMalade ) {
		this.finder.andWhere( "no_malade", "=", numeroMalade );
		return this;
	}

	/**
	 * Conditionne le paramètre "code_service" de l'Hospitalisation
	 *
	 * @param codeService Code de service exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public HospitalisationFinder codeService( String codeService ) {
		this.finder.andWhere( "code_service", "=", codeService );
		return this;
	}

	/**
	 * Conditionne le paramètre "no_chambre" de l'Hospitalisation
	 *
	 * @param numeroChambre Numéro de chambre exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public HospitalisationFinder numeroChambre( Long numeroChambre ) {
		this.finder.andWhere( "no_chambre", "=", numeroChambre );
		return this;
	}

	/**
	 * Conditionne le paramètre "lit" de l'Hospitalisation
	 *
	 * @param numeroLit Numéro de lit exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public HospitalisationFinder numeroLit( Integer numeroLit ) {
		this.finder.andWhere( "lit", "=", numeroLit );
		return this;
	}

	static Hospitalisation fromResultSet( ResultSet rs ) throws DatabaseException {
		try {
			return new Hospitalisation(
					rs.getLong( "no_malade" ),
					rs.getString( "code_service" ),
					rs.getLong( "no_chambre" ),
					rs.getInt( "lit" )
			);
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère l'ensemble des Hospitalisations répondant aux conditions
	 *
	 * @return Liste des Hospitalisations répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Hospitalisation> findList() throws DatabaseException {
		try {
			List<Hospitalisation> Hospitalisations = new LinkedList<>();
			ResultSet rs = this.finder.find();

			while( rs.next() ) {
				Hospitalisations.add( fromResultSet( rs ) );
			}

			return Hospitalisations;
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère un unique Hospitalisation répondant aux conditions
	 *
	 * @return Première Hospitalisation répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Hospitalisation findUnique() throws DatabaseException {
		return fromResultSet( this.finder.findUnique() );
	}
}
