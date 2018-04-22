package ece.ing3.java.projet.vue.dialogs.delete;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.Application;

import javax.swing.*;
import java.util.List;

/**
 * Boîte de dialogue demandant à l'utilisateur s'il souhaite supprimer un certain ensemble d'instance de modèle BDD de la base de donnée.
 */
public class ModelDeleteDialog extends JDialog {
	private List<? extends Model> selectedModels;
	private JOptionPane optionPane;

	/**
	 * Créer une nouvelle boîte de dialogue de suppression.
	 *
	 * @param selectedModels Instances de modèle BDD sélectionnés pour la suppression
	 * @throws IllegalArgumentException Il n'y a aucune instance de modèle dans la liste
	 */
	public ModelDeleteDialog( List<? extends Model> selectedModels ) throws IllegalArgumentException {
		super( Application.get() );

		this.selectedModels = selectedModels;

		if( selectedModels.size() == 0 ) {
			throw new IllegalArgumentException( "There should be a least one model selected" );
		}

		optionPane = new JOptionPane(
				generateMessage(),
				JOptionPane.WARNING_MESSAGE,
				JOptionPane.YES_NO_OPTION
		);

		setContentPane( optionPane );

		setLocationRelativeTo( null );

		pack();
		setVisible( true );
	}

	private String generateMessage() {
		String modelName = Strings.getModel( Model.getTableName( selectedModels.get( 0 ).getClass() ), selectedModels.size() > 1 ).toLowerCase();
		return Strings.get( "dialog.model.remove" ).replaceAll( "<nb>", Integer.toString( selectedModels.size() ) ).replaceAll( "<modelName>", modelName );
	}

	/**
	 * Récupère l'ensemble des instances de modèle BDD sélectionnés pour la suppression.
	 *
	 * @return Ensemble des instances de modèle BDD sélectionnés pour la suppression
	 */
	public List<? extends Model> getSelectedModels() {
		return selectedModels;
	}

	/**
	 * Récupère le panneau de demande sous-jacent.
	 *
	 * @return Panneau de demande sous-jacent
	 */
	public JOptionPane getOptionPane() {
		return optionPane;
	}
}
