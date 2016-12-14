package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.CallableStatement;

import principal.Utilidades;

public class Compra extends IntercambioComercial implements IEntidadDatos<Compra> {
	
	private Suplidor suplidor;
	private Usuario supervisor;
	private Tienda tienda;
	
	public Suplidor getSuplidor() {
		
		return suplidor;
		
	}

	public void setSuplidor(Suplidor suplidor) {
		this.suplidor = suplidor;
	}

	public Usuario getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Compra() {
		super();
		this.articulos = new ArrayList<>();
	}

	public Compra(Suplidor suplidor, Usuario supervisor, Tienda tienda) {
		super();
		this.suplidor = suplidor;
		this.supervisor = supervisor;
		this.tienda = tienda;
		this.articulos = new ArrayList<>();
	}

	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idsuplidor", suplidor.getId());
		temp.put("_idtienda", tienda.getId());
		temp.put("_idsupervisor", supervisor.getId());
		temp.put("_subtotal", subTotal);
		temp.put("_impuestos", impuestos);
		temp.put("_descuentos", descuentos);
		temp.put("_total", total);
		temp.put("_efectuado", true);
		
		try (Connection gate = Utilidades.newConnection();
			CallableStatement state = gate.prepareCall("CALL AgregarComprasEncabezado(?,?,?,?,?,?,?,?)");
			CallableStatement detail = gate.prepareCall("CALL AgregarComprasDetalle(?,?,?,?,?,?,?,?)");) {
			
			Utilidades.ejecutarCall(state, temp);
			List<Compra> laultima = this.listar("ORDER BY idcompra DESC LIMIT 0, 1");
			noDocumento = laultima.get(0).getNoDocumento();
			
			for(Articulo a : articulos) {
				temp.clear();
				temp.put("idcomp", noDocumento);
				temp.put("idprod", a.getProducto().getId());
				temp.put("_valor", a.getValor());
				temp.put("imp", a.getImpuestos());
				temp.put("subt", a.getSubTotal());
				temp.put("cant", a.getCantidad());
				
				Utilidades.ejecutarCall(detail, temp);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean actualizar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Compra buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compra> listar(String textoBusqueda) {
		List<Compra> compras = new ArrayList<Compra>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idcompra, idsuplidor, descuentos, efectuado, fecha, impuestos, idsupervisor, subtotal, idtienda, total FROM comprasencabezado " + textoBusqueda, state);
			Compra itera;
			
			StringBuilder suplidoresSb = new StringBuilder("(");
			StringBuilder supervisoresSb = new StringBuilder("(");
			StringBuilder tiendaSb = new StringBuilder("(");
			
			while(datos.next()) {
				itera = new Compra();
				itera.suplidor = new Suplidor(datos.getInt("idsuplidor"), null, null);
				itera.descuentos = datos.getFloat("descuentos");
				itera.efectuado = datos.getBoolean("efectuado");
				itera.fecha = datos.getDate("fecha");
				itera.impuestos = datos.getFloat("impuestos");
				itera.noDocumento = datos.getInt("idcompra");
				itera.subTotal = datos.getFloat("subtotal");
				itera.supervisor = new Usuario(datos.getInt("idsupervisor"), null, null, null, null, TipoUsuario.Administrador, null);
				itera.tienda = new Tienda(datos.getInt("idtienda"), null, null, null, null);
				itera.total = datos.getFloat("total");
				compras.add(itera);
			}
			
			if(suplidoresSb.charAt(suplidoresSb.length() - 1) == ',')
				suplidoresSb.setCharAt(suplidoresSb.length() - 1, ')');
			else
				suplidoresSb.append("0)");
			
			List<Suplidor> suplidores = new Suplidor().listar(String.format("WHERE idsuplidor IN %s", suplidoresSb.toString()));
			
			for(Compra compra : compras) {
				suplidores.forEach(s -> {
					if(compra.getSuplidor().getId() == s.getId())
						compra.setSuplidor(s);
				});
			}
			
			if(supervisoresSb.charAt(supervisoresSb.length() - 1) == ',')
				supervisoresSb.setCharAt(supervisoresSb.length() - 1, ')');
			else
				supervisoresSb.append("0)");
			
			List<Usuario> usuarios = new Usuario().listar(String.format("WHERE idusuario IN %s", suplidoresSb.toString()));
			
			for(Compra compra : compras) {
				usuarios.forEach(s -> {
					if(compra.getSupervisor().getId() == s.getId())
						compra.setSupervisor(s);
				});
			}
			
			if(tiendaSb.charAt(tiendaSb.length() - 1) == ',')
				tiendaSb.setCharAt(tiendaSb.length() - 1, ')');
			else
				tiendaSb.append("0)");
			
			List<Tienda> tiendas = new Tienda().listar(String.format("WHERE idtienda IN %s", suplidoresSb.toString()));
			
			for(Compra compra : compras) {
				tiendas.forEach(t -> {
					if(compra.getTienda().getId() == t.getId())
						compra.setTienda(t);
				});
			}
			
			return compras;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return compras;
		}
	}

	@Override
	protected void registrarInventario() {
		// TODO Auto-generated method stub

	}

}
