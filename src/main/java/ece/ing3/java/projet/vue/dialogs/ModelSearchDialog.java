package ece.ing3.java.projet.vue.dialogs;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.vue.components.ModelInputList;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;

public abstract class ModelSearchDialog extends JFrame {
	protected ModelInputList inputList;
	private JButton submit;
	private JButton cancel;
	private JButton reset;
	
	private boolean validated;

	public ModelSearchDialog() {
		this.setTitle( "Recherche" );
		this.setLayout( new BorderLayout() );
		this.setSize( 500, 300 );
		this.setResizable( true );
		this.setVisible( true );
		this.toFront(); // place la fenêtre devant les autres.

		this.inputList = this.build();

		this.add( this.inputList, BorderLayout.CENTER );

		JPanel bottom = new JPanel( new FlowLayout() );
		this.cancel = new JButton( "Annuler" );
		bottom.add( cancel );
		this.submit = new JButton( "Valider" );
		bottom.add( submit );
		this.reset = new JButton( "Annuler La recherche" );
		bottom.add( reset );		
		
		this.add( bottom, BorderLayout.SOUTH );
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
		reset.addActionListener( actionListener );
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated( boolean validated ) {
		this.validated = validated;
	}

	public Where getWhereClause() {
		Where where = new Where();
		for( BaseInput input : inputList.getInputs().values() ) {
			if( input.isFilled() ) {
				where.and( input.getWhere() );
			}
		}
		return where;
	}
}
