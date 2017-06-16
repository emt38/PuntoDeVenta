package pruebas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelos.Pais;
import principal.RobotFingers;
import vistas.CiudadesFrame;
import vistas.ClienteFrame;
import vistas.ProductoFrame;
import vistas.SuplidoresFrame;

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

public class ProductoFrameTest  {
	private int velocity = 4;
	private int lag = 1000;
	@Test
	public void InsertTest() {
		 RobotFingers rob = new RobotFingers();
		ProductoFrame frameProducto = new ProductoFrame();	
		
		frameProducto.setLocationRelativeTo(null);
		frameProducto.setResizable(false);
		frameProducto.setVisible(true);
		
        rob.moveToComponentAnimated(frameProducto.btnNuevo, velocity);
		rob.leftClickComponent(frameProducto.btnNuevo);
		rob.delay(lag * 2);

		
		rob.moveToComponentAnimated(frameProducto.txtDescripcion, velocity);
		rob.leftClickComponent((frameProducto.txtDescripcion));
		rob.delay(lag);
		rob.writeString("Descripcion de producto de prueba");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameProducto.txtPrecio, velocity);
		rob.leftClickComponentCenter(frameProducto.txtPrecio);
		rob.delay(lag);
		rob.writeString("123");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameProducto.txtCosto, velocity);
		rob.leftClickComponentCenter(frameProducto.txtCosto);
		rob.delay(lag);
		rob.writeString("123");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(frameProducto.txtTasaImpuesto, velocity);
		rob.leftClickComponentCenter(frameProducto.txtTasaImpuesto);
		rob.delay(lag);
		rob.writeString("123");
		rob.delay(lag);
		rob.moveToComponentAnimated(frameProducto.txtStock, velocity);
		rob.leftClickComponentCenter(frameProducto.txtStock);
		rob.delay(lag);
		rob.writeString("1236");
		rob.delay(lag);
		
		
		rob.moveToComponentAnimated(frameProducto.btnGuardar, velocity);
		rob.leftClickComponentCenter(frameProducto.btnGuardar);
		rob.delay(lag);
		
	}

}
