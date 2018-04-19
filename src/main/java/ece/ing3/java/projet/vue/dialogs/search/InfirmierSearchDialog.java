package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.ModelInputList;
import ece.ing3.java.projet.vue.components.InfirmierInputList;

public class InfirmierSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new InfirmierInputList();
	}

}
