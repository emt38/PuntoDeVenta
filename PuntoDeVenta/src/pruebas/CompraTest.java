package pruebas;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import modelos.Articulo;
import modelos.Cliente;
import modelos.Compra;
import modelos.Producto;
import modelos.Suplidor;
import modelos.Tienda;
import modelos.Usuario;

public class CompraTest {

	@Test
	public void testInsertar() {
		Compra temp = new Compra();
		temp.setSupervisor(new Usuario().listar("LIMIT 0,1").get(0));
		temp.setSuplidor(new Suplidor().listar("LIMIT 0,1").get(0));
		temp.setTienda(new Tienda().listar("LIMIT 0,1").get(0));
		temp.setFecha(new Date());
		List<Producto> productos = new Producto().listar("LIMIT 0, 5");
		for(Producto p : productos) {
			temp.agregarArticulo(new Articulo(p, 1, 10, 0, 0, 10));
		}
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar() {
		List<Compra> temp = new Compra().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Compra f = temp.get(0);
			Compra c = new Compra().buscar(f.getNoDocumento());
			assertEquals(c.getNoDocumento(), f.getNoDocumento());
		}
	}
	
	@Test
	public void testActualizar() {
		List<Compra> temp = new Compra().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Compra c = temp.get(0);
			float s = c.getDescuentos();
			c.setDescuentos(s + 25f);
			assertTrue("No se pudo ejecutar la actualización con la base de datos", c.actualizar());
			c = c.buscar(c.getNoDocumento());
			assertTrue("El registro no fue actualizado en la base de datos",  new Float(c.getDescuentos()).equals((s + 25f)));
			c.setDescuentos(s);
			assertTrue(c.actualizar());
		}
	}
	
	@Test
	public void testEliminar() {
		Compra temp = new Compra();
		temp.setSupervisor(new Usuario().listar("LIMIT 0,1").get(0));
		temp.setSuplidor(new Suplidor().listar("LIMIT 0,1").get(0));
		temp.setTienda(new Tienda().listar("LIMIT 0,1").get(0));
		temp.setFecha(new Date());
		List<Producto> productos = new Producto().listar();
		for(Producto p : productos) {
			temp.agregarArticulo(new Articulo(p, 1, 10, 0, 0, 10));
		}
		boolean ins = temp.insertar();
		assertTrue(ins);
		
		if(ins) {
			List<Compra> list = new Compra().listar("ORDER BY idCompra DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", list.size(), 0);
			if(list.size() > 0) {
				Compra c = list.get(0);
				assertTrue(c.eliminar());
			}
		}
	}
	
	@Test
	public void testListar() {
		Compra temp = new Compra();
		temp.setSupervisor(new Usuario().listar("LIMIT 0,1").get(0));
		temp.setSuplidor(new Suplidor().listar("LIMIT 0,1").get(0));
		temp.setTienda(new Tienda().listar("LIMIT 0,1").get(0));
		temp.setFecha(new Date());
		List<Producto> productos = new Producto().listar();
		for(Producto p : productos) {
			temp.agregarArticulo(new Articulo(p, 1, 10, 0, 0, 10));
		}
		boolean ins = temp.insertar();
		
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Compra> list = new Compra().listar("ORDER BY idCompra DESC LIMIT 0, 1");
			assertNotEquals("El listar no trajo devuelta ningún elemento", list.size(), 0);
			if(list.size() > 0) {
				Compra c = list.get(0);
				c.eliminar();
			}
		}
	}
}
