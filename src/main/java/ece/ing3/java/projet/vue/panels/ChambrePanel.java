package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

public class ChambrePanel extends ModelPanel<Chambre>{
		public ChambrePanel(TableModel<Chambre> tableModel)
		{
			super(Strings.getModel("chambre"), tableModel );
		}
}