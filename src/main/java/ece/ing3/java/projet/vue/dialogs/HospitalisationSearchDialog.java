package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.vue.components.ModelInputList;
import ece.ing3.java.projet.vue.components.HospitalisationInputList;

public class HospitalisationSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new HospitalisationInputList();
	}

}
