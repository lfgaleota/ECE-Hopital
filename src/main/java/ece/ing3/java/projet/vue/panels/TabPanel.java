package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TabPanel extends JTabbedPane {
	private static Insets oldInsets;
	private Image logo;

	private TabPanel() {
		super( JTabbedPane.LEFT );

		try {
			logo = ImageIO.read( Utils.getResourcePath( Constants.RESOURCE_PATH_APPLOGO ) );
		} catch( FileNotFoundException e ) {
			logo = null;
			Utils.getUILogger().error( "Logo not found.", e );
		} catch( IOException e ) {
			logo = null;
			Utils.getUILogger().error( "Logo not loaded.", e );
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
	 *
	 * Les ressources suivantes ont été utilisées pour la conception de cette méthode :
	 * - http://www.java2s.com/Tutorials/Java/Swing_How_to/JTabbedPane/Change_Java_JTabbedPane_Inset_Color.htm
	 *
	 * @param g Ensemble graphique sur lequel dessiner
	 */
	@Override
	protected void paintComponent( Graphics g ) {
		super.paintComponent( g );
		Graphics2D g2d = ( Graphics2D ) g;
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
			g2d.drawImage( scaledLogo, Constants.UI_TABCONTAINER_MARGIN.top, Constants.UI_TABCONTAINER_MARGIN.top, null );
		}
	}
}
