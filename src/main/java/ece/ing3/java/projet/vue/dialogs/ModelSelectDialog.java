package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Boîte de dialogue de sélection d'instance de de modèle BDD
 *
 * @param <M> Type de modèle BDD considéré
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ModelSelectDialog<M extends Model> extends BaseValidatedDialog {
	private ModelPanel<M> panel;

	/**
	 * Créer une nouvelle boîte de dialogue de sélection d'instance de de modèle BDD, encapsulant un panneau d'affichage, sélection, recherche et modification de modèle BDD.
	 *
	 * @param parent Fenêtre parente à la boîte de dialogue
	 * @param panel  Panneau d'affichage, sélection, recherche et modification de modèle BDD à encapsuler
	 */
	public ModelSelectDialog( Window parent, ModelPanel<M> panel ) {
		super( parent );

		this.panel = panel;

		this.panel.getToolbar().removeAll();
		this.panel.getToolbar().add( this.panel.getToolbar().getButtonRefresh() );
		this.panel.getToolbar().add( this.panel.getToolbar().getButtonSearch() );

		init();

		( (JPanel) getContentPane() ).setBorder( new EmptyBorder( Constants.UI_DIALOGSELECT_MARGIN ) );
		this.setPreferredSize( Constants.UI_DIALOGSELECT_SIZE );
		this.pack();
	}

	/**
	 * Récupère le contenu de la boîte de dialogue.
	 *
	 * @return Contenu de la boîte de dialogue
	 */
	@Override
	protected JComponent getContent() {
		return panel;
	}

	/**
	 * Récupère le titre de la boîte de dialogue.
	 *
	 * @return Titre de la boîte de dialogue
	 */
	@Override
	public String getTitle() {
		return "Sélection";
	}

	/**
	 * Récupère le texte du bouton de validation de la boîte de dialogue.
	 *
	 * @return Texte du bouton de validation
	 */
	@Override
	protected String getSubmitLabel() {
		return "Sélectionner";
	}

	/**
	 * Vérifie qu'il y a au moins une instance de sélectionner avant validation.
	 *
	 * @throws IllegalArgumentException Il n'y a pas de sélection.
	 */
	public void validateContent() throws IllegalArgumentException {
		if( panel.getList().getSelectedRowCount() == 0 ) {
			throw new IllegalArgumentException( "Aucune sélection." );
		}
	}

	/**
	 * Récupère le panneau d'affichage, sélection, recherche et modification de modèle BDD sous-jacent.
	 *
	 * @return Panneau d'affichage, sélection, recherche et modification de modèle BDD sous-jacent
	 */
	public ModelPanel<M> getPanel() {
		return panel;
	}
}
