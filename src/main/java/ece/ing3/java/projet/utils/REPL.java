package ece.ing3.java.projet.utils;

import bsh.EvalError;
import bsh.Interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Debug REPL to execute Java code interactively.
 */
public class REPL {
	public static void main( String[] args ) {
		Reader inreader = new InputStreamReader( System.in );
		Interpreter i = new Interpreter( inreader, System.out, System.err, true );
		BufferedReader in = new BufferedReader( inreader );
		String input;

		System.out.println( "ECE-Hopital REPL" );
		System.out.println( "- Entrez du code Java et appuyez sur Entrée pour l'exécuter." );
		System.out.println( "- 'quit' pour quitter." );
		System.out.println();

		try {
			i.eval( "import io.ebean.*;" );
			i.eval( "import ece.*;" );
		} catch( EvalError evalError ) {
			System.out.println( "Erreur lors de l'initialisation" );
			evalError.printStackTrace();
		}

		try {
			while( true ) {
				System.out.print( "> " );
				input = in.readLine();
				if( input.equals( "quit" ) ) {
					break;
				}
				try {
					i.eval( input );
				} catch( EvalError evalError ) {
					System.out.println( "Erreur lors de l'exécution de la commande :" );
					evalError.printStackTrace();
				}
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}

		try {
			in.close();
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
