package ece.ing3.java.projet.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Utility functions
 */
public class Utils {
	/**
	 * Get a resource's full path from a path relative to either the execution directory or the JAR
	 *
	 * @param path Relative resource path
	 * @return Resource's full path
	 * @throws FileNotFoundException Resource not found at the provided path
	 */
	public static File getResourcePath( String path ) throws FileNotFoundException {
		try {
			URL fileUrl = Utils.class.getClassLoader().getResource( path );
			if( fileUrl == null )
				throw new FileNotFoundException( "Resource '" + path + "' not found." );

			URI fileUri = fileUrl.toURI();
			return Paths.get( fileUri ).toFile();
		} catch( URISyntaxException e ) {
			throw new FileNotFoundException( "Resource '" + path + "' not found." + e.getLocalizedMessage() );
		}
	}

	/**
	 * Get the UI logger.
	 * 
	 * @return UI logger
	 */
	public static Logger getUILogger() {
		return LogManager.getLogger( Constants.LOGGER_NAME_UI );
	}
}
