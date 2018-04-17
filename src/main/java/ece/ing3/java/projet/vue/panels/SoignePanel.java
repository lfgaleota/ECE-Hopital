package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

public class SoignePanel extends ModelPanel<Soigne>{
		public SoignePanel (TableModel<Soigne> tableModel)
		{
			super (Strings.getModel("soigne"), tableModel);
		}
}
