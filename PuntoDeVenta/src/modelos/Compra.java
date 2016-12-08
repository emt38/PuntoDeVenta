package modelos;

import java.util.List;

public class Compra extends IntercambioComercial implements IEntidadDatos<IntercambioComercial> {
	
	private Suplidor suplidor;
	private Usuario supervisor;
	private Tienda tienda;
	
	public Suplidor getSuplidor() {
		return suplidor;
	}

	public void setSuplidor(Suplidor suplidor) {
		this.suplidor = suplidor;
	}

	public Usuario getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
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
	public IntercambioComercial buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IntercambioComercial> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void registrarInventario() {
		// TODO Auto-generated method stub

	}

}
