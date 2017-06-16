package pruebas;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelos.Tienda;
import modelos.TipoUsuario;
import modelos.Usuario;
import principal.RobotFingers;
import principal.Utilidades;
import vistas.LoginDialog;
import principal.Program;

public class LoginDialogTest {
	
	private int velocity = 4;
	private int lag = 500;
	
	@Test
	public void testLogin() {

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
		
		LoginDialog login = new LoginDialog();	
		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(login, componentes);
		Component bLogin = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnIniciarSesion") );
		Component txtUsername = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtNombreUsuario"));
		Component txtPassword = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtClave"));
		
		login.setVisible(true);
		
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentAnimated(txtUsername, velocity);
		rob.leftClickComponent(txtUsername);
		rob.delay(lag);
		rob.writeString("UsPruebaLogin");
		rob.delay(lag);
		rob.moveToComponentAnimated(txtPassword, velocity);
		rob.leftClickComponent(txtPassword);
		rob.delay(lag);
		rob.writeString("ClaveDePrueba123CHECK");
		rob.delay(lag);
		rob.moveToComponentAnimated(bLogin, velocity);
		rob.leftClickComponent(bLogin);
		rob.delay(lag * 2);
		
		Usuario user = login.getLoggedUser();
		
		assertFalse(user == null);
		assertTrue(user.getNombreUsuario().equals("UsPruebaLogin"));
	}
	
	@Test
	public void testCancelar() {
		LoginDialog login = new LoginDialog();
		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(login, componentes);
		Component btnSalir = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnSalir") );
		
		login.setVisible(true);
		
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentAnimated(btnSalir, velocity);
		rob.leftClickComponent(btnSalir);
		rob.delay(lag * 2);
		
		assertFalse(login.isVisible());
	}
	
	@Test
	public void testAccesoErroneo() {
		LoginDialog login = new LoginDialog();	
		List<Component> componentes = new ArrayList<>();
		Utilidades.getAllComponents(login, componentes);
		Component bLogin = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("btnIniciarSesion") );
		Component txtUsername = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtNombreUsuario"));
		Component txtPassword = Utilidades.buscarElemento(componentes, c -> c.getName() != null && c.getName().equals("txtClave"));
		
		login.setVisible(true);
		
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentAnimated(txtUsername, velocity);
		rob.leftClickComponent(txtUsername);
		rob.delay(lag);
		rob.writeString("erronio");
		rob.delay(lag);
		rob.moveToComponentAnimated(txtPassword, velocity);
		rob.leftClickComponent(txtPassword);
		rob.delay(lag);
		rob.writeString("maserronio");
		rob.delay(lag);
		rob.moveToComponentAnimated(bLogin, velocity);
		rob.leftClickComponent(bLogin);
		rob.delay(lag * 2);
		rob.writeString("\n");
		rob.delay(lag);
		login.setVisible(false);
		Usuario user = login.getLoggedUser();
		
		assertTrue(user == null);
	}
}
