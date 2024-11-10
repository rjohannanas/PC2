package com.mycompany.poo_pc2_sol.dto;

import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Yoi
 */

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class VentaDto {
		private int idempleado;
    private List<DetalleVentaDto> detalle_venta;
    private double total;
    private double importe;
    private double impuesto;
	
	
	
}
