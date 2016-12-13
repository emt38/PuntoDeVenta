package principal;

import java.awt.EventQueue;
import java.sql.DriverManager;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import vistas.MasterFrame;

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
	}
}
