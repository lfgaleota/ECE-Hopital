package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class BaseModelInputDialog extends JDialog {
	protected ModelInputList inputList;
	protected JPanel bottom;
	private JButton submit;
	private JButton cancel;

	private boolean validated;

	public BaseModelInputDialog() {
		super( Application.get() );

		this.setTitle( getTitle() );
		this.setLayout( new BorderLayout() );
		this.setResizable( true );
		this.setVisible( true );
		this.toFront(); // place la fenêtre devant les autres.

		this.inputList = this.build();

		this.add( this.inputList, BorderLayout.CENTER );

		bottom = new JPanel( new FlowLayout() );
		this.cancel = new JButton( getCancelLabel() );
		bottom.add( cancel );
		this.submit = new JButton( getSubmitLabel() );
		bottom.add( submit );

		this.add( bottom, BorderLayout.SOUTH );

		pack();
	}

	protected String getCancelLabel() {
		return "Annuler";
	}

	protected String getSubmitLabel() {
		return "Envoyer";
	}

	public abstract ModelInputList build();

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

	public abstract void validateInput() throws IllegalArgumentException;
}
