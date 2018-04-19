package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.enums.Specialite;

import javax.swing.*;

public class SpecialiteInput extends JPanel implements BaseInput {
	private JComboBox<Specialite> comboBox;
	private String columnName;

	public SpecialiteInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
		this.comboBox = new JComboBox<>( Specialite.values() );
		add( this.comboBox );
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public boolean isFilled() {
		return comboBox.getSelectedItem() != null;
	}

	@Override
	public Object getValue() {
		try {
			return ( isFilled() ? comboBox.getSelectedItem().toString() : null );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}

	@Override
	public void setValue( Object value ) throws IllegalArgumentException {
		comboBox.setSelectedItem( Specialite.valueOf( String.valueOf( value ) ) );
	}
}
