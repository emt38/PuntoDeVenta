package modelos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class DevolucionVenta implements IEntidadDatos<DevolucionVenta> {
	
	private int id;
	private Venta venta;
	private Usuario supervisor;
	private NotaCredito notaCredito;
	private List<Articulo> articulos;
	
	public DevolucionVenta() {
		super();
		this.articulos = new ArrayList<>();
	}

	public DevolucionVenta(int id, Venta venta, Usuario supervisor, NotaCredito notaCredito, List<Articulo> articulos) {
		super();
		this.id = id;
		this.venta = venta;
		this.supervisor = supervisor;
		this.notaCredito = notaCredito;
		this.articulos = articulos;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public boolean devolverArticulo(Articulo articulo, float cantidad) {
		if(cantidad > 0 && cantidad <= articulo.getCantidad() ) {
			articulo.setCantidad(cantidad);
			articulo.totalizar();
			articulos.add(articulo);
			return true;
		}
		return false;
	}
	
	public float calcularTotal()
	{
		float total = 0f;
		
		for(Articulo item : articulos) {
			total += item.getSubTotal();
		}
		
		return total;
	}
	
	public void generarNotaCredito() {
		NotaCredito temp = new NotaCredito(venta.getCliente(), String.format("Nota de Crédito por devolución de Venta #%s", venta.getNoDocumento())); 
		float total = 0f;
		
		for(Articulo item : articulos) {
			total += item.getSubTotal();
		}
		
		temp.total = total;
		this.notaCredito = temp;
	}
	
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Usuario getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}
	public NotaCredito getNotaCredito() {
		return notaCredito;
	}
	public void setNotaDebito(NotaCredito notaCredito) {
		this.notaCredito = notaCredito;
	}
	
	public void registrarInventario() {
		
	}
	
	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idVenta", venta.getNoDocumento());
		temp.put("_idSupervisor", supervisor.getId());
		temp.put("_idNotaCredito", notaCredito.getNoDocumento());
		
		try (Connection gate = Utilidades.newConnection();
			CallableStatement state = gate.prepareCall("CALL AgregarDevolucionesVentasEncabezado(?,?,?)");
			CallableStatement detail = gate.prepareCall("CALL AgregarDevolucionesVentasDetalle(?,?,?,?)");) {
			
			Utilidades.ejecutarCall(state, temp);
			List<DevolucionVenta> laultima = this.listar("ORDER BY iddevolucionventa DESC LIMIT 0, 1");
			int noDocumento = laultima.get(0).getId();
			
			for(Articulo a : articulos) {
				temp.clear();
				temp.put("_idDevolucionVenta", noDocumento);
				temp.put("_idProducto", a.getProducto().getId());
				temp.put("_subTotal", a.getSubTotal());
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
		temp.put("_idDevolucionVenta", id);
		temp.put("_idVenta", venta.getNoDocumento());
		temp.put("_idSupervisor", supervisor.getId());
		temp.put("_idNotaCredito", notaCredito.getNoDocumento());
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarDevolucionesVentasEncabezado(?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idDevolucionVenta", id);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarDevolucionesComprasEncabezado(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public DevolucionVenta buscar(int id) {
		List<DevolucionVenta> devoluciones = listar(String.format("WHERE iddevolucionventa=%s", id));
		
		if(devoluciones.size() > 0)
			return devoluciones.get(0);
		
		return null;
	}

	@Override
	public List<DevolucionVenta> listar(String textoBusqueda) {
		List<DevolucionVenta> devolucionesVenta = new ArrayList<>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT iddevolucioncompra as id, idcompra, idsupervisor, idnotadebito FROM devolucionesventasencabezado " + textoBusqueda, state);
			DevolucionVenta itera;

			StringBuilder articulosSb = new StringBuilder("(");
			StringBuilder notasCreditoSb = new StringBuilder("(");
			StringBuilder usuariosSb = new StringBuilder("(");
			StringBuilder ventasSb = new StringBuilder("(");
			
			while(datos.next()) {
				itera = new DevolucionVenta();
				itera.id = datos.getInt("id");
				itera.venta = new Venta();
				itera.venta.setNoDocumento(datos.getInt("idventa"));
				itera.articulos = new ArrayList<>();
				itera.supervisor = new Usuario(datos.getInt("idsupervisor"), "", "", "", "", TipoUsuario.Administrador, null);
				itera.notaCredito = new NotaCredito();
				itera.notaCredito.setNoDocumento(datos.getInt("idnotadebito"));
				devolucionesVenta.add(itera);
				
				articulosSb.append(String.format("%s,", datos.getInt("id")));
				notasCreditoSb.append(String.format("%s,", datos.getInt("idnotacredito")));
				usuariosSb.append(String.format("%s,", datos.getInt("idsupervisor")));
				ventasSb.append(String.format("%s,", datos.getInt("idventa")));
			}
			
			if(articulosSb.charAt(articulosSb.length() - 1) == ',')
				articulosSb.setCharAt(articulosSb.length() - 1, ')');
			else
				articulosSb.append("0)");
			
			ResultSet articulosRs = Utilidades.ejecutarQuery("SELECT iddevolucionventa AS id, idproducto,(subtotal / cantidad) AS valor, subtotal, cantidad FROM devolucionesventasdetalle " + articulosSb.toString(), state);
			List<Articulo> articulos = new ArrayList<Articulo>();
			
			while(articulosRs.next()) {
				articulos.add(new Articulo(new Producto(articulosRs.getInt("idproducto"), null, null, 0f,0f,0f, 0f), articulosRs.getFloat("cantidad"), articulosRs.getFloat("valor"), 0f, 0f, articulosRs.getFloat("subTotal")));
				for(DevolucionVenta devolucion : devolucionesVenta) {
					if(devolucion.id == articulosRs.getInt("id")) {
						devolucion.articulos.add(articulos.get(articulos.size()-1));
						break;
					}
				}
			}
			
			if(notasCreditoSb.charAt(notasCreditoSb.length() - 1) == ',')
				notasCreditoSb.setCharAt(notasCreditoSb.length() - 1, ')');
			else
				notasCreditoSb.append("0)");
			
			List<NotaCredito> notasCredito = new NotaCredito().listar(String.format("WHERE idnotacredito IN %s", notasCreditoSb.toString()));
			
			for(DevolucionVenta devolucion : devolucionesVenta) {
				notasCredito.forEach(s -> {
					if(devolucion.getNotaCredito().getNoDocumento() == s.getNoDocumento())
						devolucion.setNotaDebito(s);
				});
			}
			
			if(usuariosSb.charAt(usuariosSb.length() - 1) == ',')
				usuariosSb.setCharAt(usuariosSb.length() - 1, ')');
			else
				usuariosSb.append("0)");
			
			List<Usuario> usuarios = new Usuario().listar(String.format("WHERE idusuario IN %s", notasCreditoSb.toString()));
			
			for(DevolucionVenta devolucion : devolucionesVenta) {
				usuarios.forEach(s -> {
					if(devolucion.getSupervisor().getId() == s.getId())
						devolucion.setSupervisor(s);
				});
			}
			
			if(ventasSb.charAt(ventasSb.length() - 1) == ',')
				ventasSb.setCharAt(ventasSb.length() - 1, ')');
			else
				ventasSb.append("0)");
			
			List<Venta> ventas = new Venta().listar(String.format("WHERE idventa IN %s", notasCreditoSb.toString()));
			
			for(DevolucionVenta devolucion : devolucionesVenta) {
				ventas.forEach(t -> {
					if(devolucion.getVenta().getNoDocumento() == t.getNoDocumento())
						devolucion.setVenta(t);
				});
			}
			
			return devolucionesVenta;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return devolucionesVenta;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
