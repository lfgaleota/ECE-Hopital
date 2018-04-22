package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.panels.InfirmierPanelController;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.awt.*;

/**
 * Champ de saisie pour sélectionner des instances de modèle {@link Infirmier}, stockés en base de donnée, avec possibilité de sélection multiple pour la recherche
 */
public class InfirmierInput extends SearchInput<Infirmier, Long> {
	/**
	 * Créer un nouveau champ de saisie pour sélectionner des instances de modèle {@link Infirmier}.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 * @param parent     Fenêtre parente de l'ensemble du champ, utilisé pour la boîte de dialogue de recherche
	 */
	public InfirmierInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	/**
	 * Créer le panneau d'affichage, de recherche et sélection du modèle BDD Infirmier.
	 *
	 * @return Panneau d'affichage, de recherche et sélection du modèle BDD
	 */
	@Override
	protected ModelPanel<Infirmier> createController() {
		return new InfirmierPanelController().getPanel();
	}

	/**
	 * Récupère un tableau vide de type du modèle BDD Infirmier.
	 *
	 * @return Tableau vide de type du modèle BDD Infirmier
	 * @implNote Cette mléthode est utilisée pour faire de la conversion de {@link java.util.List} en tableau statique. Seul le type importe pour cette opération, et non la taille.
	 */
	@Override
	protected Long[] getEmptyArray() {
		return new Long[ 0 ];
	}

	/**
	 * Récupère la valeur du champ associée à une instance de modèle BDD.
	 *
	 * @param model Instance de modèle BDD
	 * @return Valeur du champ associée
	 */
	@Override
	protected Long getValueFromModel( Infirmier model ) {
		return model.getNumero();
	}

	/**
	 * Créer une instance de modèle BDD à partir de la valeur du champ fournie.
	 *
	 * @param value Valeur fournie
	 * @return Modèle BDD correspondant
	 * @throws IllegalArgumentException La valeur fgournie est invalide
	 */
	@Override
	protected Infirmier createInstanceFromValue( Long value ) throws IllegalArgumentException {
		Infirmier inf = new Infirmier();
		inf.setNumero( value );
		return inf;
	}
}
