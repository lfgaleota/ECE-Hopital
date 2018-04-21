package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServiceTableModel extends TableModel<Service> {
	private String[] extFieldNames;

	public ServiceTableModel() {
		super( Service.class );
		List<String> lst = new ArrayList<>( Arrays.asList( Model.getFieldNames( Service.class ) ) );
		lst.add( 4, "directeurPrenom" );
		lst.add( 5, "directeurNom" );
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
				case "directeurPrenom":
				case "directeurNom":
					return rowProps.get( field );
			}
		}

		return super.getValueAt( row, col );
	}
}
