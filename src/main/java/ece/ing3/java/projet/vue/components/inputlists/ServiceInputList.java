package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.vue.components.inputs.*;

import java.awt.*;

/**
 * Ensemble de champs de saisie pour la saisie des attributs d'un modèle Service
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ServiceInputList extends ModelInputList {
	/**
	 * Créer un nouveau ensemble de champs de saisie pour la saisie des attributs d'un modèle Service.
	 *
	 * @param isSearch {@code true} si les champs seront utilisés pour de la recherche
	 * @param parent   Fenêtre parente de l'ensemble de champs
	 */
	public ServiceInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
	}

	/**
	 * Récupère la classe du modèle BDD associé à l'ensemble.
	 *
	 * @return Classe du modèle BDD
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Service.class;
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
			case "code":
				return new StringIdInput( columnName, isSearch );
			case "nom":
				return new StringInput( columnName, isSearch );
			case "batiment":
				return new StringInput( columnName, isSearch );
			case "numeroDirecteur":
				return new DocteurInput( columnName, isSearch, parent );
		}

		return null;
	}
}
