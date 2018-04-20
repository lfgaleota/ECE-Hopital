package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ModelSelectDialog<M extends Model> extends BaseValidatedDialog {
	private ModelPanel<M> panel;

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

	@Override
	protected JComponent getContent() {
		return panel;
	}

	@Override
	public String getTitle() {
		return "Sélection";
	}

	@Override
	protected String getSubmitLabel() {
		return "Sélectionner";
	}

	public void validateContent() throws IllegalArgumentException {
		if( panel.getList().getSelectedRowCount() == 0 ) {
			throw new IllegalArgumentException( "No selection." );
		}
	}

	public ModelPanel<M> getPanel() {
		return panel;
	}
}
