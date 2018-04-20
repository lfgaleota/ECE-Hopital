package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class BaseValidatedDialog extends JDialog {
	protected JPanel bottom;
	protected JButton submit;
	protected JButton cancel;

	private boolean validated;

	public BaseValidatedDialog( Window parent ) {
		super( parent );
	}

	public BaseValidatedDialog( JFrame parent ) {
		super( parent );
	}

	public BaseValidatedDialog( JDialog parent ) {
		super( parent );
	}

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

	protected abstract JComponent getContent();

	protected String getCancelLabel() {
		return "Annuler";
	}

	protected String getSubmitLabel() {
		return "Envoyer";
	}

	public JButton getSubmit() {
		return submit;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void addActionListener( ActionListener actionListener ) {
		submit.addActionListener( actionListener );
		cancel.addActionListener( actionListener );
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated( boolean validated ) {
		this.validated = validated;
	}

	public abstract void validateContent() throws IllegalArgumentException;
}
