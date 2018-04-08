package ece.ing3.java.projet.modele.hopital;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.finders.HospitalisationFinder;

import java.util.List;

/**
 * Modèle d'une Hospitalisation stocké en base de donnée
 */
public class Hospitalisation extends Model {
	private Long no_malade;
	private String code_service;
	private Long no_chambre;
	private Integer lit;

	/**
	 * Créer une nouvelle hospitalisation
	 *
	 * @param numeroMalade  Numéro du malade lié à l'hospitalisation
	 * @param codeService   Code du service lié à l'hospitalisation
	 * @param numeroChambre Numéro de chambre liée à l'hospitalisation
	 * @param numeroLit           Numéro de lit occupé par le malade
	 */
	public Hospitalisation( Long numeroMalade, String codeService, Long numeroChambre, Integer numeroLit ) {
		this.no_malade = numeroMalade;
		this.code_service = codeService;
		this.no_chambre = numeroChambre;
		this.lit = numeroLit;
	}

	/**
	 * Récupère le numéro de lit occupé par le malade
	 *
	 * @return Numéro de lit occupé par le malade
	 */
	public Integer getLit() {
		return lit;
	}

	/**
	 * Modifie le numéro de lit occupé par le malade
	 *
	 * @param lit Nouveau numéro de lit occupé par le malade
	 */
	public void setLit( Integer lit ) {
		this.lit = lit;
	}

	/**
	 * Récupère le numéro du malade lié à l'hospitalisation
	 *
	 * @return Numéro du malade lié à l'hospitalisation
	 */
	public Long getNumeroMalade() {
		return no_malade;
	}

	/**
	 * Récupère le code du service lié à l'hospitalisation
	 *
	 * @return Code du service lié à l'hospitalisation
	 */
	public String getCodeService() {
		return code_service;
	}

	/**
	 * Récupère le numéro de chambre liée à l'hospitalisation
	 *
	 * @return Numéro de chambre liée à l'hospitalisation
	 */
	public Long getNumeroChambre() {
		return no_chambre;
	}

	/**
	 * Récupère le malade lié à l'hospitalisation
	 *
	 * @return Malade lié à l'hospitalisation
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Malade getMalade() throws DatabaseException {
		return Malade.find( no_malade );
	}

	/**
	 * Modifie le malade lié à l'hospitalisation
	 *
	 * @param malade Nouveau malade lié à l'hospitalisation
	 */
	public void setMalade( Malade malade ) {
		// TODO : Modification du malade d'une hospitalisation
	}

	/**
	 * Récupère le service lié à l'hospitalisation
	 *
	 * @return Service lié à l'hospitalisation
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Service getService() throws DatabaseException {
		return Service.find( code_service );
	}

	/**
	 * Modifie le service lié à l'hospitalisation
	 *
	 * @param service Nouveau service lié à l'hospitalisation
	 */
	public void setService( Service service ) {
		// TODO : Modification du service d'une hospitalisation
	}

	/**
	 * Récupère la chambre liée à l'hospitalisation
	 *
	 * @return Chambre liée à l'hospitalisation
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Chambre getChambre() throws DatabaseException {
		return Chambre.find( no_chambre, code_service );
	}

	/**
	 * Modifie la chambre liée à l'hospitalisation
	 *
	 * @param chambre Nouvelle chambre liée à l'hospitalisation
	 */
	public void setChambre( Chambre chambre ) {
		// TODO : Modification du chambre d'une hospitalisation
	}

	/**
	 * Recherche une hospitalisation pour un numéro de malade donné
	 *
	 * @param numeroMalade Numéro de malade de l'hospitalisation à rechercher
	 * @return Hospitalisation donnée ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static Hospitalisation find( Long numeroMalade ) throws DatabaseException {
		return ( new HospitalisationFinder() ).numeroMalade( numeroMalade ).findUnique();
	}

	/**
	 * Récupère l'ensemble des hospitalisations
	 *
	 * @return Liste des hospitalisations
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Hospitalisation> findList() throws DatabaseException {
		return ( new HospitalisationFinder() ).findList();
	}

	/**
	 * Génère une représentation textuelle de l'hospitalisation
	 *
	 * @return Représentation textuelle de l'hospitalisation
	 */
	@Override
	public String toString() {
		return "Hospitalisation{" +
				"no_malade=" + no_malade +
				", code_service='" + code_service + '\'' +
				", no_chambre=" + no_chambre +
				", lit=" + lit +
				'}';
	}
}
