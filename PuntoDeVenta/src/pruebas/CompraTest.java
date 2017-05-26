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
		List<Producto> productos = new Producto().listar();
		for(Producto p : productos) {
			temp.agregarArticulo(new Articulo(p, 1, 10, 0, 0, 10));
		}
		assertTrue(temp.insertar());
	}
	
//	@Test
//	public void testBuscar() {
//		List<Compra> temp = new Compra().listar("LIMIT 0,1");
//		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
//		if(temp.size() > 0) {
//			Compra f = temp.get(0);
//			Compra c = new Compra().buscar(f.getNoDocumento());
//			assertEquals(c.getNoDocumento(), f.getNoDocumento());
//		}
//	}
//	
//	@Test
//	public void testActualizar() {
//		List<Compra> temp = new Compra().listar("LIMIT 0,1");
//		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
//		if(temp.size() > 0) {
//			Compra c = temp.get(0);
//			String s = c.getNombre();
//			c.setNombre("Prueba de Actualizacion");
//			assertTrue("No se pudo ejecutar la actualización con la base de datos", c.actualizar());
//			c = c.buscar(c.getId());
//			assertEquals("El nombre no fue actualizado en la base de datos", c.getNombre(), "Prueba de Actualizacion");
//			c.setNombre(s);
//			assertTrue(c.actualizar());
//		}
//	}
//	
//	@Test
//	public void testEliminar() {
//		Compra cl = new Compra(0, new Date(), 1f);
//		cl.setNombre("Nombre de prueba");
//		cl.setApellido("Apellido de prueba");
//		cl.setCelular("809-111-1111");
//		cl.setDireccion("Yo vivo lejos");
//		cl.setIdentificacion("402-2463259-2");
//		cl.setSexo("Masculino");
//		boolean ins = cl.insertar();
//		assertTrue("No se pudo insertar sujeto de prueba", ins);
//		
//		if(ins) {
//			List<Compra> temp = new Compra().listar("ORDER BY idCompra DESC LIMIT 0, 1");
//			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
//			if(temp.size() > 0) {
//				Compra c = temp.get(0);
//				assertTrue(c.eliminar());
//			}
//		}
//	}
//	
//	@Test
//	public void testListar() {
//		Compra cl = new Compra(0, new Date(), 1f);
//		cl.setNombre("Nombre de prueba");
//		cl.setApellido("Apellido de prueba");
//		cl.setCelular("809-111-1111");
//		cl.setDireccion("Yo vivo lejos");
//		cl.setIdentificacion("402-2463259-2");
//		cl.setSexo("Masculino");
//		boolean ins = cl.insertar();
//		
//		assertTrue("No se pudo insertar sujeto de prueba", ins);
//		
//		if(ins) {
//			List<Compra> temp = new Compra().listar("ORDER BY idCompra DESC LIMIT 0, 1");
//			assertNotEquals("El listar no trajo devuelta ningún elemento", temp.size(), 0);
//			if(temp.size() > 0) {
//				Compra c = temp.get(0);
//				c.eliminar();
//			}
//		}
//	}
}
