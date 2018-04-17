package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

public class ServicePanel extends ModelPanel<Service> {
	public ServicePanel( TableModel<Service> tableModel ) {
		super( Strings.getModel( "service" ), tableModel );
	}
}
