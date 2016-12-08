package modelos;

import java.util.List;

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
	public AcuerdoComercial buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AcuerdoComercial> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}

}
