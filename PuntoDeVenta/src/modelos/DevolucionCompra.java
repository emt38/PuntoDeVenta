package modelos;

import java.util.List;

public class DevolucionCompra implements IEntidadDatos<DevolucionCompra> {
	
	private Compra compra;
	private Usuario supervisor;
	private NotaDebito notaDebito;
	private List<Articulo> articulos;
	
	public void devolverArticulo(Articulo articulo, float cantidad) {
		if(cantidad > 0 && cantidad <= articulo.getCantidad() )
			articulo.setCantidad(cantidad);
		
		articulo.totalizar();
	}
	
	public void generarNotaDebito() {
		NotaDebito temp = new NotaDebito(compra.getSuplidor(), String.format("Nota de débito por devolución de Compra #%s", compra.getNoDocumento())); 
		float total = 0f;
		
		for(Articulo item : articulos) {
			total += item.getSubTotal();
		}
		
		temp.total = total;
		this.notaDebito = temp;
	}
	
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Usuario getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}
	public NotaDebito getNotaDebito() {
		return notaDebito;
	}
	public void setNotaDebito(NotaDebito notaDebito) {
		this.notaDebito = notaDebito;
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
	public DevolucionCompra buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<DevolucionCompra> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getAllTable() {
		// TODO Auto-generated method stub
		return null;
	}
}
