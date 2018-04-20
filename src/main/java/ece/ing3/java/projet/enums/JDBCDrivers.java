package ece.ing3.java.projet.enums;

public enum JDBCDrivers {
	MySQL( "com.mysql.cj.jdbc.Driver" );

	private String className;

	JDBCDrivers( String className ) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}
}
