package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.dialogs.ModelSelectDialogController;
import ece.ing3.java.projet.controleur.panels.ServicePanelController;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.vue.dialogs.ModelSelectDialog;

import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class ServiceInput extends JPanel implements BaseInput, DialogListener {
	private String columnName;
	protected JTextField textField;
	protected JButton search;
	protected ModelSelectDialog<Service> dialogSelect;
	protected List<Service> selectedModels;

	public ServiceInput( String columnName, boolean isSearch, Window parent ) {
		this.dialogSelect = null;
		this.columnName = columnName;
		this.textField = new JTextField();
		this.textField.setEnabled( false );
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		search = new JButton( "Rechercher" );
		add( search, BorderLayout.LINE_END );
		search.addActionListener( actionEvent -> {
			if( this.dialogSelect == null ) {
				ModelSelectDialogController<Service> controller = new ModelSelectDialogController<>( parent, new ServicePanelController().getPanel(),this );
				this.dialogSelect = controller.getDialog();
			} else {
				this.dialogSelect.toFront();
			}
		});
	}

	@Override
	public void setPreferredSize( Dimension size ) {
		super.setPreferredSize( new Dimension( size.width, getPreferredSize().height ) );
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public boolean isFilled() {
		return textField.getText().length() > 0;
	}

	@Override
	public Object getValue() {
		return ( this.selectedModels != null && this.selectedModels.size() > 0 ? this.selectedModels.get( 0 ).getCode() : "" );
	}

	@Override
	public void setValue( Object value ) throws IllegalArgumentException {
		Service srv = new Service();
		srv.setCode( String.valueOf( value ) );
		this.selectedModels = new LinkedList<>();
		this.selectedModels.add( srv );
		this.textField.setText( srv.getCode() );
	}

	@Override
	public Where getWhere() throws IllegalArgumentException {
		if( this.selectedModels.size() == 0 ) {
			throw new IllegalArgumentException( "No selected model." );
		}
		Where whereClause = new Where();
		for( Service model : this.selectedModels ) {
			whereClause.or( this.columnName, "=", model.getCode() );
		}
		return whereClause;
	}

	@Override
	public void onDialogSubmitted( JDialog dialog ) {
		this.selectedModels = this.dialogSelect.getPanel().getList().getSelecteds();
		this.dialogSelect = null;

		StringBuilder text = new StringBuilder();
		for( Service selectedModel : this.selectedModels ) {
			text.append( selectedModel.getCode() ).append( "," );
		}
		this.textField.setText( text.toString() );
	}

	@Override
	public void onDialogCancelled( JDialog dialog ) {
		this.dialogSelect = null;
	}
}
