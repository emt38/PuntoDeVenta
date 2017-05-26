package pruebas;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import modelos.Ciudad;
import modelos.Cliente;
import modelos.Usuario;
import modelos.Pais;
import modelos.Provincia;
import modelos.Tienda;

public class UsuarioTest {

	@Test
	public void testInsertar() {
		Pais pais = new Pais(100, "Narnia");
		Provincia provincia = new Provincia(100, pais, "Narnia state");
		Ciudad ciudad = new Ciudad(100, provincia, "Narnia city");
		Tienda tienda = new Tienda(100, "Empresa Ink.", "Drieccion", "Slogan", ciudad);
		
		Usuario temp = new Usuario(100, "PruebaNombreUsuario", "pruebaHashClave", "pruebaSalesClave", "PruebaNombreCompleto", 
				modelos.TipoUsuario.values()[1], tienda);
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar() {
		List<Usuario> temp = new Usuario().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Usuario f = temp.get(0);
			Usuario c = new Usuario().buscar(f.getId());
			assertEquals(c.getId(), f.getId());
			assertEquals(c.getNombreUsuario(), f.getNombreUsuario());
			assertEquals(c.getNombreCompleto(), f.getNombreCompleto());
			
		}
	}
	
	@Test
	public void testActualizar() {
		List<Usuario> temp = new Usuario().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Usuario c = temp.get(0);
			String s = c.getNombreCompleto();
			c.setNombreCompleto("Prueba de Actualizacion");
			assertTrue("No se pudo ejecutar la actualización con la base de datos", c.actualizar());
			c = c.buscar(c.getId());
			assertEquals("El nombre no fue actualizado en la base de datos", c.getNombreCompleto(), "Prueba de Actualizacion");
			c.setNombreCompleto(s);
			assertTrue(c.actualizar());
		}
	}
	
	@Test
	public void testEliminar() {
		Pais pais = new Pais(100, "Narnia");
		Provincia provincia = new Provincia(100, pais, "Narnia state");
		Ciudad ciudad = new Ciudad(100, provincia, "Narnia city");
		Tienda tienda = new Tienda(100, "Empresa Ink.", "Drieccion", "Slogan", ciudad);
		
		Usuario usr = new Usuario(100, "PruebaNombreUsuario", "pruebaHashClave", "pruebaSalesClave", "PruebaNombreCompleto", 
				modelos.TipoUsuario.values()[1], tienda);
		boolean ins = usr.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Usuario> temp = new Usuario().listar("ORDER BY idusuario DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
			if(temp.size() > 0) {
				Usuario c = temp.get(0);
				assertTrue(c.eliminar());
			}
		}
	}
	
	@Test
	public void testListar() {
		Pais pais = new Pais(100, "Narnia");
		Provincia provincia = new Provincia(100, pais, "Narnia state");
		Ciudad ciudad = new Ciudad(100, provincia, "Narnia city");
		Tienda tienda = new Tienda(100, "Empresa Ink.", "Drieccion", "Slogan", ciudad);
		
		Usuario usr = new Usuario(100, "PruebaNombreUsuario", "pruebaHashClave", "pruebaSalesClave", "PruebaNombreCompleto", 
				modelos.TipoUsuario.values()[1], tienda);
		boolean ins = usr.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Usuario> temp = new Usuario().listar("ORDER BY idusuario DESC LIMIT 0, 1");
			assertNotEquals("El listar no trajo devuelta ningún elemento", temp.size(), 0);
			if(temp.size() > 0) {
				Usuario c = temp.get(0);
				c.eliminar();
			}
		}
	}

}
