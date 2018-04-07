package ece.ing3.java.projet.modele.hopital;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;

/**
 * Modèle d'une Hospitalisation stocké en base de donnée
 */
public class Hospitalisation extends Model {
	private Integer lit;

	/**
	 * Créer une nouvelle hospitalisation
	 *
	 * @param malade  Malade lié à l'hospitalisation
	 * @param service Service lié à l'hospitalisation
	 * @param chambre Chambre liée à l'hospitalisation
	 * @param lit     Numéro de lit occupé par le malade
	 */
	public Hospitalisation( Malade malade, Service service, Chambre chambre, Integer lit ) {
		this.lit = lit;
		setMalade( malade );
		setService( service );
		setChambre( chambre );
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
	 * Récupère le malade lié à l'hospitalisation
	 *
	 * @return Malade lié à l'hospitalisation
	 */
	public Malade getMalade() {
		// TODO : Récupération du malade d'une hospitalisation
		return null;
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
	 */
	public Service getService() {
		// TODO : Récupération du service d'une hospitalisation
		return null;
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
	 */
	public Chambre getChambre() {
		// TODO : Récupération du chambre d'une hospitalisation
		return null;
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
	 * Génère une représentation textuelle de l'hospitalisation
	 *
	 * @return Représentation textuelle de l'hospitalisation
	 */
	@Override
	public String toString() {
		return "Hospitalisation{" +
				"lit=" + lit +
				'}';
	}
}
