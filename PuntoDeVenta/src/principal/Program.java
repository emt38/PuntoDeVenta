package principal;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import modelos.IEntidadDatos;
import modelos.Pais;
import modelos.Producto;
import modelos.Provincia;
import vistas.MasterFrame;

public class Program {
	public static String getDBServer() {
		return "MYSQL5015.HostBuddy.com";
	}
	
	public static String getDBUser() {
		return "a137dd_posdb";
	}
	
	public static String getDBPassword() {
		return "Curne2016";
	}
	
	public static void main(String[] args) {
		try {
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
		
		
	}
}
