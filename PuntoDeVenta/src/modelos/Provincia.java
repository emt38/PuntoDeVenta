package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class Provincia implements IEntidadDatos<Provincia> {
	private int id;
	private Pais pais;
	private String nombre;
	
	public Provincia() {
		super();
	}
	public Provincia(int id, Pais pais, String nombre) {
		super();
		this.id = id;
		this.pais = pais;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.getNombre());
	};
	
	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idpais", pais.getId());
		temp.put("_nombre", nombre);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarProvincia(?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idProvincia", id);
		temp.put("_idpais", pais.getId());
		temp.put("_nombre", nombre);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarProvincia(?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idProvincia", id);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarProvincia(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	@Override
	public Provincia buscar(int id) {
		List<Provincia> provincias = listar(String.format("WHERE idprovincia=%s", id));
		if(provincias.size() > 0)
			return provincias.get(0);
		
		return null;
	}
	@Override
	public List<Provincia> listar(String textoBusqueda) {
		List<Provincia> provincias = new ArrayList<Provincia>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idprovincia, idpais, nombre FROM Provincias " + textoBusqueda, state);
			
			StringBuilder paisesSb = new StringBuilder("(");
			while(datos.next()) {
				provincias.add(new Provincia(datos.getInt("idprovincia"), new Pais(datos.getInt("idpais"), null), datos.getString("nombre")));
				paisesSb.append(String.format("%s,", datos.getInt("idprovincia")));
			}
			
			if(paisesSb.charAt(paisesSb.length() - 1) == ',')
				paisesSb.setCharAt(paisesSb.length() - 1, ')');
			else
				paisesSb.append("0)");
			
			List<Pais> paises = new Pais().listar(String.format("WHERE idpais IN %s", paisesSb.toString()));
			
			for(Provincia provincia : provincias) {
				paises.forEach(p -> {
					if(provincia.getPais().getId() == p.getId())
						provincia.setPais(p);
				});
			}
			
			return provincias;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return provincias;
		}
	}
}
