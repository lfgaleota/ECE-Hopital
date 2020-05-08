package ece.ing3.java.projet.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Ensemble des drivers JDBC utilisables pour la base de donnée
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public enum JDBCDriver {
	MySQL( "com.mysql.cj.jdbc.Driver" ),
	Embedded( "org.h2.Driver" );

	private static Map<String, JDBCDriver> valueMap;
	private String className;

	static {
		valueMap = new HashMap<>();
		for( JDBCDriver driver : JDBCDriver.values() ) {
			valueMap.put( driver.getClassName(), driver );
		}
	}

	JDBCDriver( String className ) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public static JDBCDriver parseClassName( String className ) {
		return valueMap.get( className );
	}
}
