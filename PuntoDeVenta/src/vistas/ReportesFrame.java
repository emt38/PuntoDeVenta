package vistas;
import java.sql.Connection;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import modelos.Articulo;
import modelos.Producto;
import principal.ConnectionDB;
import principal.RobotFingers;
import principal.Utilidades;
import reportes.AbstractJasperReports;
public class ReportesFrame extends JFrame{
	
	private RobotFingers robotFingers = new RobotFingers();
	private Connection conn;
	JPanel panel = new JPanel();
	static JButton btnGenerarCompras = new JButton("Reporte de Compras");
	static JButton btnGenerarVentas = new JButton("Reporte de Ventas");
	static JButton btnGenerarUtilidades = new JButton("Reporte de Utilidades");	
	JButton btnExportarCompras = new JButton("Exportar Compras PDF");	
	JButton btnExportarVentas = new JButton("Exportar Ventas PDF");
	JButton btnExportarUtilidades = new JButton("Exportar Utilidades PDF");
	JButton btnIniciarPruebasRobot = new JButton("Iniciar Pruebas Robot");
	
	public ReportesFrame(Connection _conn) {
		this.conn = _conn;
		initComponents();
	}

	private void IniciarPrueba(JButton btn){
		Robot robot;
		try {
			robot = new Robot();
			robot.delay(2000);
			int x = btn.getLocation().x +75;
			int y = btn.getLocation().y + 45;
			robot.mouseMove(x, y);

			robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	private void initComponents()
	{
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 164);
		getContentPane().setLayout(null);
		( (JPanel) getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		this.setSize(new Dimension(475,250));

		btnGenerarCompras.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.createReport(conn, "src/Compras.jasper");
				AbstractJasperReports.showViewer();
				
				AbstractJasperReports.createReport(conn, "src/Ventas.jasper");
				AbstractJasperReports.showViewer();
				
				AbstractJasperReports.createReport(conn, "src/Utilidades.jasper");
				AbstractJasperReports.showViewer();
					}
		});
		btnGenerarCompras.setBounds(44, 31, 175, 46);
		getContentPane().add(btnGenerarCompras);
		
		btnGenerarVentas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
					AbstractJasperReports.createReport(conn, "src/Ventas.jasper");
					AbstractJasperReports.showViewer();
			}
		});
		btnGenerarVentas.setBounds(44, 75, 175, 46);
		getContentPane().add(btnGenerarVentas);
		
		btnGenerarUtilidades.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
					AbstractJasperReports.createReport(conn, "src/Utilidades.jasper");
					AbstractJasperReports.showViewer();
					
					AbstractJasperReports.createReport(conn, "src/Ventas.jasper");
					AbstractJasperReports.showViewer();
					
					AbstractJasperReports.createReport(conn, "src/Utilidades.jasper");
					AbstractJasperReports.showViewer();
			}
		});
		btnGenerarUtilidades.setBounds(44, 119, 175, 46);
		getContentPane().add(btnGenerarUtilidades);
	}

	public static Component getBtnCompras(){
		return btnGenerarCompras;
	}
	
	public static Component getBtnVentas(){
		return btnGenerarVentas;
	}
	
	public static Component getBtnUtilidades(){
		return btnGenerarUtilidades;
	}
}
