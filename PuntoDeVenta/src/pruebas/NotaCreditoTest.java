package pruebas;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import modelos.Cliente;
import modelos.NotaCredito;
import modelos.Cliente;

public class NotaCreditoTest {

	@Test
	public void testInsertar() {
		NotaCredito temp = new NotaCredito();
		temp.setConcepto("Concepto de Prueba");
		temp.setFecha(new Date());
		temp.setCliente(new Cliente().listar().get(0));
		temp.setTotal(500);
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar() {
		List<NotaCredito> temp = new NotaCredito().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			NotaCredito f = temp.get(0);
			NotaCredito c = new NotaCredito().buscar(f.getNoDocumento());
			assertEquals(c.getNoDocumento(), f.getNoDocumento());
			assertEquals(c.getConcepto(), f.getConcepto());
			
		}
	}
	
	@Test
	public void testActualizar() {
		List<NotaCredito> temp = new NotaCredito().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			NotaCredito c = temp.get(0);
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
		NotaCredito cl = new NotaCredito();
		cl.setConcepto("Concepto de Prueba");
		cl.setFecha(new Date());
		cl.setCliente(new Cliente().listar().get(0));
		cl.setTotal(500);
		boolean ins = cl.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<NotaCredito> temp = new NotaCredito().listar("ORDER BY idNotaCredito DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
			if(temp.size() > 0) {
				NotaCredito c = temp.get(0);
				assertTrue(c.eliminar());
			}
		}
	}
	
	@Test
	public void testListar() {
		NotaCredito cl = new NotaCredito();
		cl.setConcepto("Concepto de Prueba");
		cl.setFecha(new Date());
		cl.setCliente(new Cliente().listar().get(0));
		cl.setTotal(500);
		boolean ins = cl.insertar();
		
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<NotaCredito> temp = new NotaCredito().listar("ORDER BY idNotaCredito DESC LIMIT 0, 1");
			assertNotEquals("El listar no trajo devuelta ningún elemento", temp.size(), 0);
			if(temp.size() > 0) {
				NotaCredito c = temp.get(0);
				c.eliminar();
			}
		}
	}
}
