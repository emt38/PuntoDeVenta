package pruebas;

import modelos.Suplidor;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SuplidorTest {

	@Test
	public void testInsertar() {
		Suplidor temp = new Suplidor();
		temp.setNombre("Nombre de prueba");
		temp.setApellido("Apellido de prueba");
		temp.setDireccion("Direccion de prueba");
		temp.setTelefono("Telefono de prueba");
		temp.setCelular("Celular de prueba");
		temp.setSexo("Masculino");
		temp.setEmpresa("Empresa de pruebas");
		temp.setRnc("RNC de prueba");
		assertTrue(temp.insertar());
	}
	
	@Test
	public void testBuscar(){
		List<Suplidor> temp = new Suplidor().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size()>0){
			Suplidor f = temp.get(0);
			Suplidor c = new Suplidor().buscar(f.getId());
			assertEquals(c.getId(), f.getId());
			assertEquals(c.getNombre(), f.getNombre());
			assertEquals(c.getTelefono(), f.getTelefono());
		}
	}
	
	@Test
	public void testActualizar(){
		List<Suplidor> temp = new Suplidor().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Suplidor s = temp.get(0);
			String str = s.getNombre();
			s.setNombre("Prueba de Actualizacion");
			assertTrue("No se pudo ejecutar la actualización con la base de datos", s.actualizar());
			s = s.buscar(s.getId());
			assertEquals("El nombre no fue actualizado en la base de datos", s.getNombre(), "Prueba de Actualizacion");
			s.setNombre(str);
			assertTrue(s.actualizar());
		}
	}
	
	@Test
	public void testEliminar(){
		Suplidor sup = new Suplidor(0, "MM12", "Empresa Ink.");
		sup.setNombre("Nombre de prueba");
		sup.setApellido("Apellido de prueba");
		sup.setDireccion("Direccion de prueba");
		sup.setTelefono("Telefono de prueba");
		sup.setCelular("Celular de prueba");
		sup.setSexo("Masculino");
		
		boolean ins = sup.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Suplidor> temp = new Suplidor().listar("ORDER BY idsuplidor DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
			if(temp.size() > 0) {
				Suplidor s = temp.get(0);
				assertTrue(s.eliminar());
			}
		}
	}
	
	@Test
	public void testListar(){
		Suplidor sup = new Suplidor(0, "MM12", "Empressa Ink.");
		sup.setNombre("Nombre de prueba");
		sup.setApellido("Apellido de prueba");
		sup.setDireccion("Direccion de prueba");
		sup.setTelefono("Telefono de prueba");
		sup.setCelular("Celular de prueba");
		sup.setSexo("Masculino");
		
		boolean ins = sup.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Suplidor> temp = new Suplidor().listar("ORDER BY idsuplidor DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
			if(temp.size() > 0) {
				Suplidor s = temp.get(0);
				assertTrue(s.eliminar());
			}
		}
	}

}
