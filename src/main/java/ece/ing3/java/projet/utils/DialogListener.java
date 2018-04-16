package ece.ing3.java.projet.utils;

import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;

public interface DialogListener {
	void onDialogSubmitted( ModelSearchDialog dialog );
	void onDialogCancelled( ModelSearchDialog dialog );
}
