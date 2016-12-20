package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import modelos.Articulo;
import modelos.DevolucionVenta;
import modelos.Producto;
import modelos.Venta;
import principal.Utilidades;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class DevolucionVentaFrame extends JFrame {
	private JTable tblVenta;
	private JTable tblDevolucion;

	private DevolucionVenta devolucion = new DevolucionVenta();
	private JButton btnDevolver;
	private JButton btnProcesarDevolucion;
	private JTextField txtTotal;
	private JTextField txtCantidad;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		principal.Program.main(new String[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DevolucionVentaFrame frame = new DevolucionVentaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void recargarTablas() {
		String[] columnas = new String[] { "cantidad", "producto", "valor", "subtotal" };
		
		Object[][] articulosVenta = Utilidades.listToBidiArray(devolucion.getVenta().getArticulos(), columnas);
		Object[][] articulosDevolucion = Utilidades.listToBidiArray(devolucion.getArticulos(), columnas);
		
		tblVenta.setModel(new DefaultTableModel(
			articulosVenta,
			new String[] {
				"Cantidad", "Producto", "Valor", "SubTotal"
			}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblVenta.getColumnModel().getColumn(0).setPreferredWidth(97);
		tblVenta.getColumnModel().getColumn(1).setPreferredWidth(311);
		tblVenta.getColumnModel().getColumn(2).setPreferredWidth(103);
		tblVenta.getColumnModel().getColumn(3).setPreferredWidth(107);
		
		tblDevolucion.setModel(new DefaultTableModel(
				articulosDevolucion,
				new String[] {
					"Cantidad", "Producto", "Valor", "SubTotal"
				}
			) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
		
		tblDevolucion.getColumnModel().getColumn(0).setPreferredWidth(97);
		tblDevolucion.getColumnModel().getColumn(1).setPreferredWidth(311);
		tblDevolucion.getColumnModel().getColumn(2).setPreferredWidth(103);
		tblDevolucion.getColumnModel().getColumn(3).setPreferredWidth(107);
		
		txtTotal.setText(String.format("%.2f", devolucion.calcularTotal()));
		btnProcesarDevolucion.setEnabled(devolucion.getArticulos().size() > 0);
	}

	/**
	 * Create the frame.
	 */
	public DevolucionVentaFrame() {
		setTitle("Devoluci\u00F3n de Ventas");
		setBounds(100, 100, 579, 474);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("#Venta");
		lblNewLabel.setBounds(21, 32, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JTextField txtVenta = new JTextField();
		txtVenta.setBounds(73, 25, 81, 28);
		txtVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtVenta);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = txtVenta.getText();
				if(txt.length() <= 0)
					JOptionPane.showMessageDialog(DevolucionVentaFrame.this, "Debe introducir una venta válida.");
				
				int no = Integer.parseInt(txt);
				
				Venta venta = /*new Venta().buscar(no);*/ new Venta();
				venta.agregarArticulo(new Articulo(new Producto(0, "0394934", "Kakatalina", 100f, 100f, 0.18f, 0f), 5f, 100f, 0.18f, 18f, 500f));
				venta.agregarArticulo(new Articulo(new Producto(0, "3453446", "Lagartoner", 100f, 100f, 0.18f, 0f), 5f, 100f, 0.18f, 18f, 500f));
				venta.agregarArticulo(new Articulo(new Producto(0, "6564564", "Gatatastic", 100f, 100f, 0.18f, 0f), 5f, 100f, 0.18f, 18f, 500f));
				
				if(venta != null) {
					devolucion.setVenta(venta);
					recargarTablas();
				} else {
					JOptionPane.showMessageDialog(DevolucionVentaFrame.this, "No se encontró la venta especificada.");
					txtVenta.setText("");
				}
			}
		});
		btnCargar.setBounds(157, 25, 66, 28);
		getContentPane().add(btnCargar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 64, 532, 129);
		getContentPane().add(scrollPane);
		
		tblVenta = new JTable();
		tblVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnDevolver.setEnabled(true);
			}
		});
		tblVenta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cantidad", "Producto", "Valor", "SubTotal"
			}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblVenta.getColumnModel().getColumn(0).setPreferredWidth(97);
		tblVenta.getColumnModel().getColumn(1).setPreferredWidth(311);
		tblVenta.getColumnModel().getColumn(2).setPreferredWidth(103);
		tblVenta.getColumnModel().getColumn(3).setPreferredWidth(107);
		scrollPane.setViewportView(tblVenta);
		
		btnDevolver = new JButton("Devolver Art\u00EDculo \u2193");
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Articulo temp = devolucion.getVenta().getArticulos().get(tblVenta.getSelectedRow());
				devolucion.devolverArticulo(temp, Float.parseFloat(txtCantidad.getText()));
				recargarTablas();
			}
		});
		btnDevolver.setEnabled(false);
		btnDevolver.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnDevolver.setBounds(21, 198, 347, 36);
		getContentPane().add(btnDevolver);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 245, 532, 129);
		getContentPane().add(scrollPane_1);
		
		tblDevolucion = new JTable();
		tblDevolucion.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cantidad", "Producto", "Valor", "SubTotal"
			}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblDevolucion.getColumnModel().getColumn(0).setPreferredWidth(97);
		tblDevolucion.getColumnModel().getColumn(1).setPreferredWidth(311);
		tblDevolucion.getColumnModel().getColumn(2).setPreferredWidth(103);
		tblDevolucion.getColumnModel().getColumn(3).setPreferredWidth(107);
		scrollPane.setViewportView(tblVenta);
		scrollPane_1.setViewportView(tblDevolucion);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
					e.consume();
				}
			}
		});
		txtCantidad.setText("1");
		txtCantidad.setBounds(447, 203, 106, 28);
		getContentPane().add(txtCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(380, 209, 55, 16);
		getContentPane().add(lblCantidad);
		
		btnProcesarDevolucion = new JButton("Procesar Devoluci\u00F3n");
		btnProcesarDevolucion.setEnabled(false);
		btnProcesarDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnProcesarDevolucion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnProcesarDevolucion.setBounds(21, 386, 532, 36);
		getContentPane().add(btnProcesarDevolucion);
		
		txtTotal = new JTextField();
		txtTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		txtTotal.setText("0.00");
		txtTotal.setBounds(380, 25, 173, 28);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);

	}
}
