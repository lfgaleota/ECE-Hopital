package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

import javax.swing.*;

public abstract class BaseModelInputDialog extends BaseValidatedDialog {
	protected ModelInputList inputList;

	public BaseModelInputDialog() {
		super( Application.get() );
		this.inputList = this.build();
		init();
	}

	@Override
	protected JComponent getContent() {
		return this.inputList;
	}

	public abstract ModelInputList build();
}
