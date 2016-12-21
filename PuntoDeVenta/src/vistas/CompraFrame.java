package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelos.Articulo;
import modelos.Compra;
import modelos.Producto;
import modelos.Suplidor;
import principal.Program;
import principal.Utilidades;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class CompraFrame extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JScrollPane scrollPane;
	private JLabel lblTotalImpuesto;
	private JLabel lblTotalDescuento;
	private JLabel lblTotal;
	private JTextField txtTotalImpuestos;
	private JTextField txtTotalDescuento;
	private JTextField txtTotal;
	private JTextField txtCambiardesc;
	
	private Compra compra = new Compra();
	private JButton btnRealizarCompra;
	
	private void ReloadAll() {
		String[] columnas = {"Cantidad", "Producto", "Costo", "Impuestos", "Subtotal"};
		String[] campos = {"cantidad", "producto","valor", "impuestos", "subtotal"};  
		Object[][] datos = Utilidades.listToBidiArray(compra.getArticulos(), campos);
		
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
		
		txtTotalDescuento.setText(String.format("%.2f", compra.getDescuentos()));
		txtTotalImpuestos.setText(String.format("%.2f", compra.getImpuestos()));
		txtTotal.setText(String.format("%.2f", compra.getTotal()));
	}
	
	public CompraFrame() {
		setTitle("Compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Suplidor suplidor = new Suplidor();
		List<Suplidor> suplidores = new ArrayList<>();
		List<String> nombreSuplidores = new ArrayList<String>();
		JComboBox cbxSuplidores = new JComboBox();
		
		suplidores.addAll(new Suplidor().listar("ORDER BY Nombre"));
		
		if( suplidores.size() > 0 ){
			for(Suplidor supl: suplidores){
				nombreSuplidores.add(supl.getNombre());
			}
			cbxSuplidores = new JComboBox(nombreSuplidores.toArray());
			suplidor = suplidor.listar("WHERE nombre='" + cbxSuplidores.getSelectedItem() + "'").get(0);
			compra = new Compra(suplidor, Program.getLoggedUser(), Program.getLoggedUser().getTienda());
			
		}else{
			
		}
		
		cbxSuplidores.setBounds(379, 11, 167, 20);
		contentPane.add(cbxSuplidores);
		
		
		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cantidad", "Producto", "Costo", "Impuestos", "Subtotal"
			}
		));

		tabla.getColumnModel().getColumn(0).setPreferredWidth(76);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(323);
		scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 86, 536, 112);
		contentPane.add(scrollPane);
		
		lblTotalImpuesto = new JLabel("Total impuesto: ");
		lblTotalImpuesto.setBounds(10, 209, 104, 14);
		contentPane.add(lblTotalImpuesto);
		
		lblTotalDescuento = new JLabel("Total descuento: ");
		lblTotalDescuento.setBounds(10, 234, 104, 14);
		contentPane.add(lblTotalDescuento);
		
		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(10, 259, 104, 14);
		contentPane.add(lblTotal);
		
		txtCambiardesc = new JTextField();
		txtCambiardesc.setBounds(168, 55, 86, 20);
		contentPane.add(txtCambiardesc);
		txtCambiardesc.setColumns(10);
		
		txtTotalImpuestos = new JTextField();
		txtTotalImpuestos.setBounds(150, 206, 86, 20);
		contentPane.add(txtTotalImpuestos);
		txtTotalImpuestos.setColumns(10);
		txtTotalImpuestos.setEditable(false);
		txtTotalImpuestos.setText(String.format("%.2f", compra.getImpuestos()));
		
		txtTotalDescuento = new JTextField();
		txtTotalDescuento.setBounds(150, 231, 86, 20);
		contentPane.add(txtTotalDescuento);
		txtTotalDescuento.setColumns(10);
		txtTotalDescuento.setEditable(false);
		txtTotalDescuento.setText(String.format("%.2f", compra.getDescuentos()));
		
		txtTotal = new JTextField();
		txtTotal.setBounds(150, 256, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setEditable(false);
		txtTotal.setText(String.format("%.2f", compra.getTotal()));
		
		JButton btnCambiarDesc = new JButton("Cambiar descuento");
		btnCambiarDesc.setBounds(10, 52, 148, 23);
		contentPane.add(btnCambiarDesc);
		btnCambiarDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isFloat(txtCambiardesc.getText())){
					compra.setDescuentos(Float.parseFloat(txtCambiardesc.getText()));
					compra.totalizar();
					txtTotalDescuento.setText(String.format("%.2f", compra.getDescuentos()));
					ReloadAll();
				}
				
			}
		});
		
		JButton btnAgregarArticulo = new JButton("Agregar Articulo");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarArticuloDialog agregarArticulo = new AgregarArticuloDialog();
				agregarArticulo.setModal(true);
				agregarArticulo.setVisible(true);
				agregarArticulo.setResizable(false);
				
				if(agregarArticulo.articuloAgregado){
					compra.agregarArticulo((Articulo) AgregarArticuloDialog.pasarObjeto());
					ReloadAll();
				}
			}
		});
		btnAgregarArticulo.setBounds(10, 13, 148, 23);
		contentPane.add(btnAgregarArticulo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompraFrame.this.dispose();
			}
		});
		btnSalir.setBounds(419, 297, 127, 30);
		getContentPane().add(btnSalir);
		
		JLabel lblNotaborrararticulo = new JLabel("*Para borrar un articulo presione la tecla \"Delete\"");
		lblNotaborrararticulo.setBounds(284, 209, 262, 14);
		contentPane.add(lblNotaborrararticulo);
		
		btnRealizarCompra = new JButton("Realizar Compra");
		btnRealizarCompra.setBounds(282, 297, 127, 30);
		btnRealizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compra.efectuar();
			}
		});
		contentPane.add(btnRealizarCompra);
		
		//////
		  int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		  InputMap inputMap = tabla.getInputMap(condition);
		  ActionMap actionMap = tabla.getActionMap();
		  
		  final String DELETE = "Delete";

		  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE);
		  actionMap.put(DELETE, new AbstractAction() {
		     public void actionPerformed(ActionEvent e) {
		    	 
		    	 Articulo articuloABorrar= new Articulo ();
		    	 String productoDescripcion = (String) tabla.getValueAt(tabla.getSelectedRow(), 1);
		    	 
		         
		         String[] campos =  {"producto"};
		         Object[][] datos = Utilidades.listToBidiArray(compra.getArticulos(), campos);
		 		
		 		for(int i = 0; i< datos.length; i++){
		 			if (productoDescripcion == compra.getArticulos().get(i).getProducto().getDescripcion())
		 				articuloABorrar = compra.getArticulos().get(i);
		 		}
		 		
		 		compra.retirarArticulo(articuloABorrar);
		 		ReloadAll();
		     }
		  });

	}
	
	public static boolean isFloat(String cadena){
		try {
			Float.parseFloat(cadena);
			return true;
		}
		catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public static boolean isInt(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		}
		catch (NumberFormatException nfe){
			return false;
		}
	}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompraFrame frame = new CompraFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
