package pruebas;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import org.junit.Test;

import modelos.Ciudad;
import modelos.Tienda;
import modelos.TipoUsuario;
import modelos.Usuario;
import principal.RobotFingers;
import principal.Utilidades;
import vistas.LoginDialog;
import vistas.MantenimientoTiendas;
import vistas.ReestablecerClaveDialog;
import vistas.TiendasFrame;
import principal.Program;

public class MantenimientoTiendasTest {
	
	private int velocity = 4;
	private int lag = 500;
	
	@Test
	public void testAgregar() {
		MantenimientoTiendas form = new MantenimientoTiendas();

		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(form, componentes);
		Component btnFinalizar = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnFinalizar") );
		JTextField txtNombre = (JTextField)Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtNombre"));
		Component txtSlogan = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtSlogan"));
		Component txtDireccion = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtDireccion"));
		Component cbCiudad = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbCiudad"));
		Component cbPais = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbPais"));
		Component cbProvincia = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbProvincia"));
//		Component btnLimpiarCampos = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnLimpiarCampos"));
		
		form.setVisible(true);

		form.agregarTienda();
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentCenterAnimated(txtNombre, velocity);
		rob.leftClickComponentCenter(txtNombre);
		rob.delay(lag);
		rob.writeString("NovaMegaloEmpresa");
		rob.delay(lag);
		
		rob.moveToComponentCenterAnimated(txtSlogan, velocity);
		rob.leftClickComponentCenter(txtSlogan);
		rob.delay(lag);
		rob.writeString("Vendemos Vainas");
		rob.delay(lag);
		
		rob.moveToComponentCenterAnimated(txtDireccion, velocity);
		rob.leftClickComponentCenter(txtDireccion);
		rob.delay(lag);
		rob.writeString("En algun ventorillo de Narnia");
		rob.delay(lag);
		
		Point rel;
		
		rob.moveToComponentCenterAnimated(cbPais, velocity);
		rob.leftClickComponentCenter(cbPais);
		rel = MouseInfo.getPointerInfo().getLocation();
		rob.delay(lag);
		rob.leftClick(rel.x, rel.y + 40);
		rob.delay(lag);
		
		rob.moveToComponentCenterAnimated(cbProvincia, velocity);
		rob.leftClickComponentCenter(cbProvincia);
		rel = MouseInfo.getPointerInfo().getLocation();
		rob.delay(lag);
		rob.leftClick(rel.x, rel.y + 15);
		rob.delay(lag);
		
		rob.moveToComponentCenterAnimated(cbCiudad, velocity);
		rob.leftClickComponentCenter(cbCiudad);
		rel = MouseInfo.getPointerInfo().getLocation();
		rob.delay(lag);
		rob.leftClick(rel.x, rel.y + 15);
		rob.delay(lag);
		
		rob.moveToComponentAnimated(btnFinalizar, velocity);
		rob.leftClickComponent(btnFinalizar);
		rob.delay(lag * 2);
		rob.writeString("\n");
		rob.delay(lag * 4);
		
		List<Tienda> comp = new Tienda().listar("WHERE nombre='NovaMegaloEmpresa'");
		
		assertFalse(comp.size() <= 0);
	}
	
