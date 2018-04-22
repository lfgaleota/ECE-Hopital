package ece.ing3.java.projet.interfaces;

import javax.swing.*;

/**
 * Interface d'objet qui écoute l'issue de sélection d'une fenêtre de dialogue.
 */
public interface DialogListener {
	/**
	 * Méthode de retour appelée quand la boîte de dialogue est validé.
	 * @param dialog Boîte de dialogue validée
	 */
	void onDialogSubmitted( JDialog dialog );
	/**
	 * Méthode de retour appelée quand la boîte de dialogue est annulée.
	 * @param dialog Boîte de dialogue annulée
	 */
	void onDialogCancelled( JDialog dialog );
}
