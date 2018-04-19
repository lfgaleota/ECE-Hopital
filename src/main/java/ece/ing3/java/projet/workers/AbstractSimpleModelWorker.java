package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.interfaces.ModelWorkerProvider;
import ece.ing3.java.projet.utils.Utils;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public abstract class AbstractSimpleModelWorker extends SwingWorker<Boolean, Object> {
	protected ModelWorkerProvider provider;

	public AbstractSimpleModelWorker( ModelWorkerProvider provider ) {
		this.provider = provider;
	}

	protected abstract String getErrorMessage();

	protected abstract String getGenericErrorMessage();

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
