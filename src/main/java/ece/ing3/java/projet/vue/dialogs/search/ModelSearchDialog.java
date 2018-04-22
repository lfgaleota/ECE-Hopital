package ece.ing3.java.projet.vue.dialogs.search;

import javax.swing.*;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;

import java.awt.event.ActionListener;

/**
 * Boîte de dialogue pour la recherche et filtrage d'instance de modèle BDD de la base de donnée, avec la saisie des attributs à filtrer.
 * <p>
 * Génère une clause {@link Where} pour le filtrage des résultats dans une requête.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public abstract class ModelSearchDialog extends BaseModelInputDialog {
	private JButton reset;

	private boolean isResetFilters;

	/**
	 * Créer une nouvelle boîte de dialogue de recherche/filtrage.
	 */
	public ModelSearchDialog() {
		super();
		this.isResetFilters = false;
		this.reset = new JButton( "Tout" );
		bottom.add( reset );
		pack();
	}

	/**
	 * Récupère le titre de la boîte de dialogue.
	 *
	 * @return Titre de la boîte de dialogue
	 */
	@Override
	public String getTitle() {
		return "Recherche";
	}

	/**
	 * Récupère le texte du bouton de validation de la boîte de dialogue.
	 *
	 * @return Texte du bouton de validation
	 */
	@Override
	protected String getSubmitLabel() {
		return "Rechercher";
	}

	/**
	 * Récupère le bouton de réinitialisation de filtre de la boîte de dialogue.
	 *
	 * @return Bouton de réinitialisation de filtre
	 */
	public JButton getReset() {
		return reset;
	}

	/**
	 * Ajoute un {@link ActionListener} aux boutons de la fenêtre, dont le bouton de réinitialisation.
	 *
	 * @param actionListener Instance d'{@link ActionListener} à ajouter
	 */
	@Override
	public void addActionListener( ActionListener actionListener ) {
		super.addActionListener( actionListener );
		reset.addActionListener( actionListener );
	}

	/**
	 * Récupère la clause Where de filtrage associée à la saisie de l'utilisateur.
	 *
	 * @return Clause Where de filtrage de la boîte de dialogue
	 */
	public Where getWhereClause() {
		Where where = new Where();
		for( BaseInput input : inputList.getInputs().values() ) {
			if( input.isFilled() ) {
				where.and( input.getWhere() );
			}
		}
		return where;
	}

	/**
	 * Valide la saisie utilisateur.
	 *
	 * @throws IllegalArgumentException La saisie est invalide
	 */
	@Override
	public void validateContent() throws IllegalArgumentException {
		getWhereClause();
	}

	/**
	 * Retourne si on doit réinitalisé les filtres.
	 *
	 * @return {@code true} il faut réinitialiser les filtres
	 */
	public boolean isResetFilters() {
		return isResetFilters;
	}

	/**
	 * Indique si on doit réinitalisé les filtres.
	 *
	 * @param resetFilters s'il faut réinitialiser les filtres
	 */
	public void setResetFilters( boolean resetFilters ) {
		isResetFilters = resetFilters;
	}
}
