package com.mycompany.poo_pc2_sol.service;

import com.mycompany.poo_pc2_sol.accesodb.accesodb;
import com.mycompany.poo_pc2_sol.dto.DetalleVentaDto;
import com.mycompany.poo_pc2_sol.dto.ResumenDto;
import com.mycompany.poo_pc2_sol.dto.VentaDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yoi
 */
public class JugueriaService {
	
				public List<ResumenDto> Consulta() {

					List<ResumenDto> lista = new ArrayList<>();
					String sql = "SELECT p.id_categoria, c.nombre, SUM(d.cantidad) AS cantidad_total, ";
					sql += "SUM(v.importe) AS ventas_totales FROM producto p ";
					sql += "JOIN categoria c ON p.id_categoria = c.id_categoria ";
					sql += "JOIN detalle_venta d ON d.id_producto = p.id_producto ";
					sql += "JOIN venta v ON v.id_venta = d.id_venta ";
					sql += "GROUP BY p.id_categoria, c.nombre;";

					Connection cn = null;
					PreparedStatement pstm;
					ResultSet rs;


					try {
						cn = accesodb.getConnection();
						pstm = cn.prepareStatement(sql);
						rs = pstm.executeQuery();

							while (rs.next()) {
								//select p.id_categoria, c.nombre, sum(d.cantidad) AS cantidad_total,
								//SUM(v.importe) AS ventas_totales FROM producto p
								int id = rs.getInt("id_categoria");
								String nombre = rs.getString("nombre");
								int cantidad = rs.getInt("cantidad_total");
								double venta = rs.getDouble("ventas_totales");

								ResumenDto bean = new ResumenDto(id, nombre, cantidad, venta);
								lista.add(bean);
							}

					} catch(SQLException e) {

							try {
								cn.rollback();
							}	catch (Exception e1) {
							}
							throw new RuntimeException(e.getMessage());

					} catch(Exception e) {
							try {
								cn.rollback();
							} catch (Exception e1) {
							}
							throw new RuntimeException("Error, inténtelo luego...");

					} finally {
							try {
								cn.close();
							} catch (Exception e) {
							}
					}

					return lista;
				}
				
				
				public void validarEmpleado(Connection cn, int idempleado) throws SQLException {
						String sql = "select COUNT(*) cont from EMPLEADO where id_empleado=?";
						PreparedStatement pstm = cn.prepareCall(sql);
						pstm.setInt(1, idempleado);
						ResultSet rs = pstm.executeQuery();
						rs.next();
						int cont = rs.getInt("cont");
						if (cont!=1) {
								throw new SQLException("No existe el id de empleado: "+idempleado);
						}
				}
				
				public void validarProducto(Connection cn, int idproducto) throws SQLException {
						String sql = "SELECT COUNT(*) cont from PRODUCTO where id_producto=?";
						PreparedStatement pstm = cn.prepareCall(sql);
						pstm.setInt(1, idproducto);
						ResultSet rs = pstm.executeQuery();
						rs.next();
						int cont = rs.getInt("cont");
						if (cont!=1) {
							throw new SQLException("No existe el id de producto: "+idproducto);
						}
				}
				
				public void validarSubtotal(Connection cn, int cantidad, double precio, double subtotal) throws SQLException{
						if (cantidad<=0 || precio<=0 ||subtotal<=0) {
								throw new SQLException("No se aceptan valores negativos");
						}
				}
				
				public void validarSubtotal0(Connection cn, int idproducto, int cantidad, double precio, double subtotal) throws SQLException{ 
						String sql = "SELECT precio from PRODUCTO where id_producto=?";
						PreparedStatement pstm = cn.prepareCall(sql);
						pstm.setInt(1, idproducto);
						ResultSet rs = pstm.executeQuery();
						rs.next();
						double prec = rs.getDouble("precio");
						double subtIdeal = prec*cantidad;
						if (subtIdeal != subtotal) {
							throw new SQLException("El precio no coincide");
						}
				}
				
				public void validarTotal(Connection cn, VentaDTO venta) throws SQLException {
						double sumaTotalDetalles = 0.0;


						for (DetalleVentaDto detalle : venta.getDetalle_venta()) {

								sumaTotalDetalles += detalle.getSubtotal();
						}

						double totalVenta=venta.getTotal();

						if (sumaTotalDetalles != totalVenta) {
								throw new SQLException("La suma de los totales de los detalles (" + sumaTotalDetalles + ") no es igual al total de la venta (" + totalVenta + ")");
						}
				}
}