package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import modelos.Suplidor;
import principal.Utilidades;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
	private JButton btnRefrescar;
	
	protected void ReloadAll() {
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
		
		txtTotal = new JTextField();
		txtTotalImpuestos = new JTextField();
		txtTotalDescuento = new JTextField();
		
	}
	
	public CompraFrame() {
		setTitle("Compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 376);
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
				if (isNumeric(txtCambiardesc.getText())){
					compra.setDescuentos(Float.parseFloat(txtCambiardesc.getText()));
					txtTotalDescuento.setText(String.format("%.2f", compra.getDescuentos()));
				}
				
			}
		});
		
		JButton btnBuscarArticulo = new JButton("Buscar Articulo");
		btnBuscarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarArticuloDialog agregarArticulo = new AgregarArticuloDialog();
				agregarArticulo.setVisible(true);
				agregarArticulo.setResizable(false);
			}
		});
		btnBuscarArticulo.setBounds(10, 13, 148, 23);
		contentPane.add(btnBuscarArticulo);
		
		
		
		List<Suplidor> suplidores = new ArrayList<>();
		Suplidor defaultSuplidor = new Suplidor();
		defaultSuplidor.setNombre("Suplidor no identificado");
		suplidores.add(defaultSuplidor);
		suplidores.addAll(new Suplidor().listar("ORDER BY Nombre"));
		
		
		JComboBox cbxSuplidores = new JComboBox(suplidores.toArray(new Suplidor[0]));
		cbxSuplidores.setSelectedIndex(0);
		cbxSuplidores.setBounds(379, 11, 167, 20);
		contentPane.add(cbxSuplidores);
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setBounds(210, 10, 90, 28);
		contentPane.add(btnRefrescar);
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				compra.agregarArticulo((Articulo) objeto);
				ReloadAll();
				System.out.println("Se refresco!");
			}
		});
		
		
	}
	
	public static boolean isNumeric(String cadena){
		try {
			Float.parseFloat(cadena);
			return true;
		}
		catch (NumberFormatException nfe){
			return false;
		}
	}
	
	static Object objeto;
	public static void pasarObjeto(Object object){
		objeto = object;
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
