package ece.ing3.java.projet.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Ensemble de chaînes de caractères utilisées à l'affichage
 *
 * Il est possible de récupérer une valeur sans la référencer directement mais en utilisant une chaîne de caractère "clé".
 * Cette "clé" est générée à partir du nom du champ, en mettant en minuscule et remplaçant les tirets par des points, par exemple model.malade -> MODEL_MALADE.
 * Il est possible de rediriger la clé selon si on veut une valeur au singulier ou pluriel. Si le pluriel est demandée, un suffixe {@link Constants#STRINGS_SUFFIX_PLURAL} est ajouté pour obtenir le nom d'attribut, par exemple model.malade -> model.malade.plural -> MODEL_MALADE_PLURAL.
 * Un sous-ensemble de chaîne associées à des modèles BDD est disponible, construit en rajoutant à la clé passée en paramètre de {@link Strings#getModel(String)} un préfixe {@link Constants#MODEL_STRINGS_PREFIX}, par exemple malade -> model.malade -> MODEL_MALADE.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Strings {
	public static final String MODEL_MALADE = "Malade";
	public static final String MODEL_EMPLOYE = "Employé";
	public static final String MODEL_DOCTEUR = "Docteur";
	public static final String MODEL_SERVICE = "Service";
	public static final String MODEL_INFIRMIER = "Infirmier";
	public static final String MODEL_CHAMBRE = "Chambre";
	public static final String MODEL_HOSPITALISATION = "Hospitalisation";
	public static final String MODEL_SOIGNE = "Prise en charge";
	public static final String MODEL_MALADE_PLURAL = "Malades";
	public static final String MODEL_EMPLOYE_PLURAL = "Employés";
	public static final String MODEL_DOCTEUR_PLURAL = "Docteurs";
	public static final String MODEL_SERVICE_PLURAL = "Services";
	public static final String MODEL_INFIRMIER_PLURAL = "Infirmiers";
	public static final String MODEL_CHAMBRE_PLURAL = "Chambres";
	public static final String MODEL_HOSPITALISATION_PLURAL = "Hospitalisations";
	public static final String MODEL_SOIGNE_PLURAL = "Prises en charge";
	public static final String MODEL_NUMERO = "Numéro";
	public static final String MODEL_NOM = "Nom";
	public static final String MODEL_PRENOM = "Prénom";
	public static final String MODEL_ADRESSE = "Adresse";
	public static final String MODEL_NUMEROTELEPHONE = "N° téléphone";
	public static final String MODEL_MUTUELLE = "Mutuelle";
	public static final String MODEL_CODE = "Code";
	public static final String MODEL_BATIMENT = "Bâtiment";
	public static final String MODEL_NUMERODIRECTEUR = "N° docteur directeur";
	public static final String MODEL_SPECIALITE = "Spécialité";
	public static final String MODEL_ROTATION = "Rotation";
	public static final String MODEL_SALAIRE = "Salaire";
	public static final String MODEL_CODESERVICE = "Code du service";
	public static final String MODEL_NUMEROCHAMBRE = "N° chambre";
	public static final String MODEL_NOMBRELITS = "Nombre de lits";
	public static final String MODEL_NUMEROSURVEILLANT = "N° infirmier surveillant";
	public static final String MODEL_CODESERVICERATTACHE = "Code du service rattaché";
	public static final String MODEL_NUMEROMALADE = "N° malade";
	public static final String MODEL_NUMEROLIT = "N° lit";
	public static final String MODEL_NUMERODOCTEUR = "N° docteur";
	public static final String MODEL_MALADENOM = "Nom du malade";
	public static final String MODEL_MALADEPRENOM = "Prénom du malade";
	public static final String MODEL_DOCTEURPRENOM = "Prénom du docteur";
	public static final String MODEL_DOCTEURNOM = "Nom du docteur";
	public static final String MODEL_DOCTEURSPECIALITE = "Spécialité du docteur";
	public static final String MODEL_SURVEILLANTPRENOM = "Prénom du surveillant";
	public static final String MODEL_SURVEILLANTNOM = "Nom du surveillant";
	public static final String MODEL_DIRECTEURPRENOM = "Prénom du directeur";
	public static final String MODEL_DIRECTEURNOM = "Nom du directeur";
	public static final String DIALOG_SUBMIT_SAVE = "Sauvegarder";
	public static final String DIALOG_MODEL_REMOVE = "Vous allez supprimer <nb> <modelName>, voulez-vous vraiment procéder ?";
	public static final String DIALOG_CONNECTING_TITLE = "Connexion en cours...";
	public static final String DIALOG_CONNECTING_TEXT = "Connexion en cours...";
	public static final String CONFIGURATION_DATABASE_URL = "URL de connexion";
	public static final String CONFIGURATION_DATABASE_USERNAME = "Nom d'utilisateur";
	public static final String CONFIGURATION_DATABASE_PASSWORD = "Mot de passe";
	public static final String CONFIGURATION_DATABASE_DRIVER = "Type de BDD";
	public static final String CONFIGURATION_TITLE = "Configuration";
	public static final String REPORTING_TITLE = "Statistiques";
	public static final String REPORTING_MALADES = "Malades";
	public static final String REPORTING_EMPLOYES = "Employés";

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

	/**
	 * Récupère la chaîne associée à la clé fournie, au singulier.
	 *
	 * @param name Clé de la chaîne
	 * @return Chaîne associée
	 */
	public static String get( String name ) {
		return get( name, false );
	}

	/**
	 * Récupère la chaîne associée à la clé fournie.
	 *
	 * @param name Clé de la chaîne
	 * @param plural {@code true} sdi on veut la chaîne au pluriel associée
	 * @return Chaîne associée
	 */
	public static String get( String name, boolean plural ) {
		if( plural ) {
			name += Constants.STRINGS_SUFFIX_PLURAL;
		}
		name = formatName( name );
		String value = map.get( name );
		return value != null ? value : "##UNTRANS:" + name + "##";
	}

	/**
	 * Récupère la chaîne associée à la clé fournie, au singulier, pour le sous-ensemble des chaînes associés à des modèles BDD.
	 *
	 * @param name Clé de la chaîne
	 * @return Chaîne associée
	 */
	public static String getModel( String name ) {
		return getModel( name, false );
	}

	/**
	 * Récupère la chaîne associée à la clé fournie, pour le sous-ensemble des chaînes associés à des modèles BDD.
	 *
	 * @param name Clé de la chaîne
	 * @param plural {@code true} sdi on veut la chaîne au pluriel associée
	 * @return Chaîne associée
	 */
	public static String getModel( String name, boolean plural ) {
		return get( Constants.MODEL_STRINGS_PREFIX + name, plural );
	}
}
