package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelos.Articulo;
import modelos.Compra;
import modelos.Producto;
import principal.Utilidades;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;

public class AgregarArticuloDialog extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JScrollPane scrollPane;
	private JTextField txtBusqueda;
	private Compra compra = new Compra();
	
	public AgregarArticuloDialog() {
		setTitle("Agregar articulo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMetodoBusqueda = new JLabel("Metodo de busqueda: ");
		lblMetodoBusqueda.setBounds(10, 11, 144, 14);
		contentPane.add(lblMetodoBusqueda);
		
		JLabel lblResultados = new JLabel("Resultados");
		lblResultados.setBounds(10, 91, 86, 16);
		contentPane.add(lblResultados);
		
		List<String> metodosBusqueda = new ArrayList<>();
		metodosBusqueda.add("Id");
		metodosBusqueda.add("Descripcion");
		
		JComboBox cbbxMetodoBusqueda = new JComboBox(metodosBusqueda.toArray());
		lblMetodoBusqueda.setLabelFor(cbbxMetodoBusqueda);
		cbbxMetodoBusqueda.setBounds(10, 36, 111, 20);
		contentPane.add(cbbxMetodoBusqueda);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(131, 36, 86, 20);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		contentPane.setLayout(null);
		
		tabla = new JTable();
		String[] columnas = {"Cantidad2", "Producto", "Costo", "Impuestos", "Subtotal"};
		String[] campos = {"cantidad", "producto","valor", "impuestos", "subtotal"};  
		Object[][] datos = Utilidades.listToBidiArray(compra.getArticulos(), campos);
		
		DefaultTableModel modelo = new DefaultTableModel(datos, columnas)
		//Para evitar que las celdas sean editables
		{
		    @Override
		    public boolean isCellEditable (int fila, int columna) {
		        return false;
		    }
		};
		
		tabla.setModel(modelo);

		tabla.getColumnModel().getColumn(0).setPreferredWidth(76);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(323);
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 109, 536, 112);
		contentPane.add(scrollPane);
		
		String textoBusqueda = txtBusqueda.getText().replace("'","''").replace("\"", "\"\"");
		Producto producto = new Producto(); 
		Articulo articulo = new Articulo();
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(229, 33, 89, 23);
		contentPane.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Producto> productos = producto.listar("WHERE p.idproducto =  " + txtBusqueda.getText());
				System.out.println(productos.get(0).getDescripcion());
				
				producto.setCodigo(productos.get(0).getCodigo());
				producto.setCosto(productos.get(0).getCosto());
				producto.setDescripcion(productos.get(0).getDescripcion());
				producto.setId(productos.get(0).getId());
				producto.setInventario(productos.get(0).getInventario());
				producto.setPrecio(productos.get(0).getPrecio());
				producto.setTasaImpuesto(productos.get(0).getTasaImpuesto());
				
				articulo.setCantidad(3);
				articulo.setProducto(producto);
				articulo.setValor(producto.getPrecio());
				articulo.setTasaImpuestos(producto.getTasaImpuesto());
				articulo.totalizar();
				
				List<Articulo> articulos = new ArrayList<Articulo>();
				articulos.add(articulo);
				//articulos.set(0,articulo);
				
				String[] columnas = {"Cantidad2", "Producto", "Costo", "Impuestos", "Subtotal"};
				String[] campos = {"cantidad", "producto","valor", "impuestos", "subtotal"};  
				Object[][] datos = Utilidades.listToBidiArray(articulos, campos);
				
				DefaultTableModel modelo = new DefaultTableModel(datos, columnas)
				//Para evitar que las celdas sean editables
				{
				    @Override
				    public boolean isCellEditable (int fila, int columna) {
				        return false;
				    }
				};
				
				tabla.setModel(modelo);
			}
		});
		
		tabla.setModel(modelo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(356, 233, 89, 23);
		contentPane.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				CompraFrame.pasarObjeto(articulo);
				AgregarArticuloDialog.this.dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(457, 233, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarArticuloDialog.this.dispose();
			}
		});

		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarArticuloDialog frame = new AgregarArticuloDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
