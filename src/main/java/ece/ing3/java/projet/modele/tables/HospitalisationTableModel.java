package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HospitalisationTableModel extends TableModel<Hospitalisation> {
	private String[] extFieldNames;

	public HospitalisationTableModel() {
		super( Hospitalisation.class );
		List<String> lst = new ArrayList<>( Arrays.asList( Model.getFieldNames( Hospitalisation.class ) ) );
		lst.add( 1, "maladePrenom" );
		lst.add( 2, "maladeNom" );
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
				case "maladePrenom":
				case "maladeNom":
					return rowProps.get( field );
			}
		}

		return super.getValueAt( row, col );
	}
}
