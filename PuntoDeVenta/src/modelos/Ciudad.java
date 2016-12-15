package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class Ciudad implements IEntidadDatos<Ciudad> {
	private int id;
	private Provincia provincia;
	private String nombre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Ciudad(int id, Provincia provincia, String nombre) {
		super();
		this.id = id;
		this.provincia = provincia;
		this.nombre = nombre;
	}
	
	public Ciudad() {
		super();
	}
	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("Idprov", provincia.getId());
		temp.put("Nom", nombre);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarCiudad(?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("id", id);
		temp.put("Idprov", provincia.getId());
		temp.put("Nom", nombre);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarCiudad(?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("id", id);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarCiudad(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public Ciudad buscar(int id) {
		List<Ciudad> ciudades = listar(String.format("WHERE idciudad=%s", id));
		
		if(ciudades.size() > 0)
			return ciudades.get(0);
			
		return null;
	}
	@Override
	public List<Ciudad> listar(String textoBusqueda) {
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idciudad, idprovincia, nombre FROM Ciudades " + textoBusqueda, state);
			
			StringBuilder provinciasSb = new StringBuilder("(");
			while(datos.next()) {
				ciudades.add(new Ciudad(datos.getInt("idciudad"), new Provincia(datos.getInt("idprovincia"), null, null), datos.getString("nombre")));
				provinciasSb.append(String.format("%s,", datos.getInt("idprovincia")));
			}
			
			if(provinciasSb.charAt(provinciasSb.length() - 1) == ',')
				provinciasSb.setCharAt(provinciasSb.length() - 1, ')');
			else
				provinciasSb.append("0)");
			
			List<Provincia> provincias = new Provincia().listar(String.format("WHERE idprovincia IN %s", provinciasSb.toString()));
			
			for(Ciudad ciudad : ciudades) {
				provincias.forEach(p -> {
					if(ciudad.getProvincia().getId() == p.getId())
						ciudad.setProvincia(p);
				});
			}
			
			return ciudades;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ciudades;
		}
	}
	@Override
	public List<Cliente> getAllTable() {
		// TODO Auto-generated method stub
		return null;
	}
}
