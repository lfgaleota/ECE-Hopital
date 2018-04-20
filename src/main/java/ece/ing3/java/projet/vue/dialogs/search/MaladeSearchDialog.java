package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.inputlists.MaladeInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class MaladeSearchDialog extends ModelSearchDialog {
	@Override
	public ModelInputList build() {
		return new MaladeInputList( true );
	}
}
