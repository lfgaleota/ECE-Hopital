package ece.ing3.java.projet.modele.employe;

import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.enums.Rotation;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.finders.ChambreFinder;
import ece.ing3.java.projet.modele.finders.InfirmierFinder;
import ece.ing3.java.projet.modele.hopital.Chambre;

import java.util.List;

/**
 * Modèle d'Infirmier (un Employé) stocké en base de donnée
 */
public class Infirmier extends Employe {
	private Rotation rotation;
	private Float salaire;
	@Column( name = "code_service" )
	private String codeService;

	/**
	 * Créer un nouvel infirmier vide
	 */
	public Infirmier() {}

	/**
	 * Créer un nouvel infirmier
	 *
	 * @param numero          Numéro d'employé
	 * @param nom             Nom de l'infirmier
	 * @param prenom          Prénom de l'infirmier
	 * @param adresse         Adresse de l'infirmier
	 * @param numeroTelephone Numéro de téléphone de l'infirmier
	 * @param rotation        Période de rotation de l'infirmier
	 * @param salaire         Salaire de l'infirmier
	 * @param codeService     Code de service de l'infirmier
	 */
	public Infirmier( Long numero, String nom, String prenom, String adresse, String numeroTelephone, Rotation rotation, Float salaire, String codeService ) {
		super( numero, nom, prenom, adresse, numeroTelephone );
		this.rotation = rotation;
		this.salaire = salaire;
		this.codeService = codeService;
	}

	/**
	 * Récupère la période de rotation de l'infirmier
	 *
	 * @return Période de rotation de l'infirmier
	 */
	public Rotation getRotation() {
		return rotation;
	}

	/**
	 * Modifie la période de rotation de l'infirmier
	 *
	 * @param rotation Nouvelle période de rotation de l'infirmier
	 */
	public void setRotation( Rotation rotation ) {
		this.rotation = rotation;
	}

	/**
	 * Récupère le salaire de l'infirmier
	 *
	 * @return Salaire de l'infirmier
	 */
	public Float getSalaire() {
		return salaire;
	}

	/**
	 * Modifie le salaire de l'infirmier
	 *
	 * @param salaire Nouveau salaire de l'infirmier
	 */
	public void setSalaire( Float salaire ) {
		this.salaire = salaire;
	}

	/**
	 * Récupère le code du service auquel est rattaché l'infirmier
	 *
	 * @return Code du service auquel est rattaché l'infirmier
	 */
	public String getCodeService() {
		return codeService;
	}

	/**
	 * Récupère le code du service auquel est rattaché l'infirmier
	 *
	 * @param codeService Code du service auquel est rattaché l'infirmier
	 */
	public void setCodeService( String codeService ) {
		this.codeService = codeService;
	}

	/**
	 * Récupère le service auquel est rattaché l'infirmier
	 *
	 * @return Service auquel est rattaché l'infirmier
	 * @throws DatabaseException Récupération depuis la BDD échouée
	 */
	public Service getService() throws DatabaseException {
		return Service.find( this.codeService );
	}

	/**
	 * Modifie le service auquel est rattaché l'infirmier
	 *
	 * @param service Nouveau service auquel est rattaché l'infirmier
	 */
	public void setService( Service service ) {
		this.codeService = service.getCode();
	}

	/**
	 * Récupère les chambres surveillées par l'infirmier
	 *
	 * @return Liste des chambres surveillées par l'infirmier
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Chambre> getChambresSurveillees() throws DatabaseException {
		return ( new ChambreFinder() ).numeroSurveillant( getNumero() ).findList();
	}

	/**
	 * Génère une représentation textuelle de l'infirmier
	 *
	 * @return Représentation textuelle de l'infirmier
	 */
	@Override
	public String toString() {
		return "Infirmier{" +
				"rotation=" + getRotation() +
				", salaire=" + getSalaire() +
				", codeService=" + getCodeService() +
				" => " + super.toString() +
				'}';
	}

	/**
	 * Recherche un infirmier de numéro spécifique
	 *
	 * @param numero Numéro d'infirmier à rechercher
	 * @return Infirmier de numéro donné ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static Infirmier find( Long numero ) throws DatabaseException {
		return ( new InfirmierFinder() ).numero( numero ).findUnique();
	}

	/**
	 * Récupère l'ensemble des infirmiers
	 *
	 * @return Liste des infirmiers
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Infirmier> findList() throws DatabaseException {
		return ( new InfirmierFinder() ).findList();
	}
}
