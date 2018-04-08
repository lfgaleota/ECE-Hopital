package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.SQLSelect;
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
	private SQLSelect finder;

	/**
	 * Initialise un nouveau utilitaire de recherche de Docteur
	 */
	@SuppressWarnings( "unchecked" )
	public DocteurFinder() {
		this.finder = new SQLSelect( new Class[]{ Docteur.class, Employe.class } );
	}

	/**
	 * Conditionne le paramètre "numero" de Docteur
	 *
	 * @param numero Numéro exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder numero( Long numero ) {
		this.finder.andWhere( "numero", "=", numero );
		return this;
	}

	/**
	 * Conditionne le paramètre "nom" de Docteur
	 *
	 * @param nom Nom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder nom( String nom ) {
		this.finder.andWhere( "nom", "=", nom );
		return this;
	}

	/**
	 * Conditionne le paramètre "prenom" de Docteur
	 *
	 * @param prenom Prénom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder prenom( String prenom ) {
		this.finder.andWhere( "prenom", "=", prenom );
		return this;
	}

	/**
	 * Conditionne le paramètre "adresse" de Docteur
	 *
	 * @param adresse Adresse exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder adresse( String adresse ) {
		this.finder.andWhere( "adresse", "=", adresse );
		return this;
	}

	/**
	 * Conditionne le paramètre "tel" de Docteur
	 *
	 * @param tel Numéro de téléphone exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public DocteurFinder tel( String tel ) {
		this.finder.andWhere( "tel", "=", tel );
		return this;
	}

	static Docteur fromResultSet( ResultSet rs ) throws DatabaseException {
		try {
			return new Docteur(
					rs.getLong( "numero" ),
					rs.getString( "nom" ),
					rs.getString( "prenom" ),
					rs.getString( "adresse" ),
					rs.getString( "tel" ),
					Specialite.valueOf( rs.getString( "specialite" ).toUpperCase() )
			);
		} catch( IllegalArgumentException e ) {
			throw new DatabaseException( "Invalid 'specialite' in database.", e );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère l'ensemble des Docteurs répondant aux conditions
	 *
	 * @return Liste des Docteurs répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Docteur> findList() throws DatabaseException {
		try {
			List<Docteur> Docteurs = new LinkedList<>();
			ResultSet rs = this.finder.find();

			while( rs.next() ) {
				Docteurs.add( this.fromResultSet( rs ) );
			}

			return Docteurs;
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère un unique Docteur répondant aux conditions
	 *
	 * @return Premier Docteur répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Docteur findUnique() throws DatabaseException {
		return this.fromResultSet( this.finder.findUnique() );
	}
}
