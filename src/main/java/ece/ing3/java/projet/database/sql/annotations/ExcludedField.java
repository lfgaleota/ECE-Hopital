package ece.ing3.java.projet.database.sql.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Exclude a field from being saved to database.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcludedField {
}
