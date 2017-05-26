package pruebas;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import modelos.Pais;

public class PaisTest {

	@Test
	public void testInsertar() {
		Pais temp = new Pais(100, "Narnia");
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar() {
		List<Pais> temp = new Pais().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Pais f = temp.get(0);
			Pais c = new Pais().buscar(f.getId());
			assertEquals(c.getId(), f.getId());
			assertEquals(c.getNombre(), f.getNombre());
		}
	}
	
	@Test
	public void testActualizar() {
		List<Pais> temp = new Pais().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Pais c = temp.get(0);
			String s = c.getNombre();
			c.setNombre("Prueba de Actualizacion");
			assertTrue("No se pudo ejecutar la actualización con la base de datos", c.actualizar());
			c = c.buscar(c.getId());
			assertEquals("El nombre no fue actualizado en la base de datos", c.getNombre(), "Prueba de Actualizacion");
			c.setNombre(s);
			assertTrue(c.actualizar());
		}
	}
	
	@Test
	public void testEliminar() {
		Pais pais = new Pais(100, "Narnia");
		boolean ins = pais.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Pais> temp = new Pais().listar("ORDER BY idpais DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
			if(temp.size() > 0) {
				Pais c = temp.get(0);
				assertTrue(c.eliminar());
			}
		}
	}
	
	@Test
	public void testListar() {
		Pais pais = new Pais(100, "Narnia");
		boolean ins = pais.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Pais> temp = new Pais().listar("ORDER BY idpais DESC LIMIT 0, 1");
			assertNotEquals("El listar no trajo devuelta ningún elemento", temp.size(), 0);
			if(temp.size() > 0) {
				Pais c = temp.get(0);
				c.eliminar();
			}
		}
	}

}
