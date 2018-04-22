package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.panels.MaladePanelController;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.awt.*;

/**
 * Champ de saisie pour sélectionner des instances de modèle {@link Malade}, stockés en base de donnée, avec possibilité de sélection multiple pour la recherche
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class MaladeInput extends SearchInput<Malade, Long> {
	/**
	 * Créer un nouveau champ de saisie pour sélectionner des instances de modèle {@link Malade}.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 * @param parent     Fenêtre parente de l'ensemble du champ, utilisé pour la boîte de dialogue de recherche
	 */
	public MaladeInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	/**
	 * Créer le panneau d'affichage, de recherche et sélection du modèle BDD Malade.
	 *
	 * @return Panneau d'affichage, de recherche et sélection du modèle BDD
	 */
	@Override
	protected ModelPanel<Malade> createController() {
		return new MaladePanelController().getPanel();
	}

	/**
	 * Récupère un tableau vide de type du modèle BDD Malade.
	 *
	 * @return Tableau vide de type du modèle BDD Malade
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
	protected Long getValueFromModel( Malade model ) {
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
	protected Malade createInstanceFromValue( Long value ) throws IllegalArgumentException {
		Malade inf = new Malade();
		inf.setNumero( value );
		return inf;
	}
}
