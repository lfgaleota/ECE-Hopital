package ece.ing3.java.projet.interfaces;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.tables.TableModel;

/**
 * Interface d'objet fournissant les informations de configurations et de retour d'un {@link ece.ing3.java.projet.workers.ModelQueryWorker}, pour la récupération de donnée dans un {@link TableModel} d'un modèle BDD défini.
 *
 * @param <M> Type associé à la requête de récupération de donnée.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public interface ModelQueryWorkerProvider<M extends Model> {
	/**
	 * Récupère la classe associé au modèle BDD utilisé.
	 *
	 * @return Classe associé au modèle
	 */
	Class<M> getModelClass();

	/**
	 * Créer le sélecteur SQL à utiliser pour la requête.
	 *
	 * @return Sélecteur SQL à utiliser
	 */
	SQLSelect<M> queryCreateSelector();

	/**
	 * Récupère la clause Where à utiliser, avant modification.
	 *
	 * @return Clause Where à utiliser
	 */
	Where getWhereClause();

	/**
	 * Récupère la clause OrderBy à utiliser, avant modification.
	 *
	 * @return Clause OrderBy à utiliser
	 */
	OrderBy getOrderByClause();

	/**
	 * Méthode pouvant modifier la clause Where avant exécution de la requête, sans impacter celle stockée.
	 *
	 * @param whereClause Ancienne clause Where
	 * @return Nouvelle clause Where
	 */
	Where queryModifyWhereClause( Where whereClause );

	/**
	 * Méthode pouvant modifier la clause Order By avant exécution de la requête, sans impacter celle stockée.
	 *
	 * @param orderByClause Ancienne clause Order By
	 * @return Nouvelle clause Order By
	 */
	OrderBy queryModifyOrderByClause( OrderBy orderByClause );

	/**
	 * Récupère le modèle de tableau où doit être stocké les données récupérées.
	 *
	 * @return Modèle de tableau à mettre à jour
	 */
	TableModel<M> getTableModel();

	/**
	 * Méthode appelée à la fin de la requête.
	 */
	void queryOnFinish();
}
