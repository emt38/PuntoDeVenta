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
		String url = "jdbc:mysql://127.0.0.1/";
		String db = "posdb";
		
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
		frmSistemaDePunto.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoUsuariosFrame temp = new ListadoUsuariosFrame();
				temp.setVisible(true);
			}
		});
		
		JLabel label = new JLabel("");
		frmSistemaDePunto.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("Atajos");
		lblNewLabel.setBackground(new Color(0, 0, 205));
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmSistemaDePunto.getContentPane().add(lblNewLabel);
		
		JLabel label_1 = new JLabel("");
		frmSistemaDePunto.getContentPane().add(label_1);
		frmSistemaDePunto.getContentPane().add(btnUsuarios);
		
		JButton btnRealizarVentas = new JButton("Realizar Ventas");
		btnRealizarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentaFrame temp = new VentaFrame();
				temp.setVisible(true);
			}
		});
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteFrame temp = new ClienteFrame();
				temp.setVisible(true);
			}
		});
		frmSistemaDePunto.getContentPane().add(btnClientes);
		frmSistemaDePunto.getContentPane().add(btnRealizarVentas);
		
		JButton btnRealizarCompras = new JButton("Realizar Compras");
		btnRealizarCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompraFrame temp = new CompraFrame();
				temp.setVisible(true);
			}
		});
		
		
		
		JButton btnTiendas = new JButton("Tiendas");
		btnTiendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TiendasFrame temp = new TiendasFrame();
				temp.setVisible(true);
			}
		});
		
		JButton btnCiudades = new JButton("Ubicaciones");
		btnCiudades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CiudadesFrame temp = new CiudadesFrame();
				temp.setLocationRelativeTo(null);
				temp.setResizable(false);
				temp.setVisible(true);
			}
		});
		frmSistemaDePunto.getContentPane().add(btnCiudades);
		frmSistemaDePunto.getContentPane().add(btnTiendas);
		frmSistemaDePunto.getContentPane().add(btnRealizarCompras);
		
		JButton btnProductos = new JButton("Productos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoFrame temp = new ProductoFrame();
				temp.setVisible(true);
			}
		});
		
		JButton btnProveedores = new JButton("Proveedores");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuplidoresFrame temp = new SuplidoresFrame();
				temp.setVisible(true);
			}
		});
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connectToDataBase();
				openReportFrame();
			}
		});
		frmSistemaDePunto.getContentPane().add(btnReportes);
		frmSistemaDePunto.getContentPane().add(btnProveedores);
		frmSistemaDePunto.getContentPane().add(btnProductos);
		
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
		
		JMenuItem mntmDevolverArtculos = new JMenuItem("Devoluci\u00F3n de Ventas");
		mntmDevolverArtculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DevolucionVentaFrame temp = new DevolucionVentaFrame();
				temp.setVisible(true);
			}
		});
		mnOperaciones.add(mntmDevolverArtculos);
		
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