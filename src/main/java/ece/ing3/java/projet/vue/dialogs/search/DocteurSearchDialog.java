package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.ModelInputList;
import ece.ing3.java.projet.vue.components.DocteurInputList;

public class DocteurSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new DocteurInputList();
	}
}
