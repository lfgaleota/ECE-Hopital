package ece.ing3.java.projet.interfaces;

/**
 * Interface d'objet fournissant les informations de configurations et de retour d'un {@link ece.ing3.java.projet.workers.AbstractSimpleModelWorker}, pour la mise à jour de donnée liée à un modèle BDD.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public interface ModelWorkerProvider {
	/**
	 * Méthode appelée à la fin de la requête.
	 */
	void workerOnFinish();
}
