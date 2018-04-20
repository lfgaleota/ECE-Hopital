package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.vue.components.LoadingThrobber;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JPanel {
	public LoadingPanel() {
		setLayout( new GridBagLayout() );
		setBackground( Constants.UI_MODEL_LOADING_BACKGROUNDCOLOR );
	}

	public void start() {
		LoadingThrobber loadingThrobber = new LoadingThrobber();
		loadingThrobber.setPreferredSize( Constants.UI_MODEL_LOADING_THROBBER_SIZE );
		loadingThrobber.setBackground( Constants.UI_MODEL_LOADING_BACKGROUNDCOLOR );
		add( loadingThrobber, new GridBagConstraints() );
	}

	public void stop() {
		removeAll();
	}
}
