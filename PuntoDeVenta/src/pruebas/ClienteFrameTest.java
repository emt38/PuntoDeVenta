package pruebas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelos.Pais;
import principal.RobotFingers;
import vistas.CiudadesFrame;
import vistas.ClienteFrame;

public class ClienteFrameTest {
	private int velocity = 4;
	private int lag = 1000;
	@Test
	public void InsertTest() {
		
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
	public void UpdateTest() {
		ClienteFrame frameCliente = new ClienteFrame();	
		frameCliente.setLocationRelativeTo(null);
		frameCliente.setResizable(false);
		frameCliente.setVisible(true);
	
		
        RobotFingers rob = new RobotFingers();
        
       
        rob.moveToComponentAnimated(frameCliente.btnModificar, velocity);
		rob.leftClickComponent(frameCliente.btnModificar);
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

}
