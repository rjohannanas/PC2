package com.mycompany.poo_pc2_sol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Yoi
 */

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ResumenDto {
		
		private int idcategoria;
    private String nombre;
    private int cantidad_total;
    private double ventas_total;
		
}
