package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.components.ModelList;
import ece.ing3.java.projet.vue.components.ModelToolbar;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle BDD
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 *
 * @param <M> Type de modèle BDD
 */
public class ModelPanel<M extends Model> extends JPanel {
	private ModelToolbar toolbar;
	private ModelList<M> list;
	private LoadingPanel loading;
	private JScrollPane listContainer;

	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle BDD.
	 *
	 * @param modelName  Nom du modèle BDD associé, qui sera utilisé pour récupérer le texte à afficher
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public ModelPanel( String modelName, TableModel<M> tableModel ) {
		setLayout( new BorderLayout() );

		toolbar = new ModelToolbar( modelName );
		list = new ModelList<>( tableModel );
		loading = new LoadingPanel();
		listContainer = new JScrollPane( list );

		add( toolbar, BorderLayout.PAGE_START );
		add( listContainer, BorderLayout.CENTER );
	}

	/**
	 * Récupère la barre d'outil du panneau, comportant les boutons d'actions.
	 *
	 * @return Barre d'outil du panneau
	 */
	public ModelToolbar getToolbar() {
		return toolbar;
	}

	/**
	 * Récupère la table d'affichage des instances sous-jacente.
	 *
	 * @return Table d'affichage des instances sous-jacente
	 */
	public ModelList<M> getList() {
		return list;
	}

	/**
	 * Indique que le panneau est en traitement et empêche l'interaction.
	 */
	public void inUpdate() {
		remove( listContainer );
		add( loading, BorderLayout.CENTER );
		revalidate();
		repaint();
		toolbar.setEnabled( false );
		loading.start();
	}

	/**
	 * Indique que le panneau n'est plus en traitement et ré-autorise l'interaction.
	 */
	public void outOfUpdate() {
		loading.stop();
		remove( loading );
		add( listContainer, BorderLayout.CENTER );
		toolbar.setEnabled( true );
		revalidate();
		repaint();
	}
}
