package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

public class DocteurPanel extends ModelPanel<Docteur>{
	public DocteurPanel(TableModel<Docteur> tableModel)
	{
		super(Strings.getModel("docteur"), tableModel);
	}
}
