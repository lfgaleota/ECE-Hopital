package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.panels.ChambrePanelController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import javax.swing.border.Border;
import java.awt.*;

/**
 * Champ de saisie pour sélectionner des instances de modèle {@link Chambre}, stockés en base de donnée, avec possibilité de sélection multiple pour la recherche
 * <p>
 * Ce champ peut être conditionné par un ou plusieurs codes de services, afin de limiter la sélection des chambres à ces services.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ChambreInput extends SearchInput<Chambre, Long> {
	private String[] codesService;
	private final Border borderDefaultButton;

	/**
	 * Créer un nouveau champ de saisie pour sélectionner des instances de modèle {@link Chambre}.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 * @param parent     Fenêtre parente de l'ensemble du champ, utilisé pour la boîte de dialogue de recherche
	 */
	public ChambreInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
		this.borderDefaultButton = this.search.getBorder();
	}

	/**
	 * Créer le panneau d'affichage, de recherche et sélection du modèle {@link Chambre} et le modifie pour prendre en compte les possibles valeurs de codes de service assignées à ce champ par {@link ChambreInput#setCodesService(String[])}.
	 *
	 * @return Panneau d'affichage, de recherche et sélection du modèle {@link Chambre}
	 */
	@Override
	protected ModelPanel<Chambre> createController() {
		return ( new ChambrePanelController() {
			@Override
			public Where queryModifyWhereClause( Where whereClause ) {
				whereClause = super.queryModifyWhereClause( whereClause );
				if( codesService != null ) {
					if( whereClause == null ) {
						whereClause = new Where();
					}
					Where inner = new Where();
					for( String codeService : codesService ) {
						inner.or( Model.getColumnNameFromFieldName( Chambre.class, "codeServiceRattache" ), "=", codeService );
					}
					whereClause.and( inner );
				}
				return whereClause;
			}
		} ).getPanel();
	}

	/**
	 * Récupère un tableau vide de type du modèle BDD Chambre.
	 *
	 * @return Tableau vide de type du modèle BDD Chambre
	 * @implNote Cette mléthode est utilisée pour faire de la conversion de {@link java.util.List} en tableau statique. Seul le type importe pour cette opération, et non la taille.
	 */
	@Override
	protected Long[] getEmptyArray() {
		return new Long[ 0 ];
	}

	/**
	 * Récupère la valeur du champ associée à une instance de modèle BDD.
	 *
	 * @param model Instance de modèle BDD
	 * @return Valeur du champ associée
	 */
	@Override
	protected Long getValueFromModel( Chambre model ) {
		return model.getNumeroChambre();
	}

	/**
	 * Créer une instance de modèle BDD à partir de la valeur du champ fournie.
	 *
	 * @param value Valeur fournie
	 * @return Modèle BDD correspondant
	 * @throws IllegalArgumentException La valeur fgournie est invalide
	 */
	@Override
	protected Chambre createInstanceFromValue( Long value ) throws IllegalArgumentException {
		Chambre ch = new Chambre();
		ch.setNumeroChambre( value );
		return ch;
	}

	/**
	 * Déclenche l'événement de mise à jour du champ. Met une bordure autour du bouton de recherche pour indiquer la réinitialisation de la valeur.
	 */
	@Override
	protected void triggerValueListener() {
		super.triggerValueListener();
		this.search.setBorder( borderDefaultButton );
	}

	/**
	 * Modifie les codes de service utilisé pour conditionner ce champ et le panneau de sélection. Réinitialise automatiquement sa valeur pour éviter de sélectionenr une chambre n'appartenant pas à un des services indiqués.
	 *
	 * @param codes Nouveaux codes de service pour la sélection
	 */
	public void setCodesService( String[] codes ) {
		this.codesService = codes;
		resetValue();
		this.search.setBorder( Constants.UI_INPUTLIST_INVALIDVALUE_BORDER );
	}
}
