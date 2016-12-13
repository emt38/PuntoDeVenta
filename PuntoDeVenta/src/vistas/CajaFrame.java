package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelos.Articulo;
import modelos.Producto;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CajaFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Producto producto = new Producto();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CajaFrame frame = new CajaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CajaFrame() {
		this.setTitle("Caja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/*********************SandBoxPrueva************************/
		/*********************************************************/
		/*********************************************************/
		Producto producto = new Producto();
		List<Producto> productos = producto.listar();
				
		Articulo articulo1 = new Articulo();
		articulo1.setProducto(productos.get(1));
		articulo1.setCantidad(6);
		articulo1.setValor(productos.get(1).getPrecio());
		articulo1.setTasaImpuestos(18f);
		articulo1.setImpuestos(0.18f * (articulo1.getValor()));
		articulo1.setSubTotal((articulo1.getValor() + 
							   articulo1.getImpuestos()) * 
							   6);
		
		Articulo articulo2 = new Articulo();
		articulo2.setProducto(productos.get(2));
		articulo2.setCantidad(3);
		articulo2.setValor(productos.get(2).getPrecio());
		articulo2.setTasaImpuestos(18f);
		articulo2.setImpuestos(0.18f * (articulo2.getValor()));
		articulo2.setSubTotal((articulo2.getValor() + 
							   articulo2.getImpuestos()) * 
							   6);
		
		String[] articulo = {articulo1.getProducto().getDescripcion(), articulo2.getProducto().getDescripcion()};
		Float[] cantidad = {articulo1.getCantidad(), articulo2.getCantidad()};
		Float[] valor = {articulo1.getValor(), articulo2.getValor()};
		Float[] impuesto = {articulo1.getImpuestos(), articulo2.getImpuestos()};
		Float[] subTotal = {articulo1.getSubTotal(), articulo2.getSubTotal()};
		
		DefaultTableModel modelo = new DefaultTableModel ()
		//Para evitar que las celdas sean editables
			{
			    @Override
			    public boolean isCellEditable (int fila, int columna) {
			        return false;
			    }
			};
		
		modelo.addColumn("ARTICULO", articulo);
		modelo.addColumn("CANTIDAD", cantidad);
		modelo.addColumn("VALOR", valor);
		modelo.addColumn("IMPUESTOS", impuesto);
		modelo.addColumn("SUBTOTAL", subTotal);
		
		/*********************SandBoxPrueva************************/
		/*********************************************************/
		/*********************************************************/
		
		/*
		List<Producto> listaProductos = producto.listar("");	
		
		String[] productosDescripcion = new String[listaProductos.size()]; 
		
		//Para pasar la descripcion de un producto de "listaProductos" al arreglo "productosDescripcion"
		int contador = 0;
		for( Producto prod : listaProductos ){
			productosDescripcion[contador] = prod.getDescripcion();
			contador++;
		}		
		
		String[] columnas = {"CODIGO", "ARTICULO", "CANTIDAD", "VALOR", };
		
		//Agregando las columnas a la tabla
		DefaultTableModel modelo = new DefaultTableModel (null, columnas)
		//Para evitar que las celdas sean editables
			{
			    @Override
			    public boolean isCellEditable (int fila, int columna) {
			        return false;
			    }
			};
			
		modelo.addColumn("ARTICULO", productosDescripcion);
		*/
		table = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 47, 679, 233);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JButton btnAgregarProducto = new JButton("Agregar articulo");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarArticuloDialog agregarArticulo = new AgregarArticuloDialog();
				agregarArticulo.setVisible(true);
			}
		});
		btnAgregarProducto.setBounds(21, 11, 151, 23);
		contentPane.add(btnAgregarProducto);
		
	}
}
