package ece.ing3.java.projet.configuration;

import ece.ing3.java.projet.Main;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Configuration helper.
 * <p>
 * Offers a convenient way to get and set configuration parameters.
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
}
