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
	private JPanel container;
	private JButton save;
	private JTextField databaseUrl, databaseUsername;
	private JComboBox<JDBCDriver> databaseDriver;
	private JPasswordField databasePassword;
	boolean showUrl, showUsername, showPassword;

	public ConfigurationPanel() {
		setLayout( new GridBagLayout() );
		JPanel content = new JPanel( new BorderLayout() );

		showUrl = true;
		showUsername = true;
		showPassword = true;

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

		container = new JPanel();
		container.setLayout( new FlexibleGridLayout( 4, 2 ) );

		updateContent();

		content.add( container, BorderLayout.CENTER );
		content.add( save, BorderLayout.PAGE_END );

		add( content, new GridBagConstraints() );
	}

	public JButton getSave() {
		return save;
	}

	public JComboBox<JDBCDriver> getDatabaseDriver() {
		return databaseDriver;
	}

	public void isUrlShown( boolean show ) {
		showUrl = show;
		updateContent();
	}

	public void isUsernameShown( boolean show ) {
		showUsername = show;
		updateContent();
	}

	public void isPasswordShown( boolean show ) {
		showPassword = show;
		updateContent();
	}

	private void updateContent() {
		container.removeAll();

		if( showUrl ) {
			container.add( new JLabel( Strings.get( "configuration.database.url" ) ) );
			container.add( databaseUrl );
		}

		if( showUsername ) {
			container.add( new JLabel( Strings.get( "configuration.database.username" ) ) );
			container.add( databaseUsername );
		}

		if( showPassword ) {
			container.add( new JLabel( Strings.get( "configuration.database.password" ) ) );
			container.add( databasePassword );
		}

		container.add( new JLabel( Strings.get( "configuration.database.driver" ) ) );
		container.add( databaseDriver );

		updateUI();
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
		this.databaseDriver.addActionListener( actionListener );
	}
}
