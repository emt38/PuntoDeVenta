package principal;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.util.List;

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
