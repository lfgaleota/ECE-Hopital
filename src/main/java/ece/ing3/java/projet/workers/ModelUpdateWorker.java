package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.interfaces.ModelWorkerProvider;

/**
 * Processus en arrière-plan de mise à jour d'instances de modèle BDD de la base de donnée
 * <p>
 * Permet de mettre à jour des instances de modèle BDD en arrière-plan, sans bloquer l'interface.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ModelUpdateWorker extends AbstractSimpleModelWorker {
	private Model model;
	private boolean isAdd;
	private boolean alreadyExist;

	/**
	 * Créer un nouveau processus en arrière-plan de mise à jour d'instances de modèle BDD de la base de donnée.
	 *
	 * @param model    Instance de modèle BDD à mettre à jour
	 * @param provider Objet fournissant les informations de configurations et de retour
	 * @param isAdd    {@code true} si on s'attend à un ajout plutôt qu'à une mise à jour d'une entrée existante
	 */
	public ModelUpdateWorker( Model model, ModelWorkerProvider provider, boolean isAdd ) {
		super( provider );
		this.model = model;
		this.isAdd = isAdd;
		this.alreadyExist = false;
	}

	/**
	 * Récupère le message d'erreur lié à l'impossibilité de mettre à jour les instances de modèle BDD.
	 *
	 * @return Message d'erreur
	 */
	@Override
	protected String getErrorMessage() {
		return ( alreadyExist ? "Une entrée de même identifiant existe déjà." : "Erreur de mise à jour des données. Vérifiez que les valeurs sont correctes." );
	}

	/**
	 * Récupère un message d'erreur générique en cas d'impossibilité de récupérer le retour du processus de mise à jour.
	 *
	 * @return Message d'erreur générique
	 */
	@Override
	protected String getGenericErrorMessage() {
		return "Erreur de mise à jour des données.";
	}

	/**
	 * Méthode exécutant le processus en arrière-plan.
	 *
	 * @return Succès de l'exécution
	 * @throws Exception Problème inattendu durant l'exécution
	 */
	@Override
	protected Boolean doInBackground() throws Exception {
		try {
			if( model.exists() && isAdd ) {
				this.alreadyExist = true;
				return false;
			}

			model.save();
			return true;
		} catch( DatabaseException e ) {
			e.printStackTrace();
		}
		return false;
	}
}
