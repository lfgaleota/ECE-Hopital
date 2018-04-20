package ece.ing3.java.projet.vue.components.inputs;

import javax.swing.*;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.enums.Rotation;
import ece.ing3.java.projet.utils.Constants;

import java.awt.*;

public class RotationInput extends JPanel implements BaseInput {
	private JComboBox<Rotation> comboBox;
	private JList<Rotation> list;
	private String columnName;

	public RotationInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
		if( !isSearch ) {
			this.comboBox = new JComboBox<>( Rotation.values() );
			add( this.comboBox );
		} else {
			DefaultListModel<Rotation> listModel = new DefaultListModel<>();
			for( Rotation Rotation : Rotation.values() ) {
				listModel.addElement( Rotation );
			}
			this.list = new JList<>( listModel );
			this.list.setVisibleRowCount( Math.min( Constants.UI_INPUTLIST_MAXVISIBLEENUMITEMS, listModel.size() ) );
			JScrollPane scrollPane = new JScrollPane( this.list );
			add( scrollPane );
		}
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
		return ( comboBox != null && comboBox.getSelectedItem() != null ) || ( list != null && list.getSelectedIndices().length > 0 );
	}

	@Override
	public Object getValue() {
		try {
			return ( comboBox != null && comboBox.getSelectedItem() != null ? comboBox.getSelectedItem() : null );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}

	@Override
	public void setValue( Object value ) throws IllegalArgumentException {
		if( comboBox != null ) {
			comboBox.setSelectedItem( Rotation.valueOf( String.valueOf( value ) ) );
		} else {
			list.setSelectedValue( value, true );
		}
	}

	@Override
	public Where getWhere() throws IllegalArgumentException {
		if( list != null ) {
			Where whereClause = new Where();
			for( Rotation rot : list.getSelectedValuesList() ) {
				whereClause.or( getColumnName(), "=", rot.toString() );
			}
			return whereClause;
		}
		return new Where( getColumnName(), "=", getValue() );
	}
}
