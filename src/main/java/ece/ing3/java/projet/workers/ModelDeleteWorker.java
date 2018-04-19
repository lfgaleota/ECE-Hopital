package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;

import javax.swing.*;
import java.util.List;

public class ModelDeleteWorker extends SwingWorker<Boolean, Object> {
	private List<? extends Model> selectedModels;

	public ModelDeleteWorker( List<? extends Model> selectedModels ) {
		this.selectedModels = selectedModels;
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
