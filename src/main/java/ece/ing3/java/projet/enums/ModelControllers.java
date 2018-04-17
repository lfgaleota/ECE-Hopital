package ece.ing3.java.projet.enums;

import ece.ing3.java.projet.controleur.panels.*;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.util.HashMap;
import java.util.Map;

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
