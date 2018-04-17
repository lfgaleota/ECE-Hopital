package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;

public class ServiceTableModel  extends TableModel<Service> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Service.class;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Service s = instances.get( row );

		switch( fieldNames[ col ] ) {
			case "code":
				return s.getCode();
			case "nom":
				return s.getNom();
			case "batiment":
				return s.getBatiment();
			case "numeroDirecteur":
				return s.getNumeroDirecteur();
	
		}

		return null;
	}
}

