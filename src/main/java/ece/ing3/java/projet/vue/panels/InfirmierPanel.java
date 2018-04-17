package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

public class InfirmierPanel extends ModelPanel<Infirmier>{
	public InfirmierPanel(TableModel<Infirmier> tableModel)
	{
		super(Strings.getModel("service"), tableModel);
	}

}
