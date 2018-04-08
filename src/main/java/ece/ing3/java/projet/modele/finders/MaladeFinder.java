package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.hopital.Malade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilitaire de recherche de modèle Malade
 */
public class MaladeFinder {
	private SQLSelect finder;

	/**
	 * Initialise un nouveau utilitaire de recherche de Malade
	 */
	public MaladeFinder() {
		this.finder = new SQLSelect( Malade.class );
	}

	/**
	 * Conditionne le paramètre "numero" du Malade
	 *
	 * @param numero Numéro exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public MaladeFinder numero( Long numero ) {
		this.finder.andWhere( "numero", "=", numero );
		return this;
	}

	/**
	 * Conditionne le paramètre "nom" du Malade
	 *
	 * @param nom Nom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public MaladeFinder nom( String nom ) {
		this.finder.andWhere( "nom", "=", nom );
		return this;
	}

	/**
	 * Conditionne le paramètre "prenom" du Malade
	 *
	 * @param prenom Prénom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public MaladeFinder prenom( String prenom ) {
		this.finder.andWhere( "prenom", "=", prenom );
		return this;
	}

	/**
	 * Conditionne le paramètre "adresse" du Malade
	 *
	 * @param adresse Adresse exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public MaladeFinder adresse( String adresse ) {
		this.finder.andWhere( "adresse", "=", adresse );
		return this;
	}

	/**
	 * Conditionne le paramètre "tel" du Malade
	 *
	 * @param numeroTelephone Numéro de téléphone exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public MaladeFinder numeroTelephone( String numeroTelephone ) {
		this.finder.andWhere( "tel", "=", numeroTelephone );
		return this;
	}

	/**
	 * Conditionne le paramètre "mutuelle" du Malade
	 *
	 * @param mutuelle Mutuelle exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public MaladeFinder mutuelle( String mutuelle ) {
		this.finder.andWhere( "mutuelle", "=", mutuelle );
		return this;
	}

	static Malade fromResultSet( ResultSet rs ) throws DatabaseException {
		try {
			return new Malade(
					rs.getLong( "numero" ),
					rs.getString( "nom" ),
					rs.getString( "prenom" ),
					rs.getString( "adresse" ),
					rs.getString( "tel" ),
					rs.getString( "mutuelle" )
			);
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère l'ensemble des Malades répondant aux conditions
	 *
	 * @return Liste des Malades répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Malade> findList() throws DatabaseException {
		try {
			List<Malade> Malades = new LinkedList<>();
			ResultSet rs = this.finder.find();

			while( rs.next() ) {
				Malades.add( fromResultSet( rs ) );
			}

			return Malades;
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère un unique Malade répondant aux conditions
	 *
	 * @return Premier Malade répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Malade findUnique() throws DatabaseException {
		return fromResultSet( this.finder.findUnique() );
	}
}
