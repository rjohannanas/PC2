package com.mycompany.poo_pc2_sol.pruebas;

import com.mycompany.poo_pc2_sol.dto.ResumenDto;
import com.mycompany.poo_pc2_sol.service.JugueriaService;
import java.util.List;

/**
 *
 * @author Yoi
 */
public class PruebaResumen {
			public static void main(String[] args) {
					try {
							JugueriaService service = new JugueriaService();
							List<ResumenDto> lista = service.Consulta();
							
							for (ResumenDto bean:lista) {
								System.out.println(bean.toString());
							}
						
					}	catch (Exception e) {
					}
				
			}
}
