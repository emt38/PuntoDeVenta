package modelos;

import java.util.List;

public abstract class IntercambioComercial extends AcuerdoComercial {
	protected float subTotal;
	public float getSubTotal() {
		return subTotal;
	}
	public float getImpuestos() {
		return impuestos;
	}
	public float getDescuentos() {
		return descuentos;
	}
	public void setDescuentos(float descuento) {
		this.descuentos = descuento;
	}
	public List<Articulo> getArticulos() {
		return articulos;
	}
	protected float impuestos;
	protected float descuentos;
	protected List<Articulo> articulos;
	
	protected abstract void registrarInventario();
	public void agregarArticulo(Articulo articulo)
	{
		articulos.add(articulo);
		totalizar();
	}
	public void retirarArticulo(Articulo articulo)
	{
		articulos.remove(articulo);
		totalizar();
	}
	public void totalizar()
	{
		float total = 0f;
		float impuestos = 0f;
		
		for(Articulo item : articulos) {
			total += item.getSubTotal();
			impuestos += item.getImpuestos();
		}
		
		this.total = total;
		this.impuestos = impuestos;
	}
}
