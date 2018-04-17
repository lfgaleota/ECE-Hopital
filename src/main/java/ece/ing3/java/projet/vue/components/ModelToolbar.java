package ece.ing3.java.projet.vue.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModelToolbar extends JToolBar {
	private JButton buttonRemove, buttonAdd, buttonModify, buttonSearch, buttonStats;

	public ModelToolbar( String modelName ) {
		buttonRemove = new JButton( "Supprimer" );
		buttonAdd = new JButton( "Ajouter" );
		buttonModify = new JButton( "Modifier" );
		buttonSearch = new JButton( "Rechercher" );
		buttonStats = new JButton( "Statistiques" );

		JLabel label = new JLabel( modelName );
		label.setFont( new Font( label.getName(), Font.BOLD, 18 ) );
		add( label );
		add( Box.createHorizontalGlue() );
		add( buttonStats );
		add( buttonSearch );
		add( buttonAdd );
		add( buttonModify );
		add( buttonRemove );
	}

	public JButton getButtonRemove() {
		return buttonRemove;
	}

	public JButton getButtonAdd() {
		return buttonAdd;
	}

	public JButton getButtonModify() {
		return buttonModify;
	}

	public JButton getButtonSearch() {
		return buttonSearch;
	}

	public JButton getButtonStats() {
		return buttonStats;
	}

	public void addActionListener( ActionListener actionListener ) {
		buttonRemove.addActionListener( actionListener );
		buttonAdd.addActionListener( actionListener );
		buttonModify.addActionListener( actionListener );
		buttonSearch.addActionListener( actionListener );
		buttonStats.addActionListener( actionListener );
	}
}
