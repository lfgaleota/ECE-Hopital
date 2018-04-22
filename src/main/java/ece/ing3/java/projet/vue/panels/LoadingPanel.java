package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.vue.components.LoadingThrobber;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau d'attente affichant une îcone de chargement.
 * <p>
 * Déstinée à être affichée à la place d'un panneau qui serait en cours de traitement.
 */
public class LoadingPanel extends JPanel {
	/**
	 * Créer un nouveau panneau de chargement.
	 */
	public LoadingPanel() {
		setLayout( new GridBagLayout() );
		setBackground( Constants.UI_MODEL_LOADING_BACKGROUNDCOLOR );
	}

	/**
	 * Démarre l'animation de l'îcone de chargement.
	 */
	public void start() {
		LoadingThrobber loadingThrobber = new LoadingThrobber();
		loadingThrobber.setPreferredSize( Constants.UI_MODEL_LOADING_THROBBER_SIZE );
		loadingThrobber.setBackground( Constants.UI_MODEL_LOADING_BACKGROUNDCOLOR );
		add( loadingThrobber, new GridBagConstraints() );
	}

	/**
	 * Arrête l'animation de l'îcone de chargement.
	 */
	public void stop() {
		removeAll();
	}
}
