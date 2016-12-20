package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class Tienda implements IEntidadDatos<Tienda> {
	
	private int id;
	private String nombre;
	private String direccion;
	private String slogan;
	private Ciudad ciudad;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_nombre", nombre);
		temp.put("_direccion", direccion);
		temp.put("_slogan", slogan);
		temp.put("_idciudad", ciudad.getId());
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarTienda(?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idTienda", id);
		temp.put("_nombre", nombre);
		temp.put("_direccion", direccion);
		temp.put("_slogan", slogan);
		temp.put("_idciudad", ciudad.getId());
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarTienda(?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idTienda", id);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarTienda(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Tienda buscar(int id) {
		List<Tienda> tiendas = listar(String.format("WHERE idtienda=%s", id));
		
		if(tiendas.size() > 0)
			return tiendas.get(0);
			
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.getNombre());
	};

	@Override
	public List<Tienda> listar(String textoBusqueda) {
		List<Tienda> tiendas = new ArrayList<Tienda>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idtienda, nombre, direccion, slogan, idciudad FROM tiendas " + textoBusqueda, state);
			Tienda itera;
			
			StringBuilder ciudadesSb = new StringBuilder("(");
			
			while(datos.next()) {
				itera = new Tienda();
				itera.id = datos.getInt("idtienda");
				itera.direccion = datos.getString("direccion");
				itera.nombre = datos.getString("nombre");
				itera.slogan = datos.getString("slogan");
				itera.ciudad = new Ciudad(datos.getInt("idciudad"), null, null);
				tiendas.add(itera);
			}
			
			if(ciudadesSb.charAt(ciudadesSb.length() - 1) == ',')
				ciudadesSb.setCharAt(ciudadesSb.length() - 1, ')');
			else
				ciudadesSb.append("0)");
			
			List<Ciudad> ciudades = new Ciudad().listar(String.format("WHERE idciudad IN %s", ciudadesSb.toString()));
			
			for(Tienda tienda : tiendas) {
				ciudades.forEach(c -> {
					if(tienda.getCiudad().getId() == c.getId())
						tienda.setCiudad(c);
				});
			}
			
			return tiendas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tiendas;
		}
	}
	
	public boolean esValida(List<String> errores) {
		errores.clear();
		
		if(nombre == null || nombre.length() <= 0)
			errores.add("El campo de nombre no puede estar vacío.");
		
		if(ciudad == null)
			errores.add("Debe seleccionar una ciudad.");
		
		return errores.size() > 0;
	}

	public Tienda(int id, String nombre, String direccion, String slogan, Ciudad ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.slogan = slogan;
		this.ciudad = ciudad;
	}

	public Tienda() {
		super();
	}

}
