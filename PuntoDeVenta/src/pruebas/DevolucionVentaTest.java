package pruebas;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import modelos.Articulo;
import modelos.Cliente;
import modelos.Compra;
import modelos.DevolucionVenta;
import modelos.NotaCredito;
import modelos.Producto;
import modelos.Suplidor;
import modelos.Tienda;
import modelos.Usuario;
import modelos.Venta;

public class DevolucionVentaTest {

	@Test
	public void testInsertar() {
		DevolucionVenta temp = new DevolucionVenta();
		temp.setSupervisor(new Usuario().listar("LIMIT 0,1").get(0));
		temp.setVenta(new Venta().listar().get(0));
		temp.generarNotaCredito();
		
		for(Articulo a : temp.getVenta().getArticulos()) {
			temp.devolverArticulo(a, a.getCantidad() / 2);
		}
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar() {
		List<DevolucionVenta> temp = new DevolucionVenta().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			DevolucionVenta f = temp.get(0);
			DevolucionVenta c = new DevolucionVenta().buscar(f.getId());
			assertEquals(c.getId(), f.getId());
		}
	}
	
	@Test
	public void testActualizar() {
		List<DevolucionVenta> temp = new DevolucionVenta().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			DevolucionVenta c = temp.get(0);
			List<Usuario> users = new Usuario().listar();
			
			if(users.size() < 2) {
				fail("Se necesitan al menos dos usuarios para realizar la prueba");
				return;
			}
			
			Usuario respaldo = c.getSupervisor();
			Usuario comp = null;
			
			for (Usuario usuario : users) {
				if(c.getSupervisor().getId() != usuario.getId()) {
					comp = usuario;
					break;
				}
			}
			
			c.setSupervisor(comp);
			assertTrue("No se pudo ejecutar la actualizaci�n con la base de datos", c.actualizar());
			c = c.buscar(c.getId());
			assertEquals("El registro no fue actualizado en la base de datos", comp.getId(), c.getSupervisor().getId());
			c.setSupervisor(respaldo);
			assertTrue(c.actualizar());
		}
	}
	
	@Test
	public void testEliminar() {
		DevolucionVenta temp = new DevolucionVenta();
		temp.setSupervisor(new Usuario().listar("LIMIT 0,1").get(0));
		temp.setVenta(new Venta().listar().get(0));
		temp.generarNotaCredito();
		
		for(Articulo a : temp.getVenta().getArticulos()) {
			temp.devolverArticulo(a, a.getCantidad() / 2);
		}
		boolean ins = temp.insertar();
		assertTrue(ins);
		
		if(ins) {
			List<DevolucionVenta> list = new DevolucionVenta().listar("ORDER BY iddevolucionventa DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", list.size(), 0);
			if(list.size() > 0) {
				DevolucionVenta c = list.get(0);
				assertTrue(c.eliminar());
			}
		}
	}
	
	@Test
	public void testListar() {
		DevolucionVenta temp = new DevolucionVenta();
		temp.setSupervisor(new Usuario().listar("LIMIT 0,1").get(0));
		temp.setVenta(new Venta().listar().get(0));
		temp.generarNotaCredito();
		
		for(Articulo a : temp.getVenta().getArticulos()) {
			temp.devolverArticulo(a, a.getCantidad() / 2);
		}
		boolean ins = temp.insertar();
		
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<DevolucionVenta> list = new DevolucionVenta().listar("ORDER BY iddevolucionventa DESC LIMIT 0, 1");
			assertNotEquals("El listar no trajo devuelta ning�n elemento", list.size(), 0);
			if(list.size() > 0) {
				DevolucionVenta c = list.get(0);
				c.eliminar();
			}
		}
	}
}