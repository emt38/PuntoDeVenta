package vistas;
import java.sql.Connection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import principal.Utilidades;
import reportes.AbstractJasperReports;
public class ReportesFrame extends JFrame{
	
	private Connection conn;
	JPanel panel = new JPanel();
	public ReportesFrame(Connection _conn) {
		this.conn = _conn;
	public ReportesFrame(Connection conn) {
		AbstractJasperReports.createReport(conn, "src/Compras.jasper");
		initComponents();
	}
	private void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 164);
		getContentPane().setLayout(null);
		( (JPanel) getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setSize(new Dimension(475,250));


		JButton btnGenerarCompras = new JButton("Reporte de Compras");
		btnGenerarCompras.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.createReport(conn, "C:\\Users\\franklyn\\git\\PuntoDeVenta\\PuntoDeVenta\\Compras.jasper");
				AbstractJasperReports.showViewer();
			}
		});
		btnGenerarCompras.setBounds(44, 31, 175, 46);
		getContentPane().add(btnGenerarCompras);
		
		JButton btnGenerarVentas = new JButton("Reporte de Ventas");
		btnGenerarVentas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.createReport(conn, "C:\\Users\\franklyn\\git\\PuntoDeVenta\\PuntoDeVenta\\ventas2.jasper");
				AbstractJasperReports.showViewer();
			}
		});
		btnGenerarVentas.setBounds(44, 75, 175, 46);
		getContentPane().add(btnGenerarVentas);
		
		JButton btnGenerarUtilidades = new JButton("Reporte de Utilidades");
		btnGenerarUtilidades.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.showViewer();
			}
		});
		btnGenerarUtilidades.setBounds(44, 119, 175, 46);
		getContentPane().add(btnGenerarUtilidades);
		
		
		
		JButton btnExportarCompras = new JButton("Exportar Compras PDF");
		btnExportarCompras.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.exportToPDF( "C:\\Users\\franklyn\\git\\PuntoDeVenta\\reporteCompras.pdf" );
			}
		});
		btnExportarCompras.setBounds(220, 31, 171, 46);
		getContentPane().add(btnExportarCompras);
		
		JButton btnExportarVentas = new JButton("Exportar Ventas PDF");
		btnExportarVentas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.exportToPDF( "C:\\Users\\franklyn\\git\\PuntoDeVenta\\reporteVentas.pdf" );
			}
		});
		btnExportarVentas.setBounds(220, 75, 171, 46);
		getContentPane().add(btnExportarVentas);
		
		JButton btnExportarUtilidades = new JButton("Exportar Utilidades PDF");
		btnExportarUtilidades.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.exportToPDF( "C:\\Users\\franklyn\\git\\PuntoDeVenta\\reporteUtilidades.pdf" );
			}
		});
		btnExportarUtilidades.setBounds(220, 119, 171, 46);
		getContentPane().add(btnExportarUtilidades);
	}
}
