package ece.ing3.java.projet;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.vue.Application;

public class Main {
	public static void main( String[] args ) throws Exception {
		Configuration.init();
		Database.init();
		Application.get();
	}

	public static void quit() {
		try {
			Database.close();
		} catch( DatabaseException e ) {

		}
	}
}
