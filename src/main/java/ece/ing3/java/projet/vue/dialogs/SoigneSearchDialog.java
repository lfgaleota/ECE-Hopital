package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.vue.components.ModelInputList;
import ece.ing3.java.projet.vue.components.SoigneInputList;

public class SoigneSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new SoigneInputList();
	}
}
