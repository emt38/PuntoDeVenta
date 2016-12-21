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
	
	Cliente cliente = new Cliente();
	List<Cliente> clientes = new Cliente().listar("ORDER BY Nombre");
	JComboBox<ComboItem> cbbxClientes = new JComboBox<ComboItem>();
	private JLabel lblCliente;
	private JLabel lblNota;
	
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
		
		String[] columnas = {"Cantidad", "Producto", "Costo", "Impuestos", "Subtotal"};
		String[] campos = {"cantidad", "producto","valor", "impuestos", "subtotal"};  
		Object[][] datos = Utilidades.listToBidiArray(venta.getArticulos(), campos);
		
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
		setBounds(100, 100, 572, 400);
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
		scrollPane.setBounds(10, 86, 536, 112);
		contentPane.add(scrollPane);
		
		ReloadAll();
		
		cbbxClientes.setBounds(379, 11, 167, 20);
		contentPane.add(cbbxClientes);
		
		lblTotalImpuesto = new JLabel("Total impuesto: ");
		lblTotalImpuesto.setBounds(320, 236, 104, 14);
		contentPane.add(lblTotalImpuesto);
		
		lblTotalDescuento = new JLabel("Total descuento: ");
		lblTotalDescuento.setBounds(320, 261, 104, 14);
		contentPane.add(lblTotalDescuento);
		
		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(320, 286, 104, 14);
		contentPane.add(lblTotal);
		
		lblEfectivoRecibido = new JLabel("Efectivo recibido: ");
		lblEfectivoRecibido.setBounds(10, 212, 86, 14);
		contentPane.add(lblEfectivoRecibido);
		
		lblCambioDevuelto = new JLabel("Cambio devuelto: ");
		lblCambioDevuelto.setBounds(10, 237, 96, 14);
		contentPane.add(lblCambioDevuelto);
		
		txtCambioDevuelto = new JTextField();
		txtCambioDevuelto.setEditable(false);
		txtCambioDevuelto.setBounds(106, 234, 86, 20);
		contentPane.add(txtCambioDevuelto);
		txtCambioDevuelto.setColumns(10);
		
		txtEfectivoRecibido = new JTextField();
		txtEfectivoRecibido.setBounds(106, 209, 86, 20);
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
		        	venta.setCambioDevuelto(new Float(venta.getEfectivoRecibido() - venta.getTotal()).intValue());
		        	txtCambioDevuelto.setText(Integer.toString(venta.getCambioDevuelto()));
		        }
		    }
		});
		
		txtTotalImpuestos = new JTextField();
		txtTotalImpuestos.setBounds(460, 233, 86, 20);
		contentPane.add(txtTotalImpuestos);
		txtTotalImpuestos.setColumns(10);
		txtTotalImpuestos.setEditable(false);
		txtTotalImpuestos.setText(String.format("%.2f", venta.getImpuestos()));
		
		txtTotalDescuento = new JTextField();
		txtTotalDescuento.setBounds(460, 258, 86, 20);
		contentPane.add(txtTotalDescuento);
		txtTotalDescuento.setColumns(10);
		txtTotalDescuento.setEditable(false);
		txtTotalDescuento.setText(String.format("%.2f", venta.getDescuentos()));
		
		txtTotal = new JTextField();
		txtTotal.setBounds(460, 283, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setEditable(false);
		txtTotal.setText(String.format("%.2f", venta.getTotal()));
		
		JButton btnAgregarArticulo = new JButton("Agregar Articulo");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarArticuloDialog agregarArticulo = new AgregarArticuloDialog();
				agregarArticulo.setModal(true);
				agregarArticulo.setVisible(true);
				agregarArticulo.setResizable(false);
				
				if(agregarArticulo.articuloAgregado){
					for(Articulo artic: (List<Articulo>)(AgregarArticuloDialog.pasarObjeto())){
						venta.agregarArticulo(artic);
					}
					ReloadAll();
				}
			}
		});
		btnAgregarArticulo.setBounds(10, 52, 148, 23);
		contentPane.add(btnAgregarArticulo);
		
		JButton btnRealizarVenta = new JButton("Realizar Venta");
		btnRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		btnRealizarVenta.setBounds(282, 321, 127, 30);
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
		btnSalir.setBounds(419, 321, 127, 30);
		getContentPane().add(btnSalir);
		
		JLabel lblusarEnter = new JLabel("*Usar Enter");
		lblusarEnter.setHorizontalAlignment(SwingConstants.LEFT);
		lblusarEnter.setBounds(202, 209, 86, 14);
		contentPane.add(lblusarEnter);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(323, 14, 46, 14);
		contentPane.add(lblCliente);
		
		lblNota = new JLabel("*Para borrar un articulo use Delete.");
		lblNota.setVerticalAlignment(SwingConstants.TOP);
		lblNota.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNota.setBounds(349, 205, 197, 14);
		contentPane.add(lblNota);
		
		
		//////Para eliminar articulos///////
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
		         Object[][] datos = Utilidades.listToBidiArray(venta.getArticulos(), campos);
		 		
		 		for(int i = 0; i< datos.length; i++){
		 			if (productoDescripcion == venta.getArticulos().get(i).getProducto().getDescripcion())
		 				articuloABorrar = venta.getArticulos().get(i);
		 		}
		 		
		 		venta.retirarArticulo(articuloABorrar);
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