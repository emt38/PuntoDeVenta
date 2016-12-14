package modelos;

import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar() {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public List<NotaDebito> listar(String textoBusqueda) {
		// Mi primer commit, ahora mira cómo
		List<NotaDebito> notasDebito = new ArrayList<>();
		return null;
	}

}
