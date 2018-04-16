package ece.ing3.java.projet.vue.components.inputs;

import javax.swing.*;

public class StringInput extends JTextField implements BaseInput {
	private String columnName;

	public StringInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public boolean isFilled() {
		return getText().length() > 0;
	}

	@Override
	public Object getValue() {
		return getText();
	}
}
