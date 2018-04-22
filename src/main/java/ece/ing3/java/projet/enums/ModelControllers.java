package ece.ing3.java.projet.enums;

import ece.ing3.java.projet.controleur.panels.*;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.util.HashMap;
import java.util.Map;

/**
 * Ensemble des contrôleurs de panneau principaux de modèles.
 * Utilisés pour remplir automatiquement les onglets de l'application.
 */
public enum ModelControllers {
	MALADE( new MaladePanelController() ),
	SERVICE( new ServicePanelController() ),
	EMPLOYE( new EmployePanelController() ),
	DOCTEUR( new DocteurPanelController() ),
	INFIRMIER( new InfirmierPanelController() ),
	CHAMBRE( new ChambrePanelController() ),
	HOSPITALISATION( new HospitalisationPanelController() ),
	SOIGNE( new SoignePanelController() );

	private String prettyName;
	private ModelPanelController panelController;

	ModelControllers( ModelPanelController panelController ) {
		this.prettyName = Strings.get( Constants.MODEL_STRINGS_PREFIX + name() );
		this.panelController = panelController;
	}

	/**
	 * Récupère le nom associé au contrôleur et au panneau
	 *
	 * @return Nom associé à afficher
	 */
	public String getPrettyName() {
		return prettyName;
	}

	/**
	 * Récupère le contrôleur associé à la valeur
	 *
	 * @return Contrôleur associé
	 */
	public ModelPanelController getPanelController() {
		return panelController;
	}
}
