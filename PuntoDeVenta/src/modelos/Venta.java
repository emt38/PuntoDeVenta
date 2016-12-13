package modelos;

import java.util.List;

public class Venta extends IntercambioComercial implements IEntidadDatos<Venta> {
	
	private Cliente cliente;
	private Usuario cajero;
	private int terminalVentas;
	private Tienda tienda;
	private int efectivoRecibido;
	private int cambioDevuelto;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getCajero() {
		return cajero;
	}

	public void setCajero(Usuario cajero) {
		this.cajero = cajero;
	}

	public int getTerminalVentas() {
		return terminalVentas;
	}

	public void setTerminalVentas(int terminalVentas) {
		this.terminalVentas = terminalVentas;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public int getEfectivoRecibido() {
		return efectivoRecibido;
	}

	public void setEfectivoRecibido(int efectivoRecibido) {
		this.efectivoRecibido = efectivoRecibido;
	}

	public int getCambioDevuelto() {
		return cambioDevuelto;
	}

	public void setCambioDevuelto(int cambioDevuelto) {
		this.cambioDevuelto = cambioDevuelto;
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
	public Venta buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venta> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void registrarInventario() {
		// TODO Auto-generated method stub

	}

	public Venta(Cliente cliente, Usuario cajero, int terminalVentas, Tienda tienda, int efectivoRecibido,
			int cambioDevuelto) {
		super();
		this.cliente = cliente;
		this.cajero = cajero;
		this.terminalVentas = terminalVentas;
		this.tienda = tienda;
		this.efectivoRecibido = efectivoRecibido;
		this.cambioDevuelto = cambioDevuelto;
	}

	public Venta() {
		super();
	}
	
}
