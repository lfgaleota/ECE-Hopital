package ece.ing3.java.projet.vue.components;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.tables.TableModel;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Liste d'affichage de modèle BDD, sous forme de table
 *
 * @param <M> Type du modèle BDD considéré
 */
public class ModelList<M extends Model> extends JTable {
	private TableModel<M> tableModel;

	/**
	 * Créer une nouvelle liste, utilisant le modèle de table {@link TableModel} fourni en paramètre comme source de donnée.
	 *
	 * @param tableModel Modèle de table à utiliser
	 */
	public ModelList( TableModel<M> tableModel ) {
		super( tableModel );
		this.tableModel = tableModel;
		setAutoCreateRowSorter( true );
	}

	/**
	 * Récupère l'instance de modèle BDD correspondant à la ligne sléectionnée dans la table, ou à la première ligne sélectionnée s'il y en a plusieurs.
	 *
	 * @return Instance de modèle BDD correspondant à la ligne sélectionnée
	 */
	public M getSelected() {
		if( getSelectedRow() > -1 ) {
			return tableModel.getList().get( getSelectedRow() );
		}

		return null;
	}

	/**
	 * Récupère l'ensemble des instance de modèle BDD correspondant aux lignes sélectionnées.
	 *
	 * @return Liste d'instances de modèle BDD correspondant aux lignes sélectionnées
	 */
	public List<M> getSelecteds() {
		M instance;
		List<M> list = new LinkedList<>();

		for( int row : getSelectedRows() ) {
			instance = tableModel.getList().get( row );

			if( instance != null ) {
				list.add( instance );
			}
		}

		return list;
	}
}
