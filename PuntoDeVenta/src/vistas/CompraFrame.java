package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

public class CompraFrame extends JFrame {

	private JPanel contentPane;
	private JTable tabla = new JTable();
	private JScrollPane scrollPane;
	private JLabel lblTotalImpuesto;
	private JLabel lblTotalDescuento;
	private JLabel lblTotal;
	private JTextField txtTotalImpuestos = new JTextField();
	private JTextField txtTotalDescuento = new JTextField();
	private JTextField txtTotal = new JTextField();
	private JTextField txtCambiardesc = new JTextField();
	private JButton btnRealizarCompra;
	AgregarProductoDialog agregarProducto = new AgregarProductoDialog(tabla);
	
	Suplidor suplidor = new Suplidor();
	
	private Compra compra = new Compra(suplidor, Program.getLoggedUser(), Program.getLoggedUser().getTienda());
	String[] columnas = {"Cantidad", "Producto", "Costo", "Impuestos", "Subtotal"};
	
	List<Suplidor> suplidores = new Suplidor().listar("ORDER BY Nombre");
	JComboBox cbxSuplidores = new JComboBox();
	private JLabel lblSuplidor;
	private JButton btnVaciarArticulos;
	
	
	public void ReloadAll() {
		if( suplidores.size() > 0 ){	
			cbxSuplidores = new JComboBox<ComboItem>();
			for(Suplidor supl: suplidores){
				cbxSuplidores.addItem(new ComboItem(supl.getNombre(),supl));
			}
			
			Object item = cbxSuplidores.getSelectedItem();
			Object objeto = ((ComboItem)item).getObjeto();
			suplidor = (Suplidor) objeto;
			compra.setSuplidor(suplidor);
		}
		
		
		String[] campos = {"cantidad", "producto","valor", "impuestos", "subtotal"};  
		Object[][] datos = Utilidades.listToBidiArray(AgregarProductoDialog.getArticulosSeleccionados(), campos);
		
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ReloadAll();
		
		cbxSuplidores.setBounds(379, 11, 167, 23);
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
		tabla.setName("tabla");
		scrollPane.setBounds(10, 86, 536, 112);
		contentPane.add(scrollPane);
		
		lblTotalImpuesto = new JLabel("Total impuesto: ");
		lblTotalImpuesto.setBounds(10, 216, 104, 14);
		contentPane.add(lblTotalImpuesto);
		
		lblTotalDescuento = new JLabel("Total descuento: ");
		lblTotalDescuento.setBounds(10, 251, 104, 14);
		contentPane.add(lblTotalDescuento);
		
		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(10, 287, 104, 14);
		contentPane.add(lblTotal);
		
		txtCambiardesc = new JTextField();
		txtCambiardesc.setName("txtCambiardesc");
		txtCambiardesc.setBounds(168, 52, 86, 25);
		contentPane.add(txtCambiardesc);
		txtCambiardesc.setColumns(10);
		
		txtTotalImpuestos = new JTextField();
		txtTotalImpuestos.setBounds(124, 210, 86, 25);
		contentPane.add(txtTotalImpuestos);
		txtTotalImpuestos.setColumns(10);
		txtTotalImpuestos.setEditable(false);
		txtTotalImpuestos.setText(String.format("%.2f", compra.getImpuestos()));
		
		txtTotalDescuento = new JTextField();
		txtTotalDescuento.setName("txtTotalDescuento");
		txtTotalDescuento.setBounds(124, 245, 86, 25);
		contentPane.add(txtTotalDescuento);
		txtTotalDescuento.setColumns(10);
		txtTotalDescuento.setEditable(false);
		txtTotalDescuento.setText(String.format("%.2f", compra.getDescuentos()));
		
		txtTotal = new JTextField();
		txtTotal.setBounds(124, 281, 86, 25);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setEditable(false);
		txtTotal.setText(String.format("%.2f", compra.getTotal()));
		
		JButton btnCambiarDesc = new JButton("Cambiar descuento");
		btnCambiarDesc.setName("btnCambiarDesc");
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
		
		JButton btnBuscarArticulos = new JButton("Buscar Articulos");
		btnBuscarArticulos.setName("btnBuscarArticulos");
		btnBuscarArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(Articulo art:AgregarProductoDialog.getArticulosSeleccionados()){
					compra.retirarArticulo(art);
				}
				
				agregarProducto.setModal(true);
				agregarProducto.setVisible(true);
				agregarProducto.setResizable(false);
				
				if(AgregarProductoDialog.getArticulosSeleccionados().size()>0){
					for(Articulo artic: AgregarProductoDialog.getArticulosSeleccionados()){
						compra.agregarArticulo(artic);
					}
					ReloadAll();
				}
			}
		});
		btnBuscarArticulos.setBounds(10, 13, 148, 23);
		contentPane.add(btnBuscarArticulos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion =JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (opcion == 0){
					AgregarProductoDialog.vaciarArticulosSeleccionados();
					CompraFrame.this.dispose();
				}
			}
		});
		btnSalir.setBounds(419, 297, 127, 30);
		getContentPane().add(btnSalir);
		
		JLabel lblNotaborrararticulo = new JLabel("*Para borrar un articulo use la tecla \"Delete\"");
		lblNotaborrararticulo.setVerticalAlignment(SwingConstants.TOP);
		lblNotaborrararticulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNotaborrararticulo.setBounds(307, 206, 239, 14);
		contentPane.add(lblNotaborrararticulo);
		
		btnRealizarCompra = new JButton("Realizar Compra");
		btnRealizarCompra.setName("btnRealizarCompra");
		btnRealizarCompra.setBounds(282, 297, 127, 30);
		btnRealizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AgregarProductoDialog.getArticulosSeleccionados().size()>0){
					
					int opcion =JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea realizar la compra?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (opcion == 0){
						try{
							ReloadAll();
							compra.efectuar();
							
							AgregarProductoDialog.vaciarArticulosSeleccionados();
							
							CompraFrame.this.dispose();
							JOptionPane.showMessageDialog(null, "La compra se ha realizado correctamente!");
						}catch(Exception ex){
							
						}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "No hay articulos para realizar la compra.", "Alerta!", JOptionPane.ERROR_MESSAGE);
			}
		});
		contentPane.add(btnRealizarCompra);
		
		lblSuplidor = new JLabel("Suplidor:");
		lblSuplidor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSuplidor.setBounds(307, 14, 62, 14);
		contentPane.add(lblSuplidor);
		
		btnVaciarArticulos = new JButton("Vaciar articulos");
		btnVaciarArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.setModel(new DefaultTableModel(columnas, 0));
				
				for(Articulo art:AgregarProductoDialog.getArticulosSeleccionados()){
					compra.retirarArticulo(art);
				}
				
				AgregarProductoDialog.vaciarArticulosSeleccionados();
				
				tabla.getColumnModel().getColumn(0).setPreferredWidth(76);
				tabla.getColumnModel().getColumn(1).setPreferredWidth(323);
				ReloadAll();
			}
		});
		btnVaciarArticulos.setName("btnVaciarArticulos");
		btnVaciarArticulos.setBounds(407, 53, 139, 23);
		contentPane.add(btnVaciarArticulos);
		
		//////Para eliminar articulos////
		  int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		  InputMap inputMap = tabla.getInputMap(condition);
		  ActionMap actionMap = tabla.getActionMap();
		  
		  final String DELETE = "Delete";

		  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE);
		  actionMap.put(DELETE, new AbstractAction() {
		     public void actionPerformed(ActionEvent e) {
		    	 
		    	 if(tabla.getSelectedRow() >=0){
			    	 
			    	 Articulo articuloABorrar= new Articulo ();
			    	 String productoDescripcion = (String) tabla.getValueAt(tabla.getSelectedRow(), 1);
			    	 
			         
			         String[] campos =  {"producto"};
			         Object[][] datos = Utilidades.listToBidiArray(AgregarProductoDialog.getArticulosSeleccionados(), campos);
			 		
			 		for(int i = 0; i< datos.length; i++){
			 			if (productoDescripcion == AgregarProductoDialog.getArticulosSeleccionados().get(i).getProducto().getDescripcion())
			 				articuloABorrar = AgregarProductoDialog.getArticulosSeleccionados().get(i);
			 		}
			 		
			 		compra.retirarArticulo(articuloABorrar);
			 		AgregarProductoDialog.getArticulosSeleccionados().remove(articuloABorrar);
			 		ReloadAll();
		    	 }
		     }
		  });

	}
	
	public AgregarProductoDialog getAgregarProductoDialog(){
		return this.agregarProducto;
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
