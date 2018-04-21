package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.ValueChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class StringInput extends JPanel implements BaseInput<String>, DocumentListener {
	private String columnName;
	protected JTextField textField;
	protected JComboBox<String> selector;
	private ValueChangeListener valueChangeListener;

	public StringInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
		this.textField = new JTextField();
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		if( isSearch ) {
			this.selector = new JComboBox<>( new String[]{ "=", "~=" } );
			add( this.selector, BorderLayout.EAST );
		}
		this.valueChangeListener = null;
		this.textField.getDocument().addDocumentListener( this );
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
	public String getValue() {
		return textField.getText();
	}

	@Override
	public String[] getValues() throws IllegalArgumentException {
		return new String[]{ getValue() };
	}

	@Override
	public void setValue( String value ) throws IllegalArgumentException {
		textField.setText( value );
		triggerValueListener();
	}

	@Override
	public void setRawValue( Object value ) throws IllegalArgumentException {
		setValue( String.valueOf( value ) );
	}

	@Override
	public Where getWhere() throws IllegalArgumentException {
		String selector = ( this.selector.getSelectedItem() != null ? (String) this.selector.getSelectedItem() : "=" );
		if( selector.equals( "~=" ) ) {
			return new Where( getColumnName(), "LIKE", "%" + getValue() + "%" );
		}
		return new Where( getColumnName(), "=", getValue() );
	}

	@Override
	public void addValueChangeListener( ValueChangeListener valueChangeListener ) {
		this.valueChangeListener = valueChangeListener;
	}

	@Override
	public void insertUpdate( DocumentEvent documentEvent ) {
		triggerValueListener();
	}

	@Override
	public void removeUpdate( DocumentEvent documentEvent ) {
		triggerValueListener();
	}

	@Override
	public void changedUpdate( DocumentEvent documentEvent ) {
		triggerValueListener();
	}

	private void triggerValueListener() {
		if( this.valueChangeListener != null ) {
			this.valueChangeListener.onValueChanged( getValues() );
		}
	}
}
