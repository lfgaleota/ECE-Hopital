package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.ModelInputList;
import ece.ing3.java.projet.vue.components.ServiceInputList;

public class ServiceSearchDialog extends ModelSearchDialog{
	@Override
	public ModelInputList build() {
		return new ServiceInputList();
	}
}
