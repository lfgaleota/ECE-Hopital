package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.components.inputs.DocteurIdInput;
import ece.ing3.java.projet.vue.components.inputs.LongIdInput;
import ece.ing3.java.projet.vue.components.inputs.MaladeIdInput;

import java.awt.*;

/**
 * Ensemble de champs de saisie pour la saisie des attributs d'un modèle Soigne/de prise en charge
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class SoigneInputList extends ModelInputList {
	/**
	 * Créer un nouveau ensemble de champs de saisie pour la saisie des attributs d'un modèle Soigne/de prise en charge.
	 *
	 * @param isSearch {@code true} si les champs seront utilisés pour de la recherche
	 * @param parent   Fenêtre parente de l'ensemble de champs
	 */
	public SoigneInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
	}

	/**
	 * Récupère la classe du modèle BDD associé à l'ensemble.
	 *
	 * @return Classe du modèle BDD
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Soigne.class;
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
			case "numeroDocteur":
				return new DocteurIdInput( columnName, isSearch, parent );
			case "numeroMalade":
				return new MaladeIdInput( columnName, isSearch, parent );
		}

		return null;
	}

}
