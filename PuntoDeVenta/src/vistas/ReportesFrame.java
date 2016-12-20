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
	
	JPanel panel = new JPanel();
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
		this.setSize(new Dimension(800,600));


		JButton btnGenerarCompras = new JButton("Reporte de Compras");
		//btnGenerarCompras.setLayout(new GridLayout(1,3,200,0));
		//btnGenerarCompras.add(panel);
		btnGenerarCompras.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.showViewer();
			}
		});
		btnGenerarCompras.setBounds(44, 31, 110, 46);
		getContentPane().add(btnGenerarCompras);
		
		JButton btnGenerarVentas = new JButton("Reporte de Ventas");
		btnGenerarVentas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.showViewer();
			}
		});
		btnGenerarVentas.setBounds(118, 83, 110, 46);
		getContentPane().add(btnGenerarVentas);
		
		
		
		JButton btnExportarPdf = new JButton("Exportar PDF");
		btnExportarPdf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.exportToPDF( "src/report.pdf" );
			}
		});
		btnExportarPdf.setBounds(197, 31, 110, 46);
		getContentPane().add(btnExportarPdf);
	}
}
