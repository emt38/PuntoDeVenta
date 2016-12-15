package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class Suplidor extends Persona implements IEntidadDatos<Suplidor> {
	
	private int id;
	
	public Suplidor() {
		super();
	}

	public Suplidor(int id, String rnc, String empresa) {
		super();
		this.id = id;
		this.rnc = rnc;
		this.empresa = empresa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	private String rnc;
	private String empresa;
	
	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_nombre", nombre);
		temp.put("_apellido", apellido);
		temp.put("_direccion", direccion);
		temp.put("_telefono", telefono);
		temp.put("_celular", celular);
		temp.put("_sexo", sexo.toLowerCase() == "masculino");
		temp.put("_empresa", empresa);
		temp.put("_rnc", rnc);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarSuplidor(?,?,?,?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idSuplidor", id);
		temp.put("_nombre", nombre);
		temp.put("_apellido", apellido);
		temp.put("_direccion", direccion);
		temp.put("_telefono", telefono);
		temp.put("_celular", celular);
		temp.put("_sexo", sexo.toLowerCase() == "masculino");
		temp.put("_empresa", empresa);
		temp.put("_rnc", rnc);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarSuplidor(?,?,?,?,?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idSuplidor", id);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarSuplidor(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Suplidor buscar(int id) {
		List<Suplidor> suplidores = listar(String.format("WHERE idsuplidor=%s", id));
		
		if(suplidores.size() > 0)
			return suplidores.get(0);
		
		return null;
	}

	@Override
	public List<Suplidor> listar(String textoBusqueda) {
		List<Suplidor> suplidores = new ArrayList<Suplidor>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idsuplidor, nombre, apellido, direccion, telefono, celular, sexo, rnc, empresa FROM suplidores " + textoBusqueda, state);
			Suplidor itera;
			
			while(datos.next()) {
				itera = new Suplidor();
				itera.id = datos.getInt("idsuplidor");
				itera.nombre = datos.getString("nombre");
				itera.apellido = datos.getString("apellido");
				itera.telefono = datos.getString("telefono");
				itera.celular = datos.getString("celular");
				itera.sexo = datos.getBoolean("sexo") ? "Masculino" : "Femenino";
				itera.empresa = datos.getString("empresa");
				itera.rnc = datos.getString("rnc");
				suplidores.add(itera);
			}
			return suplidores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return suplidores;
		}
	}

	@Override
	public List<Cliente> getAllTable() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
