package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.hopital.Malade;

public class DocteurTableModel extends TableModel<Docteur> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Docteur.class;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		Docteur d = instances.get( row );

		switch( fieldNames[ col ] ) {
			case "numero":
				return d.getNumero();
			case "nom":
				return d.getNom();
			case "prenom":
				return d.getPrenom();
			case "adresse":
				return d.getAdresse();
			case "tel":
				return d.getNumeroTelephone();
			case "specialite":
				return d.getSpecialite();
		}

		return null;
	}
}
