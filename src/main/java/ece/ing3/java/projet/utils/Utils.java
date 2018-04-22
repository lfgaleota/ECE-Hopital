package ece.ing3.java.projet.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Utility functions
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Utils {
	/**
	 * Get a resource's stream from a path relative to either the execution directory, JAR, or other paths supported by the JVM.
	 *
	 * @param path Relative resource path
	 * @return Resource's stream
	 * @throws IOException Resource not found at the provided path
	 */
	public static InputStream getResource( String path ) throws IOException {
		InputStream stream = Utils.class.getClassLoader().getResourceAsStream( path );
		if( stream == null )
			throw new FileNotFoundException( "Resource '" + path + "' not found." );

		return stream;
	}

	/**
	 * Get a resource's stream from a path relative to either the execution directory, JAR, or other paths supported by the JVM.
	 *
	 * @param path Relative resource path
	 * @return Resource's stream
	 * @throws IOException Loading error
	 */
	public static Image getImageResource( String path ) throws IOException {
		return ImageIO.read( new BufferedInputStream( getResource( path ) ) );
	}

	/**
	 * Affiche un message général à l'utilisateur
	 *
	 * @param message Message à afficher
	 */
	public static void message( String message ) {
		message( null, message );
	}

	/**
	 * Affiche un message général à l'utilisateur
	 *
	 * @param parent  Fenêtre parente
	 * @param message Message à afficher
	 */
	public static void message( JFrame parent, String message ) {
		JOptionPane.showMessageDialog( parent, message, "Information", JOptionPane.INFORMATION_MESSAGE );
	}

	/**
	 * Affiche un message d'erreur à l'utilisateur
	 *
	 * @param message Message d'erreur à afficher
	 */
	public static void error( String message ) {
		error( null, message );
	}

	/**
	 * Affiche un message d'erreur à l'utilisateur
	 *
	 * @param parent  Fenêtre parente
	 * @param message Message d'erreur à afficher
	 */
	public static void error( JFrame parent, String message ) {
		JOptionPane.showMessageDialog( parent, message, "Erreur", JOptionPane.ERROR_MESSAGE );
	}
}
