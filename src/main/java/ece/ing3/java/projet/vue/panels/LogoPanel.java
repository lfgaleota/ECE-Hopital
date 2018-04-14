package ece.ing3.java.projet.vue.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.LogManager;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;

public class LogoPanel extends BasePanel {
	private Image logo;

	public LogoPanel() {
		try {
			logo = ImageIO.read( Utils.getResourcePath( Constants.RESOURCE_PATH_APPLOGO ) );
		} catch (FileNotFoundException e) {
			Utils.getUILogger().error( "Logo not found.", e );
		} catch (IOException e) {
			Utils.getUILogger().error( "Logo not loaded.", e );
		}
	}

	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		g.drawImage( logo, 0, 0, 115, 100, this );
	}
}