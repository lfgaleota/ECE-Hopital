package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.enums.JDBCDriver;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.components.FlexibleGridLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationPanel extends JPanel {
	private JButton save;
	private JTextField databaseUrl, databaseUsername;
	private JComboBox<JDBCDriver> databaseDriver;
	private JPasswordField databasePassword;

	public ConfigurationPanel() {
		setLayout( new BorderLayout() );

		save = new JButton( Strings.get( "dialog.submit.save" ) );
		databaseUrl = new JTextField();
		databaseUsername = new JTextField();
		databasePassword = new JPasswordField();
		databaseDriver = new JComboBox<>( JDBCDriver.values() );

		databaseUrl.setPreferredSize( Constants.UI_DIALOGCOMPONENT_PREFERREDSIZE );
		databaseUsername.setPreferredSize( Constants.UI_DIALOGCOMPONENT_PREFERREDSIZE );
		databasePassword.setPreferredSize( Constants.UI_DIALOGCOMPONENT_PREFERREDSIZE );
		databaseDriver.setPreferredSize( Constants.UI_DIALOGCOMPONENT_PREFERREDSIZE );

		setValues();

		JPanel panel = new JPanel();
		panel.setLayout( new FlexibleGridLayout( 4, 2 ) );

		panel.add( new JLabel( Strings.get( "configuration.database.url" ) ) );
		panel.add( databaseUrl );
		panel.add( new JLabel( Strings.get( "configuration.database.username" ) ) );
		panel.add( databaseUsername );
		panel.add( new JLabel( Strings.get( "configuration.database.password" ) ) );
		panel.add( databasePassword );
		panel.add( new JLabel( Strings.get( "configuration.database.driver" ) ) );
		panel.add( databaseDriver );

		add( panel, BorderLayout.CENTER );
		add( save, BorderLayout.PAGE_END );
	}

	public JButton getSave() {
		return save;
	}

	private void setValues() {
		databaseUrl.setText( Configuration.getString( "database.url", "" ) );
		databaseUsername.setText( Configuration.getString( "database.username", "" ) );
		databasePassword.setText( Configuration.getString( "database.password", "" ) );
		try {
			databaseDriver.setSelectedItem( JDBCDriver.parseClassName( Configuration.getString( "database.driver", "" ) ) );
		} catch( IllegalArgumentException e ) {
			databaseDriver.setSelectedIndex( 0 );
		}
	}

	public Map<String, Object> getValues() {
		Map<String, Object> map = new HashMap<>();
		map.put( "database.url", databaseUrl.getText() );
		map.put( "database.username", databaseUsername.getText() );
		map.put( "database.password", String.valueOf( databasePassword.getPassword() ) );
		map.put( "database.driver", ( databaseDriver.getSelectedItem() != null ? ( (JDBCDriver) databaseDriver.getSelectedItem() ).getClassName() : null ) );
		return map;
	}

	public void addActionListener( ActionListener actionListener ) {
		this.save.addActionListener( actionListener );
	}
}
