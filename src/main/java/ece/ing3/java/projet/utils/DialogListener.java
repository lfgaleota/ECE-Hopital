package ece.ing3.java.projet.utils;

import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;

import javax.swing.*;

public interface DialogListener {
	void onDialogSubmitted( JDialog dialog );
	void onDialogCancelled( JDialog dialog );
}
