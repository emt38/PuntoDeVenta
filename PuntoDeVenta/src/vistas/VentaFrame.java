package vistas;

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
import modelos.Cliente;
import modelos.Venta;
import modelos.Producto;
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

public class VentaFrame extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JScrollPane scrollPane;
	private JLabel lblTotalImpuesto;
	private JLabel lblTotalDescuento;
	private JLabel lblTotal;
	private JLabel lblEfectivoRecibido;
	private JLabel lblCambioDevuelto;
	private JTextField txtTotalImpuestos = new JTextField();
	private JTextField txtTotalDescuento = new JTextField();
	private JTextField txtTotal = new JTextField();
	private JTextField txtCambiardesc = new JTextField();
	private JTextField txtEfectivoRecibido = new JTextField();
	private JTextField txtCambioDevuelto = new JTextField();
	private Venta venta = new Venta();
	
	String[] columnas = {"Cantidad", "Producto", "Costo", "Impuestos", "Subtotal"};
	
	Cliente cliente = new Cliente();
	List<Cliente> clientes = new Cliente().listar("ORDER BY Nombre");
	JComboBox<ComboItem> cbbxClientes = new JComboBox<ComboItem>();
	private JLabel lblCliente;
	private JLabel lblNota;
	private JTextField txtAgregar;
	
	private void ReloadAll() {
		
		if( clientes.size() > 0 ){	
			cbbxClientes = new JComboBox<ComboItem>();
			for(Cliente clint: clientes){
				cbbxClientes.addItem(new ComboItem(clint.getNombre(),clint));
			}
			Object item = cbbxClientes.getSelectedItem();
			Object objeto = ((ComboItem)item).getObjeto();
			cliente = (Cliente) objeto;
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
		
		txtEfectivoRecibido.setText(Integer.toString(venta.getEfectivoRecibido()));
		
		txtTotalDescuento.setText(String.format("%.2f", venta.getDescuentos()));
		txtTotalImpuestos.setText(String.format("%.2f", venta.getImpuestos()));
		txtTotal.setText(String.format("%.2f", venta.getTotal()));
	}
	
	public VentaFrame() {
		setTitle("Venta");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 654, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		scrollPane.setBounds(10, 86, 618, 227);
		contentPane.add(scrollPane);
		
		ReloadAll();
		
		cbbxClientes.setBounds(458, 11, 167, 25);
		contentPane.add(cbbxClientes);
		
		lblTotalImpuesto = new JLabel("Total impuesto: ");
		lblTotalImpuesto.setBounds(402, 357, 130, 14);
		contentPane.add(lblTotalImpuesto);
		
		lblTotalDescuento = new JLabel("Total descuento: ");
		lblTotalDescuento.setBounds(402, 392, 130, 14);
		contentPane.add(lblTotalDescuento);
		
		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(402, 428, 130, 14);
		contentPane.add(lblTotal);
		
		lblEfectivoRecibido = new JLabel("Efectivo recibido: ");
		lblEfectivoRecibido.setBounds(10, 324, 118, 14);
		contentPane.add(lblEfectivoRecibido);
		
		lblCambioDevuelto = new JLabel("Cambio devuelto: ");
		lblCambioDevuelto.setBounds(10, 357, 118, 14);
		contentPane.add(lblCambioDevuelto);
		
		txtCambioDevuelto = new JTextField();
		txtCambioDevuelto.setEditable(false);
		txtCambioDevuelto.setBounds(138, 352, 86, 25);
		contentPane.add(txtCambioDevuelto);
		txtCambioDevuelto.setColumns(10);
		
		txtEfectivoRecibido = new JTextField();
		txtEfectivoRecibido.setBounds(138, 321, 86, 25);
		contentPane.add(txtEfectivoRecibido);
		txtEfectivoRecibido.setColumns(10);
		//Para tomar el Enter
		txtEfectivoRecibido.addActionListener(new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        if(isFloat(txtEfectivoRecibido.getText())){
		        	venta.setEfectivoRecibido(new Float(Float.parseFloat(txtEfectivoRecibido.getText().replaceAll(",", "."))).intValue());
		        	if((venta.getEfectivoRecibido())>(venta.getTotal())){
		        		venta.setCambioDevuelto(new Float(venta.getEfectivoRecibido() - venta.getTotal()).intValue());
		        		txtCambioDevuelto.setText(Integer.toString(venta.getCambioDevuelto()));		        		
		        	}
		        	else
		        		txtCambioDevuelto.setText("0");
		        		
		        }
		    }
		});
		
		txtTotalImpuestos = new JTextField();
		txtTotalImpuestos.setBounds(542, 352, 86, 25);
		contentPane.add(txtTotalImpuestos);
		txtTotalImpuestos.setColumns(10);
		txtTotalImpuestos.setEditable(false);
		txtTotalImpuestos.setText(String.format("%.2f", venta.getImpuestos()));
		
		txtTotalDescuento = new JTextField();
		txtTotalDescuento.setBounds(542, 387, 86, 25);
		contentPane.add(txtTotalDescuento);
		txtTotalDescuento.setColumns(10);
		txtTotalDescuento.setEditable(false);
		txtTotalDescuento.setText(String.format("%.2f", venta.getDescuentos()));
		
		txtTotal = new JTextField();
		txtTotal.setBounds(542, 423, 86, 25);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setEditable(false);
		txtTotal.setText(String.format("%.2f", venta.getTotal()));
		
		JButton btnBuscarArticulos = new JButton("Buscar Articulos");
		btnBuscarArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarProductoDialog agregarProducto = new AgregarProductoDialog(tabla);
				agregarProducto.setModal(true);
				agregarProducto.setVisible(true);
				agregarProducto.setResizable(false);
								
				if(AgregarProductoDialog.getArticulosSeleccionados().size()>0){
					for(Articulo artic: AgregarProductoDialog.getArticulosSeleccionados()){
						venta.agregarArticulo(artic);
					}
					ReloadAll();
				}
			}
		});
		btnBuscarArticulos.setBounds(10, 11, 148, 23);
		contentPane.add(btnBuscarArticulos);
		
		JButton btnRealizarVenta = new JButton("Realizar Venta");
		btnRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if((venta.getEfectivoRecibido())>(venta.getTotal())){
					
					int opcion =JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea realizar la venta?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (opcion == 0){
						try{
							ReloadAll();
							venta.setCliente(cliente);
							venta.setCajero(Program.getLoggedUser());
							venta.setTerminalVentas(0);
							venta.setTienda(Program.getLoggedUser().getTienda());
							venta.efectuar();
							JOptionPane.showMessageDialog(null, "La venta se realizao correctamente!");
						}catch(Exception e){
						}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "El monto recibido no es suficiente para realizar la compra.", "Alerta!", JOptionPane.ERROR_MESSAGE);

				
			}
		});
		btnRealizarVenta.setBounds(364, 457, 127, 30);
		contentPane.add(btnRealizarVenta);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion =JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?","Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (opcion == 0){
					VentaFrame.this.dispose();
				}
			}
		});
		btnSalir.setBounds(501, 457, 127, 30);
		getContentPane().add(btnSalir);
		
		JLabel lblusarEnter = new JLabel("*Usar Enter");
		lblusarEnter.setHorizontalAlignment(SwingConstants.LEFT);
		lblusarEnter.setBounds(234, 321, 86, 14);
		contentPane.add(lblusarEnter);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(402, 14, 46, 14);
		contentPane.add(lblCliente);
		
		lblNota = new JLabel("*Para borrar un articulo use Delete.");
		lblNota.setVerticalAlignment(SwingConstants.TOP);
		lblNota.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNota.setBounds(383, 317, 245, 14);
		contentPane.add(lblNota);
		
		JButton btnVaciarArticulos = new JButton("Vaciar articulos");
		btnVaciarArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabla.setModel(new DefaultTableModel(columnas, 0));
				
				for(Articulo art:AgregarProductoDialog.getArticulosSeleccionados()){
					venta.retirarArticulo(art);
				}
				
				AgregarProductoDialog.getArticulosSeleccionados().clear();
				
				tabla.getColumnModel().getColumn(0).setPreferredWidth(76);
				tabla.getColumnModel().getColumn(1).setPreferredWidth(323);
				ReloadAll();
			}
		});
		btnVaciarArticulos.setBounds(489, 52, 139, 23);
		contentPane.add(btnVaciarArticulos);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtAgregar.getText().length()>0){
					List<Producto> tempProductos = new Producto().listar("WHERE p.codigo = '" + txtAgregar.getText() + "'");
					
					if(tempProductos.size()>0){
						Producto producto = tempProductos.get(0);
						
						Articulo articuloTemp = new Articulo();
						articuloTemp.setCantidad(1);
						articuloTemp.setProducto(producto);
						articuloTemp.setValor(producto.getPrecio());
						articuloTemp.setTasaImpuestos(producto.getTasaImpuesto());
						articuloTemp.totalizar();
						
						AgregarProductoDialog.getArticulosSeleccionados().add(articuloTemp);
						venta.agregarArticulo(articuloTemp);
						
						ReloadAll();
					}
				}
				
			}
		});
		btnAgregar.setBounds(175, 52, 89, 23);
		contentPane.add(btnAgregar);
		
		txtAgregar = new JTextField();
		txtAgregar.setBounds(79, 50, 86, 25);
		contentPane.add(txtAgregar);
		txtAgregar.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(20, 51, 58, 25);
		contentPane.add(lblCodigo);
		
		
		//////Para eliminar articulos///////
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
			 		
			 		AgregarProductoDialog.getArticulosSeleccionados().remove(articuloABorrar);
			 		venta.retirarArticulo(articuloABorrar);
			 		ReloadAll();
		    	 }
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
					VentaFrame frame = new VentaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


class ComboItem
{
    private String descripcion;
    private Object objeto;

    public ComboItem(String descripcion, Object objeto)
    {
        this.descripcion = descripcion;
        this.objeto = objeto;
    }

    @Override
    public String toString()
    {
        return descripcion;
    }

    public String Getdescripcion()
    {
        return descripcion;
    }

    public Object getObjeto()
    {
        return objeto;
    }
}