package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Classe modèle d'un {@link javax.swing.JTable} pour l'affichage d'un ensemble d'instance de modèle Service
 * <p>
 * Affiche des informations supplémentaires par jointure
 */
public class ServiceTableModel extends TableModel<Service> {
	private String[] extFieldNames;

	/**
	 * Créer un nouveau modèle de {@link javax.swing.JTable} pour le modèle BDD Service.
	 */
	public ServiceTableModel() {
		super( Service.class );
		List<String> lst = new ArrayList<>( Arrays.asList( Model.getFieldNames( Service.class ) ) );
		lst.add( 4, "directeurPrenom" );
		lst.add( 5, "directeurNom" );
		extFieldNames = lst.toArray( new String[ 0 ] );
	}

	/**
	 * Récupère les noms des attributs du modèle BDD qui sont à afficher dans la {@link javax.swing.JTable}.
	 *
	 * @return Noms des attributs du modèle BDD à afficher
	 */
	@Override
	String[] getFieldNames() {
		return extFieldNames;
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
		Map<String, Object> rowProps = getRows().get( row );

		if( rowProps != null ) {
			String field = getFieldNames()[ col ];
			switch( field ) {
				case "directeurPrenom":
				case "directeurNom":
					return rowProps.get( field );
			}
		}

		return super.getValueAt( row, col );
	}
}
