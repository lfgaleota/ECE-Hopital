package ece.ing3.java.projet.controleur.dialogs;

<<<<<<< HEAD
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.EmployeSearchDialog;
=======
import static ece.ing3.java.projet.controleur.dialogs.ModelSearchDialogController.createDialog;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.MaladeSearchDialog;
>>>>>>> 26ff9c0f1b317a64c4c1552132b8a42dcd3b3d3a
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;

public class EmployeSearchDialogController extends ModelSearchDialogController {
	private EmployeSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
<<<<<<< HEAD
		return createDialog( new EmployeSearchDialog(), listener );
=======
		return createDialog( new MaladeSearchDialog(), listener );
>>>>>>> 26ff9c0f1b317a64c4c1552132b8a42dcd3b3d3a
	}
}
