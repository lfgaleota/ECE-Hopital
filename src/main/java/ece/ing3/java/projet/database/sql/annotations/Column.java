package ece.ing3.java.projet.database.sql.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Configure the field's corresponding database column.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name();
}

