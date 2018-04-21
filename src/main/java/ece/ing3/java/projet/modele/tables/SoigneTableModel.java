package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.modele.hopital.Soigne;

import java.util.Map;

public class SoigneTableModel extends TableModel<Soigne> {
	public SoigneTableModel() {
		super( Soigne.class );
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Map<String, Object> rowProps = getRows().get( row );

		if( rowProps != null ) {
			switch( getFieldNames()[ col ] ) {
				case "numeroMalade":
					return rowProps.get( "maladePrenom" ) + " " + rowProps.get( "maladeNom" );
				case "numeroDocteur":
					return rowProps.get( "docteurPrenom" ) + " " + rowProps.get( "docteurNom" ) + " (" + rowProps.get( "docteurSpecialite" ) + ")";
			}
		}

		return super.getValueAt( row, col );
	}
}
