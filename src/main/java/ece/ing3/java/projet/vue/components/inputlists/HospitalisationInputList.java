package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.vue.components.inputs.*;

import java.awt.*;

/**
 * Ensemble de champs de saisie pour la saisie des attributs d'un modèle Hospitalisation
 * <p>
 * Cette classe dispose de quelques aménagements pour permettre de conditionner la saisie des chambres par la saisie du/des Service(s).
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class HospitalisationInputList extends ModelInputList {
	private final ChambreInput chambreInput;

	/**
	 * Créer un nouveau ensemble de champs de saisie pour la saisie des attributs d'un modèle Hospitalisation.
	 *
	 * @param isSearch {@code true} si les champs seront utilisés pour de la recherche
	 * @param parent   Fenêtre parente de l'ensemble de champs
	 */
	public HospitalisationInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
		chambreInput = (ChambreInput) getInputs().get( "numeroChambre" );
		getInputs().get( "codeService" ).addValueChangeListener( newList -> chambreInput.setCodesService( (String[]) newList ) );
	}

	/**
	 * Récupère la classe du modèle BDD associé à l'ensemble.
	 *
	 * @return Classe du modèle BDD
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}

	/**
	 * Construit, pour un attribut du modèle BDD, le champ de saisie implémentant {@link BaseInput} associé.
	 *
	 * @param fieldName  Nom de l'attribut
	 * @param columnName Nom de la colonne associée à l'attribut
	 * @param isSearch   {@code true} si le champ sera utilisé pour de la recherche
	 * @param parent     Fenêtre parente du champ
	 * @return Champ de saisie
	 */
	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch, Window parent ) {
		switch( fieldName ) {
			case "numeroMalade":
				return new MaladeIdInput( columnName, isSearch, parent );
			case "codeService":
				return new ServiceInput( columnName, isSearch, parent );
			case "numeroChambre":
				return new ChambreInput( columnName, isSearch, parent );
			case "numeroLit":
				return new IntegerInput( columnName, isSearch );
		}

		return null;
	}
}
