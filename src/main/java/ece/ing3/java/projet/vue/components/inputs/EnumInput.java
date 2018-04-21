package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.ValueChangeListener;
import ece.ing3.java.projet.utils.Constants;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public abstract class EnumInput<T> extends JPanel implements BaseInput<T>, ActionListener, ListSelectionListener {
	protected JComboBox<T> comboBox;
	protected JList<T> list;
	protected String columnName;
	private ValueChangeListener valueChangeListener;

	public EnumInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
		if( !isSearch ) {
			this.comboBox = new JComboBox<>( getEnumValues() );
			this.comboBox.addActionListener( this );
			add( this.comboBox );
		} else {
			DefaultListModel<T> listModel = new DefaultListModel<>();
			for( T val : getEnumValues() ) {
				listModel.addElement( val );
			}
			this.list = new JList<>( listModel );
			this.list.setVisibleRowCount( Math.min( Constants.UI_INPUTLIST_MAXVISIBLEENUMITEMS, listModel.size() ) );
			this.list.addListSelectionListener( this );
			JScrollPane scrollPane = new JScrollPane( this.list );
			add( scrollPane );
		}
		this.valueChangeListener = null;
	}

	protected abstract T[] getEnumValues();

	protected abstract T getEnumValueOf( String value );

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
	@SuppressWarnings( "unchecked" )
	public T getValue() {
		if( comboBox != null ) {
			return (T) comboBox.getSelectedItem();
		}
		if( list != null ) {
			return list.getSelectedValue();
		}
		return null;
	}

	@Override
	public void setValue( T value ) throws IllegalArgumentException {
		if( comboBox != null ) {
			comboBox.setSelectedItem( getEnumValueOf( String.valueOf( value ) ) );
		} else {
			list.setSelectedValue( value, true );
		}
		triggerValueListener();
	}

	@Override
	public Where getWhere() throws IllegalArgumentException {
		if( list != null ) {
			Where whereClause = new Where();
			for( T val : list.getSelectedValuesList() ) {
				whereClause.or( getColumnName(), "=", val.toString() );
			}
			return whereClause;
		}
		return new Where( getColumnName(), "=", getValue() );
	}

	@Override
	public void addValueChangeListener( ValueChangeListener valueChangeListener ) {
		this.valueChangeListener = valueChangeListener;
	}

	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == this.comboBox ) {
			triggerValueListener();
		}
	}

	@Override
	public void valueChanged( ListSelectionEvent listSelectionEvent ) {
		if( listSelectionEvent.getSource() == this.list ) {
			triggerValueListener();
		}
	}

	@Override
	public T[] getValues() throws IllegalArgumentException {
		if( this.list != null ) {
			return this.list.getSelectedValuesList().toArray( getEnumValues() );
		}
		if( getValue() != null ) {
			LinkedList<T> lst = new LinkedList<T>();
			lst.add( getValue() );
			return lst.toArray( getEnumValues() );
		}
		return null;
	}

	private void triggerValueListener() {
		if( this.valueChangeListener != null ) {
			this.valueChangeListener.onValueChanged( getValues() );
		}
	}
}
