package ece.ing3.java.projet.vue.components;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ModelToolbar extends JToolBar {
	private JButton buttonRemove, buttonAdd, buttonModify, buttonSearch, buttonRefresh;

	public ModelToolbar( String modelName ) {
		try {
			buttonRemove = new JButton( new ImageIcon( Utils.getImageResource( Constants.RESOURCE_PATH_ICON_REMOVE ) ) );
			buttonAdd = new JButton( new ImageIcon( Utils.getImageResource( Constants.RESOURCE_PATH_ICON_ADD ) ) );
			buttonModify = new JButton( new ImageIcon( Utils.getImageResource( Constants.RESOURCE_PATH_ICON_MODIFY ) ) );
			buttonSearch = new JButton( new ImageIcon( Utils.getImageResource( Constants.RESOURCE_PATH_ICON_SEARCH ) ) );
			buttonRefresh = new JButton( new ImageIcon( Utils.getImageResource( Constants.RESOURCE_PATH_ICON_REFRESH ) ) );
		} catch( IOException e ) {
			throw new RuntimeException( "Resource not found.\n" + e.getLocalizedMessage(), e );
		}

		JLabel label = new JLabel( modelName );
		label.setFont( new Font( label.getName(), Font.BOLD, 18 ) );
		add( label );

		add( Box.createHorizontalGlue() );

		add( buttonRefresh );
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

	public JButton getButtonRefresh() {
		return buttonRefresh;
	}

	public void addActionListener( ActionListener actionListener ) {
		buttonRemove.addActionListener( actionListener );
		buttonAdd.addActionListener( actionListener );
		buttonModify.addActionListener( actionListener );
		buttonSearch.addActionListener( actionListener );
		buttonRefresh.addActionListener( actionListener );
	}
}
