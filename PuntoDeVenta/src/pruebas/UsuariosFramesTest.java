package pruebas;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.junit.Test;

import modelos.Tienda;
import modelos.TipoUsuario;
import modelos.Usuario;
import principal.RobotFingers;
import principal.Utilidades;
import vistas.ListadoUsuariosFrame;
import vistas.LoginDialog;
import vistas.ReestablecerClaveDialog;
import vistas.RegistroUsuariosFrame;

public class UsuariosFramesTest {
	
	private int velocity = 4;
	private int lag = 500;
	
	@Test
	public void testEliminarUsuario() {
		try {
			List<Usuario> temp = new Usuario().listar("WHERE nombreusuario='UsPruebaLogin'");
			
			if(temp.size() < 1) {
				Usuario model = new Usuario();
				model.setNombreUsuario("UsPruebaLogin");
				model.setHashClave("ClaveDePrueba123CHECK");
				model.setSalesClave(Utilidades.generarSales());
				model.setHashClave(Utilidades.generarHash(model.getHashClave(), model.getSalesClave()));
				model.setTipo(TipoUsuario.Cajero);
				model.setTienda(new Tienda().listar().get(0));
				model.setNombreCompleto("UsuarioTest");
				model.insertar();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		ListadoUsuariosFrame consulta = new ListadoUsuariosFrame();	
		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(consulta, componentes);
		
		JTable tblUsuarios = (JTable)Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("tblUsuarios") );
		Component btnEliminar = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnEliminar") );
		
		
		consulta.setVisible(true);
		
		List<Usuario> users = consulta.getUsuarios();
		
		int x = 0;
		
		for(x = 0; x < users.size(); x++) {
			if(users.get(x).getNombreUsuario().equals("UsPruebaLogin"))
				break;
		}
		
		RobotFingers rob = new RobotFingers();
		Point targetRow = tblUsuarios.getLocationOnScreen();
		targetRow.setLocation(targetRow.x + tblUsuarios.getWidth() / 2, targetRow.y + (tblUsuarios.getRowHeight()) * (x+1) - tblUsuarios.getRowHeight() / 2);
		rob.moveToAnimated(targetRow, velocity);
		rob.leftClick(targetRow);
		rob.delay(lag);
		Usuario selected = consulta.getUsuarios().get(tblUsuarios.getSelectedRow());
		rob.delay(lag);
		rob.moveToComponentAnimated(btnEliminar, velocity);
		rob.delay(lag);
		rob.leftClickComponent(btnEliminar);
		rob.delay(lag);
		Point dialogPos = MouseInfo.getPointerInfo().getLocation();
		dialogPos.setLocation(dialogPos.x - 50, dialogPos.y + 180);
		rob.moveToAnimated(dialogPos, velocity);
		rob.leftClick(dialogPos);
		rob.delay(lag * 4);
 		Usuario comprobacion = new Usuario().buscar(selected.getId());
 		consulta.setVisible(false);
 		assertTrue(comprobacion == null);
	}
	
	@Test
	public void testReestablecerClave() {
		try {
			List<Usuario> temp = new Usuario().listar("WHERE nombreusuario='UsPruebaLogin'");
			
			if(temp.size() < 1) {
				Usuario model = new Usuario();
				model.setNombreUsuario("UsPruebaLogin");
				model.setHashClave("ClaveDePrueba123CHECK");
				model.setSalesClave(Utilidades.generarSales());
				model.setHashClave(Utilidades.generarHash(model.getHashClave(), model.getSalesClave()));
				model.setTipo(TipoUsuario.Cajero);
				model.setTienda(new Tienda().listar().get(0));
				model.setNombreCompleto("UsuarioTest");
				model.insertar();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		ListadoUsuariosFrame consulta = new ListadoUsuariosFrame();	
		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(consulta, componentes);
		
		JTable tblUsuarios = (JTable)Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("tblUsuarios") );
		Component btnReestablecerContrasea = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnReestablecerContrasea") );
		
		ReestablecerClaveDialog re = new ReestablecerClaveDialog();
		re.setVisible(true);
		List<Component> cre = new ArrayList<>();
		Utilidades.getAllComponents(re, cre);
		
		// Component reBtnCancelar = Utilidades.buscarElemento(cre, c -> c.getName() != null && c.getName().equals("btnCancelar") );
		Component reBtnReestablecer = Utilidades.buscarElemento(cre, c -> c.getName() != null && c.getName().equals("btnReestablecer") );
		Component rePfClave = Utilidades.buscarElemento(cre, c -> c.getName() != null && c.getName().equals("pfClave") );
		Component rePfConfirmacion = Utilidades.buscarElemento(cre, c -> c.getName() != null && c.getName().equals("pfConfirmacion") );
		
		// pfClave
		// pfConfirmacion
		
		re.setVisible(true);
		consulta.setVisible(true);
		
		
		RobotFingers rob = new RobotFingers();
		Point firstRow = tblUsuarios.getLocationOnScreen();
		firstRow.setLocation(firstRow.x + tblUsuarios.getWidth() / 2, firstRow.y + tblUsuarios.getRowHeight() / 2);
		rob.moveToAnimated(firstRow, velocity);
		
		rob.leftClick(firstRow);
		rob.moveToComponentAnimated(btnReestablecerContrasea, velocity);
		Usuario respaldo = consulta.getUsuarios().get(tblUsuarios.getSelectedRow());
		String hash = respaldo.getHashClave();
		String sales = respaldo.getSalesClave();
		rob.leftClickComponent(btnReestablecerContrasea);
		
		rob.delay(lag);
		
		rob.moveToComponentAnimated(rePfClave, velocity);
		rob.leftClickComponent(rePfClave);
		rob.writeString("123456");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(rePfConfirmacion, velocity);
		rob.leftClickComponent(rePfConfirmacion);
		rob.writeString("123456");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(reBtnReestablecer, velocity);
		rob.leftClickComponent(reBtnReestablecer);
		rob.delay(lag);
		
		Point conf = MouseInfo.getPointerInfo().getLocation();
		conf.setLocation(conf.x - 50, conf.y - 20);
		rob.moveToAnimated(conf, velocity);
		rob.delay(lag);
		rob.leftClick(conf);
		
		rob.delay(lag * 4);
		
		Usuario comprobacion = new Usuario().buscar(respaldo.getId());
		
		assertFalse(hash.equals(comprobacion.getHashClave()));
		
		respaldo.setHashClave(hash);
		respaldo.setSalesClave(sales);
		respaldo.actualizar();
		consulta.setVisible(false);
	}
	
	@Test
	public void testAgregarUsuario() {
		RegistroUsuariosFrame mantenimiento = new RegistroUsuariosFrame();
		mantenimiento.setVisible(true);
		List<Component> components = new ArrayList<>();
		Utilidades.getAllComponents(mantenimiento, components);
		
		Component txtNombreCompleto = Utilidades.buscarElemento(components, c -> c.getName() != null && c.getName().equals("txtNombreCompleto") );
		Component txtClave = Utilidades.buscarElemento(components, c -> c.getName() != null && c.getName().equals("txtClave") );
		Component txtConfirmacion = Utilidades.buscarElemento(components, c -> c.getName() != null && c.getName().equals("txtConfirmacion") );
		Component btnAgregar = Utilidades.buscarElemento(components, c -> c.getName() != null && c.getName().equals("btnAgregar") );
		//Component cbTipoUsuario = Utilidades.buscarElemento(components, c -> c.getName() != null && c.getName().equals("cbTipoUsuario") );
		Component txtNombreUsuario = Utilidades.buscarElemento(components, c -> c.getName() != null && c.getName().equals("txtNombreUsuario") );
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentAnimated(txtNombreCompleto, velocity);
		rob.leftClickComponent(txtNombreCompleto);
		rob.delay(lag);
		rob.writeString("Hola, mi nombre es dagoberto");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(txtNombreUsuario, velocity);
		rob.leftClickComponent(txtNombreUsuario);
		rob.delay(lag);
		rob.writeString("DagoRankiadoPrueba");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(txtClave, velocity);
		rob.leftClickComponent(txtClave);
		rob.delay(lag);
		rob.writeString("A123456789");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(txtConfirmacion, velocity);
		rob.leftClickComponent(txtConfirmacion);
		rob.delay(lag);
		rob.writeString("A123456789");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(btnAgregar, velocity);
		rob.leftClickComponent(btnAgregar);
		rob.delay(lag);
		rob.writeString("\n");
		rob.delay(lag * 2);
		rob.writeString("\n");
		rob.delay(lag * 4);
		
		assertFalse(mantenimiento.isVisible());
		
		List<Usuario> comp = new Usuario().listar(String.format("WHERE nombreusuario='%s'", "DagoRankiadoPrueba"));
		assertTrue(comp.size() > 0);
		
		Usuario u = comp.get(0);
		u.eliminar();
	}
	
	@Test
	public void testClaveIgual() {
		Usuario model = new Usuario();
		model.setNombreUsuario("UsPruebaLogin");
		model.setHashClave("123456");
		model.setSalesClave(Utilidades.generarSales());
		model.setHashClave(Utilidades.generarHash(model.getHashClave(), model.getSalesClave()));
		model.setTipo(TipoUsuario.Cajero);
		model.setTienda(new Tienda().listar().get(0));
		model.setNombreCompleto("UsuarioTest");
		
		ReestablecerClaveDialog re = new ReestablecerClaveDialog();
		re.setVisible(true);
		List<Component> cre = new ArrayList<>();
		Utilidades.getAllComponents(re, cre);
		
		// Component reBtnCancelar = Utilidades.buscarElemento(cre, c -> c.getName() != null && c.getName().equals("btnCancelar") );
		Component reBtnReestablecer = Utilidades.buscarElemento(cre, c -> c.getName() != null && c.getName().equals("btnReestablecer") );
		Component rePfClave = Utilidades.buscarElemento(cre, c -> c.getName() != null && c.getName().equals("pfClave") );
		Component rePfConfirmacion = Utilidades.buscarElemento(cre, c -> c.getName() != null && c.getName().equals("pfConfirmacion") );
		re.reestablecerClave(model);
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentAnimated(rePfClave, velocity);
		rob.leftClickComponent(rePfClave);
		rob.writeString("123456");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(rePfConfirmacion, velocity);
		rob.leftClickComponent(rePfConfirmacion);
		rob.writeString("123456");
		rob.delay(lag);
		
		rob.moveToComponentAnimated(reBtnReestablecer, velocity);
		rob.leftClickComponent(reBtnReestablecer);
		rob.delay(lag * 4);
	}
}
