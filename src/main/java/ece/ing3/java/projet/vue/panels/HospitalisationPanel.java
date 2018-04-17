package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

public class HospitalisationPanel extends ModelPanel<Hospitalisation>{
		public HospitalisationPanel(TableModel<Hospitalisation> tableModel)
		{
			super ( Strings.getModel("hospitalisation") , tableModel);
		}
}