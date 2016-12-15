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
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_suplidor", suplidor);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarNotaCredito(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public NotaDebito buscar(int id) {
		// TODO Auto-generated method stub
		List<AcuerdoComercial> NotasDebito = listar(String.format("WHERE suplidor=%s", id));
		if(NotasDebito.size() > 0)
			return NotasDebito.get(0);
		return null;
	}

	@Override
	public List<NotaDebito> listar(String textoBusqueda) {
		// Mi primer commit, ahora mira c�mo
		List<NotaDebito> notasDebito = new ArrayList<>();
		return null;
	}

}
