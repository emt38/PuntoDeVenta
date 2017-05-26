package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;
import modelos.Articulo;
import modelos.Producto;

public class ArticuloTest {

	@Test
	public void testCantidad() {
		Articulo temp = new Articulo();
		temp.setCantidad(-1f);
		assertTrue(temp.getCantidad() >= 0);
	}
	
	@Test
	public void testValor() {
		Articulo temp = new Articulo();
		temp.setValor(-1f);
		assertTrue(temp.getValor() >= 0);
	}
	
	@Test
	public void testTotalizar() {
		Articulo temp = new Articulo(new Producto(0, "", "", 10, 0f, 18f, 1f), 3f, 10f, 18f, 0f, 0f);
		temp.totalizar();
		assertTrue(temp.getSubTotal() == 35.4f);
		assertTrue(temp.getImpuestos() == 5.4f);
	}
}
