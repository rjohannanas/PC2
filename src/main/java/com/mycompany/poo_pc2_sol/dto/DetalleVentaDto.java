package com.mycompany.poo_pc2_sol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Yoi
 */


@Data @AllArgsConstructor @NoArgsConstructor
public class DetalleVentaDto {
		
			private int idventa;
			private int idproducto;
			private int cantidad;
			private double precio;
			private double subtotal;
}
