package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.inputlists.ChambreInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class ChambreSearchDialog extends ModelSearchDialog {
	@Override
	public ModelInputList build() {
		return new ChambreInputList( true, this );
	}

}
