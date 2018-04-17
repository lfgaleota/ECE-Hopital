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
	public static final String MODEL_SERVICE = "Service";
	public static final String MODEL_CODE = "Code";
	public static final String MODEL_BATIMENT = "Bâtiment";
	public static final String MODEL_NUMERODIRECTEUR = "N° directeur";
	public static final String MODEL_EMPLOYE = "Employé";
	public static final String MODEL_DOCTEUR = "Docteur";
	public static final String MODEL_SPECIALITE = "Spécialité";
	public static final String MODEL_ROTATION = "Rotation";
	public static final String MODEL_SALAIRE = "Salaire";
	public static final String MODEL_CODESERVICE = "Code du service";
	public static final String MODEL_INFIRMIER = "Infirmier";
	public static final String MODEL_CHAMBRE = "Chambre";
	public static final String MODEL_NUMEROCHAMBRE = "N° chambre";
	public static final String MODEL_NOMBRELITS = "Nombre de lits";
	public static final String MODEL_NUMEROSURVEILLANT = "N° surveillant";
	public static final String MODEL_CODESERVICERATTACHE = "Code du service rattaché";
	public static final String MODEL_HOSPITALISATION = "Hospitalisation";
	public static final String MODEL_NUMEROMALADE = "N° malade";
	public static final String MODEL_NUMEROLIT = "N° lit";
	public static final String MODEL_SOIGNE = "Prise en charge";
	public static final String MODEL_NUMERODOCTEUR = "N° docteur";

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
