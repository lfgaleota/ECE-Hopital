package ece.ing3.java.projet.configuration;

import ece.ing3.java.projet.Main;
import ece.ing3.java.projet.utils.Constants;
import net.harawata.appdirs.AppDirsFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Configuration helper.
 * <p>
 * Offers a convenient way to get and set configuration parameters.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Configuration {
	private static Preferences config;

	/**
	 * Loads the configuration.
	 */
	public static void init() {
		config = Preferences.userNodeForPackage( Main.class );
	}

	/**
	 * Returns the underlying Preferences instance.
	 *
	 * @return Preferences instance
	 */
	public static Preferences get() {
		return config;
	}

	/**
	 * Saves the configuration.
	 *
	 * @throws BackingStoreException Error while saving.
	 */
	public static void save() throws BackingStoreException {
		config.sync();
	}

	/**
	 * Get a configuration String value by key.
	 *
	 * @param key Configuration key
	 * @return Value
	 */
	public static String getString( String key ) {
		return getString( key, null );
	}

	/**
	 * Get a configuration String value by key.
	 *
	 * @param key          Configuration key
	 * @param defaultValue Default value
	 * @return Value
	 */
	public static String getString( String key, String defaultValue ) {
		return config.get( key, defaultValue );
	}

	/**
	 * Sets a configuration value.
	 *
	 * @param key   Configuration key
	 * @param value Value
	 */
	public static void set( String key, String value ) {
		config.put( key, value );
	}

	/**
	 * Gets the application's data storage path for the current user.
	 *
	 * @return Application data storage path or {@code null} on error
	 */
	public static Path getUserDataPath() {
		Path userDir = Paths.get( AppDirsFactory.getInstance().getUserDataDir( Constants.APP_FOLDER_NAME, null, null ) );

		if( Files.notExists( userDir ) ) {
			try {
				Files.createDirectory( userDir );
			} catch( IOException e ) {
				e.printStackTrace();
				return null;
			}
		}
		return userDir;
	}
}
