package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;

public class ChambreTableModel extends TableModel<Chambre> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Chambre c = instances.get( row );

		switch( fieldNames[ col ] ) {
			case "numeroChambre":
				return c.getNumeroChambre();
			case "nombreLits":
				return c.getNombreLits();
			case "numeroSurveillant":
				return c.getNumeroSurveillant();
			case "codeServiceRattache":
				return c.getCodeServiceRattache();
		}

		return null;
	}
}
