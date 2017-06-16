package pruebas;

import modelos.Cliente;
import principal.RobotFingers;
import principal.Utilidades;
import vistas.ClienteFrame;
import vistas.LoginDialog;

import static org.junit.Assert.*;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.junit.Test;

public class ClienteTest {

	private int velocity = 4;
	private int lag = 1000;
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
		
		ClienteFrame frameCliente = new ClienteFrame();	
		frameCliente.setLocationRelativeTo(null);
		frameCliente.setResizable(false);
		frameCliente.setVisible(true);
	
		
        RobotFingers rob = new RobotFingers();
        
       
        rob.moveToComponentAnimated(frameCliente.btnNuevo, velocity);
		rob.leftClickComponent(frameCliente.btnNuevo);
		rob.delay(lag * 2);
		
		rob.moveToComponentAnimated(frameCliente.txtNombre, velocity);
		rob.leftClickComponent((frameCliente.txtNombre));
		rob.delay(lag);
		rob.writeString("Nombre de prueba");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtApellido, velocity);
		rob.leftClickComponent(frameCliente.txtApellido);
		rob.delay(lag);
		rob.writeString("Apellido de prueba");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtDireccion, velocity);
		rob.leftClickComponent(frameCliente.txtDireccion);
		rob.delay(lag);
		rob.writeString("Yo vivo lejos");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtCelular, velocity);
		rob.leftClickComponent(frameCliente.txtCelular);
		rob.delay(lag);
		rob.writeString("809-111-1111");
		rob.delay(lag);
		rob.moveToComponentAnimated(frameCliente.txtIdentificacion, velocity);
		rob.leftClickComponent(frameCliente.txtIdentificacion);
		rob.delay(lag);
		rob.writeString("402-2463259-2");
		rob.delay(lag);
		rob.moveToComponentAnimated(frameCliente.cmbSexo, velocity);
		rob.leftClickComponent(frameCliente.cmbSexo);
		rob.delay(lag);
		rob.writeString("Masculino");
		rob.delay(lag);
		rob.moveToAnimated(650,385, 5);
		rob.leftClick(650,385);
		
		rob.moveToComponentAnimated(frameCliente.btnGuardar, velocity);
		rob.leftClickComponent(frameCliente.btnGuardar);
		rob.delay(lag);
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
	
	@Test
	public void testActualizar() {
		List<Cliente> temp = new Cliente().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Cliente c = temp.get(0);
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
		Cliente cl = new Cliente(0, new Date(), 1f);
		cl.setNombre("Nombre de prueba");
		cl.setApellido("Apellido de prueba");
		cl.setCelular("809-111-1111");
		cl.setDireccion("Yo vivo lejos");
		cl.setIdentificacion("402-2463259-2");
		cl.setSexo("Masculino");
		boolean ins = cl.insertar();
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Cliente> temp = new Cliente().listar("ORDER BY idcliente DESC LIMIT 0, 1");
			assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
			if(temp.size() > 0) {
				Cliente c = temp.get(0);
				assertTrue(c.eliminar());
			}
		}
	}
	
	@Test
	public void testListar() {
		Cliente cl = new Cliente(0, new Date(), 1f);
		cl.setNombre("Nombre de prueba");
		cl.setApellido("Apellido de prueba");
		cl.setCelular("809-111-1111");
		cl.setDireccion("Yo vivo lejos");
		cl.setIdentificacion("402-2463259-2");
		cl.setSexo("Masculino");
		boolean ins = cl.insertar();
		
		assertTrue("No se pudo insertar sujeto de prueba", ins);
		
		if(ins) {
			List<Cliente> temp = new Cliente().listar("ORDER BY idcliente DESC LIMIT 0, 1");
			assertNotEquals("El listar no trajo devuelta ningún elemento", temp.size(), 0);
			if(temp.size() > 0) {
				Cliente c = temp.get(0);
				c.eliminar();
			}
		}
	}
}
