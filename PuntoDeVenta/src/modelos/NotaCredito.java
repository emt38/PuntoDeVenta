<<<<<<< HEAD
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

public class NotaCredito extends AcuerdoComercial implements IEntidadDatos<AcuerdoComercial> {
	
	private int idNotaCredito;
	private Date fecha;
	private float total;
	private float efectuado;
	private Cliente cliente;
	private String concepto;
	
	public NotaCredito() {
		super();
	}

	public NotaCredito(int idNotaCredito, Date fecha, float total, float efectuado, Cliente cliente, String concepto) {
		super();
		this.idNotaCredito = idNotaCredito;
		this.fecha = fecha;
		this.total = total;
		this.efectuado = efectuado;
		this.cliente = cliente;		
		this.concepto = concepto;
	}
	
	

	public int getIdNotaCredito() {
		return idNotaCredito;
	}

	public void setIdNotaCredito(int idNotaCredito) {
		this.idNotaCredito = idNotaCredito;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getEfectuado() {
		return efectuado;
	}

	public void setEfectuado(float efectuado) {
		this.efectuado = efectuado;
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
		temp.put("_idNotaCredito", idNotaCredito);
		temp.put("_fecha", fecha);
		temp.put("_total", total);
		temp.put("_efectuado", efectuado);
		temp.put("_idcliente", cliente.getId());
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
		temp.put("_idNotaCredito", idNotaCredito);
		temp.put("_fecha", fecha);
		temp.put("_total", total);
		temp.put("_efectuado", efectuado);
		temp.put("_cliente", cliente.getId());
		temp.put("_concepto", concepto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarNotaCredito(?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean eliminar() {
		// TODO Auto-generated method stub
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_cliente", cliente);
		
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
		List<AcuerdoComercial> NotasCreditos = listar(String.format("WHERE cliente=%s", id));
		if(NotasCreditos.size() > 0)
			return NotasCreditos.get(0);
		return null;
	}

	@Override
	public List<AcuerdoComercial> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		/*List<AcuerdoComercial> notasCredito = new ArrayList<AcuerdoComercial>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idnotacredito, fecha, total, efectuado, idcliente, concepto FROM notascredito " + textoBusqueda, state);
			
			StringBuilder paisesSb = new StringBuilder("(");
			while(datos.next()) {
				//notasCredito.add(new NotaCredito(datos.getInt("idprovincia"), new Pais(datos.getInt("idpais"), null), datos.getString("nombre")));
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
=======
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

	@Override
	public List<Cliente> getAllTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
>>>>>>> branch 'master' of https://github.com/emt38/PuntoDeVenta.git
