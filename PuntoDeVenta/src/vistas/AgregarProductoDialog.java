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
import javax.swing.JFrame;
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

public class AgregarProductoDialog extends JDialog{

	private JPanel contentPane;
	private JTable tabla;
	private JTable tablaExterna;
	private JScrollPane scrollPane;
	private JTextField txtBusqueda;
	List<Producto> productosJtable = new Producto().listar();
	List<Producto>  productos = new ArrayList<Producto>();
	static List<Articulo> articulos =  new ArrayList<Articulo>();
	
	public static List<Articulo> getArticulosSeleccionados(){
		return articulos;
	}
	public static void vaciarArticulosSeleccionados(){
		articulos.clear();
	}
	
	public AgregarProductoDialog(JTable tablaExterna) {
		setTitle("Agregar articulo");
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setBounds(100, 100, 570, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		this.tablaExterna = tablaExterna;
		
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
		cbbxMetodoBusqueda.setBounds(10, 34, 111, 23);
		contentPane.add(cbbxMetodoBusqueda);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(131, 34, 86, 25);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		contentPane.setLayout(null);
		
		tabla = new JTable();
		tabla.setName("tabla");
		String[] columnas = {"ID", "Codigo", "Descripcion", "Costo", "Precio", "Tasa de impuesto"};
		String[] campos = {"Id", "Codigo", "Descripcion", "Costo", "Precio", "TasaImpuesto"};  
		Object[][] datos = Utilidades.listToBidiArray(productosJtable, campos);
		
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
		scrollPane.setName("scrollPane");
		scrollPane.setBounds(10, 109, 536, 209);
		contentPane.add(scrollPane);
		
		String textoBusqueda = txtBusqueda.getText().replace("'","''").replace("\"", "\"\""); 
		Articulo articulo = new Articulo();
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(418, 27, 101, 39);
		contentPane.add(btnFiltrar);
		getRootPane().setDefaultButton(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Producto> tempProductos = new Producto().listar();
					
				if(!(txtBusqueda.getText() == "")){
					if(cbbxMetodoBusqueda.getSelectedItem() == "Id"){
						if(CompraFrame.isInt(txtBusqueda.getText())){
							tempProductos = new Producto().listar("WHERE p.idproducto = " + txtBusqueda.getText());
						}
					}else if(cbbxMetodoBusqueda.getSelectedItem() == "Descripcion"){
						tempProductos = new Producto().listar("WHERE descripcion LIKE '%" + txtBusqueda.getText() + "%' OR codigo LIKE  '%" + txtBusqueda.getText() + "%' ");
					}
				}
				else{
					tempProductos = new Producto().listar();
				}
				
				if (tempProductos.size()==0){
					return;
				}
				
				productos = tempProductos;
				productosJtable = tempProductos;
				
				String[] columnas = {"ID", "Codigo", "Descripcion", "Costo", "Precio", "Tasa de impuesto"};
				String[] campos = {"Id", "Codigo", "Descripcion", "Costo", "Precio", "TasaImpuesto"};  
				Object[][] datos = Utilidades.listToBidiArray(productosJtable, campos);
				
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
		btnAgregar.setName("btnAgregar");
		btnAgregar.setBounds(354, 329, 89, 23);
		contentPane.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ActualizarTablaExterna(tablaExterna);
				}
				catch(Exception e){
				}
				
			}
		});
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 78, 536, 2);
		contentPane.add(separator);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setName("btnCerrar");
		btnCerrar.setBounds(455, 329, 89, 23);
		contentPane.add(btnCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCerrar.setActionCommand("cancelar");
				AgregarProductoDialog.this.dispose();
			}
		});
	}
	
	private Producto ConvertRowToProduct(JTable tabla, int rowNumber) {
		Producto tempProducto = new Producto();    
		
		int colId = getColumnIndex(tabla, "ID");
		int colCodigo = getColumnIndex(tabla, "Codigo");
		int colDescripcion = getColumnIndex(tabla, "Descripcion");
		int colCosto = getColumnIndex(tabla, "Costo");
		int colPrecio = getColumnIndex(tabla, "Precio");
		int colTasaImpuesto = getColumnIndex(tabla, "Tasa de impuesto");
		
		int Id = (int)tabla.getValueAt(rowNumber, colId);
		String Codigo = (String)tabla.getValueAt(rowNumber, colCodigo);
		String Descripcion = (String)tabla.getValueAt(rowNumber, colDescripcion);
		float Costo = (float)tabla.getValueAt(rowNumber, colCosto);
		float Precio = (float)tabla.getValueAt(rowNumber, colPrecio);
		float TasaImpuesto = (float)tabla.getValueAt(rowNumber, colTasaImpuesto);
		
		tempProducto.setId(Id);
		tempProducto.setCodigo(Codigo);
		tempProducto.setDescripcion(Descripcion);
		tempProducto.setCosto(Costo);
		tempProducto.setPrecio(Precio);
		tempProducto.setTasaImpuesto(TasaImpuesto);
				
		return tempProducto;
	}
	
	private int getColumnIndex (JTable table, String colName) {
	    for (int i=0; i < table.getColumnCount(); i++) {
	        if (table.getColumnName(i).equals(colName)) return i;
	    }
	    return -1;
	}
	
	public void ActualizarTablaExterna(JTable tabla) {
		
		int rowNum = this.tabla.getSelectedRow();
		
		Producto producto = ConvertRowToProduct(this.tabla, rowNum);
		
		Articulo articuloTemp = new Articulo();
		articuloTemp.setCantidad(1);
		articuloTemp.setProducto(producto);
		articuloTemp.setValor(producto.getPrecio());
		articuloTemp.setTasaImpuestos(producto.getTasaImpuesto());
		articuloTemp.totalizar();
				
		articulos.add(articuloTemp);
		
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
	
	private void ReloadAll() {
		String[] columnas = {"ID", "Codigo", "Descripcion", "Costo", "Precio", "Tasa de impuesto"};
		String[] campos = {"Id", "Codigo", "Descripcion", "Costo", "Precio", "TasaImpuesto"};    
		Object[][] datos = Utilidades.listToBidiArray(productosJtable, campos);
		
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
					AgregarProductoDialog dialog = new AgregarProductoDialog( new JTable());
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
