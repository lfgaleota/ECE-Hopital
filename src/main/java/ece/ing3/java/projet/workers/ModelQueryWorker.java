package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.interfaces.ModelQueryWorkerProvider;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelQueryWorker<M extends Model> extends SwingWorker<List<M>, Object> {
	private ModelQueryWorkerProvider<M> provider;

	public ModelQueryWorker( ModelQueryWorkerProvider<M> provider ) {
		this.provider = provider;
	}

	@Override
	protected List<M> doInBackground() {
		System.out.println( "Update started" );
		try {
			SQLSelect<M> sql = provider.queryCreateSelector();
			Where whereClause = provider.queryModifyWhereClause( provider.getWhereClause() );
			if( whereClause != null ) {
				sql.where( whereClause );
			}
			OrderBy orderByClause = provider.queryModifyOrderByClause( provider.getOrderByClause() );
			if( orderByClause != null ) {
				sql.orderBy( orderByClause );
			}
			List<M> list = sql.findList();
			System.out.println( "Update got : " + list );
			return list;
		} catch( DatabaseException e ) {
			e.printStackTrace();
		}
		System.out.println( "Update failed" );
		return null;
	}

	@Override
	protected void done() {
		try {
			System.out.println( "Set list in model : " + get() );
			if( get() != null ) {
				provider.getTableModel().setList( get() );
			} else {
				Utils.error( Application.get(), "Erreur de récupération des données." );
			}
		} catch( InterruptedException | ExecutionException e ) {
			e.printStackTrace();
			Utils.error( "Erreur de récupération des données." );
		}
		provider.queryOnFinish();
	}
}
