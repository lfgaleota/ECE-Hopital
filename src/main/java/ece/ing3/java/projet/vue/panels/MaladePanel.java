package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

public class MaladePanel extends ModelPanel<Malade> {
	public MaladePanel( TableModel<Malade> tableModel ) {
		super( Strings.getModel( "malade" ), tableModel );
	}
}
