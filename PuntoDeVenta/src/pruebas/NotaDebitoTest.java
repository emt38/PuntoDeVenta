package pruebas;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import modelos.Cliente;
import modelos.NotaDebito;
import modelos.Suplidor;

public class NotaDebitoTest {

	@Test
	public void testInsertar() {
		NotaDebito temp = new NotaDebito();
		temp.setConcepto("Concepto de Prueba");
		temp.setFecha(new Date());
		temp.setSuplidor(new Suplidor().listar().get(0));
		temp.setTotal(500);
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar() {
		List<NotaDebito> temp = new NotaDebito().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			NotaDebito f = temp.get(0);
			NotaDebito c = new NotaDebito().buscar(f.getNoDocumento());
			assertEquals(c.getNoDocumento(), f.getNoDocumento());
			assertEquals(c.getConcepto(), f.getConcepto());
			
		}
	}
	
	@Test
	public void testActualizar() {
		List<NotaDebito> temp = new NotaDebito().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			NotaDebito c = temp.get(0);
			String s = c.getConcepto();
			c.setConcepto("Prueba de Actualizacion");
			assertTrue("No se pudo ejecutar la actualización con la base de datos", c.actualizar());
			c = c.buscar(c.getNoDocumento());
			assertEquals("El concepto no fue actualizado en la base de datos", c.getConcepto(), "Prueba de Actualizacion");
			c.setConcepto(s);
			assertTrue(c.actualizar());
		}
	}
	
	@Test
	public void testEliminar() {
		NotaDebito cl = new NotaDebito();
		cl.setConcepto("Concepto de Prueba");
		cl.setFecha(new Date());
		cl.setSuplidor(new Suplidor().listar().get(0));
		cl.setTotal(500);
		boolean ins = cl.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<NotaDebito> temp = new NotaDebito().listar("ORDER BY idnotadebito DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
			if(temp.size() > 0) {
				NotaDebito c = temp.get(0);
				assertTrue(c.eliminar());
			}
		}
	}
	
	@Test
	public void testListar() {
		NotaDebito cl = new NotaDebito();
		cl.setConcepto("Concepto de Prueba");
		cl.setFecha(new Date());
		cl.setSuplidor(new Suplidor().listar().get(0));
		cl.setTotal(500);
		boolean ins = cl.insertar();
		
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<NotaDebito> temp = new NotaDebito().listar("ORDER BY idnotadebito DESC LIMIT 0, 1");
			assertNotEquals("El listar no trajo devuelta ningún elemento", temp.size(), 0);
			if(temp.size() > 0) {
				NotaDebito c = temp.get(0);
				c.eliminar();
			}
		}
	}
}
