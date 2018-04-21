package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.ValueChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public abstract class NumericInput<T> extends JPanel implements BaseInput<T>, DocumentListener {
	protected JTextField textField;
	protected JComboBox<String> selector;
	private ValueChangeListener valueChangeListener;

	public NumericInput( boolean isSearch ) {
		this.textField = new JTextField();
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		if( isSearch ) {
			this.selector = new JComboBox<>( new String[]{ "=", "<", ">", "<=", ">=" } );
			add( this.selector, BorderLayout.EAST );
		}
		this.valueChangeListener = null;
		this.textField.getDocument().addDocumentListener( this );
	}

	@Override
	public boolean isFilled() {
		return textField.getText().length() > 0;
	}

	public String getTextValue() {
		return textField.getText();
	}

	@Override
	public void setValue( T value ) throws IllegalArgumentException {
		textField.setText( String.valueOf( value ) );
		triggerValueListener();
	}

	@Override
	public Where getWhere() throws IllegalArgumentException {
		String selector = ( this.selector.getSelectedItem() != null ? (String) this.selector.getSelectedItem() : "=" );
		return new Where( getColumnName(), selector, getValue() );
	}

	@Override
	public void setPreferredSize( Dimension size ) {
		super.setPreferredSize( new Dimension( size.width, getPreferredSize().height ) );
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
