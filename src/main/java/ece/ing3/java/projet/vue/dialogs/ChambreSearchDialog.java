package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.vue.components.ChambreInputList;
import ece.ing3.java.projet.vue.components.ModelInputList;

public class ChambreSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new ChambreInputList();
	}

}
