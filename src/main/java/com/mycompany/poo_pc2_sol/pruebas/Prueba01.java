package com.mycompany.poo_pc2_sol.pruebas;

import com.mycompany.poo_pc2_sol.accesodb.accesodb;
import java.sql.Connection;

/**
 *
 * @author Yoi
 */
public class Prueba01 {
			public static void main(String[] args) {
						try {
							Connection cn = accesodb.getConnection();
							System.out.println("Conexio´n realizada satisfactoriamente");
							
						}	
						catch (Exception e) {
							System.out.println(e.getMessage());
						}
			}
}
