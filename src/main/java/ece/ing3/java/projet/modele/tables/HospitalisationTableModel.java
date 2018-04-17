package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;

public class HospitalisationTableModel extends TableModel<Hospitalisation> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Hospitalisation h = instances.get( row );

		switch( fieldNames[ col ] ) {
			case "numeroMalade":
				return h.getNumeroMalade();
			case "codeService":
				return h.getCodeService();
			case "numeroChambre":
				return h.getNumeroChambre();
			case "numeroLit":
				return h.getNumeroLit();
		}

		return null;
	}
}
