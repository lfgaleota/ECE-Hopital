package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Infirmier;

public class InfirmierTableModel  extends TableModel<Infirmier> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Infirmier.class;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Infirmier i = instances.get( row );

		switch( fieldNames[ col ] ) {
			case "numero":
				return i.getNumero();
			case "nom":
				return i.getNom();
			case "prenom":
				return i.getPrenom();
			case "adresse":
				return i.getAdresse();
			case "numeroTelephone":
				return i.getNumeroTelephone();
			case "rotation":
				return i.getRotation();
			case "salaire":
				return i.getSalaire();
			case "codeService":
				return i.getCodeService();
		}

		return null;
	}

}
