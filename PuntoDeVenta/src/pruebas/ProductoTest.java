package pruebas;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import modelos.Cliente;
import modelos.Producto;

public class ProductoTest {

	@Test
	public void testInsertar() {
		Producto temp = new Producto();
		temp.setCodigo("ASD123");
		temp.setCosto(10f);
		temp.setDescripcion("Producto de prueba");
		temp.setId(100);
		temp.setInventario(100f);
		temp.setPrecio(10f);
		temp.setTasaImpuesto(0.16f);
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar() {
		List<Producto> temp = new Producto().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Producto f = temp.get(0);
			Producto c = new Producto().buscar(f.getId());
			assertEquals(c.getId(), f.getId());
			assertEquals(c.getCodigo(), f.getCodigo());
			assertEquals(c.getDescripcion(), f.getDescripcion());	
		}
	}
	
	@Test
	public void testActualizar() {
		List<Producto> temp = new Producto().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Producto c = temp.get(0);
			String s = c.getDescripcion();
			c.setDescripcion("Prueba de Actualizacion");
			assertTrue("No se pudo ejecutar la actualización con la base de datos", c.actualizar());
			c = c.buscar(c.getId());
			assertEquals("El nombre no fue actualizado en la base de datos", c.getDescripcion(), "Prueba de Actualizacion");
			c.setDescripcion(s);
			assertTrue(c.actualizar());
		}
	}
	
	@Test
	public void testEliminar() {
		Producto pr = new Producto();
		pr.setCodigo("ASD123");
		pr.setCosto(10f);
		pr.setDescripcion("Producto de prueba");
		pr.setId(100);
		pr.setInventario(100f);
		pr.setPrecio(10f);
		pr.setTasaImpuesto(0.16f);
		boolean ins = pr.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Producto> temp = new Producto().listar("ORDER BY idproducto DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
			if(temp.size() > 0) {
				Producto c = temp.get(0);
				assertTrue(c.eliminar());
			}
		}
	}
	
	@Test
	public void testListar() {
		Producto pr = new Producto();
		pr.setCodigo("ASD123");
		pr.setCosto(10f);
		pr.setDescripcion("Producto de prueba");
		pr.setId(100);
		pr.setInventario(100f);
		pr.setPrecio(10f);
		pr.setTasaImpuesto(0.16f);
		boolean ins = pr.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Producto> temp = new Producto().listar("ORDER BY idproducto DESC LIMIT 0, 1");
			assertNotEquals("El listar no trajo devuelta ningún elemento", temp.size(), 0);
			if(temp.size() > 0) {
				Producto c = temp.get(0);
				c.eliminar();
			}
		}
	}
}
