package modelos;

import java.util.List;

public class DevolucionVenta implements IEntidadDatos<DevolucionVenta> {
	
	private Venta venta;
	private Usuario supervisor;
	private NotaCredito notaCredito;
	private List<Articulo> articulos;
	
	public void devolverArticulo(Articulo articulo, float cantidad) {
		if(cantidad > 0 && cantidad <= articulo.getCantidad() )
			articulo.setCantidad(cantidad);
		
		articulo.totalizar();
	}
	
	public void generarNotaCredito() {
		NotaCredito temp = new NotaCredito(venta.getCliente(), String.format("Nota de Crédito por devolución de Venta #%s", venta.getNoDocumento())); 
		float total = 0f;
		
		for(Articulo item : articulos) {
			total += item.getSubTotal();
		}
		
		temp.total = total;
		this.notaCredito = temp;
	}
	
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Usuario getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}
	public NotaCredito getNotaCredito() {
		return notaCredito;
	}
	public void setNotaDebito(NotaCredito notaCredito) {
		this.notaCredito = notaCredito;
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
	public DevolucionVenta buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DevolucionVenta> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getAllTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
