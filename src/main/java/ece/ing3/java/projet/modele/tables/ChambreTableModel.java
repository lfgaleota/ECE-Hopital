package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChambreTableModel extends TableModel<Chambre> {
	private String[] extFieldNames;

	public ChambreTableModel() {
		super( Chambre.class );
		List<String> lst = new ArrayList<>( Arrays.asList( Model.getFieldNames( Chambre.class ) ) );
		lst.add( 4, "surveillantPrenom" );
		lst.add( 5, "surveillantNom" );
		extFieldNames = lst.toArray( new String[ 0 ] );
	}

	@Override
	String[] getFieldNames() {
		return extFieldNames;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Map<String, Object> rowProps = getRows().get( row );

		if( rowProps != null ) {
			String field = getFieldNames()[ col ];
			switch( field ) {
				case "surveillantPrenom":
				case "surveillantNom":
					return rowProps.get( field );
			}
		}

		return super.getValueAt( row, col );
	}
}
