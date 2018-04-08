package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.employe.Employe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilitaire de recherche de modèle Employe
 */
public class EmployeFinder {
	private SQLSelect finder;

	/**
	 * Initialise un nouveau utilitaire de recherche d'Employe
	 */
	public EmployeFinder() {
		this.finder = new SQLSelect( Employe.class );
	}

	/**
	 * Conditionne le paramètre "numero" de l'Employe
	 *
	 * @param numero Numéro exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public EmployeFinder numero( Long numero ) {
		this.finder.andWhere( "numero", "=", numero );
		return this;
	}

	/**
	 * Conditionne le paramètre "nom" de l'Employe
	 *
	 * @param nom Nom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public EmployeFinder nom( String nom ) {
		this.finder.andWhere( "nom", "=", nom );
		return this;
	}

	/**
	 * Conditionne le paramètre "prenom" de l'Employe
	 *
	 * @param prenom Prénom exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public EmployeFinder prenom( String prenom ) {
		this.finder.andWhere( "prenom", "=", prenom );
		return this;
	}

	/**
	 * Conditionne le paramètre "adresse" de l'Employe
	 *
	 * @param adresse Adresse exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public EmployeFinder adresse( String adresse ) {
		this.finder.andWhere( "adresse", "=", adresse );
		return this;
	}

	/**
	 * Conditionne le paramètre "tel" de l'Employe
	 *
	 * @param tel Numéro de téléphone exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public EmployeFinder tel( String tel ) {
		this.finder.andWhere( "tel", "=", tel );
		return this;
	}

	static Employe fromResultSet( ResultSet rs ) throws DatabaseException {
		try {
			return new Employe(
					rs.getLong( "numero" ),
					rs.getString( "nom" ),
					rs.getString( "prenom" ),
					rs.getString( "adresse" ),
					rs.getString( "tel" )
			);
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère l'ensemble des Employes répondant aux conditions
	 *
	 * @return Liste des Employes répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Employe> findList() throws DatabaseException {
		try {
			List<Employe> Employes = new LinkedList<>();
			ResultSet rs = this.finder.find();

			while( rs.next() ) {
				Employes.add( fromResultSet( rs ) );
			}

			return Employes;
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Récupère un unique Employe répondant aux conditions
	 *
	 * @return Premier Employe répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Employe findUnique() throws DatabaseException {
		return fromResultSet( this.finder.findUnique() );
	}
}
