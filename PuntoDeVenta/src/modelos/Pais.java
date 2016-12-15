package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import principal.Utilidades;

public class Pais implements IEntidadDatos<Pais> {
	private int id;
	private String nombre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pais() {
		super();
	}
	public Pais(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_Nombre", nombre);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarPais(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idPais", id);
		temp.put("_Nombre", nombre);
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarPais(?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idPais", id);
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarPais(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public Pais buscar(int id) {
		List<Pais> paises = listar(String.format("WHERE idpais=%s", id));
		if(paises.size() > 0)
			return paises.get(0);
		
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.getNombre());
	};
	
	@Override
	public List<Pais> listar(String textoBusqueda) {
		List<Pais> paises = new ArrayList<Pais>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT IdPais, Nombre FROM Paises " + textoBusqueda, state);
			
			while(datos.next()) {
				paises.add(new Pais(datos.getInt("IdPais"), datos.getString("Nombre")));
			}
			return paises;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return paises;
		}
	}
}
