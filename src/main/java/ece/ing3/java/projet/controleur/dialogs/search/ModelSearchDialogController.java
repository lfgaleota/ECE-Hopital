package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.controleur.dialogs.BaseModelInputDialogController;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModelSearchDialogController extends BaseModelInputDialogController {
	protected ModelSearchDialogController( BaseModelInputDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( ModelSearchDialog dialog, DialogListener listener ) {
		new ModelSearchDialogController( dialog, listener );
		return dialog;
	}

	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == ( (ModelSearchDialog) dialog ).getReset() ) {
			this.dialog.setValidated( true );
			( (ModelSearchDialog) this.dialog ).setResetFilters( true );
			dialog.dispose();
		}

		super.actionPerformed( actionEvent );
	}
}
