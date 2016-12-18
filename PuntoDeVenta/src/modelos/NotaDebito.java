package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class NotaDebito extends AcuerdoComercial implements IEntidadDatos<NotaDebito> {
	
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
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_total", total);
		temp.put("_efectuado", efectuado);
		temp.put("_idSuplidor", suplidor.getId());
		temp.put("_concepto", concepto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL AgregarNotaDebito(?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idNotaDebito", noDocumento);
		temp.put("_total", total);
		temp.put("_efectuado", efectuado);
		temp.put("_idSuplidor", suplidor.getId());
		temp.put("_concepto", concepto);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL ModificarNotaDebito(?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idNotaDebito", noDocumento);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarNotaDebito(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	@Override
	public NotaDebito buscar(int id) {
		List<NotaDebito> notasDebito = listar(String.format("WHERE idnotadebito=%s", id));
		
		if(notasDebito.size() > 0)
			return notasDebito.get(0);
		
		return null;
	}

	@Override
	public List<NotaDebito> listar(String textoBusqueda) {
		List<NotaDebito> notasDebito = new ArrayList<>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idnotadebito as id, fecha, total, efectuado, idsuplidor, concepto FROM notasdebito " + textoBusqueda, state);
			
			StringBuilder suplidoresSb = new StringBuilder("(");
			NotaDebito itera;
			while(datos.next()) {
				itera = new NotaDebito();
				itera.noDocumento = datos.getInt("id");
				itera.concepto = datos.getString("concepto");
				itera.fecha =datos.getDate("fecha");
				itera.suplidor = new Suplidor(datos.getInt("idsuplidor"), null, null);
				itera.total = datos.getFloat("total");
				itera.efectuado = datos.getBoolean("efectuado");
				notasDebito.add(itera);
				suplidoresSb.append(String.format("%s,", datos.getInt("idsuplidor")));
			}
			
			if(suplidoresSb.charAt(suplidoresSb.length() - 1) == ',')
				suplidoresSb.setCharAt(suplidoresSb.length() - 1, ')');
			else
				suplidoresSb.append("0)");
			
			List<Suplidor> suplidores = new Suplidor().listar(String.format("WHERE idsuplidor IN %s", suplidoresSb.toString()));
			
			for(NotaDebito notaDebito : notasDebito) {
				suplidores.forEach(s -> {
					if(notaDebito.getSuplidor().getId() == s.getId())
						notaDebito.setSuplidor(s);
				});
			}
			
			return notasDebito;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return notasDebito;
		}
	}

}
