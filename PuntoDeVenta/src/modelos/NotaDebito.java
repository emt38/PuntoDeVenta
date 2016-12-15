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
