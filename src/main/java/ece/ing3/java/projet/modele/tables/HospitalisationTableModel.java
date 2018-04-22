package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Classe modèle d'un {@link javax.swing.JTable} pour l'affichage d'un ensemble d'instance de modèle Hospitalisation
 * <p>
 * Affiche des informations supplémentaires par jointure
 */
public class HospitalisationTableModel extends TableModel<Hospitalisation> {
	private String[] extFieldNames;

	/**
	 * Créer un nouveau modèle de {@link javax.swing.JTable} pour le modèle BDD Hospitalisation.
	 */
	public HospitalisationTableModel() {
		super( Hospitalisation.class );
		List<String> lst = new ArrayList<>( Arrays.asList( Model.getFieldNames( Hospitalisation.class ) ) );
		lst.add( 1, "maladePrenom" );
		lst.add( 2, "maladeNom" );
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
				case "maladePrenom":
				case "maladeNom":
					return rowProps.get( field );
			}
		}

		return super.getValueAt( row, col );
	}
}
