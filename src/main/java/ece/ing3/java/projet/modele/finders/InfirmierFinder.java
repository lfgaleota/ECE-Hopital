package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.SQLSelect;
import ece.ing3.java.projet.enums.Rotation;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilitaire de recherche de modèle Infirmier
 */
public class InfirmierFinder {
	private SQLSelect finder;

	/**
	 * Initialise un nouveau utilitaire de recherche d'Infirmier
	 */
	@SuppressWarnings( "unchecked" )
	public InfirmierFinder() {
		this.finder = new SQLSelect( new Class[]{ Infirmier.class, Employe.class } );
	}

	/**
	 * Conditionne le paramètre "numero" de l'Infirmier
	 *
	 * @param numero Numéro exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder numero( Long numero ) {
		this.finder.andWhere( "numero", "=", numero );
		return this;
	}

	/**
	 * Conditionne le paramètre "nom" de l'Infirmier
	 *
	 * @param nom Nom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder nom( String nom ) {
		this.finder.andWhere( "nom", "=", nom );
		return this;
	}

	/**
	 * Conditionne le paramètre "prenom" de l'Infirmier
	 *
	 * @param prenom Prénom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder prenom( String prenom ) {
		this.finder.andWhere( "prenom", "=", prenom );
		return this;
	}

	/**
	 * Conditionne le paramètre "adresse" de l'Infirmier
	 *
	 * @param adresse Adresse exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder adresse( String adresse ) {
		this.finder.andWhere( "adresse", "=", adresse );
		return this;
	}

	/**
	 * Conditionne le paramètre "tel" de l'Infirmier
	 *
	 * @param tel Numéro de téléphone exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder tel( String tel ) {
		this.finder.andWhere( "tel", "=", tel );
		return this;
	}

	static Infirmier fromResultSet( ResultSet rs ) throws DatabaseException {
		try {
			return new Infirmier(
					rs.getLong( "numero" ),
					rs.getString( "nom" ),
					rs.getString( "prenom" ),
					rs.getString( "adresse" ),
					rs.getString( "tel" ),
					Rotation.valueOf( rs.getString( "rotation" ).toUpperCase() ),
					rs.getFloat( "salaire" ),
					rs.getString( "code_service" )
			);
		} catch( IllegalArgumentException e ) {
			throw new DatabaseException( "Invalid 'specialite' in database.", e );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère l'ensemble des Infirmiers répondant aux conditions
	 *
	 * @return Liste des Infirmiers répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Infirmier> findList() throws DatabaseException {
		try {
			List<Infirmier> Infirmiers = new LinkedList<>();
			ResultSet rs = this.finder.find();

			while( rs.next() ) {
				Infirmiers.add( fromResultSet( rs ) );
			}

			return Infirmiers;
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère un unique Infirmier répondant aux conditions
	 *
	 * @return Premier Infirmier répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Infirmier findUnique() throws DatabaseException {
		return fromResultSet( this.finder.findUnique() );
	}
}
