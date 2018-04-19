package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.MaladeInputList;
import ece.ing3.java.projet.vue.components.ModelInputList;

public class MaladeSearchDialog extends ModelSearchDialog {
	@Override
	public ModelInputList build() {
		return new MaladeInputList();
	}
}
