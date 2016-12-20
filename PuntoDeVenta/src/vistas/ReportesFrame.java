package vistas;
import java.sql.Connection;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
	
	
	public ReportesFrame(Connection conn) {
		AbstractJasperReports.createReport(conn, "C:\\Users\\franklyn\\git\\PuntoDeVenta\\PuntoDeVenta\\Compras.jasper");
		initComponents();
	}
	private void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 164);
		getContentPane().setLayout(null);
		( (JPanel) getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.showViewer();
			}
		});
		btnGenerar.setBounds(44, 31, 110, 46);
		getContentPane().add(btnGenerar);
		
		JButton btnExportarPdf = new JButton("Exportar PDF");
		btnExportarPdf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				AbstractJasperReports.exportToPDF( "C:\\reports\\report.pdf" );
			}
		});
		btnExportarPdf.setBounds(197, 31, 110, 46);
		getContentPane().add(btnExportarPdf);
	}
}
