package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

import javax.swing.*;

/**
 * Base de boîte de dialogue de saisie liés aux attributs d'un modèle BDD
 */
public abstract class BaseModelInputDialog extends BaseValidatedDialog {
	protected ModelInputList inputList;

	/**
	 * Créer une nouvelle boîte de dialogue de saisie liés aux attributs d'un modèle BDD
	 */
	public BaseModelInputDialog() {
		super( Application.get() );
		this.inputList = this.build();
		init();
	}

	/**
	 * Récupère le contenu de la boîte de dialogue.
	 *
	 * @return Contenu de la boîte de dialogue, ici la liste {@link ModelInputList}
	 */
	@Override
	protected JComponent getContent() {
		return this.inputList;
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle BDD considéré.
	 *
	 * @return Liste des champs de saisie
	 */
	public abstract ModelInputList build();
}
