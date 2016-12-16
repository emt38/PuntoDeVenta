package principal;

import java.awt.EventQueue;
import java.sql.DriverManager;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import vistas.MasterFrame;
import modelos.*;

public class Program {
	public static String getDBServer() {
		return "jdbc:mysql://MYSQL5015.HostBuddy.com/db_a137dd_posdb";
	}
	
	public static String getDBUser() {
		return "a137dd_posdb";
	}
	
	public static String getDBPassword() {
		return "Curne2016";
	}
	
	public static Usuario getLoggedUser() {
		return new Usuario(loggedUser.getId(), loggedUser.getNombreUsuario(), "", "", loggedUser.getNombreCompleto(), loggedUser.getTipo(), loggedUser.getTienda());
	}
	
	public static boolean setLoggedUser(Usuario temp) {
		if(temp.iniciarSesion()) {
			loggedUser = temp;
			return true;
		} else {
			return false;
		}
	}
	
	private static Usuario loggedUser;
	
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
		
		loggedUser = new Usuario(1,"sysadmin","","","Usuario de Prueba", TipoUsuario.Gerente, new Tienda(1, "asd1", "asd1", "asd1", "San Francisco"));
		
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
		
		String sales = Utilidades.generarSales();
		String clave = "Curne2016";
		String hash = Utilidades.generarHash(clave, sales);
		
		if(Utilidades.verificarHash("Curne2016", hash, sales))
			System.out.println("Se inició sesión");
		
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
