package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.dialogs.ModelSelectDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.interfaces.ValueChangeListener;
import ece.ing3.java.projet.vue.dialogs.ModelSelectDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public abstract class SearchInput<M extends Model, T> extends JPanel implements BaseInput<T>, DialogListener {
	private String columnName;
	protected JTextField textField;
	protected JButton search;
	protected ModelSelectDialog<M> dialogSelect;
	protected List<M> selectedModels;
	protected ValueChangeListener valueChangeListener;

	public SearchInput( String columnName, boolean isSearch, Window parent ) {
		this.dialogSelect = null;
		this.columnName = columnName;
		this.textField = new JTextField();
		this.textField.setEnabled( false );
		this.valueChangeListener = null;
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		search = new JButton( "Rechercher" );
		add( search, BorderLayout.LINE_END );
		search.addActionListener( actionEvent -> {
			if( this.dialogSelect == null ) {
				ModelSelectDialogController<M> controller = new ModelSelectDialogController<>( parent, createController(), this );
				this.dialogSelect = controller.getDialog();
			} else {
				this.dialogSelect.toFront();
			}
		} );
	}

	protected abstract ModelPanel<M> createController();

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
	public T getValue() {
		return ( this.selectedModels != null && this.selectedModels.size() > 0 ? getValueFromModel( this.selectedModels.get( 0 ) ) : null );
	}

	protected abstract T[] getEmptyArray();

	protected abstract T getValueFromModel( M model );

	protected abstract M createInstanceFromValue( T value ) throws IllegalArgumentException;

	@Override
	public void setValue( T value ) throws IllegalArgumentException {
		M model = createInstanceFromValue( value );
		this.selectedModels = new LinkedList<>();
		this.selectedModels.add( model );
		this.textField.setText( String.valueOf( getValueFromModel( model ) ) );
		triggerValueListener();
	}

	protected void triggerValueListener() {
		if( this.valueChangeListener != null ) {
			this.valueChangeListener.onValueChanged( getValues() );
		}
	}

	@Override
	public Where getWhere() throws IllegalArgumentException {
		if( this.selectedModels.size() == 0 ) {
			throw new IllegalArgumentException( "No selected model." );
		}
		Where whereClause = new Where();
		for( M model : this.selectedModels ) {
			whereClause.or( this.columnName, "=", getValueFromModel( model ) );
		}
		return whereClause;
	}

	@Override
	public void addValueChangeListener( ValueChangeListener valueChangeListener ) {
		this.valueChangeListener = valueChangeListener;
	}

	@Override
	public void onDialogSubmitted( JDialog dialog ) {
		this.selectedModels = this.dialogSelect.getPanel().getList().getSelecteds();
		this.dialogSelect = null;

		StringBuilder text = new StringBuilder();
		for( M selectedModel : this.selectedModels ) {
			text.append( getValueFromModel( selectedModel ) ).append( "," );
		}
		this.textField.setText( text.toString() );
		triggerValueListener();
	}

	@Override
	public void onDialogCancelled( JDialog dialog ) {
		this.dialogSelect = null;
	}

	@Override
	public T[] getValues() throws IllegalArgumentException {
		if( selectedModels != null && selectedModels.size() > 0 ) {
			LinkedList<T> lst = new LinkedList<>();

			for( M selectedModel : this.selectedModels ) {
				lst.add( getValueFromModel( selectedModel ) );
			}

			return lst.toArray( getEmptyArray() );
		}
		return null;
	}

	public void resetValue() {
		this.textField.setText( "" );
		this.selectedModels = null;
	}
}
