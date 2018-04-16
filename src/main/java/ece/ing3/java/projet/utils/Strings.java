package ece.ing3.java.projet.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Strings {
	public static final String MODEL_MALADE = "Malade";
	public static final String MODEL_NUMERO = "Numéro";
	public static final String MODEL_NOM = "Nom";
	public static final String MODEL_PRENOM = "Prénom";
	public static final String MODEL_ADRESSE = "Adresse";
	public static final String MODEL_NUMEROTELEPHONE = "N° téléphone";
	public static final String MODEL_MUTUELLE = "Mutuelle";

	private static final Map<String, String> map = new HashMap<>();

	static {
		buildAssociationMap();
	}

	private static void buildAssociationMap() {
		for( Field field : Strings.class.getDeclaredFields() ) {
			if( field.getType().equals( String.class ) ) {
				try {
					map.put( field.getName(), (String) field.get( null ) );
				} catch( IllegalAccessException e ) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String formatName( String name ) {
		return name.toUpperCase().replaceAll( "\\.", "_" );
	}

	public static String get( String name ) {
		String value = map.get( formatName( name ) );
		return value != null ? value : "##UNTRANS:" + name + "##";
	}

	public static String getModel( String name ) {
		return get( Constants.MODEL_STRINGS_PREFIX + name );
	}
}
