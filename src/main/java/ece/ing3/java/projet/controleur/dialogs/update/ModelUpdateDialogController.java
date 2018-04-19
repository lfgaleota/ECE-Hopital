package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.controleur.dialogs.BaseModelInputDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModelUpdateDialogController extends BaseModelInputDialogController {
	protected ModelUpdateDialogController( BaseModelInputDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelUpdateDialog<? extends Model> createDialog( ModelUpdateDialog<? extends Model> dialog, DialogListener listener ) {
		new ModelUpdateDialogController( dialog, listener );
		return dialog;
	}
}
