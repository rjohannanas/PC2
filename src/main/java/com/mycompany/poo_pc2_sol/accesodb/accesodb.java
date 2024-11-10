package com.mycompany.poo_pc2_sol.accesodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yoi
 */
public class accesodb {
	
		public static Connection getConnection() throws SQLException {
			
			Connection cn = null;
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String urlDB = "jdbc:sqlserver://localhost:1433;databaseName=JugueriaDB;encrypt=true;TrustServerCertificate=True;";
			String user = "sa";
			String pass = "sql";
			
			try {
				//Driver en memoria
				Class.forName(driver).getDeclaredConstructor().newInstance();
				
				//Objeto Connection
				cn = DriverManager.getConnection(urlDB,user,pass);
				
			} catch (SQLException e) {
				throw e;
			} catch (ClassNotFoundException e) {
				throw new SQLException("No se encontró el driver de la base de datos.");
			} catch (Exception e) {
				throw new SQLException("No se puede establecer la conexión con la BD.");
			}
			
			return cn;
		}
}
