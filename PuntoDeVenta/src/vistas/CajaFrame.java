package vistas;

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
import principal.Utilidades;

public class CajaFrame extends JFrame implements ActionListener {

	private JPanel contentPane = new JPanel();
	private static DefaultTableModel modelo;
	JTable table;
	JScrollPane scrollPane;
	
	public CajaFrame() {
		this.setTitle("Caja");
		
		JButton btnAgregarArticulo = new JButton("Agregar articulo");
		btnAgregarArticulo.setBounds(21, 11, 151, 23);
		contentPane.add(btnAgregarArticulo);
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarArticuloFrame agregarArticulo = new AgregarArticuloFrame();
				agregarArticulo.setVisible(true);
			}
		});			
		
		
		String[] campos = {"producto", "cantidad", "valor", "impuestos", "subtotal"};
		Object[][] datos = Utilidades.listToBidiArray(listaArticulos(), campos);
		
		for (int i = 0; i < datos.length; i++){
			datos[i][0] = ((Producto) datos[i][0]).getDescripcion();
		}
		
		String[] columnas = {"ARTICULO", "CANTIDAD", "VALOR", "IMPUESTOS", "SUBTOTAL" };
		
		modelo = new DefaultTableModel (datos, columnas)
		//Para evitar que las celdas sean editables
			{
			    @Override
			    public boolean isCellEditable (int fila, int columna) {
			        return false;
			    }
			};
			
		table = new JTable(modelo);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 47, 679, 233);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		contentPane.add(scrollPane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 352);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	public static List<Articulo> listaArticulos(){
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
		
		List<Articulo>articulos = new ArrayList<Articulo>();
		articulos.add(articulo1);
		articulos.add(articulo2);
	
		return articulos;
	}
	
	
	
	public static void pasarArticulo(Articulo articulo){
		Object[] articuloObj = new Object[5];
		articuloObj[0] = articulo.getProducto().getDescripcion();
		articuloObj[1] = articulo.getCantidad();
		articuloObj[2] = articulo.getValor();
		articuloObj[3] = articulo.getImpuestos();
		articuloObj[4] = articulo.getSubTotal();
		modelo.addRow(articuloObj);
		
	}
	
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
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
}
