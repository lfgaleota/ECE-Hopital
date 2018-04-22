package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.interfaces.ModelWorkerProvider;

import java.util.List;

/**
 * Processus en arrière-plan de suppression d'instances de modèle BDD de la base de donnée
 * <p>
 * Permet de supprimer des instances de modèle BDD en arrière-plan, sans bloquer l'interface.
 */
public class ModelDeleteWorker extends AbstractSimpleModelWorker {
	private List<? extends Model> selectedModels;

	/**
	 * Créer un nouveau processus en arrière-plan de suppression d'instances de modèle BDD de la base de donnée.
	 *
	 * @param selectedModels Instances de modèle BDD à supprimer
	 * @param provider       Objet fournissant les informations de configurations et de retour
	 */
	public ModelDeleteWorker( List<? extends Model> selectedModels, ModelWorkerProvider provider ) {
		super( provider );
		this.selectedModels = selectedModels;
	}

	/**
	 * Récupère le message d'erreur lié à l'impossibilité de supprimer les instances de modèle BDD.
	 *
	 * @return Message d'erreur
	 */
	@Override
	protected String getErrorMessage() {
		return "Erreur de suppression inattendue.";
	}

	/**
	 * Récupère un message d'erreur générique en cas d'impossibilité de récupérer le retour du processus de suppresion.
	 *
	 * @return Message d'erreur générique
	 */
	@Override
	protected String getGenericErrorMessage() {
		return "Erreur de suppression inattendue.";
	}

	/**
	 * Méthode exécutant le processus en arrière-plan.
	 *
	 * @return Succès de l'exécution
	 * @throws Exception Problème inattendu durant l'exécution
	 */
	@Override
	protected Boolean doInBackground() throws Exception {
		boolean success = true;
		for( Model model : selectedModels ) {
			try {
				model.delete();
			} catch( DatabaseException e ) {
				success = false;
				e.printStackTrace();
			}
		}
		return success;
	}
}
