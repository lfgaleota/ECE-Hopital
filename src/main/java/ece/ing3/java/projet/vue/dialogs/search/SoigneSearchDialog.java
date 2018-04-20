package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;
import ece.ing3.java.projet.vue.components.inputlists.SoigneInputList;

public class SoigneSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new SoigneInputList( true, this );
	}
}
