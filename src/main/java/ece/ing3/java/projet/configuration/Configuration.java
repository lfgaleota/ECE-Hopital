package ece.ing3.java.projet.configuration;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.FileNotFoundException;

/**
 * Configuration helper.
 * <p>
 * Offers a convenient way to get and set configuration parameters.
 */
public class Configuration {
	private static FileBasedConfigurationBuilder<PropertiesConfiguration> builder;
	private static PropertiesConfiguration config;

	/**
	 * Loads the configuration from standard configuration file.
	 *
	 * @throws ConfigurationException Configuration file not found or malformed.
	 */
	public static void init() throws ConfigurationException {
		Configurations configs = new Configurations();
		try {
			builder = configs.propertiesBuilder( Utils.getResourcePath( Constants.RESOURCE_PATH_CONFIGURATION ) );
			config = builder.getConfiguration();
		} catch( FileNotFoundException e ) {
			throw new ConfigurationException( "Configuration file not found.", e );
		}
	}

	/**
	 * Returns the underlying Apache Commons Configuration2 instance.
	 *
	 * @return Configuration instance
	 */
	public static org.apache.commons.configuration2.Configuration get() {
		return config;
	}

	/**
	 * Save the configuration to file.
	 *
	 * @throws ConfigurationException Error while saving to file.
	 */
	public static void save() throws ConfigurationException {
		builder.save();
	}

	/**
	 * Get a configuration String value by key.
	 *
	 * @param key Configuration key
	 * @return Value
	 */
	public static String getString( String key ) {
		return config.getString( key );
	}

	/**
	 * Sets a configuration value.
	 *
	 * @param key Configuration key
	 * @param value Value
	 */
	public static void set( String key, Object value ) {
		config.setProperty( key, value  );
	}
}
