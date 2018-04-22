package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.interfaces.ModelWorkerProvider;
import ece.ing3.java.projet.utils.Utils;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Base de classe exécutant un processus de mise à jour des données d'instances de modèle BDD en arrière-plan
 * <p>
 * Se charge de la gestion des erreurs et gestion des {@link ModelWorkerProvider}
 */
public abstract class AbstractSimpleModelWorker extends SwingWorker<Boolean, Object> {
	/**
	 * Objet fournissant les informations de configurations et de retour.
	 */
	protected ModelWorkerProvider provider;

	/**
	 * Créer un nouveau processus de mise à jour des données d'instances de modèle BDD.
	 *
	 * @param provider Objet fournissant les informations de configurations et de retour
	 */
	public AbstractSimpleModelWorker( ModelWorkerProvider provider ) {
		this.provider = provider;
	}

	/**
	 * Récupère le message d'erreur lié à l'impossibilité d'exécuter le processus à son terme, par exemple le message d'une exception.
	 *
	 * @return Message d'erreur
	 */
	protected abstract String getErrorMessage();

	/**
	 * Récupère un message d'erreur générique en cas d'impossibilité de récupérer le retour du processus exécuté à son terme.
	 *
	 * @return Message d'erreur générique
	 */
	protected abstract String getGenericErrorMessage();

	/**
	 * Méthode appelée à la fin de l'exécution du processus, gère de manière élémentaire les erreurs.
	 */
	@Override
	protected void done() {
		try {
			if( !get() ) {
				Utils.error( getErrorMessage() );
			}
		} catch( InterruptedException | ExecutionException e ) {
			Utils.error( getGenericErrorMessage() );
			e.printStackTrace();
		}
		provider.workerOnFinish();
	}
}
