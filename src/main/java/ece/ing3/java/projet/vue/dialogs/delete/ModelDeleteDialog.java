package ece.ing3.java.projet.vue.dialogs.delete;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.Application;

import javax.swing.*;
import java.util.List;

public class ModelDeleteDialog extends JDialog {
	private List<? extends Model> selectedModels;
	private JOptionPane optionPane;

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

		pack();
		setVisible( true );
	}

	private String generateMessage() {
		String modelName = Strings.getModel( Model.getTableName( selectedModels.get( 0 ).getClass() ), selectedModels.size() > 1 ).toLowerCase();
		return Strings.get( "dialog.model.remove" ).replaceAll( "<nb>", Integer.toString( selectedModels.size() ) ).replaceAll( "<modelName>", modelName );
	}

	public List<? extends Model> getSelectedModels() {
		return selectedModels;
	}

	public JOptionPane getOptionPane() {
		return optionPane;
	}
}
