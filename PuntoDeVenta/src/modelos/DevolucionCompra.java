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

public class DevolucionCompra implements IEntidadDatos<DevolucionCompra> {
	
	private int id;
	
	public int getId() {
		return id;
	}

	public DevolucionCompra() {
		super();
		this.articulos = new ArrayList<>();
	}
	
	public float calcularTotal()
	{
		float total = 0f;
		
		for(Articulo item : articulos) {
			total += item.getSubTotal();
		}
		
		return total;
	}

	public DevolucionCompra(int id, Compra compra, Usuario supervisor, NotaDebito notaDebito,
			List<Articulo> articulos) {
		super();
		this.id = id;
		this.compra = compra;
		this.supervisor = supervisor;
		this.notaDebito = notaDebito;
		this.articulos = articulos;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setId(int id) {
		this.id = id;
	}
	private Compra compra;
	private Usuario supervisor;
	private NotaDebito notaDebito;
	private List<Articulo> articulos;
	
	public boolean devolverArticulo(Articulo articulo, float cantidad) {
		if(cantidad > 0 && cantidad <= articulo.getCantidad() ) {
			articulo.setCantidad(cantidad);
			articulo.totalizar();
			articulos.add(articulo);
			return true;
		}
		return false;
	}
	
	public void generarNotaDebito() {
		NotaDebito temp = new NotaDebito(compra.getSuplidor(), String.format("Nota de débito por devolución de Compra #%s", compra.getNoDocumento())); 
		float total = 0f;
		
		for(Articulo item : articulos) {
			total += item.getSubTotal();
		}
		
		temp.total = total;
		this.notaDebito = temp;
	}
	
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Usuario getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}
	public NotaDebito getNotaDebito() {
		return notaDebito;
	}
	public void setNotaDebito(NotaDebito notaDebito) {
		this.notaDebito = notaDebito;
	}
	
	public void registrarInventario() {
		
	}
	
	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idCompra", compra.getNoDocumento());
		temp.put("_idSupervisor", supervisor.getId());
		temp.put("_idNotaDebito", notaDebito.getNoDocumento());
		
