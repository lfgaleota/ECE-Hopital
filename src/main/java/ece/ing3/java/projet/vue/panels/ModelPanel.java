package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.components.ModelList;
import ece.ing3.java.projet.vue.components.ModelToolbar;

import javax.swing.*;
import java.awt.*;

public class ModelPanel<M extends Model> extends JPanel {
	private ModelToolbar toolbar;
	private ModelList<M> list;
	private LoadingPanel loading;
	private JScrollPane listContainer;

	public ModelPanel( TableModel<M> tableModel ) {
		setLayout( new BorderLayout() );

		toolbar = new ModelToolbar();
		list = new ModelList<>( tableModel );
		loading = new LoadingPanel();
		listContainer = new JScrollPane( list );

		add( toolbar, BorderLayout.PAGE_START );
		add( listContainer, BorderLayout.CENTER );
	}

	public ModelToolbar getToolbar() {
		return toolbar;
	}

	public ModelList<M> getList() {
		return list;
	}

	public void inUpdate() {
		remove( listContainer );
		add( loading );
	}

	public void outOfUpdate() {
		remove( loading );
		add( listContainer );
	}
}
