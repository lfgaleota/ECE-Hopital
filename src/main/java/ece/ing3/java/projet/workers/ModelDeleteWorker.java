package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.interfaces.ModelWorkerProvider;

import java.util.List;

public class ModelDeleteWorker extends AbstractSimpleModelWorker {
	private List<? extends Model> selectedModels;

	public ModelDeleteWorker( List<? extends Model> selectedModels, ModelWorkerProvider provider ) {
		super( provider );
		this.selectedModels = selectedModels;
	}

	@Override
	protected String getErrorMessage() {
		return "Erreur de suppression inattendue.";
	}

	@Override
	protected String getGenericErrorMessage() {
		return "Erreur de suppression inattendue.";
	}

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
