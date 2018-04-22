package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Base de boîte de dialogue qui peut être validée ou annulée par l'utilisateur
 */
public abstract class BaseValidatedDialog extends JDialog {
	/**
	 * Barre de boutons en bas de la boîte de dialogue
	 */
	protected JPanel bottom;
	/**
	 * Bouton de soumission
	 */
	protected JButton submit;
	/**
	 * Bouton d'annulation
	 */
	protected JButton cancel;

	private boolean validated;

	/**
	 * Créer une nouvelle boîte de dialogue validable
	 *
	 * @param parent Fenêtre parente
	 */
	public BaseValidatedDialog( Window parent ) {
		super( parent );
	}

	/**
	 * Créer une nouvelle boîte de dialogue validable
	 *
	 * @param parent {@link JFrame} parent
	 */
	public BaseValidatedDialog( JFrame parent ) {
		super( parent );
	}

	/**
	 * Créer une nouvelle boîte de dialogue validable
	 *
	 * @param parent Boîte de dialogue parente
	 */
	public BaseValidatedDialog( JDialog parent ) {
		super( parent );
	}

	/**
	 * Initialise la boîte de dialogue après création et appel de tous les constructeurs. Construit le contenu de la boîte.
	 */
	protected void init() {
		this.setTitle( getTitle() );
		this.setLayout( new BorderLayout() );
		this.setResizable( true );
		this.setLocationRelativeTo( getParent() );

		( (JPanel) getContentPane() ).setBorder( new EmptyBorder( Constants.UI_DIALOG_MARGIN ) );

		this.add( getContent(), BorderLayout.CENTER );

		bottom = new JPanel( new FlowLayout() );
		this.cancel = new JButton( getCancelLabel() );
		bottom.add( cancel );
		this.submit = new JButton( getSubmitLabel() );
		bottom.add( submit );

		this.add( bottom, BorderLayout.SOUTH );

		this.pack();

		this.setVisible( true );
		this.toFront(); // place la fenêtre devant les autres.
	}

	/**
	 * Récupère le contenu de la boîte de dialogue.
	 *
	 * @return Contenu de la boîte de dialogue
	 */
	protected abstract JComponent getContent();

	/**
	 * Récupère le texte du bouton d'annulation de la boîte de dialogue.
	 *
	 * @return Texte du bouton d'annulation
	 */
	protected String getCancelLabel() {
		return "Annuler";
	}

	/**
	 * Récupère le texte du bouton de validation de la boîte de dialogue.
	 *
	 * @return Texte du bouton de validation
	 */
	protected String getSubmitLabel() {
		return "Envoyer";
	}

	/**
	 * Récupère le bouton de validation de la boîte de dialogue.
	 *
	 * @return Bouton de validation
	 */
	public JButton getSubmit() {
		return submit;
	}

	/**
	 * Récupère le bouton d'annulation de la boîte de dialogue.
	 *
	 * @return Bouton d'annulation
	 */
	public JButton getCancel() {
		return cancel;
	}

	/**
	 * Ajoute un {@link ActionListener} aux boutons de la boîte de dialogue.
	 *
	 * @param actionListener Instance d'{@link ActionListener} à ajouter
	 */
	public void addActionListener( ActionListener actionListener ) {
		submit.addActionListener( actionListener );
		cancel.addActionListener( actionListener );
	}

	/**
	 * Retourne si la boîte de dialogue a été validée par l'utilisateur.
	 *
	 * @return {@code true} Boîte de dialogue validée
	 */
	public boolean isValidated() {
		return validated;
	}

	/**
	 * Indique si la boîte de dialogue a été validée par l'utilisateur.
	 *
	 * @param validated Boîte de dialogue validée ou non
	 */
	public void setValidated( boolean validated ) {
		this.validated = validated;
	}

	/**
	 * Vérifie le contenu de la boîte de dialogue avant validation.
	 *
	 * @throws IllegalArgumentException Le contenu est dans un éat invalide
	 */
	public abstract void validateContent() throws IllegalArgumentException;
}
