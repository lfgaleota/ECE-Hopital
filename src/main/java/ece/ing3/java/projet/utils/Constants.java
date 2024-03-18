package ece.ing3.java.projet.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Project constants
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Constants {
	public static final String APP_FOLDER_NAME = "ECE-Hopital";

	public static final String RESOURCE_PATH_CONFIGURATION = "config.properties";

	public static final String RESOURCE_PATH_APPLOGO = "imgs/logo.png";
	public static final String RESOURCE_PATH_CONFIGLOGO = "imgs/config.jpg";
	public static final String RESOURCE_PATH_STATLOGO = "imgs/statistiques.png";
	public static final String RESOURCE_PATH_AJOUTERLOGO = "imgs/ajouter.png";
	public static final String RESOURCE_PATH_MODIFIERLOGO = "imgs/modifier.png";
	public static final String RESOURCE_PATH_RECHERCHERLOGO = "imgs/rechercher.png";
	public static final String RESOURCE_PATH_SUPPRIMERLOGO = "imgs/supprimer.png";
	public static final String RESOURCE_PATH_SERVICELOGO = "imgs/services.png";
	public static final String RESOURCE_PATH_PATIENTLOGO = "imgs/patients.png";
	public static final String RESOURCE_PATH_CHAMBRELOGO = "imgs/chambres.png";
	public static final String RESOURCE_PATH_EMPLOYELOGO = "imgs/employes.png";
	public static final String RESOURCE_PATH_HOSPITALISATIONLOGO = "imgs/hospitalisations.png";
	public static final String RESOURCE_PATH_ICON_ADD = "imgs/ic_add_black_24dp_1x.png";
	public static final String RESOURCE_PATH_ICON_REMOVE = "imgs/ic_delete_forever_black_24dp_1x.png";
	public static final String RESOURCE_PATH_ICON_MODIFY = "imgs/ic_mode_edit_black_24dp_1x.png";
	public static final String RESOURCE_PATH_ICON_STATS = "imgs/ic_pie_chart_black_24dp_1x.png";
	public static final String RESOURCE_PATH_ICON_REFRESH = "imgs/ic_refresh_black_24dp_1x.png";
	public static final String RESOURCE_PATH_ICON_SEARCH = "imgs/ic_search_black_24dp_1x.png";
	public static final String RESOURCE_PATH_HOMELOGO = "imgs/accueil.png";

	public static final String LOGGER_NAME_UI = "UI";
	public static final Integer UI_TEXTFIELD_MAXLENGTH = 255;
	public static final Insets UI_TABCONTAINER_MARGIN  = new Insets( 5, 120, 0, 0 );

	public static final String MODEL_STRINGS_PREFIX = "model.";

	public static final String STRINGS_SUFFIX_PLURAL = ".plural";

	public static final Dimension UI_DIALOGCOMPONENT_PREFERREDSIZE = new Dimension( 200, 24 );

	public static final Insets UI_DIALOG_MARGIN = new Insets( 5, 5, 5, 5 );
	public static final Insets UI_DIALOGSELECT_MARGIN = new Insets( 0, 0, 5, 0 );
	public static final Dimension UI_LOADINGTHROBBER_PREFERREDSIZE = new Dimension( 50, 50 );
	public static final int UI_LOADINGTHROBBER_DELAY = 10;

	public static final Dimension UI_MODEL_LOADING_THROBBER_SIZE = new Dimension( 200, 200 );
	public static final Color UI_MODEL_LOADING_BACKGROUNDCOLOR = Color.WHITE;
	public static final Dimension UI_DIALOGSELECT_SIZE = new Dimension( 500, 300 );
	public static final int UI_INPUTLIST_HGAP = 5;
	public static final int UI_INPUTLIST_VGAP = 5;
	public static final int UI_INPUTLIST_MAXVISIBLEENUMITEMS = 5;
	public static final Border UI_INPUTLIST_INVALIDVALUE_BORDER = BorderFactory.createMatteBorder( 1, 1, 1, 1, Color.RED );

	public final static String DB_EMBEDDED_DATABASE_FILENAME = "hopitaldb.hsql";

	public final static String DB_VALIDATION_QUERY = "select count(numero) from employe;\n" +
			"select count(numero) from docteur;\n" +
			"select count(code) from service;\n" +
			"select count(numero) from infirmier;\n" +
			"select count(no_chambre) from chambre;\n" +
			"select count(numero) from malade;\n" +
			"select count(no_malade) from hospitalisation;\n" +
			"select count(no_docteur) from soigne;\n";
	public final static String DB_EMBEDDED_INIT_IDL_FILENAME = "init.sql";
	public final static String DB_EMBEDDED_EXAMPLE_IDL_FILENAME = "example.sql";
}
