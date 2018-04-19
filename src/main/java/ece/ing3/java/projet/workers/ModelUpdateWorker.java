package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.interfaces.ModelWorkerProvider;

public class ModelUpdateWorker extends AbstractSimpleModelWorker {
	private Model model;

	public ModelUpdateWorker( Model model, ModelWorkerProvider provider ) {
		super( provider );
		this.model = model;
	}

	@Override
	protected String getErrorMessage() {
		return "Erreur de mise à jour des données. Vérifiez que les valeurs sont correctes.";
	}

	@Override
	protected String getGenericErrorMessage() {
		return "Erreur de mise à jour des données.";
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		try {
			model.save();
			return true;
		} catch( DatabaseException e ) {
			e.printStackTrace();
		}
		return false;
	}
}
