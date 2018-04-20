package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;
import ece.ing3.java.projet.vue.components.inputlists.InfirmierInputList;

public class InfirmierSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new InfirmierInputList( true, this );
	}

}
