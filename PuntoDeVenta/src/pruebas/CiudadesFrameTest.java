package pruebas;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelos.Pais;
import principal.RobotFingers;
import vistas.CiudadesFrame;

public class CiudadesFrameTest {

	@Test
	public void test() {
		
		RobotFingers miRobot = new RobotFingers();
		CiudadesFrame frame = new CiudadesFrame();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		
		miRobot.delay(2000);
		miRobot.moveToComponentAnimated((CiudadesFrame.getBtnNuevo()), 5);
		miRobot.leftClickComponent(CiudadesFrame.getBtnNuevo());
		miRobot.delay(2000);
		miRobot.moveToComponentAnimated((CiudadesFrame.getRBPais()), 5);
		miRobot.leftClickComponent(CiudadesFrame.getRBPais());
		miRobot.delay(2000);
		miRobot.moveToComponentAnimated(CiudadesFrame.getComboBoxPais(), 5);
		miRobot.leftClickComponent(CiudadesFrame.getComboBoxPais());
		CiudadesFrame.getComboBoxPais().setSelectedItem("");
		miRobot.writeString("Dinamarca");
	 	miRobot.delay(2000);
		miRobot.moveToComponentAnimated(CiudadesFrame.getBtnGuardar(), 5);
		miRobot.leftClickComponent(CiudadesFrame.getBtnGuardar());
		miRobot.delay(2000);
		miRobot.moveToAnimated(650,385, 5);
		miRobot.leftClick(650,385);
		miRobot.delay(2000);
		
		miRobot.leftClick(650,385);
		miRobot.delay(1000);
		miRobot.writeString("\n");
		
		List<Pais> temp = new Pais().listar("LIMIT 0,1");
		assertNotEquals("No existen elementos para realizar la prueba", temp.size(), 0);
		if(temp.size() > 0) {
			Pais f = temp.get(0);
			Pais c = new Pais().buscar(f.getId());
			assertEquals(c.getId(), f.getId());
			assertEquals(c.getNombre(), f.getNombre());
			
			for (Pais temp1: temp)
			{
			System.out.println(temp1.listar());
			
			}
			
		}
		
		
		
	}

}