		try (Connection gate = Utilidades.newConnection();
			CallableStatement state = gate.prepareCall("CALL AgregarDevolucionesComprasEncabezado(?,?,?)");
			CallableStatement detail = gate.prepareCall("CALL AgregarDevolucionesComprasDetalle(?,?,?,?)");) {
			
			Utilidades.ejecutarCall(state, temp);
			List<DevolucionCompra> laultima = this.listar("ORDER BY iddevolucioncompra DESC LIMIT 0, 1");
			int noDocumento = laultima.get(0).getId();
			
			for(Articulo a : articulos) {
				temp.clear();
				temp.put("_iddevolucioncompra", noDocumento);
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
		temp.put("_idDevolucionCompra", id);
		temp.put("_idCompra", compra.getNoDocumento());
		temp.put("_idSupervisor", supervisor.getId());
		temp.put("_idNotaDebito", notaDebito.getNoDocumento());
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarDevolucionesComprasEncabezado(?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idDevolucionCompra", id);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarDevolucionesComprasEncabezado(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public DevolucionCompra buscar(int id) {
		List<DevolucionCompra> devoluciones = listar(String.format("WHERE iddevolucioncompra=%s", id));
		
		if(devoluciones.size() > 0)
			return devoluciones.get(0);
		
		return null;
	}
	@Override
	public List<DevolucionCompra> listar(String textoBusqueda) {
		List<DevolucionCompra> devolucionesCompra = new ArrayList<>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT iddevolucioncompra as id, idcompra, idsupervisor, idnotadebito FROM devolucionescomprasencabezado " + textoBusqueda, state);
			DevolucionCompra itera;

			StringBuilder articulosSb = new StringBuilder("(");
			StringBuilder notasDebitoSb = new StringBuilder("(");
			StringBuilder usuariosSb = new StringBuilder("(");
			StringBuilder comprasSb = new StringBuilder("(");
			
			while(datos.next()) {
				itera = new DevolucionCompra();
				itera.id = datos.getInt("id");
				itera.compra = new Compra();
				itera.compra.setNoDocumento(datos.getInt("idcompra"));
				itera.articulos = new ArrayList<>();
				itera.supervisor = new Usuario(datos.getInt("idsupervisor"), "", "", "", "", TipoUsuario.Administrador, null);
				itera.notaDebito = new NotaDebito();
				itera.notaDebito.setNoDocumento(datos.getInt("idnotadebito"));
				devolucionesCompra.add(itera);
				
				articulosSb.append(String.format("%s,", datos.getInt("id")));
				notasDebitoSb.append(String.format("%s,", datos.getInt("idnotadebito")));
				usuariosSb.append(String.format("%s,", datos.getInt("idsupervisor")));
				comprasSb.append(String.format("%s,", datos.getInt("idcompra")));
			}
			
			if(articulosSb.charAt(articulosSb.length() - 1) == ',')
				articulosSb.setCharAt(articulosSb.length() - 1, ')');
			else
				articulosSb.append("0)");
			
			ResultSet articulosRs = Utilidades.ejecutarQuery("SELECT iddevolucioncompra AS id, idproducto,(subtotal / cantidad) AS valor, subtotal, cantidad FROM devolucionescomprasdetalle " + articulosSb.toString(), state);
			List<Articulo> articulos = new ArrayList<Articulo>();
			
			while(articulosRs.next()) {
				articulos.add(new Articulo(new Producto(articulosRs.getInt("idproducto"), null, null, 0f,0f,0f, 0f), articulosRs.getFloat("cantidad"), articulosRs.getFloat("valor"), 0f, 0f, articulosRs.getFloat("subTotal")));
				for(DevolucionCompra devolucion : devolucionesCompra) {
					if(devolucion.id == articulosRs.getInt("id")) {
						devolucion.articulos.add(articulos.get(articulos.size()-1));
						break;
					}
				}
			}
			
			if(notasDebitoSb.charAt(notasDebitoSb.length() - 1) == ',')
				notasDebitoSb.setCharAt(notasDebitoSb.length() - 1, ')');
			else
				notasDebitoSb.append("0)");
			
			List<NotaDebito> notasDebito = new NotaDebito().listar(String.format("WHERE idnotadebito IN %s", notasDebitoSb.toString()));
			
			for(DevolucionCompra devolucion : devolucionesCompra) {
				notasDebito.forEach(s -> {
					if(devolucion.getNotaDebito().getNoDocumento() == s.getNoDocumento())
						devolucion.setNotaDebito(s);
				});
			}
			
			if(usuariosSb.charAt(usuariosSb.length() - 1) == ',')
				usuariosSb.setCharAt(usuariosSb.length() - 1, ')');
			else
				usuariosSb.append("0)");
			
			List<Usuario> usuarios = new Usuario().listar(String.format("WHERE idusuario IN %s", notasDebitoSb.toString()));
			
			for(DevolucionCompra devolucion : devolucionesCompra) {
				usuarios.forEach(s -> {
					if(devolucion.getSupervisor().getId() == s.getId())
						devolucion.setSupervisor(s);
				});
			}
			
			if(comprasSb.charAt(comprasSb.length() - 1) == ',')
				comprasSb.setCharAt(comprasSb.length() - 1, ')');
			else
				comprasSb.append("0)");
			
			List<Compra> compras = new Compra().listar(String.format("WHERE idcompra IN %s", notasDebitoSb.toString()));
			
			for(DevolucionCompra devolucion : devolucionesCompra) {
				compras.forEach(t -> {
					if(devolucion.getCompra().getNoDocumento() == t.getNoDocumento())
						devolucion.setCompra(t);
				});
			}
			
			return devolucionesCompra;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return devolucionesCompra;
		}
	}
}
