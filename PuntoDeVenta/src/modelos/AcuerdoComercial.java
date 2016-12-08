package modelos;

import java.util.Date;

public abstract class AcuerdoComercial {
	protected int noDocumento;
	public int getNoDocumento() {
		return noDocumento;
	}
	public void setNoDocumento(int noDocumento) {
		this.noDocumento = noDocumento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public AcuerdoComercial() {
		super();
		this.efectuado = false;
	}
	public AcuerdoComercial(int noDocumento, Date fecha, float total) {
		super();
		this.efectuado = false;
		this.noDocumento = noDocumento;
		this.fecha = fecha;
		this.total = total;
	}
	
	/**
	 * Al ejecutar el método efectuar, cualquiera que sea el mecanismo para registrar
	 * el acuerdo comercial, se debe de cambiar el valor del miembro efectuado a true
	 */
	public void efectuar() {
		efectuado = true;
	}
	
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	protected Date fecha;
	protected float total;
	protected boolean efectuado;
}
