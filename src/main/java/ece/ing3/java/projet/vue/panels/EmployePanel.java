package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

public class EmployePanel extends ModelPanel<Employe> {
	public EmployePanel( TableModel<Employe> tableModel ) {
		super( Strings.getModel( "employe" ), tableModel );
	}
}
