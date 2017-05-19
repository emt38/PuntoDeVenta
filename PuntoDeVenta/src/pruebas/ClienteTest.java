package pruebas;

import modelos.Cliente;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class ClienteTest {

	@Test
	public void testInsertar() {
		Cliente temp = new Cliente(0, new Date(), 1f);
		temp.setNombre("Nombre de prueba");
		temp.setApellido("Apellido de prueba");
		temp.setCelular("809-111-1111");
		temp.setDireccion("Yo vivo lejos");
		temp.setIdentificacion("402-2463259-2");
		temp.setSexo("Masculino");
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar() {
		List<Cliente> temp = new Cliente().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Cliente f = temp.get(0);
			Cliente c = new Cliente().buscar(f.getId());
			assertEquals(c.getId(), f.getId());
			assertEquals(c.getNombre(), f.getNombre());
			assertEquals(c.getTelefono(), f.getTelefono());
		} 
	}
}
