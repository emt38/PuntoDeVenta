package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class NotaCredito extends AcuerdoComercial implements IEntidadDatos<AcuerdoComercial> {
	
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
		// TODO Auto-generated method stub
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idCliente", cliente.getId());
		temp.put("_concepto", concepto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarNotaCredito(?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean actualizar() {
		// TODO Auto-generated method stub
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idCliente", cliente);
		temp.put("_concepto", concepto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarNotaDebito(?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean eliminar() {
		// TODO Auto-generated method stub
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idCliente", cliente);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarNotaCredito(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public AcuerdoComercial buscar(int id) {
		// TODO Auto-generated method stub
		List<AcuerdoComercial> acuerdosComerciales = listar(String.format("WHERE idCliente=%s", id));
		if(acuerdosComerciales.size() > 0)
			return acuerdosComerciales.get(0);
		return null;
	}

	@Override
	public List<AcuerdoComercial> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		//DEBO TERMINARLO LUEGO ATT: FRANKLYN
		/*
		List<AcuerdoComercial> acuerdosComerciales = new ArrayList<AcuerdoComercial>();
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
		}*/
		return null;
	}

}
