package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import principal.ConnectionDB;

public class MasterFrame {

	private JFrame frame;
	private static ConnectionDB conn;

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//
//	}
	private static void connectToDataBase(){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://MYSQL5015.HostBuddy.com/";
		String db = "db_a137dd_posdb";
		
		conn = new ConnectionDB(driver, db, url);
		conn.connect();
	}
	private static void openReportFrame()
	{
		EventQueue.invokeLater(new Runnable(){
		public void run(){
			try{
				ReportesFrame frame =  new ReportesFrame(conn.getConn());
				frame.setVisible(true);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		});
	}
	/**
	 * Create the application.
	 */
	public MasterFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteFrame temp = new ClienteFrame();
				temp.setVisible(true);
			}
		});
		btnClientes.setBounds(50, 89, 89, 23);
		frame.getContentPane().add(btnClientes);
		
		JButton btnProveedores = new JButton("Proveedores");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuplidoresFrame temp = new SuplidoresFrame();
				temp.setVisible(true);
			}
		});
		btnProveedores.setBounds(269, 89, 89, 23);
		frame.getContentPane().add(btnProveedores);
		
		
		
		JButton btnTiendas = new JButton("Tiendas");
		btnTiendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnTiendas.setBounds(269, 120, 85,23 );
		frame.getContentPane().add(btnTiendas);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoUsuariosFrame temp = new ListadoUsuariosFrame();
				temp.setVisible(true);
			}
		});
		btnUsuarios.setBounds(157, 120, 89, 23);
		frame.getContentPane().add(btnUsuarios);
		
		JButton btnCiudades = new JButton("Ciudades");
		btnCiudades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CiudadesFrame temp = new CiudadesFrame();
				temp.setVisible(true);
			}
		});
		btnCiudades.setBounds(50, 120, 85,23 );
		frame.getContentPane().add(btnCiudades);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connectToDataBase();
				openReportFrame();
			}
		});
		btnReportes.setBounds(50, 89, 89, 123);
		frame.getContentPane().add(btnReportes);
	}
		
	}

