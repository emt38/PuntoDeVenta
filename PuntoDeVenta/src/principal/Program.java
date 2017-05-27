package principal;

import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import vistas.LoginDialog;
import vistas.MasterFrame;
import modelos.*;

public class Program {
	public static String getDBServer() {
		return "jdbc:mysql://127.0.0.1/posdb";
	}
	
	public static String getDBUser() {
		return "root";
	}
	
	public static String getDBPassword() {
		return "";
	}
	
	public static Usuario getLoggedUser() {
		if(loggedUser == null)
			loggedUser = new Usuario().listar().get(0);
		return new Usuario(loggedUser.getId(), loggedUser.getNombreUsuario(), "", "", loggedUser.getNombreCompleto(), loggedUser.getTipo(), loggedUser.getTienda());
	}
	
	private static void setLoggedUser(Usuario temp) {
		loggedUser = temp;
	}
	
	private static Usuario loggedUser;
	public static RobotFingers robot;
	
	public static void main(String[] args) {	
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		robot = new RobotFingers();
		LoginDialog login = new LoginDialog();
		login.setModalityType(ModalityType.APPLICATION_MODAL);
		
		login.setVisible(true);
		
		Usuario user = login.getLoggedUser();
		
		if(user == null)
			return;
		
		setLoggedUser(user);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterFrame window = new MasterFrame();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// Ejemplo de Clientes
		
//		Cliente temp = new Cliente();
//		temp.setNombre("King");
//		temp.setApellido("Enum");
//		temp.setCelular("809-852-8745");
//		temp.setSexo("Masculino");
//		//temp.insertar();
//		
//		Cliente mod = temp.buscar(2);
		//mod.setNombre("Lambda");
		//mod.actualizar();
//		Cliente del = temp.buscar(5);
//		del.eliminar();
//		
//		List<Cliente> clientes = new Cliente().listar();
//		
//		for(Cliente c : clientes) {
//			System.out.println(c.getNombre() + " " + c.getApellido());
//		}
		
		// Ejemplo de Ubicaciones
		
//		List<Pais> paises = new Pais().listar();
//		
//		for(Pais p : paises) {
//			System.out.println(p.getNombre());
//		}
//		
//		System.out.println();
//		
//		List<Provincia> provincias = new Provincia().listar();
//		
//		for(Provincia p : provincias) {
//			System.out.println(p.getNombre());
//		}
//		
//		System.out.println();
//		
//		Ciudad temp = new Ciudad(0, provincias.get(0), "San Francisco de Macorís");
//		//temp.insertar();
//		
//		List<Ciudad> ciudades = new Ciudad().listar();
//		
//		for(Ciudad c : ciudades) {
//			System.out.println(c.getNombre());
//		}
	}
}
