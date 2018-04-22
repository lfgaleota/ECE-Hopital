package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle BDD de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle BDD à partir de la saisie utilisateur.
 * </p>
 * <p>
 * Cette classe doit être dérivée pour chaque modèle BDD considéré.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public abstract class ModelUpdateDialog<M extends Model> extends BaseModelInputDialog {
	private M model;

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle BDD.
	 */
	public ModelUpdateDialog( M model ) {
		this.model = model;
		setTitle( getTitle() );
		getSubmit().setText( getSubmitLabel() );
		try {
			if( model != null ) {
				fillFromModel( model );
			}
		} catch( IllegalArgumentException e ) {
			dispose();
			e.printStackTrace();
			Utils.error( "Impossible de modifier cette entrée." );
		}
	}

	/**
	 * Retourne si on veut fare un ajout ou non en base de donnée.
	 *
	 * @return {@code true} on veut faire un ajout en base de donnée
	 */
	public boolean isAdd() {
		return model == null;
	}

	/**
	 * Récupère le titre de la boîte de dialogue.
	 *
	 * @return Titre de la boîte de dialogue
	 */
	@Override
	public String getTitle() {
		return ( isAdd() ? "Ajout" : "Modification" );
	}

	/**
	 * Récupère le texte du bouton de validation de la boîte de dialogue.
	 *
	 * @return Texte du bouton de validation
	 */
	@Override
	protected String getSubmitLabel() {
		return ( isAdd() ? "Ajouter" : "Mettre à jour" );
	}

	/**
	 * Construit une nouvelle instance de modèle BDD.
	 *
	 * @return Nouvelle instance de modèle BDD
	 */
	protected abstract M buildNewModel();

	/**
	 * Récupère la classe associée au modèle BDD considéré.
	 *
	 * @return Classe du modèle BDD
	 */
	protected abstract Class<? extends Model> getModelClass();

	/**
	 * Construit un nouveau modèle BDD à partir de la saisie
	 * @return Modèle construit
	 * @throws IllegalArgumentException Au moins une valeur saisie est invalide
	 */
	public M buildModel() throws IllegalArgumentException {
		M model = buildNewModel();
		int fieldsTotal = Model.getFieldNames( getModelClass() ).length, fieldsValid = 0;

		for( Map.Entry<String, BaseInput> input : inputList.getInputs().entrySet() ) {
			if( input.getValue().isFilled() ) {
				fillModel( model, input.getKey(), input.getValue() );
				fieldsValid++;
			}
		}

		if( fieldsTotal != fieldsValid ) {
			throw new IllegalArgumentException( "Il y a uniquement " + fieldsValid + " champ(s) de remplis sur " + fieldsTotal );
		}

		return model;
	}

	/**
	 * Valide la saisie utilisateur.
	 *
	 * @throws IllegalArgumentException La saisie est invalide
	 */
	@Override
	public void validateContent() throws IllegalArgumentException {
		buildModel();
	}

	/**
	 * Rempli la boîte de dialogue à partir d'une instancde de modèle BDD.
	 *
	 * @param model Modèle utilisé pour le remplissage
	 * @throws IllegalArgumentException Au moins une valeur du modèle est invalide
	 */
	public void fillFromModel( M model ) throws IllegalArgumentException {
		for( Map.Entry<String, BaseInput> entry : inputList.getInputs().entrySet() ) {
			try {
				entry.getValue().setRawValue( PropertyUtils.getSimpleProperty( model, entry.getKey() ) );
			} catch( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
				throw new IllegalArgumentException( e );
			}
		}
	}

	/**
	 * Rempli un champ de saisie d'attribut de la boîte de dialogue à partir d'une instance de modèle BDD.
	 *
	 * @param model Modèle utilisé pour le remplissage
	 * @param fieldName Nom de l'attribut
	 * @param input Champ lié à l'attribut
	 * @throws IllegalArgumentException La valeur du modèle est invalide
	 */
	protected void fillModel( M model, String fieldName, BaseInput input ) throws IllegalArgumentException {
		try {
			PropertyUtils.setSimpleProperty( model, fieldName, input.getValue() );
		} catch( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new IllegalArgumentException( e );
		}
	}
}
