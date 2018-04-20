package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.interfaces.ModelWorkerProvider;

public class ModelUpdateWorker extends AbstractSimpleModelWorker {
	private Model model;
	private boolean isAdd;
	private boolean alreadyExist;

	public ModelUpdateWorker( Model model, ModelWorkerProvider provider, boolean isAdd ) {
		super( provider );
		this.model = model;
		this.isAdd = isAdd;
		this.alreadyExist = false;
	}

	@Override
	protected String getErrorMessage() {
		return ( alreadyExist ? "Une entrée de même identifiant existe déjà." : "Erreur de mise à jour des données. Vérifiez que les valeurs sont correctes." );
	}

	@Override
	protected String getGenericErrorMessage() {
		return "Erreur de mise à jour des données.";
	}

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
