package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Panneau à onglets de l'application
 * <p>
 * Contient tous les panneaux de l'application, comme les {@link ModelPanel} ou {@link StatistiquesPanel}.
 */
public class TabPanel extends JTabbedPane {
	private static Insets oldInsets;
	private Image logo;

	private TabPanel() {
		super( JTabbedPane.LEFT );

		try {
			logo = Utils.getImageResource( Constants.RESOURCE_PATH_APPLOGO );
		} catch( IOException e ) {
			logo = null;
			throw new RuntimeException( "Logo not found?\n" + e.getLocalizedMessage() );
		}
	}

	/**
	 * Les ressources suivantes ont été utilisées pour la conception de cette méthode :
	 * - https://stackoverflow.com/a/5184026
	 * - http://www.java2s.com/Tutorial/Java/0240__Swing/CustomizingaJTabbedPaneLookandFeel.htm
	 */
	private static void prepare() {
		oldInsets = UIManager.getDefaults().getInsets( "TabbedPane.tabAreaInsets" );
		UIManager.getDefaults().put( "TabbedPane.tabAreaInsets", Constants.UI_TABCONTAINER_MARGIN );
	}

	private static void finish() {
		UIManager.getDefaults().put( "TabbedPane.tabAreaInsets", oldInsets );
	}

	/**
	 * Créer un nouveau panneau à onglets
	 *
	 * @return Panneau à onglets
	 */
	public static TabPanel create() {
		prepare();
		TabPanel pan = new TabPanel();
		finish();
		return pan;
	}

	/**
	 * Dessine le fond du panneau
	 * <p>
	 * Les ressources suivantes ont été utilisées pour la conception de cette méthode :
	 * - http://www.java2s.com/Tutorials/Java/Swing_How_to/JTabbedPane/Change_Java_JTabbedPane_Inset_Color.htm
	 *
	 * @param g Ensemble graphique sur lequel dessiner
	 */
	@Override
	protected void paintComponent( Graphics g ) {
		super.paintComponent( g );
		Graphics2D g2d = (Graphics2D) g;
		if( logo != null ) {
			int maxTabWidth = 0, tabWidth;
			for( int i = 0; i < getTabCount(); i++ ) {
				tabWidth = getBoundsAt( i ).width;
				if( maxTabWidth < tabWidth ) {
					maxTabWidth = tabWidth;
				}
			}
			int size = ( maxTabWidth - Constants.UI_TABCONTAINER_MARGIN.top );
			Image scaledLogo = logo.getScaledInstance( size, size, Image.SCALE_DEFAULT );
			g2d.drawImage( scaledLogo, Constants.UI_TABCONTAINER_MARGIN.top, getHeight() - size - Constants.UI_TABCONTAINER_MARGIN.top, null );
		}
	}
}
