package ece.ing3.java.projet.interfaces;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.tables.TableModel;

public interface ModelQueryWorkerProvider<M extends Model> {
	Where queryModifyWhereClause( Where whereClause );
	SQLSelect<M> queryCreateSelector();
	void queryOnFinish();
	Where getWhereClause();
	OrderBy getOrderByClause();
	TableModel<M> getTableModel();
}
