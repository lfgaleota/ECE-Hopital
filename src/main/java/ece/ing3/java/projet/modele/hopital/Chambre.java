package ece.ing3.java.projet.modele.hopital;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.finders.ChambreFinder;
import ece.ing3.java.projet.modele.finders.HospitalisationFinder;

import java.util.List;

/**
 * Modèle de Chambre stocké en base de donnée
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Chambre extends Model {
	@Id
	@Column( name = "code_service" )
	private String codeServiceRattache;
	@Id
	@Column( name = "no_chambre" )
	private Long numeroChambre;
	@Column( name = "nb_lits" )
	private Integer nombreLits;
	@Column( name = "surveillant" )
	private Long numeroSurveillant;

	/**
	 * Créer une nouvelle chambre vide
	 */
	public Chambre() {
	}

	/**
	 * Créer une nouvelle chambre
	 *
	 * @param numeroChambre       Numéro de la chambre
	 * @param nombreLits          Nombre de lits dans la chambre
	 * @param numeroSurveillant   Numéro de l'infirmier surveillant la chambre
	 * @param codeServiceRattache Code du service auquel est rattaché la chambre
	 */
	public Chambre( Long numeroChambre, Integer nombreLits, Long numeroSurveillant, String codeServiceRattache ) {
		this.numeroChambre = numeroChambre;
		this.nombreLits = nombreLits;
		this.numeroSurveillant = numeroSurveillant;
		this.codeServiceRattache = codeServiceRattache;
	}

	/**
	 * Récupère le numéro de la chambre
	 *
	 * @return Numéro de la chambre
	 */
	public Long getNumeroChambre() {
		return numeroChambre;
	}

	/**
	 * Modifie le numéro de la chambre
	 *
	 * @param numeroChambre Nouveau numéro de la chambre
	 */
	public void setNumeroChambre( Long numeroChambre ) {
		this.numeroChambre = numeroChambre;
	}

	/**
	 * Récupère le nombre de lits dans la chambre
	 *
	 * @return Nombre de lits dans la chambre
	 */
	public Integer getNombreLits() {
		return nombreLits;
	}

	/**
	 * Modifie le nombre de lits dans la chambre
	 *
	 * @param nombreLits Nombre de lits dans la chambre
	 */
	public void setNombreLits( Integer nombreLits ) {
		this.nombreLits = nombreLits;
	}

	/**
	 * Récupère le numéro du infirmier surveillant la chambre
	 *
	 * @return Numéro d'infirmier surveillant la chambre
	 */
	public Long getNumeroSurveillant() {
		return numeroSurveillant;
	}

	/**
	 * Modifie le numéro du infirmier surveillant la chambre
	 *
	 * @param numeroSurveillant Nouveau numéro d'infirmier surveillant la chambre
	 */
	public void setNumeroSurveillant( Long numeroSurveillant ) {
		this.numeroSurveillant = numeroSurveillant;
	}

	/**
	 * Récupère l'infirmier surveillant la chambre
	 *
	 * @return Infirmier surveillant la chambre
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Infirmier getSurveillant() throws DatabaseException {
		return Infirmier.find( numeroSurveillant );
	}

	/**
	 * Modifie l'infirmier surveillant la chambre
	 *
	 * @param surveillant Infirmier surveillant la chambre
	 */
	public void setSurveillant( Infirmier surveillant ) {
		this.numeroSurveillant = surveillant.getNumero();
	}

	/**
	 * Récupère le code du service auquel est rattaché la chambre
	 *
	 * @return Code du service auquel est rattaché la chambre
	 */
	public String getCodeServiceRattache() {
		return codeServiceRattache;
	}

	/**
	 * Modifie le code du service auquel est rattaché la chambre
	 *
	 * @param codeServiceRattache Nouveau code du service auquel est rattaché la chambre
	 */
	public void setCodeServiceRattache( String codeServiceRattache ) {
		this.codeServiceRattache = codeServiceRattache;
	}

	/**
	 * Récupère le service auquel est rattaché la chambre
	 *
	 * @return Service auquel est rattaché la chambre
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public Service getServiceRattache() throws DatabaseException {
		return Service.find( codeServiceRattache );
	}

	/**
	 * Modifie le service auquel est rattaché la chambre
	 *
	 * @param service Nouveau service auquel est rattaché la chambre
	 */
	public void setServiceRattache( Service service ) {
		this.codeServiceRattache = service.getCode();
	}

	/**
	 * Récupère les hospitalisations liées à cette chambre
	 *
	 * @return Liste des hospitalisations liées à la chambre
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public List<Hospitalisation> getHospitalisations() throws DatabaseException {
		return ( new HospitalisationFinder() ).numeroChambre( getNumeroChambre() ).codeService( getCodeServiceRattache() ).findList();
	}

	/**
	 * Recherche une chambre de numéro et code service spécifique
	 *
	 * @param numeroChambre       Numéro de chambre à rechercher
	 * @param codeServiceRattache Code du service de rattachement à rechercher
	 * @return Chambre donnée ou {@code null}
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static Chambre find( Long numeroChambre, String codeServiceRattache ) throws DatabaseException {
		return ( new ChambreFinder() ).codeServiceRattache( codeServiceRattache ).numeroChambre( numeroChambre ).findUnique();
	}

	/**
	 * Récupère l'ensemble des chambres
	 *
	 * @return Liste des chambres
	 * @throws DatabaseException Erreur lors de la recherche en base de donnée
	 */
	public static List<Chambre> findList() throws DatabaseException {
		return ( new ChambreFinder() ).findList();
	}

	/**
	 * Génère une représentation textuelle de la chambre
	 *
	 * @return Représentation textuelle de la chambre
	 */
	@Override
	public String toString() {
		return "Chambre{" +
				"no_chambre=" + getNumeroChambre() +
				", nb_lits=" + getNombreLits() +
				", surveillant=" + getNumeroSurveillant() +
				", service=" + getCodeServiceRattache() +
				'}';
	}


}
