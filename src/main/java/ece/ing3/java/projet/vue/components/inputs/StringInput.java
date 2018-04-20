package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;

import javax.swing.*;
import java.awt.*;

public class StringInput extends JPanel implements BaseInput {
	private String columnName;
	protected JTextField textField;
	protected JComboBox<String> selector;

	public StringInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
		this.textField = new JTextField();
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		if( isSearch ) {
			this.selector = new JComboBox<>( new String[]{ "=", "~=" } );
			add( this.selector, BorderLayout.EAST );
		}
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
		return textField.getText();
	}

	@Override
	public void setValue( Object value ) throws IllegalArgumentException {
		textField.setText( String.valueOf( value ) );
	}

	@Override
	public Where getWhere() throws IllegalArgumentException {
		String selector = ( this.selector.getSelectedItem() != null ? (String) this.selector.getSelectedItem() : "=" );
		if( selector.equals( "~=" ) ) {
			return new Where( getColumnName(), "LIKE", "%" + getValue() + "%" );
		}
		return new Where( getColumnName(), "=", getValue() );
	}
}
