package ece.ing3.java.projet.modele.administration;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.finders.ChambreFinder;
import ece.ing3.java.projet.modele.finders.InfirmierFinder;
import ece.ing3.java.projet.modele.finders.ServiceFinder;
import ece.ing3.java.projet.modele.hopital.Chambre;

import java.util.List;

/**
 * Modèle de Service stocké en base de donnée
 */
public class Service extends Model {
	@Id
	private String code;
	private String nom;
	private String batiment;
	@Column( name = "directeur" )
	private Long numeroDirecteur;

	/**
	 * Créer un nouveau service vide
	 */
	public Service() {}

	/**
	 * Créer un nouveau service
	 *
	 * @param code            Code du service
	 * @param nom             Nom du service
	 * @param batiment        Bâtiment où le service se trouve
	 * @param numeroDirecteur Numéro de docteur du directeur de département
	 */
	public Service( String code, String nom, String batiment, Long numeroDirecteur ) {
		this.code = code;
		this.nom = nom;
		this.batiment = batiment;
		this.numeroDirecteur = numeroDirecteur;
	}

	/**
	 * Récupère le code du service
	 *
	 * @return Code du service
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Modifie le code du service
	 *
	 * @param code Nouveau code du service
	 */
	public void setCode( String code ) {
		this.code = code;
	}

	/**
	 * Récupère le nom du service
	 *
	 * @return Nom du service
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du service
	 *
	 * @param nom Nouveau nom du service
	 */
	public void setNom( String nom ) {
		this.nom = nom;
	}

	/**
	 * Récupère le bâtiment où se trouve le service
	 *
	 * @return Bâtiment où se trouve le service
	 */
	public String getBatiment() {
		return batiment;
	}

	/**
	 * Modifie le bâtiment où se trouve le service
	 *
	 * @param batiment Nouveau bâtiment
	 */
	public void setBatiment( String batiment ) {
		this.batiment = batiment;
	}

	/**
	 * Récupère les infirmiers rattachés au service
	 *
	 * @return Liste d'infirmiers rattachés au service
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Infirmier> getInfirmiersRattaches() throws DatabaseException {
		return ( new InfirmierFinder() ).codeService( getCode() ).findList();
	}

	/**
	 * Récupère le numéro du directeur du service
	 *
	 * @return Numéro du directeur du service
	 */
	public Long getNumeroDirecteur() {
		return numeroDirecteur;
	}

	/**
	 * Modifie le numéro du directeur du service
	 *
	 * @param numeroDirecteur numéro du directeur du service
	 */
	public void setNumeroDirecteur( Long numeroDirecteur ) {
		this.numeroDirecteur = numeroDirecteur;
	}

	/**
	 * Récupère le directeur du service
	 *
	 * @return Directeur du service
	 * @throws DatabaseException Récupération depuis la BDD échouée
	 */
	public Docteur getDirecteur() throws DatabaseException {
		return Docteur.find( numeroDirecteur );
	}

	/**
	 * Modifie le directeur du service
	 *
	 * @param directeur Nouveau directeur du service
	 */
	public void setDirecteur( Docteur directeur ) {
		this.numeroDirecteur = directeur.getNumero();
	}

	/**
	 * Récupère les chambres rattachées au service
	 *
	 * @return Liste des chambres rattachées au service
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Chambre> getChambresRattachees() throws DatabaseException {
		return ( new ChambreFinder() ).codeServiceRattache( code ).findList();
	}

	/**
	 * Recherche un service de code spécifique
	 *
	 * @param code Code du service à rechercher
	 * @return Service de code donnée ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static Service find( String code ) throws DatabaseException {
		return ( new ServiceFinder() ).code( code ).findUnique();
	}

	/**
	 * Récupère l'ensemble des services
	 *
	 * @return Liste des services
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Service> findList() throws DatabaseException {
		return ( new ServiceFinder() ).findList();
	}

	/**
	 * Génère une représentation textuelle du service
	 *
	 * @return Représentation textuelle du service
	 */
	@Override
	public String toString() {
		return "Service{" +
				"code=" + getCode() +
				", nom='" + getNom() + '\'' +
				", batiment='" + getBatiment() + '\'' +
				", numeroDirecteur=" + getNumeroDirecteur() +
				'}';
	}
}
