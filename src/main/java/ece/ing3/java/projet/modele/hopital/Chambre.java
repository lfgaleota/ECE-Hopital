package ece.ing3.java.projet.modele.hopital;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Infirmier;

/**
 * Modèle de Chambre stocké en base de donnée
 */
public class Chambre extends Model {
	private Long no_chambre;
	private Integer nb_lits;

	/**
	 * Créer une nouvelle chambre
	 *
	 * @param numeroChambre Numéro de la chambre
	 * @param nombreLits Nombre de lits dans la chambre
	 * @param surveillant  Infirmier surveillant la chambre
	 * @param serviceRattache Service auquel est rattaché la chambre
	 */
	public Chambre( Long numeroChambre, Integer nombreLits, Infirmier surveillant, Service serviceRattache ) {
		this.no_chambre = numeroChambre;
		this.nb_lits = nombreLits;
		setSurveillant( surveillant );
		setServiceRattache( serviceRattache );
	}

	/**
	 * Récupère le numéro de la chambre
	 * @return Numéro de la chambre
	 */
	public Long getNumeroChambre() {
		return no_chambre;
	}

	/**
	 * Récupère le nombre de lits dans la chambre
	 * @return Nombre de lits dans la chambre
	 */
	public Integer getNombreLits() {
		return nb_lits;
	}

	/**
	 * Modifie le nombre de lits dans la chambre
	 * @param nb_lits Nombre de lits dans la chambre
	 */
	public void setNombreLits( Integer nb_lits ) {
		this.nb_lits = nb_lits;
	}

	/**
	 * Récupère l'infirmier surveillant la chambre
	 * @return Infirmier surveillant la chambre
	 */
	public Infirmier getSurveillant() {
		// TODO : Récupération du surveillant de chambre
		return null;
	}

	/**
	 * Modifie l'infirmier surveillant la chambre
	 * @param surveillant Infirmier surveillant la chambre
	 */
	public void setSurveillant( Infirmier surveillant ) {
		// TODO : Modification du surveillant de chambre
	}

	/**
	 * Récupère le service auquel est rattaché la chambre
	 * @return Service auquel est rattaché la chambre
	 */
	public Service getServiceRattache() {
		// TODO : Récupération du service auquel est rattaché la chambre
		return null;
	}

	/**
	 * Modifie le service auquel est rattaché la chambre
	 * @param service Nouveau service auquel est rattaché la chambre
	 */
	public void setServiceRattache( Service service ) {
		// TODO : Modification du service auquel est rattaché la chambre
	}

	/**
	 * Récupère les hospitalisations liées à cette chambre
	 * @return Liste des hospitalisations liées à la chambre
	 */
	public Service getHospitalisations() {
		// TODO : Récupération des hospitalisations d'une chambre
		return null;
	}

	/**
	 * Génère une représentation textuelle de la chambre
	 * @return Représentation textuelle de la chambre
	 */
	@Override
	public String toString() {
		return "Chambre{" +
				"no_chambre=" + getNumeroChambre() +
				", nb_lits=" + getNombreLits() +
				", surveillant=" + getSurveillant() +
				", service=" + getServiceRattache() +
				'}';
	}
}
