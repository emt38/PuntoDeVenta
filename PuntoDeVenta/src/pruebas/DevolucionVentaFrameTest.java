package pruebas;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import org.junit.Test;

import modelos.Tienda;
import modelos.TipoUsuario;
import modelos.Usuario;
import modelos.Venta;
import principal.RobotFingers;
import principal.Utilidades;
import vistas.DevolucionVentaFrame;
import vistas.ListadoUsuariosFrame;

public class DevolucionVentaFrameTest {

	private int velocity = 4;
	private int lag = 500;
	
	@Test
	public void testDevolverVenta() {
		Venta temp = null;
		try {
			List<Venta> eval = new Venta().listar("ORDER BY idventa DESC LIMIT 0, 1");
			if(eval.size() == 0) {
				fail("No existen ventas para realizar la prueba");
				return;
			}
			temp = eval.get(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		DevolucionVentaFrame consulta = new DevolucionVentaFrame();	
		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(consulta, componentes);
		
		JTable tblVenta = (JTable)Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("tblVenta") );
		JTable tblDevolucion = (JTable)Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("tblDevolucion") );
		Component txtVenta = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtVenta") );
		Component btnCargar = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnCargar") );
		Component btnDevolver = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnDevolver") );
		Component txtCantidad = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtCantidad") );
		Component btnProcesarDevolucion = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnProcesarDevolucion") );
		Component btnRetornar = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnRetornar") );
		
		consulta.setVisible(true);
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentAnimated(txtVenta, velocity);
		rob.leftClickComponent(txtVenta);
		rob.writeString(String.format("%s", temp.getNoDocumento()));
		rob.delay(lag);
		rob.moveToComponentAnimated(btnCargar, velocity);
		rob.leftClickComponent(btnCargar);
		rob.delay(lag);
		rob.leftClickComponent(btnCargar);
		rob.delay(lag * 2);
		
		Point targetRow = tblVenta.getLocationOnScreen();
		targetRow.setLocation(targetRow.x + tblVenta.getWidth() / 2, targetRow.y + (tblVenta.getRowHeight()) - tblVenta.getRowHeight() / 2);
		rob.moveToAnimated(targetRow, velocity);
		rob.leftClick(targetRow);
		rob.delay(lag);
		rob.moveToComponentAnimated(btnDevolver, velocity);
		rob.leftClickComponent(btnDevolver);
		rob.delay(lag);
		Point targetRow2 = tblDevolucion.getLocationOnScreen();
		targetRow.setLocation(targetRow2.x + tblDevolucion.getWidth() / 2, targetRow2.y + (tblDevolucion.getRowHeight()) - tblDevolucion.getRowHeight() / 2);
		rob.moveToAnimated(targetRow2, velocity);
		rob.leftClick(targetRow2);
		rob.delay(lag);
		rob.moveToComponentAnimated(btnRetornar, velocity);
		rob.leftClickComponent(btnRetornar);
		rob.delay(lag);
		targetRow = tblVenta.getLocationOnScreen();
		targetRow.setLocation(targetRow.x + tblVenta.getWidth() / 2, targetRow.y + (tblVenta.getRowHeight()) - tblVenta.getRowHeight() / 2);
		rob.moveToAnimated(targetRow, velocity);
		rob.leftClick(targetRow);
		rob.delay(lag);
		rob.moveToComponentAnimated(btnDevolver, velocity);
		rob.leftClickComponent(btnDevolver);
		rob.delay(lag);
		rob.moveToComponentAnimated(btnProcesarDevolucion, velocity);
		rob.delay(lag);
		rob.leftClickComponent(btnProcesarDevolucion);

		
		rob.delay(lag * 2);
		rob.writeString("\n");
		rob.delay(lag * 2);
		rob.writeString("\n");
		rob.delay(lag * 4);
		
		assertFalse(consulta.isVisible());
	}

}
