package ece.ing3.java.projet.modele.hopital;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.finders.HospitalisationFinder;

import java.util.List;

/**
 * Modèle d'une Hospitalisation stocké en base de donnée
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Hospitalisation extends Model {
	@Id
	@Column( name = "no_malade" )
	private Long numeroMalade;
	@Column( name = "code_service" )
	private String codeService;
	@Column( name = "no_chambre" )
	private Long numeroChambre;
	@Column( name = "lit" )
	private Integer numeroLit;

	/**
	 * Créer une nouvelle hospitalisation
	 */
	public Hospitalisation() {
	}

	/**
	 * Créer une nouvelle hospitalisation
	 *
	 * @param numeroMalade  Numéro du malade lié à l'hospitalisation
	 * @param codeService   Code du service lié à l'hospitalisation
	 * @param numeroChambre Numéro de chambre liée à l'hospitalisation
	 * @param numeroLit     Numéro de numeroLit occupé par le malade
	 */
	public Hospitalisation( Long numeroMalade, String codeService, Long numeroChambre, Integer numeroLit ) {
		this.numeroMalade = numeroMalade;
		this.codeService = codeService;
		this.numeroChambre = numeroChambre;
		this.numeroLit = numeroLit;
	}

	/**
	 * Récupère le numéro de lit occupé par le malade
	 *
	 * @return Numéro de numeroLit occupé par le malade
	 */
	public Integer getNumeroLit() {
		return numeroLit;
	}

	/**
	 * Modifie le numéro de lit occupé par le malade
	 *
	 * @param numeroLit Nouveau numéro de numeroLit occupé par le malade
	 */
	public void setNumeroLit( Integer numeroLit ) {
		this.numeroLit = numeroLit;
	}

	/**
	 * Récupère le numéro du malade lié à l'hospitalisation
	 *
	 * @return Numéro du malade lié à l'hospitalisation
	 */
	public Long getNumeroMalade() {
		return numeroMalade;
	}

	/**
	 * Modifie le numéro du malade lié à l'hospitalisation
	 *
	 * @param numeroMalade Nouveau numéro du malade lié à l'hospitalisation
	 */
	public void setNumeroMalade( Long numeroMalade ) {
		this.numeroMalade = numeroMalade;
	}

	/**
	 * Récupère le code du service lié à l'hospitalisation
	 *
	 * @return Code du service lié à l'hospitalisation
	 */
	public String getCodeService() {
		return codeService;
	}

	/**
	 * Modifie le code du service lié à l'hospitalisation
	 *
	 * @param codeService Nouveau code du service lié à l'hospitalisation
	 */
	public void setCodeService( String codeService ) {
		this.codeService = codeService;
	}

	/**
	 * Récupère le numéro de chambre liée à l'hospitalisation
	 *
	 * @return Numéro de chambre liée à l'hospitalisation
	 */
	public Long getNumeroChambre() {
		return numeroChambre;
	}

	/**
	 * Modifie le numéro de chambre liée à l'hospitalisation
	 *
	 * @param numeroChambre Nouveau numéro de chambre liée à l'hospitalisation
	 */
	public void setNumeroChambre( Long numeroChambre ) {
		this.numeroChambre = numeroChambre;
	}

	/**
	 * Récupère le malade lié à l'hospitalisation
	 *
	 * @return Malade lié à l'hospitalisation
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Malade getMalade() throws DatabaseException {
		return Malade.find( numeroMalade );
	}

	/**
	 * Modifie le malade lié à l'hospitalisation
	 *
	 * @param malade Nouveau malade lié à l'hospitalisation
	 */
	public void setMalade( Malade malade ) {
		this.numeroMalade = malade.getNumero();
	}

	/**
	 * Récupère le service lié à l'hospitalisation
	 *
	 * @return Service lié à l'hospitalisation
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Service getService() throws DatabaseException {
		return Service.find( codeService );
	}

	/**
	 * Modifie le service lié à l'hospitalisation
	 *
	 * @param service Nouveau service lié à l'hospitalisation
	 */
	public void setService( Service service ) {
		this.codeService = service.getCode();
	}

	/**
	 * Récupère la chambre liée à l'hospitalisation
	 *
	 * @return Chambre liée à l'hospitalisation
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Chambre getChambre() throws DatabaseException {
		return Chambre.find( numeroChambre, codeService );
	}

	/**
	 * Modifie la chambre liée à l'hospitalisation
	 *
	 * @param chambre Nouvelle chambre liée à l'hospitalisation
	 */
	public void setChambre( Chambre chambre ) {
		this.numeroChambre = chambre.getNumeroChambre();
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
				"numeroMalade=" + numeroMalade +
				", codeService='" + codeService + '\'' +
				", numeroChambre=" + numeroChambre +
				", numeroLit=" + numeroLit +
				'}';
	}
}
