package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Classe modèle d'un {@link javax.swing.JTable} pour l'affichage d'un ensemble d'instance de modèle BDD générique
 *
 * @param <M>v Modèle BDD considéré
 */
public class TableModel<M extends Model> extends AbstractTableModel {
	private Class<? extends Model> modelClass;
	private List<M> instances;
	private List<Map<String, Object>> rows;

	private String[] fieldNames;

	/**
	 * Créer un nouveau modèle de {@link javax.swing.JTable} pour un modèle BDD donné.
	 *
	 * @param modelClass Classe du modèle BDD
	 */
	public TableModel( Class<? extends Model> modelClass ) {
		this.modelClass = modelClass;
		fieldNames = Model.getFieldNames( getModelClass() );
	}

	/**
	 * Récupère la liste d'instances utilisée pour l'affichage.
	 *
	 * @return Liste d'instances de modèle BDD
	 */
	public List<M> getList() {
		return instances;
	}

	/**
	 * Modifie la liste d'instances utilisée pour l'affichage, et par conséquent ce qu'affiche la {@link javax.swing.JTable} qui l'utilise.
	 *
	 * @param instances Pair constituée:
	 *                  - d'une liste d'instance de modèle BDD
	 *                  - d'un ensemble d'associations colonne-valeur de la colonne extrait directement du résultat de la requête de récupération de donnée SQL
	 */
	public void setList( Map.Entry<List<M>, List<Map<String, Object>>> instances ) {
		this.instances = instances.getKey();
		this.rows = instances.getValue();
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	/**
	 * Récupère les noms des attributs du modèle BDD qui sont à afficher dans la {@link javax.swing.JTable}.
	 *
	 * @return Noms des attributs du modèle BDD à afficher
	 */
	String[] getFieldNames() {
		return fieldNames;
	}

	/**
	 * Récupère les associations colonne-valeur de la colonne extrait de la requête SQL.
	 *
	 * @return Ensemble d'associations colonne-valeur de la colonne
	 */
	List<Map<String, Object>> getRows() {
		return rows;
	}

	/**
	 * Récupère le nom de colonne à afficher dans la table à l'index fourni.
	 *
	 * @param col Index de la clonne
	 * @return Nom associé
	 */
	@Override
	public String getColumnName( int col ) {
		return Strings.get( Constants.MODEL_STRINGS_PREFIX + getFieldNames()[ col ] );
	}

	/**
	 * Récupère la classe du type des valeurs de la colonne d'index spécifiée.
	 *
	 * @param columnIndex Index de la colonne
	 * @return Classe associée
	 */
	@Override
	public Class<?> getColumnClass( int columnIndex ) {
		PropertyDescriptor desc = Model.getPropertyDescriptor( getModelClass(), getFieldNames()[ columnIndex ] );
		return ( desc != null ? desc.getPropertyType() : Object.class );
	}

	/**
	 * Récupère le nombre de ligne de la table.
	 *
	 * @return Nombre de ligne
	 */
	@Override
	public int getRowCount() {
		return instances != null ? instances.size() : 0;
	}

	/**
	 * Récupère le nombre de colonne de la table.
	 *
	 * @return Nombre de colonne
	 */
	@Override
	public int getColumnCount() {
		return getFieldNames().length;
	}

	/**
	 * Récupère la valeur à afficher dans la table à la ligne et colonne indiquée.
	 *
	 * @param row Index de la ligne
	 * @param col Index de la colonne
	 * @return Valeur associée à afficher
	 */
	@Override
	public Object getValueAt( int row, int col ) {
		M s = instances.get( row );

		try {
			return PropertyUtils.getSimpleProperty( s, getFieldNames()[ col ] );
		} catch( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Récupère la classe associé au modèle BDD considéré.
	 *
	 * @return Classe du modèle BDD
	 */
	public Class<? extends Model> getModelClass() {
		return modelClass;
	}
}
