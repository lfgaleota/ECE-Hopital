package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.inputlists.HospitalisationInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class HospitalisationSearchDialog extends ModelSearchDialog {
	@Override
	public ModelInputList build() {
		return new HospitalisationInputList( true, this );
	}

}
