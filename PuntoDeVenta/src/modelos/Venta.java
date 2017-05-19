package modelos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import principal.Program;
import principal.Utilidades;

public class Venta extends IntercambioComercial implements IEntidadDatos<Venta> {
	
	private Cliente cliente;
	private Usuario cajero;
	private int terminalVentas;
	private Tienda tienda;
	private int efectivoRecibido;
	private int cambioDevuelto;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getCajero() {
		return cajero;
	}

	public void setCajero(Usuario cajero) {
		this.cajero = cajero;
	}

	public int getTerminalVentas() {
		return terminalVentas;
	}

	public void setTerminalVentas(int terminalVentas) {
		this.terminalVentas = terminalVentas;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public int getEfectivoRecibido() {
		return efectivoRecibido;
	}

	public void setEfectivoRecibido(int efectivoRecibido) {
		this.efectivoRecibido = efectivoRecibido;
	}

	public int getCambioDevuelto() {
		return cambioDevuelto;
	}

	public void setCambioDevuelto(int cambioDevuelto) {
		this.cambioDevuelto = cambioDevuelto;
	}
	
	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idCliente", cliente.getId());
		temp.put("_idTienda", tienda.getId());
		temp.put("_idCajero", cajero.getId());
		temp.put("_terminalVentas", terminalVentas);
		temp.put("_efectivoRecibido", efectivoRecibido);
		temp.put("_cambioDevuelto", cambioDevuelto);
		temp.put("_subtotal", subTotal);
		temp.put("_impuestos", impuestos);
		temp.put("_descuentos", descuentos);
		temp.put("_total", total);
		temp.put("_efectuado", true);
		
		try (Connection gate = Utilidades.newConnection();
			CallableStatement state = gate.prepareCall("CALL AgregarVentasEncabezado(?,?,?,?,?,?,?,?,?,?,?)");
			CallableStatement detail = gate.prepareCall("CALL AgregarVentasDetalle(?,?,?,?,?,?)");) {
			
			Utilidades.ejecutarCall(state, temp);
			List<Venta> laultima = this.listar("ORDER BY idventa DESC LIMIT 0, 1");
			noDocumento = laultima.get(0).getNoDocumento();
			
			for(Articulo a : articulos) {
				temp.clear();
				temp.put("_idVenta", noDocumento);
				temp.put("_idProducto", a.getProducto().getId());
				temp.put("_valor", a.getValor());
				temp.put("_impuestos", a.getImpuestos());
				temp.put("_subtotal", a.getSubTotal());
				temp.put("_cantidad", a.getCantidad());
				
				Utilidades.ejecutarCall(detail, temp);
				registrarInventario();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idVenta", noDocumento);
		temp.put("_idCliente", cliente.getId());
		temp.put("_idTienda", tienda.getId());
		temp.put("_idCajero", cajero.getId());
		temp.put("_terminalVentas", terminalVentas);
		temp.put("_efectivoRecibido", efectivoRecibido);
		temp.put("_cambioDevuelto", cambioDevuelto);
		temp.put("_subtotal", subTotal);
		temp.put("_impuestos", impuestos);
		temp.put("_descuentos", descuentos);
		temp.put("_total", total);
		temp.put("_efectuado", true);
		
		try (Connection gate = Utilidades.newConnection();) {
			
			Utilidades.ejecutarCall("CALL ModificarVentasEncabezado(?,?,?,?,?,?,?,?,?,?,?,?)", temp, gate);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idventa", noDocumento);
		
		try (Connection gate = Utilidades.newConnection();) {
			
			Utilidades.ejecutarCall("CALL EliminarVentasEncabezado(?)", temp, gate);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Venta buscar(int id) {
		List<Venta> ventas = listar(String.format("WHERE idventa=%s", id));
		if(ventas.size() > 0)
			return ventas.get(0);
		
		return null;
	}

	@Override
	public List<Venta> listar(String textoBusqueda) {
		List<Venta> ventas = new ArrayList<Venta>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idventa, idcliente, descuentos, efectuado, fecha, impuestos, idcajero, subtotal, idtienda, efectivoRecibido, cambioDevuelto, terminalVentas, total FROM ventasencabezado " + textoBusqueda, state);
			
			Venta itera;
			
			StringBuilder articulosSb = new StringBuilder("(");
			StringBuilder clientesSb = new StringBuilder("(");
			StringBuilder cajerosSb = new StringBuilder("(");
			StringBuilder tiendaSb = new StringBuilder("(");
			
			while(datos.next()) {
				itera = new Venta();
				itera.cliente = new Cliente(datos.getInt("idcliente"), new Date(), 0f);
				itera.descuentos = datos.getFloat("descuentos");
				itera.efectuado = datos.getBoolean("efectuado");
				itera.fecha = datos.getDate("fecha");
				itera.impuestos = datos.getFloat("impuestos");
				itera.noDocumento = datos.getInt("idventa");
				itera.subTotal = datos.getFloat("subtotal");
				itera.cajero = new Usuario(datos.getInt("idcajero"), null, null, null, null, TipoUsuario.Administrador, null);
				itera.tienda = new Tienda(datos.getInt("idtienda"), null, null, null, null);
				itera.efectivoRecibido = datos.getInt("efectivoRecibido");
				itera.cambioDevuelto = datos.getInt("cambioDevuelto");
				itera.total = datos.getFloat("total");
				itera.terminalVentas = datos.getInt("terminalVentas");
				ventas.add(itera);
				articulosSb.append(String.format("%s,", datos.getInt("idventa")));
				clientesSb.append(String.format("%s,", datos.getInt("idcliente")));
				cajerosSb.append(String.format("%s,", datos.getInt("idcajero")));
				tiendaSb.append(String.format("%s,", datos.getInt("idtienda")));
			}
			
			if(articulosSb.charAt(articulosSb.length() - 1) == ',')
				articulosSb.setCharAt(articulosSb.length() - 1, ')');
			else
				articulosSb.append("0)");
			
			ResultSet articulosRs = Utilidades.ejecutarQuery("SELECT idventa AS id, idproducto, valor, impuestos, subtotal, cantidad, tasaImpuestos FROM ventasdetalle WHERE idventa IN " + articulosSb.toString(), state);
			List<Articulo> articulos = new ArrayList<Articulo>();
			StringBuilder productosSb = new StringBuilder("(");
			
			while(articulosRs.next()) {
				articulos.add(new Articulo(new Producto(articulosRs.getInt("idproducto"), null, null, 0f,0f,0f, 0f),articulosRs.getFloat("cantidad"), articulosRs.getFloat("valor"), articulosRs.getFloat("tasaImpuestos"), articulosRs.getFloat("impuestos"), articulosRs.getFloat("subTotal")));
				productosSb.append(String.format("%s,", articulosRs.getInt("idproducto")));
				
				for(Venta venta : ventas) {
					if(venta.noDocumento == articulosRs.getInt("id")) {
						venta.articulos.add(articulos.get(articulos.size()-1));
						break;
					}
				}
			}
			
			if(productosSb.charAt(productosSb.length() - 1) == ',')
				productosSb.setCharAt(productosSb.length() - 1, ')');
			else
				productosSb.append("0)");
			
			List<Producto> productos = new Producto().listar(String.format(" WHERE p.idproducto IN %s", productosSb.toString()));
			
			for(Articulo item : articulos) {
				productos.forEach(p -> {
					if(item.getProducto().getId() == p.getId())
						item.setProducto(p);
				});
			}
			
			if(clientesSb.charAt(clientesSb.length() - 1) == ',')
				clientesSb.setCharAt(clientesSb.length() - 1, ')');
			else
				clientesSb.append("0)");
			
			List<Cliente> clientes = new Cliente().listar(String.format("WHERE idcliente IN %s", clientesSb.toString()));
			
			for(Venta venta : ventas) {
				clientes.forEach(s -> {
					if(venta.getCliente().getId() == s.getId())
						venta.setCliente(s);
				});
			}
			
			if(cajerosSb.charAt(cajerosSb.length() - 1) == ',')
				cajerosSb.setCharAt(cajerosSb.length() - 1, ')');
			else
				cajerosSb.append("0)");
			
			List<Usuario> usuarios = new Usuario().listar(String.format("WHERE idusuario IN %s", clientesSb.toString()));
			
			for(Venta venta : ventas) {
				usuarios.forEach(s -> {
					if(venta.getCajero().getId() == s.getId())
						venta.setCajero(s);
				});
			}
			
			if(tiendaSb.charAt(tiendaSb.length() - 1) == ',')
				tiendaSb.setCharAt(tiendaSb.length() - 1, ')');
			else
				tiendaSb.append("0)");
			
			List<Tienda> tiendas = new Tienda().listar(String.format("WHERE idtienda IN %s", clientesSb.toString()));
			
			for(Venta venta : ventas) {
				tiendas.forEach(t -> {
					if(venta.getTienda().getId() == t.getId())
						venta.setTienda(t);
				});
			}
			
			return ventas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ventas;
		}
	}

	@Override
	protected void registrarInventario() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idtienda", Program.getLoggedUser().getTienda().getId());
		
		try (Connection gate = Utilidades.newConnection();
			CallableStatement state = gate.prepareCall("CALL ReducirInventario(?,?,?)");) {
			for(Articulo item : articulos) {
				temp.put("_idproducto", item.getProducto().getId());
				temp.put("_cantidad", item.getCantidad());
				Utilidades.ejecutarCall(state, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Venta(Cliente cliente, Usuario cajero, int terminalVentas, Tienda tienda, int efectivoRecibido,
			int cambioDevuelto) {
		super();
		this.cliente = cliente;
		this.cajero = cajero;
		this.terminalVentas = terminalVentas;
		this.tienda = tienda;
		this.efectivoRecibido = efectivoRecibido;
		this.cambioDevuelto = cambioDevuelto;
	}

	public Venta() {
		super();
	}
	
	@Override
	public void efectuar() {
		super.efectuar();
		insertar();
		registrarInventario();
	}
}
