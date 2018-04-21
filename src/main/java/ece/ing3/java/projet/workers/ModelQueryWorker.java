package ece.ing3.java.projet.workers;

import ece.ing3.java.projet.controleur.panels.SoignePanelController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.interfaces.ModelQueryWorkerProvider;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ModelQueryWorker<M extends Model> extends SwingWorker<Map.Entry<List<M>, List<Map<String, Object>>>, Object> {
	private ModelQueryWorkerProvider<M> provider;

	public ModelQueryWorker( ModelQueryWorkerProvider<M> provider ) {
		this.provider = provider;
	}

	@Override
	protected Map.Entry<List<M>, List<Map<String, Object>>> doInBackground() {
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
			ResultSet rs = sql.findRaw();
			ResultSetHandler<M> h = new BeanHandler<>( provider.getModelClass() );
			List<M> list = new ArrayList<>();
			List<Map<String, Object>> rows = new ArrayList<>();
			while( !rs.isAfterLast() ) {
				M s = h.handle( rs );
				if( s != null ) {
					list.add( s );
					if( !rs.isAfterLast() ) {
						Map<String, Object> rowValues = new HashMap<>();
						ResultSetMetaData rsmd = rs.getMetaData();
						for( int i = 1; i <= rsmd.getColumnCount(); i++ ) {
							rowValues.put( rsmd.getColumnLabel( i ), rs.getObject( i ) );
						}
						rows.add( rowValues );
					}
				} else {
					try {
						if( !rs.next() ) {
							break;
						}
					} catch( SQLException e ) {
						break;
					}
				}
			}
			System.out.println( "Update got : " + list );
			return new AbstractMap.SimpleEntry<>( list, rows );
		} catch( DatabaseException | SQLException e ) {
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
