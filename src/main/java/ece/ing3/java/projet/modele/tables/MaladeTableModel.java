package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Malade;

public class MaladeTableModel extends TableModel<Malade> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Malade.class;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Malade m = instances.get( row );

		switch( fieldNames[ col ] ) {
			case "numero":
				return m.getNumero();
			case "nom":
				return m.getNom();
			case "prenom":
				return m.getPrenom();
			case "adresse":
				return m.getAdresse();
			case "numeroTelephone":
				return m.getNumeroTelephone();
			case "mutuelle":
				return m.getMutuelle();
		}

		return null;
	}
}
