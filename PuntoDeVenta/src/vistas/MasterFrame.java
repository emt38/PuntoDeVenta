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
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;

public class MasterFrame {

	private JFrame frmSistemaDePunto;
	private static ConnectionDB conn;

	public JFrame getFrame() {
		return frmSistemaDePunto;
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
		frmSistemaDePunto = new JFrame();
		frmSistemaDePunto.setTitle("Sistema de Punto de Venta (POS)");
		frmSistemaDePunto.getContentPane().setBackground(new Color(220, 220, 220));
		frmSistemaDePunto.setBounds(100, 100, 472, 344);
		frmSistemaDePunto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDePunto.getContentPane().setLayout(null);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteFrame temp = new ClienteFrame();
				temp.setVisible(true);
			}
		});
		btnClientes.setBounds(156, 119, 138, 44);
		frmSistemaDePunto.getContentPane().add(btnClientes);
		
		JButton btnProveedores = new JButton("Proveedores");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuplidoresFrame temp = new SuplidoresFrame();
				temp.setVisible(true);
			}
		});
		btnProveedores.setBounds(156, 232, 138, 44);
		frmSistemaDePunto.getContentPane().add(btnProveedores);
		
		
		
		JButton btnTiendas = new JButton("Tiendas");
		btnTiendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TiendasFrame temp = new TiendasFrame();
				temp.setVisible(true);
			}
		});
		btnTiendas.setBounds(156, 176, 138,44 );
		frmSistemaDePunto.getContentPane().add(btnTiendas);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoUsuariosFrame temp = new ListadoUsuariosFrame();
				temp.setVisible(true);
			}
		});
		btnUsuarios.setBounds(6, 119, 138, 44);
		frmSistemaDePunto.getContentPane().add(btnUsuarios);
		
		JButton btnCiudades = new JButton("Ubicaciones");
		btnCiudades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CiudadesFrame temp = new CiudadesFrame();
				temp.setVisible(true);
			}
		});
		btnCiudades.setBounds(6, 176, 138,44 );
		frmSistemaDePunto.getContentPane().add(btnCiudades);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connectToDataBase();
				openReportFrame();
			}
		});
		btnReportes.setBounds(6, 232, 138, 44);
		frmSistemaDePunto.getContentPane().add(btnReportes);
		
		JButton btnRealizarVentas = new JButton("Realizar Ventas");
		btnRealizarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentaFrame temp = new VentaFrame();
				temp.setVisible(true);
			}
		});
		btnRealizarVentas.setBounds(306, 119, 138, 44);
		frmSistemaDePunto.getContentPane().add(btnRealizarVentas);
		
		JButton btnRealizarCompras = new JButton("Realizar Compras");
		btnRealizarCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompraFrame temp = new CompraFrame();
				temp.setVisible(true);
			}
		});
		btnRealizarCompras.setBounds(306, 176, 138, 44);
		frmSistemaDePunto.getContentPane().add(btnRealizarCompras);
		
		JButton btnProductos = new JButton("Productos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoFrame temp = new ProductoFrame();
				temp.setVisible(true);
			}
		});
		btnProductos.setBounds(306, 232, 138, 44);
		frmSistemaDePunto.getContentPane().add(btnProductos);
		
		JLabel lblNewLabel = new JLabel("Atajos");
		lblNewLabel.setBackground(new Color(0, 0, 205));
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(156, 62, 138, 30);
		frmSistemaDePunto.getContentPane().add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		frmSistemaDePunto.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Principal");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoUsuariosFrame temp = new ListadoUsuariosFrame();
				temp.setVisible(true);
			}
		});
		mnNewMenu.add(mntmUsuarios);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MasterFrame.this.getFrame().dispose();
			}
		});
		mnNewMenu.add(mntmSalir);
		
		JMenu mnMantenimientos = new JMenu("Mantenimientos");
		menuBar.add(mnMantenimientos);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteFrame temp = new ClienteFrame();
				temp.setVisible(true);
			}
		});
		mnMantenimientos.add(mntmClientes);
		
		JMenuItem mntmUbicaciones = new JMenuItem("Productos");
		mntmUbicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoFrame temp = new ProductoFrame();
				temp.setVisible(true);
			}
		});
		mnMantenimientos.add(mntmUbicaciones);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mntmProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuplidoresFrame temp = new SuplidoresFrame();
				temp.setVisible(true);
			}
		});
		mnMantenimientos.add(mntmProveedores);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Tiendas");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TiendasFrame temp = new TiendasFrame();
				temp.setVisible(true);
			}
		});
		mnMantenimientos.add(mntmNewMenuItem);
		
		JMenuItem mntmUbicaciones_1 = new JMenuItem("Ubicaciones");
		mntmUbicaciones_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CiudadesFrame temp = new CiudadesFrame();
				temp.setVisible(true);
			}
		});
		mnMantenimientos.add(mntmUbicaciones_1);
		
		JMenu mnOperaciones = new JMenu("Operaciones");
		menuBar.add(mnOperaciones);
		
		JMenuItem mntmRealizarVentas = new JMenuItem("Realizar Ventas");
		mntmRealizarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentaFrame temp = new VentaFrame();
				temp.setVisible(true);
			}
		});
		mnOperaciones.add(mntmRealizarVentas);
		
		JMenuItem mntmRealizarCompras = new JMenuItem("Realizar Compras");
		mntmRealizarCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompraFrame temp = new CompraFrame();
				temp.setVisible(true);
			}
		});
		mnOperaciones.add(mntmRealizarCompras);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmReportes = new JMenuItem("M\u00F3dulo de Reportes");
		mnReportes.add(mntmReportes);
		mntmReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connectToDataBase();
				openReportFrame();
			}
		});
	}
	}