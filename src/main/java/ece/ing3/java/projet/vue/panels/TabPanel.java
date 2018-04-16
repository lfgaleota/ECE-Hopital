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

	public TabPanel() {
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

	public static void prepare() {
		//https://stackoverflow.com/a/5184026
		//http://www.java2s.com/Tutorial/Java/0240__Swing/CustomizingaJTabbedPaneLookandFeel.htm
		oldInsets = UIManager.getDefaults().getInsets( "TabbedPane.tabAreaInsets" );
		UIManager.getDefaults().put( "TabbedPane.tabAreaInsets", new Insets( Constants.UI_TAB_MARGIN_LEFT, Constants.UI_TAB_MARGIN_TOP, 0, 0 ) );
	}

	public static void finish() {
		UIManager.getDefaults().put( "TabbedPane.tabAreaInsets", oldInsets );
	}

	//http://www.java2s.com/Tutorials/Java/Swing_How_to/JTabbedPane/Change_Java_JTabbedPane_Inset_Color.htm
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
			int size = ( maxTabWidth - Constants.UI_TAB_MARGIN_LEFT );
			Image scaledLogo = logo.getScaledInstance( size, size, Image.SCALE_DEFAULT );
			g2d.drawImage( scaledLogo, Constants.UI_TAB_MARGIN_LEFT, Constants.UI_TAB_MARGIN_LEFT, null );
		}
	}
}
