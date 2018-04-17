package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Employe;

public class EmployeTableModel extends TableModel<Employe> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Employe.class;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Employe e = instances.get( row );

		switch( fieldNames[ col ] ) {
			case "numero":
				return e.getNumero();
			case "nom":
				return e.getNom();
			case "prenom":
				return e.getPrenom();
			case "adresse":
				return e.getAdresse();
			case "numeroTelephone":
				return e.getNumeroTelephone();

		}

		return null;
	}
}
