package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelos.Articulo;
import modelos.Compra;
import modelos.Producto;
import modelos.Suplidor;
import principal.Utilidades;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class AgregarArticuloDialog extends JDialog{

	private JPanel contentPane;
	private JTable tabla;
	private JScrollPane scrollPane;
	private JTextField txtBusqueda;
	private Compra compra = new Compra();
	List<Articulo> articulos = compra.getArticulos();
	
	public boolean articuloAgregado = false;
	private JTextField textCantidad;
	
	public AgregarArticuloDialog() {
		setTitle("Agregar articulo");
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setBounds(100, 100, 570, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
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
		String[] columnas = {"Cantidad", "Producto", "Costo", "Impuestos", "Subtotal"};
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

		tabla.getColumnModel().getColumn(0).setPreferredWidth(76);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(323);
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 109, 536, 112);
		contentPane.add(scrollPane);
		
		String textoBusqueda = txtBusqueda.getText().replace("'","''").replace("\"", "\"\""); 
		Articulo articulo = new Articulo();
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(418, 27, 101, 39);
		contentPane.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Producto producto = new Producto();
				List<Producto> productos = new Producto().listar();
				
				if(cbbxMetodoBusqueda.getSelectedItem() == "Id"){
					if(CompraFrame.isInt(txtBusqueda.getText())){
						productos = new Producto().listar("WHERE p.idproducto = " + txtBusqueda.getText());
					}
					else
						return;
				}else if(cbbxMetodoBusqueda.getSelectedItem() == "Descripcion"){
					productos = new Producto().listar("WHERE descripcion LIKE '%" + txtBusqueda.getText() + "%' OR codigo LIKE  '%" + txtBusqueda.getText() + "%' ");
				}
				
				if (productos.size()==0){
					return;
				}
				
				//Para sacar el producto del ActionListener
				producto.setCodigo(productos.get(0).getCodigo());
				producto.setCosto(productos.get(0).getCosto());
				producto.setDescripcion(productos.get(0).getDescripcion());
				producto.setId(productos.get(0).getId());
				producto.setInventario(productos.get(0).getInventario());
				producto.setPrecio(productos.get(0).getPrecio());
				producto.setTasaImpuesto(productos.get(0).getTasaImpuesto());
				
				Articulo articuloTemp = new Articulo();
				
				if (CompraFrame.isInt(textCantidad.getText()))
					articuloTemp.setCantidad(Integer.parseInt(textCantidad.getText()));
				else
					articuloTemp.setCantidad(1);
				articuloTemp.setProducto(producto);
				articuloTemp.setValor(producto.getPrecio());
				articuloTemp.setTasaImpuestos(producto.getTasaImpuesto());
				articuloTemp.totalizar();
				
				articulo.setCantidad(articuloTemp.getCantidad());
				articulo.setProducto(articuloTemp.getProducto());
				articulo.setValor(articuloTemp.getProducto().getPrecio());
				articulo.setTasaImpuestos(articuloTemp.getProducto().getTasaImpuesto());
				articulo.totalizar();

				
				articulos.add(articuloTemp);
				
				String[] columnas = {"Cantidad", "Producto", "Costo", "Impuestos", "Subtotal"};
				String[] campos = {"cantidad","producto","valor", "impuestos", "subtotal"};  
				Object[][] datos = Utilidades.listToBidiArray(articulos, campos);
				
				for(int i = 0; i< datos.length; i++){
					datos[i][1] = ((Producto) datos[i][1]).getDescripcion(); 
				}
				
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
				try{
					
					pasarObjeto(articulos);
					articuloAgregado = true;
				}
				catch(Exception e){
					articuloAgregado = false;
				}
				
				AgregarArticuloDialog.this.dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(457, 233, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(227, 38, 55, 14);
		contentPane.add(lblCantidad);
		
		textCantidad = new JTextField("1");
		textCantidad.setBounds(292, 36, 86, 20);
		contentPane.add(textCantidad);
		textCantidad.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 78, 536, 2);
		contentPane.add(separator);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancelar.setActionCommand("cancelar");
				AgregarArticuloDialog.this.dispose();
			}
		});
		
		
		
		//////Para eliminar articulos///////
			  int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
			  InputMap inputMap = tabla.getInputMap(condition);
			  ActionMap actionMap = tabla.getActionMap();
			  
			  final String DELETE = "Delete";

			  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE);
			  actionMap.put(DELETE, new AbstractAction() {
			     public void actionPerformed(ActionEvent e) {
			    	 
			    	 String productoDescripcion = (String) tabla.getValueAt(tabla.getSelectedRow(), 1);
			 		
			 		for(int i = 0; i< articulos.size(); i++){
			 			if (productoDescripcion == articulos.get(i).getProducto().getDescripcion())
			 				articulos.remove(i);
			 		}
			 		ReloadAll();
			     }
			  });
	}
	
	static Object objeto;
	
	public static void pasarObjeto(Object object){
		objeto = object;
	}
	
	public static Object pasarObjeto(){
		return objeto;
	}
	
	private void ReloadAll() {
		String[] columnas = {"Cantidad", "Producto", "Costo", "Impuestos", "Subtotal"};
		String[] campos = {"cantidad", "producto","valor", "impuestos", "subtotal"};  
		Object[][] datos = Utilidades.listToBidiArray(articulos, campos);
		
		for(int i = 0; i< datos.length; i++){
			datos[i][1] = ((Producto) datos[i][1]).getDescripcion(); 
		}
		
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
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarArticuloDialog dialog = new AgregarArticuloDialog();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
