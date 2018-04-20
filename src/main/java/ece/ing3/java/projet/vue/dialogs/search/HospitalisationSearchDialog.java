package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;
import ece.ing3.java.projet.vue.components.inputlists.HospitalisationInputList;

public class HospitalisationSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new HospitalisationInputList( true );
	}

}
