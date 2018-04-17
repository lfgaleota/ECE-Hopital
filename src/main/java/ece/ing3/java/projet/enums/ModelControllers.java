package ece.ing3.java.projet.enums;

import ece.ing3.java.projet.controleur.panels.MaladePanelController;
import ece.ing3.java.projet.controleur.panels.ServicePanelController;
import ece.ing3.java.projet.controleur.panels.EmployePanelController;
import ece.ing3.java.projet.controleur.panels.DocteurPanelController;
import ece.ing3.java.projet.controleur.panels.InfirmierPanelController;
import ece.ing3.java.projet.controleur.panels.ChambrePanelController;
import ece.ing3.java.projet.controleur.panels.HospitalisationPanelController;
import ece.ing3.java.projet.controleur.panels.SoignePanelController;
import ece.ing3.java.projet.controleur.panels.ModelPanelController;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.util.HashMap;
import java.util.Map;

public enum ModelControllers {
	MALADE( MaladePanelController.get() ),
	SERVICE( ServicePanelController.get()  ),
	EMPLOYE(EmployePanelController.get( )),
	DOCTEUR(DocteurPanelController.get()),
	INFIRMIER(InfirmierPanelController.get() ),
	CHAMBRE(ChambrePanelController.get()),
	HOSPITALISATION(HospitalisationPanelController.get()),
	SOIGNE(SoignePanelController.get());
	

	private String prettyName;
	private ModelPanelController panelController;

	private static Map<ModelPanel, ModelPanelController> panelControllers = new HashMap<>();

	static {
		for( ModelControllers modelController : values() ) {
			panelControllers.put( modelController.getPanelController().getPanel(), modelController.getPanelController() );
		}
	}

	ModelControllers( ModelPanelController panelController ) {
		this.prettyName = Strings.get( Constants.MODEL_STRINGS_PREFIX + name() );
		this.panelController = panelController;
	}

	public String getPrettyName() {
		return prettyName;
	}

	public ModelPanelController getPanelController() {
		return panelController;
	}

	public static ModelPanelController getPanelControllerFromModelPanel( ModelPanel panel ) {
		return panelControllers.get( panel );
	}
}
