package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;

import javax.swing.*;
import java.awt.*;

public abstract class NumericInput extends JPanel implements BaseInput {
	protected JTextField textField;
	protected JComboBox<String> selector;

	public NumericInput( boolean isSearch ) {
		this.textField = new JTextField();
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		if( isSearch ) {
			this.selector = new JComboBox<>( new String[]{ "=", "<", ">", "<=", ">=" } );
			add( this.selector, BorderLayout.EAST );
		}
	}

	@Override
	public boolean isFilled() {
		return textField.getText().length() > 0;
	}

	public String getTextValue() {
		return textField.getText();
	}

	@Override
	public void setValue( Object value ) throws IllegalArgumentException {
		textField.setText( String.valueOf( value ) );
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
}