	@Test
	public void testEditar() {
		
		Tienda eval = null;
		
		try {
			List<Tienda> temp = new Tienda().listar("WHERE nombre='Empresa Ink.' OR nombre='NovaMegaloEmpresa'");
			
			if(temp.size() < 1) {
				Tienda model = new Tienda(100, "Empresa Ink.", "Drieccion", "Slogan", new Ciudad().listar("LIMIT 0, 1").get(0) );
				model.insertar();
				eval = model;
			} else {
				eval = temp.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		MantenimientoTiendas form = new MantenimientoTiendas();

		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(form, componentes);
		Component btnFinalizar = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnFinalizar") );
		JTextField txtNombre = (JTextField)Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtNombre"));
//		Component txtSlogan = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtSlogan"));
//		Component txtDireccion = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtDireccion"));
//		Component cbCiudad = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbCiudad"));
//		Component cbPais = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbPais"));
//		Component cbProvincia = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbProvincia"));
//		Component btnLimpiarCampos = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnLimpiarCampos"));
		
		form.setVisible(true);

		form.editarTienda(eval);
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentCenterAnimated(txtNombre, velocity);
		rob.leftClickComponentCenter(txtNombre);
		rob.delay(lag);
		rob.writeString("Cambio");
		rob.delay(lag);
		rob.moveToComponentAnimated(btnFinalizar, velocity);
		rob.leftClickComponent(btnFinalizar);
		rob.delay(lag * 2);
		rob.writeString("\n");
		rob.delay(lag * 2);
		
		Tienda comp = new Tienda().buscar(eval.getId());
		
		assertFalse(comp == null);
		assertTrue(comp.getNombre().equals(txtNombre.getText()));
	}
	
	@Test
	public void testVisualizar() {
		
		Tienda eval = null;
		
		try {
			List<Tienda> temp = new Tienda().listar("WHERE nombre='Empresa Ink.' OR nombre='NovaMegaloEmpresa'");
			
			if(temp.size() < 1) {
				Tienda model = new Tienda(100, "Empresa Ink.", "Drieccion", "Slogan", new Ciudad().listar("LIMIT 0, 1").get(0) );
				model.insertar();
				eval = model;
			} else {
				eval = temp.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		MantenimientoTiendas form = new MantenimientoTiendas();

		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(form, componentes);
		Component btnFinalizar = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnFinalizar") );
//		JTextField txtNombre = (JTextField)Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtNombre"));
//		Component txtSlogan = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtSlogan"));
//		Component txtDireccion = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtDireccion"));
//		Component cbCiudad = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbCiudad"));
//		Component cbPais = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbPais"));
//		Component cbProvincia = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbProvincia"));
//		Component btnLimpiarCampos = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnLimpiarCampos"));
		
		form.setVisible(true);

		form.visualizarTienda(eval);
		
		RobotFingers rob = new RobotFingers();
		
		rob.delay(lag * 4);
		rob.moveToComponentAnimated(btnFinalizar, velocity);
		rob.leftClickComponent(btnFinalizar);
		rob.delay(lag * 2);
		
		assertFalse(form.isVisible());
	}
	
	@Test
	public void testEliminar() {
		
		Tienda eval = null;
		
		try {
			List<Tienda> temp = new Tienda().listar("WHERE nombre LIKE '%cambios%'");
			
			if(temp.size() < 1) {
				Tienda model = new Tienda(100, "Empresa Ink.", "Drieccion", "Slogan", new Ciudad().listar("LIMIT 0, 1").get(0) );
				model.insertar();
				eval = model;
			} else {
				eval = temp.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		MantenimientoTiendas form = new MantenimientoTiendas();

		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(form, componentes);
		Component btnFinalizar = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnFinalizar") );
//		JTextField txtNombre = (JTextField)Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtNombre"));
//		Component txtSlogan = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtSlogan"));
//		Component txtDireccion = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtDireccion"));
//		Component cbCiudad = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbCiudad"));
//		Component cbPais = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbPais"));
//		Component cbProvincia = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("cbProvincia"));
//		Component btnLimpiarCampos = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnLimpiarCampos"));
		
		form.setVisible(true);

		form.eliminarTienda(eval);
		
		RobotFingers rob = new RobotFingers();
		
		rob.delay(lag);
		rob.moveToComponentAnimated(btnFinalizar, velocity);
		rob.leftClickComponent(btnFinalizar);
		rob.delay(lag * 2);
		rob.writeString("\n");
		rob.delay(lag * 2);
		rob.writeString("\n");
		rob.delay(lag * 4);
		
		assertFalse(form.isVisible());
	}
}
