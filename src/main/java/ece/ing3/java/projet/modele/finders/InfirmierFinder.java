package ece.ing3.java.projet.modele.finders;

import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.enums.Rotation;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;

import java.util.List;
/**
 * Utilitaire de recherche de modèle Infirmier
 */
public class InfirmierFinder {
	private SQLSelect<Infirmier> finder;

	/**
	 * Initialise un nouveau utilitaire de recherche d'Infirmier
	 */
	@SuppressWarnings( "unchecked" )
	public InfirmierFinder() {
		this.finder = new SQLSelect<>( new Class[]{ Infirmier.class, Employe.class } );
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
	 * @param numeroTelephone Numéro de téléphone exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder numeroTelephone( String numeroTelephone ) {
		this.finder.andWhere( "tel", "=", numeroTelephone );
		return this;
	}

	/**
	 * Conditionne le paramètre "rotation" de l'Infirmier
	 *
	 * @param rotation Période de rotation exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder rotation( Rotation rotation ) {
		this.finder.andWhere( "rotation", "=", rotation );
		return this;
	}

	/**
	 * Conditionne le paramètre "salaire" de l'Infirmier
	 *
	 * @param salaire Salaire exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder salaire( Float salaire ) {
		this.finder.andWhere( "salaire", "=", salaire );
		return this;
	}

	/**
	 * Conditionne le paramètre "code_service" de l'Infirmier
	 *
	 * @param codeService Code de service exact qui sera utilisé pour la sélection
	 * @return Utilitaire de recherche
	 */
	public InfirmierFinder codeService( String codeService ) {
		this.finder.andWhere( "code_service", "=", codeService );
		return this;
	}

	/**
	 * Récupère l'ensemble des Infirmiers répondant aux conditions
	 *
	 * @return Liste des Infirmiers répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Infirmier> findList() throws DatabaseException {
		return this.finder.findList();
	}

	/**
	 * Récupère un unique Infirmier répondant aux conditions
	 *
	 * @return Premier Infirmier répondant aux conditions
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Infirmier findUnique() throws DatabaseException {
		return this.finder.findUnique();
	}
}
