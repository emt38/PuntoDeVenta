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

public class NotaCredito extends AcuerdoComercial implements IEntidadDatos<NotaCredito> {
	
	private Cliente cliente;
	private String concepto;
	
	public NotaCredito() {
		super();
	}

	public NotaCredito(Cliente cliente, String concepto) {
		super();
		this.cliente = cliente;
		this.concepto = concepto;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_total", total);
		temp.put("_efectuado", efectuado);
		temp.put("_idCliente", cliente.getId());
		temp.put("_concepto", concepto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarNotaCredito(?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idNotaCredito", noDocumento);
		temp.put("_total", total);
		temp.put("_efectuado", efectuado);
		temp.put("_idCliente", cliente.getId());
		temp.put("_concepto", concepto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarNotaCredito(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idNotaCredito", noDocumento);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarNotaCredito(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public NotaCredito buscar(int id) {
		List<NotaCredito> notasCredito = listar(String.format("WHERE idnotacredito=%s", id));
		
		if(notasCredito.size() > 0)
			return notasCredito.get(0);
		
		return null;
	}

	@Override
	public List<NotaCredito> listar(String textoBusqueda) {
		List<NotaCredito> notasCredito = new ArrayList<>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idnotacredito as id, fecha, total, efectuado, idcliente, concepto FROM notascredito " + textoBusqueda, state);
			
			StringBuilder clientesSb = new StringBuilder("(");
			NotaCredito itera;
			while(datos.next()) {
				itera = new NotaCredito();
				itera.noDocumento = datos.getInt("id");
				itera.concepto = datos.getString("concepto");
				itera.fecha =datos.getDate("fecha");
				itera.cliente = new Cliente(datos.getInt("idcliente"), new Date(), 0f);
				itera.total = datos.getFloat("total");
				itera.efectuado = datos.getBoolean("efectuado");
				notasCredito.add(itera);
				clientesSb.append(String.format("%s,", datos.getInt("idsuplidor")));
			}
			
			if(clientesSb.charAt(clientesSb.length() - 1) == ',')
				clientesSb.setCharAt(clientesSb.length() - 1, ')');
			else
				clientesSb.append("0)");
			
			List<Cliente> clientes = new Cliente().listar(String.format("WHERE idcliente IN %s", clientesSb.toString()));
			
			for(NotaCredito notaDebito : notasCredito) {
				clientes.forEach(s -> {
					if(notaDebito.getCliente().getId() == s.getId())
						notaDebito.setCliente(s);
				});
			}
			
			return notasCredito;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return notasCredito;
		}
	}

}
