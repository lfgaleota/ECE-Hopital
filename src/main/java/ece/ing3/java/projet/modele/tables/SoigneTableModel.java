package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;

public class SoigneTableModel extends TableModel<Soigne> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Soigne.class;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Soigne s = instances.get( row );

		switch( fieldNames[ col ] ) {
			case "numeroDocteur":
				return s.getNumeroDocteur();
			case "numeroMalade":
				return s.getNumeroMalade();

		}

		return null;
	}
}
