<<<<<<< HEAD
package modelos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class NotaDebito extends AcuerdoComercial implements IEntidadDatos<NotaDebito> {
	
	private int idNotaDebito;
	private Date fecha;
	private float total;
	private float efectuado;
	private Suplidor suplidor;
	private String concepto;
	
	public NotaDebito() {
		super();
	}

	public NotaDebito(int idNotaDebito, Date fecha, float total, float efectuado, Suplidor suplidor, String concepto) {
		super();
		this.idNotaDebito = idNotaDebito;
		this.fecha = fecha;
		this.total = total;
		this.efectuado = efectuado;
		this.suplidor = suplidor;
		this.concepto = concepto;
	}

	@Override
	public boolean insertar() {
		// TODO Auto-generated method stub
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idNotaCredito", idNotaDebito);
		temp.put("_fecha", fecha);
		temp.put("_total", total);
		temp.put("_efectuado", efectuado);
		temp.put("_supidor", suplidor.getId());
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
		temp.put("_idNotaCredito", idNotaDebito);
		temp.put("_fecha", fecha);
		temp.put("_total", total);
		temp.put("_efectuado", efectuado);
		temp.put("_siplidor", suplidor.getId());
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
		return false;
	}

	@Override
	public NotaDebito buscar(int id) {
		// TODO Auto-generated method stub
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_cliente", cliente);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarNotaCredito(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<NotaDebito> listar(String textoBusqueda) {
		// Mi primer commit, ahora mira cómo
		List<NotaDebito> notasDebito = new ArrayList<>();
		return null;
	}

}
=======
package modelos;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class NotaDebito extends AcuerdoComercial implements IEntidadDatos<modelos.AcuerdoComercial> {
	
	private Suplidor suplidor;
	private String concepto;
	
	public NotaDebito() {
		super();
	}

	public NotaDebito(Suplidor suplidor, String concepto) {
		super();
		this.suplidor = suplidor;
		this.concepto = concepto;
	}

	public Suplidor getSuplidor() {
		return suplidor;
	}

	public void setSuplidor(Suplidor suplidor) {
		this.suplidor = suplidor;
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
		temp.put("_idSuplidor", suplidor.getId());
		temp.put("_concepto", concepto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarNotaDebito(?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizar() {
		// TODO Auto-generated method stub
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idSuplidor", suplidor);
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
		temp.put("_idSuplidor", suplidor);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarNotaDebito(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public modelos.AcuerdoComercial buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<modelos.AcuerdoComercial> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getAllTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
>>>>>>> branch 'master' of https://github.com/emt38/PuntoDeVenta.git
