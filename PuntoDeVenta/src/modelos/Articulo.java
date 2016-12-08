package modelos;

public class Articulo {
	
	private Producto producto;
	private float cantidad;
	private float valor;
	private float tasaImpuestos;
	private float impuestos;
	private float subTotal;
	
	public void totalizar() {
		impuestos = valor * producto.getTasaImpuesto() / 100 * cantidad;
		subTotal = valor * cantidad - impuestos;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(float impuestos) {
		this.impuestos = impuestos;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public float getTasaImpuestos() {
		return tasaImpuestos;
	}

	public void setTasaImpuestos(float tasaImpuestos) {
		this.tasaImpuestos = tasaImpuestos;
	}
}
