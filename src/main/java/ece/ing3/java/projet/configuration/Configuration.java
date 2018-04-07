package ece.ing3.java.projet.configuration;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.FileNotFoundException;

/**
 * Configuration helper.
 * <p>
 * Offers a convenient way to get and set configuration parameters.
 */
public class Configuration {
	private static org.apache.commons.configuration2.Configuration config;

	public static void init() throws ConfigurationException {
		Configurations configs = new Configurations();
		try {
			config = configs.properties( Utils.getResourcePath( Constants.RESOURCE_PATH_CONFIGURATION ) );
		} catch( FileNotFoundException e ) {
			throw new ConfigurationException( "Configuration file not found.", e );
		}
	}

	public static org.apache.commons.configuration2.Configuration get() {
		return config;
	}

	public static String getString( String s ) {
		return config.getString( s );
	}
}
