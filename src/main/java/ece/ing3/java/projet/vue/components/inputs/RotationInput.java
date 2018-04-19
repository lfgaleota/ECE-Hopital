package ece.ing3.java.projet.vue.components.inputs;

import javax.swing.*;

import ece.ing3.java.projet.enums.Rotation;

public class RotationInput extends JPanel implements BaseInput {
	private JComboBox<Rotation> comboBox;
	private String columnName;

	public RotationInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
		this.comboBox = new JComboBox<>( Rotation.values() );
		add( this.comboBox );
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public boolean isFilled() {
		return comboBox.getSelectedItem() != null ;
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
		comboBox.setSelectedItem( Rotation.valueOf( String.valueOf( value ) ) );
	}
}
