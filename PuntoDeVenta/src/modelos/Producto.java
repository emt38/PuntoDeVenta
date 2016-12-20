package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import principal.Program;
import principal.Utilidades;

public class Producto implements IEntidadDatos<Producto> {
	//test Commit  to server
	private int id;
	private String codigo;
	private String descripcion;
	private float precio;
	private float costo;
	private float tasaImpuesto;
	private float inventario;
	
	@Override
	public String toString() {
		return String.format("%s - %s", descripcion, codigo);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getTasaImpuesto() {
		return tasaImpuesto;
	}

	public void setTasaImpuesto(float tasaImpuesto) {
		this.tasaImpuesto = tasaImpuesto;
	}

	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_codigo", codigo);
		temp.put("_descripcion", descripcion);
		temp.put("_costo", costo);
		temp.put("_precio", precio);
		temp.put("_tasaImpuesto", tasaImpuesto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarProducto(?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idProducto", id);
		temp.put("_codigo", codigo);
		temp.put("_descripcion", descripcion);
		temp.put("_costo", costo);
		temp.put("_precio", precio);
		temp.put("_tasaImpuesto", tasaImpuesto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarProducto(?,?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idProducto", id);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarProducto(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	public Producto(int id, String codigo, String descripcion, float precio, float costo, float tasaImpuesto, float inventario) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.costo = costo;
		this.tasaImpuesto = tasaImpuesto;
		this.setInventario(inventario);
	}
	
	public Producto() {
		super();
	}

	@Override
	public Producto buscar(int id) {
		List<Producto> productos = listar(String.format("WHERE p.idproducto=%s", id));
		if(productos.size() > 0)
			return productos.get(0);
		
		return null;
	}

	@Override
	public List<Producto> listar(String textoBusqueda) {
		List<Producto> productos = new ArrayList<Producto>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT p.idproducto, p.codigo, p.descripcion, p.costo, p.precio, p.tasaImpuesto, IFNULL(i.inventario, 0) as inventario  FROM productos AS p LEFT OUTER JOIN inventarioproductos AS i ON  p.idproducto = i.idproducto AND i.idtienda=" + Program.getLoggedUser().getTienda().getId() + " " + textoBusqueda, state);
			
			while(datos.next()) {
				productos.add(new Producto(datos.getInt("idproducto"), datos.getString("codigo"), datos.getString("descripcion"), datos.getFloat("precio"), datos.getFloat("costo"), datos.getFloat("tasaImpuesto"), datos.getFloat("inventario")));
			}
			return productos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return productos;
		}
	}

	public float getInventario() {
		return inventario;
	}

	public void setInventario(float inventario) {
		this.inventario = inventario;
	}

}
