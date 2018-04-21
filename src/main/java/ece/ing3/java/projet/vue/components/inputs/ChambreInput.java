package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.panels.ChambrePanelController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import javax.swing.border.Border;
import java.awt.*;

public class ChambreInput extends SearchInput<Chambre, Long> {
	private String[] codesService;
	private final Border borderDefaultButton;

	public ChambreInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
		this.borderDefaultButton = this.search.getBorder();
	}

	@Override
	protected ModelPanel<Chambre> createController() {
		return ( new ChambrePanelController() {
			@Override
			public Where queryModifyWhereClause( Where whereClause ) {
				whereClause = super.queryModifyWhereClause( whereClause );
				if( codesService != null ) {
					if( whereClause == null ) {
						whereClause = new Where();
					}
					Where inner = new Where();
					for( String codeService : codesService ) {
						inner.or( Model.getColumnNameFromFieldName( Chambre.class, "codeServiceRattache" ), "=", codeService );
					}
					whereClause.and( inner );
				}
				return whereClause;
			}
		}).getPanel();
	}

	@Override
	protected Long[] getEmptyArray() {
		return new Long[ 0 ];
	}

	@Override
	protected Long getValueFromModel( Chambre model ) {
		return model.getNumeroChambre();
	}

	@Override
	protected Chambre createInstanceFromValue( Long value ) throws IllegalArgumentException {
		Chambre ch = new Chambre();
		ch.setNumeroChambre( value );
		return ch;
	}

	@Override
	protected void triggerValueListener() {
		super.triggerValueListener();
		this.search.setBorder( borderDefaultButton );
	}

	public void setCodesService( String[] codes ) {
		this.codesService = codes;
		resetValue();
		this.search.setBorder( Constants.UI_INPUTLIST_INVALIDVALUE_BORDER );
	}
}
