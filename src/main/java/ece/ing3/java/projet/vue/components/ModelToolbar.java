package ece.ing3.java.projet.vue.components;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Barre d'outil standard pour le panneau d'affichage, sélection, recherche et modification de modèle BDD {@link ece.ing3.java.projet.vue.panels.ModelPanel}
 * <p>
 * Comporte l'ensemble des boutons pour la manipulations des modèles BDD
 */
public class ModelToolbar extends JToolBar {
	private JButton buttonRemove, buttonAdd, buttonModify, buttonSearch, buttonRefresh;

	/**
	 * Créer une nouvelle barre d'outil pour panneau de modèle.
	 *
	 * @param modelName Nom du modèle BDD associé, qui sera utilisé pour récupérer le texte à afficher
	 */
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

	/**
	 * Récupère le bouton de suppression.
	 *
	 * @return Bouton de suppression
	 */
	public JButton getButtonRemove() {
		return buttonRemove;
	}

	/**
	 * Récupère le bouton d'ajout.
	 *
	 * @return Bouton d'ajout
	 */
	public JButton getButtonAdd() {
		return buttonAdd;
	}

	/**
	 * Récupère le bouton de modification.
	 *
	 * @return Bouton de modification
	 */
	public JButton getButtonModify() {
		return buttonModify;
	}

	/**
	 * Récupère le bouton de recherche.
	 *
	 * @return Bouton de recherche
	 */
	public JButton getButtonSearch() {
		return buttonSearch;
	}

	/**
	 * Récupère le bouton de rafraichissement des données.
	 *
	 * @return Bouton de rafraichissement des données
	 */
	public JButton getButtonRefresh() {
		return buttonRefresh;
	}

	/**
	 * Ajoute un {@link ActionListener} aux boutons de la barre d'outil.
	 *
	 * @param actionListener {@link ActionListener} à ajouter aux boutons
	 */
	public void addActionListener( ActionListener actionListener ) {
		buttonRemove.addActionListener( actionListener );
		buttonAdd.addActionListener( actionListener );
		buttonModify.addActionListener( actionListener );
		buttonSearch.addActionListener( actionListener );
		buttonRefresh.addActionListener( actionListener );
	}

	/**
	 * Active ou désactive les boutons de la barre d'outils.
	 *
	 * @param b Activer ou désactiver les boutons
	 */
	@Override
	public void setEnabled( boolean b ) {
		buttonRemove.setEnabled( b );
		buttonAdd.setEnabled( b );
		buttonModify.setEnabled( b );
		buttonSearch.setEnabled( b );
		buttonRefresh.setEnabled( b );
		super.setEnabled( b );
	}
}
