package pruebas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelos.Pais;
import principal.RobotFingers;
import vistas.CiudadesFrame;
import vistas.ClienteFrame;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;

public class ClienteFrameTest  {
	private int velocity = 4;
	private int lag = 1000;
	@Test
	public void InsertTest()throws IOException {
		 RobotFingers rob = new RobotFingers();
		ClienteFrame frameCliente = new ClienteFrame();	
		frameCliente.setLocationRelativeTo(null);
		frameCliente.setResizable(false);
		frameCliente.setVisible(true);
		
        rob.moveToComponentAnimated(frameCliente.btnNuevo, velocity);
		rob.leftClickComponent(frameCliente.btnNuevo);
		rob.delay(lag * 2);

		
		rob.moveToComponentAnimated(frameCliente.txtNombre, velocity);
		rob.leftClickComponent((frameCliente.txtNombre));
		rob.delay(lag);
		rob.writeString("Nombre de prueba");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtApellido, velocity);
		rob.leftClickComponentCenter(frameCliente.txtApellido);
		rob.delay(lag);
		rob.writeString("Apellido de prueba");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtDireccion, velocity);
		rob.leftClickComponentCenter(frameCliente.txtDireccion);
		rob.delay(lag);
		rob.writeString("Yo vivo lejos");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtCelular, velocity);
		rob.leftClickComponentCenter(frameCliente.txtCelular);
		rob.delay(lag);
		rob.writeString("809-111-1111");
		rob.delay(lag);
		rob.moveToComponentAnimated(frameCliente.txtIdentificacion, velocity);
		rob.leftClickComponentCenter(frameCliente.txtIdentificacion);
		rob.delay(lag);
		rob.writeString("402-2463259-2");
		rob.delay(lag);
		rob.moveToComponentAnimated(frameCliente.cmbSexo, velocity);
		rob.leftClickComponentCenter(frameCliente.cmbSexo);
		rob.delay(lag);
		rob.writeString("Masculino");
		rob.delay(lag);
		rob.moveToAnimated(650,385, 5);
		rob.leftClick(650,385);
		
		rob.moveToComponentAnimated(frameCliente.btnGuardar, velocity);
		rob.leftClickComponentCenter(frameCliente.btnGuardar);
		rob.delay(lag);
		rob.writeString("\n");
	}
	/*@Test
	public void UpdateTest() {
		ClienteFrame frameCliente = new ClienteFrame();	
		frameCliente.setLocationRelativeTo(null);
		frameCliente.setResizable(false);
		frameCliente.setVisible(true);
	
		
        RobotFingers rob = new RobotFingers();
        
       
        rob.moveToComponentAnimated(frameCliente.btnModificar, velocity);
		rob.leftClickComponentCenter(frameCliente.btnModificar);
		rob.delay(lag * 2);
		
		rob.moveToComponentAnimated(frameCliente.txtNombre, velocity);
		rob.leftClickComponentCenter((frameCliente.txtNombre));
		rob.delay(lag);
		rob.writeString("Nombre de prueba");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtApellido, velocity);
		rob.leftClickComponentCenter(frameCliente.txtApellido);
		rob.delay(lag);
		rob.writeString("Apellido de prueba");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtDireccion, velocity);
		rob.leftClickComponentCenter(frameCliente.txtDireccion);
		rob.delay(lag);
		rob.writeString("Yo vivo lejos");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameCliente.txtCelular, velocity);
		rob.leftClickComponentCenter(frameCliente.txtCelular);
		rob.delay(lag);
		rob.writeString("809-111-1111");
		rob.delay(lag);
		rob.moveToComponentAnimated(frameCliente.txtIdentificacion, velocity);
		rob.leftClickComponentCenter(frameCliente.txtIdentificacion);
		rob.delay(lag);
		rob.writeString("402-2463259-2");
		rob.delay(lag);
		rob.moveToComponentAnimated(frameCliente.cmbSexo, velocity);
		rob.leftClickComponentCenter(frameCliente.cmbSexo);
		rob.delay(lag);
		rob.writeString("Masculino");
		rob.delay(lag);
		rob.moveToAnimated(650,385, 5);
		rob.leftClick(650,385);
		
		rob.moveToComponentAnimated(frameCliente.btnGuardar, velocity);
		rob.leftClickComponentCenter(frameCliente.btnGuardar);
		rob.delay(lag);
	}*/

}
