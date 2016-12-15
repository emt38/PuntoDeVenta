package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class Cliente extends Persona implements IEntidadDatos<Cliente> {
	private int id;
	private Date clienteDesde;
	private float tasaDescuento;
	
	public Date getClienteDesde() {
		return clienteDesde;
	}

	public void setClienteDesde(Date clienteDesde) {
		this.clienteDesde = clienteDesde;
	}

	public float getTasaDescuento() {
		return tasaDescuento;
	}

	public void setTasaDescuento(float tasaDescuento) {
		this.tasaDescuento = tasaDescuento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Cliente(int id, Date clienteDesde, float tasaDescuento) {
		super();
		this.id = id;
		this.clienteDesde = clienteDesde;
		this.tasaDescuento = tasaDescuento;
	}

	public Cliente() {
		super();
	}

	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("nom", nombre);
		temp.put("ape", apellido);
		temp.put("dir", direccion);
		temp.put("tel", telefono);
		temp.put("cel", celular);
		temp.put("ident", identificacion);
		temp.put("sex", sexo.toLowerCase() == "masculino");
		temp.put("tasadesc", tasaDescuento);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarCliente(?,?,?,?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("id", id);
		temp.put("nom", nombre);
		temp.put("ape", apellido);
		temp.put("dir", direccion);
		temp.put("tel", telefono);
		temp.put("cel", celular);
		temp.put("ident", identificacion);
		temp.put("sex", sexo.toLowerCase() == "masculino");
		temp.put("tasadesc", tasaDescuento);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarCliente(?,?,?,?,?,?,?,?,?)", temp, gate);
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
			return Utilidades.ejecutarCall("CALL EliminarCliente(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Cliente buscar(int id) {
		List<Cliente> clientes = listar(String.format("WHERE idcliente=%s", id));
		
		if(clientes.size() > 0)
			return clientes.get(0);
			
		return null;
	}
	@Override
	public List<Cliente> listar(String textoBusqueda) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idcliente, nombre, apellido, direccion, telefono, celular, identificacion, sexo, tasaDescuento, clienteDesde FROM clientes " + textoBusqueda, state);
			Cliente itera;
			
			while(datos.next()) {
				itera = new Cliente();
				itera.id = datos.getInt("idcliente");
				itera.nombre = datos.getString("nombre");
				itera.apellido = datos.getString("apellido");
				itera.telefono = datos.getString("telefono");
				itera.celular = datos.getString("celular");
				itera.identificacion = datos.getString("identificacion");
				itera.sexo = datos.getBoolean("sexo") ? "Masculino" : "Femenino";
				itera.clienteDesde = datos.getDate("clienteDesde");
				itera.tasaDescuento = datos.getFloat("tasaDescuento");
				clientes.add(itera);
			}
			return clientes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return clientes;
		}
	}

	@Override
	public List<Cliente> getAllTable() {
     List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idcliente, nombre, apellido, direccion, telefono, celular, identificacion, sexo, tasaDescuento, clienteDesde FROM clientes " , state);
			Cliente itera;
			
			while(datos.next()) {
				itera = new Cliente();
				itera.id = datos.getInt("idcliente");
				itera.nombre = datos.getString("nombre");
				itera.apellido = datos.getString("apellido");
				itera.telefono = datos.getString("telefono");
				itera.celular = datos.getString("celular");
				itera.identificacion = datos.getString("identificacion");
				itera.sexo = datos.getBoolean("sexo") ? "Masculino" : "Femenino";
				itera.clienteDesde = datos.getDate("clienteDesde");
				itera.tasaDescuento = datos.getFloat("tasaDescuento");
				clientes.add(itera);
			}
			return clientes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return clientes;
		}
	}
}
