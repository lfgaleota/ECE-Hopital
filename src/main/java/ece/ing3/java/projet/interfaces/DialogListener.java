package ece.ing3.java.projet.interfaces;

import javax.swing.*;

public interface DialogListener {
	void onDialogSubmitted( JDialog dialog );
	void onDialogCancelled( JDialog dialog );
}
