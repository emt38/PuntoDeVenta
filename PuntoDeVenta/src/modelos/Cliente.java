package modelos;

import java.util.Date;
import java.util.List;

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
	public Cliente buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}
}
